package com.jakka.model.dao;

import java.util.ArrayList;

/**
 * Comments 인터페이스는 댓글 기능을 제공합니다.
 *
 * @param <T> 댓글 대상 객체 타입
 */
public interface Comments<T> {

	/**
     * 부모 시퀀스로 댓글 목록을 조회합니다.
     *
     * @param parentSeq 부모 시퀀스 (게시글, 책 등)
     * @return 댓글 목록
     */
    public ArrayList<T> findChild(String parentSeq);
	
}//End of interface
