package com.jakka.model.dao.admin;

import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.admin.AdminDTO;

public interface AdminDAO extends BasicDAO<AdminDTO>{
	
	public int savePw(AdminDTO dto);
	
}
