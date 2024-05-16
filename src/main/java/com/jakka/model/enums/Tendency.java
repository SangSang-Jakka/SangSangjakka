package com.jakka.model.enums;

/**
 * 동화 취향 성향을 나타내는 열거형입니다.
 */
public enum Tendency {

	/**
     * 모험 성향
     */
    AdventureInclination("1"),
    /**
     * 감성 성향
     */
    EmotionalInclination("2"),
    /**
     * 교육 성향
     */
    EducationalInclination("3"),
    /**
     * 유머 성향
     */
    HumorousInclination("4"),
    /**
     * 창의성 성향
     */
    CreativeInclination("5");

    private final String value;

    Tendency(String value) {
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
