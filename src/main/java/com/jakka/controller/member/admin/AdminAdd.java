package com.jakka.controller.member.admin;

import java.io.IOException;

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
 * AdminAdd 서블릿은 관리자 등록 기능을 제공합니다.
 */
@WebServlet("/admin/add.do")
public class AdminAdd extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 관리자 등록 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_add.jsp");
		dispatcher.forward(req, resp);
	}
	
	/**
     * POST 요청을 처리합니다.
     * 새로운 관리자를 등록합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		//1. 데이터 가져오기
		//2. DB 작업 > insert
		//3. 피드백
		
		//POST + 한글
		req.setCharacterEncoding("UTF-8");
		
		String adname = req.getParameter("adname");
		String adaddress = req.getParameter("adaddress");
		String adtel = req.getParameter("adtel");
		String adnick = req.getParameter("adnick");
		String adid = req.getParameter("adid");
		String adpw = req.getParameter("adpw");
		
		AdminDAO dao = DAOManager.getAdminDAO();
		
		AdminDTO dto = new AdminDTO();
		
		dto.setAdName(adname);
		dto.setAdAddress(adaddress);
		dto.setAdTel(adtel);
		dto.setAdNick(adnick);
		dto.setAdId(adid);
		dto.setAdPw(adpw);
		
		System.out.println(dto.getAdName());
		System.out.println(dto.getAdAddress());
		int result = dao.add(dto);
		
		
		if (result == 1) {
			resp.sendRedirect("/sangsangjakka/admin/list.do");
		}
		
		
	}
	
}//End of class
