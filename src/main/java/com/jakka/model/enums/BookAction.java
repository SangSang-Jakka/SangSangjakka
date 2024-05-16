package com.jakka.model.enums;

/**
 * 동화책에 대한 사용자 액션을 나타내는 열거형입니다.
 */
public enum BookAction {
	
	Inquiry("1"),		// 조회
	Like("2"), 			// 좋아요
	Scrap("3"), 		// 스크랩
	BranchBook("4"),	// 나의 이야기로 만들기 
	CreateBook("5"),	// 동화책 제작
	Review("6");		// 리뷰
	
	private final String value;
	
	BookAction(String vale) {
		this.value = vale;
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
