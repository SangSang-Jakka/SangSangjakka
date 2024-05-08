package com.jakka.controller.dashboard.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/admin/dashboard/blacklist/manage.do")
public class BlackListManagement extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		UserDAO userDAO = DAOManager.getUserDAO();

		ArrayList<UserDTO> list = userDAO.findAllBlackList();

		req.setAttribute("userList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_user/blacklist_manage.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("hello");
	
		req.setCharacterEncoding("UTF-8");
		
		
		
		String userSeq = req.getParameter("userSeq");
		System.out.println(userSeq);
		

		UserDAO dao = DAOManager.getUserDAO();
		
		
		//String userSeq, String adId
		
		int result = dao.blaklistReStore(userSeq);
		System.out.println(result);
		
		if (result == 1) {
			//resp.sendRedirect("/toy/board/list.do");
			resp.sendRedirect("/sangsangjakka/admin/dashboard/blacklist/manage.do");
		}
	}

}// End