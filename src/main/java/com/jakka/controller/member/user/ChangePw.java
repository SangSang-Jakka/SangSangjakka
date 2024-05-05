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

@WebServlet("/user/change_pw.do")
public class ChangePw extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/change_pw.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션값 받아오기
		HttpSession session = req.getSession();
		String userSeq = (String)session.getAttribute("userSeq");
		// 새 비밀번호와 확인용 비밀번호 받아오기
		String pwInput = req.getParameter("pwInput");
		
		UserDAO dao = DAOManager.getUserDAO();
		UserDTO dto = new UserDTO();
		
		dto.setUserSeq(userSeq);
		dto.setUserPw(pwInput);
		
		int result = dao.savePw(dto);
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		if(result == 1) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('비밀번호가 변경되었습니다.');");
			writer.println("location.href='/sangsangjakka/user/login.do';");
			writer.println("</script>");
			
		} else if(result == 0){
			writer.println("<script type='text/javascript'>");
			writer.println("alert('테스트입니다. 아직 더미가 없기 때문에 이 문구가 출력됩니다.');");
			writer.println("location.href='/sangsangjakka/index.do';");
			writer.println("</script>");
		} else if(result == -1) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('테스트입니다. 데이터베이스 오류입니다.');");
			writer.println("location.href='/sangsangjakka/index.do';");
			writer.println("</script>");
		}
		writer.close();
	}
	
}
