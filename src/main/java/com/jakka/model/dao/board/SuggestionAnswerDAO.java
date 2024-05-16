package com.jakka.model.dao.board;

import java.util.ArrayList;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Comments;
import com.jakka.model.dto.board.SuggestionAnswerDTO;

/**
* SuggestionAnswerDAO 인터페이스는 건의사항 답변 관련 데이터 액세스 작업을 수행하는 메서드를 정의합니다.
*/
public interface SuggestionAnswerDAO extends BasicDAO<SuggestionAnswerDTO>, Comments<SuggestionAnswerDTO>{

		/**
	    * 건의사항 시퀀스로 해당 건의사항의 답변 목록을 조회합니다.
	    *
	    * @param parentSgstSeq 건의사항 시퀀스
	    * @return 답변 목록
	    */
	   public ArrayList<SuggestionAnswerDTO> list(String parentSgstSeq);

	   /**
	    * 답변을 삭제합니다.
	    *
	    * @param answSeq 답변 시퀀스
	    * @param adId    관리자 ID
	    * @return 삭제 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int del(String answSeq, String adId);
	
}
