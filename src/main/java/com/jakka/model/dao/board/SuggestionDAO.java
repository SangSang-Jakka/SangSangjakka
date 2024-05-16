package com.jakka.model.dao.board;

import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.SuggestionDTO;

/**
* SuggestionDAO 인터페이스는 건의사항 관련 데이터 액세스 작업을 수행하는 메서드를 정의합니다.
*/
public interface SuggestionDAO extends BasicDAO<SuggestionDTO>, Cnt, Search<SuggestionDTO>{

		/**
	    * 활성화된 건의사항 목록을 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 활성화된 건의사항 목록
	    */
	   ArrayList<SuggestionDTO> findAllWhite(HashMap<String, String> map);

	   /**
	    * 활성화된 건의사항 목록을 조회합니다. (정렬 기능 포함)
	    *
	    * @param map     검색 조건 맵
	    * @param orderBy 정렬 조건
	    * @return 활성화된 건의사항 목록
	    */
	   ArrayList<SuggestionDTO> findAllWhite(HashMap<String, String> map, String orderBy);

	   /**
	    * 활성화된 건의사항 수를 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 활성화된 건의사항 수
	    */
	   int whiteTotalCnt(HashMap<String, String> map);

	   /**
	    * 건의사항을 삭제합니다.
	    *
	    * @param seq 건의사항 시퀀스
	    * @return 삭제 성공 여부 (1: 성공, 0: 실패)
	    */
	   int del(String seq);

	   /**
	    * 비밀 건의사항 목록을 조회합니다.
	    *
	    * @return 비밀 건의사항 목록
	    */
	   public ArrayList<SuggestionDTO> findAllSecret();

	   /**
	    * 공개 건의사항 목록을 조회합니다.
	    *
	    * @return 공개 건의사항 목록
	    */
	   public ArrayList<SuggestionDTO> findAllOpen();

	   /**
	    * 답변된 건의사항 목록을 조회합니다.
	    *
	    * @return 답변된 건의사항 목록
	    */
	   public ArrayList<SuggestionDTO> findAllAnswer();

	   /**
	    * 답변되지 않은 건의사항 목록을 조회합니다.
	    *
	    * @return 답변되지 않은 건의사항 목록
	    */
	   public ArrayList<SuggestionDTO> findAllNoAnswer();

	   /**
	    * 오늘 작성된 건의사항 목록을 조회합니다.
	    *
	    * @param today 오늘 날짜
	    * @return 오늘 작성된 건의사항 목록
	    */
	   public ArrayList<SuggestionDTO> findToday(String today);
	
}
