package com.jakka.controller.board.notice;

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
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.board.NoticeDTO;

	@WebServlet("/board/notice/View.do")
	public class NoticeView extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			HttpSession session = req.getSession();
			
			// 로그인 여부 확인
		    String userId = (String) session.getAttribute("userId");
		    if (userId == null) {
		        // 비로그인 상태일 경우 로그인 페이지로 리디렉션
		        resp.sendRedirect("/sangsangjakka/user/login.do");
		        return;
		    }
			
			String noticeSeq = req.getParameter("no");
			
			NoticeDAO dao = DAOManager.getNoticeDAO();
			
			if (session.getAttribute("read") != null
					&& session.getAttribute("read").toString().equals("n")) {
				//조회수 증가
				dao.addCnt(noticeSeq);
				session.setAttribute("read", "y");
			}
			
			NoticeDTO dto = dao.findById(noticeSeq);
			
			//게시글 조작
			dto.setNoticeTitle(dto.getNoticeTitle().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
			dto.setNoticeContents(dto.getNoticeContents().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
			
			req.setAttribute("dto", dto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/notice/notice_view.jsp");
			dispatcher.forward(req, resp);
			
		}
		
	}


