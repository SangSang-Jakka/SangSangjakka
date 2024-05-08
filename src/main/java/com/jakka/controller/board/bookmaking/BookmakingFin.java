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
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/board/bookmaking/fin.do")
public class BookmakingFin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
	    String userId = (String) session.getAttribute("userId");
	    String bookId = null;

	    UserDAO userDao = DAOManager.getUserDAO();
	    UserDTO userDto = userDao.findById(userId);
	    
	    if (userDto != null) {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_fin.jsp");
		dispatcher.forward(req, resp);
		
	    } else {
	    	resp.sendRedirect("/sangsangjakka/user/login.do");
	    }

	}

}