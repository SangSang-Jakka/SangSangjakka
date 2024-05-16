/**
* <p>com.jakka.controller.board.freeboard 패키지는 자유 게시판과 관련된 컨트롤러들을 포함하고 있습니다.</p>
*
* <p>이 패키지의 주요 기능은 다음과 같습니다:</p>
*
* <ul>
*     <li>자유 게시글 목록 조회 (페이징, 검색, 정렬)</li>
*     <li>자유 게시글 상세 보기 (조회수 증가, 댓글 작성/수정/삭제)</li>
*     <li>자유 게시글 작성</li>
*     <li>자유 게시글 수정</li>
*     <li>자유 게시글 삭제</li>
* </ul>
*
* <p>이 패키지의 컨트롤러는 다음과 같습니다:</p>
*
* <ul>
*     <li>{@link FreeboardList}: 자유 게시글 목록 조회</li>
*     <li>{@link FreeboardView}: 자유 게시글 상세 보기 및 댓글 작성</li>
*     <li>{@link FreeboardAdd}: 자유 게시글 작성</li>
*     <li>{@link FreeboardEdit}: 자유 게시글 수정</li>
*     <li>{@link FreeboardDel}: 자유 게시글 삭제</li>
*     <li>{@link FreeboardEditComment}: 댓글 수정</li>
*     <li>{@link FreeboardDelComment}: 댓글 삭제</li>
* </ul>
*
* @version 1.0
* @see com.jakka.model.dao.board.BoardDAO
* @see com.jakka.model.dao.board.BoardCommentsDAO
* @see com.jakka.model.dto.board.BoardDTO
* @see com.jakka.model.dto.board.BoardCommentDTO
*/
package com.jakka.controller.board.freeboard;