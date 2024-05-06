package com.jakka.model.dto.admin;

import lombok.Data;

@Data
public class AdminDTO {

	private String adId;
	private String adPw;
	private String adName;
	private String adNick;
	private String adAddress;
	private String adTel;
	private String adLv;
	
	//관리자 로그
	private String adLogSeq;
	private String adLogDate;
	private String adCatContents;
	private String adLogContents;
	
	
	
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getAdPw() {
		return adPw;
	}
	public void setAdPw(String adPw) {
		this.adPw = adPw;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdNick() {
		return adNick;
	}
	public void setAdNick(String adNick) {
		this.adNick = adNick;
	}
	public String getAdAddress() {
		return adAddress;
	}
	public void setAdAddress(String adAddress) {
		this.adAddress = adAddress;
	}
	public String getAdTel() {
		return adTel;
	}
	public void setAdTel(String adTel) {
		this.adTel = adTel;
	}
	public String getAdLv() {
		return adLv;
	}
	public void setAdLv(String adLv) {
		this.adLv = adLv;
	}
	
	
	
}//End of class
