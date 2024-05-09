package com.jakka.controller.board.suggestion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.SuggestionDTO;

@WebServlet("/board/suggestion/del.do")
public class SuggestionDel extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		System.out.println("세션 : " + session);
		String seq = req.getParameter("sgstseq");
		System.out.println("GET seq값 : " + seq);
		req.setAttribute("seq", seq);
		req.setAttribute("userId", userId);
		System.out.println(userId);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/suggestion/suggestion_del.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// AJAX에서 del> post 메서드
		System.out.println("==================AJAX > del의 Post=======================");
		// AJAX에서 보낸 데이터 가져오기
		String sgstSeq = req.getParameter("sgstSeq");
		String userSeq = req.getParameter("userSeq");
		
		System.out.println("post userSeq값 " + userSeq);
		
		SuggestionDAO dao = DAOManager.getSuggestionDAO();
		SuggestionDTO dto = new SuggestionDTO();
		
		int result = dao.del(sgstSeq);
		System.out.println(result);
		
		resp.setContentType("text/html; charset=UTF-8");
		
		if(result > 0) {
			// JSON 객체 생성
			JSONObject json = new JSONObject();
			json.put("code", 0);
			json.put("message", "삭제가 완료되었습니다.");
			
			// JSON 데이터 반환
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json.toString());
		} else {
			JSONObject json = new JSONObject();
		    json.put("code", 1); // 실패 코드
		    json.put("message", "삭제 실패, 오류입니다.");

		    // JSON 데이터 반환
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json.toString());
		}
		
	}
}
