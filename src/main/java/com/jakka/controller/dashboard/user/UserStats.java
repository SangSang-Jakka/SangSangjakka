package com.jakka.controller.dashboard.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dao.user.UserDAO;

@WebServlet("/admin/dashboard/user/stats.do")
public class UserStats extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 LocalDate currentDate = LocalDate.now();
		    // currentDate를 필요한 형식으로 포맷팅할 수 있습니다.
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
		    // 포맷을 적용하여 문자열로 변환합니다.
		 String formattedDate = currentDate.format(formatter);
		
		UserDAO dao = DAOManager.getUserDAO();
		//int userCnt = dao.userCnt(formattedDate);
		int userCnt = dao.userCnt("23/03/11");
		
		Map<String,Integer> userCounts  = dao.userGender();
		int manCount = userCounts.get("man");
		int womanCount = userCounts.get("woman");
		
		JsonObject chartData = new JsonObject();
		JsonArray labels = new JsonArray();
		labels.add("남자");
		labels.add("여자");
		chartData.add("labels", labels);

		JsonArray counts = new JsonArray();
		counts.add(manCount);
		counts.add(womanCount);
		chartData.add("data", counts);

		// Gson을 사용하여 JSON 형식의 문자열로 변환
		Gson gson = new Gson();
		String jsonData = gson.toJson(chartData);
		String script = "<script>var chartData = " + jsonData + ";</script>";
		
		
		 req.setAttribute("jsonData", jsonData);
	     req.setAttribute("userCnt", userCnt); // 신규 가입
	        
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_user/user_stats.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	
	
	
}//End of class
