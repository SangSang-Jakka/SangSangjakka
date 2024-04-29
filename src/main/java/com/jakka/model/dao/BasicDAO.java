package com.jakka.model.dao;

import java.util.ArrayList;

public interface BasicDAO<T> {

	public ArrayList<T> listAll ();
	public int add(T dto);
	public int set(T dto);
	public T get(String seq);
	
}//End of interface
