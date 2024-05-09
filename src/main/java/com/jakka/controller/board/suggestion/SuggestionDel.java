package com.jakka.controller.board.suggestion;

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
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.SuggestionDTO;

@WebServlet("/board/suggestion/del.do")
public class SuggestionDel extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		System.out.println("세션 : " + session);
		String seq = req.getParameter("sgstseq");
		System.out.println("GET seq값 : " + seq);
		req.setAttribute("seq", seq);
		req.setAttribute("userId", userId);
		System.out.println(userId);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/suggestion/suggestion_del.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Logger 객체 생성
		final Logger logger = Logger.getLogger(SuggestionDel.class.getName());
		
		// 세션에서 로그인 한 아이디 찾아오기
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		
		String seq = req.getParameter("seq");
		System.out.println("post seq값 " + seq);
		
		// 아이디가 
		SuggestionDAO dao = DAOManager.getSuggestionDAO();
		
		int result = dao.del(seq);
		System.out.println(result);
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<script type='text/javascript'>");
		if(result == 1) {
			writer.println("alert('삭제가 완료되었습니다.');");
			writer.println("location.href='/sangsangjakka/board/suggestion/list.do';");
			writer.println("</script>");
		} else {
			writer.println("alert('삭제 실패, 오류입니다.');");
			writer.println("location.href='/sangsangjakka/board/suggestion/list.do';");
			writer.println("</script>");
		}
		
	}
}
