/**
 * 이 패키지는 동화책 관련 데이터 전송 객체(DTO) 클래스를 포함합니다.
 * DTO 클래스는 계층 간 데이터 전송을 위해 사용되며, 도메인 모델과 프레젠테이션 계층 간의 데이터 전송을 캡슐화합니다.
 * <p>
 * 주요 DTO 클래스:
 * <ul>
 *     <li>{@link com.jakka.model.dto.book.BookDTO BookDTO}: 동화책 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>bookSeq: 동화책 시퀀스</li>
 *         <li>bookTitle: 동화책 제목</li>
 *         <li>bookInfo: 동화책 정보</li>
 *         <li>bookCover: 동화책 표지</li>
 *         <li>bookRegdate: 동화책 등록일자</li>
 *         <li>bookModDate: 동화책 수정일자</li>
 *         <li>likeCnt: 좋아요 수</li>
 *         <li>bookReviewCnt: 리뷰 수</li>
 *         <li>bookScrapCnt: 스크랩 수</li>
 *         <li>bookReportCnt: 신고 수</li>
 *         <li>userSeq: 사용자 시퀀스</li>
 *         <li>parentBookSeq: 부모 동화책 시퀀스</li>
 *         <li>rcmAgeSeq: 추천 연령 시퀀스</li>
 *         <li>userNick: 사용자 닉네임</li>
 *         <li>shareCnt: 공유 수</li>
 *         <li>bookCnt: 동화책 수</li>
 *         <li>awardRegdate: 수상 등록일자</li>
 *         <li>awardRank: 수상 등급</li>
 *         <li>reportDate: 신고 일자</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dto.book.PageDTO PageDTO}: 동화책 페이지 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>pageSeq: 페이지 시퀀스</li>
 *         <li>bookSeq: 동화책 시퀀스</li>
 *         <li>pageUrl: 페이지 URL</li>
 *         <li>pageContents: 페이지 내용</li>
 *         <li>cmntYN: 댓글 여부</li>
 *         <li>imgYN: 이미지 여부</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dto.book.ReviewDTO ReviewDTO}: 동화책 리뷰 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>reviewSeq: 리뷰 시퀀스</li>
 *         <li>reviewContents: 리뷰 내용</li>
 *         <li>reviewLikeCnt: 리뷰 좋아요 수</li>
 *         <li>reviewReportCnt: 리뷰 신고 수</li>
 *         <li>userSeq: 사용자 시퀀스</li>
 *         <li>bookSeq: 동화책 시퀀스</li>
 *         <li>reviewRegdate: 리뷰 등록일자</li>
 *         <li>userNick: 사용자 닉네임</li>
 *     </ul>
 * </ul>
 * <p>
 * DTO 클래스는 데이터베이스와 직접 상호작용하지 않으며, 주로 서비스 계층과 컨트롤러 계층 사이에서 데이터를 전달하는 역할을 합니다.
 * 이를 통해 도메인 모델의 복잡성을 숨기고, 계층 간의 의존성을 낮출 수 있습니다.
 * <p>
 * DTO 클래스는 lombok 라이브러리의 {@link lombok.Data @Data} 어노테이션을 사용하여 게터(getter), 세터(setter), toString 등의 메서드를 자동으로 생성합니다.
 * 이를 통해 보일러플레이트 코드를 줄이고 코드의 가독성을 높일 수 있습니다.
 */
package com.jakka.model.dto.book;