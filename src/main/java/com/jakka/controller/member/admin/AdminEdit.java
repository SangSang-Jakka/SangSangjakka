package com.jakka.controller.member.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dto.admin.AdminDTO;
import com.jakka.model.dto.board.SuggestionAnswerDTO;


@WebServlet("/admin/edit.do")
public class AdminEdit extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String id = req.getParameter("id");
		System.out.println(id);
		AdminDAO dao = DAOManager.getAdminDAO();
		
		AdminDTO view = dao.findById(id);
	
		

		req.setAttribute("adminedit", view);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
			//1. 데이터 가져오기
				//2. DB 작업 > update
				//3. 결과
				
//				req.setCharacterEncoding("UTF-8");
//				
//				
//		
//				
//				String adPw = req.getParameter("adPw");
//				String adId = req.getParameter("adId");
//				
//				
//				AdminDAO dao = DAOManager.getAdminDAO();
//				
//				AdminDTO dto = new AdminDTO();
//				
//				
//				dto.setAdPw(adPw);
//				dto.setAdId(adId);
//				
//				int result = dao.savePw(dto);
//				
//				if (result == 1) {
//					//resp.sendRedirect("/toy/board/list.do");
//					resp.sendRedirect("/sangsangjakka/admin/view.do?id=" + adId);
//				}
		
		  String requestURI = req.getRequestURI();

		    if (requestURI.endsWith("/sangsangjakka/admin/edit.do")) {
		        String action = req.getParameter("action");

		        if (action != null && action.equals("delete")) {
		            String adId = req.getParameter("adId");

		            if (adId != null) {
		                // 게시물 삭제
		                AdminDAO dao = DAOManager.getAdminDAO();
		                int result = dao.remove(adId);
		                System.out.println(result);

		                JSONObject obj = new JSONObject();
		                obj.put("result", result);

		                resp.setContentType("application/json");
		                PrintWriter writer = resp.getWriter();
		                writer.print(obj);
		                writer.close();
		            }
		        } else {
		            // 다른 요청 처리 (예: 확인 버튼 클릭 시)
		            // ...
		        }
		    } else {
		        // 다른 URL 매핑 처리
		        // ...
		    }
				
				
	}
	
}//End of class
