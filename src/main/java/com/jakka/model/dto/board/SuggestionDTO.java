package com.jakka.model.dto.board;

import lombok.Data;

@Data
public class SuggestionDTO {

	private String sgstSeq;
	private String sgstTitle;
	private String sgstContents;
	private String sgstRegdate;
	private String sgstSecretYN;
	private String userSeq;
	private String sgstCnt;
	
	public String getSgstSeq() {
		return sgstSeq;
	}
	public void setSgstSeq(String sgstSeq) {
		this.sgstSeq = sgstSeq;
	}
	public String getSgstTitle() {
		return sgstTitle;
	}
	public void setSgstTitle(String sgstTitle) {
		this.sgstTitle = sgstTitle;
	}
	public String getSgstContents() {
		return sgstContents;
	}
	public void setSgstContents(String sgstContents) {
		this.sgstContents = sgstContents;
	}
	public String getSgstRegdate() {
		return sgstRegdate;
	}
	public void setSgstRegdate(String sgstRegdate) {
		this.sgstRegdate = sgstRegdate;
	}
	public String getSgstSecretYN() {
		return sgstSecretYN;
	}
	public void setSgstSecretYN(String sgstSecretYN) {
		this.sgstSecretYN = sgstSecretYN;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getSgstCnt() {
		return sgstCnt;
	}
	public void setSgstCnt(String sgstCnt) {
		this.sgstCnt = sgstCnt;
	}
	
	
	
}//End of class
