package com.jakka.model.enums;

public enum UserState {
	
	ACTIVE("y"),
	DISABLE("n");
	
	private final String value;
	
	UserState(String vale) {
		this.value = vale;
	}
	
	public String getValue() {
		return value;
	}
	
}//End of enum
