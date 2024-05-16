package com.jakka.model.dao.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.user.UserDTO;

/**
* UserDAO 인터페이스는 사용자 관련 데이터 액세스 작업을 수행하는 메서드를 정의합니다.
*/
public interface UserDAO extends BasicDAO<UserDTO>, ActiveStatus<UserDTO>{
	
		/**
	    * 사용자의 비밀번호를 변경합니다.
	    *
	    * @param dto 사용자 DTO
	    * @return 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int savePw(UserDTO dto);

	   /**
	    * 사용자 ID로 사용자 시퀀스를 찾습니다.
	    *
	    * @param id 사용자 ID
	    * @return 사용자 시퀀스
	    */
	   public String findSeq(String id);

	   /**
	    * 사용자의 저장소 용량을 변경합니다.
	    *
	    * @param dto  사용자 DTO
	    * @param adId 관리자 ID
	    * @return 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int saveStorage(UserDTO dto, String adId);

	   /**
	    * 사용자 폴더를 생성합니다.
	    *
	    * @param userId 사용자 ID
	    */
	   public void createUserFolder(String userId);

	   /**
	    * 사용자 로그인을 처리합니다.
	    *
	    * @param dto 사용자 DTO
	    * @return 로그인 사용자 DTO
	    */
	   public UserDTO login(UserDTO dto);

	   /**
	    * 사용자 ID를 찾습니다.
	    *
	    * @param dto 사용자 DTO
	    * @return 사용자 DTO (ID와 가입일자)
	    */
	   public UserDTO findId(UserDTO dto);

	   /**
	    * 사용자 비밀번호를 찾습니다.
	    *
	    * @param dto 사용자 DTO
	    * @return 사용자 DTO (사용자 시퀀스)
	    */
	   public UserDTO findPw(UserDTO dto);

	   /**
	    * 사용자 탈퇴를 처리합니다.
	    *
	    * @param dto 사용자 DTO
	    * @return 성공 여부
	    */
	   public boolean unRegister(UserDTO dto);

	   /**
	    * 사용자 가입을 처리합니다.
	    *
	    * @param dto 사용자 DTO
	    * @return 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int signUp(UserDTO dto);

	   /**
	    * 사용자 ID 중복 확인을 수행합니다.
	    *
	    * @param dto 사용자 DTO
	    * @return 중복 여부 (0: 중복 없음, 1: 중복 있음)
	    */
	   public int checkId(UserDTO dto);

	   /**
	    * 특정 날짜에 가입한 사용자 수를 반환합니다.
	    *
	    * @param userRegdate 사용자 등록 날짜
	    * @return 사용자 수
	    */
	   public int userCnt(String userRegdate);

	   /**
	    * 사용자의 성별 비율을 반환합니다.
	    *
	    * @return 성별 비율 (key: "man" 또는 "woman", value: 사용자 수)
	    */
	   public Map<String, Integer> userGender();

	   /**
	    * 사용자 닉네임 중복 확인을 수행합니다.
	    *
	    * @param dto 사용자 DTO
	    * @return 중복 여부 (0: 중복 없음, 1: 중복 있음)
	    */
	   public int checkNick(UserDTO dto);

	   /**
	    * 사용자 이메일 중복 확인을 수행합니다.
	    *
	    * @param dto 사용자 DTO
	    * @return 중복 여부 (0: 중복 없음, 1: 중복 있음)
	    */
	   public int checkEmail(UserDTO dto);

	   /**
	    * 사용자 ID로 사용자 시퀀스를 찾습니다.
	    *
	    * @param dto 사용자 DTO
	    * @return 사용자 시퀀스
	    */
	   public int findPK(UserDTO dto);

	   /**
	    * 특정 기간 동안의 신규 가입자 수와 탈퇴자 수를 반환합니다.
	    *
	    * @param formattedNum1 시작 날짜 (YY/MM 형식)
	    * @param formattedNum2 종료 날짜 (YY/MM 형식)
	    * @return 월별 신규 가입자 수와 탈퇴자 수 (key: 월, value: Map(key: "user_count" 또는 "user_left", value: 사용자 수))
	    */
	   public Map<String, Map<String, Integer>> newCnt(String formattedNum1, String formattedNum2);

	   /**
	    * 연령대별 자녀 수를 반환합니다.
	    *
	    * @return 연령대별 자녀 수 (key: 연령대, value: 자녀 수)
	    */
	   public Map<String, Integer> childAge();

	   /**
	    * 연령대별 사용자 수를 반환합니다.
	    *
	    * @return 연령대별 사용자 수 (key: 연령대, value: 사용자 수)
	    */
	   public Map<String, Integer> userAge();

	   /**
	    * 블랙리스트에 있는 사용자 목록을 반환합니다.
	    *
	    * @return 블랙리스트 사용자 목록
	    */
	   public ArrayList<UserDTO> findAllBlackList();

	   /**
	    * 사용자 로그인 로그를 작성합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    */
	   public void loginLog(String userSeq);

	   /**
	    * 블랙리스트에서 사용자를 복원합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int blaklistReStore(String userSeq);

	   /**
	    * 사용자 ID로 블랙리스트 여부를 확인합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 블랙리스트 여부 (1: 블랙리스트에 있음, 0: 블랙리스트에 없음)
	    */
	   public int findBlacklistSeq(String userSeq);

	   /**
	    * 비활성화된 사용자 수를 반환합니다.
	    *
	    * @return 비활성화된 사용자 수
	    */
	   public int nUserCount();

	   /**
	    * 특정 연도의 월별 가입자 수와 탈퇴자 수를 반환합니다.
	    *
	    * @param year 연도
	    * @return 월별 가입자 수와 탈퇴자 수 (key: 월, value: 배열 {가입자 수, 탈퇴자 수})
	    */
	   public Map<String, Integer[]> member(String year);

	   /**
	    * 사용자가 작성한 동화책 수를 포함한 사용자 목록을 반환합니다.
	    *
	    * @return 사용자 목록 (동화책 수 포함)
	    */
	   public ArrayList<UserDTO> findAllBook();

	   /**
	    * 사용자 시퀀스로 사용자 정보와 동화책 수를 반환합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 사용자 DTO (동화책 수 포함)
	    */
	   public UserDTO findByBook(String userSeq);

	   /**
	    * 사용자의 장르 점수를 반환합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 사용자의 상위 4개 장르 이름 배열
	    */
	   public String[] findGenreScore(String userSeq);

	   /**
	    * 사용자의 성향 점수를 반환합니다.
	    *
	    * @param userSeq 사용자 시퀀스
	    * @return 사용자의 성향 점수 (key: 성향 이름, value: 성향 점수)
	    */
	   public HashMap<String, Double> findTendencyScore(String userSeq);

}
