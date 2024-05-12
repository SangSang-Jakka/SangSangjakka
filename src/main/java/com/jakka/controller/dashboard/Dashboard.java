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

@WebServlet("/admin/dashboard.do")
public class Dashboard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String selectedMonth = req.getParameter("selectedMonth");
		 //String currentMonth = req.getParameter("currentMonth");
		 String year = selectedMonth.substring(0, 4);
		 System.out.println("가져옴?" + selectedMonth);
		 System.out.println("년도만!?" + year);
		
		 
		 AdminDAO dao = DAOManager.getAdminDAO();
		 List<AdminDTO>inflowCount = dao.getInflowCountData(selectedMonth);
		// List<AdminDTO>currentMonths = dao.getInflowCountData(currentMonth);
		 
		 System.out.println("유입경로" + inflowCount);
		 String json = new Gson().toJson(inflowCount);
		 
		 
//		 if (selectedMonth != null) {
			 
			 BookDAO bookdao = DAOManager.getBookDAO();
			 Map<String, Integer> makeBook  = bookdao.makeBook(year);

			 System.out.println("북 만든 수 " + makeBook);
			 
			 JsonObject bookData = new JsonObject();
			    JsonArray monthYears = new JsonArray();
			    JsonArray bookCounts = new JsonArray();

			    // Map 데이터를 JsonObject로 변환
			    for (Map.Entry<String, Integer> entry : makeBook.entrySet()) {
			        String monthYear = entry.getKey();
			        int bookCount = entry.getValue();

			        // monthYear와 bookCount를 JsonArray에 추가
			        monthYears.add(monthYear);
			        bookCounts.add(bookCount);
			    }

			    // JsonObject에 JsonArray 추가
			    bookData.add("monthYears", monthYears);
			    bookData.add("bookCounts", bookCounts);
			    
			    Gson gson = new Gson();
			    String jsonmakeBookData = gson.toJson(bookData);
				String makeBookScript = "<script>var chartData = " + jsonmakeBookData + ";</script>";
				
				System.out.println("책 만든" +jsonmakeBookData);
				
			
				 req.setAttribute("jsonmakeBookData", jsonmakeBookData);
			     
		 
		 
		// JSON 응답 보내기
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
		//resp.getWriter().write(currentjson);
		 
		 
		 
		 
		
	}

}// End of class
