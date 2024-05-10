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
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.board.SuggestionDTO;

@WebServlet("/admin/dashboard/freeboard/manage.do")
public class FreeboardManagement extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		BoardDAO boardDAO = DAOManager.getBoardDAO();

		// 자유게시판 목록
		ArrayList<BoardDTO> list = boardDAO.findAll();

		req.setAttribute("boardList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/freeboard_manage.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String selectedCondition = req.getParameter("condition");

		BoardDAO boardDAO = DAOManager.getBoardDAO();

		ArrayList<BoardDTO> dataList = null;

		if ("option".equals(selectedCondition)) {
			// 조건 1 선택시 출력될 데이터 목록
			dataList = boardDAO.findAllWhite();

			// 필요한 데이터만 추출하여 새로운 ArrayList 추가
			// 번호, 제목, 작성자, 작성일, 조회수, 신고수
			ArrayList<BoardDTO> filteredList = new ArrayList<>();

			for (BoardDTO board : dataList) {
				BoardDTO filteredBoard = new BoardDTO();

				filteredBoard.setBoardSeq(board.getBoardSeq());
				filteredBoard.setBoardTitle(board.getBoardTitle());
				filteredBoard.setUserNick(board.getUserNick());
				filteredBoard.setBoardRegdate(board.getBoardRegdate());
				filteredBoard.setBoardCnt(board.getBoardCnt());
				filteredBoard.setBoardReportCnt(board.getBoardReportCnt());

				filteredList.add(filteredBoard);

			}

			dataList = filteredList;

		} else if ("option2".equals(selectedCondition)) {

			// 조건 2 선택
			dataList = boardDAO.findAllBlack();
			// 필요한 데이터만 추출하여 새로운 ArrayList 추가
			ArrayList<BoardDTO> filteredList = new ArrayList<>();

			for (BoardDTO board : dataList) {
				BoardDTO filteredBoard = new BoardDTO();

				filteredBoard.setBoardSeq(board.getBoardSeq());
				filteredBoard.setBoardTitle(board.getBoardTitle());
				filteredBoard.setUserNick(board.getUserNick());
				filteredBoard.setBoardRegdate(board.getBoardRegdate());
				filteredBoard.setBoardCnt(board.getBoardCnt());
				filteredBoard.setBoardReportCnt(board.getBoardReportCnt());

				filteredList.add(filteredBoard);

			}

			dataList = filteredList;

		} else if ("option3".equals(selectedCondition)) {

			// 조건 3 선택
			dataList = boardDAO.findAllReport();

			// 필요한 데이터만 추출하여 새로운 ArrayList 추가
			ArrayList<BoardDTO> filteredList = new ArrayList<>();

			for (BoardDTO board : dataList) {
				BoardDTO filteredBoard = new BoardDTO();

				filteredBoard.setBoardSeq(board.getBoardSeq());
				filteredBoard.setBoardTitle(board.getBoardTitle());
				filteredBoard.setUserNick(board.getUserNick());
				filteredBoard.setBoardRegdate(board.getBoardRegdate());
				filteredBoard.setBoardCnt(board.getBoardCnt());
				filteredBoard.setBoardReportCnt(board.getBoardReportCnt());

				filteredList.add(filteredBoard);

			}

			dataList = filteredList;

		} else if ("option4".equals(selectedCondition)) {

			// 조건 4 선택
			dataList = boardDAO.findAllNoReport();

			// 필요한 데이터만 추출하여 새로운 ArrayList 추가
			ArrayList<BoardDTO> filteredList = new ArrayList<>();

			for (BoardDTO board : dataList) {
				BoardDTO filteredBoard = new BoardDTO();

				filteredBoard.setBoardSeq(board.getBoardSeq());
				filteredBoard.setBoardTitle(board.getBoardTitle());
				filteredBoard.setUserNick(board.getUserNick());
				filteredBoard.setBoardRegdate(board.getBoardRegdate());
				filteredBoard.setBoardCnt(board.getBoardCnt());
				filteredBoard.setBoardReportCnt(board.getBoardReportCnt());

				filteredList.add(filteredBoard);

			}

			dataList = filteredList;

		}

		else

		{
			// 전체 선택시 출력될 데이터 목록
			dataList = boardDAO.findAll();
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
