package com.jakka.model.dto.book;

import lombok.Data;

@Data
public class PageDTO {

	private String pageSeq;
	private String bookSeq;
	private String pageUrl;
	private String pageContents;
	private String cmntYN;
	private String imgYN;
	public String getPageSeq() {
		return pageSeq;
	}
	public void setPageSeq(String pageSeq) {
		this.pageSeq = pageSeq;
	}
	public String getBookSeq() {
		return bookSeq;
	}
	public void setBookSeq(String bookSeq) {
		this.bookSeq = bookSeq;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getPageContents() {
		return pageContents;
	}
	public void setPageContents(String pageContents) {
		this.pageContents = pageContents;
	}
	public String getCmntYN() {
		return cmntYN;
	}
	public void setCmntYN(String cmntYN) {
		this.cmntYN = cmntYN;
	}
	public String getImgYN() {
		return imgYN;
	}
	public void setImgYN(String imgYN) {
		this.imgYN = imgYN;
	}
	
	
	
	
}//End of class
