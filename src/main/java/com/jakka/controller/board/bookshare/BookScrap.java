package com.jakka.controller.board.bookshare;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dto.book.BookDTO;

/**
 * 동화책 스크랩 서블릿 클래스입니다.
 * 
 * @author Jakka
 */
@WebServlet("/board/book/scrap.do")
public class BookScrap extends HttpServlet{

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
		
		String bookSeq = req.getParameter("bookSeq");
		String userSeq = req.getParameter("userSeq");
		
		BookDAO dao = DAOManager.getBookDAO();
		
	    
	    BookDTO dto = new BookDTO();
	    
	    dto.setBookSeq(bookSeq);
	    dto.setUserSeq(userSeq);
	    
	    int resultScrap = dao.addScrap(bookSeq, userSeq);
	   
	    System.out.println(resultScrap);//1
	    
	    resp.setContentType("text/html; charset=UTF-8");
		
		
	    PrintWriter out = resp.getWriter();
	    if (resultScrap == 1) {
	        out.print("success");
	    } else {
	        out.print("error");
	    }
	    out.flush();
	    out.close();
	}
}
