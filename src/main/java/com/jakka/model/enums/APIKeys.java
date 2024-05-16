package com.jakka.model.enums;

public enum APIKeys {

	StabilityAPI(""),
	DeepLAPI("");
	
	private final String value;
	
	APIKeys(String vale) {
		this.value = vale;
	}
	
	public String getValue() {
		return value;
	}
	
}
