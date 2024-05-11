package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dto.book.BookDTO;

@WebServlet("/admin/dashboard/bookshare/manage.do")
public class BookShareManagement extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		// 1. DB 작업 > select
		// 2. 결과 > 출력

		BookDAO dao = DAOManager.getBookDAO();

		ArrayList<BookDTO> list = dao.findAll();

		req.setAttribute("bookList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/bookshare_manage.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String selectedCondition = req.getParameter("condition");

		BookDAO bookDAO = DAOManager.getBookDAO();

		ArrayList<BookDTO> dataList = null;

		if ("option".equals(selectedCondition)) {
			// 조건 1 선택시 출력될 데이터 목록
			dataList = bookDAO.findAllWhite();

			// 필요한 데이터 추출
			// 번호, 동화책명, 작성자, 작성일, 조회수, 저장수, 좋아요수, 소감수

			ArrayList<BookDTO> filteredList = new ArrayList<>();

			for (BookDTO book : dataList) {
				BookDTO filteredBook = new BookDTO();

				filteredBook.setBookSeq(book.getBookSeq());
				filteredBook.setBookTitle(book.getBookTitle());
				filteredBook.setUserNick(book.getUserNick());
				filteredBook.setBookRegdate(book.getBookRegdate());
				filteredBook.setBookCnt(book.getBookCnt());
				filteredBook.setBookScrapCnt(book.getBookScrapCnt());
				filteredBook.setLikeCnt(book.getLikeCnt());
				filteredBook.setBookReviewCnt(book.getBookReviewCnt());
				filteredBook.setBookReportCnt(book.getBookReportCnt());

				filteredList.add(filteredBook);

			}

			dataList = filteredList;

		} else if ("option2".equals(selectedCondition)) {

			// vwBlack에 좋아요, 리뷰, 소감수가 없음
			// 조건 2 선택
			dataList = bookDAO.findAllBlack();

			ArrayList<BookDTO> filteredList = new ArrayList<>();

			for (BookDTO book : dataList) {
				BookDTO filteredBook = new BookDTO();

				filteredBook.setBookSeq(book.getBookSeq());
				filteredBook.setBookTitle(book.getBookTitle());
				filteredBook.setUserNick(book.getUserNick());
				filteredBook.setBookRegdate(book.getBookRegdate());
				filteredBook.setBookCnt(book.getBookCnt());
//				filteredBook.setBookScrapCnt(book.getBookScrapCnt());
//				filteredBook.setLikeCnt(book.getLikeCnt());
//				filteredBook.setBookReviewCnt(book.getBookReviewCnt());
				filteredBook.setBookReportCnt(book.getBookReportCnt());

				filteredList.add(filteredBook);

			}

			dataList = filteredList;

		} else if ("option3".equals(selectedCondition)) {

			// 조건 3 선택
			dataList = bookDAO.findAllReport();

			ArrayList<BookDTO> filteredList = new ArrayList<>();

			for (BookDTO book : dataList) {
				BookDTO filteredBook = new BookDTO();

				filteredBook.setBookSeq(book.getBookSeq());
				filteredBook.setBookTitle(book.getBookTitle());
				filteredBook.setUserNick(book.getUserNick());
				filteredBook.setBookRegdate(book.getBookRegdate());
				filteredBook.setBookCnt(book.getBookCnt());
				filteredBook.setBookScrapCnt(book.getBookScrapCnt());
				filteredBook.setLikeCnt(book.getLikeCnt());
				filteredBook.setBookReviewCnt(book.getBookReviewCnt());
				filteredBook.setBookReportCnt(book.getBookReportCnt());

				filteredList.add(filteredBook);

			}

			dataList = filteredList;

		} else if ("option4".equals(selectedCondition)) {

			// 조건 4 선택
			dataList = bookDAO.findAllNoReport();

			ArrayList<BookDTO> filteredList = new ArrayList<>();

			for (BookDTO book : dataList) {
				BookDTO filteredBook = new BookDTO();

				filteredBook.setBookSeq(book.getBookSeq());
				filteredBook.setBookTitle(book.getBookTitle());
				filteredBook.setUserNick(book.getUserNick());
				filteredBook.setBookRegdate(book.getBookRegdate());
				filteredBook.setBookCnt(book.getBookCnt());
				filteredBook.setBookScrapCnt(book.getBookScrapCnt());
				filteredBook.setLikeCnt(book.getLikeCnt());
				filteredBook.setBookReviewCnt(book.getBookReviewCnt());
				filteredBook.setBookReportCnt(book.getBookReportCnt());

				filteredList.add(filteredBook);

			}

			dataList = filteredList;

		} else {
			// 전체 선택시 출력될 데이터 목록

			dataList = bookDAO.findAll();

		}

		// JSON으로 변환
		Gson gson = new Gson();
		// "Action" 열을 제외하고 JSON 데이터 생성
		String jsonData = gson.toJson(dataList, new TypeToken<ArrayList<BookDTO>>() {
		}.getType());

		// 응답에 JSON 데이터 전송
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonData);

	}

}// End of class
