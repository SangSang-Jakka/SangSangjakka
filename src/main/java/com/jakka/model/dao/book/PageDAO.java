package com.jakka.model.dao.book;

import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.book.PageDTO;

public interface PageDAO extends BasicDAO<PageDTO>{
	
	HashMap<Integer, PageDTO> findPages(String bookSeq);
	
	public PageDTO findById(String pageSeq, String bookSeq);
	public PageDTO lastpage(String bookSeq);
	public int del(String pageSeq, String bookSeq);
	
}
