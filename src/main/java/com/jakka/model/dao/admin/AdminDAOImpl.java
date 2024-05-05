package com.jakka.model.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.admin.AdminDTO;

public class AdminDAOImpl implements AdminDAO{
	
	private final static AdminDAOImpl instance = new AdminDAOImpl();
	
	private AdminDAOImpl() {
		//외부 생성 방지
	}
	
	public static AdminDAO getInstance() {
		
		return instance;
		
	}//getInstance()

	//전체 관리자 리스트(루트관리자 제외)
	public ArrayList<AdminDTO> findAll() {
		
		final String SQL = "select * from tblAdmin where adLv = 2";
		
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
	public AdminDTO findById(String Id) {
		
		final String SQL = "select * from tblAdmin where adId = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, Id);
			
			ResultSet rs = pstat.executeQuery();
			
			
			if (rs.next()) {
				
				AdminDTO dto = new AdminDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setAdPw(rs.getString("adPw"));
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
	public int savePw(AdminDTO dto) {
		
	 	final String SQL = "update tblAdmin set adPw = ? where adId = ?";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
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
	public int save(AdminDTO dto) {
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
	
	
	// 아이디, 비밀번호 유효 여부 리턴
	public boolean isValid(AdminDTO dto) {
		
		// 맞는 정보인지 여부를 담을 지역 변수 선언하고 초기값 false 넣어주기
		boolean isValid = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			String sql = "select * from tblAdmin where adId = ? and adPw = ?";
			pstat = conn.prepareStatement(sql);
			
			// ? 에 값 바인딩
			pstat.setString(1, dto.getAdId());
			pstat.setString(2, dto.getAdPw());
			// query 문 수행하고 결과 받기
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				// 만약 select 된 row가 있으면
				isValid=true;
			}
			
		} catch (Exception e) {
			System.out.println("AdminDAOImpl.isValid");
			e.printStackTrace();
		}
		
		return isValid;
		
	}
	
	
	// 관리자 로그인
	public AdminDTO login(AdminDTO dto) {
		
		final String SQL = "select * from tblAdmin where adId = ? and adPw = ?";
		
		try(Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL)) {
		
			pstat.setString(1, dto.getAdId());
			pstat.setString(2, dto.getAdPw());
			
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				AdminDTO result = new AdminDTO();
				
				result.setAdName(rs.getString("adName"));
				result.setAdLv(rs.getString("adLv"));		
				result.setAdTel(rs.getString("adTel"));
				result.setAdAddress(rs.getString("adAddress"));
				result.setAdNick(rs.getString("adNick"));
		
				return result;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| login");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
}//End of class
