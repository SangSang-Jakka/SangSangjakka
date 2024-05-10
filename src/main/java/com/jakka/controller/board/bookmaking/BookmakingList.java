package com.jakka.controller.board.bookmaking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
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

@WebServlet("/board/bookmaking/list.do")
public class BookmakingList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        UserDAO userDao = DAOManager.getUserDAO();
        UserDTO userDto = userDao.findById(userId);
        
        if (userDto != null) {
            BookDAO bookDao = DAOManager.getBookDAO();
            PageDAO pageDao = DAOManager.getPageDAO();  // Corrected here: Declaring the pageDao instance
            ArrayList<BookDTO> bookDtoList = bookDao.findByNick(userDto.getUserNick());
            ArrayList<PageDTO> pageDtoList = new ArrayList<>();
            Iterator<BookDTO> iterator = bookDtoList.iterator();
            while (iterator.hasNext()) {
                BookDTO dto = iterator.next();
                if ("작성중".equals(dto.getBookTitle()) || "작성중".equals(dto.getBookInfo())) {
                    pageDtoList.add(pageDao.findById("1", dto.getBookSeq()));  // Corrected here: Using pageDao instance
                } else {
                    iterator.remove();
                }
            }

            req.setAttribute("userDto", userDto);
            req.setAttribute("bookDtoList", bookDtoList);
            req.setAttribute("pageDtoList", pageDtoList);
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_list.jsp");
            dispatcher.forward(req, resp);
        } else {
        	resp.sendRedirect("/sangsangjakka/user/login.do");
        }

    }
}
