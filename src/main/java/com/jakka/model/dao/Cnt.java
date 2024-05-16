package com.jakka.model.dao;

/**
 * Cnt 인터페이스는 조회수 기능을 제공합니다.
 */
public interface Cnt {

	/**
     * 조회수를 증가시킵니다.
     *
     * @param seq 대상 시퀀스 (게시글, 책 등)
     * @return 증가 성공 여부 (1: 성공, 0: 실패)
     */
    public int addCnt(String seq);
	
}//End of interface
