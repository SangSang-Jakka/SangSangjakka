package com.jakka.model.dto.book;

import lombok.Data;

/**
 * 동화책 리뷰 정보를 저장하는 DTO 클래스입니다.
 */
@Data
public class ReviewDTO {

	private String reviewSeq; // 리뷰 시퀀스
    private String reviewContents; // 리뷰 내용
    private String reviewLikeCnt; // 리뷰 좋아요 수
    private String reviewReportCnt; // 리뷰 신고 수
    private String userSeq; // 사용자 시퀀스
    private String bookSeq; // 동화책 시퀀스
    private String reviewRegdate; // 리뷰 등록일자
    private String userNick; // 사용자 닉네임
	
}//End of class
