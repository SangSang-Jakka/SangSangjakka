package com.jakka.model.enums;

/**
 * 유입 경로를 나타내는 열거형입니다.
 */
public enum Inflow {

	/**
     * 인터넷 검색
     */
    Internet_Search("1"),
    /**
     * 지인 소개
     */
    ACQUAINTANCE("2"),
    /**
     * 카페
     */
    CAFFE("3"),
    /**
     * 블로그
     */
    BLOG("4"),
    /**
     * 소셜 미디어 플랫폼
     */
    SOCIAL_MEDIA_PLATFORM("5"),
    /**
     * 광고지
     */
    FLYER("6"),
    /**
     * 기타
     */
    ECT("7");

    private final String value;

    Inflow(String value) {
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
	
}//End of interface
