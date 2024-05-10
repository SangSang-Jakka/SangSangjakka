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
	
	private String bookCnt;
	
	private String awardRegdate;
	private String awardRank;
	
	
	public String getAwardRegdate() {
		return awardRegdate;
	}

	public void setAwardRegdate(String awardRegdate) {
		this.awardRegdate = awardRegdate;
	}

	public String getAwardRank() {
		return awardRank;
	}

	public void setAwardRank(String awardRank) {
		this.awardRank = awardRank;
	}

	public String getBookCnt() {
		return bookCnt;
	}

	public void setBookCnt(String bookCnt) {
		this.bookCnt = bookCnt;
	}

	private HashMap<Integer, PageDTO> pages;

	public String getBookSeq() {
		return bookSeq;
	}

	public void setBookSeq(String bookSeq) {
		this.bookSeq = bookSeq;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(String bookInfo) {
		this.bookInfo = bookInfo;
	}

	public String getBookCover() {
		return bookCover;
	}

	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}

	public String getBookRegdate() {
		return bookRegdate;
	}

	public void setBookRegdate(String bookRegdate) {
		this.bookRegdate = bookRegdate;
	}

	public String getBookModDate() {
		return bookModDate;
	}

	public void setBookModDate(String bookModDate) {
		this.bookModDate = bookModDate;
	}

	public String getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(String likeCnt) {
		this.likeCnt = likeCnt;
	}

	public String getBookReviewCnt() {
		return bookReviewCnt;
	}

	public void setBookReviewCnt(String bookReviewCnt) {
		this.bookReviewCnt = bookReviewCnt;
	}

	public String getBookScrapCnt() {
		return bookScrapCnt;
	}

	public void setBookScrapCnt(String bookScrapCnt) {
		this.bookScrapCnt = bookScrapCnt;
	}

	public String getBookReportCnt() {
		return bookReportCnt;
	}

	public void setBookReportCnt(String bookReportCnt) {
		this.bookReportCnt = bookReportCnt;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getParentBookSeq() {
		return parentBookSeq;
	}

	public void setParentBookSeq(String parentBookSeq) {
		this.parentBookSeq = parentBookSeq;
	}

	public String getRcmAgeSeq() {
		return rcmAgeSeq;
	}

	public void setRcmAgeSeq(String rcmAgeSeq) {
		this.rcmAgeSeq = rcmAgeSeq;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public HashMap<Integer, PageDTO> getPages() {
		return pages;
	}

	public void setPages(HashMap<Integer, PageDTO> pages) {
		this.pages = pages;
	}
	
	
	
	
}//End of class
