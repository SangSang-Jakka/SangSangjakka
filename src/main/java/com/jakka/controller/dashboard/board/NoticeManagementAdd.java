package com.jakka.controller.dashboard.board;

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
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dao.board.NoticeDAOImpl;
import com.jakka.model.dto.board.NoticeDTO;

/**
 * NoticeManagementAdd 서블릿은 공지사항 등록 기능을 제공합니다.
 */
@WebServlet("/admin/dashboard/notice/manageadd.do")
public class NoticeManagementAdd extends HttpServlet {

	/**
     * GET 요청을 처리합니다.
     * 공지사항 등록 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/notice_manage_add.jsp");
		dispatcher.forward(req, resp);

	}

	/**
     * POST 요청을 처리합니다.
     * 새로운 공지사항을 등록합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 첨부파일 처리는 어떻게 할 지...

		// 1. 데이터 가져오기(subject, content)
		// 2. DB 작업 > insert
		// 3. 결과

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");

		// 폼으로부터 데이터 추출
		String noticeTitle = req.getParameter("noticeTitle");
		String noticeContents = req.getParameter("noticeContents");

		// 공지사항 객체 생성
		NoticeDTO dto = new NoticeDTO();

		dto.setNoticeTitle(noticeTitle);
		dto.setNoticeContents(noticeContents);
		dto.setAdId(adId);

		// db에 게시글 추가
		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();
		int result = noticeDAO.add(dto);

		if (result == 1) {
			resp.sendRedirect("/sangsangjakka/admin/dashboard/notice/manage.do");
		} else {
			
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('작성에 실패했습니다.');</script>");
			writer.println("<script>location.href='/sangsangjakka/admin/dashboard/notice/manage.do';</script>");
			writer.close();

		}

	}

}// End of class