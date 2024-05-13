package com.jakka.controller.board.bookmaking;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/board/bookmaking/add.do")
public class BookmakingAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/user/login.do"); // Redirect to login if not authenticated
			return;
		}
		
		String type = req.getParameter("type");

		UserDAO userDao = DAOManager.getUserDAO();
		UserDTO userDto = userDao.findById(userId);

		BookDAO bookDao = DAOManager.getBookDAO();
		PageDAO pageDao = DAOManager.getPageDAO();
		
		if (type != null) {
			creatBook(req, resp, type, userDto, bookDao, pageDao);
		} else {
			notFinBook(req, resp, userDto, bookDao, pageDao);
		}
		

	}

	private void creatBook(HttpServletRequest req, HttpServletResponse resp, String type, UserDTO userDto, BookDAO bookDao,
			PageDAO pageDao) {
		try {

			BookDTO book = new BookDTO();
			book.setBookTitle("작성중");
			book.setBookInfo("작성중");
			book.setBookCover("/sangsangjakka/resources/img/empty.jpg");
			book.setUserSeq(userDto.getUserSeq());
			book.setRcmAgeSeq("1");

			String bookSeq = String.valueOf(bookDao.add(book));
			if (bookSeq.equals("0")) {
				throw new Exception("Failed to create a new book");
			}

			// Create the first page of the book
			PageDTO page = new PageDTO();
			page.setBookSeq(bookSeq);
			page.setPageSeq("1"); // Assuming you start page numbering at 1
			page.setPageUrl("/sangsangjakka/resources/img/empty.jpg");
			page.setPageContents("내용");
			page.setCmntYN(type.equals("y") ? "y" : "n");
			page.setImgYN(type.equals("y") ? "y" : "n");

			pageDao.add(page);
			// ServletContext를 통해 리소스 스트림 얻기
	        ServletContext context = getServletContext();
			bookDao.createBookFolder(userDto.getUserId(), bookSeq, context);
			PageDTO lastpage = pageDao.lastpage(bookSeq);
			
			req.setAttribute("lastpage", lastpage.getPageSeq());
			req.setAttribute("bookSeq", bookSeq);
			req.setAttribute("link", req.getContextPath());
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_add.jsp");
			dispatcher.forward(req, resp);

		} catch (Exception e) {
			System.out.println("BookmakingAdd.creatBook");
			e.printStackTrace();
		}

	}

	private void notFinBook(HttpServletRequest req, HttpServletResponse resp, UserDTO userDto, BookDAO bookDao,
			PageDAO pageDao) {
		try {

			ArrayList<BookDTO> bookDtoList = bookDao.findByNick(userDto.getUserNick());
			ArrayList<PageDTO> pageDtoList = new ArrayList<>();
			Iterator<BookDTO> iterator = bookDtoList.iterator();
			while (iterator.hasNext()) {
				BookDTO dto = iterator.next();
				if ("작성중".equals(dto.getBookTitle()) && "작성중".equals(dto.getBookInfo())) {
					pageDtoList.add(pageDao.findById("1", dto.getBookSeq()));
				} else {
					iterator.remove();
				}

			req.setAttribute("userDto", userDto);
			req.setAttribute("bookDtoList", bookDtoList);
			req.setAttribute("pageDtoList", pageDtoList);
			}
			PageDTO lastpage = pageDao.lastpage(null);
			
			req.setAttribute("lastpage", lastpage.getPageSeq());
			req.setAttribute("bookSeq", "0");
			// Redirect to other servlets based on condition
			//String redirectUrl = bookDtoList.isEmpty() ? "view.jsp"	: "list.jsp";
			String redirectUrl = "view.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_"+redirectUrl);
			dispatcher.forward(req, resp);


		} catch (Exception e) {
			System.out.println("BookmakingAdd.notFinBook");
			e.printStackTrace();
		}
	}
}
