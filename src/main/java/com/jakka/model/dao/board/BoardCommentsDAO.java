package com.jakka.model.dao.board;

import java.util.ArrayList;
import java.util.List;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Comments;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;

public interface BoardCommentsDAO extends BasicDAO<BoardCommentDTO>, ActiveStatus<BoardCommentDTO>
										, ReportCnt, Comments<BoardCommentDTO>{

		/**
	    * 게시글 시퀀스로 해당 게시글의 댓글 목록을 조회합니다.
	    *
	    * @param parentBoardSeq 게시글 시퀀스
	    * @return 댓글 목록
	    */
	   public ArrayList<BoardCommentDTO> findAllChild(String parentBoardSeq);

	   /**
	    * 신고된 댓글 목록을 조회합니다.
	    *
	    * @return 신고된 댓글 목록
	    */
	   public ArrayList<BoardCommentDTO> findAllReport();

	   /**
	    * 신고되지 않은 댓글 목록을 조회합니다.
	    *
	    * @return 신고되지 않은 댓글 목록
	    */
	   public ArrayList<BoardCommentDTO> findAllNoReport();

	   /**
	    * 닉네임으로 댓글 목록을 조회합니다.
	    *
	    * @param userNick 닉네임
	    * @return 댓글 목록
	    */
	   public ArrayList<BoardCommentDTO> findByNick(String userNick);

	   /**
	    * 새로운 댓글을 등록합니다.
	    *
	    * @param dto 댓글 DTO
	    * @return 등록 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int add(BoardCommentDTO dto);

	   /**
	    * 댓글을 삭제합니다.
	    *
	    * @param cmntDto 댓글 DTO
	    * @return 삭제 성공 여부 (1: 성공, 0: 실패)
	    */
	   public int del(BoardCommentDTO cmntDto);

}
