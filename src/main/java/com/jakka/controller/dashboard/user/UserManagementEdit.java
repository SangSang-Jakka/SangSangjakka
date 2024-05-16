package com.jakka.controller.dashboard.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.admin.AdminDTO;
import com.jakka.model.dto.user.UserDTO;

/**
 * UserManagementEdit 서블릿은 사용자 정보 수정 기능을 제공합니다.
 */
@WebServlet("/dashboard/user/edit.do")
public class UserManagementEdit extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 선택한 사용자의 정보를 조회하여 수정 페이지로 이동합니다.
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
		
		req.setAttribute("user", dto);
		System.out.println(dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_user/user_manage_edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	/**
     * POST 요청을 처리합니다.
     * 사용자 정보를 수정합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String userId = req.getParameter("userId");
		String userName = req.getParameter("userName");
		String userPw = req.getParameter("userPw");
		String userNick = req.getParameter("userNick");
		String userBirth = req.getParameter("userBirth");
		String userTel = req.getParameter("userTel");
		String userEmail = req.getParameter("userEmail");
		String userAddress = req.getParameter("userAddress");
		String userRegdate = req.getParameter("userRegdate");
		String userLimitStorage = req.getParameter("userLimitStorage");
	
		UserDAO dao = DAOManager.getUserDAO();
			
		UserDTO dto = new UserDTO();
		
		dto.setUserId(userId);
		dto.setUserPw(userPw);
		dto.setUserName(userName);
		dto.setUserNick(userNick);
		dto.setUserLeftSsn(userBirth);
		dto.setUserTel(userTel);
		dto.setUserEmail(userEmail);
		dto.setUserAddress(userAddress);
		dto.setUserRegdate(userRegdate);
		dto.setLimitStorage(userLimitStorage);
		
		int result = dao.save(dto);
		
		
		if (result == 1) {
			//resp.sendRedirect("/toy/board/list.do");
			resp.sendRedirect("/sangsangjakka/admin/dashboard/user/manageview.do?id=" + userId);
		}
		
	}
}//End of class
