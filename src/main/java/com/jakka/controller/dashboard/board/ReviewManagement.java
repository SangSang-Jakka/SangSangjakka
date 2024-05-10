package com.jakka.controller.dashboard.board;

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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String selectedCondition = req.getParameter("condition");

		ReviewDAO dao = DAOManager.getReviewDAO();

		ArrayList<ReviewDTO> dataList = null;

		if ("option".equals(selectedCondition)) {

			dataList = dao.findAllWhite();

			ArrayList<ReviewDTO> filteredList = new ArrayList<>();

			for (ReviewDTO dto : dataList) {

				ReviewDTO filteredReview = new ReviewDTO();

				filteredReview.setReviewSeq(dto.getReviewSeq());
				filteredReview.setUserNick(dto.getUserNick());
				filteredReview.setReviewRegdate(dto.getReviewRegdate());
				filteredReview.setReviewContents(dto.getReviewContents());
				filteredReview.setReviewLikeCnt(dto.getReviewLikeCnt());
				filteredReview.setReviewReportCnt(dto.getReviewReportCnt());

				filteredList.add(filteredReview);

			}

			dataList = filteredList;

		} else if ("option2".equals(selectedCondition)) {

			dataList = dao.findAllBlack();

			ArrayList<ReviewDTO> filteredList = new ArrayList<>();

			for (ReviewDTO dto : dataList) {

				ReviewDTO filteredReview = new ReviewDTO();

				filteredReview.setReviewSeq(dto.getReviewSeq());
				filteredReview.setUserNick(dto.getUserNick());
				filteredReview.setReviewRegdate(dto.getReviewRegdate());
				filteredReview.setReviewContents(dto.getReviewContents());
				filteredReview.setReviewLikeCnt(dto.getReviewLikeCnt());
				filteredReview.setReviewReportCnt(dto.getReviewReportCnt());

				filteredList.add(filteredReview);

			}

			dataList = filteredList;

		} else if ("option3".equals(selectedCondition)) {

			dataList = dao.findAllReport();

			ArrayList<ReviewDTO> filteredList = new ArrayList<>();

			for (ReviewDTO dto : dataList) {

				ReviewDTO filteredReview = new ReviewDTO();

				filteredReview.setReviewSeq(dto.getReviewSeq());
				filteredReview.setUserNick(dto.getUserNick());
				filteredReview.setReviewRegdate(dto.getReviewRegdate());
				filteredReview.setReviewContents(dto.getReviewContents());
				filteredReview.setReviewLikeCnt(dto.getReviewLikeCnt());
				filteredReview.setReviewReportCnt(dto.getReviewReportCnt());

				filteredList.add(filteredReview);

			}

			dataList = filteredList;

		} else if ("option4".equals(selectedCondition)) {

			dataList = dao.findAllNoReport();

			ArrayList<ReviewDTO> filteredList = new ArrayList<>();

			for (ReviewDTO dto : dataList) {

				ReviewDTO filteredReview = new ReviewDTO();

				filteredReview.setReviewSeq(dto.getReviewSeq());
				filteredReview.setUserNick(dto.getUserNick());
				filteredReview.setReviewRegdate(dto.getReviewRegdate());
				filteredReview.setReviewContents(dto.getReviewContents());
				filteredReview.setReviewLikeCnt(dto.getReviewLikeCnt());
				filteredReview.setReviewReportCnt(dto.getReviewReportCnt());

				filteredList.add(filteredReview);

			}

			dataList = filteredList;

		} else {

			dataList = dao.findAll();
		}

		// JSON 으로 변환
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataList, new TypeToken<ArrayList<ReviewDTO>>() {
		}.getType());

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonData);

	}
	
}// end
