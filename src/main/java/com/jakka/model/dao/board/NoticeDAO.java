package com.jakka.model.dao.board;

import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.NoticeDTO;

public interface NoticeDAO extends BasicDAO<NoticeDTO>, Cnt, Search<NoticeDTO>{
	
	public int fixed(String noticeSeq, String adId);
	public int Unfixing(String noticeSeq, String adId);
	public int remove(String noticeSeq, String adId);
	public ArrayList<NoticeDTO> findAll(HashMap<String, String> map);
	public ArrayList<NoticeDTO> findAllFix();
	public int totalCnt(HashMap<String, String> map);
	
}
