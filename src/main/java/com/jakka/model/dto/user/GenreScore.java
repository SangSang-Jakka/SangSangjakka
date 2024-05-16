package com.jakka.model.dto.user;

import lombok.Data;

/**
* 장르 점수 정보를 저장하는 DTO 클래스입니다.
*/
@Data
public class GenreScore {

	private double score; // 장르 점수
	private String genreName; // 장르 이름
	private String genreSeq; // 장르 시퀀스
	
}
