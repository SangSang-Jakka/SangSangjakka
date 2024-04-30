package com.jakka.model.dto.book;

import lombok.Data;

@Data
public class ReviewDTO {

	private String reviewSeq;
	private String reviewContents;
	private String reviewLikeCnt;
	private String reviewReportCnt;
	private String userSeq;
	private String bookSeq;
	private String reviewRegdate;
	
}//End of class
