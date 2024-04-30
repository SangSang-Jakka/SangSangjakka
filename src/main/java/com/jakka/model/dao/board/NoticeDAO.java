package com.jakka.model.dao.board;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.NoticeDTO;

public interface NoticeDAO extends BasicDAO<NoticeDTO>, Cnt, Search<NoticeDTO>{
	
	public int fixed(String noticeSeq);
	public int Unfixing(String noticeSeq);
	public int remove(String noticeSeq);
	
}
