package com.jakka.model.dto.board;

import lombok.Data;

@Data
public class NoticeDTO {

	private String noticeSeq;
	private String noticeTitle;
	private String noticeContents;
	private String noticeRegdate;
	private String noticeCnt;
	private String adId;
	public String getNoticeSeq() {
		return noticeSeq;
	}
	public void setNoticeSeq(String noticeSeq) {
		this.noticeSeq = noticeSeq;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContents() {
		return noticeContents;
	}
	public void setNoticeContents(String noticeContents) {
		this.noticeContents = noticeContents;
	}
	public String getNoticeRegdate() {
		return noticeRegdate;
	}
	public void setNoticeRegdate(String noticeRegdate) {
		this.noticeRegdate = noticeRegdate;
	}
	public String getNoticeCnt() {
		return noticeCnt;
	}
	public void setNoticeCnt(String noticeCnt) {
		this.noticeCnt = noticeCnt;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	
	
}//End of class
