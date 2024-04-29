package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.board.SuggestionCommentDTO;
import com.jakka.model.dto.board.SuggestionDTO;

public class SuggestionAnswerDAO implements BasicDAO<SuggestionCommentDTO>{

	private final static SuggestionAnswerDAO DAO = new SuggestionAnswerDAO();
	
	private SuggestionAnswerDAO() {
		//외부 생성 방지
	}
	
	public static SuggestionAnswerDAO getInstance() {
		
		return DAO;
		
	}//getInstance()
	
	@Override
	public int add(SuggestionCommentDTO dto) {
		
		final String SQL = "insert into tblSuggestionAnswer(answSeq, adId, sgstSeq, sgstAnsw, sgstRegdate) values((SELECT NVL(MAX(answSeq), 0) + 1 FROM tblSuggestionAnswer), ?, ?, ?, default)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getAdId());
			pstat.setString(2, dto.getSgstSeq());
			pstat.setString(3, dto.getSgstAnsw());
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("SuggestionAnswerDTO.| add");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public SuggestionCommentDTO get(String answSeq) {
		
		final String SQL = "select * from tblSuggestionAnswer where answSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, answSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				SuggestionCommentDTO dto = new SuggestionCommentDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setAnswRegdate(rs.getString("answRegdate"));
				dto.setAnswSeq(rs.getString("answSeq"));
				dto.setSgstAnsw(rs.getString("sgstAnsw"));
				dto.setSgstSeq(rs.getString("sgstSeq"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("SuggestionAnswerDAO.| get");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<SuggestionCommentDTO> list() {
		
		final String SQL = "select * from tblSuggestionAnswer";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<SuggestionCommentDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				SuggestionCommentDTO dto = new SuggestionCommentDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setAnswRegdate(rs.getString("answRegdate"));
				dto.setAnswSeq(rs.getString("answSeq"));
				dto.setSgstAnsw(rs.getString("sgstAnsw"));
				dto.setSgstSeq(rs.getString("sgstSeq"));
				
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("SuggestionAnswerDAO.| list");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public ArrayList<SuggestionCommentDTO> list(String parentSgstSeq) {
		
		final String SQL = "select * from tblSuggestionAnswer where sgstSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, parentSgstSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<SuggestionCommentDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				SuggestionCommentDTO dto = new SuggestionCommentDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setAnswRegdate(rs.getString("answRegdate"));
				dto.setAnswSeq(rs.getString("answSeq"));
				dto.setSgstAnsw(rs.getString("sgstAnsw"));
				dto.setSgstSeq(rs.getString("sgstSeq"));
				
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("SuggestionAnswerDAO.| list");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@Override
	public int set(SuggestionCommentDTO dto) {
		
		final String SQL = "update tblSuggestionAnswer set sgstAnsw = ? where answSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getSgstAnsw());
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("SuggestionAnswerDAO.| set");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	

}//End of class
