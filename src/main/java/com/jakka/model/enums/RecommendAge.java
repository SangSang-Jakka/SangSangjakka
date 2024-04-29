package com.jakka.model.enums;

public enum RecommendAge {
	
	ODER_1YEARS("1"),		//1세 이상
	ODER_3YEARS("2"),		//3세 이상
	ODER_5YEARS("3"),		//5세 이상
	ODER_7YEARS("4"),		//7세 이상
	ODER_10YEARS("5");		//10세 이상
	
	private final String value;
	
	RecommendAge(String vale) {
		this.value = vale;
	}
	
	public String getValue() {
		return value;
	}
	
}//End of enum
