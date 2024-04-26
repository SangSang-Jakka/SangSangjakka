package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.board.NoticeDTO;
import com.jakka.model.dto.board.SuggestionDTO;

public class SuggestionDAO {

	//건의사항 전체 리스트
	public ArrayList<SuggestionDTO> list() {
		
		final String SQL = "select * from tblSuggestion";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<SuggestionDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				SuggestionDTO dto = new SuggestionDTO();
				
				dto.setSgstCnt(rs.getString("sgstCnt"));
				dto.setSgstContents(rs.getString("sgstContents"));
				dto.setSgstRegdate(rs.getString("sgstRegdate"));
				dto.setSgstSecretYN(rs.getString("sgstSecretYN"));
				dto.setSgstSeq(rs.getString("sgstSeq"));
				dto.setSgstTitle(rs.getString("sgstTitle"));
				dto.setUserSeq(rs.getString("userSeq"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("SuggestionDAO.| list");
			e.printStackTrace();
		}
		
		return null;
		
	}//list()
	
	//건의사항 추가
	public int add(SuggestionDTO dto) {
		
		final String SQL = "INSERT INTO tblSuggestion (sgstSeq, sgstTitle, sgstContents, sgstRegdate, sgstSecretYN, userSeq, sgstCnt) VALUES ((SELECT NVL(MAX(sgstSeq), 0) + 1 FROM tblSuggestion), ?, ?, DEFAULT, ?, ?, DEFAULT)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getSgstTitle());
			pstat.setString(2, dto.getSgstContents());
			pstat.setString(3, dto.getSgstSecretYN());
			pstat.setString(4, dto.getUserSeq());
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("SuggestionDAO.| add");
			e.printStackTrace();
		}
		
		return 0;
		
	}//add()
	
	//건의사항 수정(제목, 내용, 비밀글 여부)
	public int  set(SuggestionDTO dto) {
		
		final String SQL = "update tblSuggestion set sgstTitle = ?, sgstContents = ?, sgstSecretYN = ? where sgstSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getSgstTitle());
			pstat.setString(2, dto.getSgstContents());
			pstat.setString(3, dto.getSgstSecretYN());
			pstat.setString(4, dto.getSgstSeq());
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("SuggestionDAO.| set");
			e.printStackTrace();
		}
		
		return 0;
		
	}//set()
	
	//건의사항글 조회수 증가
	public int addCnt(String sgstSeq) {
		
		final String SQL = "update tblSuggestion set SgstCnt = SgstCnt + 1 where sgstSeq = ?";
		
		try (
				
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, sgstSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.| addNoticeCnt");
			e.printStackTrace();
		}
		
		return 0;
		
	}//addCnt()
	
	public SuggestionDTO get(String sgstSeq) {
		
		final String SQL = "select * from tblSuggestion where sgstSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, sgstSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				SuggestionDTO dto = new SuggestionDTO();
				
				dto.setSgstCnt(rs.getString("sgstCnt"));
				dto.setSgstContents(rs.getString("sgstContents"));
				dto.setSgstRegdate(rs.getString("sgstRegdate"));
				dto.setSgstSecretYN(rs.getString("sgstSecretYN"));
				dto.setSgstSeq(rs.getString("sgstSeq"));
				dto.setSgstTitle(rs.getString("sgstTitle"));
				dto.setUserSeq(rs.getString("userSeq"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("SuggestionDAO.| get");
			e.printStackTrace();
		}
		
		return null;
		
	}//get()

		
	
}//End of class















