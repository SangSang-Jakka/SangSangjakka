package com.jakka.controller.dashboard.book;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dto.book.BookDTO;

@WebServlet("/admin/dashboard/book/report.do")
public class ReportBook extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		BookDAO bookDAO = DAOManager.getBookDAO();

		ArrayList<BookDTO> list = bookDAO.findAllReport();

		req.setAttribute("reportList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_book/report_book_manage.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String selectedCondition = req.getParameter("condition");

		BookDAO bookDAO = DAOManager.getBookDAO();

		ArrayList<BookDTO> dataList = null;

		if ("option".equals(selectedCondition)) {
			// 조건 1 선택시 출력될 데이터 목록
			dataList = bookDAO.findAllReport();
		} else if ("option2".equals(selectedCondition)) {
			// 조건 2 선택
			dataList = bookDAO.findAllReport();
		} else {
			// 전체
			dataList = bookDAO.findAllReport();
		}

		// JSON으로 변환
		Gson gson = new Gson();
		// "Action" 열을 제외하고 JSON 데이터 생성
		String jsonData = gson.toJson(dataList, new TypeToken<ArrayList<BookDTO>>() {
		}.getType());

		// 응답에 JSON 데이터 전송
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonData);

	}

}// End of class
