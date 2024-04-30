package com.jakka;


import java.util.ArrayList;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardCommentsDAOImpl;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.BoardDAOImpl;
import com.jakka.model.dao.board.NoticeDAOImpl;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.enums.Genre;

public class Main {
	
	public static void main(String[] args) {
		

	BoardDAO boardDAO = DAOManager.getBoardDAO();

	ArrayList<BoardDTO> list = boardDAO.findByContentsContains("여행");
	
	System.out.println(list);
	
	}//main
	
}//End of class
