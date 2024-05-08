package com.jakka.controller.board.suggestion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.SuggestionDTO;

@WebServlet("/board/suggestion/View.do")

public class SuggestionView extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 로그인 여부 확인
		String userId = (String)session.getAttribute("userId");
		
		if(userId == null) {
			// 비로그인 상태인 경우 로그인 페이지로 리다이렉션
			resp.sendRedirect("/sangsangjakka/user/login.do");
			return;
		}
		String page = req.getParameter("page");
		
		SuggestionDAO dao = DAOManager.getSuggestionDAO();

		
		// 조회수 증가
		if(session.getAttribute("read") != null && session.getAttribute("read").toString().equals("n")) {
			dao.addCnt(page);
			session.setAttribute("read", "y");
		}
		
		SuggestionDTO dto = dao.findById(page);
		
		//게시글 조작
		dto.setSgstTitle(dto.getSgstTitle().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
		dto.setSgstContents(dto.getSgstContents().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
				
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/suggestion/suggestion_view.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}


