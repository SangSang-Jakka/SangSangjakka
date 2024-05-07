package com.jakka.model.dto.board;

import lombok.Data;

@Data
public class BoardDTO {
	
	private String boardSeq;
	private String boardTitle;
	private String boardContents;
	private String boardRegdate;
	private String boardReportCnt;
	private String boardCnt;
	private String userSeq;
	
	private String userNick;
	
	private String cmntCnt;
	

	public String getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(String boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}

	public String getBoardRegdate() {
		return boardRegdate;
	}

	public void setBoardRegdate(String boardRegdate) {
		this.boardRegdate = boardRegdate;
	}

	public String getBoardReportCnt() {
		return boardReportCnt;
	}

	public void setBoardReportCnt(String boardReportCnt) {
		this.boardReportCnt = boardReportCnt;
	}

	public String getBoardCnt() {
		return boardCnt;
	}

	public void setBoardCnt(String boardCnt) {
		this.boardCnt = boardCnt;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	
	
}//End of class
