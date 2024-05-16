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
 * AdminPageEdit 서블릿은 관리자 개인 정보 수정 기능을 제공합니다.
 */
@WebServlet("/admin/myedit.do")
public class AdminPageEdit extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 로그인한 관리자의 개인 정보를 조회하여 수정 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String adId = (String)session.getAttribute("adId");
		
		AdminDAO dao = DAOManager.getAdminDAO();
		
		AdminDTO view = dao.findById(adId);
	
		

		req.setAttribute("adminedit", view);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_page_edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	/**
     * POST 요청을 처리합니다.
     * 로그인한 관리자의 개인 정보를 수정합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
				//1. 데이터 가져오기
				//2. DB 작업 > update
				//3. 결과
				
				req.setCharacterEncoding("UTF-8");
		
				String adnick = req.getParameter("adNick");
				String adaddress = req.getParameter("adAddress");
				String adtel = req.getParameter("adTel");
				String adid = req.getParameter("adId");
				
				AdminDAO dao = DAOManager.getAdminDAO();
				
				AdminDTO dto = new AdminDTO();
				
				dto.setAdNick(adnick);
				dto.setAdAddress(adaddress);
				dto.setAdTel(adtel);
				dto.setAdId(adid);
				
				int result = dao.save(dto);
				
				if (result == 1) {
					//resp.sendRedirect("/toy/board/list.do");
					resp.sendRedirect("/sangsangjakka/admin/mypage.do");
				}
				
	}
	
}//End of class
