package com.jakka.controller.member.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dao.admin.AdminDAOImpl;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dto.admin.AdminDTO;

@WebServlet("/admin/log.do")
public class AdminLog extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		AdminDAO dao = DAOManager.getAdminDAO();
		ArrayList<AdminDTO> list = dao.findAll();

		req.setAttribute("adminList", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_log.jsp");
		dispatcher.forward(req, resp);
	}
	
}//End of class
