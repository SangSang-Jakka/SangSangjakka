package com.jakka.model.dao.admin;

import java.util.ArrayList;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.admin.AdminDTO;

public interface AdminDAO extends BasicDAO<AdminDTO>{
	
	public int savePw(AdminDTO dto);
	public AdminDTO login(AdminDTO dto);
	public boolean isValid(AdminDTO dto);
	public ArrayList<AdminDTO> log(String id);
	
	
	
}
