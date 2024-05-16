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

/**
 * AdminEdit 서블릿은 관리자 정보 수정과 삭제 기능을 제공합니다.
 */
@WebServlet("/admin/edit.do")
public class AdminEdit extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 선택한 관리자의 정보를 조회하여 수정 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
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
	

	/**
     * POST 요청을 처리합니다.
     * 선택한 관리자를 삭제합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
