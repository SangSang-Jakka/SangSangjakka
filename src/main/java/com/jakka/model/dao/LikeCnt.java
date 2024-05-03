package com.jakka.model.dao;

public interface LikeCnt {
	
	public int addLikeCnt(String bookSeq, String userSeq);
	public boolean isLike(String seq, String userSeq);
	
}
