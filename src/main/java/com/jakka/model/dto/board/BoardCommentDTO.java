package com.jakka.model.dto.board;

import lombok.Data;

@Data
public class BoardCommentDTO {

	private String cmntSeq;
	private String userSeq;
	private String boardSeq;
	private String cmntContents;
	private String cmntReportCnt;
	private String cmntRegdate;
	
	private String userNick;
	
	
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getCmntSeq() {
		return cmntSeq;
	}
	public void setCmntSeq(String cmntSeq) {
		this.cmntSeq = cmntSeq;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(String boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getCmntContents() {
		return cmntContents;
	}
	public void setCmntContents(String cmntContents) {
		this.cmntContents = cmntContents;
	}
	public String getCmntReportCnt() {
		return cmntReportCnt;
	}
	public void setCmntReportCnt(String cmntReportCnt) {
		this.cmntReportCnt = cmntReportCnt;
	}
	public String getCmntRegdate() {
		return cmntRegdate;
	}
	public void setCmntRegdate(String cmntRegdate) {
		this.cmntRegdate = cmntRegdate;
	}
	
	
}//End of class
