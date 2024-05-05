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

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.SuggestionAnswerDTO;
import com.jakka.model.dto.board.SuggestionDTO;

@WebServlet("/admin/dashboard/suggestion/manageview.do")
public class SuggestionManagementView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		String seq = req.getParameter("seq");

		SuggestionDAO suggestionDAO = DAOManager.getSuggestionDAO();
		
		

		// 문의사항 가져오기
		SuggestionDTO dto = suggestionDAO.findById(seq);

		// 데이터 조작
		String sgstContents = dto.getSgstContents();

		// 글내용 > 태그 > 이스케이프
		sgstContents = sgstContents.replace(">", "&gt;").replace("<", "&lt;");

		// 글내용 > 개행 문자 처리
		sgstContents = sgstContents.replace("\r\n", "<br>");

		dto.setSgstContents(sgstContents);

		// 제목
		String sgstTitle = dto.getSgstTitle();

		// 제목 > 태그 > 이스케이프
		sgstTitle = sgstTitle.replace(">", "&gt;").replace("<", "&lt;");

		dto.setSgstTitle(sgstTitle);

		req.setAttribute("dto", dto);
		
		
		// 문의사항 답변 가져오기
		
		SuggestionAnswerDAO suggestionAnswerDAO = DAOManager.getSuggestionAnswerDAO();
		
		ArrayList<SuggestionAnswerDTO> answerList = suggestionAnswerDAO.list(seq);
		
		req.setAttribute("answerList", answerList);
				
	

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/suggestion_manage_view.jsp");
		dispatcher.forward(req, resp);

	}

}// End of class
