package com.jakka.controller.dashboard.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dao.board.NoticeDAOImpl;
import com.jakka.model.dto.board.NoticeDTO;

/**
 * NoticeManagement 서블릿은 공지사항 관리 기능을 제공합니다.
 */
@WebServlet("/admin/dashboard/notice/manage.do")
public class NoticeManagement extends HttpServlet {
	
	/**
     * GET 요청을 처리합니다.
     * 전체 공지사항 목록을 조회하여 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		// 1. DB 작업 > select
		// 2. 결과 > 출력

		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();

		// 공지사항 목록을 가져옴
		ArrayList<NoticeDTO> list = noticeDAO.findAll();

		req.setAttribute("noticeList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/notice_manage.jsp");
		dispatcher.forward(req, resp);

	}

	/**
     * POST 요청을 처리합니다.
     * 선택한 조건에 따라 공지사항 목록을 필터링하여 JSON 형식으로 응답합니다.
     * 조건: 전체, 고정된 공지사항
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
		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();

		ArrayList<NoticeDTO> dataList = null;

		if ("option".equals(selectedCondition)) {
			// 조건 1 선택시 출력될 데이터 목록
			dataList = noticeDAO.findAllFix();

			// 필요한 데이터만 추출하여 새로운 ArrayList 추가
			ArrayList<NoticeDTO> filteredList = new ArrayList<>();
			
			for (NoticeDTO notice : dataList) {
				NoticeDTO filteredNotice = new NoticeDTO();
				filteredNotice.setNoticeSeq(notice.getNoticeSeq());
				filteredNotice.setNoticeTitle(notice.getNoticeTitle());
				filteredNotice.setAdId(notice.getAdId());
				filteredNotice.setNoticeRegdate(notice.getNoticeRegdate());
				filteredNotice.setNoticeCnt(notice.getNoticeCnt());
			
				
				filteredList.add(filteredNotice);
			}
			dataList = filteredList;

		}  else {
			// 전체 선택시 출력될 데이터 목록
			dataList = noticeDAO.findAll();
		}

		// JSON으로 변환
		Gson gson = new Gson();
		// "Action" 열을 제외하고 JSON 데이터 생성
		// String jsonData = gson.toJson(dataList);
		String jsonData = gson.toJson(dataList, new TypeToken<ArrayList<NoticeDTO>>() {}.getType());

		// 응답에 JSON 데이터 전송
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonData);

		// req.setAttribute("fixList", fixList);

	}

}// End of class
