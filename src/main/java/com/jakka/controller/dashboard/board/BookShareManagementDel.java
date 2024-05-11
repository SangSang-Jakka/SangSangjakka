package com.jakka.controller.dashboard.board;

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

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;
import com.jakka.model.dto.book.ReviewDTO;

@WebServlet("/admin/dashboard/bookshare/managedel.do")
public class BookShareManagementDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		// 로그인 여부 확인
		String adId = (String) session.getAttribute("adId");
		if (adId == null) {
			resp.sendRedirect("/sangsangjakka/admin/login.do");
			return;
		}

		String seq = req.getParameter("seq");

		BookDAO dao = DAOManager.getBookDAO();

		// 게시물 가져오기
		BookDTO dto = dao.findById(seq);

		// 게시글 조작
		dto.setBookTitle(dto.getBookTitle().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
		dto.setBookInfo(dto.getBookInfo().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));

		PageDAO pageDAO = DAOManager.getPageDAO();
		HashMap<Integer, PageDTO> pageMap = pageDAO.findPages(seq);

		// 리뷰 가져오기
		ReviewDAO reviewDAO = DAOManager.getReviewDAO();

		ArrayList<ReviewDTO> reviewList = reviewDAO.findChild(seq);

		req.setAttribute("dto", dto);
		req.setAttribute("pageMap", pageMap);
		req.setAttribute("reviewList", reviewList);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/bookshare_manage_view.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");

		if (adId == null) {
			req.setAttribute("errorMessage", "로그인이 필요합니다.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/admin/admin_login.jsp");
			dispatcher.forward(req, resp);
		}

		String action = req.getParameter("action");

		// 동화책 비활성화
		if ("disableBook".equals(action)) {
			String bookSeq = req.getParameter("bookSeq");

			BookDAO dao = DAOManager.getBookDAO();
			int result = dao.disable(bookSeq, adId);

			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain");
			PrintWriter writer = resp.getWriter();

			if (result > 0) {
				writer.print("동화책이 비공개 처리하었습니다.");
			} else {
				writer.print("동화책 비공개 처리 실패했습니다.");

			}
		}

		// 동화책 활성화
		else if ("activationBook".equals(action)) {
			String bookSeq = req.getParameter("bookSeq");
			BookDAO dao = DAOManager.getBookDAO();
			int result = dao.activation(bookSeq, adId);

			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain");
			PrintWriter writer = resp.getWriter();

			if (result > 0) {
				writer.print("동화책 공개 처리하였습니다.");
			} else {
				writer.print("동화책 공개 처리에 실패했습니다.");

			}

		}

	}

}// End