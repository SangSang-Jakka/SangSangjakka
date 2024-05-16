package com.jakka.model.dao;

import java.util.ArrayList;

/**
 * BasicDAO 인터페이스는 기본적인 CRUD 작업을 정의합니다.
 *
 * @param <T> 데이터 객체 타입
 */
public interface BasicDAO<T> {
	
	// interface -> 내가 구현 및 사용하고자 하는 메서드들을 정의하는 곳. -> 무조건 다른 클래스에서 오버라이딩으로 구현하면된다( ex : Impl 클래스 )
	// interface 는 new 인스턴스 생성을 통해서 사용하지 못한다. 구조화 시키기 위한 방식이다.
	// interface 는 구현체를 통해서 꼭 오버라이딩 구현을 해줘야한다.
	
	/**
     * 전체 데이터 목록을 조회합니다.
     *
     * @return 데이터 목록
     */
    public ArrayList<T> findAll();

    /**
     * 시퀀스로 데이터를 조회합니다.
     *
     * @param id 시퀀스
     * @return 데이터 객체
     */
    public T findById(String id);

    /**
     * 새로운 데이터를 추가합니다.
     *
     * @param dto 데이터 DTO
     * @return 추가 성공 여부 (1: 성공, 0: 실패)
     */
    public int add(T dto);

    /**
     * 데이터를 수정합니다.
     *
     * @param dto 데이터 DTO
     * @return 수정 성공 여부 (1: 성공, 0: 실패)
     */
    public int save(T dto);
	
	
}//End of interface
