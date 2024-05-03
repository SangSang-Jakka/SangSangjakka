package com.jakka;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.NoticeDTO;
import com.jakka.model.dto.board.SuggestionDTO;
import com.jakka.model.enums.UserState;

public class Test {
	
	public static void main(String[] args) {
	
		SuggestionDAO dao = DAOManager.getSuggestionDAO();
		
		SuggestionDTO dto = new SuggestionDTO();
		
		dto.setSgstSecretYN(UserState.ACTIVE.getValue());
		dto.setUserSeq("1");
		dto.setSgstTitle("소신발언합니다.");
		dto.setSgstContents("소신발언");
		
		int result = dao.add(dto);
		
		System.out.println(result);
	}
	
	
}

