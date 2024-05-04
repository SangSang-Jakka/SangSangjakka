package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.dao.board.NoticeDAOImpl;
import com.jakka.model.dto.board.NoticeDTO;

@WebServlet("/admin/dashboard/notice/manage.do")
public class NoticeManagement extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/notice_manage.jsp");
		dispatcher.forward(req, resp);
		
		/*
		NoticeDAOImpl dao = new NoticeDAOImpl(); 
		
		ArrayList<NoticeDTO> list = dao.list(map);
		*/
		
		
		
	}
	
}//End of class
