package com.jakka.model.dto.board;

import lombok.Data;

@Data
public class SuggestionDTO {

	private String sgstSeq;
	private String sgstTitle;
	private String sgstContents;
	private String sgstRegdate;
	private String sgstSecretYN;
	private String userSeq;
	private String sgstCnt;
	
}//End of class
