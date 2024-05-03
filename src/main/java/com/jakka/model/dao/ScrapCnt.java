package com.jakka.model.dao;

public interface ScrapCnt {
	
	public int addScrapCnt(String bookSeq, String userSeq);
	public boolean isScrap(String bookSeq, String userSeq);
	
}//End of interface
