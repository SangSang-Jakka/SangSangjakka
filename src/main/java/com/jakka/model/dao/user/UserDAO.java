package com.jakka.model.dao.user;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.user.UserDTO;

public interface UserDAO extends BasicDAO<UserDTO>, ActiveStatus<UserDTO>{
	
	public int savePw(UserDTO dto);
	public String findSeq(String id);
	public int saveStorage(UserDTO dto, String adId);
	public void createUserFolder(String userId);
	
}
