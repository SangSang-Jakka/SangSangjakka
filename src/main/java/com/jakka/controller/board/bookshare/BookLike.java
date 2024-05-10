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
import com.jakka.model.dto.book.BookDTO;

@WebServlet("/board/book/like.do")
public class BookLike extends HttpServlet {
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String bookSeq = req.getParameter("bookSeq");
		String userSeq = req.getParameter("userSeq");
		
		BookDAO dao = DAOManager.getBookDAO();
		
	    
	    BookDTO dto = new BookDTO();
	    
	    dto.setBookSeq(bookSeq);
	    dto.setUserSeq(userSeq);
	    
	    int resultLike = dao.addLikeCnt(bookSeq, userSeq);
	   
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
