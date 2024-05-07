package com.jakka.controller.member.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.DBUtil;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

public class ChangeInfo extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		
		// 세션에서 사용자 아이디 가져오기
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        // 사용자가 입력한 정보 가져오기
        String nickName = req.getParameter("nickName");
        String phoneStart = req.getParameter("phoneStart");
        String phoneInput1 = req.getParameter("phoneInput1");
        String phoneInput2 = req.getParameter("phoneInput2");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        
        UserDTO dto=new UserDTO();
        
        dto.setUserId(userId);
        dto.setUserNick(nickName);
        dto.setUserTel(phoneStart + "-" + phoneInput1 + "-" + phoneInput2);
        dto.setUserAddress(address);
        dto.setUserEmail(email);
        
     // DAO를 통해 정보 업데이트
        UserDAO dao = DAOManager.getUserDAO();
        int result = dao.save(dto);

        if (result > 0) {
            // 업데이트 성공
            resp.sendRedirect("/user/mypage.do");
            
        } else {
            // 업데이트 실패
            resp.sendRedirect("/error.jsp");
        }
    }
}