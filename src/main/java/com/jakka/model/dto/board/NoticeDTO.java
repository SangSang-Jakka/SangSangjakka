package com.jakka.model.dto.board;

import lombok.Data;

/**
 * 공지사항 정보를 저장하는 DTO 클래스입니다.
 */
@Data
public class NoticeDTO {

	private String noticeSeq; // 공지사항 시퀀스
    private String noticeTitle; // 공지사항 제목
    private String noticeContents; // 공지사항 내용
    private String noticeRegdate; // 공지사항 등록일자
    private String noticeCnt; // 공지사항 수
    private String adId; // 관리자 ID
	
}//End of class
