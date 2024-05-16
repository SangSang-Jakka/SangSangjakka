package com.jakka.controller.dashboard.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

/**
 * UserManagement 서블릿은 사용자 관리 기능을 제공합니다.
 */
@WebServlet("/admin/dashboard/user/manage.do")
public class UserManagement extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 전체 사용자 목록을 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		UserDAO userDAO = DAOManager.getUserDAO();
		
		ArrayList<UserDTO> list = userDAO.findAll();
		System.out.println(list);
		
		req.setAttribute("userList", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_user/user_manage.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	
}//End of class
