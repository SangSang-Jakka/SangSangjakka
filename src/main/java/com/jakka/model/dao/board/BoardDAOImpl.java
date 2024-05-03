package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.enums.UserLog;

public class BoardDAOImpl implements BoardDAO{

	private static final BoardDAOImpl DAO = new BoardDAOImpl();
	
	private BoardDAOImpl() {
		//외부 생성 방지
	}
	
	public static BoardDAOImpl getInstance() {
		
		return DAO;
		
	}//getInstance()
	
	//자유게시판 전체글
	@Override
	public ArrayList<BoardDTO> findAll() {
		
		final String SQL = "select * from tblBoard order by boardRegdate desc";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<BoardDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setUserSeq(rs.getString("userSeq"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| list");
			e.printStackTrace();
		}
		
		return null;
		
	}//list()
	
	//블라인드 제외 전체글
	public ArrayList<BoardDTO> findAllWhite() {
		
		final String SQL = "select * from vwBoard";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<BoardDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setUserSeq(rs.getString("userSeq"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| list");
			e.printStackTrace();
		}
		
		return null;
		
	}//list()
	
	
	//블라인드된 자유게시판 글
	@Override
	public ArrayList<BoardDTO> findAllBlack() {
		
		final String SQL = "SELECT * FROM vwBoardBlack order by boardRegdate desc";

	    try (
	    	Connection conn = DBUtil.open();
	        Statement stat = conn.createStatement();
	        ResultSet rs = stat.executeQuery(SQL)
	    ) {

	        ArrayList<BoardDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            BoardDTO dto = new BoardDTO();
	            
	            dto.setBoardSeq(rs.getString("boardSeq"));
	            dto.setBoardTitle(rs.getString("boardTitle"));
	            dto.setBoardContents(rs.getString("boardContents"));
	            dto.setBoardRegdate(rs.getString("boardRegdate"));
	            dto.setBoardReportCnt(rs.getString("boardReportCnt"));
	            dto.setBoardCnt(rs.getString("boardCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BoardDAO.| findAllBlack");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	//게시판 글 추가
	//제목, 내용, 작성자seq
	@Override
	public int add(BoardDTO dto) {
		
		final String SQL = "INSERT INTO tblBoard (boardSeq, boardTitle, boardContents, boardRegdate, boardReportCnt, boardCnt, userSeq) VALUES ((SELECT NVL(MAX(boardSeq), 0) + 1 FROM tblBoard), ?, ?, DEFAULT, DEFAULT, DEFAULT, ?)";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";

		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);				
			){
			
			conn.setAutoCommit(false);
			
			pstat.setString(1, dto.getBoardTitle());
			pstat.setString(2, dto.getBoardContents());
			pstat.setString(3, dto.getUserSeq());
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 글제목'" + dto.getBoardTitle() +"' 글내용'" + dto.getBoardContents() + "' 자유게시판글을 작성했습니다.");
				log.setString(3, UserLog.BoardCreated.getValue());
				log.executeUpdate();
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| add");
			e.printStackTrace();
		}
		
		return 0;
		
	}//add()
	
	//게시글 수정(이름, 내용)
	@Override
	public int save(BoardDTO dto) {
		
		final String SQL = "update tblBoard set boardTitle = ?, boardContents = ? where boardSeq = ?";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";

		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);	
				
			){
			
			conn.setAutoCommit(false);
			
			pstat.setString(1, dto.getBoardTitle());
			pstat.setString(2, dto.getBoardContents());
			pstat.setString(3, dto.getBoardSeq());
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 글번호'" + dto.getBoardSeq() + "' 글제목'" + dto.getBoardTitle() +"' 글내용'" + dto.getBoardContents() + "' 자유게시판글을 수정했습니다.");
				log.setString(3, UserLog.BoardEdited.getValue());
				log.executeUpdate();
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| set");
			e.printStackTrace();
		}
		
		
		return 0;
		
	}//set()
	
	//게시글 조회수 증가
	public int addCnt(String boardSeq) {
		
		final String SQL = "update tblBoard set boardCnt = boardCnt + 1 where boardSeq = ?";
		
		try (
				
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
					
				){
				
				pstat.setString(1, boardSeq);
				
				int result = pstat.executeUpdate();
				
				return result;
				
			} catch (Exception e) {
			System.out.println("BoardDAO.| addCnt");
			e.printStackTrace();
		}
		
		return 0;
	}//addCnt()
	
	//게시글 신고횟수 증가
	public int addReportCnt(String boardSeq, String userSeq) {
		
		final String SQL = "update tblBoard set boardReportCnt = boardReportCnt + 1 where boardSeq = ?";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";

		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
				
			){
			
			conn.setAutoCommit(false);
			
			pstat.setString(1, boardSeq);
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
				//작업중
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| addReportCnt");
			e.printStackTrace();
		}
		
		return 0;
		
	}//addReportCnt()
	
	//특정 게시글 정보 가져오기
	@Override
	public BoardDTO findById(String boardSeq) {
		
		final String SQL = "select * from tblBoard where boardSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, boardSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setUserSeq(rs.getString("userSeq"));
				
				return dto;
				
			}
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| get");
			e.printStackTrace();
		}
		
		return null;
		
	}//get()
	
	//비활성화
	public int disable(String boardSeq, String adId) {
		
		final String SQL = "delete from tblBoardWhiteList where boardSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			pstat.setString(1, boardSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| disable");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//활성화
	public int activation(String boardSeq, String adId) {
		
		final String SQL = "insert into tblBoardWhiteList(boardSeq) values(?)";
		
		try (
			Connection conn = DBUtil.open();	
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			pstat.setString(1, boardSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| activation");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public ArrayList<BoardDTO> findByContentsContains(String word) {
		
		final String SQL = "SELECT * FROM tblBoard WHERE boardContents LIKE ? order by boardRegdate desc";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, "%" + word + "%");
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BoardDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            BoardDTO dto = new BoardDTO();
	            
	            dto.setBoardSeq(rs.getString("boardSeq"));
	            dto.setBoardTitle(rs.getString("boardTitle"));
	            dto.setBoardContents(rs.getString("boardContents"));
	            dto.setBoardRegdate(rs.getString("boardRegdate"));
	            dto.setBoardReportCnt(rs.getString("boardReportCnt"));
	            dto.setBoardCnt(rs.getString("boardCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BoardDAO.| findByContentsContains");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BoardDTO> findByNick(String Nick) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<BoardDTO> findByRegdateAfter(String date) {
		
		final String SQL = "SELECT * FROM tblBoard WHERE boardRegdate > TO_DATE(?, 'YYYY-MM-DD') ORDER BY boardRegdate DESC";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, date);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BoardDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            BoardDTO dto = new BoardDTO();
	            
	            dto.setBoardSeq(rs.getString("boardSeq"));
	            dto.setBoardTitle(rs.getString("boardTitle"));
	            dto.setBoardContents(rs.getString("boardContents"));
	            dto.setBoardRegdate(rs.getString("boardRegdate"));
	            dto.setBoardReportCnt(rs.getString("boardReportCnt"));
	            dto.setBoardCnt(rs.getString("boardCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BoardDAO.| findByRegdateAfter");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BoardDTO> findByRegdateBefore(String date) {
		
		final String SQL = "SELECT * FROM tblBoard WHERE boardRegdate < TO_DATE(?, 'YYYY-MM-DD') ORDER BY boardRegdate DESC";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	     ) {
	        pstmt.setString(1, date);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BoardDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            BoardDTO dto = new BoardDTO();
	            
	            dto.setBoardSeq(rs.getString("boardSeq"));
	            dto.setBoardTitle(rs.getString("boardTitle"));
	            dto.setBoardContents(rs.getString("boardContents"));
	            dto.setBoardRegdate(rs.getString("boardRegdate"));
	            dto.setBoardReportCnt(rs.getString("boardReportCnt"));
	            dto.setBoardCnt(rs.getString("boardCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BoardDAO.| findByRegdateBefore");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BoardDTO> findByRegdateBetween(String startDate, String endDate) {
		
		final String SQL = "SELECT * FROM tblBoard WHERE boardRegdate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ORDER BY boardRegdate DESC";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, startDate);
	        pstmt.setString(2, endDate);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BoardDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            BoardDTO dto = new BoardDTO();
	            
	            dto.setBoardSeq(rs.getString("boardSeq"));
	            dto.setBoardTitle(rs.getString("boardTitle"));
	            dto.setBoardContents(rs.getString("boardContents"));
	            dto.setBoardRegdate(rs.getString("boardRegdate"));
	            dto.setBoardReportCnt(rs.getString("boardReportCnt"));
	            dto.setBoardCnt(rs.getString("boardCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BoardDAO.| findByRegdateBetween");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BoardDTO> findByTitleContains(String word) {
		
		final String SQL = "SELECT * FROM tblBoard WHERE boardTitle LIKE ? order by boardRegdate desc";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, "%" + word + "%");
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BoardDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            BoardDTO dto = new BoardDTO();
	            
	            dto.setBoardSeq(rs.getString("boardSeq"));
	            dto.setBoardTitle(rs.getString("boardTitle"));
	            dto.setBoardContents(rs.getString("boardContents"));
	            dto.setBoardRegdate(rs.getString("boardRegdate"));
	            dto.setBoardReportCnt(rs.getString("boardReportCnt"));
	            dto.setBoardCnt(rs.getString("boardCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BoardDAO.| findByTitleContains");
	        e.printStackTrace();
	    }
	    return null;
	}
	
}//End of class
















