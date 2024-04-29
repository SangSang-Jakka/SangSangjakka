package com.jakka.model.dao.book;

import java.util.ArrayList;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.book.PageDTO;

public class PageDAO implements BasicDAO<PageDTO>{

	private final static PageDAO DAO = new PageDAO();
	
	private PageDAO() {
		//외부 생성 방지
	}
	
	public static PageDAO getInstance() {
		
		return DAO;
		
	}//getInstance()
	
	@Override
	public int add(PageDTO dto) {
		
		
		
		return 0;
	}
	
	@Override
	public PageDTO get(String seq) {
		
		
		
		return null;
	}
	
	@Override
	public ArrayList<PageDTO> listAll() {
		return null;
	}
	
	@Override
	public int set(PageDTO dto) {
		return 0;
	}
	
}//End of class
