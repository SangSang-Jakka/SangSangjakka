package com.jakka.model.dao;

import java.util.ArrayList;

public interface Search<T> {

	public ArrayList<T> findByRegdateBefore(String date);
	public ArrayList<T> findByRegdateAfter(String date);
	public ArrayList<T> findByRegdateBetween(String startDate, String endDate);
	public ArrayList<T> findByTitleContains(String word);
	public ArrayList<T> findByContentsContains(String word);
	public ArrayList<T> findByNick(String Nick);
	
	
}//End of interface
 