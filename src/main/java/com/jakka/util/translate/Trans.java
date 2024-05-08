package com.jakka.util.translate;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import com.jakka.model.enums.APIKeys;

public class Trans {

	//한글을 영어로 번역해주는 메서드
	public static String translate(String prompt) throws IllegalArgumentException, DeepLException, InterruptedException {
		
		String authKey = APIKeys.DeepLAPI.getValue();
        Translator translator = new Translator(authKey);
        TextResult result = translator.translateText(prompt, "ko", "en-US");
        
        return result.getText();
		
	}//translate()
	
}//End of class
