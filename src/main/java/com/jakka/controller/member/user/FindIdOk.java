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

/**
 * FindIdOk 서블릿은 사용자 아이디 찾기 결과를 제공합니다.
 */
@WebServlet("/user/find_id_ok.do")
public class FindIdOk extends HttpServlet{

	/**
     * POST 요청을 처리합니다.
     * 입력된 닉네임과 이메일로 사용자 아이디와 가입일자를 조회하여 JSP 페이지로 전달합니다.
     * 일치하는 아이디가 없으면 알림 메시지를 출력합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userNick = req.getParameter("userNick");
		String userEmail = req.getParameter("userEmail");
		
		// DB작업한 결과, UserDAO 타입
		UserDAO dao = DAOManager.getUserDAO();
		// 결과값을 들고 움직일 것들
		UserDTO dto = new UserDTO();
		
		dto.setUserNick(userNick);
		dto.setUserEmail(userEmail);
		
		// 들고 움직일 것들의 참조변수 result
		UserDTO result = dao.findId(dto);
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		

		if(result != null) {
			// find_id_ok.jsp에서 사용할 것들 쓰기
			req.setAttribute("userId", result.getUserId());
			req.setAttribute("userRegdate", result.getUserRegdate());
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/find_id_ok.jsp");
			dispatcher.forward(req, resp);
		} else {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('일치하는 아이디가 없습니다.');");
			writer.println("location.href='/sangsangjakka/user/find_id.do'");
			writer.println("</script>");
			writer.close();
		}
		
	}
	
}
