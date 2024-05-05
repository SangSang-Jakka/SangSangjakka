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


@WebServlet("/admin/add.do")
public class AdminAdd extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_add.jsp");
		dispatcher.forward(req, resp);
	}
	
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
