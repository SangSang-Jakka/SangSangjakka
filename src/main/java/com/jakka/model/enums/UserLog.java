package com.jakka.model.enums;

/**
 * 사용자 로그 유형을 나타내는 열거형입니다.
 */
public enum UserLog {

	/**
     * 회원가입
     */
    SignUp("1"),
    /**
     * 탈퇴
     */
    Withdrawal("2"),
    /**
     * 로그인
     */
    Login("3"),
    /**
     * 자유게시판 글작성
     */
    BoardCreated("4"),
    /**
     * 자유게시판 글수정
     */
    BoardEdited("5"),
    /**
     * 자유게시판 글 신고누적
     */
    BoardReported("6"),
    /**
     * 자유게시판 댓글 작성
     */
    BoardCommentCreated("7"),
    /**
     * 자유게시판 댓글 삭제
     */
    BoardCommentDeleted("8"),
    /**
     * 자유게시판 댓글 수정
     */
    BoardCommentEdited("9"),
    /**
     * 자유게시판 댓글 신고누적
     */
    BoardCommentReported("10"),
    /**
     * 동화책 공유 등록
     */
    BookCreated("11"),
    /**
     * 동화책 조회
     */
    BookViewed("12"),
    /**
     * 동화책 수정
     */
    BookEdited("13"),
    /**
     * 동화책 스크랩
     */
    BookScraped("14"),
    /**
     * 동화책 좋아요
     */
    BookLiked("15"),
    /**
     * 동화책 신고
     */
    BookReported("16"),
    /**
     * 동화책 신고 누적
     */
    BookReportedCumulative("17"),
    /**
     * 동화책 감상 작성
     */
    BookReviewCreated("18"),
    /**
     * 동화책 감상 수정
     */
    BookReviewEdited("19"),
    /**
     * 동화책 감상 좋아요
     */
    BookReviewLiked("20"),
    /**
     * 동화책 감상 신고누적
     */
    BookReviewReported("21"),
    /**
     * 남의 동화책 이야기 바꾸기
     */
    BookStoryChanged("22"),
    /**
     * 건의사항 작성
     */
    SuggestionCreated("23"),
    /**
     * 건의사항 수정
     */
    SuggestionEdited("24");

    private final String value;

    UserLog(String value) {
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
