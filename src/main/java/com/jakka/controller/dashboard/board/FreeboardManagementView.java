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
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;

@WebServlet("/admin/dashboard/freeboard/manageview.do")
public class FreeboardManagementView extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String seq = req.getParameter("seq");
		
		BoardDAO boardDAO = DAOManager.getBoardDAO();
		
		// 게시물 가져오기
		BoardDTO dto = boardDAO.findById(seq);
		
		String boardContents = dto.getBoardContents();
		
		// 글 내용 > 태그 > 이스케이프
		boardContents = boardContents.replace(">", "&gt;").replace("<", "&lt;");
		
		// 글 내용 > 개행 문자 처리
		boardContents = boardContents.replace("\r\n", "<br>");
		
		dto.setBoardContents(boardContents);
		
		// 제목
		String boardTitle = dto.getBoardTitle();
		
		//제목 > 태그 > 이스케이프
		boardTitle = boardTitle.replace(">", "&gt;").replace("<", "&lt;");
		
		dto.setBoardTitle(boardTitle);
		
		req.setAttribute("dto", dto);
		
		// 댓글 가져오기
		BoardCommentsDAO boardCommentsDAO = DAOManager.getBoardCommentDAO();
		
		ArrayList<BoardCommentDTO> cmntList = boardCommentsDAO.findChild(seq);

		req.setAttribute("cmntList", cmntList);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/freeboard_manage_view.jsp");
		dispatcher.forward(req, resp);
		
	}

}//End of class
