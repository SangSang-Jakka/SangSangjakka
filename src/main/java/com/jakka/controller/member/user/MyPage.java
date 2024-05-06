package com.jakka.controller.member.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/user/mypage.do")
public class MyPage extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	    HttpSession session = req.getSession();
	    String id = (String) session.getAttribute("userSeq");

	   
	    UserDAO dao = new UserDAO();
	    UserDTO dto = dao.getUser(userSeq);


	    req.setAttribute("dto", dto);

	
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/mypage.jsp");
	    dispatcher.forward(req, resp);
	
	}
	
}//End of class
