package com.jakka.controller.member.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dto.admin.AdminDTO;

/**
 * AdminLogin 서블릿은 관리자 로그인 기능을 제공합니다.
 */
@WebServlet("/admin/login.do")
public class AdminLogin extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 관리자 로그인 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Login.java
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_login.jsp");
		dispatcher.forward(req, resp);
	}
	
	/**
     * POST 요청을 처리합니다.
     * 관리자 로그인을 처리합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String adId = req.getParameter("adId");
		String adPw = req.getParameter("adPw");
		
		AdminDAO adminDAO = DAOManager.getAdminDAO();
		AdminDTO dto = new AdminDTO();
		
		dto.setAdId(adId);
		dto.setAdPw(adPw);
		
		AdminDTO result = adminDAO.login(dto);
		
		if (result != null) {
			//인증 처리
			
			HttpSession session = req.getSession();
			
			session.setAttribute("adId", adId); //인증 티켓
			session.setAttribute("adNick", result.getAdNick());
			session.setAttribute("adLv", result.getAdLv());
			
			// System.out.println("adLv: " + result.getAdLv());
			
			resp.sendRedirect("/sangsangjakka/admin/dashboard.do");
			
		} else {
			//실패 처리
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			
			PrintWriter writer = resp.getWriter();
		    writer.println("<script>alert('로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.');</script>");
		    writer.println("<script>location.href='/sangsangjakka/admin/login.do';</script>"); // 로그인 페이지로 이동
			writer.close();
			
			
		}
		
	}
	
}//End of class
