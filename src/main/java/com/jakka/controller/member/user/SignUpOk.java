package com.jakka.controller.member.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/signok.do")
public class SignUpOk extends HttpServlet{

	@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 속성에서 새로운 사용자 ID를 가져옵니다.
        String newUserId = (String) req.getAttribute("newUserId");
        
        // JSP에서 사용할 수 있도록 새로운 사용자 ID를 요청 속성으로 설정합니다.
        req.setAttribute("newUserId", newUserId);
        
        // 요청을 해당 JSP 페이지로 포워딩합니다.
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/signup_ok.jsp");
        dispatcher.forward(req, resp);

	}
	
}
