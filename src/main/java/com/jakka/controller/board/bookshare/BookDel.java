package com.jakka.controller.board.bookshare;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.book.ReviewDTO;


@WebServlet("/board/book/del.do")
public class BookDel extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    System.out.println("aaaaaaaaaa");
		
		// 요청 파라미터 가져오기
	    String reviewSeq = req.getParameter("reviewSeq");
	    
	    System.out.println(reviewSeq);

	    // 게시물 삭제 로직 구현
	    ReviewDAO dao = DAOManager.getReviewDAO();
	    ReviewDTO dto = new ReviewDTO();
	    
	    int result = dao.reviewDel(reviewSeq);
	    
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
