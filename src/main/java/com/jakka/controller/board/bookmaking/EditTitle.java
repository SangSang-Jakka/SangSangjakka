package com.jakka.controller.board.bookmaking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dto.book.BookDTO;

@WebServlet("/board/bookmaking/edittitle.do")
public class EditTitle extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 결과
		resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        
		HttpSession session = req.getSession();
		String userId = (String) req.getSession().getAttribute("userId");
		
		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/user/login.do");
			return;
		}
		
		String bookSeq = req.getParameter("bookSeq");
		String title = req.getParameter("title");
		
		BookDAO dao = DAOManager.getBookDAO();
		BookDTO dto = dao.findById(bookSeq);
		
		dto.setBookSeq(bookSeq);
		dto.setBookTitle(title);
		
		dao.save(dto);
		
    	resp.setCharacterEncoding("UTF-8");
    	writer.print(dto);
    	writer.close();
	}

}