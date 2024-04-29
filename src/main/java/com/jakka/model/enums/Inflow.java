package com.jakka.model.enums;

public enum Inflow {

	Internet_Search("1"),			//인터넷 검색
	ACQUAINTANCE("2"),				//지인 소개
	CAFFE("3"),						//카페
	BLOG("4"),						//블로그
	SOCIAL_MEDIA_PLATFORM("5"),	//소셜 미디어 플랫폼
	FLYER("6"),						//광고지
	ECT("7");						//기타
	
	private final String value;
	
	Inflow(String vale) {
		this.value = vale;
	}
	
	public String getValue() {
		return value;
	}
	
}//End of interface
