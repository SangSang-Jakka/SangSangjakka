package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.board.BoardDTO;
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
	
	public int whiteTotalCnt(HashMap<String, String> map) {
		
		String where = "";
		
		if (map.get("search").equals("y")) {

			where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
		}
		
		String sql = String.format("select count(*) as cnt from vwSuggestion %s", where);
		
		System.out.println("SQL Query: " + sql);
		
		// 연결
		try(
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(sql);	
		) {
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
			return 0;
		}
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
			System.out.println("pstat set하기 전 dto" + dto);
			pstat.setString(1, dto.getSgstTitle());
			pstat.setString(2, dto.getSgstContents());
			pstat.setString(3, dto.getSgstSecretYN());
			pstat.setString(4, dto.getUserSeq());
			System.out.println("add제목" + dto.getSgstTitle());
			System.out.println("add내용" + dto.getSgstContents());
			System.out.println("add비밀글" + dto.getSgstSecretYN());
			System.out.println("add사용자번호" + dto.getUserSeq());

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
			System.out.println(dto.getUserSeq());
			int result = pstat.executeUpdate();
			System.out.println(result);
			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 글번호'" + dto.getSgstSeq() + "' 글제목'" + dto.getSgstTitle() + "' 글내용'" + dto.getSgstContents() + "' 건의사항을 '수정'했습니다.");
				log.setString(3, UserLog.SuggestionEdited.getValue());
				log.executeUpdate();
			}
			
			conn.commit();
			System.out.println(result);
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
				System.out.println("findById에서 dto확인 : " + dto);
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
public ArrayList<SuggestionDTO> findAllWhite(HashMap<String, String> map, String orderBy) {
		
		//System.out.println(orderBy);
	    String SQL = "SELECT sgstSeq, sgstTitle, sgstContents, sgstRegdate, sgstSecretYN, userSeq, sgstCnt, userNick " +
	                 "FROM (SELECT ROWNUM RNUM, f.sgstSeq, f.sgstTitle, f.sgstContents, f.sgstRegdate, f.sgstSecretYN, f.userSeq, f.sgstCnt, f.userNick " +
	                 "FROM (SELECT * FROM vwSuggestion ";

	    if (map.get("search").equals("y")) {
	        SQL += String.format("WHERE %s like '%%%s%%' ", map.get("column"), map.get("word"));
	    }

	    SQL += "ORDER BY ";

	    switch (orderBy) {
	        case "newest":
	            SQL += "sgstRegdate DESC";
	            break;
	        case "view_count":
	            SQL += "sgstCnt DESC";
	            break;
	        case "comment_count":
	            SQL += "sgstCnt DESC";
	            break;
	        default:
	            SQL += "sgstRegdate DESC";
	    }

	    SQL += ") f) " +
	           "WHERE RNUM BETWEEN ? AND ?";

	    try (
	        Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL);
	    ) {
	        pstmt.setString(1, map.get("begin"));
	        pstmt.setString(2, map.get("end"));

	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<SuggestionDTO> list = new ArrayList<>();
	        
	        System.out.println(SQL);

	        while (rs.next()) {
	        	SuggestionDTO dto = new SuggestionDTO();
	        	dto.setSgstSeq(rs.getString("sgstSeq"));
				dto.setSgstTitle(rs.getString("sgstTitle"));
				dto.setSgstContents(rs.getString("sgstContents"));
				dto.setSgstRegdate(rs.getString("sgstRegdate"));
				dto.setSgstSecretYN(rs.getString("sgstSecretYN"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setSgstCnt(rs.getString("sgstCnt"));
				dto.setUserNick(rs.getString("userNick"));
				list.add(dto);
	        }

	        System.out.println(list);
	        return list;

	    } catch (Exception e) {
	        System.out.println("SuggestionDAO.| list");
	        e.printStackTrace();
	    }

	    return null;
	}
	
	public ArrayList<SuggestionDTO> findAllWhite(HashMap<String, String> map) {
		
		String where = "where rnum BETWEEN ? AND ?";
		String col = "";
		System.out.println(map.get("column"));
		if(map.get("search").equals("y")) {
			col = col + String.format(" where %s like '%%%s%%'", map.get("column"), map.get("word"));
		}
		
		String sql = String.format("SELECT sgstSeq, sgstTitle, sgstContents, sgstRegdate, sgstSecretYN, userSeq, sgstCnt, userNick " +
				" FROM (SELECT ROWNUM RNUM, f.sgstSeq, f.sgstTitle, f.sgstContents, f.sgstRegdate, f.sgstSecretYN, f.userSeq, f.sgstCnt, f.userNick " +
				" FROM (SELECT * FROM vwSuggestion %s ORDER BY sgstRegdate desc) F) " +
				"%s", col, where);

		try (

				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(sql);
			) {
			
				pstat.setString(1, map.get("begin"));
				pstat.setString(2, map.get("end"));
				ResultSet rs = pstat.executeQuery();
				ArrayList<SuggestionDTO> list = new ArrayList<>();
				
				while (rs.next()) {
					
					SuggestionDTO dto = new SuggestionDTO();
					
					// 데이터 설정
					dto.setSgstSeq(rs.getString("sgstSeq"));
					dto.setSgstTitle(rs.getString("sgstTitle"));
					dto.setSgstContents(rs.getString("sgstContents"));
					dto.setSgstRegdate(rs.getString("sgstRegdate"));
					dto.setSgstSecretYN(rs.getString("sgstSecretYN"));
					dto.setUserSeq(rs.getString("userSeq"));
					dto.setSgstCnt(rs.getString("sgstCnt"));
					dto.setUserNick(rs.getString("userNick"));
					list.add(dto);
				
				}
				
				return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}// list()
	
	public int del(String seq) {
		
		String cSQL = "delete from tblSuggestionAnswer where sgstSeq = ?";
		String pSQL = "delete from tblSuggestion where sgstSeq = ?";
	    
	    try (
	    	Connection conn = DBUtil.open();
	    	PreparedStatement pstat = conn.prepareStatement(cSQL);
	    	PreparedStatement ppstat = conn.prepareStatement(pSQL);
	    ){
	        pstat.setString(1, seq);
	        pstat.executeUpdate();

	        pstat.setString(1, seq);
	        return pstat.executeUpdate();

	    } catch (Exception e) {
	        System.out.println("SuggestionDAO.| del");
	        e.printStackTrace();
	    }
	    
	    return 0;
	}
//	public int del(String seq) {
//		String pSQL = "delete from tblSuggestion where sgstSeq = ?";
//		String cSQL = "delete from tblSuggestionAnswer where sgstSeq = ?";
//	    try (
//	    	Connection conn = DBUtil.open();
//	        PreparedStatement pstmt = conn.prepareStatement(cSQL);
//	    ) {
//	    	
//	    	conn.setAutoCommit(false);
//	    	
//	        System.out.println("seq의 값 확인 : " + seq + "넘어오는지 확인");
//	    	pstmt.setString(1, seq);
////	        pstmt.setString(2, seq);
//	        System.out.println("pstmt? : " + pstmt);
//	        int result = pstmt.executeUpdate();
//	        System.out.println("쿼리 실행 후 결과 : " + result);
//	        
//	        return result;
//	        
//	        } catch (Exception e) {
//	        System.out.println("SuggestionDAO.| findByRegdateBetween");
//	        e.printStackTrace();
//	    }
//	    return 0;
//	}




//건의사항 비밀글 조회  리스트

	@Override
	public ArrayList<SuggestionDTO> findAllSecret() {
		
		final String SQL = "SELECT * FROM vwSuggestion WHERE sgstSecretYN = 'y' ORDER BY sgstRegdate DESC";
		
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
		
	}
	
	
	
	
	

	//건의사항 일반글 조회  리스트

		@Override
		public ArrayList<SuggestionDTO> findAllOpen() {
			
			final String SQL = "SELECT * FROM vwSuggestion WHERE sgstSecretYN = 'n' ORDER BY sgstRegdate DESC";
			
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
			
		}

		

		// 답변이 있는 건의사항 조회
		
		@Override
		public ArrayList<SuggestionDTO> findAllAnswer() {
			
			final String SQL = "select s.sgstSeq, s.sgstTitle, s.sgstContents, s.sgstRegdate, s.sgstSecretYN, s.userSeq, s.usernick, s.sgstCnt, sa.answSeq, sa.adId, sa.sgstAnsw, sa.sgstRegdate as answRegdate from vwSuggestion s inner join tblSuggestionAnswer sa on s.sgstSeq = sa.sgstSeq";
			
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
			
		}
		
		// 답변이 없는 건의사항 조회
		
		@Override
		public ArrayList<SuggestionDTO> findAllNoAnswer() {
			
			final String SQL = "select s.sgstSeq, s.sgstTitle, s.sgstContents, s.sgstRegdate, s.sgstSecretYN, s.userSeq, s.usernick, s.sgstCnt from vwSuggestion s left join tblSuggestionAnswer sa on s.sgstSeq = sa.sgstSeq where sa.answSeq is null";
			
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
			
		}
		
		
		@Override
		public ArrayList<SuggestionDTO> findToday(String today) {
			
			final String SQL = "SELECT * \r\n"
					+ "FROM vwSuggestion \r\n"
					+ "WHERE TRUNC(SGSTREGDATE) = TO_DATE(?, 'YY/MM/DD') \r\n"
					+ "ORDER BY SGSTREGDATE DESC";

			try (
			        Connection conn = DBUtil.open();
			        PreparedStatement pstmt = conn.prepareStatement(SQL);
			    ) {
			        pstmt.setString(1, today);
			        ResultSet rs = pstmt.executeQuery();
			        
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
			        System.out.println("BoardDAO.| list");
			        e.printStackTrace();
			    }

			    return null;
		}

	
}//End of class












