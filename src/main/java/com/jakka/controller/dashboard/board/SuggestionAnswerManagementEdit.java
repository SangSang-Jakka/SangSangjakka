package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dto.board.SuggestionAnswerDTO;

@WebServlet("/admin/dashboard/suggestionanswer/manageedit.do")
public class SuggestionAnswerManagementEdit extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		
		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");
		String answSeq = req.getParameter("answSeq");
		String sgstAnsw = req.getParameter("sgstAnsw");
	//	String seq = req.getParameter("seq"); // 게시글 번호
		
		SuggestionAnswerDAO dao = DAOManager.getSuggestionAnswerDAO();
		
		// 답변 가져오기
		SuggestionAnswerDTO dto = dao.findById(answSeq);
		
		
		if (adId.equals(dto.getAdId())) {
			// 답변 수정
			dto.setAnswSeq(answSeq);
			dto.setSgstAnsw(sgstAnsw);
			
			// 저장
			int result = dao.save(dto);
			
			// 클라이언트에게 응답
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
			
			if (result == 1) {
				
				out.println("{\"result\": \"1\"}");
			//	resp.sendRedirect("/sangsangjakka/admin/dashboard/suggestion/manageview.do?seq=" + seq);
				
			} else {
				/*
				resp.setCharacterEncoding("UTF-8");
				PrintWriter writer = resp.getWriter();
				writer.println("<html><head><meta charset=\"UTF-8\"></head><body>");
				writer.println("<script>alert('수정 실패했습니다.');</script>");
				writer.println("<script>location.href='/sangsangjakka/admin/dashboard/notice/manage.do';</script>");
				writer.println("</body></html>");
				writer.close();
				*/
				  out.println("{\"result\": \"0\"}");
			} 
			out.close();
				
			} else {
				// 권한이 없는 경우 처리
	            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}

		}

	}// end


