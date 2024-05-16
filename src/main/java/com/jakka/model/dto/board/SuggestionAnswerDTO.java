package com.jakka.model.dto.board;

import lombok.Data;

/**
 * 건의사항 답변 정보를 저장하는 DTO 클래스입니다.
 */
@Data
public class SuggestionAnswerDTO {

	private String answSeq; // 답변 시퀀스
    private String adId; // 관리자 ID
    private String sgstSeq; // 건의사항 시퀀스
    private String sgstAnsw; // 건의사항 답변
    private String sgstRegdate; // 건의사항 답변 등록일자
	
}//End of class
