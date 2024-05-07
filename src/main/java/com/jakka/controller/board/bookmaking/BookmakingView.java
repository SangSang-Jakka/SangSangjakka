package com.jakka.controller.board.bookmaking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect to login if not authenticated
            return;
        }

        UserDAO userDao = DAOManager.getUserDAO();
        UserDTO userDto = userDao.findById(userId);
        
        BookDAO bookDao = DAOManager.getBookDAO();
        PageDAO pageDao = DAOManager.getPageDAO();

        if (type != null) {
            try {
                BookDTO book = new BookDTO();
                book.setBookTitle("작성중");
                book.setBookInfo("작성중");
                book.setBookCover("/sangsangjakka/resources/img/empty.jpg");
                book.setUserSeq(userDto.getUserSeq());
                book.setRcmAgeSeq("1");

                int bookSeq = bookDao.add(book);
                if (bookSeq == 0) {
                    throw new Exception("Failed to create a new book");
                }

                // Create the first page of the book
                PageDTO page = new PageDTO();
                page.setBookSeq(String.valueOf(bookSeq));
                page.setPageSeq("1"); // Assuming you start page numbering at 1
                page.setPageUrl("/sangsangjakka/resources/img/empty.jpg");
                page.setPageContents("내용");
                page.setCmntYN(type.equals("recommendedBook") ? "y" : "n");
                page.setImgYN(type.equals("recommendedBook") ? "y" : "n");

                pageDao.add(page);

                // Redirect to the book view page with the new bookSeq
                response.sendRedirect(request.getContextPath() + "/board/bookmaking/view.do?bookSeq=" + bookSeq);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/error"); // Redirect to an error page on failure
            }
        } else {
            // If no type is specified, simply forward to the view page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_view.jsp");
            dispatcher.forward(request, response);
        }
    }
}
