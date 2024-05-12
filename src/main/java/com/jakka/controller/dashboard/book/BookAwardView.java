package com.jakka.controller.dashboard.book;

import java.io.BufferedReader;
import java.io.IOException;
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
import org.json.simple.parser.JSONParser;


import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dto.book.BookDTO;

@WebServlet("/admin/dashboard/book/awardview.do")
public class BookAwardView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		BookDAO dao = DAOManager.getBookDAO();

		ArrayList<BookDTO> list = dao.findAllWhite();

		req.setAttribute("bookList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_book/book_award_view.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");

		
		// 받은 JSON 데이터 파싱
		// 받은 JSON 데이터 파싱
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
       
        JSONParser parser = new JSONParser();
        
        try {
        	
        	JSONObject data = (JSONObject) parser.parse(sb.toString());
			
        	 // 선택된 도서와 수상 순위 추출
            JSONArray selectedBooks = (JSONArray) data.get("selectedBooks");
            JSONArray selectedRanks = (JSONArray) data.get("selectedRanks");
        	
            // 여기서부터 수상작 등록 등의 로직을 수행합니다.
            // BookDAOImpl.presentAward 호출 등의 작업을 수행합니다.
            
            ArrayList<BookDTO> awardBooks = new ArrayList<>();
            
            // BookDTO 리스트
            for (int i = 0; i < selectedBooks.size(); i++) {
            	String bookSeq = (String) selectedBooks.get(i);
            	String awardRank = (String) selectedRanks.get(i);
            	
            	BookDTO dto = new BookDTO();
            	dto.setBookSeq(bookSeq);
            	dto.setAwardRank(awardRank);
            	
            	awardBooks.add(dto);
            	
            }
            
            BookDAO bookDAO = DAOManager.getBookDAO();
            int result = bookDAO.presentAward(awardBooks, adId);
            
            
         // 응답 작성
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Success");
            
		} catch (Exception e) {
			System.out.println("BookAwardView.doPost");
			e.printStackTrace();
			
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error");
		}

	    

	}

}// End of class
