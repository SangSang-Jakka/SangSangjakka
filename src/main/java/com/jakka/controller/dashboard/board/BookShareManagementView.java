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

/**
 * BookShareManagementView 서블릿은 동화책 공유 관리 기능 중 리뷰 비활성화/활성화 기능을 제공합니다.
 */
@WebServlet("/admin/dashboard/bookshare/manageview.do")
public class BookShareManagementView extends HttpServlet {

	/**
     * GET 요청을 처리합니다.
     * 선택한 동화책의 상세 정보, 페이지 정보, 리뷰 목록을 조회하여 JSP 페이지로 전달합니다.
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

	/**
     * POST 요청을 처리합니다.
     * 리뷰의 비활성화 또는 활성화를 처리합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
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

		// 리뷰 비활성화
		if ("disableReview".equals(action)) {
			String reviewSeq = req.getParameter("reviewSeq");
			ReviewDAO dao = DAOManager.getReviewDAO();
			int result = dao.disable(reviewSeq, adId);

			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain");
			PrintWriter writer = resp.getWriter();

			if (result > 0) {
				writer.print("해당 리뷰를 비공개 처리하였습니다.");
			} else {
				writer.print("리뷰 비공개 처리에 실패했습니다.");

			}
		}

		// 댓글 활성화
		else if ("activationReview".equals(action)) {
			String reviewSeq = req.getParameter("reviewSeq");
			ReviewDAO dao = DAOManager.getReviewDAO();
			int result = dao.activation(reviewSeq, adId);

			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain");
			PrintWriter writer = resp.getWriter();

			if (result > 0) {
				writer.print("해당 리뷰를 공개 처리하였습니다.");
			} else {
				writer.print("리뷰 공개 처리에 실패했습니다.");

			}

		}

	}

}
// End of class
