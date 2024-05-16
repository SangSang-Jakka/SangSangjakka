package com.jakka.model.dao;

/**
 * ScrapCnt 인터페이스는 스크랩 기능을 제공합니다.
 */
public interface ScrapCnt {
	
	/**
     * 스크랩 수를 증가시킵니다.
     *
     * @param bookSeq 책 시퀀스
     * @param userSeq 사용자 시퀀스
     * @return 증가 성공 여부 (1: 성공, 0: 실패)
     */
    public int addScrapCnt(String bookSeq, String userSeq);

    /**
     * 스크랩 여부를 확인합니다.
     *
     * @param bookSeq 책 시퀀스
     * @param userSeq 사용자 시퀀스
     * @return 스크랩 여부
     */
    public boolean isScrap(String bookSeq, String userSeq);
	
}//End of interface
