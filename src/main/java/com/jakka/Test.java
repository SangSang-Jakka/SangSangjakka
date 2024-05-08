package com.jakka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.jakka.model.DAOManager;
import com.jakka.model.DBUtil;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;

public class Test {
	
	public static void main(String[] args) {
		
		UserDAO dao = DAOManager.getUserDAO();
		
		ArrayList<UserDTO> list = dao.findAll();
		
		for(UserDTO dto : list) {
			
			String userId = dto.getUserId();
			String userSeq = dto.getUserSeq();
			//dao.createUserFolder(userId);
			
			String sql = "insert into tblUserGenrePreference(genreSeq, userSeq, genreCnt)"
					+ "values(?, ?, default)";
			
			try (
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(sql);
				){
				
				pstat.setString(2, userSeq);
				
				for(int i = 1; i <= 10; i++) {
					pstat.setString(1, i + "");
					pstat.executeUpdate();
					
				}
				
				
			} catch (Exception e) {
				System.out.println("Test.| main");
				e.printStackTrace();
			}
			
			
		}
				
	
    }
}

