package com.jakka.model.dao.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.LikeCnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dao.ScrapCnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;

public interface BookDAO extends BasicDAO<BookDTO>, ReportCnt, Cnt, ActiveStatus<BookDTO>
								, Search<BookDTO>, LikeCnt , ScrapCnt{
	
	public int addScrap(String bookSeq, String userSeq);
	
	public ArrayList<BookDTO> findAllLike(String userSeq);
	public ArrayList<BookDTO> findAllScrap(String userSeq);
	
	public ArrayList<BookDTO> findByNick(HashMap<String, String> map);
	public ArrayList<BookDTO> findByTitleContains(HashMap<String, String> map);
	public ArrayList<BookDTO> findByContentsContains(HashMap<String, String> map);
	public ArrayList<BookDTO> findAllWhite(HashMap<String, String> map);
	public int whiteTotalCnt(HashMap<String, String> map);
	public void createBookFolder(String userId, String bookSeq, ServletContext context);
	public int complete(BookDTO dto);
	
	public int addCnt(String bookSeq, String userSeq);
	
	public ArrayList<BookDTO> findAllAward();
	public int presentAward(ArrayList<BookDTO> list, String adId);

	public ArrayList<BookDTO> findAllReport();
	public ArrayList<BookDTO> findAllNoReport();
	public ArrayList<BookDTO> findNowAward();
	public ArrayList<BookDTO> findMonthAward(String month);
	
	public List<BookDTO>getShareCount(String month);
	
	public ArrayList<BookDTO> findByReport(String bookSeq);
	
	public int delAward(String bookSeq);
	
	public int getBookCount();
	public int getTodayBookCount(String today);
	public int getPageCount();
	public  Map<String, Integer> makeBook(String month);

	public ArrayList<BookDTO> findByUserSeq(String userSeq);

	
}//End of interface
