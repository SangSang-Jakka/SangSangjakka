package com.jakka.model.enums;

/**
* 사용자 계정 상태를 나타내는 열거형입니다.
*/
public enum UserState {
	
	ACTIVE("y"),
	DISABLE("n");
	
	private final String value;
	
	UserState(String vale) {
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
