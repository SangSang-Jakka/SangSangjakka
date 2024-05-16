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

/**
 * BookAward 서블릿은 동화책 수상 관리 기능을 제공합니다.
 */
@WebServlet("/admin/dashboard/book/award.do")
public class BookAward extends HttpServlet {

	/**
     * GET 요청을 처리합니다.
     * 수상 동화책 목록을 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
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

	/**
     * POST 요청을 처리합니다.
     * 선택한 동화책의 수상 정보를 삭제합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
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
