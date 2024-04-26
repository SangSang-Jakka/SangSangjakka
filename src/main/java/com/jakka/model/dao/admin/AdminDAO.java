package com.jakka.model.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.admin.AdminDTO;

public class AdminDAO {

	//전체 관리자 리스트
	public ArrayList<AdminDTO> list() {
		
		final String SQL = "select * from tblAdmin";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<AdminDTO> list = new ArrayList<>();
			
			 for (ResultSet row = rs; row.next(); ) {
				
				AdminDTO dto = new AdminDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setAdName(rs.getString("adName"));
				dto.setAdNick(rs.getString("adNick"));
				dto.setAdAddress(rs.getString("adAddress"));
				dto.setAdTel(rs.getString("adTel"));
				dto.setAdLv(rs.getString("adLv"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| list");
			e.printStackTrace();
		}
		
		
		return null;
	}//list()
	
	//관리자 한명의 정보를 가져올때
	public AdminDTO get(String adId) {
		
		final String SQL = "select * from tblAdmin where adId = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, adId);
			
			ResultSet rs = pstat.executeQuery();
			
			
			if (pstat.executeQuery().next()) {
				
				AdminDTO dto = new AdminDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setAdName(rs.getString("adName"));
				dto.setAdAddress(rs.getString("adAddress"));
				dto.setAdLv(rs.getString("adLv"));
				dto.setAdTel(rs.getString("adTel"));
				dto.setAdNick(rs.getString("adNick"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| get");
			e.printStackTrace();
		}
		
		
		return null;
		
	}//get()
	
	//관리자 추가
	// 아이디, 이름, 닉네임, 주소, 전화번호를 입력
	public int add(AdminDTO dto) {
		
		final String SQL = "insert into tblAdmin (adId, adPw, adName, adNick, adAddress, adTel, adLv) values (?, default, ?, ?, ?, ?, default)";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			) {
			
			pstat.setString(1, dto.getAdId());
			pstat.setString(2, dto.getAdName());
			pstat.setString(3, dto.getAdNick());
			pstat.setString(4, dto.getAdAddress());
			pstat.setString(5, dto.getAdTel());
			
			int result = pstat.executeUpdate(); 
			
			return result;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| add");
			e.printStackTrace();
			
		}
		
		return 0;
		
	}//add()
	
	//관리자 비밀번호 변경
	public int setPw(AdminDTO dto) {
		
	 	final String sql = "update tblAdmin set adPw = ? where adId = ?";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			) {
			
			pstat.setString(1, dto.getAdPw());
			pstat.setString(2, dto.getAdId());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| setPw");
			e.printStackTrace();
		}
		
		return 0;
	}//setPw()
	
	//관리자 개인정보 수정(닉네임, 주소, 전화번호)
	public int set(AdminDTO dto) {
	    final String SQL = "UPDATE tblAdmin SET adNick = ?, adAddress = ?, adTel = ? WHERE adId = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL)) {

	        pstat.setString(1, dto.getAdNick());
	        pstat.setString(2, dto.getAdAddress());
	        pstat.setString(3, dto.getAdTel());
	        pstat.setString(4, dto.getAdId());

	        int result = pstat.executeUpdate();
	        
	        return result;
	    } catch (Exception e) {
	        System.out.println("AdminDAO.| set");
	        e.printStackTrace();
	        
	    }
	    
	    return 0;
	    
	}//set()
	
	
}//End of class
