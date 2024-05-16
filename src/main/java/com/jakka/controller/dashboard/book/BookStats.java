package com.jakka.controller.dashboard.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dto.admin.AdminDTO;
import com.jakka.model.dto.book.BookDTO;

/**
 * BookStats 서블릿은 동화책 통계 정보를 제공합니다.
 */
@WebServlet("/admin/dashboard/book/stats.do")
public class BookStats extends HttpServlet{

	/**
     * GET 요청을 처리합니다.
     * 동화책 통계 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		// 1. DB 작업 > select
		// 2. 결과 > 출력
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_book/book_stats.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	/**
     * POST 요청을 처리합니다.
     * 선택한 월의 최다 공유 동화책 정보를 JSON 형식으로 응답합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 최다 공유 동화책 수
		 String selectedMonth = req.getParameter("selectedMonth");
		
		 
		 System.out.println("동화책 선택 날짜" + selectedMonth);
		
		 
		 
		 //List<AdminDTO>inflowCount = dao.getInflowCountData(selectedMonth);
		//최다 공유 동화책 수..
		 BookDAO dao = DAOManager.getBookDAO();
		List<BookDTO>shareCount = dao.getShareCount(selectedMonth);
		System.out.println(shareCount);
		
		String json = new Gson().toJson(shareCount);
		 //String currentjson = new Gson().toJson(currentMonths);
		 //System.out.println(currentjson);
//

		

		//ArrayList<BookDTO> list = dao.findAll();

		//req.setAttribute("bookList", list);
		
		// JSON 응답 보내기
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
		
	}
	
}//End of class
