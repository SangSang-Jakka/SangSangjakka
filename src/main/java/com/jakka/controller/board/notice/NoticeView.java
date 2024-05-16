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

/**
 * NoticeView 서블릿은 공지사항 게시판의 상세 보기 기능을 수행합니다.
 * 로그인 상태를 확인하고, 선택한 공지사항의 상세 정보와 이전/다음 공지사항 정보를 제공합니다.
 */
	@WebServlet("/board/notice/view.do")
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

	        // 요청 파라미터에서 공지사항 번호 가져오기
	        String noticeSeq = req.getParameter("no");

	        NoticeDAO dao = DAOManager.getNoticeDAO();

	        // 조회수 증가
	        if (session.getAttribute("read") != null && session.getAttribute("read").toString().equals("n")) {
	            dao.addCnt(noticeSeq);
	            session.setAttribute("read", "y");
	        }

	        // 다음/이전 공지사항 정보 가져오기
	        NoticeDTO next = dao.findById(Integer.parseInt(noticeSeq) + 1 + "");
	        NoticeDTO prev = dao.findById(Integer.parseInt(noticeSeq) - 1 + "");

	        // 선택한 공지사항 정보 가져오기
	        NoticeDTO dto = dao.findById(noticeSeq);

	        // 데이터 가공
	        processNoticeData(next);
	        processNoticeData(prev);
	        processNoticeData(dto);

	        // 요청 속성에 데이터 저장
	        req.setAttribute("next", next);
	        req.setAttribute("prev", prev);
	        req.setAttribute("dto", dto);

	        // JSP 페이지로 포워딩
	        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/notice/notice_view.jsp");
	        dispatcher.forward(req, resp);
	    }

	    /**
	     * 공지사항 데이터를 가공하는 메서드입니다.
	     *
	     * @param dto 공지사항 DTO 객체
	     */
	    private void processNoticeData(NoticeDTO dto) {
	        if (dto != null) {
	            dto.setNoticeRegdate(dto.getNoticeRegdate().substring(0, 10)); // 날짜 포맷 변경
	            String title = dto.getNoticeTitle();
	            title = title.replace(">", "&gt;").replace("<", "&lt;").replace("\\r\\n", "<br>"); // 태그 이스케이프 및 줄바꿈 처리
	            dto.setNoticeTitle(title);
	            String contents = dto.getNoticeContents();
	            contents = contents.replace(">", "&gt;").replace("<", "&lt;").replace("\\r\\n", "<br>"); // 태그 이스케이프 및 줄바꿈 처리
	            dto.setNoticeContents(contents);
	        }
	    }
		
	}//End of class


