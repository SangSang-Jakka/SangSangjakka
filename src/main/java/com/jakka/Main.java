package com.jakka;


import java.util.ArrayList;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.book.BookDTO;

public class Main {
	
	public static void main(String[] args) {
		


	BookDAO bookDAO = DAOManager.getBookDAO();
	
	ArrayList<BookDTO> list = bookDAO.findAllLike("1");
	
	System.out.println(list);
	
	}//main
	
}//End of class
