package com.jakka.model.enums;

public enum UserLog {

	SignUp("1"),                  // 회원가입
	Withdrawal("2"),              // 탈퇴
	Login("3"),                   // 로그인
	BoardCreated("4"),        // 자유게시판 글작성
	BoardEdited("5"),         // 자유게시판 글수정
	BoardReported("6"),       // 자유게시판 글 신고누적
	BoardCommentCreated("7"),     // 자유게시판 댓글 작성
	BoardCommentEdited("9"),      // 자유게시판 댓글 수정
	BoardCommentReported("10"),   // 자유게시판 댓글 신고누적
	BookCreated("11"),             // 동화책 공유 등록
	BookViewed("12"),             // 동화책 조회
	BookEdited("13"),             // 동화책 수정
	BookScraped("14"),            // 동화책 스크랩
	BookLiked("15"),              // 동화책 좋아요
	BookReported("16"),           // 동화책 신고
	BookReportedCumulative("17"), // 동화책 신고 누적
	BookReviewCreated("18"),      // 동화책 감상 작성
	BookReviewEdited("19"),       // 동화책 감상 수정
	BookReviewLiked("20"),        // 동화책 감상 좋아요
	BookReviewReported("21"),     // 동화책 감상 신고누적
	BookStoryChanged("22");       // 남의 동화책 이야기 바꾸기
    
	
	private final String value;
	
	UserLog(String vale) {
		this.value = vale;
	}
	
	public String getValue() {
		return value;
	}
	
}//End of enum
