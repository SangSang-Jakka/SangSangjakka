package com.jakka.model.enums;

public enum APIKeys {

	StabilityAPI("sk-zTtHMdj33bQk24KtXGUvK6g4GoDbryTznYIAVEsO4TnyzT4V");
	
	private final String value;
	
	APIKeys(String vale) {
		this.value = vale;
	}
	
	public String getValue() {
		return value;
	}
	
}
