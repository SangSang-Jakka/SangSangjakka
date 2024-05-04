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

		HttpSession session = req.getSession();
		// 관리자 권한 작업 해야 됨!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		// 1. 데이터 가져오기(seq)
		// 2. DB 작업 > select > 수정할 데이터의 원본을 미리 보여주기 위해서
		// 3. 결과 > 출력
		
		String seq = req.getParameter("seq");

		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();

		// 게시물 가져오기
		NoticeDTO dto = noticeDAO.findById(seq);

		req.setAttribute("seq", seq);
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/notice_manage_edit.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 가져오기
		// 2. DB 작업 > update
		// 3. 결과
		
		String noticeTitle = req.getParameter("noticeTitle");
		String noticeContents = req.getParameter("noticeContents");
		String seq = req.getParameter("seq");
		
		
		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();
		NoticeDTO dto = noticeDAO.findById(seq);
		
		dto.setNoticeTitle(noticeTitle);
		dto.setNoticeContents(noticeContents);
		dto.setNoticeSeq(seq);

		int result = noticeDAO.save(dto);
		
		if (result ==1) {

			resp.sendRedirect("sangsangjakka/admin/dashboard/notice/manageview.do?seq=" + seq);
		} else {
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			// writer.print(OutputUtil.redirect("실패했습니다."));
			writer.close();
		}
	}

}// end
