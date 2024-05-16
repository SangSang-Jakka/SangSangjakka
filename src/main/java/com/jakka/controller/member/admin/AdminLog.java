package com.jakka.controller.member.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dao.admin.AdminDAOImpl;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dto.admin.AdminDTO;

/**
 * AdminLog 서블릿은 관리자 로그 조회 기능을 제공합니다.
 */
@WebServlet("/admin/log.do")
public class AdminLog extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 전체 관리자 로그 목록을 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		AdminDAO dao = DAOManager.getAdminDAO();
		ArrayList<AdminDTO> list = dao.findAll();

		req.setAttribute("adminList", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_log.jsp");
		dispatcher.forward(req, resp);
	}
	
}//End of class
