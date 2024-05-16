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
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dto.board.BoardCommentDTO;

/**
* CommentManagement 서블릿은 댓글 관리 기능을 제공합니다.
*/
@WebServlet("/admin/dashboard/comment/manage.do")
public class CommentManagement extends HttpServlet {

	/**
     * GET 요청을 처리합니다.
     * 전체 댓글 목록을 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		BoardCommentsDAO dao = DAOManager.getBoardCommentDAO();

		// 전체 댓글 목록

		ArrayList<BoardCommentDTO> list = dao.findAll();

		req.setAttribute("cmntList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/comment_manage.jsp");
		dispatcher.forward(req, resp);

	}

	/**
     * POST 요청을 처리합니다.
     * 선택한 조건에 따라 댓글 목록을 필터링하여 JSON 형식으로 응답합니다.
     * 조건: 전체, 활성화된 댓글, 비활성화된 댓글, 신고된 댓글, 신고되지 않은 댓글
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String selectedCondition = req.getParameter("condition");

		BoardCommentsDAO cmntDAO = DAOManager.getBoardCommentDAO();

		ArrayList<BoardCommentDTO> dataList = null;

		if ("option".equals(selectedCondition)) {
			// 조건 1 선택시 출력될 데이터 목록
			// 댓글 공개
			dataList = cmntDAO.findAllWhite();

			// 필요한 데이터만 추출하여 새로운 ArrayList 추가
			// 번호, 작성자, 작성일, 댓글내용, 신고수
			ArrayList<BoardCommentDTO> filteredList = new ArrayList<>();

			for (BoardCommentDTO cmnt : dataList) {
				
				BoardCommentDTO filteredCmnt = new BoardCommentDTO();

				filteredCmnt.setCmntSeq(cmnt.getCmntSeq());
				filteredCmnt.setUserNick(cmnt.getUserNick());
				filteredCmnt.setCmntRegdate(cmnt.getCmntRegdate());
				filteredCmnt.setCmntContents(cmnt.getCmntContents());
				filteredCmnt.setCmntReportCnt(cmnt.getCmntReportCnt());

				filteredList.add(filteredCmnt);

			}

			dataList = filteredList;

		} else if ("option2".equals(selectedCondition)) {

			// 조건 2 선택
			// 비공개
			dataList = cmntDAO.findAllBlack();

			ArrayList<BoardCommentDTO> filteredList = new ArrayList<>();

			for (BoardCommentDTO cmnt : dataList) {
				BoardCommentDTO filteredCmnt = new BoardCommentDTO();

				filteredCmnt.setCmntSeq(cmnt.getCmntSeq());
				filteredCmnt.setUserNick(cmnt.getUserNick());
				filteredCmnt.setCmntRegdate(cmnt.getCmntRegdate());
				filteredCmnt.setCmntContents(cmnt.getCmntContents());
				filteredCmnt.setCmntReportCnt(cmnt.getCmntReportCnt());

				filteredList.add(filteredCmnt);

			}

			dataList = filteredList;

		} else if ("option3".equals(selectedCondition)) {

			// 조건 3 선택
			// 신고 O
			dataList = cmntDAO.findAllReport();

			ArrayList<BoardCommentDTO> filteredList = new ArrayList<>();

			for (BoardCommentDTO cmnt : dataList) {
				BoardCommentDTO filteredCmnt = new BoardCommentDTO();

				filteredCmnt.setCmntSeq(cmnt.getCmntSeq());
				filteredCmnt.setUserNick(cmnt.getUserNick());
				filteredCmnt.setCmntRegdate(cmnt.getCmntRegdate());
				filteredCmnt.setCmntContents(cmnt.getCmntContents());
				filteredCmnt.setCmntReportCnt(cmnt.getCmntReportCnt());

				filteredList.add(filteredCmnt);

			}

			dataList = filteredList;

		} else if ("option4".equals(selectedCondition)) {

			// 조건 4 선택
			dataList = cmntDAO.findAllNoReport();

			ArrayList<BoardCommentDTO> filteredList = new ArrayList<>();

			for (BoardCommentDTO cmnt : dataList) {
				BoardCommentDTO filteredCmnt = new BoardCommentDTO();

				filteredCmnt.setCmntSeq(cmnt.getCmntSeq());
				filteredCmnt.setUserNick(cmnt.getUserNick());
				filteredCmnt.setCmntRegdate(cmnt.getCmntRegdate());
				filteredCmnt.setCmntContents(cmnt.getCmntContents());
				filteredCmnt.setCmntReportCnt(cmnt.getCmntReportCnt());

				filteredList.add(filteredCmnt);

			}

			dataList = filteredList;

		}

		else

		{

			dataList = cmntDAO.findAll();
		}

		// JSON으로 변환
		Gson gson = new Gson();
		// "Action" 열을 제외하고 JSON 데이터 생성

		String jsonData = gson.toJson(dataList, new TypeToken<ArrayList<BoardCommentDTO>>() {}.getType());

		// 응답에 JSON 데이터 전송
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonData);

	}

}// end
