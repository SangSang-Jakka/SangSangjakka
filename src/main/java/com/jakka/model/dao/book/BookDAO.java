package com.jakka.model.dao.book;

import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;

public interface BookDAO extends BasicDAO<BookDTO>, ReportCnt, Cnt
							   , ActiveStatus<BookDTO>, Search<BookDTO> {
	
	public int addLike(String bookSeq, String userSeq);
	public int addScrap(String bookSeq, String userSeq);
	
	public ArrayList<BookDTO> findAllLike(String userSeq);
	public ArrayList<BookDTO> findAllScrap(String userSeq);
	
}//End of interface
