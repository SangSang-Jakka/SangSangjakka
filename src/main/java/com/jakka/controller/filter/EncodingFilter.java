package com.jakka.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * EncodingFilter 클래스는 요청과 응답의 문자 인코딩을 설정하는 필터입니다.
 */
public class EncodingFilter implements Filter{
	
	//초기화 파라미터를 읽어와서 저장할 필드 
	private String encoding;
	
	/**
     * 필터 객체가 제거될 때 실행되는 메소드
     */
	@Override
	public void destroy() {
		//필터 객체가 제거될 때 실행되는 메소드
		System.out.println("EncodingFilter.java - destroy()");
	}
	
	/**
     * web.xml에서 설정한 encoding 초기화 파라미터 값을 읽어옵니다.
     */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		//web.xml에 encoding 이라는 키값으로 저장된 utf-8 문자열 얻어오기
		encoding = filterConfig.getInitParameter("encoding");
	}
	
	/**
     * 요청과 응답의 문자 인코딩을 초기화 파라미터에서 설정한 값으로 설정합니다.
     */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("EncodingFilter.java - doFilter()");
		
		if(req.getCharacterEncoding()==null) {	//인코딩이 설정되지 않으면
			
			req.setCharacterEncoding(encoding); //인코딩 설정하기
			
		}
		
		chain.doFilter(req, resp);
		
	}
	
	
}//End of class
