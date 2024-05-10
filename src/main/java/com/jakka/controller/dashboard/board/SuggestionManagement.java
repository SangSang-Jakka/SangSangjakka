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
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.SuggestionAnswerDTO;
import com.jakka.model.dto.board.SuggestionDTO;

@WebServlet("/admin/dashboard/suggestion/manage.do")
public class SuggestionManagement extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		SuggestionDAO suggestionDAO = DAOManager.getSuggestionDAO();

		// 건의사항 목록
		ArrayList<SuggestionDTO> list = suggestionDAO.findAll();

		req.setAttribute("suggestionList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/suggestion_manage.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String selectedCondition = req.getParameter("condition");

		SuggestionDAO sgstDAO = DAOManager.getSuggestionDAO();

		ArrayList<SuggestionDTO> dataList = null;

		if ("option".equals(selectedCondition)) {
			// 조건 1 선택시 출력될 데이터 목록
			dataList = sgstDAO.findAllSecret();

			// 필요한 데이터만 추출하여 새로운 ArrayList 추가
			ArrayList<SuggestionDTO> filteredList = new ArrayList<>();

			for (SuggestionDTO sgst : dataList) {
				SuggestionDTO filteredSgst = new SuggestionDTO();

				filteredSgst.setSgstSeq(sgst.getSgstSeq());
				filteredSgst.setSgstSecretYN(sgst.getSgstSecretYN());
				filteredSgst.setSgstTitle(sgst.getSgstTitle());
				filteredSgst.setUserNick(sgst.getUserNick());
				filteredSgst.setSgstRegdate(sgst.getSgstRegdate());
				filteredSgst.setSgstCnt(sgst.getSgstCnt());

				filteredList.add(filteredSgst);

			}

			dataList = filteredList;

		} else if ("option2".equals(selectedCondition)) {

			// 조건 2 선택
			dataList = sgstDAO.findAllOpen();

			// 필요한 데이터만 추출하여 새로운 ArrayList 추가
			ArrayList<SuggestionDTO> filteredList = new ArrayList<>();

			for (SuggestionDTO sgst : dataList) {
				SuggestionDTO filteredSgst = new SuggestionDTO();

				filteredSgst.setSgstSeq(sgst.getSgstSeq());
				filteredSgst.setSgstSecretYN(sgst.getSgstSecretYN());
				filteredSgst.setSgstTitle(sgst.getSgstTitle());
				filteredSgst.setUserNick(sgst.getUserNick());
				filteredSgst.setSgstRegdate(sgst.getSgstRegdate());
				filteredSgst.setSgstCnt(sgst.getSgstCnt());

				filteredList.add(filteredSgst);

			}

			dataList = filteredList;

		} else if ("option3".equals(selectedCondition)) {

			// 조건 3 선택
			dataList = sgstDAO.findAllAnswer();

			// 필요한 데이터만 추출하여 새로운 ArrayList 추가
			ArrayList<SuggestionDTO> filteredList = new ArrayList<>();

			for (SuggestionDTO sgst : dataList) {
				SuggestionDTO filteredSgst = new SuggestionDTO();

				filteredSgst.setSgstSeq(sgst.getSgstSeq());
				filteredSgst.setSgstSecretYN(sgst.getSgstSecretYN());
				filteredSgst.setSgstTitle(sgst.getSgstTitle());
				filteredSgst.setUserNick(sgst.getUserNick());
				filteredSgst.setSgstRegdate(sgst.getSgstRegdate());
				filteredSgst.setSgstCnt(sgst.getSgstCnt());

				filteredList.add(filteredSgst);

			}

			dataList = filteredList;

		} else if ("option4".equals(selectedCondition)) {

			// 조건 4 선택
			dataList = sgstDAO.findAllNoAnswer();

			// 필요한 데이터만 추출하여 새로운 ArrayList 추가
			ArrayList<SuggestionDTO> filteredList = new ArrayList<>();

			for (SuggestionDTO sgst : dataList) {
				SuggestionDTO filteredSgst = new SuggestionDTO();

				filteredSgst.setSgstSeq(sgst.getSgstSeq());
				filteredSgst.setSgstSecretYN(sgst.getSgstSecretYN());
				filteredSgst.setSgstTitle(sgst.getSgstTitle());
				filteredSgst.setUserNick(sgst.getUserNick());
				filteredSgst.setSgstRegdate(sgst.getSgstRegdate());
				filteredSgst.setSgstCnt(sgst.getSgstCnt());

				filteredList.add(filteredSgst);

			}

			dataList = filteredList;

		}
		
		else {

			// 전체 선택시 출력될 데이터 목록
			dataList = sgstDAO.findAll();
		}

		// JSON으로 변환
		Gson gson = new Gson();
		// "Action" 열을 제외하고 JSON 데이터 생성
		String jsonData = gson.toJson(dataList, new TypeToken<ArrayList<SuggestionDTO>>() {
		}.getType());

		// 응답에 JSON 데이터 전송
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonData);

	}

}// End of class
