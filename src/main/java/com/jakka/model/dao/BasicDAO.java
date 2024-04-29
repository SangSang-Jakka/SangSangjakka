package com.jakka.model.dao;

import java.util.ArrayList;

public interface BasicDAO<T> {

	
	public ArrayList<T> findAll();
	public T findById(String id);
	public int add(T dto);
	public int save(T dto);
	
}//End of interface
