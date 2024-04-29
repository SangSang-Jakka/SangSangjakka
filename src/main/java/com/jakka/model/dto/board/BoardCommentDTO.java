package com.jakka.model.dto.board;

import lombok.Data;

@Data
public class BoardCommentDTO {

	private String cmntSeq;
	private String userSeq;
	private String boardSeq;
	private String cmntContents;
	private String cmntReportCnt;
	private String cmntRegdate;
	
}//End of class
