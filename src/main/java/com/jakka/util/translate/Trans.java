package com.jakka.util.translate;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import com.jakka.model.enums.APIKeys;

/**
* DeepL 번역 API를 사용하여 한국어를 영어로 번역하는 유틸리티 클래스입니다.
*/
public class Trans {

	
	/**
    * 한국어 문자열을 영어로 번역합니다.
    *
    * @param prompt 번역할 한국어 문자열
    * @return 번역된 영어 문자열
    * @throws IllegalArgumentException 잘못된 입력 값인 경우
    * @throws DeepLException DeepL API 호출 중 오류가 발생한 경우
    * @throws InterruptedException 스레드 인터럽트가 발생한 경우
    */
	public static String translate(String prompt) throws IllegalArgumentException, DeepLException, InterruptedException {
		
		String authKey = APIKeys.DeepLAPI.getValue();
        Translator translator = new Translator(authKey);
        TextResult result = translator.translateText(prompt, "ko", "en-US");
        
        return result.getText();
		
	}//translate()
	
}//End of class
