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
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
		 String formattedDate = currentDate.format(formatter);
		
		UserDAO dao = DAOManager.getUserDAO();
		//int userCnt = dao.userCnt(formattedDate);
		int userCnt = dao.userCnt("formattedDate");
		System.out.println(userCnt);
		Map<String,Integer> userCounts  = dao.userGender();
		System.out.println(123);
		System.out.println(userCounts);
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

	
		
		
	        
	     
			// 사용자 나이 보여주는 차트
	    
		
	       
			
			Map<String,Integer> child  = dao.childAge();
			System.out.println(child);
			
			JsonObject ageData = new JsonObject();
			JsonArray ageRanges = new JsonArray();
			JsonArray ageCounts = new JsonArray();

			for (Map.Entry<String, Integer> entry : child.entrySet()) {
			    String ageRange = entry.getKey();
			    int ageCount = entry.getValue();
			    ageRanges.add(ageRange);
			    ageCounts.add(ageCount);
			}

			ageData.add("labels", ageRanges);
			ageData.add("data", ageCounts);
			
			
			
			// 사용자 연령대 보여주는 차트
			Map<String,Integer> userAge  = dao.userAge();
			System.out.println(child);
			
			JsonObject userAgeData = new JsonObject();
			JsonArray userAgeRanges = new JsonArray();
			JsonArray userAgeCounts = new JsonArray();

			for (Map.Entry<String, Integer> entry : userAge.entrySet()) {
			    String userAgeRange = entry.getKey();
			    int userAgeCount = entry.getValue();
			    userAgeRanges.add(userAgeRange);
			    userAgeCounts.add(userAgeCount);
			}

			userAgeData.add("labels", userAgeRanges);
			userAgeData.add("data", userAgeCounts);
			
			

			// Gson을 사용하여 JSON 형식의 문자열로 변환
			Gson gson = new Gson();
			String jsonData = gson.toJson(chartData);
			String script = "<script>var chartData = " + jsonData + ";</script>";
			
			
			String jsonAgeData = gson.toJson(ageData);
			String ageScript = "<script>var chartData = " + jsonAgeData + ";</script>";
			

			String jsonUserAgeData = gson.toJson(userAgeData);
			String userAgeScript = "<script>var chartData = " + jsonUserAgeData + ";</script>";
			
			System.out.println(jsonUserAgeData);
			
			 req.setAttribute("jsonData", jsonData);
			 req.setAttribute("jsonAgeData", jsonAgeData);
			 req.setAttribute("jsonUserAgeData", jsonUserAgeData);
		     req.setAttribute("userCnt", userCnt); // 신규 가입
	     
	     
	     
	     
	     
	     
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_user/user_stats.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String num1 = req.getParameter("month1");
//		String num2 = req.getParameter("month2");
//			
//			
//		String formattedNum1 = num1.substring(2).replace("-", "/");
//	    String formattedNum2 = num2.substring(2).replace("-", "/");
//	    
//	    
//	    // 변경된 값 확인
//	    System.out.println(formattedNum1); // 출력: 21/06
//	    System.out.println(formattedNum2); // 출력: 21/06
//	    
//	    System.out.println("post");
	    String year = req.getParameter("selectedValue");
	    
	    
	   
	    if (year != null) {
	    	 UserDAO dao = DAOManager.getUserDAO();
	 	    Map<String, Integer[]> memberStats  = dao.member(year);

            JsonArray jsonArray = new JsonArray();
            for (Map.Entry<String, Integer[]> entry : memberStats.entrySet()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("label", entry.getKey());
                Integer[] values = entry.getValue();
                jsonObject.addProperty("joinCount", values[0]);
                jsonObject.addProperty("withdrawCount", values[1]);
                jsonArray.add(jsonObject);
            }

            Gson gson = new Gson();
            String jsonData = gson.toJson(jsonArray);
            System.out.println(jsonData);

            resp.setContentType("application/json");
            resp.getWriter().write(jsonData);
        } else {
        	resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	resp.getWriter().write("Invalid request parameters");
        }
    
	    
	    
	    
	    
	    
//
        
//	    
//	    
//	    
//	    UserDAO dao = DAOManager.getUserDAO();
//	    Map<String, Map<String, Integer>> countsMap = dao.newCnt(formattedNum1, formattedNum2);
//
//	    JsonObject userData = new JsonObject();
//
//	    JsonArray labels = new JsonArray();
//	    labels.add("가입자수");
//	    labels.add("탈퇴자수");
//	    userData.add("labels", labels);
//
//	    JsonArray data = new JsonArray();
//	    JsonArray months = new JsonArray();
//
//	    for (Map.Entry<String, Map<String, Integer>> entry : countsMap.entrySet()) {
//	       String month = entry.getKey();
//	       Map<String, Integer> countMap = entry.getValue();
//	       int userCount = countMap.get("user_count");
//	       int userLeft = countMap.get("user_left");
//	       
//	       months.add(month);
//	       
//	       JsonArray monthData = new JsonArray();
//	       monthData.add(userCount);
//	       monthData.add(userLeft);
//	       data.add(monthData);
//	    }
//
//	    userData.add("data", data);
//	    userData.add("months", months);
//
//	    Gson gson = new Gson();
//	    String jsonData = gson.toJson(userData);
//
//	    System.out.println(jsonData);
//	    
//	    
//	    
//	    
//
//	    resp.setContentType("application/json");
//	    resp.setCharacterEncoding("UTF-8");
//	    resp.getWriter().write(jsonData);
		
		
	    
	    
	    
	 
	}
	
	
	
	

	
	
	
}//End of class
