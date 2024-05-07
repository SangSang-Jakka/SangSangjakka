package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.board.SuggestionDTO;
import com.jakka.model.enums.UserLog;

public class SuggestionDAOImpl implements SuggestionDAO{
	
	private final static SuggestionDAOImpl DAO = new SuggestionDAOImpl();
	
	public SuggestionDAOImpl() {
		//외부 생성 방지
	}
	
	public static SuggestionDAOImpl getInstance() {
		return DAO;
	}//getInstance()

	//건의사항 전체 리스트
	@Override
	public ArrayList<SuggestionDTO> findAll() {
		
		final String SQL = "select * from vwSuggestion order by sgstRegdate desc";
		
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
				dto.setUserNick(rs.getString("userNick"));
				
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
	@Override
	public int add(SuggestionDTO dto) {
		
		final String SQL = "INSERT INTO tblSuggestion (sgstSeq, sgstTitle, sgstContents, sgstRegdate, sgstSecretYN, userSeq, sgstCnt) VALUES ((SELECT NVL(MAX(sgstSeq), 0) + 1 FROM tblSuggestion), ?, ?, DEFAULT, ?, ?, DEFAULT)";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
				
			){
			
			conn.setAutoCommit(false);
			
			pstat.setString(1, dto.getSgstTitle());
			pstat.setString(2, dto.getSgstContents());
			pstat.setString(3, dto.getSgstSecretYN());
			pstat.setString(4, dto.getUserSeq());
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 글제목'" + dto.getSgstTitle() + "' 글내용'" + dto.getSgstContents() + "' 건의사항을 '작성'했습니다.");
				log.setString(3, UserLog.SuggestionCreated.getValue());
				log.executeUpdate();
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("SuggestionDAO.| add");
			e.printStackTrace();
		}
		
		return 0;
		
	}//add()
	
	//건의사항 수정(제목, 내용, 비밀글 여부)
	@Override
	public int save(SuggestionDTO dto) {
		
		final String SQL = "update tblSuggestion set sgstTitle = ?, sgstContents = ?, sgstSecretYN = ? where sgstSeq = ?";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);	
			){
			conn.setAutoCommit(false);
			
			pstat.setString(1, dto.getSgstTitle());
			pstat.setString(2, dto.getSgstContents());
			pstat.setString(3, dto.getSgstSecretYN());
			pstat.setString(4, dto.getSgstSeq());
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 글번호'" + dto.getSgstSeq() + "' 글제목'" + dto.getSgstTitle() + "' 글내용'" + dto.getSgstContents() + "' 건의사항을 '수정'했습니다.");
				log.setString(3, UserLog.SuggestionEdited.getValue());
				log.executeUpdate();
			}
			
			conn.commit();
			
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
	
	@Override
	public SuggestionDTO findById(String sgstSeq) {
		
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
	
	@Override
	public ArrayList<SuggestionDTO> findByContentsContains(String word) {
		
		final String SQL = "SELECT * FROM tblSuggestion WHERE sgstContents LIKE ?";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, "%" + word + "%");
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<SuggestionDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            SuggestionDTO dto = new SuggestionDTO();
	            
	            dto.setSgstSeq(rs.getString("sgstSeq"));
	            dto.setSgstTitle(rs.getString("sgstTitle"));
	            dto.setSgstContents(rs.getString("sgstContents"));
	            dto.setSgstRegdate(rs.getString("sgstRegdate"));
	            dto.setSgstSecretYN(rs.getString("sgstSecretYN"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setSgstCnt(rs.getString("sgstCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("SuggestionDAO.| findByContentsContains");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<SuggestionDTO> findByNick(String nick) {
		
		final String SQL = "select * from vwSuggestion where userNick = ? order by sgstRegdate desc";

		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstmt = conn.prepareStatement(SQL)
		) {
			
		 pstmt.setString(1, nick);
		 
		 ResultSet rs = pstmt.executeQuery();
		
		 ArrayList<SuggestionDTO> list = new ArrayList<>();
		 
		 while (rs.next()) {
			 
		     SuggestionDTO dto = new SuggestionDTO();
		     
		     dto.setSgstSeq(rs.getString("sgstSeq"));
		     dto.setSgstTitle(rs.getString("sgstTitle"));
		     dto.setSgstContents(rs.getString("sgstContents"));
		     dto.setSgstRegdate(rs.getString("sgstRegdate"));
		     dto.setSgstSecretYN(rs.getString("sgstSecretYN"));
		     dto.setUserSeq(rs.getString("userSeq"));
		     dto.setSgstCnt(rs.getString("sgstCnt"));
		     
		     list.add(dto);
		 }
		 
		 return list;
		 
		} catch (Exception e) {
		 System.out.println("SuggestionDAO.| findByNick");
		 e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<SuggestionDTO> findByRegdateAfter(String date) {
		
		final String SQL = "SELECT * FROM tblSuggestion WHERE sgstRegdate > TO_DATE(?, 'YYYY-MM-DD') ORDER BY sgstRegdate DESC";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, date);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<SuggestionDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            SuggestionDTO dto = new SuggestionDTO();
	            
	            dto.setSgstSeq(rs.getString("sgstSeq"));
	            dto.setSgstTitle(rs.getString("sgstTitle"));
	            dto.setSgstContents(rs.getString("sgstContents"));
	            dto.setSgstRegdate(rs.getString("sgstRegdate"));
	            dto.setSgstSecretYN(rs.getString("sgstSecretYN"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setSgstCnt(rs.getString("sgstCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("SuggestionDAO.| findByRegdateAfter");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<SuggestionDTO> findByRegdateBefore(String date) {
		
		final String SQL = "SELECT * FROM tblSuggestion WHERE sgstRegdate < TO_DATE(?, 'YYYY-MM-DD') ORDER BY sgstRegdate DESC";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, date);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<SuggestionDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            SuggestionDTO dto = new SuggestionDTO();
	            
	            dto.setSgstSeq(rs.getString("sgstSeq"));
	            dto.setSgstTitle(rs.getString("sgstTitle"));
	            dto.setSgstContents(rs.getString("sgstContents"));
	            dto.setSgstRegdate(rs.getString("sgstRegdate"));
	            dto.setSgstSecretYN(rs.getString("sgstSecretYN"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setSgstCnt(rs.getString("sgstCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("SuggestionDAO.| findByRegdateBefore");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<SuggestionDTO> findByRegdateBetween(String startDate, String endDate) {
		
		final String SQL = "SELECT * FROM tblSuggestion WHERE sgstRegdate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ORDER BY sgstRegdate DESC";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, startDate);
	        pstmt.setString(2, endDate);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<SuggestionDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            SuggestionDTO dto = new SuggestionDTO();
	            
	            dto.setSgstSeq(rs.getString("sgstSeq"));
	            dto.setSgstTitle(rs.getString("sgstTitle"));
	            dto.setSgstContents(rs.getString("sgstContents"));
	            dto.setSgstRegdate(rs.getString("sgstRegdate"));
	            dto.setSgstSecretYN(rs.getString("sgstSecretYN"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setSgstCnt(rs.getString("sgstCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("SuggestionDAO.| findByRegdateBetween");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<SuggestionDTO> findByTitleContains(String word) {
		
		final String SQL = "SELECT * FROM tblSuggestion WHERE sgstTitle LIKE ? order by sgstRegdate desc";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	    	
	        pstmt.setString(1, "%" + word + "%");
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<SuggestionDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            SuggestionDTO dto = new SuggestionDTO();
	            
	            dto.setSgstSeq(rs.getString("sgstSeq"));
	            dto.setSgstTitle(rs.getString("sgstTitle"));
	            dto.setSgstContents(rs.getString("sgstContents"));
	            dto.setSgstRegdate(rs.getString("sgstRegdate"));
	            dto.setSgstSecretYN(rs.getString("sgstSecretYN"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setSgstCnt(rs.getString("sgstCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("SuggestionDAO.| findByTitleContains");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public ArrayList<SuggestionDTO> findAllWhite(HashMap<String, String> map) {
		
		String where ="where rnum BETWEEN ? AND ?";
		
		if(map.get("search").equals("y")) {
			
		}
		
		return null;
	}
}//End of class















