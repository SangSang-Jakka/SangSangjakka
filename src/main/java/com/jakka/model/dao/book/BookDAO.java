package com.jakka.model.dao.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.LikeCnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dao.ScrapCnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;

/**
* BookDAO 인터페이스는 동화책 관련 데이터 액세스 작업을 수행하는 메서드를 정의합니다.
*/
public interface BookDAO extends BasicDAO<BookDTO>, ReportCnt, Cnt, ActiveStatus<BookDTO>
								, Search<BookDTO>, LikeCnt , ScrapCnt{
	
	/**
	    * 동화책을 스크랩합니다.
	    *
	    * @param bookSeq 동화책 시퀀스
	    * @param userSeq 사용자 시퀀스
	    * @return 스크랩 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int addScrap(String bookSeq, String userSeq);

	   /**
	    * 사용자가 좋아요한 동화책 목록을 조회합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 동화책 목록
	    */
	   public ArrayList<BookDTO> findAllLike(String userSeq);

	   /**
	    * 사용자가 스크랩한 동화책 목록을 조회합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 동화책 목록
	    */
	   public ArrayList<BookDTO> findAllScrap(String userSeq);

	   /**
	    * 닉네임으로 동화책 목록을 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 동화책 목록
	    */
	   public ArrayList<BookDTO> findByNick(HashMap<String, String> map);

	   /**
	    * 제목에 특정 문자열이 포함된 동화책 목록을 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 동화책 목록
	    */
	   public ArrayList<BookDTO> findByTitleContains(HashMap<String, String> map);

	   /**
	    * 내용에 특정 문자열이 포함된 동화책 목록을 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 동화책 목록
	    */
	   public ArrayList<BookDTO> findByContentsContains(HashMap<String, String> map);

	   /**
	    * 활성화된 동화책 목록을 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 동화책 목록
	    */
	   public ArrayList<BookDTO> findAllWhite(HashMap<String, String> map);

	   /**
	    * 활성화된 동화책 수를 조회합니다.
	    *
	    * @param map 검색 조건 맵
	    * @return 동화책 수
	    */
	   public int whiteTotalCnt(HashMap<String, String> map);

	   /**
	    * 동화책 폴더를 생성합니다.
	    *
	    * @param userId 사용자 ID
	    * @param bookSeq 동화책 시퀀스
	    * @param context ServletContext 객체
	    */
	   public void createBookFolder(String userId, String bookSeq, ServletContext context);

	   /**
	    * 동화책 제작을 완료합니다.
	    *
	    * @param dto 동화책 DTO
	    * @return 완료 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int complete(BookDTO dto);

	   /**
	    * 동화책 조회수를 증가시킵니다.
	    *
	    * @param bookSeq 동화책 시퀀스
	    * @param userSeq 사용자 시퀀스
	    * @return 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int addCnt(String bookSeq, String userSeq);

	   /**
	    * 수상 동화책 목록을 조회합니다.
	    *
	    * @return 수상 동화책 목록
	    */
	   public ArrayList<BookDTO> findAllAward();

	   /**
	    * 동화책에 수상 정보를 부여합니다.
	    *
	    * @param list 동화책 목록
	    * @param adId 관리자 ID
	    * @return 수상 처리 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int presentAward(ArrayList<BookDTO> list, String adId);

	   /**
	    * 신고된 동화책 목록을 조회합니다.
	    *
	    * @return 신고된 동화책 목록
	    */
	   public ArrayList<BookDTO> findAllReport();

	   /**
	    * 신고되지 않은 동화책 목록을 조회합니다.
	    *
	    * @return 신고되지 않은 동화책 목록
	    */
	   public ArrayList<BookDTO> findAllNoReport();

	   /**
	    * 현재 수상 동화책 목록을 조회합니다.
	    *
	    * @return 현재 수상 동화책 목록
	    */
	   public ArrayList<BookDTO> findNowAward();

	   /**
	    * 특정 월의 수상 동화책 목록을 조회합니다.
	    *
	    * @param month 월
	    * @return 해당 월의 수상 동화책 목록
	    */
	   public ArrayList<BookDTO> findMonthAward(String month);

	   /**
	    * 특정 월의 공유 동화책 수를 조회합니다.
	    *
	    * @param month 월
	    * @return 해당 월의 공유 동화책 수
	    */
	   public List<BookDTO> getShareCount(String month);

	   /**
	    * 신고된 동화책 정보를 조회합니다.
	    *
	    * @param bookSeq 동화책 시퀀스
	    * @return 신고된 동화책 정보
	    */
	   public ArrayList<BookDTO> findByReport(String bookSeq);

	   /**
	    * 동화책의 수상 정보를 삭제합니다.
	    *
	    * @param bookSeq 동화책 시퀀스
	    * @return 삭제 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int delAward(String bookSeq);

	   /**
	    * 전체 동화책 수를 조회합니다.
	    *
	    * @return 전체 동화책 수
	    */
	   public int getBookCount();

	   /**
	    * 오늘 등록된 동화책 수를 조회합니다.
	    *
	    * @param today 오늘 날짜
	    * @return 오늘 등록된 동화책 수
	    */
	   public int getTodayBookCount(String today);

	   /**
	    * 전체 페이지 수를 조회합니다.
	    *
	    * @return 전체 페이지 수
	    */
	   public int getPageCount();

	   /**
	    * 특정 월의 동화책 제작 정보를 조회합니다.
	    *
	    * @param month 월
	    * @return 동화책 제작 정보 맵 (key: 동화책 제목, value: 동화책 수)
	    */
	   public Map<String, Integer> makeBook(String month);

	   /**
	    * 사용자 시퀀스로 동화책 목록을 조회합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 동화책 목록
	    */
	   public ArrayList<BookDTO> findByUserSeq(String userSeq);

	
}//End of interface
