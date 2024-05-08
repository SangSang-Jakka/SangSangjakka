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
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String num1 = req.getParameter("month1");
		String num2 = req.getParameter("month2");
			
			
		String formattedNum1 = num1.substring(2).replace("-", "/");
	    String formattedNum2 = num2.substring(2).replace("-", "/");
	    
	    
	    // 변경된 값 확인
	    System.out.println(formattedNum1); // 출력: 21/06
	    System.out.println(formattedNum2); // 출력: 21/06
	    
//	    UserDAO dao = DAOManager.getUserDAO();
//	    Map<String,Integer> newcnt  = dao.newCnt(formattedNum1,formattedNum2);
//		
//	    int date1 = newcnt.get("user_count_23_06");
//	    int date2 = newcnt.get("user_count_23_07");
//	    
//	    System.out.println(date1);
//	    System.out.println(date2);
//	    
//	    JsonObject UserDate = new JsonObject();
//	    JsonArray labels = new JsonArray();
//	    labels.add("가입자수");
//	    labels.add("탈퇴자수");
//	    UserDate.add("labels", labels);
//
//	    JsonArray counts = new JsonArray();
//	    counts.add(date1);
//	    counts.add(date2);
//
//	    UserDate.add("data", counts);
//	    
//	    JsonArray month = new JsonArray();
//	    month.add(formattedNum1);
//	    month.add(formattedNum2);
//
//	    UserDate.add("month", month);
//	 
//	    System.out.println(counts);
//	    System.out.println(labels);
//	    System.out.println(month);
//	    
//	    Gson gson = new Gson();
//	    String DateBar = gson.toJson(UserDate);
//	    System.out.println(DateBar);
//	    //req.setAttribute("DateBar", DateBar);
//	    
//	    resp.setContentType("application/json");
//	    resp.setCharacterEncoding("UTF-8");
//	    resp.getWriter().write(DateBar);
	    
	    
	    UserDAO dao = DAOManager.getUserDAO();
	    Map<String, Map<String, Integer>> countsMap = dao.newCnt(formattedNum1, formattedNum2);

	    JsonObject userData = new JsonObject();

	    JsonArray labels = new JsonArray();
	    labels.add("가입자수");
	    labels.add("탈퇴자수");
	    userData.add("labels", labels);

	    JsonArray data = new JsonArray();
	    JsonArray months = new JsonArray();

	    for (Map.Entry<String, Map<String, Integer>> entry : countsMap.entrySet()) {
	       String month = entry.getKey();
	       Map<String, Integer> countMap = entry.getValue();
	       int userCount = countMap.get("user_count");
	       int userLeft = countMap.get("user_left");
	       
	       months.add(month);
	       
	       JsonArray monthData = new JsonArray();
	       monthData.add(userCount);
	       monthData.add(userLeft);
	       data.add(monthData);
	    }

	    userData.add("data", data);
	    userData.add("months", months);

	    Gson gson = new Gson();
	    String jsonData = gson.toJson(userData);

	    System.out.println(jsonData);

	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.getWriter().write(jsonData);
	    
	}
	

	
	
	
}//End of class
