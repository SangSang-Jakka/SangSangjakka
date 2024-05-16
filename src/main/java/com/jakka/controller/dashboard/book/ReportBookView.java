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
 * ReportBookView 서블릿은 신고된 동화책 상세 정보와 신고자 목록을 제공합니다.
 */
@WebServlet("/admin/dashboard/book/reportview.do")
public class ReportBookView extends HttpServlet{

	/**
     * GET 요청을 처리합니다.
     * 선택한 동화책의 상세 정보와 신고자 목록을 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String seq = req.getParameter("seq");
		
		BookDAO dao = DAOManager.getBookDAO();
		
		BookDTO dto = dao.findById(seq);
		
		req.setAttribute("dto", dto);
		
		ArrayList<BookDTO> list = dao.findByReport(seq);
		
		req.setAttribute("reportList", list);

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_book/report_book_view.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}//End of class
