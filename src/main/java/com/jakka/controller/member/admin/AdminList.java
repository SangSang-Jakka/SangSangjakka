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

@WebServlet("/admin/list.do")
public class AdminList extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. DB 작업 > select
		//2. 결과셋 > 화면 출력
		
		AdminDAO dao = DAOManager.getAdminDAO();
		ArrayList<AdminDTO> list = dao.findAll();

		req.setAttribute("adminList", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_list.jsp");
		dispatcher.forward(req, resp);
	}
	
}//End of class
