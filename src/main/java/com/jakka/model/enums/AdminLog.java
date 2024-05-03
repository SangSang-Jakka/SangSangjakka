package com.jakka.model.enums;

public enum AdminLog {
	
	UserDisabled("1"),         // 사용자 비활성화
	UserEnabled("2"),          // 사용자 활성화
	UserStorageChanged("3"),   // 사용자 저장소 용량 변경
	SuggestionAnswered("4"),   // 건의사항 답변
	BoardDisabled("5"),    // 자유게시판 게시글 비활성화
	BoardEnabled("6"),     // 자유게시판 게시글 활성화
	NoticeCreated("7"),        // 공지사항 작성
	NoticeDeleted("8"),        // 공지사항 삭제
	NoticeFixed("9"),         // 공지사항 고정
	NoticeUnfixed("10"),    // 공지사항 고정해제
	BookDisabled("11"),        // 동화책 비활성화
	BookEnabled("12"),         // 동화책 활성화
	BookReviewDisabled("13"), // 동화책 댓글 비활성화
	BookReviewEnabled("14"),  // 동화책 댓글 활성화
	BookAwarded("15"),         // 동화책 수상
	BoardCommentDisabled("16"),// 자유게시판 댓글 비활성화
	BoardCommentEnabled("17"), // 자유게시판 댓글 활성화
	NoticeEdited("18"),        // 공지사항 작성
	SuggestionAnsweredEdited("19");			// 건의사항 답변 수정
	
	private final String value;
	
	AdminLog(String vale) {
		this.value = vale;
	}
	
	public String getValue() {
		return value;
	}
	
}//End of enum
