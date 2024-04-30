package com.jakka.model.dao;

import java.util.ArrayList;

public interface Comments<T> {

	public ArrayList<T> findChild(String parentSeq);
	
}//End of interface
