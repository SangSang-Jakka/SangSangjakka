package com.jakka.controller.board.bookshare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;
import com.jakka.model.dto.book.ReviewDTO;

@WebServlet("/board/book/view.do")
public class BookView extends HttpServlet{

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
		
	    String bookSeq = req.getParameter("no");
	    
	    BookDAO dao = DAOManager.getBookDAO();
	    
	    if (session.getAttribute("read") != null
				&& session.getAttribute("read").toString().equals("n")) {
			//조회수 증가
			dao.addCnt(bookSeq);
			session.setAttribute("read", "y");
		}
	    
	    BookDTO dto = dao.findById(bookSeq);
	    
	    //게시글 조작
	    dto.setBookTitle(dto.getBookTitle().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
	    dto.setBookInfo(dto.getBookInfo().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
	    
	    PageDAO pageDAO = DAOManager.getPageDAO();
	    HashMap<Integer, PageDTO> pageMap = pageDAO.findPages(bookSeq);
	    
	    
	    ReviewDAO revieDao = DAOManager.getReviewDAO();
	    ArrayList<ReviewDTO> reviewList = revieDao.findChild(bookSeq);
	    
	    req.setAttribute("dto", dto);
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("pageMap", pageMap);
	    
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookshare/bookshare_view.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}//End of class
