package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dto.book.ReviewDTO;

@WebServlet("/admin/dashboard/review/manage.do")
public class ReviewManagement extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		ReviewDAO dao = DAOManager.getReviewDAO();
		
		ArrayList<ReviewDTO> list = dao.findAll();
		
		req.setAttribute("reviewList", list);
		
		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/review_manage.jsp");
		dispatcher.forward(req, resp);
		
		
	}

}//end
