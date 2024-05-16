package com.jakka.model.dao;

import java.util.ArrayList;

/**
* Search 인터페이스는 등록일자, 제목, 내용, 닉네임을 기준으로 검색 기능을 제공합니다.
*
* @param <T> 검색 대상 객체 타입
*/
public interface Search<T> {

		/**
	    * 특정 날짜 이전에 등록된 데이터를 조회합니다.
	    *
	    * @param date 기준 날짜
	    * @return 조회된 데이터 목록
	    */
	   public ArrayList<T> findByRegdateBefore(String date);

	   /**
	    * 특정 날짜 이후에 등록된 데이터를 조회합니다.
	    *
	    * @param date 기준 날짜
	    * @return 조회된 데이터 목록
	    */
	   public ArrayList<T> findByRegdateAfter(String date);

	   /**
	    * 특정 날짜 범위 내에 등록된 데이터를 조회합니다.
	    *
	    * @param startDate 시작 날짜
	    * @param endDate   종료 날짜
	    * @return 조회된 데이터 목록
	    */
	   public ArrayList<T> findByRegdateBetween(String startDate, String endDate);

	   /**
	    * 제목에 특정 단어가 포함된 데이터를 조회합니다.
	    *
	    * @param word 검색어
	    * @return 조회된 데이터 목록
	    */
	   public ArrayList<T> findByTitleContains(String word);

	   /**
	    * 내용에 특정 단어가 포함된 데이터를 조회합니다.
	    *
	    * @param word 검색어
	    * @return 조회된 데이터 목록
	    */
	   public ArrayList<T> findByContentsContains(String word);

	   /**
	    * 닉네임으로 데이터를 조회합니다.
	    *
	    * @param Nick 닉네임
	    * @return 조회된 데이터 목록
	    */
	   public ArrayList<T> findByNick(String Nick);
	
}//End of interface
 