package com.jakka.model.dao.board;

import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.BoardDTO;

public interface BoardDAO extends BasicDAO<BoardDTO>, Cnt, ReportCnt
								, ActiveStatus<BoardDTO>, Search<BoardDTO>{
	
	public int whiteTotalCnt(HashMap<String, String> map);
	public ArrayList<BoardDTO> findAllWhite(HashMap<String, String> map);
	
	public int remove(String boardSeq);
	public int findSeq(String userSeq);
	public BoardDTO findInfo(String freeSeq);
	public int saveEdit(BoardDTO dto);
	public int freeDel(String boardSeq);
	public ArrayList<BoardDTO> findAllWhite(HashMap<String, String> map, String orderBy);
	public ArrayList<BoardDTO> findAllReport();
	public ArrayList<BoardDTO> findAllNoReport();
	public ArrayList<BoardDTO> findToday(String today);
	public ArrayList<BoardDTO> findByNickBoard(String Nick);
	public int getNewPostCount(String userRegdate);
	public int getNewSuggestionCount(String userRegdate);
	public int boardReportCount(String userRegdate);
	public int CommReportCount(String userRegdate);
	
}
