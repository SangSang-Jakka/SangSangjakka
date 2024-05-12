package com.jakka.controller.board.bookshare;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.ReviewDTO;

@WebServlet("/board/review/like.do")
public class ReviewLike extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String reviewSeq = req.getParameter("reviewSeq");
		
		HttpSession session = req.getSession();
		String userSeq = (String) session.getAttribute("userSeq");	
		
		ReviewDAO dao = DAOManager.getReviewDAO();
	    
	    ReviewDTO dto = new ReviewDTO();
	    
	    //dto.setReviewSeq(reviewSeq);
	    //dto.setUserSeq(userSeq);
	    
	    int resultLike = dao.addLikeCnt(reviewSeq,userSeq);
	   
	    System.out.println(resultLike);//1
	    
	    resp.setContentType("text/html; charset=UTF-8");
		
		
	    PrintWriter out = resp.getWriter();
	    if (resultLike == 1) {
	        out.print("success");
	    } else {
	        out.print("error");
	    }
	    out.flush();
	    out.close();
	}
}
