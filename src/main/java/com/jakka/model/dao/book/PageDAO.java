package com.jakka.model.dao.book;

import java.util.HashMap;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.book.PageDTO;

public interface PageDAO extends BasicDAO<PageDTO>{
	
	public HashMap<Integer, PageDTO> findPages(String bookSeq);
	
	public PageDTO findById(String pageSeq, String bookSeq);
	
}
