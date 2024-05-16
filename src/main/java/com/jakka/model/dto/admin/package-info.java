/**
 * 이 패키지는 관리자 관련 데이터 전송 객체(DTO) 클래스를 포함합니다.
 * DTO 클래스는 계층 간 데이터 전송을 위해 사용되며, 도메인 모델과 프레젠테이션 계층 간의 데이터 전송을 캡슐화합니다.
 * <p>
 * 주요 DTO 클래스:
 * <ul>
 *     <li>{@link com.jakka.model.dto.admin.AdminDTO AdminDTO}: 관리자 정보와 관리자 로그를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>adId: 관리자 ID</li>
 *         <li>adPw: 관리자 비밀번호</li>
 *         <li>adName: 관리자 이름</li>
 *         <li>adNick: 관리자 닉네임</li>
 *         <li>adAddress: 관리자 주소</li>
 *         <li>adTel: 관리자 전화번호</li>
 *         <li>adLv: 관리자 레벨</li>
 *         <li>adLogSeq: 관리자 로그 시퀀스</li>
 *         <li>adLogDate: 관리자 로그 날짜</li>
 *         <li>adCatContents: 관리자 로그 카테고리 내용</li>
 *         <li>adLogContents: 관리자 로그 내용</li>
 *         <li>registrationMonth: 등록 월</li>
 *         <li>inflowname: 유입경로 이름</li>
 *         <li>inflowCount: 유입경로 수</li>
 *     </ul>
 * </ul>
 * <p>
 * 이 패키지의 DTO 클래스는 관리자와 관련된 정보를 전송하고 저장하는 데 사용됩니다.
 * 관리자의 기본 정보, 로그 정보, 유입경로 정보 등을 포함하고 있습니다.
 * <p>
 * DTO 클래스는 lombok 라이브러리의 {@link lombok.Data @Data} 어노테이션을 사용하여 게터(getter), 세터(setter), toString 등의 메서드를 자동으로 생성합니다.
 * 이를 통해 보일러플레이트 코드를 줄이고 코드의 가독성을 높일 수 있습니다.
 */
package com.jakka.model.dto.admin;