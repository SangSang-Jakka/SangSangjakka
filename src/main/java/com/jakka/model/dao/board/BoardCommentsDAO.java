package com.jakka.model.dao.board;

import java.util.ArrayList;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Comments;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.dto.board.BoardDTO;

public interface BoardCommentsDAO extends BasicDAO<BoardCommentDTO>, ActiveStatus<BoardCommentDTO>
										, ReportCnt, Comments<BoardCommentDTO>{


	   public ArrayList<BoardCommentDTO> findAllChild(String parentBoardSeq);
	    public ArrayList<BoardCommentDTO> findAllReport();
	    public ArrayList<BoardCommentDTO> findAllNoReport();


	    public int add(BoardCommentDTO dto);
		public int del(BoardCommentDTO cmntDto);
		public ArrayList<BoardCommentDTO> findByNick(String userNick);
}
