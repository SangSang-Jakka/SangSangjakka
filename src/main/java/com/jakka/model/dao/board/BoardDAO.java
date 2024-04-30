package com.jakka.model.dao.board;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.BoardDTO;

public interface BoardDAO extends BasicDAO<BoardDTO>, Cnt, ReportCnt
								, ActiveStatus<BoardDTO>, Search<BoardDTO>{
	
}
