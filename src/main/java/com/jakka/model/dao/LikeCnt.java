package com.jakka.model.dao;

/**
* LikeCnt 인터페이스는 좋아요 기능을 제공합니다.
*/
public interface LikeCnt {
	
	/**
	    * 좋아요 수를 증가시킵니다.
	    *
	    * @param seq     대상 시퀀스 (게시글, 댓글, 책 등)
	    * @param userSeq 사용자 시퀀스
	    * @return 증가 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int addLikeCnt(String seq, String userSeq);

	   /**
	    * 좋아요 여부를 확인합니다.
	    *
	    * @param seq     대상 시퀀스 (게시글, 댓글, 책 등)
	    * @param userSeq 사용자 시퀀스
	    * @return 좋아요 여부
	    */
	   public boolean isLike(String seq, String userSeq);
	
}
