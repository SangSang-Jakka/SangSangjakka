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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dao.board.NoticeDAOImpl;
import com.jakka.model.dto.board.NoticeDTO;

@WebServlet("/admin/dashboard/notice/manage.do")
public class NoticeManagement extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// req.setCharacterEncoding("UTF-8");
		//1. DB 작업 > select
		//2. 결과 > 출력
		
		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();
		
		// 공지사항 목록을 가져옴
		ArrayList<NoticeDTO> list = noticeDAO.findAll();
		
		req.setAttribute("noticeList", list);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/notice_manage.jsp");
		dispatcher.forward(req, resp);
			
		
	}
	
}//End of class
