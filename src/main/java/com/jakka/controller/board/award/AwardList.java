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
	        String monthParam = req.getParameter("month");
	        String monthString = null; // monthString 변수 초기화

	        // 'month' 값이 숫자로만 이루어져 있는지 확인
	        if (monthParam != null && monthParam.matches("\\d+")) {
	            int month = Integer.parseInt(monthParam);

	            // 월 값이 유효한 범위인지 확인 (1 ~ 12)
	            if (month >= 1 && month <= 12) {
	                monthString = String.format("%02d", month); // 월 값을 두 자리 문자열로 변환 (예: '05')
	            }
	        }

	        BookDAO bookDAO = DAOManager.getBookDAO();
	        ArrayList<BookDTO> list = bookDAO.findNowAward();
	        ArrayList<BookDTO> listMonth = null;

	        if (monthString != null) {
	            listMonth = bookDAO.findMonthAward(monthString);
	        }

	        req.setAttribute("list", list);
	        req.setAttribute("listMonth", listMonth);

	        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/award/award_list.jsp");
	        dispatcher.forward(req, resp);
	    } catch (Exception e) {
	        // 예외 처리
	        e.printStackTrace();
	        resp.sendRedirect("/sangsangjakka/error.jsp");
	    }
	}
}