package com.jakka.controller.board.bookmaking;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/board/bookmaking/view.do")
public class BookmakingView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
	    String userId = (String) session.getAttribute("userId");
	    String bookId = null;

	    UserDAO userDao = DAOManager.getUserDAO();
	    UserDTO userDto = userDao.findById(userId);
	    
	    BookDAO bookDao = DAOManager.getBookDAO();
	    BookDTO bookDto = bookDao.findById(bookId);

	    req.setAttribute("userDto", userDto);
	    req.setAttribute("bookDto", bookDto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_view.jsp");
		dispatcher.forward(req, resp);

	}

}