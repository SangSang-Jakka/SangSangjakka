package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.util.logging.Logger;

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

/**
 * SuggestionManagement 서블릿은 건의사항 관리 기능을 제공합니다.
 */
@WebServlet("/admin/dashboard/suggestionanswer/managedel.do")
public class SuggestionAnswerManagementDel extends HttpServlet {
	
	/**
     * GET 요청을 처리합니다.
     * 전체 건의사항 목록을 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/suggestion_manage_view.jsp");
		dispatcher.forward(req, resp);

	}

	/**
     * POST 요청을 처리합니다.
     * 선택한 조건에 따라 건의사항 목록을 필터링하여 JSON 형식으로 응답합니다.
     * 조건: 비밀 건의사항, 공개 건의사항, 답변된 건의사항, 답변되지 않은 건의사항, 전체
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		final Logger logger = Logger.getLogger(SuggestionAnswerManagementDel.class.getName());

		// 세션에서 로그인한 아이디 가져오기
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("adId");

		// 데이터 가져오기
		String answSeq = req.getParameter("answSeq");
		String adId = req.getParameter("adId");
		String sgstAnsw = req.getParameter("sgstAnsw");

		if (loginId != null && loginId.equals(adId)) {

			// 게시물 삭제
			SuggestionAnswerDAO dao = DAOManager.getSuggestionAnswerDAO();
			SuggestionAnswerDTO dto = new SuggestionAnswerDTO();

			dto.setAnswSeq(answSeq);
			dto.setAdId(adId);
		

			int result = dao.del(answSeq, adId);

			if (result > 0) {
				// 삭제 성공
				resp.getWriter().write("success");
				logger.info("답변 삭제 성공 - answSeq: " + answSeq + ", adId: " + adId);
			} else {
				resp.getWriter().write("fail");
				logger.warning("답변 삭제 실패 - answSeq: " + answSeq + ", adId: " + adId);
			}
		} else {
			// 권한 없음
			resp.getWriter().write("unauthorized");
			logger.warning("권한 없음 - loginId: " + loginId + ", adId: " + adId);
		}

	}

}//End of class
