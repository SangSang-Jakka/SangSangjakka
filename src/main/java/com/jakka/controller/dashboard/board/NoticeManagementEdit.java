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
import com.jakka.model.dto.board.NoticeDTO;

@WebServlet("/admin/dashboard/notice/manageedit.do")
public class NoticeManagementEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		// 인증 받지 못한 사용자 or 권한이 없는 사용자 > 거부

		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");
		
		if (adId == null) {
			
			resp.sendRedirect("/sangsangjakka/admin/login.do");
			return;
			
		}
	
		// 공지사항 번호 가져오기
		String seq = req.getParameter("seq");
		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();

		
		// 게시물 가져오기

		NoticeDTO dto = noticeDAO.findById(seq);

	// 작성자와 현재 사용자가 일치하지 않으면 권한 없음 처리
	
		if (!adId.equals(dto.getAdId())) {
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('권한이 없습니다.');</script>");
			writer.println("<script>location.href='/sangsangjakka/admin/dashboard/notice/manage.do';</script>");
			writer.close();
			return;
			
		}

		

		req.setAttribute("seq", seq);
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/notice_manage_edit.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");

		// 1. 데이터 가져오기
		// 2. DB 작업 > update
		// 3. 결과
		
		
		String noticeTitle = req.getParameter("noticeTitle");
		String noticeContents = req.getParameter("noticeContents");
		String seq = req.getParameter("seq");


	
		// 공지사항 작성자 == 현재 사용자 ?
		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");
		
		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();
		NoticeDTO dto = noticeDAO.findById(seq);
		
		if (!adId.equals(dto.getAdId())) { 
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('권한이 없습니다.');</script>");
			writer.println("<script>location.href='/sangsangjakka/admin/dashboard/notice/manage.do';</script>");
			writer.close();
			return;
		}
	
		
		// 공지사항 수정
		dto.setNoticeTitle(noticeTitle);
		dto.setNoticeContents(noticeContents);
		dto.setNoticeSeq(seq);

		int result = noticeDAO.save(dto);

		if (result == 1) {

			resp.sendRedirect("sangsangjakka/admin/dashboard/notice/manageview.do?seq=" + seq);

			
		} else {
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('수정 실패했습니다.');</script>");
			writer.println("<script>location.href='/sangsangjakka/admin/dashboard/notice/manage.do';</script>");
			writer.close();
		}
	}

}// end
