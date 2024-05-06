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

		req.setCharacterEncoding("UTF-8");
		
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

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/suggestion_manage_view.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		// 세션에 로그인한 관리자
		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");

		if (adId == null) {
			req.setAttribute("errorMessage", "로그인 후 답변을 작성할 수 있습니다.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_login.jsp"); 
			dispatcher.forward(req, resp);
		} else {

			// 폼에서 입력한 답변 가져오기
			String sgstAnsw = req.getParameter("sgstAnsw");
			// 건의사항 번호 가져오기
			String seq = req.getParameter("seq");

			// 답변을 DB에 저장
			SuggestionAnswerDTO dto = new SuggestionAnswerDTO();
			dto.setSgstSeq(seq); // 건의사항 번호
			dto.setSgstAnsw(sgstAnsw); // 답변 내용
			dto.setAdId(adId); // 관리자 아이디

			SuggestionAnswerDAO suggestionAnswerDAO = DAOManager.getSuggestionAnswerDAO();
			suggestionAnswerDAO.add(dto); // 답변 저장

			// 저장된 답변을 포함하여 다시 화면에 표시
			ArrayList<SuggestionAnswerDTO> answerList = suggestionAnswerDAO.list(seq);
			req.setAttribute("answerList", answerList);

			// 문의사항 상세 화면으로 리다이렉트 또는 포워딩
			resp.sendRedirect(req.getContextPath() + "/admin/dashboard/suggestion/manageview.do?seq=" + seq);

		}

	}

}// End of class
