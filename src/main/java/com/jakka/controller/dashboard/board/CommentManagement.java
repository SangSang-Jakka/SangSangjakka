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
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dto.board.BoardCommentDTO;

@WebServlet("/admin/dashboard/comment/manage.do")
public class CommentManagement extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		BoardCommentsDAO dao = DAOManager.getBoardCommentDAO();

		// 전체 댓글 목록

		ArrayList<BoardCommentDTO> list = dao.findAll();

		req.setAttribute("cmntList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/comment_manage.jsp");
		dispatcher.forward(req, resp);

	}

}
