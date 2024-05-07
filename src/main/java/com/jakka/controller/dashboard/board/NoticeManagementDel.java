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
import com.jakka.model.dao.board.NoticeDAO;

@WebServlet("/admin/dashboard/notice/managedel.do")
public class NoticeManagementDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/notice_manage_view.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		// Logger 객체 생성
	    final Logger logger = Logger.getLogger(NoticeManagementDel.class.getName());
	    
		// 세션에서 로그인한 아이디 가져오기
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("adId");
		
		
		
		// 데이터 가져오기
		String noticeSeq = req.getParameter("noticeSeq");
		String adId = req.getParameter("adId");

		if (loginId != null && loginId.equals(adId)) {
			
			// 게시물 삭제
			NoticeDAO noticeDAO = DAOManager.getNoticeDAO();
			int result = noticeDAO.remove(noticeSeq, adId);
			
			if (result > 0) {
				// 삭제 성공
				resp.getWriter().write("success");
				logger.info("게시물 삭제 성공 - noticeSeq: " + noticeSeq + ", adId: " + adId);
			} else {
				resp.getWriter().write("fail");
				logger.warning("게시물 삭제 실패 - noticeSeq: " + noticeSeq + ", adId: " + adId);
			}
		} else {
			// 권한 없음
			resp.getWriter().write("unauthorized");
			logger.warning("권한 없음 - loginId: " + loginId + ", adId: " + adId);
		}

	}

}
