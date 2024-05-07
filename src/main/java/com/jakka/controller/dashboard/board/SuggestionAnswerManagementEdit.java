package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dto.board.SuggestionAnswerDTO;

@WebServlet("/admin/dashboard/suggestionanswer/manageedit.do")
public class SuggestionAnswerManagementEdit extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/suggestion_manage_view.jsp");
		dispatcher.forward(req, resp);
        
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// 세션에 로그인한 관리자
		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");
		
		
		try {
			
			 // POST 요청에서 데이터 가져오기
            String answSeq = req.getParameter("answSeq");
            String editedSgstAnsw = req.getParameter("sgstAnsw");

            // SuggestionAnswerDTO 객체 생성 및 수정할 내용 설정
            SuggestionAnswerDTO dto = new SuggestionAnswerDTO();
            dto.setAnswSeq(answSeq);
            dto.setSgstAnsw(editedSgstAnsw);

            // SuggestionAnswerDAO 객체 생성
            SuggestionAnswerDAO suggestionAnswerDAO = DAOManager.getSuggestionAnswerDAO();

            // 답변 업데이트 메서드 호출
            int update = suggestionAnswerDAO.save(dto);
            
         // 결과를 텍스트 형태로 반환
            String resultText = (update > 0) ? "1" : "0";
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(resultText);
            
			
		} catch (Exception e) {
			System.out.println("SuggestionAnswerManagementEdit.doPost");
			e.printStackTrace();
		}

		
		/*
		if (adId == null) {
			req.setAttribute("errorMessage", "로그인 후 수정할 수 있습니다.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_login.jsp"); 
	        dispatcher.forward(req, resp);
	        return; 

		} 
			
			// 폼에서 입력한 수정할 답변 정보 가져오기
			
			// String seq = req.getParameter("seq"); // 건의사항 번호
			String answSeq = req.getParameter("answSeq"); // 답변 번호. 
			String sgstAnsw = req.getParameter("sgstAnsw"); // 답변 수정 내용
			
			
			// 수정된 답변을 DB에 업데이트
			SuggestionAnswerDTO dto = new SuggestionAnswerDTO();
			dto.setAnswSeq(answSeq);
			dto.setSgstAnsw(sgstAnsw);
			dto.setAdId(adId);
			
			SuggestionAnswerDAO suggestionAnswerDAO = DAOManager.getSuggestionAnswerDAO();
	        int update = suggestionAnswerDAO.save(dto); // 답변 업데이트
	        
	        boolean success = update > 0;
	        
	    
	        // 문의사항 상세 화면으로 리다이렉트
	        // resp.sendRedirect(req.getContextPath() + "/admin/dashboard/suggestion/manageview.do?seq=" + seq);

	        // 응답 데이터 설정
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        JsonObject jsonResponse = new JsonObject();
	        jsonResponse.addProperty("success", success ? "1" : "0"); // 성공 여부를 JSON으로 전달
	        resp.getWriter().write(jsonResponse.toString());
	

	        */
		
	}

}
