/**
 * 이 패키지는 게시판 관련 데이터 전송 객체(DTO) 클래스를 포함합니다.
 * DTO 클래스는 계층 간 데이터 전송을 위해 사용되며, 도메인 모델과 프레젠테이션 계층 간의 데이터 전송을 캡슐화합니다.
 * <p>
 * 주요 DTO 클래스:
 * <ul>
 *     <li>{@link com.jakka.model.dto.board.BoardCommentDTO BoardCommentDTO}: 게시판 댓글 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>cmntSeq: 댓글 시퀀스</li>
 *         <li>userSeq: 사용자 시퀀스</li>
 *         <li>boardSeq: 게시판 시퀀스</li>
 *         <li>cmntContents: 댓글 내용</li>
 *         <li>cmntReportCnt: 댓글 신고 수</li>
 *         <li>cmntRegdate: 댓글 등록일자</li>
 *         <li>userNick: 사용자 닉네임</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dto.board.BoardDTO BoardDTO}: 게시판 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>boardSeq: 게시판 시퀀스</li>
 *         <li>boardTitle: 게시판 제목</li>
 *         <li>boardContents: 게시판 내용</li>
 *         <li>boardRegdate: 게시판 등록일자</li>
 *         <li>boardReportCnt: 게시판 신고 수</li>
 *         <li>boardCnt: 게시판 수</li>
 *         <li>userSeq: 사용자 시퀀스</li>
 *         <li>userNick: 사용자 닉네임</li>
 *         <li>cmntCnt: 댓글 수</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dto.board.NoticeDTO NoticeDTO}: 공지사항 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>noticeSeq: 공지사항 시퀀스</li>
 *         <li>noticeTitle: 공지사항 제목</li>
 *         <li>noticeContents: 공지사항 내용</li>
 *         <li>noticeRegdate: 공지사항 등록일자</li>
 *         <li>noticeCnt: 공지사항 수</li>
 *         <li>adId: 관리자 ID</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dto.board.SuggestionAnswerDTO SuggestionAnswerDTO}: 건의사항 답변 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>answSeq: 답변 시퀀스</li>
 *         <li>adId: 관리자 ID</li>
 *         <li>sgstSeq: 건의사항 시퀀스</li>
 *         <li>sgstAnsw: 건의사항 답변</li>
 *         <li>sgstRegdate: 건의사항 답변 등록일자</li>
 *     </ul>
 *     <li>{@link com.jakka.model.dto.board.SuggestionDTO SuggestionDTO}: 건의사항 정보를 저장하는 DTO 클래스</li>
 *     <ul>
 *         <li>sgstSeq: 건의사항 시퀀스</li>
 *         <li>sgstTitle: 건의사항 제목</li>
 *         <li>sgstContents: 건의사항 내용</li>
 *         <li>sgstRegdate: 건의사항 등록일자</li>
 *         <li>sgstSecretYN: 건의사항 비밀 여부</li>
 *         <li>userSeq: 사용자 시퀀스</li>
 *         <li>sgstCnt: 건의사항 수</li>
 *         <li>userNick: 사용자 닉네임</li>
 *         <li>attach: 파일</li>
 *     </ul>
 * </ul>
 * <p>
 * DTO 클래스는 데이터베이스와 직접 상호작용하지 않으며, 주로 서비스 계층과 컨트롤러 계층 사이에서 데이터를 전달하는 역할을 합니다.
 * 이를 통해 도메인 모델의 복잡성을 숨기고, 계층 간의 의존성을 낮출 수 있습니다.
 * <p>
 * DTO 클래스는 lombok 라이브러리의 {@link lombok.Data @Data} 어노테이션을 사용하여 게터(getter), 세터(setter), toString 등의 메서드를 자동으로 생성합니다.
 * 이를 통해 보일러플레이트 코드를 줄이고 코드의 가독성을 높일 수 있습니다.
 */
package com.jakka.model.dto.board;