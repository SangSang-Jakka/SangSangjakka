package com.jakka;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dto.board.NoticeDTO;
import com.jakka.model.dto.board.SuggestionDTO;
import com.jakka.model.enums.UserState;

public class Test {
	
	public static void main(String[] args) {
	
		ReviewDAO dao = DAOManager.getReviewDAO();
		
		
		int result = dao.addLikeCnt("51", "1");
		
		System.out.println(result);
	}
	
	
}

