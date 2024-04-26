package com.jakka.model.dto.board;

import lombok.Data;

@Data
public class BoardDTO {
	
	private String boardSeq;
	private String boardTitle;
	private String boardContents;
	private String boardRegdate;
	private String boardReportCnt;
	private String boardCnt;
	private String userSeq;
	
}//End of class
