package com.jakka.controller.board.bookshare;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dto.book.ReviewDTO;

/**
 * 동화책 리뷰 수정 서블릿 클래스입니다.
 *
 * @author Jakka
 */
@WebServlet("/board/book/edit.do")
public class BookEdit extends HttpServlet {
	
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
		// TODO Auto-generated method stub
		super.doGet(req, resp);
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

		String editReviewContents = req.getParameter("editReviewContents");
		String reviewSeq = req.getParameter("reviewSeq");
		String bookSeq = req.getParameter("bookSeq");

		System.out.println("받은 리뷰 내용: " + editReviewContents);
		System.out.println("받은 리뷰 번호: " + reviewSeq);
		System.out.println("받은 동화책 번호: " + bookSeq);

		
		 ReviewDAO dao = DAOManager.getReviewDAO();
		  
		 ReviewDTO dto = new ReviewDTO();
		 
		 
		 dto.setReviewContents(editReviewContents);
		 dto.setReviewSeq(reviewSeq);
		  
		 ReviewDTO resultEdit = dao.edit(dto);
		 
		 String reviewContents = resultEdit.getReviewContents();
		 
		 System.out.println(reviewContents);	
		 
		 // Gson 라이브러리를 사용하여 JSON 형식으로 변환합니다.
		 Gson gson = new Gson();
		 String jsonResultEdit = gson.toJson(resultEdit);
		 
		 // 클라이언트에게 JSON 응답을 전송합니다.
		 resp.setContentType("application/json");
		 resp.setCharacterEncoding("UTF-8");
		 resp.getWriter().write(jsonResultEdit);
		 
	}
		 

}
