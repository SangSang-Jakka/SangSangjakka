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

@WebServlet("/user/find_pw.do")
public class FindPw extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/find_pw.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//		// 아이디와 주민번호 입력 후 넘어옴
		String userId = req.getParameter("userId");
		String userLeftSsn = req.getParameter("userLeftSsn");

		UserDAO dao = DAOManager.getUserDAO();
		UserDTO dto = new UserDTO();
		// 입력받은 값을 dto 객체에 설정 후 findPw 메서드에 전달
		// 메서드 내에서는 쿼리 실행 후 반환값으로 result 반환 후
		// 조건 실행 후 다른 서블릿으로 이동
		dto.setUserId(userId);
		dto.setUserLeftSsn(userLeftSsn);

		UserDTO seq = dao.findPw(dto);
		PrintWriter writer = resp.getWriter();

		if(seq != null) {
			// 반환된 userSeq를 저장하여 조건에 맞는 사용자의 정보를 세션에 저장하고 들고감
			HttpSession session = req.getSession();
			session.setAttribute("userSeq", seq.getUserSeq());
			writer.println("<script type='text/javascript'>");
			writer.println("location.href='/sangsangjakka/user/change_pw.do';");
			writer.println("</script>");


		} else {
			resp.setContentType("text/html; charset=UTF-8");

			writer.println("<script type='text/javascript'>");
			writer.print("alert('ID 혹은 PASSWORD가 일치하지 않습니다.');");
			writer.print("location.href='/sangsangjakka/user/change_pw.do';");
			writer.println("</script>");

		}
		writer.close();



		//		writer.println("</script>");
		//		writer.println("<script>");
		//		writer.println("location.href='/sangsangjakka/user/change_pw.do';");
		//		writer.println("</script>");
		//		writer.close();
	}

}
