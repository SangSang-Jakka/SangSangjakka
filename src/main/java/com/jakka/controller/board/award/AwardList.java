package com.jakka.controller.board.award;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dto.book.BookDTO;

@WebServlet("/board/award/list.do")
public class AwardList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			
			BookDAO bookDAO = DAOManager.getBookDAO();
			
	        ArrayList<BookDTO> list = bookDAO.findNowAward();
	        
	        req.setAttribute("list", list);
	        
	        
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/award/award_list.jsp");
		dispatcher.forward(req, resp);
		}catch (Exception e) {
	        // 예외 처리
	        e.printStackTrace();
	        resp.sendRedirect("/sangsangjakka/error.jsp");
	    }
	}
	
}
