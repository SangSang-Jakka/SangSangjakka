package com.jakka.controller.member.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.DBUtil;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;
import com.jakka.model.enums.Inflow;


@WebServlet("/user/signup.do")
public class SignUp extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/signup.jsp");
	     dispatcher.forward(req, resp);
	     
	     System.out.println("get");
	}

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//USERID, USERPW, USERNICK, USERTEL, USERADDRESS, USEREMAIL, USERLEFTSSN, USERRIGHTSSN, USERSTATE, USERLV, USERREGDATE, LIMITSTORAGE,USERNAME
		 req.setCharacterEncoding("UTF-8");
		 
		 System.out.println("post");
		
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
		
		int newUserId = dao.signUp(dto); 
		
		System.out.println(newUserId);

		if (newUserId > 0  ) { // 회원가입 성공한 경우
			req.setAttribute("newUserId", userId);
		
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
		
		
		String childSsn = req.getParameter("childSsn");
		String[] selectedInflowValues = req.getParameterValues("inflow");
		
		for (String value : selectedInflowValues) {
	        System.out.println("Selected Inflow Value: " + value);
	    }
		
		// 값이 있는 경우에만 데이터베이스에 저장
		if (childSsn != null && !childSsn.isEmpty()) {
			
			System.out.println(dto.getUserId());
			
			String userPk = dao.findSeq(dto.getUserId());
			
			System.out.println(userPk);
		    saveChildAgeData(childSsn, userPk); // userId는 회원의 고유 식별자
		}
		
		if (selectedInflowValues != null && selectedInflowValues.length > 0) {
			String userPk = dao.findSeq(dto.getUserId());
		    saveInflowData(userPk, selectedInflowValues); // userId는 회원의 고유 식별자
		}
			
		
       
    }

	private void saveInflowData(String userPk, String[] selectedInflowValues) {
		String SQL = "INSERT INTO tblUserInflow (userSeq, inflowCatSeq) VALUES (?, ?)";

        try (Connection conn = DBUtil.open();
             PreparedStatement pstat = conn.prepareStatement(SQL);) {

            for (String inflowValue : selectedInflowValues) {
                int inflowSeq = getInflowSeq(inflowValue);

                pstat.setString(1, userPk);
                pstat.setInt(2, inflowSeq);
                pstat.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	private int getInflowSeq(String inflowValue) {
		switch (inflowValue) {
        case "인터넷 검색":
            return Integer.parseInt(Inflow.Internet_Search.getValue());
        case "지인 소개":
            return Integer.parseInt(Inflow.ACQUAINTANCE.getValue());
        case "카페":
            return Integer.parseInt(Inflow.CAFFE.getValue());
        case "블로그":
            return Integer.parseInt(Inflow.BLOG.getValue());
        case "소셜 미디어 플랫폼":
            return Integer.parseInt(Inflow.SOCIAL_MEDIA_PLATFORM.getValue());
        case "광고지":
            return Integer.parseInt(Inflow.FLYER.getValue());
        default:
            return Integer.parseInt(Inflow.ECT.getValue());
    }
}
	

	private void saveChildAgeData(String childSsn, String userPk) {
		 String SQL = "INSERT INTO tblChildAge (childAgeSeq, childBirth, userSeq, ageCatSeq) VALUES ((SELECT NVL(MAX(userSeq), 0) + 1 FROM tblChildAge),?, ?, ?)";

	        try (Connection conn = DBUtil.open();
	             PreparedStatement pstat = conn.prepareStatement(SQL);) {
	        	
	        	String childCatSeq = getChildAge(childSsn);
	        	
	            pstat.setString(1, childSsn);
	            pstat.setString(2, userPk);
	            pstat.setString(3, childCatSeq);
	            pstat.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}

	private String getChildAge(String childSsn) {
		
	    String ageCatSeq = null;

	    // 아동의 주민등록번호를 기반으로 생년월일을 추출하는 로직
	    String birthYear = childSsn.substring(0, 4);
	    int birthYearInt = Integer.parseInt(birthYear);

	    // 현재 년도를 가져옴
	    Calendar now = Calendar.getInstance();
	    int currentYear = now.get(Calendar.YEAR);

	    // 추천 연령 카테고리 계산
	    int age = currentYear - birthYearInt;
	    if (age >= 10) {
	        ageCatSeq = "5"; // 10세 이상
	    } else if (age >= 7) {
	        ageCatSeq = "4"; // 7세 이상
	    } else if (age >= 5) {
	        ageCatSeq = "3"; // 5세 이상
	    } else if (age >= 3) {
	        ageCatSeq = "2"; // 3세 이상
	    } else {
	        ageCatSeq = "1"; // 1세 이상
	    }

	    return ageCatSeq;
	}
}//End of class
