package com.jakka.controller.dashboard.board;

import java.io.IOException;
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

@WebServlet("/admin/dashboard/bookshare/manageview.do")
public class BookShareManagementView extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		String seq = req.getParameter("seq");
		
		
		BookDAO bookDAO = DAOManager.getBookDAO();
		
		// 게시물 가져오기
		BookDTO dto = bookDAO.findById(seq);
		
		// dto.setBookTitle(dto.getBookTitle().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
	    // dto.setBookInfo(dto.getBookInfo().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
		
	    PageDAO pageDAO = DAOManager.getPageDAO();
	    HashMap<Integer, PageDTO> pageMap = pageDAO.findPages(seq);
		
		// 리뷰 가져오기
		ReviewDAO reviewDAO = DAOManager.getReviewDAO();
		
		ArrayList<ReviewDTO> reviewList = reviewDAO.findChild(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("pageMap", pageMap);
		req.setAttribute("reviewList", reviewList);


		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/bookshare_manage_view.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}//End of class
