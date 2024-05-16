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
 * 자유게시판 댓글 수정 서블릿 클래스입니다.
 * 
 * @author Jakka
 */
@WebServlet("/board/freeboard/editcomment.do")
public class FreeboardEditComment extends HttpServlet {
	
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
        String cmntContents = req.getParameter("cmntContents");

        BoardCommentDTO dto = new BoardCommentDTO();
        dto.setCmntSeq(cmntSeq);
        dto.setCmntContents(cmntContents);

        BoardCommentsDAO dao = DAOManager.getBoardCommentDAO();
        int result = dao.save(dto);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (result > 0) {
            JSONObject json = new JSONObject();
            json.put("code", 0);
            json.put("message", "댓글이 수정되었습니다.");
            json.put("reviewContents", cmntContents); // 수정된 댓글 내용 반환
            resp.getWriter().write(json.toString());
        } else {
            JSONObject json = new JSONObject();
            json.put("code", 1);
            json.put("message", "댓글 수정 실패, 오류입니다.");
            resp.getWriter().write(json.toString());
        }
    }
}


