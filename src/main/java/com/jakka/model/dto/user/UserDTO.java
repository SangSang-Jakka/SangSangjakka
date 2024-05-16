package com.jakka.model.dto.user;

import lombok.Data;

/**
 * 사용자 정보를 저장하는 DTO 클래스입니다.
 */
@Data
public class UserDTO {
	
	private String userSeq; // 사용자 시퀀스
    private String userId; // 사용자 ID
    private String userPw; // 사용자 비밀번호
    private String userName; // 사용자 이름
    private String userNick; // 사용자 닉네임
    private String userTel; // 사용자 전화번호
    private String userAddress; // 사용자 주소
    private String userEmail; // 사용자 이메일
    private String userLeftSsn; // 사용자 주민등록번호 앞자리
    private String userRightSsn; // 사용자 주민등록번호 뒷자리
    private String userState; // 사용자 상태
    private String userLV; // 사용자 레벨
    private String userRegdate; // 사용자 등록일자
    private String LimitStorage; // 제한 저장소
    private String numBooks; // 동화책 수

}//End of class
