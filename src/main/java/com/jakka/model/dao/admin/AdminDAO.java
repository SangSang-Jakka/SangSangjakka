package com.jakka.model.dao.admin;

import java.util.ArrayList;
import java.util.List;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.admin.AdminDTO;

/**
* AdminDAO 인터페이스는 관리자 관련 데이터 액세스 작업을 수행하는 메서드를 정의합니다.
*/
public interface AdminDAO extends BasicDAO<AdminDTO>{
	
	/**
	    * 관리자의 비밀번호를 변경합니다.
	    *
	    * @param dto 관리자 DTO
	    * @return 변경 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int savePw(AdminDTO dto);

	   /**
	    * 관리자 로그인을 처리합니다.
	    *
	    * @param dto 관리자 DTO
	    * @return 로그인한 관리자 DTO
	    */
	   public AdminDTO login(AdminDTO dto);

	   /**
	    * 관리자 인증을 확인합니다.
	    *
	    * @param dto 관리자 DTO
	    * @return 인증 여부
	    */
	   public boolean isValid(AdminDTO dto);

	   /**
	    * 관리자 로그 정보를 조회합니다.
	    *
	    * @param id 관리자 ID
	    * @return 관리자 로그 목록
	    */
	   public ArrayList<AdminDTO> log(String id);

	   /**
	    * 관리자를 삭제합니다.
	    *
	    * @param id 관리자 ID
	    * @return 삭제 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int remove(String id);

	   /**
	    * 유입 경로 데이터를 조회할 수 있는 연도 목록을 반환합니다.
	    *
	    * @return 연도 목록
	    */
	   public List<String> getYear();

	   /**
	    * 특정 월의 유입 경로 데이터를 조회합니다.
	    *
	    * @param month 월
	    * @return 유입 경로 데이터 목록
	    */
	   public List<AdminDTO> getInflowCountData(String month);
	
}
