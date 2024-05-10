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
	
	private String userNick;

	
	public String getReviewSeq() {
		return reviewSeq;
	}

	public void setReviewSeq(String reviewSeq) {
		this.reviewSeq = reviewSeq;
	}

	public String getReviewContents() {
		return reviewContents;
	}

	public void setReviewContents(String reviewContents) {
		this.reviewContents = reviewContents;
	}

	public String getReviewLikeCnt() {
		return reviewLikeCnt;
	}

	public void setReviewLikeCnt(String reviewLikeCnt) {
		this.reviewLikeCnt = reviewLikeCnt;
	}

	public String getReviewReportCnt() {
		return reviewReportCnt;
	}

	public void setReviewReportCnt(String reviewReportCnt) {
		this.reviewReportCnt = reviewReportCnt;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getBookSeq() {
		return bookSeq;
	}

	public void setBookSeq(String bookSeq) {
		this.bookSeq = bookSeq;
	}

	public String getReviewRegdate() {
		return reviewRegdate;
	}

	public void setReviewRegdate(String reviewRegdate) {
		this.reviewRegdate = reviewRegdate;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	
	
}//End of class
