package com.jakka.model.dao.board;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Comments;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.board.BoardCommentDTO;

public interface BoardCommentsDAO extends BasicDAO<BoardCommentDTO>, ActiveStatus<BoardCommentDTO>
										, ReportCnt, Comments<BoardCommentDTO>{

}
