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

@WebServlet("/user/signok.do")
public class SignUpOk extends HttpServlet{

	@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 요청을 해당 JSP 페이지로 포워딩합니다.
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/signup_ok.jsp");
        dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String newUserId = (String)req.getAttribute("newUserId");
	    String childSsn = (String)req.getAttribute("childSsn");
	    String[] selectedInflowValues = req.getParameterValues("selectedValues");
        
        // JSP에서 사용할 수 있도록 새로운 사용자 ID를 요청 속성으로 설정합니다.
        req.setAttribute("newUserId", newUserId);
        
        // 전송된 유입 경로 값들을 이용하여 데이터베이스에 저장하는 로직을 추가합니다.
        saveInflowData(newUserId, selectedInflowValues);
        
	}

	private void saveInflowData(String newUserId, String[] selectedInflowValues) {
		 // 데이터베이스 연결 및 회원 정보 저장 로직을 구현합니다.
	    // ...

	    // 회원 정보 저장 후 해당 회원의 PK 값을 가져옵니다.
	    int userPK = getUserPK(userId);

	    // 유입 경로 값들을 이용하여 회원 유입 경로 테이블에 저장합니다.
	    for (String inflowValue : inflowValues) {
	        int inflowCategoryNumber = getInflowCategoryNumber(inflowValue);
	        insertInflowData(userPK, inflowCategoryNumber);
	    }
		
	}
	
	private int getUserPK(String userId) {
	    // 회원 ID를 이용하여 회원 PK 값을 조회하는 로직을 구현합니다.
		
		UserDTO dto = new UserDTO();
		
		dto.setUserId(userId);
		
		UserDAO dao = DAOManager.getUserDAO();
		
		int userPk = dao.findPK(dto); // 회원가입 메서드 호출하여 아이디 반환
		
		return 0;
	    
	}

	private int getInflowCategoryNumber(String inflowValue) {
	    // 유입 경로 값을 이용하여 유입 경로 카테고리 번호를 조회하는 로직을 구현합니다.
	    // ...
	}

	private void insertInflowData(int userPK, int inflowCategoryNumber) {
	    // 회원 PK 값과 유입 경로 카테고리 번호를 이용하여 회원 유입 경로 테이블에 데이터를 저장하는 로직을 구현합니다.
	    // ...
	}
	
}
