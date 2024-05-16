package com.jakka.model.dto.board;

import lombok.Data;

/**
 * 게시판 정보를 저장하는 DTO 클래스입니다.
 */
@Data
public class BoardDTO {
	
	private String boardSeq; // 게시판 시퀀스
    private String boardTitle; // 게시판 제목
    private String boardContents; // 게시판 내용
    private String boardRegdate; // 게시판 등록일자
    private String boardReportCnt; // 게시판 신고 수
    private String boardCnt; // 게시판 수
    private String userSeq; // 사용자 시퀀스
    private String userNick; // 사용자 닉네임
    private String cmntCnt; // 댓글 수
	
}//End of class
