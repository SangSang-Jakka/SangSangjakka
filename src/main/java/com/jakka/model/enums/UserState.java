package com.jakka.model.enums;

/**
* 사용자 계정 상태를 나타내는 열거형입니다.
*/
public enum UserState {
	
		/**
	    * 활성화된 상태
	    */
	   ACTIVE("y"),
	   /**
	    * 비활성화된 상태
	    */
	   DISABLE("n");
	
	   private final String value;
	
	   UserState(String value) {
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
