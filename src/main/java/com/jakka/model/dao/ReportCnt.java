package com.jakka.model.dao;

/**
 * ReportCnt 인터페이스는 신고 기능을 제공합니다.
 */
public interface ReportCnt {
	
	/**
     * 신고 수를 증가시킵니다.
     *
     * @param seq     대상 시퀀스 (게시글, 댓글, 책 등)
     * @param userSeq 사용자 시퀀스
     * @return 증가 성공 여부 (1: 성공, 0: 실패)
     */
    public int addReportCnt(String seq, String userSeq);

    /**
     * 신고 여부를 확인합니다.
     *
     * @param seq     대상 시퀀스 (게시글, 댓글, 책 등)
     * @param userSeq 사용자 시퀀스
     * @return 신고 여부
     */
    public boolean isReport(String seq, String userSeq);
	
}
