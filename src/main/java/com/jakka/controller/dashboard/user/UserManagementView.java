package com.jakka.controller.dashboard.user;

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
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.admin.AdminDTO;
import com.jakka.model.dto.user.UserDTO;

/**
 * UserManagementView 서블릿은 사용자 상세 정보 조회와 비활성화 기능을 제공합니다.
 */
@WebServlet("/admin/dashboard/user/manageview.do")
public class UserManagementView extends HttpServlet {
	
	/**
     * GET 요청을 처리합니다.
     * 선택한 사용자의 상세 정보와 블랙리스트 여부를 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String userId = req.getParameter("id");
		
		UserDAO dao = DAOManager.getUserDAO();
		UserDTO dto = dao.findById(userId);
		
		
		// 차단된 회원 확인
		int blacklistSeq = dao.findBlacklistSeq(userId);
		
		req.setAttribute("user", dto);
		req.setAttribute("blacklistSeq", blacklistSeq);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_user/user_manage_view.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	/**
     * POST 요청을 처리합니다.
     * 선택한 사용자를 비활성화합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("hello");
		
		
				HttpSession session = req.getSession();
				String adId = (String)session.getAttribute("adId");
				
				req.setCharacterEncoding("UTF-8");
				
				String userSeq = req.getParameter("userSeq");
				String userId = req.getParameter("userId");

				UserDAO dao = DAOManager.getUserDAO();
				
				int result = dao.disable(userSeq, adId);
				
				if (result == 1) {
					//resp.sendRedirect("/toy/board/list.do");
					resp.sendRedirect("/sangsangjakka/admin/dashboard/user/manage.do");
				}
		
	}

}//End of class
