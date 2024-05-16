/**
 * 이 패키지는 번역 기능과 관련된 유틸리티 클래스를 포함합니다.
 * <p>
 * Trans 클래스는 DeepL 번역 API를 사용하여 한국어를 영어로 번역하는 기능을 제공합니다.
 * 주요 기능으로는 한국어 문자열을 입력받아 DeepL API를 호출하여 영어로 번역된 결과를 반환합니다.
 * <p>
 * 주요 기능:
 * <ul>
 *     <li>DeepL 번역 API를 사용한 한국어 to 영어 번역</li>
 *     <li>번역할 한국어 문자열을 입력받아 영어로 번역된 결과 반환</li>
 * </ul>
 * <p>
 * 이 패키지의 주요 클래스:
 * <ul>
 *     <li>{@link com.jakka.util.translate.Trans Trans 클래스}: DeepL API를 사용하여 한국어를 영어로 번역하는 유틸리티 클래스</li>
 * </ul>
 * <p>
 * 예외 상황:
 * <ul>
 *     <li>{@link java.lang.IllegalArgumentException IllegalArgumentException}: 잘못된 입력 값인 경우 발생</li>
 *     <li>{@link com.deepl.api.DeepLException DeepLException}: DeepL API 호출 중 오류가 발생한 경우 발생</li>
 *     <li>{@link java.lang.InterruptedException InterruptedException}: 스레드 인터럽트가 발생한 경우 발생</li>
 * </ul>
 */
package com.jakka.util.translate;