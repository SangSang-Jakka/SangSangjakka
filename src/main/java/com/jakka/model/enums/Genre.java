package com.jakka.model.enums;

public enum Genre {

	ART("1"),							//예술
    SCIENCE_FICTION("2"),				//공상과학
    FANTASY("3"),						//판타지
    HORROR("4"),						//공포
    EDUCATION("5"),						//교육
    HISTORY("6"),						//역사
    NATURE("7"),						//자연생태
    MYSTERY("8"),						//미스테리
    ARTIFICIAL_INTELLIGENCE("9"),		//인공지능
    FAIRYTALE("10"),					//동화
    SPORTS("11"),						//스포츠
    PUZZLE("12"),						//수수께끼
    ROMANTIC("13"),						//로맨틱
    HEARTWARMING("14"),				//따뜻한
    SPACE("15"),						//우주
    HEALTH("16"),						//건강
    CREATIVE("17"),						//창조적
    MYSTICAL("18"),						//신비
    CLASSICAL("19"),					//고전적
    COMEDY("20"),						//코믹
    FRIENDSHIP("21"),					//우정
    CREATIVITY("22");					//창의력
	
	private final String value;
	
	Genre(String vale) {
		this.value = vale;
	}
	
	public String getValue() {
		return value;
	}
	
	
}//End of enum
