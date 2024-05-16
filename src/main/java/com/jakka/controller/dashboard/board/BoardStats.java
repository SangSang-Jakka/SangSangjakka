package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.board.SuggestionDTO;

/**
 * BoardStats 서블릿은 게시판 통계 정보를 제공합니다.
 */
@WebServlet("/admin/dashboard/board/stats.do")
public class BoardStats extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 오늘 작성된 자유 게시글, 건의사항 게시글 목록과 전체 댓글 목록을 조회하여
     * JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
        String formattedDate = dateFormat.format(currentDate);

		BoardDAO boardDAO = DAOManager.getBoardDAO();
		
		// 자유게시판 목록
		ArrayList<BoardDTO> boardList = boardDAO.findToday(formattedDate);
		
		req.setAttribute("boardList", boardList);
		
		SuggestionDAO suggestionDAO = DAOManager.getSuggestionDAO();

		// 건의사항 목록
		ArrayList<SuggestionDTO> suggestionList = suggestionDAO.findToday(formattedDate);

		req.setAttribute("suggestionList", suggestionList);
		
		BoardCommentsDAO dao = DAOManager.getBoardCommentDAO();

		// 전체 댓글 목록

		ArrayList<BoardCommentDTO> cmntList = dao.findAll();

		req.setAttribute("cmntList", cmntList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/board_stats.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}//End of class
