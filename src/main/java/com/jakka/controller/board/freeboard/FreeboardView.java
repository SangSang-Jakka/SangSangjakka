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

import org.json.simple.JSONObject;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;

/**
 * 자유게시판 글 상세보기 서블릿 클래스입니다.
 * 
 * @author Jakka
 */
@WebServlet("/board/freeboard/view.do")
public class FreeboardView extends HttpServlet {

	/**
     * GET 요청을 처리합니다.
     * 
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
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
		System.out.println("get의 setBoardTitle 확인중 : " + dto.getBoardTitle());
		dto.setBoardContents(dto.getBoardContents().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
		System.out.println("게시글 조작 dto : " + dto);

		BoardCommentsDAO cmntDao = DAOManager.getBoardCommentDAO();

		ArrayList<BoardCommentDTO> list = cmntDao.findChild(boardSeq);

		req.setAttribute("dto", dto);
		System.out.println("Get메서드의 dto " + dto);
		req.setAttribute("list", list);
		System.out.println("Get메서드의 list " + list);
		
		// Get메서드의 list [BoardCommentDTO(cmntSeq=5722, userSeq=79, boardSeq=324, cmntContents=테스트 10시31분, cmntReportCnt=0, cmntRegdate=2024-05-11 22:31:17, userNick=user0078NICK)

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/freeboard/freeboard_view.jsp");
		dispatcher.forward(req, resp);

	}

	/**
     * POST 요청을 처리합니다.
     * 
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 댓글 추가
		String boardSeq = req.getParameter("boardSeq");
		String userSeq = req.getParameter("userSeq");
		String cmntContents = req.getParameter("cmntContents");
		
		BoardCommentsDAO cmntDao = DAOManager.getBoardCommentDAO();
		
		BoardCommentDTO cmntDto = new BoardCommentDTO();
		cmntDto.setBoardSeq(boardSeq);
		cmntDto.setUserSeq(userSeq);
		cmntDto.setCmntContents(cmntContents);
		
		int result = cmntDao.add(cmntDto);
		
		resp.setContentType("text/html; charset=UTF-8");
		
		if (result > 0) {
		    // JSON 객체 생성
		    JSONObject json = new JSONObject();
		    json.put("code", 0); // 성공 코드
		    json.put("message", "작성 완료");

		    // JSON 데이터 반환
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json.toString());
		} else {
		    // JSON 객체 생성
		    JSONObject json = new JSONObject();
		    json.put("code", 1); // 실패 코드
		    json.put("message", "작성 실패, 댓글을 작성해주세요");

		    // JSON 데이터 반환
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json.toString());
		}
	}
	
	
}