package com.jakka.controller.dashboard;

import java.io.IOException;
import java.util.List;

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
		 
		 System.out.println("가져옴?" + selectedMonth);
		
		 
		 AdminDAO dao = DAOManager.getAdminDAO();
		 List<AdminDTO>inflowCount = dao.getInflowCountData(selectedMonth);
		// List<AdminDTO>currentMonths = dao.getInflowCountData(currentMonth);
		 
		 System.out.println(inflowCount);
//		 JsonArray jsonArray = new JsonArray();
//	       
//		 for (AdminDTO dto : inflowCount) {
//	            JsonObject jsonObject = new JsonObject();
//	            jsonObject.addProperty("registrationMonth", dto.getRegistrationMonth());
//	            jsonObject.addProperty("inflowname", dto.getInflowname());
//	            jsonObject.addProperty("inflowCount", dto.getInflowCount());
//	            jsonArray.add(jsonObject);
//	        }
//
//		 
//		 System.out.println(jsonArray);
//		 resp.setContentType("application/json");
//		 resp.getWriter().write(jsonArray.toString());
		 String json = new Gson().toJson(inflowCount);
		 //String currentjson = new Gson().toJson(currentMonths);
		 //System.out.println(currentjson);
//
		// JSON 응답 보내기
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
		//resp.getWriter().write(currentjson);
		 
		 
		 
		 
		
	}

}// End of class
