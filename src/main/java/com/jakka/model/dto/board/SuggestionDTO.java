package com.jakka.model.dto.board;

import lombok.Data;

/**
 * 건의사항 정보를 저장하는 DTO 클래스입니다.
 */
@Data
public class SuggestionDTO {

	private String sgstSeq; // 건의사항 시퀀스
    private String sgstTitle; // 건의사항 제목
    private String sgstContents; // 건의사항 내용
    private String sgstRegdate; // 건의사항 등록일자
    private String sgstSecretYN; // 건의사항 비밀 여부
    private String userSeq; // 사용자 시퀀스
    private String sgstCnt; // 건의사항 수
    private String userNick; // 사용자 닉네임
    private String attach; // 파일
	
}//End of class
