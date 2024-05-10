package com.jakka.model.dao.board;

import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.SuggestionDTO;

public interface SuggestionDAO extends BasicDAO<SuggestionDTO>, Cnt, Search<SuggestionDTO>{

	ArrayList<SuggestionDTO> findAllWhite(HashMap<String, String> map);
	ArrayList<SuggestionDTO> findAllWhite(HashMap<String, String> map, String orderBy);
	int whiteTotalCnt(HashMap<String, String> map);
	int del(String seq);
	public ArrayList<SuggestionDTO> findAllSecret();
	public ArrayList<SuggestionDTO> findAllOpen();
	public ArrayList<SuggestionDTO> findAllAnswer();
	public ArrayList<SuggestionDTO> findAllNoAnswer();
	
	
}
