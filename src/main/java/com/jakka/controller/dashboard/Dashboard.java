package com.jakka.controller.dashboard;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.admin.AdminDTO;

/**
 * Dashboard 서블릿은 관리자 대시보드 페이지를 제공하며,
 * 유입 경로 데이터와 동화책 제작 통계를 가져옵니다.
 */
@WebServlet("/admin/dashboard.do")
public class Dashboard extends HttpServlet {

	/**
     * GET 요청을 처리합니다.
     * 대시보드 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard.jsp");
		dispatcher.forward(req, resp);

	}
	
	/**
     * POST 요청을 처리합니다.
     * 선택한 월의 유입 경로 데이터와 동화책 제작 통계를 가져와
     * JSON 형식으로 응답합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String selectedMonth = req.getParameter("selectedMonth");
		 String year = req.getParameter("year");
		 
		 AdminDAO dao = DAOManager.getAdminDAO();
		 List<AdminDTO>inflowCount = dao.getInflowCountData(selectedMonth);
		 String json = new Gson().toJson(inflowCount);
			 
			 BookDAO bookdao = DAOManager.getBookDAO();
			 Map<String, Integer> makeBook  = bookdao.makeBook(year);

			    
			 JsonArray bookData = new JsonArray();

			 for (Map.Entry<String, Integer> entry : makeBook.entrySet()) {
			     JsonObject monthData = new JsonObject();
			     monthData.addProperty("monthYear", entry.getKey());
			     monthData.addProperty("bookCount", entry.getValue());
			     bookData.add(monthData);
			 }

			 // 만들어진 JSON 배열을 JsonObject에 추가

			 // Gson을 사용하여 JSON 문자열로 변환
			 Gson gson = new Gson();
			 String jsonmakeBookData = gson.toJson(bookData);
			    
			    
		// JSON 응답 보내기
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
		//resp.getWriter().write(currentjson);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonmakeBookData);
		
	}

}// End of class
