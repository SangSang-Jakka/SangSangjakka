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


@WebServlet("/admin/myedit.do")
public class AdminPageEdit extends HttpServlet{
	
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
