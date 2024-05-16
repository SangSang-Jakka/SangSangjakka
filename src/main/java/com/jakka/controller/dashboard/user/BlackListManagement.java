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
 * BlackListManagement 서블릿은 블랙리스트 관리 기능을 제공합니다.
 */
@WebServlet("/admin/dashboard/blacklist/manage.do")
public class BlackListManagement extends HttpServlet {

	/**
     * GET 요청을 처리합니다.
     * 블랙리스트에 있는 사용자 목록을 조회하여 JSP 페이지로 전달합니다.
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

		ArrayList<UserDTO> list = userDAO.findAllBlackList();

		req.setAttribute("userList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_user/blacklist_manage.jsp");
		dispatcher.forward(req, resp);

	}
	
	/**
     * POST 요청을 처리합니다.
     * 선택한 사용자를 블랙리스트에서 복원합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("hello");
	
		req.setCharacterEncoding("UTF-8");
		
		
		
		String userSeq = req.getParameter("userSeq");
		System.out.println(userSeq);
		

		UserDAO dao = DAOManager.getUserDAO();
		
		
		//String userSeq, String adId
		
		int result = dao.blaklistReStore(userSeq);
		System.out.println(result);
		
		if (result == 1) {
			//resp.sendRedirect("/toy/board/list.do");
			resp.sendRedirect("/sangsangjakka/admin/dashboard/blacklist/manage.do");
		}
	}

}// End