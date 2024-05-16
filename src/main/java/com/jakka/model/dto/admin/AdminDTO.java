package com.jakka.model.dto.admin;

import lombok.Data;

/**
* 관리자 정보와 관리자 로그를 저장하는 DTO 클래스입니다.
*/
@Data
public class AdminDTO {

	private String adId; // 관리자 ID
    private String adPw; // 관리자 비밀번호
    private String adName; // 관리자 이름
    private String adNick; // 관리자 닉네임
    private String adAddress; // 관리자 주소
    private String adTel; // 관리자 전화번호
    private String adLv; // 관리자 레벨
	
    // 관리자 로그
    private String adLogSeq; // 관리자 로그 시퀀스
    private String adLogDate; // 관리자 로그 날짜
    private String adCatContents; // 관리자 로그 카테고리 내용
    private String adLogContents; // 관리자 로그 내용
	
    // 유입경로
    private String registrationMonth; // 등록 월
    private String inflowname; // 유입경로 이름
    private String inflowCount; // 유입경로 수
	
}//End of class
