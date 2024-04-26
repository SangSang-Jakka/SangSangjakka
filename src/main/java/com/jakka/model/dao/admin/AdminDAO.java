package com.jakka.model.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public int add(HashMap<String, String> map) {
		
		final String SQL = "insert into tblAdmin (adId, adPw, adName, adNick, adAddress, adTel, adLv) values (?, default, ?, ?, ?, ?, default)";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			) {
			
			pstat.setString(1, map.get("adId"));
			pstat.setString(2, map.get("adName"));
			pstat.setString(3, map.get("adNick"));
			pstat.setString(4, map.get("adAddress"));
			pstat.setString(5, map.get("adTel"));
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| add");
			e.printStackTrace();
		}
		
		return 0;
		
	}//add()
	
	//관리자 비밀번호 변경
	public int setPw(String adId, String adPw) {
		
	 	final String sql = "update tblAdmin set adPw = ? where adId = ?";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			) {
			
			pstat.setString(1, adPw);
			pstat.setString(2, adId);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| setPw");
			e.printStackTrace();
		}
		
		return 0;
	}//setPw()
	
	
	//관리자 닉네임 변경
	public int setNick(String adId, String adNick) {
		
	 	final String sql = "update tblAdmin set adNick = ? where adId = ?";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			) {
			
			pstat.setString(1, adNick);
			pstat.setString(2, adId);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| setPw");
			e.printStackTrace();
		}
		
		return 0;
	}//setPw()
	
	//관리자 주소 변경
	public int setAddress(String adId, String adAddress) {
		
	 	final String sql = "update tblAdmin set adAddress = ? where adId = ?";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			) {
			
			pstat.setString(1, adAddress);
			pstat.setString(2, adId);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| setPw");
			e.printStackTrace();
		}
		
		return 0;
	}//setPw()

	//관리자 전화번호 변경
	public int setTel(String adId, String adTel) {
		
	 	final String sql = "update tblAdmin set adTel = ? where adId = ?";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			) {
			
			pstat.setString(1, adTel);
			pstat.setString(2, adId);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| setPw");
			e.printStackTrace();
		}
		
		return 0;
	}//setPw()
	
	
	
	
}//End of class
