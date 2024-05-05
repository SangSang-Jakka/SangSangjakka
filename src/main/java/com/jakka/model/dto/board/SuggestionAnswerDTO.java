package com.jakka.model.dto.board;

import lombok.Data;

@Data
public class SuggestionAnswerDTO {

	private String answSeq;
	private String adId;
	private String sgstSeq;
	private String sgstAnsw;
	private String sgstRegdate;
	
	public String getAnswSeq() {
		return answSeq;
	}
	public void setAnswSeq(String answSeq) {
		this.answSeq = answSeq;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getSgstSeq() {
		return sgstSeq;
	}
	public void setSgstSeq(String sgstSeq) {
		this.sgstSeq = sgstSeq;
	}
	public String getSgstAnsw() {
		return sgstAnsw;
	}
	public void setSgstAnsw(String sgstAnsw) {
		this.sgstAnsw = sgstAnsw;
	}
	public String getSgstRegdate() {
		return sgstRegdate;
	}
	public void setSgstRegdate(String sgstRegdate) {
		this.sgstRegdate = sgstRegdate;
	}
	
	
	
}//End of class
