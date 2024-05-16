/**
 * 이 패키지는 게시판 관련 데이터 액세스 객체(DAO) 인터페이스와 구현 클래스를 포함합니다.
 * DAO는 데이터베이스와의 상호 작용을 추상화하며, 데이터 영속성 및 비즈니스 로직과 데이터베이스 액세스를 분리하는 역할을 합니다.
 * <p>
 * 주요 인터페이스:
 * <ul>
 *     <li>{@link com.jakka.model.dao.board.BoardDAO BoardDAO 인터페이스}: 게시판 관련 데이터 액세스 작업을 위한 메서드를 정의합니다.</li>
 *     <ul>
 *         <li>게시글 정보 조회, 저장, 수정, 삭제 등의 기본적인 CRUD 작업을 위한 메서드를 제공합니다.</li>
 *         <li>게시글 검색, 필터링, 정렬 등의 기능을 위한 메서드를 제공합니다.</li>
 *         <li>게시글에 대한 신고, 활성화/비활성화 등의 관리 기능을 위한 메서드를 제공합니다.</li>
 *         <li>게시글 관련 통계 정보 조회를 위한 메서드를 제공합니다.</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dao.board.BoardCommentsDAO BoardCommentsDAO 인터페이스}: 게시판 댓글 관련 데이터 액세스 작업을 위한 메서드를 정의합니다.</li>
 *     <ul>
 *         <li>게시글 댓글 정보 조회, 저장, 수정, 삭제 등의 기본적인 CRUD 작업을 위한 메서드를 제공합니다.</li>
 *         <li>댓글에 대한 신고, 활성화/비활성화 등의 관리 기능을 위한 메서드를 제공합니다.</li>
 *         <li>특정 게시글의 댓글 목록 조회를 위한 메서드를 제공합니다.</li>
 *         <li>닉네임을 기준으로 댓글 목록을 조회하는 메서드를 제공합니다.</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dao.board.NoticeDAO NoticeDAO 인터페이스}: 공지사항 관련 데이터 액세스 작업을 위한 메서드를 정의합니다.</li>
 *     <ul>
 *         <li>공지사항 정보 조회, 저장, 수정, 삭제 등의 기본적인 CRUD 작업을 위한 메서드를 제공합니다.</li>
 *         <li>공지사항 검색, 필터링, 정렬 등의 기능을 위한 메서드를 제공합니다.</li>
 *         <li>공지사항 고정, 삭제 등의 관리 기능을 위한 메서드를 제공합니다.</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dao.board.SuggestionDAO SuggestionDAO 인터페이스}: 건의사항 관련 데이터 액세스 작업을 위한 메서드를 정의합니다.</li>
 *     <ul>
 *         <li>건의사항 정보 조회, 저장, 수정, 삭제 등의 기본적인 CRUD 작업을 위한 메서드를 제공합니다.</li>
 *         <li>건의사항 검색, 필터링, 정렬 등의 기능을 위한 메서드를 제공합니다.</li>
 *         <li>건의사항 삭제, 공개/비공개 설정 등의 관리 기능을 위한 메서드를 제공합니다.</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dao.board.SuggestionAnswerDAO SuggestionAnswerDAO 인터페이스}: 건의사항 답변 관련 데이터 액세스 작업을 위한 메서드를 정의합니다.</li>
 *     <ul>
 *         <li>건의사항 답변 정보 조회, 저장, 수정, 삭제 등의 기본적인 CRUD 작업을 위한 메서드를 제공합니다.</li>
 *         <li>특정 건의사항의 답변 목록 조회를 위한 메서드를 제공합니다.</li>
 *         <li>답변 삭제 등의 관리 기능을 위한 메서드를 제공합니다.</li>
 *     </ul>
 * </ul>
 * <p>
 * 주요 구현 클래스:
 * <ul>
 *     <li>{@link com.jakka.model.dao.board.BoardDAOImpl BoardDAOImpl 클래스}: BoardDAO 인터페이스를 구현한 클래스입니다.</li>
 *     <li>{@link com.jakka.model.dao.board.BoardCommentsDAOImpl BoardCommentsDAOImpl 클래스}: BoardCommentsDAO 인터페이스를 구현한 클래스입니다.</li>
 *     <li>{@link com.jakka.model.dao.board.NoticeDAOImpl NoticeDAOImpl 클래스}: NoticeDAO 인터페이스를 구현한 클래스입니다.</li>
 *     <li>{@link com.jakka.model.dao.board.SuggestionDAOImpl SuggestionDAOImpl 클래스}: SuggestionDAO 인터페이스를 구현한 클래스입니다.</li>
 *     <li>{@link com.jakka.model.dao.board.SuggestionAnswerDAOImpl SuggestionAnswerDAOImpl 클래스}: SuggestionAnswerDAO 인터페이스를 구현한 클래스입니다.</li>
 * </ul>
 * <p>
 * 이 패키지의 DAO 클래스들은 데이터베이스와의 상호 작용을 담당하며, 서비스 계층과 데이터베이스 사이의 중간 계층 역할을 합니다.
 * 서비스 클래스는 DAO를 사용하여 필요한 데이터를 조회하거나 변경하며, 비즈니스 로직을 구현합니다.
 * DAO는 데이터베이스와의 연결, 쿼리 실행, 결과 반환 등의 작업을 수행하며, 서비스 클래스는 이를 활용하여 애플리케이션의 기능을 구현합니다.
 */
package com.jakka.model.dao.board;