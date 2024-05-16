/**
 * 이 패키지는 사용자 관련 데이터 전송 객체(DTO) 클래스를 포함합니다.
 * DTO 클래스는 계층 간 데이터 전송을 위해 사용되며, 도메인 모델과 프레젠테이션 계층 간의 데이터 전송을 캡슐화합니다.
 * <p>
 * 주요 DTO 클래스:
 * <ul>
 *     <li>{@link com.jakka.model.dto.user.GenreScore GenreScore}: 장르 점수 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>score: 장르 점수</li>
 *         <li>genreName: 장르 이름</li>
 *         <li>genreSeq: 장르 시퀀스</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dto.user.UserDTO UserDTO}: 사용자 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>userSeq: 사용자 시퀀스</li>
 *         <li>userId: 사용자 ID</li>
 *         <li>userPw: 사용자 비밀번호</li>
 *         <li>userName: 사용자 이름</li>
 *         <li>userNick: 사용자 닉네임</li>
 *         <li>userTel: 사용자 전화번호</li>
 *         <li>userAddress: 사용자 주소</li>
 *         <li>userEmail: 사용자 이메일</li>
 *         <li>userLeftSsn: 사용자 주민등록번호 앞자리</li>
 *         <li>userRightSsn: 사용자 주민등록번호 뒷자리</li>
 *         <li>userState: 사용자 상태</li>
 *         <li>userLV: 사용자 레벨</li>
 *         <li>userRegdate: 사용자 등록일자</li>
 *         <li>LimitStorage: 제한 저장소</li>
 *         <li>numBooks: 동화책 수</li>
 *     </ul>
 * </ul>
 * <p>
 * DTO 클래스는 데이터베이스와 직접 상호작용하지 않으며, 주로 서비스 계층과 컨트롤러 계층 사이에서 데이터를 전달하는 역할을 합니다.
 * 이를 통해 도메인 모델의 복잡성을 숨기고, 계층 간의 의존성을 낮출 수 있습니다.
 * <p>
 * DTO 클래스는 lombok 라이브러리의 {@link lombok.Data @Data} 어노테이션을 사용하여 게터(getter), 세터(setter), toString 등의 메서드를 자동으로 생성합니다.
 * 이를 통해 보일러플레이트 코드를 줄이고 코드의 가독성을 높일 수 있습니다.
 */
package com.jakka.model.dto.user;