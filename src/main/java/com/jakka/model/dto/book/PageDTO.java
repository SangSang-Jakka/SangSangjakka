package com.jakka.model.dto.book;

import lombok.Data;

@Data
public class PageDTO {

	private String pageSeq;
	private String bookSeq;
	private String pageUrl;
	private String pageContents;
	private String cmntYN;
	private String imgYN;
	
}//End of class
