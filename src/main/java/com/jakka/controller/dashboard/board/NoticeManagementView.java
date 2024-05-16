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
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dto.board.NoticeDTO;

/**
 * NoticeManagementView 서블릿은 공지사항 관리 기능 중 공지사항 상세 보기와 고정/고정 해제 기능을 제공합니다.
 */
@WebServlet("/admin/dashboard/notice/manageview.do")
public class NoticeManagementView extends HttpServlet {

	/**
     * GET 요청을 처리합니다.
     * 선택한 공지사항의 상세 정보를 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		String seq = req.getParameter("seq");

		// 1. 데이터 가져오기(seq)
		// 2. DB 작업 > select .. where seq = 10
		// 3. 결과 > 출력

		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();

		// 게시물 가져오기
		NoticeDTO dto = noticeDAO.findById(seq);

		// 데이터 조작
		String noticeContents = dto.getNoticeContents();

		// 글내용 > 태그 > 이스케이프
		noticeContents = noticeContents.replace(">", "&gt;").replace("<", "&lt;");

		// 글내용 > 개행 문자 처리
		noticeContents = noticeContents.replace("\r\n", "<br>");

		dto.setNoticeContents(noticeContents);

		// 제목
		String noticeTitle = dto.getNoticeTitle();

		// 제목 > 태그 > 이스케이프
		noticeTitle = noticeTitle.replace(">", "&gt;").replace("<", "&lt;");

		dto.setNoticeTitle(noticeTitle);

		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/notice_manage_view.jsp");
		dispatcher.forward(req, resp);

	}

	/**
     * POST 요청을 처리합니다.
     * 공지사항의 고정 또는 고정 해제를 처리합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");

		if (adId == null) {
			req.setAttribute("errorMessage", "로그인이 필요합니다.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_login.jsp");
			dispatcher.forward(req, resp);
		}

		String action = req.getParameter("action");
		
		// 고정
		
		if ("activationFix".equals(action)) {
			String noticeSeq = req.getParameter("noticeSeq");
			NoticeDAO dao = DAOManager.getNoticeDAO();
			int result = dao.fixed(noticeSeq, adId);
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain");
			PrintWriter writer = resp.getWriter();
			
			if (result > 0) {
				writer.print("공지사항을 고정하였습니다.");
			} else {
				writer.print("공지사항이 이미 고정되어 있습니다.");
			}
			
		}
		
		
		// 고정 취소
		else if ("unFix".equals(action)) {
			String noticeSeq = req.getParameter("noticeSeq");
			NoticeDAO dao = DAOManager.getNoticeDAO();
			int result = dao.Unfixing(noticeSeq, adId);
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain");
			PrintWriter writer = resp.getWriter();
			
			if (result > 0) {
				writer.print("공지사항을 고정 해제하였습니다.");
			} else {
				writer.print("공지사항이 이미 고정 해제되어 있습니다.");
				
			}

			
		}
		

	}

}// End of class
