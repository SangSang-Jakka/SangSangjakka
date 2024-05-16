package com.jakka.model.enums;

/**
 * 동화 추천 연령대를 나타내는 열거형입니다.
 */
public enum RecommendAge {
	
	/**
     * 1세 이상
     */
    ODER_1YEARS("1"),
    /**
     * 3세 이상
     */
    ODER_3YEARS("2"),
    /**
     * 5세 이상
     */
    ODER_5YEARS("3"),
    /**
     * 7세 이상
     */
    ODER_7YEARS("4"),
    /**
     * 10세 이상
     */
    ODER_10YEARS("5");

    private final String value;

    RecommendAge(String value) {
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
