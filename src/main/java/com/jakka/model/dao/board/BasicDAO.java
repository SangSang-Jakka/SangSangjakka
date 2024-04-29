package com.jakka.model.dao.board;

import java.util.ArrayList;

public interface BasicDAO<T> {

	public ArrayList<T> list ();
	public int add(T dto);
	public int set(T dto);
	public T get(String seq);
	
}//End of interface
