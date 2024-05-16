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
 * IdCheckForm 서블릿은 사용자 아이디 중복 확인 기능을 제공합니다.
 */
@WebServlet("/user/idcheck.do")
public class IdCheckForm extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 아이디 중복 확인 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/idcheckform.jsp");
	       dispatcher.forward(req, resp);
	}
	
	/**
     * POST 요청을 처리합니다.
     * 입력된 아이디의 중복 여부를 확인하여 응답합니다.
     * 중복되는 아이디가 있으면 "true", 없으면 "false"를 반환합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 클라이언트로부터 아이디 받기
        String userId = req.getParameter("id");
        
        System.out.println(userId);
        
        // 중복 여부 확인
        UserDAO dao = DAOManager.getUserDAO();
        UserDTO dto = new UserDTO();
        dto.setUserId(userId);
        int count = dao.checkId(dto);

        // 결과 전송
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        if (count > 0) {
            out.print("true"); // 중복되는 아이디가 존재함
        } else {
            out.print("false"); // 중복되는 아이디가 존재하지 않음
        }
        out.flush();
    }
}
