package com.jakka;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

public class Test {
	
	public static void main(String[] args) {
		
		UserDAO dao = DAOManager.getUserDAO();
		
		UserDTO dto = new UserDTO();
		dto.setUserId("user0001");
		dto.setUserPw("1111");
		
		UserDTO result = dao.login(dto);
		
		System.out.println(result);
				
	
    }
}

