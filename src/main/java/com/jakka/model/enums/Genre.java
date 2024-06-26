package com.jakka.model.enums;

/**
* 동화의 장르를 나타내는 열거형입니다.
*/
public enum Genre {

	/**
	    * 판타지 동화
	    */
	   Fantasytales("1"),
	   /**
	    * 탐험 동화
	    */
	   Adventuretales("2"),
	   /**
	    * 친구/가족 이야기 동화
	    */
	   FriendFamilyStories("3"),
	   /**
	    * 감동적인 동화
	    */
	   HeartwarmingTales("4"),
	   /**
	    * 과학/자연 동화
	    */
	   ScienceNatureTales("5"),
	   /**
	    * 역사 동화
	    */
	   HistoricalTales("6"),
	   /**
	    * 익살스런 동화
	    */
	   HumorousTales("7"),
	   /**
	    * 말장난 동화
	    */
	   WordplayTales("8"),
	   /**
	    * 발명품 관련 동화
	    */
	   InventionRelatedTales("9"),
	   /**
	    * 상상력 자극 동화
	    */
	   ImaginativeTales("10");

	   private final String value;

	   Genre(String value) {
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
