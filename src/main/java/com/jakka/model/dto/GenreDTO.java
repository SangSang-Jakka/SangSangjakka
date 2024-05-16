package com.jakka.model.dto;

import lombok.Data;

/**
* GenreDTO 클래스는 동화책의 장르 정보를 저장하기 위한 DTO(Data Transfer Object) 클래스입니다.
*/
@Data
public class GenreDTO {
	
	private String genreSeq; // 장르 시퀀스
	private String genreName; // 장르 이름
}
