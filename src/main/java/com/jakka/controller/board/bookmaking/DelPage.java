package com.jakka.controller.board.bookmaking;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.PageDAO;

@WebServlet("/board/bookmaking/delpage.do")
public class DelPage extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 데이터 가져오기(bookSeq, pageSeq)
		//2. DB 작업 > delete
		//3. 결과
		
		HttpSession session = req.getSession();
		
		String bookSeq = req.getParameter("bookSeq");
		String pageSeq = req.getParameter("pageSeq");
		
		PageDAO dao = new DAOManager().getPageDAO();
		
		int result = dao.del(pageSeq, bookSeq);
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(result);
		writer.close();

	}

}