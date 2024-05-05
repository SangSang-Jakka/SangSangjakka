package com.jakka.controller.member.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/user/find_id_ok.do")
public class FindIdOk extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userNick = req.getParameter("userNick");
		String userRegdate = req.getParameter("userRegdate");
		
		// DB작업한 결과, UserDAO 타입
		UserDAO dao = DAOManager.getUserDAO();
		// 결과값을 들고 움직일 것들
		UserDTO dto = new UserDTO();
		
		// 들고 움직일 것들의 참조변수 result
		UserDTO result = dao.findId(dto);
		
		if(result != null) {
			// find_id_ok.jsp에서 사용할 것들 쓰기
			req.setAttribute("userId", result.getUserId());
			req.setAttribute("userRegdate", result.getUserRegdate());
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/find_id_ok.jsp");
			dispatcher.forward(req, resp);
		} else {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('일치하는 아이디가 없습니다.');");
			writer.println("location.href='/sangsangjakka/user/find_id.do'");
			writer.println("</script>");
			writer.close();
		}
		
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/find_id_ok.jsp");
//		dispatcher.forward(req, resp);
	}
	
}
