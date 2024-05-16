package com.jakka.model.enums;

/**
 * 동화책에 대한 사용자 액션을 나타내는 열거형입니다.
 */
public enum BookAction {
	
	/**
     * 조회
     */
    Inquiry("1"),
    /**
     * 좋아요
     */
    Like("2"),
    /**
     * 스크랩
     */
    Scrap("3"),
    /**
     * 나의 이야기로 만들기
     */
    BranchBook("4"),
    /**
     * 동화책 제작
     */
    CreateBook("5"),
    /**
     * 리뷰
     */
    Review("6");

    private final String value;

    BookAction(String value) {
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
