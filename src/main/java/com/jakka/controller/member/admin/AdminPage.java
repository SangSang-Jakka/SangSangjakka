package com.jakka.controller.member.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dto.admin.AdminDTO;

@WebServlet("/admin/mypage.do")
public class AdminPage extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 개인정보 가져오기
		HttpSession session = req.getSession();
		String adId = (String)session.getAttribute("adId");

		AdminDAO adminDAO = DAOManager.getAdminDAO();

		AdminDTO dto = adminDAO.findById(adId);
				
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_page.jsp");
		dispatcher.forward(req, resp);
	}
	
}//End of class

