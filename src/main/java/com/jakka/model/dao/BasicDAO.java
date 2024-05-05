package com.jakka.model.dao;

import java.util.ArrayList;

public interface BasicDAO<T> {
	public ArrayList<T> findAll();
	public T findById(String id);
	public int add(T dto);
	public int save(T dto);
	
	// interface -> 내가 구현 및 사용하고자 하는 메서드들을 정의하는 곳. -> 무조건 다른 클래스에서 오버라이딩으로 구현하면된다( ex : Impl 클래스 )
	// interface 는 new 인스턴스 생성을 통해서 사용하지 못한다. 구조화 시키기 위한 방식이다.
	// interface 는 구현체를 통해서 꼭 오버라이딩 구현을 해줘야한다.
	
}//End of interface
