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

@WebServlet("/user/signout.do")
public class SignOut extends HttpServlet {
	// 회원탈퇴.jsp 에서 넘어옴
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/signout.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 상태에서 회원탈퇴를 하면 현재 세션에 접속된 user의 Seq번호를 가져옴
		// 그리고 입력한 inputPw를 가져와 변수에 담음
		HttpSession session = req.getSession();
		String userSeq = (String)session.getAttribute("userSeq");
		String inputPw = req.getParameter("inputPw");

		UserDAO dao = DAOManager.getUserDAO();
		UserDTO dto = new UserDTO();
		// 로그인 시 세션정보 가져오기(seq)

		// 입력한 값을 dto에 pw값으로 써서 메서드에서 사용, 
		dto.setUserPw(inputPw);
		dto.setUserSeq(userSeq);
		// dao.unRegister에 dto를 넘겨줌
		boolean success = dao.unRegister(dto);
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<script type='text/javascript'>");
		
		if(success) {
			writer.println("alert('회원 탈퇴가 완료되었습니다.');");
			writer.println("location.href='/sangsangjakka/index.do';");
		} else {
			writer.println("alert('비밀번호가 틀렸습니다. 다시 입력해주세요.');");
			writer.println("location.href='/sangsangjakka/user/signout.do';");
		}

		writer.println("</script>");

	}
}
