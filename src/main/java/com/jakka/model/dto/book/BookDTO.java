package com.jakka.model.dto.book;

import lombok.Data;

/**
 * 동화책 정보를 저장하는 DTO 클래스입니다.
 */
@Data
public class BookDTO {

	private String bookSeq; // 동화책 시퀀스
    private String bookTitle; // 동화책 제목
    private String bookInfo; // 동화책 정보
    private String bookCover; // 동화책 표지
    private String bookRegdate; // 동화책 등록일자
    private String bookModDate; // 동화책 수정일자
    private String likeCnt; // 좋아요 수
    private String bookReviewCnt; // 리뷰 수
    private String bookScrapCnt; // 스크랩 수
    private String bookReportCnt; // 신고 수
    private String userSeq; // 사용자 시퀀스
    private String parentBookSeq; // 부모 동화책 시퀀스
    private String rcmAgeSeq; // 추천 연령 시퀀스
    private String userNick; // 사용자 닉네임
    private String shareCnt; // 공유 수
    private String bookCnt; // 동화책 수
    private String awardRegdate; // 수상 등록일자
    private String awardRank; // 수상 등급
    private String reportDate; // 신고 일자
	
}//End of class
