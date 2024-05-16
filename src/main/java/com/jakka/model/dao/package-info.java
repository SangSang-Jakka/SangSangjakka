/**
 * com.jakka.model.dao 패키지는 데이터 액세스 객체(DAO)와 관련된 인터페이스와 클래스를 포함합니다.
 * 
 * <p>이 패키지는 다음과 같은 인터페이스를 제공합니다:</p>
 * <ul>
 *   <li>{@link com.jakka.model.dao.Search Search}: 등록일자, 제목, 내용, 닉네임을 기준으로 검색 기능을 제공합니다.</li>
 *   <li>{@link com.jakka.model.dao.ScrapCnt ScrapCnt}: 스크랩 기능을 제공합니다.</li>
 *   <li>{@link com.jakka.model.dao.ReportCnt ReportCnt}: 신고 기능을 제공합니다.</li>
 *   <li>{@link com.jakka.model.dao.LikeCnt LikeCnt}: 좋아요 기능을 제공합니다.</li>
 *   <li>{@link com.jakka.model.dao.Comments Comments}: 댓글 기능을 제공합니다.</li>
 *   <li>{@link com.jakka.model.dao.Cnt Cnt}: 조회수 기능을 제공합니다.</li>
 *   <li>{@link com.jakka.model.dao.ActiveStatus ActiveStatus}: 활성화/비활성화 기능을 제공합니다.</li>
 *   <li>{@link com.jakka.model.dao.BasicDAO BasicDAO}: 기본적인 CRUD 작업을 정의합니다.</li>
 * </ul>
 * 
 * <p>주요 클래스:</p>
 * <ul>
 *   <li>{@link com.jakka.model.dao.GenreDAO GenreDAO}: 장르 관련 기능을 구현한 DAO 클래스입니다.</li>
 * </ul>
 * 
 * @since 1.0
 */
package com.jakka.model.dao;