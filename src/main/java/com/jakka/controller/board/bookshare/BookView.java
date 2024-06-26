package com.jakka.controller.board.bookshare;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;
import com.jakka.model.dto.book.ReviewDTO;

/**
 * 동화책 상세 보기 서블릿 클래스입니다.
 * 
 * @author Jakka
 */
@WebServlet("/board/book/view.do")
public class BookView extends HttpServlet{

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
	    String userSeq = (String) session.getAttribute("userSeq");
	    if (userId == null) {
	        // 비로그인 상태일 경우 로그인 페이지로 리디렉션
	        resp.sendRedirect("/sangsangjakka/user/login.do");
	        return;
	    }
		
	    
	    req.setAttribute("userSeq", userSeq);
	    
	    String bookSeq = req.getParameter("no");
	    req.setAttribute("bookSeq", bookSeq);
	    
	    System.out.println(bookSeq);//14
	    
	    BookDAO dao = DAOManager.getBookDAO();

	    if (session.getAttribute("read") != null
				&& session.getAttribute("read").toString().equals("n")) {
			//조회수 증가
			dao.addCnt(bookSeq, (String) session.getAttribute("userSeq"));
			session.setAttribute("read", "y");
		}
	    
	    ReviewDAO reviewDao = DAOManager.getReviewDAO();
	    
	    BookDTO dto = dao.findById(bookSeq);
	    ReviewDTO reviewDto = new ReviewDTO();
	    
	    boolean result = dao.isLike(bookSeq, userSeq);
	    boolean resultScrap = dao.isScrap(bookSeq, userSeq);
	    boolean resultReport = dao.isReport(bookSeq, userSeq);
	    
	    
	    String reviewSeq = req.getParameter("reviewSeq");
	    
	    
	    
	    ArrayList<ReviewDTO> likedReviews = reviewDao.findLikedReviews(userSeq);
	    req.setAttribute("likedReviews", likedReviews);


	    if (likedReviews != null && !likedReviews.isEmpty()) {
	        System.out.println("Liked reviews exist.");
	        System.out.println(likedReviews);
	    } else {
	        System.out.println("Liked reviews are empty.");
	    }

	   
	    
		
		System.out.println(result);
		System.out.println("scrap" + resultScrap);
		System.out.println("report" + resultReport);
		//System.out.println("reviewLike" + resultReviewLike);
		
		req.setAttribute("result", result);
		req.setAttribute("resultScrap", resultScrap);
		req.setAttribute("resultReport", resultReport);
		/* req.setAttribute("reviewLike", resultReviewLike); */
		
	    
	    //게시글 조작
	    dto.setBookTitle(dto.getBookTitle().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
	    dto.setBookInfo(dto.getBookInfo().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
	    
	    
	    PageDAO pageDAO = DAOManager.getPageDAO();
	    HashMap<Integer, PageDTO> pageMap = pageDAO.findPages(bookSeq);
	    
	    
        ArrayList<ReviewDTO> reviewList = reviewDao.findChildWhite(bookSeq);
        
        int reviewTotal = reviewDao.reviewTotal(bookSeq);
        
	    
	    req.setAttribute("dto", dto);
		req.setAttribute("pageMap", pageMap);
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("reviewTotal", reviewTotal);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookshare/bookshare_view.jsp");
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
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		
		String reviewContents = req.getParameter("writeContents");
		
		System.out.println(reviewContents);
		
		String bookSeq = req.getParameter("bookSeq");
		
		HttpSession session = req.getSession();
		
		// 로그인 여부 확인
	    String userId = (String) session.getAttribute("userId");
	    if (userId == null) {
	        // 비로그인 상태일 경우 로그인 페이지로 리디렉션
	        resp.sendRedirect("/sangsangjakka/user/login.do");
	        return;
	    }
	    
	    String userSeq = (String) session.getAttribute("userSeq");
	    
	    System.out.println(userSeq);
	    
	    ReviewDAO dao = DAOManager.getReviewDAO();
	    
	    ReviewDTO dto = new ReviewDTO();
	    
	    dto.setBookSeq(bookSeq);
	    dto.setReviewContents(reviewContents);
	    dto.setUserSeq(userSeq);
	    
	    int result = dao.add(dto);
	    
	    req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = resp.getWriter();

		if(result > 0) {
		    writer.println("<script>");
		    writer.println("alert('리뷰 작성이 완료되었습니다.');");
		    writer.println("location.href='/sangsangjakka/board/book/view.do?no=" + bookSeq + "';");
		    writer.println("</script>");
		} else {
		    writer.println("<script>");
		    writer.println("alert('리뷰 작성을 실패했습니다.');");
		    writer.println("location.href='/sangsangjakka/board/book/view.do?no=" + bookSeq + "';");
		    writer.println("</script>");
		}
		

		writer.close();
	    
		
	}
	
}//End of class
