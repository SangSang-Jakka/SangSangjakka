package com.jakka.controller.member.user;

import java.io.IOException;

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

@WebServlet("/user/mypage.do")
public class MyPage extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	    HttpSession session = req.getSession();
	    String userId = (String) session.getAttribute("userId");

	    UserDAO dao = DAOManager.getUserDAO();
	    UserDTO dto = dao.findById(userId);
	    

	    req.setAttribute("dto", dto);

	
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/mypage.jsp");
	    dispatcher.forward(req, resp);
	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		    req.setCharacterEncoding("UTF-8");
		    
		    HttpSession session = req.getSession();
		    String userId = (String) session.getAttribute("userId");

		    // 사용자가 입력한 정보 가져오기
		    String nickName = req.getParameter("nickName");
		    String phoneStart = req.getParameter("phoneStart");
		    String phoneInput1 = req.getParameter("phoneInput1");
		    String phoneInput2 = req.getParameter("phoneInput2");
		    String address = req.getParameter("address");
		    String email = req.getParameter("email");
		    
		    UserDTO dto = new UserDTO();
		    
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
		        // 마이페이지로 이동
		        resp.sendRedirect(req.getContextPath() + "/user/mypage.do");
		    } else {
		        // 업데이트 실패
		        resp.sendRedirect(req.getContextPath() + "/error.jsp");
		    }
		}



	
}//End of class
