package com.jakka.model.dao;

import java.util.ArrayList;

public interface ActiveStatus<T> {

	public int activation(String seq);
	public int disable(String seq);
	
	public ArrayList<T> findAllWhite();
	public ArrayList<T> findAllBlack();
	
}//End of interface
