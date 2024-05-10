package com.jakka.controller.board.bookmaking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.GenreDAO;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.GenreDTO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/board/bookmaking/fin.do")
public class BookmakingFin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
	    String userId = (String) session.getAttribute("userId");
	    String bookSeq = req.getParameter("no");

	    UserDAO userDao = DAOManager.getUserDAO();
	    UserDTO userDto = userDao.findById(userId);
	    
	    if (userDto != null) {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_fin.jsp");
		dispatcher.forward(req, resp);
		
	    } else {
	    	resp.sendRedirect("/sangsangjakka/user/login.do");
	    }

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
	    String userId = (String) session.getAttribute("userId");
	    
	    if (userId == null) {
	    		resp.sendRedirect("/sangsangjakka/user/login.do"); // Redirect to login if not authenticated
            return;
	    }
	    
	    String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
	    
	    try {
	    	//책 완성 > 책 정보 업데이트, 장르인포 업데이트, 화이트리스트 업데이트, 동화게시판 업데이트
	    	// Parse the request body to JSON
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(requestBody);

            // Extract data from JSON
            String bookSeq = String.valueOf(json.get("bookSeq"));
            String title = String.valueOf(json.get("title"));
            String bookInfo = String.valueOf(json.get("bookInfo"));
            System.out.println("Received title: " + title);

            // If expecting an array
            JSONArray categories = (JSONArray) json.get("categories");
            GenreDAO genreDao = new GenreDAO();
            ArrayList<GenreDTO> genre = new ArrayList<GenreDTO>();
            if (categories != null) {
                for (Object item : categories) {
                	String name = (String)item;
                	name = name.replace("\\", "");
                	GenreDTO dto = genreDao.findByName(String.format("%s 동화", name));
                	genre.add(dto);
                }
            }
            
            // Save the book
            BookDAO dao = DAOManager.getBookDAO();
            BookDTO dto = dao.findById(bookSeq);
            dto.setBookTitle(title);
            dto.setBookInfo(bookInfo);
            dao.save(dto);
            dao.complete(dto);
            genreDao.infoAdd(genre, bookSeq);

            // Respond back
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            resp.getWriter().write("{\"status\":\"success\"}");

		} catch (Exception e) {
			System.out.println("BookmakingFin.doPost");
			e.printStackTrace();
		}
		
	}

}