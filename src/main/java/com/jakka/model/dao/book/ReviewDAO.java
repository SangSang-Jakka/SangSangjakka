package com.jakka.model.dao.book;

import java.util.ArrayList;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Comments;
import com.jakka.model.dao.LikeCnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.book.ReviewDTO;

/**
* ReviewDAO 인터페이스는 동화책 리뷰 관련 데이터 액세스 작업을 수행하는 메서드를 정의합니다.
*/
public interface ReviewDAO extends BasicDAO<ReviewDTO>, ReportCnt, ActiveStatus<ReviewDTO>
								, Comments<ReviewDTO>, LikeCnt{
	
	
	/**
	    * 동화책 시퀀스로 활성화된 리뷰 목록을 조회합니다.
	    *
	    * @param parentSeq 동화책 시퀀스
	    * @return 활성화된 리뷰 목록
	    */
	   public ArrayList<ReviewDTO> findChildWhite(String parentSeq);

	   /**
	    * 신고된 리뷰 목록을 조회합니다.
	    *
	    * @return 신고된 리뷰 목록
	    */
	   public ArrayList<ReviewDTO> findAllReport();

	   /**
	    * 신고되지 않은 리뷰 목록을 조회합니다.
	    *
	    * @return 신고되지 않은 리뷰 목록
	    */
	   public ArrayList<ReviewDTO> findAllNoReport();

	   /**
	    * 동화책 시퀀스와 페이징 범위로 활성화된 리뷰 목록을 조회합니다.
	    *
	    * @param bookSeq    동화책 시퀀스
	    * @param startIndex 시작 인덱스
	    * @param endIndex   종료 인덱스
	    * @return 활성화된 리뷰 목록
	    */
	   public ArrayList<ReviewDTO> findChildWhite(String bookSeq, int startIndex, int endIndex);

	   /**
	    * 동화책 시퀀스로 리뷰 수를 조회합니다.
	    *
	    * @param bookSeq 동화책 시퀀스
	    * @return 리뷰 수
	    */
	   public int reviewTotal(String bookSeq);

	   /**
	    * 리뷰를 수정합니다.
	    *
	    * @param dto 리뷰 DTO
	    * @return 수정된 리뷰 DTO
	    */
	   public ReviewDTO edit(ReviewDTO dto);

	   /**
	    * 리뷰를 삭제합니다.
	    *
	    * @param reviewSeq 리뷰 시퀀스
	    * @return 삭제 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int reviewDel(String reviewSeq);

	   /**
	    * 리뷰에 좋아요를 추가합니다.
	    *
	    * @param dto 리뷰 DTO
	    * @return 좋아요 추가 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int addLike(ReviewDTO dto);

	   /**
	    * 사용자가 리뷰를 좋아요했는지 여부를 확인합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 좋아요 여부
	    */
	   boolean isLike(String userSeq);

	   /**
	    * 사용자가 좋아요한 리뷰 목록을 조회합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 좋아요한 리뷰 목록
	    */
	   public ArrayList<ReviewDTO> findLikedReviews(String userSeq);

}
