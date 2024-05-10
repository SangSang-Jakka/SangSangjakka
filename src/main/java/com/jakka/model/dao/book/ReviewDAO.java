package com.jakka.model.dao.book;

import java.util.ArrayList;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Comments;
import com.jakka.model.dao.LikeCnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.book.ReviewDTO;

public interface ReviewDAO extends BasicDAO<ReviewDTO>, ReportCnt, ActiveStatus<ReviewDTO>
								, Comments<ReviewDTO>, LikeCnt{
	
	
	public ArrayList<ReviewDTO> findChildWhite(String parentSeq);
	
	public ArrayList<ReviewDTO> findAllReport();
	public ArrayList<ReviewDTO> findAllNoReport();

	public ArrayList<ReviewDTO> findChildWhite(String bookSeq, int startIndex, int endIndex);

	

}
