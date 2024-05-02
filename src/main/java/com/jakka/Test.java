package com.jakka;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

public class Test {
	
	public static void main(String[] args) {
	
		UserDAO userDAO = DAOManager.getUserDAO();
		
		UserDTO dto = userDAO.findById("jy1234");
		dto.setLimitStorage("21474836480");
		
		int result = userDAO.saveStorage(dto, "admin2463");
		
		System.out.println(result);
		
		
	}
	
	
}

