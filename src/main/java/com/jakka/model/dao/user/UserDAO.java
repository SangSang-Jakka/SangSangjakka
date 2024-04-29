package com.jakka.model.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.board.BasicDAO;
import com.jakka.model.dto.user.UserDTO;

public class UserDAO implements BasicDAO<UserDTO>{

	public final static UserDAO DAO = new UserDAO();
	
	private UserDAO() {
		//외부 생성 방지
	}
	
	public static UserDAO getInstance() {
		return DAO;
	}//getInstance()
	
	@Override
	public int add(UserDTO dto) {
		
		final String SQL = "INSERT INTO tblUser (userSeq, userId, userPw, userNick, userTel, userAddress, userEmail, userLeftSsn, userRightSsn, userState, userLv, userRegdate, userLimitStorage) VALUES ((SELECT NVL(MAX(userSeq), 0) + 1 FROM tblUser), ?, ?, ?, ?, ?, ?, ?, ?, default, default, SYSDATE, default)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getUserId());
			pstat.setString(2, dto.getUserPw());
			pstat.setString(3, dto.getUserNick());
			pstat.setString(4, dto.getUserTel());
			pstat.setString(5, dto.getUserAddress());
			pstat.setString(6, dto.getUserEmail());
			pstat.setString(7, dto.getUserLeftSsn());
			pstat.setString(8, dto.getUserRightSsn());
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("UserDAO.| get");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public UserDTO get(String userSeq) {
		
		final String SQL = "select * from tblUser where userSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, userSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			
			if (rs.next()) {
				
				UserDTO dto = new UserDTO();
				
				dto.setLimitStorage(rs.getString("limitStorage"));
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserLeftSsn(rs.getString("userLeftSsn"));
				dto.setUserLV(rs.getString("userLv"));
				dto.setUserNick(rs.getString("userNick"));
				dto.setUserRegdate(rs.getString("userRegdate"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserState(rs.getString("userState"));
				dto.setUserTel(rs.getString("userTel"));
				
				rs.close();
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println("UserDAO.| get");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<UserDTO> list() {
		
		final String SQL = "select * from tblUser";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<UserDTO> list = new ArrayList<>();
			
			 for (ResultSet row = rs; row.next(); ) {
				
				UserDTO dto = new UserDTO();
				
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserLeftSsn(rs.getString("userLeftSsn"));
				dto.setLimitStorage(rs.getString("limitStorage"));
				dto.setUserLV(rs.getString("userLv"));
				dto.setUserNick(rs.getString("userNick"));
				dto.setUserRegdate(rs.getString("userRegdate"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserState(rs.getString("userState"));
				dto.setUserTel(rs.getString("userTel"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| list");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	//닉네임, 전화번호, 주소, 이메일, 용량 변경
	@Override
	public int set(UserDTO dto) {
		
		final String SQL = "UPDATE tblUser SET userNick = ?, userTel = ?, userAddress = ?, userEmail = ?, userLimitStorage = ? WHERE userId = ?";
		
		try (
			Connection conn = DBUtil.open();
		    PreparedStatement pstat = conn.prepareStatement(SQL)
		) {

		        pstat.setString(1, dto.getUserNick());
		        pstat.setString(2, dto.getUserTel());
		        pstat.setString(3, dto.getUserAddress());
		        pstat.setString(4, dto.getLimitStorage());
		        pstat.setString(5, dto.getUserSeq());

		        int result = pstat.executeUpdate();
		        
		        return result;
		    } catch (Exception e) {
		        System.out.println("UserDAO.| set");
		        e.printStackTrace();
		        
		    }
		    
		    return 0;
	}
	
	//비밀번호 변경
	public int setPw(UserDTO dto) {
		
		final String SQL = "update tblUser set userPw = ? where userSeq = ?";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			) {
			
			pstat.setString(1, dto.getUserPw());
			pstat.setString(2, dto.getUserSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("UserDAO.| setPw");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int disable(String userSeq) {
		
		final String SQL = "insert into tblBlackList(userSeq) values(?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
		){
			pstat.setString(1, userSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("UserDAO.| disable");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int activation(String userSeq) {
		
		final String SQL = "delete from tblBlackList where userSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
		){
			pstat.setString(1, userSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("UserDAO.| disable");
			e.printStackTrace();
		}
		
		return 0;
	}
	
}//End of class