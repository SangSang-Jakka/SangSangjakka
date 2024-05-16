package com.jakka.model.enums;

/**
 * 관리자 로그 유형을 나타내는 열거형입니다.
 */
public enum AdminLog {
	
	/**
     * 사용자 비활성화
     */
    serDisabled("1"),
    /**
     * 사용자 활성화
     */
    UserEnabled("2"),
    /**
     * 사용자 저장소 용량 변경
     */
    UserStorageChanged("3"),
    /**
     * 건의사항 답변
     */
    SuggestionAnswered("4"),
    /**
     * 자유게시판 게시글 비활성화
     */
    BoardDisabled("5"),
    /**
     * 자유게시판 게시글 활성화
     */
    BoardEnabled("6"),
    /**
     * 공지사항 작성
     */
    NoticeCreated("7"),
    /**
     * 공지사항 삭제
     */
    NoticeDeleted("8"),
    /**
     * 공지사항 고정
     */
    NoticeFixed("9"),
    /**
     * 공지사항 고정해제
     */
    NoticeUnfixed("10"),
    /**
     * 동화책 비활성화
     */
    BookDisabled("11"),
    /**
     * 동화책 활성화
     */
    BookEnabled("12"),
    /**
     * 동화책 댓글 비활성화
     */
    BookReviewDisabled("13"),
    /**
     * 동화책 댓글 활성화
     */
    BookReviewEnabled("14"),
    /**
     * 동화책 수상
     */
    BookAwarded("15"),
    /**
     * 자유게시판 댓글 비활성화
     */
    BoardCommentDisabled("16"),
    /**
     * 자유게시판 댓글 활성화
     */
    BoardCommentEnabled("17"),
    /**
     * 공지사항 작성
     */
    NoticeEdited("18"),
    /**
     * 건의사항 답변 수정
     */
    SuggestionAnsweredEdited("19"),
    /**
     * 건의사항 답변 삭제
     */
    SuggestionAnsweredDeleted("20");

    private final String value;

    AdminLog(String value) {
        this.value = value;
    }

    /**
     * 열거형 값을 반환합니다.
     *
     * @return 열거형 값
     */
    public String getValue() {
        return value;
    }
	
}//End of enum
