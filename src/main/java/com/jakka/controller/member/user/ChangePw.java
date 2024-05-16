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

/**
 * ChangePw 서블릿은 사용자 비밀번호 변경 기능을 제공합니다.
 */
@WebServlet("/user/change_pw.do")
public class ChangePw extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 비밀번호 변경 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/change_pw.jsp");
		dispatcher.forward(req, resp);
	}
	
	/**
     * POST 요청을 처리합니다.
     * 사용자의 비밀번호를 변경합니다.
     * 변경 성공 시 로그인 페이지로 리다이렉트되고, 실패 시 알림 메시지를 출력합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
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
