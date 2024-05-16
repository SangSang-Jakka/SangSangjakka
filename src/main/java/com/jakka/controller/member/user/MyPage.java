package com.jakka.controller.member.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/user/mypage.do")
public class MyPage extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	    HttpSession session = req.getSession();
	    String userId = (String) session.getAttribute("userId");
	    String userNick = (String) session.getAttribute("userNick");
	    String userSeq = (String) session.getAttribute("userSeq");
	    
	    if (userId == null) {
			
			resp.sendRedirect("/sangsangjakka/user/login.do");
			return;
			
		}
	    
	    
	    try {
	    	
	    UserDAO dao = DAOManager.getUserDAO();
	    BoardDAO boardDAO = DAOManager.getBoardDAO();
	    BoardCommentsDAO commentsDAO = DAOManager.getBoardCommentDAO();
	    BookDAO bookDAO = DAOManager.getBookDAO();
	    
	    UserDTO dto = dao.findById(userId);
	    
	    ArrayList<BoardDTO> list = boardDAO.findByNickBoard(userNick);
        
	    HashMap<String, Double> tendency = dao.findTendencyScore(userSeq);
	    
	    ArrayList<BoardCommentDTO> comments = commentsDAO.findByNick(userNick);
	    
	    ArrayList<BookDTO> allBookList = bookDAO.findAllWhite();
	    Iterator<BookDTO> iterator = allBookList.iterator();
	    ArrayList<BookDTO> bookList = new ArrayList<>();
        while (iterator.hasNext()) {
            BookDTO bookDto = iterator.next();
            if (("작성중".equals(bookDto.getBookTitle()) && "작성중".equals(bookDto.getBookInfo())) || !bookDto.getUserSeq().equals(userSeq)) {
            	iterator.remove();
            } else {
            	bookList.add(bookDto);
            }
        }
	    
	    req.setAttribute("dto", dto);
	    req.setAttribute("list", list);
	    req.setAttribute("tendency", tendency);
	    req.setAttribute("comments", comments);
	    req.setAttribute("bookList", bookList);
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/mypage.jsp");
	    dispatcher.forward(req, resp);
	    
	    }catch (Exception e) {
	        // 예외 처리
	        e.printStackTrace();
	        resp.sendRedirect("/sangsangjakka/error.jsp");
	    }
	}
	
	
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		    req.setCharacterEncoding("UTF-8");
		    
		  
		    HttpSession session = req.getSession();
		    String userId = (String) session.getAttribute("userId");

		    // 사용자가 입력한 정보 가져오기
		    String nickName = req.getParameter("nickName");
		    String phoneStart = req.getParameter("phoneStart");
		    String phoneInput1 = req.getParameter("phoneInput1");
		    String phoneInput2 = req.getParameter("phoneInput2");
		    String address = req.getParameter("address");
		    String email = req.getParameter("email");
		    
		    UserDTO dto = new UserDTO();
		    
		    dto.setUserId(userId);
		    dto.setUserNick(nickName);
		    dto.setUserTel(phoneStart + "-" + phoneInput1 + "-" + phoneInput2);
		    dto.setUserAddress(address);
		    dto.setUserEmail(email);
		    
		    UserDAO dao = DAOManager.getUserDAO();
		    int count = dao.save(dto);
		  
		
	        if (count > 0) {
	        	// 업데이트 성공
	        	// 마이페이지로 이동
	        	resp.sendRedirect(req.getContextPath() + "/user/mypage.do");
	        } else {
	        	// 업데이트 실패
	        	resp.sendRedirect(req.getContextPath() + "/error.jsp");
	        }
	        }
		    





	
}//End of class
