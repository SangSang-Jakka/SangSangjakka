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

@WebServlet("/admin/dashboard/book/award.do")
public class BookAward extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		BookDAO bookDAO = DAOManager.getBookDAO();

		ArrayList<BookDTO> list = bookDAO.findAllAward();

		req.setAttribute("awardList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_book/book_award.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//
		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");

		String bookSeq = req.getParameter("bookSeq");

		if (adId != null) {

			// 삭제
			BookDAO bookDAO = DAOManager.getBookDAO();
			int result = bookDAO.delAward(bookSeq);

			if (result > 0) {
				// 삭제 성공
				resp.getWriter().write("success");
			} else {

				resp.getWriter().write("fail");

			}

		}

	}

}// End of class
