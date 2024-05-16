package com.jakka.model.dto.board;

import lombok.Data;

/**
 * 게시판 댓글 정보를 저장하는 DTO 클래스입니다.
 */
@Data
public class BoardCommentDTO {

	private String cmntSeq; // 댓글 시퀀스
    private String userSeq; // 사용자 시퀀스
    private String boardSeq; // 게시판 시퀀스
    private String cmntContents; // 댓글 내용
    private String cmntReportCnt; // 댓글 신고 수
    private String cmntRegdate; // 댓글 등록일자
    private String userNick; // 사용자 닉네임
	
}//End of class
