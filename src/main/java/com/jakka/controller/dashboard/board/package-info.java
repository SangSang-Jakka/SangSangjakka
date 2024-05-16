/**
* <p>com.jakka.controller.dashboard.board 패키지는 게시판 대시보드와 관련된 컨트롤러들을 포함하고 있습니다.</p>
*
* <p>이 패키지의 주요 기능은 다음과 같습니다:</p>
*
* <ul>
*     <li>공지사항 관리 (등록, 수정, 삭제, 상세 조회, 고정/고정 해제)</li>
*     <li>자유 게시판 관리 (목록 조회, 상세 조회, 게시글 및 댓글 비활성화/활성화, 게시글 수정)</li>
*     <li>건의사항 관리 (목록 조회, 상세 조회, 답변 작성, 답변 수정/삭제)</li>
*     <li>리뷰 관리 (목록 조회, 비활성화/활성화)</li>
*     <li>댓글 관리 (목록 조회, 비활성화/활성화)</li>
*     <li>동화책 공유 관리 (목록 조회, 상세 조회, 리뷰 비활성화/활성화)</li>
*     <li>게시판 통계 정보 제공 (오늘 작성된 게시글, 전체 댓글 목록)</li>
* </ul>
*
* <p>이 패키지의 각 컨트롤러는 특정 기능을 담당하며, 게시판 관련 비즈니스 로직을 처리합니다.</p>
*
* <p>주요 컨트롤러는 다음과 같습니다:</p>
*
* <ul>
*     <li>NoticeManagement: 공지사항 관리</li>
*     <li>NoticeManagementView: 공지사항 상세 조회 및 고정/고정 해제</li>
*     <li>NoticeManagementEdit: 공지사항 수정</li>
*     <li>NoticeManagementDel: 공지사항 삭제</li>
*     <li>FreeboardManagement: 자유 게시판 관리</li>
*     <li>FreeboardManagementView: 자유 게시글 상세 조회 및 댓글 관리</li>
*     <li>FreeboardManagementEdit: 자유 게시글 수정</li>
*     <li>FreeboardManagementDel: 자유 게시글 비활성화/활성화</li>
*     <li>SuggestionManagement: 건의사항 관리</li>
*     <li>SuggestionManagementView: 건의사항 상세 조회 및 답변 작성</li>
*     <li>SuggestionAnswerManagementEdit: 건의사항 답변 수정</li>
*     <li>SuggestionAnswerManagementDel: 건의사항 답변 삭제</li>
*     <li>ReviewManagement: 리뷰 관리</li>
*     <li>CommentManagement: 댓글 관리</li>
*     <li>BookShareManagement: 동화책 공유 관리</li>
*     <li>BookShareManagementView: 동화책 상세 조회 및 리뷰 비활성화/활성화</li>
*     <li>BoardStats: 게시판 통계 정보 제공</li>
* </ul>
*
* @version 1.0
*/
package com.jakka.controller.dashboard.board;