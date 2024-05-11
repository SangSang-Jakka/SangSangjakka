package com.jakka.model.dto.user;

import lombok.Data;

@Data
public class UserDTO {
	
	private String userSeq;
	private String userId;
	private String userPw;
	private String userName;
	private String userNick;
	private String userTel;
	private String userAddress;
	private String userEmail;
	private String userLeftSsn;
	private String userRightSsn;
	private String userState;
	private String userLV;
	private String userRegdate;
	private String LimitStorage;
	
	private String numBooks;
	
	public String getNumBooks() {
		return numBooks;
	}
	public void setNumBooks(String numBooks) {
		this.numBooks = numBooks;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserLeftSsn() {
		return userLeftSsn;
	}
	public void setUserLeftSsn(String userLeftSsn) {
		this.userLeftSsn = userLeftSsn;
	}
	public String getUserRightSsn() {
		return userRightSsn;
	}
	public void setUserRightSsn(String userRightSsn) {
		this.userRightSsn = userRightSsn;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserLV() {
		return userLV;
	}
	public void setUserLV(String userLV) {
		this.userLV = userLV;
	}
	public String getUserRegdate() {
		return userRegdate;
	}
	public void setUserRegdate(String userRegdate) {
		this.userRegdate = userRegdate;
	}
	public String getLimitStorage() {
		return LimitStorage;
	}
	public void setLimitStorage(String limitStorage) {
		LimitStorage = limitStorage;
	}
	
	
	

}//End of class
