package com.jakka.model.dao.board;

import java.util.ArrayList;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Comments;
import com.jakka.model.dto.board.SuggestionAnswerDTO;

public interface SuggestionAnswerDAO extends BasicDAO<SuggestionAnswerDTO>, Comments<SuggestionAnswerDTO>{

	public ArrayList<SuggestionAnswerDTO> list(String parentSgstSeq);

	int del(SuggestionAnswerDTO dto);


	
	

}
