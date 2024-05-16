package com.jakka.controller.member.admin;

import java.io.IOException;

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
 * AdminPage 서블릿은 관리자 개인 정보 조회 기능을 제공합니다.
 */
@WebServlet("/admin/mypage.do")
public class AdminPage extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 로그인한 관리자의 개인 정보를 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 개인정보 가져오기
		HttpSession session = req.getSession();
		String adId = (String)session.getAttribute("adId");

		AdminDAO adminDAO = DAOManager.getAdminDAO();

		AdminDTO dto = adminDAO.findById(adId);
				
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_page.jsp");
		dispatcher.forward(req, resp);
	}
	
}//End of class

