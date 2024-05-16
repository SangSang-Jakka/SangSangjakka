/**
* <p>com.jakka.controller.board.bookshare 패키지는 동화책 공유 게시판과 관련된 컨트롤러들을 포함하고 있습니다.</p>
*
* <p>이 패키지의 주요 기능은 다음과 같습니다:</p>
*
* <ul>
*     <li>동화책 목록 조회 (페이징, 검색)</li>
*     <li>동화책 상세 보기 (조회수 증가, 좋아요/스크랩/신고 기능)</li>
*     <li>동화책 리뷰 작성/수정/삭제</li>
*     <li>리뷰 좋아요 기능</li>
* </ul>
*
* <p>이 패키지의 컨트롤러는 다음과 같습니다:</p>
*
* <ul>
*     <li>{@link BookList}: 동화책 목록 조회</li>
*     <li>{@link BookView}: 동화책 상세 보기 및 리뷰 작성</li>
*     <li>{@link BookLike}: 동화책 좋아요 기능</li>
*     <li>{@link BookScrap}: 동화책 스크랩 기능</li>
*     <li>{@link BookReport}: 동화책 신고 기능</li>
*     <li>{@link ReviewLike}: 리뷰 좋아요 기능</li>
*     <li>{@link BookEdit}: 리뷰 수정 기능</li>
*     <li>{@link BookDel}: 리뷰 삭제 기능</li>
*     <li>{@link BookAdd}: 동화책 추가 페이지</li>
* </ul>
*
* @version 1.0
* @see com.jakka.model.dao.book.BookDAO
* @see com.jakka.model.dao.book.ReviewDAO
* @see com.jakka.model.dto.book.BookDTO
* @see com.jakka.model.dto.book.ReviewDTO
*/
package com.jakka.controller.board.bookshare;