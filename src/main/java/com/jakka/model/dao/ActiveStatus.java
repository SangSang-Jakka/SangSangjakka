package com.jakka.model.dao;

import java.util.ArrayList;

/**
 * ActiveStatus 인터페이스는 활성화/비활성화 기능을 제공합니다.
*
* @param <T> 대상 객체 타입
*/
public interface ActiveStatus<T> {

	/**
     * 대상을 활성화합니다.
     *
     * @param seq  대상 시퀀스 (게시글, 댓글, 책 등)
     * @param adId 관리자 ID
     * @return 활성화 성공 여부 (1: 성공, 0: 실패)
     */
    public int activation(String seq, String adId);

    /**
     * 대상을 비활성화합니다.
     *
     * @param seq  대상 시퀀스 (게시글, 댓글, 책 등)
     * @param adId 관리자 ID
     * @return 비활성화 성공 여부 (1: 성공, 0: 실패)
     */
    public int disable(String seq, String adId);

    /**
     * 활성화된 대상 목록을 조회합니다.
     *
     * @return 활성화된 대상 목록
     */
    public ArrayList<T> findAllWhite();

    /**
     * 비활성화된 대상 목록을 조회합니다.
     *
     * @return 비활성화된 대상 목록
     */
    public ArrayList<T> findAllBlack();
	
}//End of interface
