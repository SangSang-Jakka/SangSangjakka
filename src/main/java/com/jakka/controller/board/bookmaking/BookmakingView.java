package com.jakka.controller.board.bookmaking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.DBUtil;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/board/bookmaking/view.do")
public class BookmakingView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String userId = (String) req.getSession().getAttribute("userId");
		String bookSeq = req.getParameter("no");
		System.out.println(bookSeq);

		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/user/login.do"); // Redirect to login if not authenticated
			return;
		}

		UserDAO userDao = DAOManager.getUserDAO();
		UserDTO userDto = userDao.findById(userId);

		BookDAO bookDao = DAOManager.getBookDAO();
		PageDAO pageDao = DAOManager.getPageDAO();

		if (bookSeq != null) {
			HashMap<Integer, PageDTO> pages = pageDao.findPages(bookSeq);
			PageDTO lastpage = pageDao.lastpage(bookSeq);
			ArrayList<PageDTO> dto = new ArrayList<>();
			for (int i = 0; i <= Integer.parseInt(lastpage.getPageSeq()); i++) {
				if (pages.get(i) != null) {
					dto.add(pages.get(i));
				}
			}
			req.setAttribute("firstpage", dto.get(0));
			req.setAttribute("lastpage", lastpage);
			req.setAttribute("dto", dto);
			req.setAttribute("bookSeq", bookSeq);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_view.jsp");
			dispatcher.forward(req, resp);

		} else {
			HashMap<Integer, PageDTO> pages = pageDao.findPages("0");
			PageDTO lastpage = pageDao.lastpage(bookSeq);
			req.setAttribute("lastpage", lastpage);
			req.setAttribute("bookSeq", 0);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_view.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
