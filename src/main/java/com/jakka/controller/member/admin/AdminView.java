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
import com.jakka.model.dto.admin.AdminDTO;

/**
 * AdminView 서블릿은 관리자 개인 정보 상세 조회 기능을 제공합니다.
 */
@WebServlet("/admin/view.do")
public class AdminView extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 선택한 관리자의 개인 정보를 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		AdminDAO dao = DAOManager.getAdminDAO();
		
		AdminDTO view = dao.findById(id);
	
		req.setAttribute("adminview", view);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_view.jsp");
		dispatcher.forward(req, resp);
	}
	
}//End of class

