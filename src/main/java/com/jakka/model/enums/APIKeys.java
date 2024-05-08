package com.jakka.model.enums;

public enum APIKeys {

	StabilityAPI("sk-zTtHMdj33bQk24KtXGUvK6g4GoDbryTznYIAVEsO4TnyzT4V"),
	DeepLAPI("56b1cb5d-d111-4af5-81dc-e7cdaefa1251:fx");
	
	private final String value;
	
	APIKeys(String vale) {
		this.value = vale;
	}
	
	public String getValue() {
		return value;
	}
	
}
