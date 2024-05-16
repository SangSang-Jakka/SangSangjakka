package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.board.SuggestionAnswerDTO;

/**
 * FreeboardManagementView 서블릿은 자유 게시판 관리 기능 중 게시글 상세 보기와 댓글 관리 기능을 제공합니다.
 */
@WebServlet("/admin/dashboard/freeboard/manageview.do")
public class FreeboardManagementView extends HttpServlet {
	
	/**
     * GET 요청을 처리합니다.
     * 선택한 자유 게시글의 상세 정보와 댓글 목록을 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		
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
		
		ArrayList<BoardCommentDTO> cmntList = boardCommentsDAO.findAllChild(seq);		

		// 댓글 내용 이스케이프 처리
		
		for (BoardCommentDTO cmnt : cmntList) {
			String cmntContents = cmnt.getCmntContents();
			cmntContents = cmntContents.replace(">", "&gt;").replace("<", "&lt;");
			
			// 개행 문자 처리
			cmntContents = cmntContents.replace("\r\n", "<br>");
			cmnt.setCmntContents(cmntContents);
		}
		
		req.setAttribute("cmntList", cmntList);
		
	
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/freeboard_manage_view.jsp");
		dispatcher.forward(req, resp);
		
		
	}
	
	/**
     * POST 요청을 처리합니다.
     * 댓글의 비활성화 또는 활성화를 처리합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");
		
		if (adId == null) {
			req.setAttribute("errorMessage", "로그인이 필요합니다.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_login.jsp");
			dispatcher.forward(req, resp);
		}
		
		String action = req.getParameter("action");
	
		// 댓글 비활성화
		if ("disableComment".equals(action)) {
			String cmntSeq = req.getParameter("cmntSeq");
			BoardCommentsDAO dao = DAOManager.getBoardCommentDAO();
			int result = dao.disable(cmntSeq, adId);
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain");
			PrintWriter writer = resp.getWriter();
			
			if (result > 0) {
				writer.print("댓글이 비활성화되었습니다.");
			} else {
				writer.print("댓글 비활성화에 실패했습니다.");
				
			}
		}
		
		// 댓글 활성화
		else if ("activation".equals(action)) {
			String cmntSeq = req.getParameter("cmntSeq");
			BoardCommentsDAO dao = DAOManager.getBoardCommentDAO();
			int result = dao.activation(cmntSeq, adId);
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain");
			PrintWriter writer = resp.getWriter();
			
			if (result > 0) {
				writer.print("댓글이 활성화되었습니다.");
			} else {
				writer.print("댓글 활성화에 실패했습니다.");
				
			}
			
		}
		
		
	}// doPost
		
		

}//End of class
