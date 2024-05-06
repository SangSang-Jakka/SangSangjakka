package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.board.SuggestionAnswerDTO;
import com.jakka.model.dto.board.SuggestionDTO;
import com.jakka.model.enums.AdminLog;

public class SuggestionAnswerDAOImpl implements SuggestionAnswerDAO{

	private final static SuggestionAnswerDAOImpl DAO = new SuggestionAnswerDAOImpl();
	
	private SuggestionAnswerDAOImpl() {
		//외부 생성 방지
	}
	
	public static SuggestionAnswerDAOImpl getInstance() {
		
		return DAO;
		
	}//getInstance()
	
	@Override
	public int add(SuggestionAnswerDTO dto) {
		
		final String SQL = "insert into tblSuggestionAnswer(answSeq, adId, sgstSeq, sgstAnsw, sgstRegdate) values((SELECT NVL(MAX(answSeq), 0) + 1 FROM tblSuggestionAnswer), ?, ?, ?, default)";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
				
			){
			
			conn.setAutoCommit(false);
			
			pstat.setString(1, dto.getAdId());
			pstat.setString(2, dto.getSgstSeq());
			pstat.setString(3, dto.getSgstAnsw());
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
	            log.setString(1, dto.getAdId());
	            log.setString(2, "관리자'" + dto.getAdId() + "'이 글번호'" + dto.getSgstSeq() + "'에 '" + dto.getSgstAnsw() + "'건의사항 답변을 '작성'했습니다.");
	            log.setString(3, AdminLog.SuggestionAnswered.getValue());
	            log.executeUpdate();
	        }
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("SuggestionAnswerDTO.| add");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public SuggestionAnswerDTO findById(String answSeq) {
		
		final String SQL = "select * from tblSuggestionAnswer where answSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, answSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				SuggestionAnswerDTO dto = new SuggestionAnswerDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setSgstRegdate(rs.getString("sgstRegdate"));
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
	public ArrayList<SuggestionAnswerDTO> findAll() {
		
		final String SQL = "select * from tblSuggestionAnswer";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<SuggestionAnswerDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				SuggestionAnswerDTO dto = new SuggestionAnswerDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setSgstRegdate(rs.getString("sgstRegdate"));
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
	
	public ArrayList<SuggestionAnswerDTO> list(String parentSgstSeq) {
		
		final String SQL = "select * from tblSuggestionAnswer where sgstSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, parentSgstSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<SuggestionAnswerDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				SuggestionAnswerDTO dto = new SuggestionAnswerDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setSgstRegdate(rs.getString("sgstRegdate"));
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
	public int save(SuggestionAnswerDTO dto) {
		
		final String SQL = "update tblSuggestionAnswer set sgstAnsw = ? where answSeq = ?";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
				
			){
			
			conn.setAutoCommit(false);
			
			pstat.setString(1, dto.getSgstAnsw());
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, dto.getAdId());
				log.setString(2, "관리자'" + dto.getAdId() + "'이 글번호'" + dto.getSgstSeq() + "'에 '" + dto.getSgstAnsw() + "'건의사항 답변을 '수정'했습니다.");
				log.setString(3, AdminLog.SuggestionAnsweredEdited.getValue());
				log.executeUpdate();
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("SuggestionAnswerDAO.| set");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	@Override
	public ArrayList<SuggestionAnswerDTO> findChild(String parentSeq) {
		
		final String SQL = "SELECT * FROM tblSuggestionAnswer WHERE sgsSeq = ? order by sgstRegdate desc";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	     ) {
	        pstmt.setString(1, parentSeq);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<SuggestionAnswerDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            SuggestionAnswerDTO dto = new SuggestionAnswerDTO();
	            
	            dto.setAnswSeq(rs.getString("answSeq"));
	            dto.setAdId(rs.getString("adId"));
	            dto.setSgstSeq(rs.getString("sgsSeq"));
	            dto.setSgstAnsw(rs.getString("sgstAnsw"));
	            dto.setSgstRegdate(rs.getString("sgstRegdate"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("SuggestionAnswerDAO.| findChild");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public int del(SuggestionAnswerDTO dto) {
	    final String SQL = "DELETE FROM tblSuggestionAnswer WHERE answSeq = ?";
	    final String LOGSQL = "INSERT INTO tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) VALUES ((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL);
	         PreparedStatement log = conn.prepareStatement(LOGSQL)) {

	        conn.setAutoCommit(false);

	        pstat.setString(1, dto.getAnswSeq());

	        int result = pstat.executeUpdate();

	        if (result > 0) {
	            // 삭제 로그 기록
	            log.setString(1, dto.getAdId());
	            log.setString(2, "관리자'" + dto.getAdId() + "답변 번호'" + dto.getAnswSeq() + "'을 '삭제'했습니다.");
	            log.setString(3, AdminLog.SuggestionAnsweredDeleted.getValue());
	            log.executeUpdate();
	        }

	        conn.commit();

	        return result;

	    } catch (Exception e) {
	        System.out.println("SuggestionAnswerDAO | delete");
	        e.printStackTrace();
	    }

	    return 0;
	}
	
	
	
	

}//End of class
