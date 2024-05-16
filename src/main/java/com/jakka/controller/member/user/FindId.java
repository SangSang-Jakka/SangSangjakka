package com.jakka.controller.member.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FindId 서블릿은 사용자 아이디 찾기 기능을 제공합니다.
 */
@WebServlet("/user/find_id.do")
public class FindId extends HttpServlet{

	/**
     * GET 요청을 처리합니다.
     * 아이디 찾기 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/find_id.jsp");
		dispatcher.forward(req, resp);
	}
	
}
