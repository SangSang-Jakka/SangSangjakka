package com.jakka;

import java.util.ArrayList;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.user.UserDTO;

public class Main {
	
	public static void main(String[] args) {
		

	UserDAO userDAO = DAOManager.getUserDAO();
	
//	int result = userDAO.disable("1");
	int result = userDAO.activation("1");
	
	System.out.println(result);
	
	
		
	}//main
	
}//End of class
