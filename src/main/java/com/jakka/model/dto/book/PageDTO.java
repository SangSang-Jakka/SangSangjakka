package com.jakka.model.dto.book;

import lombok.Data;

/**
 * 동화책 페이지 정보를 저장하는 DTO 클래스입니다.
 */
@Data
public class PageDTO {

	private String pageSeq; // 페이지 시퀀스
    private String bookSeq; // 동화책 시퀀스
    private String pageUrl; // 페이지 URL
    private String pageContents; // 페이지 내용
    private String cmntYN; // 댓글 여부
    private String imgYN; // 이미지 여부
	
}//End of class
