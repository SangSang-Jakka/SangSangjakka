package com.jakka.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	
	//초기화 파라미터를 읽어와서 저장할 필드 
	private String encoding;
	
	
	@Override
	public void destroy() {
		//필터 객체가 제거될 때 실행되는 메소드
		System.out.println("EncodingFilter.java - destroy()");
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		//web.xml에 encoding 이라는 키값으로 저장된 utf-8 문자열 얻어오기
		encoding = filterConfig.getInitParameter("encoding");
	}
	
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
