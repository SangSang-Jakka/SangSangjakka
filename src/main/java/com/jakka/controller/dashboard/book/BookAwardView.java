package com.jakka.controller.dashboard.book;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/admin/dashboard/book/awardview.do")
public class BookAwardView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		BookDAO dao = DAOManager.getBookDAO();

		ArrayList<BookDTO> list = dao.findAllWhite();

		req.setAttribute("bookList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_book/book_award_view.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");

		String[] selectedBookSeqs = req.getParameterValues("selectedBooks");
		String[] selectedRanks = req.getParameterValues("selectedRanks"); // 수정된 부분
		
		BookDAO bookDAO = DAOManager.getBookDAO();

		ArrayList<BookDTO> selectedBooks = new ArrayList<>();

		// 선택된 동화책들에 해당하는 BookDTO 객체를 생성하여 리스트에 추가

		for (int i = 0; i < selectedBookSeqs.length; i++) {
			
		
			
		    String bookSeq = selectedBookSeqs[i];
	        String rank = selectedRanks[i]; 
	        
	        BookDTO dto = bookDAO.findById(bookSeq);
	        dto.setAwardRank(rank); // 등수 설정
	        selectedBooks.add(dto);
	        
		
		}
	

		// DAO 메서드를 호출하여 수상작 등록
		int result = bookDAO.presentAward(selectedBooks, adId);

		// 결과에 따른 응답 처리
		if (result == 0) {
			resp.getWriter().write("success");
		} else {
			resp.getWriter().write("failure");
		}
	}

}// End of class
