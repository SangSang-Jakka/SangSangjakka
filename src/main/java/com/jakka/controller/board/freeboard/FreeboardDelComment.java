package com.jakka.controller.board.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dto.board.BoardCommentDTO;

/**
 * 자유게시판 댓글 삭제 서블릿 클래스입니다.
 *
 * @author Jakka
 */
@WebServlet("/board/freeboard/delcomment.do")
public class FreeboardDelComment extends HttpServlet {
	
	/**
     * POST 요청을 처리합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");

	    String cmntSeq = req.getParameter("cmntSeq");
	    String boardSeq = req.getParameter("boardSeq");
	    String userSeq = req.getParameter("delUserSeq");
	    
	    BoardCommentDTO dto = new BoardCommentDTO();
	    dto.setCmntSeq(cmntSeq);
	    dto.setBoardSeq(boardSeq);
	    dto.setUserSeq(userSeq);

	    BoardCommentsDAO dao = DAOManager.getBoardCommentDAO();
	    int result = dao.del(dto);
	    
	    resp.setContentType("text/html; charset=UTF-8");
	    
	    if (result > 0) {
		    // JSON 객체 생성
		    JSONObject json = new JSONObject();
		    json.put("code", 0); // 성공 코드
		    json.put("message", "삭제가 완료되었습니다.");

		    // JSON 데이터 반환
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json.toString());
		} else {
		    // JSON 객체 생성
		    JSONObject json = new JSONObject();
		    json.put("code", 1); // 실패 코드
		    json.put("message", "삭제 실패, 오류입니다.");

		    // JSON 데이터 반환
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json.toString());
		}
	}
}


