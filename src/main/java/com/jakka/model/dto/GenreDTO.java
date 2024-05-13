package com.jakka.model.dto;

import lombok.Data;

@Data
public class GenreDTO {
	
	private String genreSeq;
	private String genreName;
	public String getGenreSeq() {
		return genreSeq;
	}
	public void setGenreSeq(String genreSeq) {
		this.genreSeq = genreSeq;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	
	
}
