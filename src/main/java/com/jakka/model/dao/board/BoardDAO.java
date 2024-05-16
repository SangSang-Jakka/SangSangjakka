package com.jakka.model.dao.board;

import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.BoardDTO;

/**
* BoardDAO 인터페이스는 게시판 관련 데이터 액세스 작업을 수행하는 메서드를 정의합니다.
*/
public interface BoardDAO extends BasicDAO<BoardDTO>, Cnt, ReportCnt
								, ActiveStatus<BoardDTO>, Search<BoardDTO>{
	
		/**
	    * 활성화된 게시글 수를 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 활성화된 게시글 수
	    */
	   public int whiteTotalCnt(HashMap<String, String> map);

	   /**
	    * 활성화된 게시글 목록을 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 활성화된 게시글 목록
	    */
	   public ArrayList<BoardDTO> findAllWhite(HashMap<String, String> map);

	   /**
	    * 게시글을 삭제합니다.
	    *
	    * @param boardSeq 게시글 시퀀스
	    * @return 삭제 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int remove(String boardSeq);

	   /**
	    * 사용자 시퀀스로 게시글 수를 조회합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 게시글 수
	    */
	   public int findSeq(String userSeq);

	   /**
	    * 게시글 정보를 조회합니다.
	    *
	    * @param freeSeq 게시글 시퀀스
	    * @return 게시글 DTO
	    */
	   public BoardDTO findInfo(String freeSeq);

	   /**
	    * 게시글을 수정합니다.
	    *
	    * @param dto 게시글 DTO
	    * @return 수정 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int saveEdit(BoardDTO dto);

	   /**
	    * 게시글을 삭제합니다.
	    *
	    * @param boardSeq 게시글 시퀀스
	    * @return 삭제 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int freeDel(String boardSeq);

	   /**
	    * 활성화된 게시글 목록을 조회합니다. (정렬 기능 포함)
	    *
	    * @param map     검색 조건 맵
	    * @param orderBy 정렬 조건
	    * @return 활성화된 게시글 목록
	    */
	   public ArrayList<BoardDTO> findAllWhite(HashMap<String, String> map, String orderBy);

	   /**
	    * 신고된 게시글 목록을 조회합니다.
	    *
	    * @return 신고된 게시글 목록
	    */
	   public ArrayList<BoardDTO> findAllReport();

	   /**
	    * 신고되지 않은 게시글 목록을 조회합니다.
	    *
	    * @return 신고되지 않은 게시글 목록
	    */
	   public ArrayList<BoardDTO> findAllNoReport();

	   /**
	    * 오늘 작성된 게시글 목록을 조회합니다.
	    *
	    * @param today 오늘 날짜
	    * @return 오늘 작성된 게시글 목록
	    */
	   public ArrayList<BoardDTO> findToday(String today);

	   /**
	    * 닉네임으로 게시글 목록을 조회합니다.
	    *
	    * @param Nick 닉네임
	    * @return 게시글 목록
	    */
	   public ArrayList<BoardDTO> findByNickBoard(String Nick);

	   /**
	    * 특정 날짜에 작성된 새로운 게시글 수를 조회합니다.
	    *
	    * @param userRegdate 날짜
	    * @return 새로운 게시글 수
	    */
	   public int getNewPostCount(String userRegdate);

	   /**
	    * 특정 날짜에 작성된 새로운 건의사항 수를 조회합니다.
	    *
	    * @param userRegdate 날짜
	    * @return 새로운 건의사항 수
	    */
	   public int getNewSuggestionCount(String userRegdate);

	   /**
	    * 특정 날짜에 신고된 게시글 수를 조회합니다.
	    *
	    * @param userRegdate 날짜
	    * @return 신고된 게시글 수
	    */
	   public int boardReportCount(String userRegdate);

	   /**
	    * 특정 날짜에 신고된 댓글 수를 조회합니다.
	    *
	    * @param userRegdate 날짜
	    * @return 신고된 댓글 수
	    */
	   public int CommReportCount(String userRegdate);
	
}
