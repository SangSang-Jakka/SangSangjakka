/**
 * 이 패키지는 애플리케이션 전반에서 사용되는 데이터 전송 객체(DTO) 클래스를 포함합니다.
 * DTO 클래스는 계층 간 데이터 전송을 위해 사용되며, 도메인 모델과 프레젠테이션 계층 간의 데이터 전송을 캡슐화합니다.
 * <p>
 * 주요 DTO 클래스:
 * <ul>
 *     <li>{@link com.jakka.model.dto.GenreDTO GenreDTO}: 동화책의 장르 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>genreSeq: 장르 시퀀스</li>
 *         <li>genreName: 장르 이름</li>
 *     </ul>
 * </ul>
 * <p>
 * 이 패키지는 애플리케이션의 여러 도메인에서 공통으로 사용되는 DTO 클래스를 포함합니다.
 * 이러한 DTO 클래스는 도메인 간의 데이터 전송을 위해 사용되며, 데이터의 캡슐화와 계층 간의 느슨한 결합을 제공합니다.
 * <p>
 * DTO 클래스는 lombok 라이브러리의 {@link lombok.Data @Data} 어노테이션을 사용하여 게터(getter), 세터(setter), toString 등의 메서드를 자동으로 생성합니다.
 * 이를 통해 보일러플레이트 코드를 줄이고 코드의 가독성을 높일 수 있습니다.
 */
package com.jakka.model.dto;