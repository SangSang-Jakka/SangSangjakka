package com.jakka;


import java.util.ArrayList;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.book.BookDTO;

public class Main {
	
	public static void main(String[] args) {
		

	BookDAO bookDAO = DAOManager.getBookDAO();
	
//	int result = bookDAO.activation("157");
	int result = bookDAO.disable("157");
		
	System.out.println(result);
	
	}//main
	
}//End of class
