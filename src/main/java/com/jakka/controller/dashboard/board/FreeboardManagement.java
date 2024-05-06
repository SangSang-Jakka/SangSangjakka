package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dto.board.BoardDTO;

@WebServlet("/admin/dashboard/freeboard/manage.do")
public class FreeboardManagement extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		BoardDAO boardDAO = DAOManager.getBoardDAO();
		
		// 자유게시판 목록
		ArrayList<BoardDTO> list = boardDAO.findAll();
		
		req.setAttribute("boardList", list);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/freeboard_manage.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}//End of class
