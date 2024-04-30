package com.jakka.model.dto.book;

import java.util.HashMap;

import lombok.Data;

@Data
public class BookDTO {

	private String bookSeq;
	private String bookTitle;
	private String bookInfo;
	private String bookCover;
	private String bookRegdate;
	private String bookModDate;
	private String likeCnt;
	private String bookReviewCnt;
	private String bookScrapCnt;
	private String bookReportCnt;
	private String userSeq;
	private String parentBookSeq;
	private String rcmAgeSeq;
	private String userNick;
	
	private HashMap<Integer, PageDTO> pages;
	
}//End of class
