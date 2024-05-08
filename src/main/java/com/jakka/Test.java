package com.jakka;

import java.util.ArrayList;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

public class Test {
	
	public static void main(String[] args) {
		
		UserDAO dao = DAOManager.getUserDAO();
		
		ArrayList<UserDTO> list = dao.findAll();
		
		for(UserDTO dto : list) {
			
			String userId = dto.getUserId();
			dao.createUserFolder(userId);
			
		}
				
	
    }
}

