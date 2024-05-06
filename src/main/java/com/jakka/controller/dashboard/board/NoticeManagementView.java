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
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dto.board.NoticeDTO;

@WebServlet("/admin/dashboard/notice/manageview.do")
public class NoticeManagementView extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		String seq = req.getParameter("seq");
		
		//1. 데이터 가져오기(seq)
		//2. DB 작업 > select .. where seq = 10
		//3. 결과 > 출력  
		
		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();
		
		// 게시물 가져오기
		NoticeDTO dto = noticeDAO.findById(seq);
		    
		// 데이터 조작
		String noticeContents = dto.getNoticeContents();
		
		//글내용 > 태그 > 이스케이프
		noticeContents = noticeContents.replace(">", "&gt;").replace("<", "&lt;");
		
		//글내용 > 개행 문자 처리
		noticeContents = noticeContents.replace("\r\n", "<br>");
		
		dto.setNoticeContents(noticeContents);
		
		// 제목
		String noticeTitle = dto.getNoticeTitle();
		
		//제목 > 태그 > 이스케이프
		noticeTitle = noticeTitle.replace(">", "&gt;").replace("<", "&lt;");
		
		dto.setNoticeTitle(noticeTitle);
		
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/notice_manage_view.jsp");
		dispatcher.forward(req, resp);
	
		
	}

	
}//End of class
