package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.NoticeDAO;

@WebServlet("/admin/dashboard/freeboard/managedel.do")
public class FreeboardManagementDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/freeboard_manage_view.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String boardSeq = req.getParameter("boardSeq");
		
		
        resp.setContentType("text/plain");
        PrintWriter writer = resp.getWriter();
        writer.write("success"); // 성공 시 'success' 응답
        writer.close();
        
		/*
		// 세션에서 로그인한 아이디 가져오기
		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");

		// 데이터 가져오기
		String boardSeq = req.getParameter("boardSeq");

		BoardDAO boardDAO = DAOManager.getBoardDAO();

		int result = boardDAO.remove(boardSeq);

		if (result > 0) {
			// 삭제 성공
			resp.getWriter().write("success");

		} else {
			resp.getWriter().write("fail");

		}
		
		*/
		

	}

}
