package com.jakka;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 메인 페이지를 처리하는 서블릿 클래스입니다.
 * 
 * @author Jakka
 */
@WebServlet("/index.do")
public class Index extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      I/O 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(req, resp);
	}
	
}//End of class
