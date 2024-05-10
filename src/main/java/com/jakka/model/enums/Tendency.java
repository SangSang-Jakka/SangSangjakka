package com.jakka.model.enums;

public enum Tendency {

	AdventureInclination("1"),	//모험 성향
	EmotionalInclination("2"),	//감성 성향
	EducationalInclination("3"),	//교육 성향
	HumorousInclination("4"),		//유머 성향
	CreativeInclination("5");		//창의성 성향
	
	private final String value;
	
	Tendency(String vale) {
		this.value = vale;
	}
	
	public String getValue() {
		return value;
	}
	
}//End of enum
