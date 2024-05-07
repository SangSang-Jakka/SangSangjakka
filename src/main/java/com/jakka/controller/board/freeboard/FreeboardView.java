package com.jakka.controller.board.freeboard;

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
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;

@WebServlet("/board/freeboard/view.do")
public class FreeboardView extends HttpServlet {
	
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
		
		String boardSeq = req.getParameter("no");
		
		
		BoardDAO dao = DAOManager.getBoardDAO();
		
		if (session.getAttribute("read") != null
				&& session.getAttribute("read").toString().equals("n")) {
			//조회수 증가
			dao.addCnt(boardSeq);
			session.setAttribute("read", "y");
		}
		
		BoardDTO dto = dao.findById(boardSeq);
		
		//게시글 조작
		dto.setBoardTitle(dto.getBoardTitle().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
		dto.setBoardContents(dto.getBoardContents().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
		
		BoardCommentsDAO cmntDao = DAOManager.getBoardCommentDAO();
		
		ArrayList<BoardCommentDTO> list = cmntDao.findChild(boardSeq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/freeboard/freeboard_view.jsp");
		dispatcher.forward(req, resp);
		
	}

}
