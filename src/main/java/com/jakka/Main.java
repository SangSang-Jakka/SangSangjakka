package com.jakka;


import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.board.SuggestionDTO;

public class Main {
	
	public static void main(String[] args) {
		

		BoardDAO dao = new BoardDAO();
		
		BoardDTO dto = dao.get("44");
		
		System.out.println(dto);
		
	}//main
	
}//End of class
