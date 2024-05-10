package com.jakka.controller.board.freeboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dto.board.BoardDTO;


@WebServlet("/board/freeboard/del.do")
public class FreeboardDel extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String userSeq = (String)session.getAttribute("userSeq");
		String delUserSeq = req.getParameter("userSeq");
		
		System.out.println(userSeq);
		System.out.println(delUserSeq);

		if (!userSeq.equals(delUserSeq)) {
		    // 본인이 작성한 글이 아닌 경우
		    req.setAttribute("message", "본인이 작성한 글이 아닙니다.");
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/freeboard/freeboard_list.jsp");
		    dispatcher.forward(req, resp);
		    return;
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    System.out.println("aaaaaaaaaa");
		
		// 요청 파라미터 가져오기
	    String boardSeq = req.getParameter("boardSeq");
	    String userSeq = req.getParameter("userSeq");
	    
	    System.out.println(boardSeq);
	    System.out.println(userSeq);

	    // 게시물 삭제 로직 구현
	    BoardDAO dao = DAOManager.getBoardDAO();
	    BoardDTO dto = new BoardDTO();
	    
	    int result = dao.freeDel(boardSeq);
	    
	    System.out.println(result);
	    
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
