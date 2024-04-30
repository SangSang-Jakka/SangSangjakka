package com.jakka.model;

import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dao.admin.AdminDAOImpl;
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dao.board.BoardCommentsDAOImpl;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.BoardDAOImpl;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dao.board.NoticeDAOImpl;
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dao.board.SuggestionAnswerDAOImpl;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dao.board.SuggestionDAOImpl;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dao.user.UserDAOImpl;

public class DAOManager {

	
	public static AdminDAO getAdminDAO() {
		
		return AdminDAOImpl.getInstance();
		
	}//getBoardDAO()
	
	public static BoardDAO getBoardDAO() {
		
		return BoardDAOImpl.getInstance();
		
	}//getBoardDAO()
	
	public static NoticeDAO getNoticeDAO() {
		
		return NoticeDAOImpl.getInstance();
		
	}//getBoardDAO()
	
	public static SuggestionDAO getSuggestionDAO() {
		
		return SuggestionDAOImpl.getInstance();
		
	}//getBoardDAO()
	
	public static BoardCommentsDAO getBoardCommentDAO() {
		
		return BoardCommentsDAOImpl.getInstance();
		
	}//BoardCommentDAO()
	
	public static SuggestionAnswerDAO getSuggestionCommentDAO() {
		
		return SuggestionAnswerDAOImpl.getInstance();
		
	}//SuggestionCommentDAO()
	
	public static UserDAO getUserDAO() {
		
		return UserDAOImpl.getInstance();
		
	}//getUserDAO()
	
	public static BookDAO getBookDAO() {
		
		return BookDAO.getInstace();
		
	}//getBookDAO()
	
	public static PageDAO getPageDAO() {
		
		return PageDAO.getInstance();
		
	}//getPageDAO()
	
	public static ReviewDAO getReviewDAO() {
		
		return ReviewDAO.getInstance();
		
	}//getReviewDAO()
	
	
}//End of class
