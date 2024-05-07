package com.jakka.controller.member.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/user/login.do")
public class UserLogin extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/user_login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String userId = req.getParameter("userId");
		String userPw = req.getParameter("userPw");
	
		UserDAO dao = DAOManager.getUserDAO();
		
		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		dto.setUserPw(userPw);
		
		dto.setUserId(userId);
		dto.setUserPw(userPw);
		
		UserDTO result = dao.login(dto);

		if(result.getUserId() != null) {
			HttpSession session = req.getSession();
			
			session.setAttribute("userId", result.getUserId());
			session.setAttribute("userSeq", result.getUserSeq());
			session.setAttribute("userNick", result.getUserNick());
			session.setAttribute("userLV", result.getUserLV());
			resp.sendRedirect("/sangsangjakka/index.do");
		}  else {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.print("alert('ID 혹은 PASSWORD가 일치하지 않습니다.');");
			writer.print("location.href='/sangsangjakka/user/login.do';");
			writer.println("</script>");
			writer.close();
		}		
	}
	
}//End of class
