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


@WebServlet("/user/signup.do")
public class SignUp extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/signup.jsp");
	     dispatcher.forward(req, resp);
	}

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//USERID, USERPW, USERNICK, USERTEL, USERADDRESS, USEREMAIL, USERLEFTSSN, USERRIGHTSSN, USERSTATE, USERLV, USERREGDATE, LIMITSTORAGE,USERNAME
		 req.setCharacterEncoding("UTF-8");
		
		String userId = req.getParameter("userId");
		String userPw = req.getParameter("userPw");
		String userNick = req.getParameter("userNick");
		
		String prefix = req.getParameter("phonePrefix");
		String input1 = req.getParameter("phoneInput1");
		String input2 = req.getParameter("phoneInput2");
		String usertel = prefix + "-" + input1 + "-" + input2;
		
		String userAddress = req.getParameter("userAddress");
		
		String userEmailId = req.getParameter("userEmail");
		String domain = req.getParameter("domain");
		
		String userEmail;
		if (domain.equals("직접 입력")) {
	        String userDomain = req.getParameter("userDomain");
	        userEmail = userEmailId + "@" + userDomain;
	    } else {
	        userEmail = userEmailId + "@" + domain;
	    }
		
		
		
		String userLeftSsn = req.getParameter("userLeftSSN");
		String userRightSsn = req.getParameter("userRightSSN");
		String userName = req.getParameter("userName");
		
		
		UserDTO dto = new UserDTO();
		
		//USERID, USERPW, USERNICK, USERTEL, USERADDRESS, USEREMAIL, USERLEFTSSN, USERRIGHTSSN, USERSTATE, USERLV, USERREGDATE, LIMITSTORAGE,USERNAME
		dto.setUserId(userId);
		dto.setUserPw(userPw);
		dto.setUserNick(userNick);
		dto.setUserTel(usertel);
		dto.setUserAddress(userAddress);
		dto.setUserEmail(userEmail);
		dto.setUserLeftSsn(userLeftSsn);
		dto.setUserRightSsn(userRightSsn);
		dto.setUserName(userName);
		
		
		
		UserDAO dao = DAOManager.getUserDAO();
		
		String newUserId = dao.signUp(dto); // 회원가입 메서드 호출하여 아이디 반환

		if (newUserId != null) { // 회원가입 성공한 경우
			req.setAttribute("newUserId", newUserId);

	        // JSP 페이지로 포워딩
	        req.getRequestDispatcher("/WEB-INF/views/member/user/signup_ok.jsp").forward(req, resp);
		} else { // 회원가입 실패한 경우
		    resp.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.println("<script type='text/javascript'>");
		    writer.print("alert('회원가입 실패');");
		    writer.println("</script>");
		    writer.close();
		}
			
		
       
    }
}//End of class
