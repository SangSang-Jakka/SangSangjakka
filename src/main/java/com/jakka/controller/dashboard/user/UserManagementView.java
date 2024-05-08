package com.jakka.controller.dashboard.user;

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
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.admin.AdminDTO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/admin/dashboard/user/manageview.do")
public class UserManagementView extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String userId = req.getParameter("id");
		
		UserDAO dao = DAOManager.getUserDAO();
		UserDTO dto = dao.findById(userId);
		
		req.setAttribute("user", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_user/user_manage_view.jsp");
		dispatcher.forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("hello");
		
		
				//1. 데이터 가져오기
				//2. DB 작업 > update
				//3. 결과
		
				HttpSession session = req.getSession();
				String adId = (String)session.getAttribute("adId");
				
				System.out.println(adId);
				
				req.setCharacterEncoding("UTF-8");
		
				
				
				String userSeq = req.getParameter("userSeq");
				String userId = req.getParameter("userId");

				UserDAO dao = DAOManager.getUserDAO();
				
				
				//String userSeq, String adId
				
				int result = dao.disable(userSeq, adId);
				
				if (result == 1) {
					//resp.sendRedirect("/toy/board/list.do");
					resp.sendRedirect("/sangsangjakka/admin/dashboard/user/manage.do");
				}
		
		
		
		
		
	}

}
