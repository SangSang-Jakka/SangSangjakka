package com.jakka.model.dao.user;

import java.util.Map;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.user.UserDTO;

public interface UserDAO extends BasicDAO<UserDTO>, ActiveStatus<UserDTO>{
	
	public int savePw(UserDTO dto);
	public String findSeq(String id);
	public int saveStorage(UserDTO dto, String adId);
	public void createUserFolder(String userId);
	public UserDTO login(UserDTO dto);
	public UserDTO findId(UserDTO dto);
	public UserDTO findPw(UserDTO dto);
	public boolean unRegister(UserDTO dto);
	public int userCnt(String userRegdate);
	public Map<String, Integer> userGender();
	
	
}
