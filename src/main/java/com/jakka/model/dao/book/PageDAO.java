package com.jakka.model.dao.book;

import java.util.HashMap;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.book.PageDTO;

/**
* PageDAO 인터페이스는 동화책 페이지 관련 데이터 액세스 작업을 수행하는 메서드를 정의합니다.
*/
public interface PageDAO extends BasicDAO<PageDTO>{
	
	/**
	    * 동화책 시퀀스로 해당 동화책의 페이지 정보를 조회합니다.
	    *
	    * @param bookSeq 동화책 시퀀스
	    * @return 페이지 정보 맵 (key: 페이지 번호, value: PageDTO)
	    */
	   HashMap<Integer, PageDTO> findPages(String bookSeq);

	   /**
	    * 페이지 시퀀스와 동화책 시퀀스로 페이지 정보를 조회합니다.
	    *
	    * @param pageSeq 페이지 시퀀스
	    * @param bookSeq 동화책 시퀀스
	    * @return 페이지 DTO
	    */
	   public PageDTO findById(String pageSeq, String bookSeq);

	   /**
	    * 동화책 시퀀스로 마지막 페이지 정보를 조회합니다.
	    *
	    * @param bookSeq 동화책 시퀀스
	    * @return 마지막 페이지 DTO
	    */
	   public PageDTO lastpage(String bookSeq);

	   /**
	    * 페이지를 삭제합니다.
	    *
	    * @param pageSeq 페이지 시퀀스
	    * @param bookSeq 동화책 시퀀스
	    * @return 삭제 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int del(String pageSeq, String bookSeq);
	
}
