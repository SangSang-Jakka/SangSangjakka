package com.jakka.controller.member.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dto.admin.AdminDTO;


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
				
				req.setCharacterEncoding("UTF-8");
				
				
		
				
				String adPw = req.getParameter("adPw");
				String adId = req.getParameter("adId");
				
				AdminDAO dao = DAOManager.getAdminDAO();
				
				AdminDTO dto = new AdminDTO();
				
				
				dto.setAdPw(adPw);
				dto.setAdId(adId);
				
				int result = dao.savePw(dto);
				
				if (result == 1) {
					//resp.sendRedirect("/toy/board/list.do");
					resp.sendRedirect("/sangsangjakka/admin/view.do?id=" + adId);
				}
				
				
	}
	
}//End of class
