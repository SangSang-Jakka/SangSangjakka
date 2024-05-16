package com.jakka.model.dao.board;

import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.NoticeDTO;

/**
* NoticeDAO 인터페이스는 공지사항 관련 데이터 액세스 작업을 수행하는 메서드를 정의합니다.
*/
public interface NoticeDAO extends BasicDAO<NoticeDTO>, Cnt, Search<NoticeDTO>{
	
		/**
	    * 공지사항을 고정합니다.
	    *
	    * @param noticeSeq 공지사항 시퀀스
	    * @param adId      관리자 ID
	    * @return 고정 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int fixed(String noticeSeq, String adId);

	   /**
	    * 공지사항의 고정을 해제합니다.
	    *
	    * @param noticeSeq 공지사항 시퀀스
	    * @param adId      관리자 ID
	    * @return 고정 해제 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int Unfixing(String noticeSeq, String adId);

	   /**
	    * 공지사항을 삭제합니다.
	    *
	    * @param noticeSeq 공지사항 시퀀스
	    * @param adId      관리자 ID
	    * @return 삭제 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int remove(String noticeSeq, String adId);

	   /**
	    * 공지사항 목록을 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 공지사항 목록
	    */
	   public ArrayList<NoticeDTO> findAll(HashMap<String, String> map);

	   /**
	    * 고정 공지사항 목록을 조회합니다.
	    *
	    * @return 고정 공지사항 목록
	    */
	   public ArrayList<NoticeDTO> findAllFix();

	   /**
	    * 총 공지사항 수를 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 총 공지사항 수
	    */
	   public int totalCnt(HashMap<String, String> map);
	
}
