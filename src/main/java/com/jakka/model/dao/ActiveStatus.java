package com.jakka.model.dao;

import java.util.ArrayList;

public interface ActiveStatus<T> {

	public int activation(String seq, String adId);
	public int disable(String seq, String adId);
	
	public ArrayList<T> findAllWhite();
	public ArrayList<T> findAllBlack();
	
}//End of interface
