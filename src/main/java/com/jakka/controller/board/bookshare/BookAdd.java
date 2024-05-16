package com.jakka.controller.board.bookshare;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 동화책 추가 페이지를 처리하는 서블릿 클래스입니다.
 * 
 * @author Jakka
 */
@WebServlet("/board/book/add.do")
public class BookAdd extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookshare/bookshare_view.jsp");
		dispatcher.forward(req, resp);
		
	}
	

	
	
}//End of class
