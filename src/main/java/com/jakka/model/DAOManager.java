package com.jakka.model;

import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dao.user.UserDAO;

public class DAOManager {

	public DAOManager() {
		
	}
	
	public static AdminDAO getAdminDAO() {
		
		return AdminDAO.getInstance();
		
	}//getBoardDAO()
	
	public static BoardDAO getBoardDAO() {
		
		return BoardDAO.getInstance();
		
	}//getBoardDAO()
	
	public static NoticeDAO getNoticeDAO() {
		
		return NoticeDAO.getInstance();
		
	}//getBoardDAO()
	
	public static SuggestionDAO getSuggestionDAO() {
		
		return SuggestionDAO.getInstance();
		
	}//getBoardDAO()
	
	public static BoardCommentsDAO getBoardCommentDAO() {
		
		return BoardCommentsDAO.getInstance();
		
	}//BoardCommentDAO()
	
	public static SuggestionAnswerDAO getSuggestionCommentDAO() {
		
		return SuggestionAnswerDAO.getInstance();
		
	}//SuggestionCommentDAO()
	
	public static UserDAO getUserDAO() {
		
		return UserDAO.getInstance();
		
	}//getUserDAO()
	
	
}//End of class
