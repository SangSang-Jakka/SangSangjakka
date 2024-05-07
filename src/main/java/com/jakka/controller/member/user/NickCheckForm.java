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

@WebServlet("/user/nickcheck.do")
public class NickCheckForm extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/nickcheckform.jsp");
	       dispatcher.forward(req, resp);
	}
	
	@Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 클라이언트로부터 아이디 받기
        String userNick = req.getParameter("nick");
        
        System.out.println(userNick);
        
        // 중복 여부 확인
        UserDAO dao = DAOManager.getUserDAO();
        UserDTO dto = new UserDTO();
        dto.setUserId(userNick);
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
