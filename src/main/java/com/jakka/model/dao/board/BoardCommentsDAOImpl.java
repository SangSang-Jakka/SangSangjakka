package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.board.BoardCommentDTO;
import com.jakka.model.enums.AdminLog;
import com.jakka.model.enums.UserLog;

public class BoardCommentsDAOImpl implements BoardCommentsDAO{

	private static BoardCommentsDAOImpl DAO = new BoardCommentsDAOImpl();
	
	private BoardCommentsDAOImpl() {
		//외부 생성 방지
	}
	
	public static BoardCommentsDAOImpl getInstance() {
		
		return DAO;
		
	}//getInstance()
	
	//댓글 추가
	//유저번호, 부모글번호, 내용
	@Override
	public int add(BoardCommentDTO dto) {
		
		final String SQL = "insert into tblBoardComments (cmntSeq, userSeq, boardSeq, cmntContents, cmntRegdate) values((SELECT NVL(MAX(cmntSeq), 0) + 1 FROM tblBoardComments), ?, ?, ?, default)";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
			){
			conn.setAutoCommit(false);
			
			pstat.setString(1, dto.getUserSeq());
			pstat.setString(2, dto.getBoardSeq());
			pstat.setString(3, dto.getCmntContents());
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 부모글번호'" + dto.getBoardSeq() + "' 글번호'" + dto.getCmntSeq() + "' 글내용'" + dto.getCmntContents() + "'에 자유게시판 댓글을 '작성'했습니다.");
				log.setString(3, UserLog.BoardCommentCreated.getValue());
				log.executeUpdate();
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardCommentDAO.| add");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public BoardCommentDTO findById(String cmntSeq) {
		
		final String SQL = "select * from vwBoardComments where cmntSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, cmntSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				BoardCommentDTO dto = new BoardCommentDTO();
				
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setCmntContents(rs.getString("cmntContents"));
				dto.setCmntRegdate(rs.getString("cmntRegdate"));
				dto.setCmntReportCnt(rs.getString("cmntReportCnt"));
				dto.setCmntSeq(rs.getString("cmntSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				
				return dto;
				
			}
			
		} catch (Exception e) {
			System.out.println("BoardCommentDAO.| get");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@Override
	public ArrayList<BoardCommentDTO> findAll() {
		
		final String SQL = "select * from vwBoardComments order by cmntRegdate desc";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<BoardCommentDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				BoardCommentDTO dto = new BoardCommentDTO();
				
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setCmntContents(rs.getString("cmntContents"));
				dto.setCmntRegdate(rs.getString("cmntRegdate"));
				dto.setCmntReportCnt(rs.getString("cmntReportCnt"));
				dto.setCmntSeq(rs.getString("cmntSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BoardCommentDAO.| list");
			e.printStackTrace();
		}
		
		return null;
	}
	
	//블라인드 되지 않는 모든 댓글리스트
	public ArrayList<BoardCommentDTO> findAllWhite() {
		
		final String SQL = "select * from vwBoardCommentsWhite order by cmntRegdate desc";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<BoardCommentDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				BoardCommentDTO dto = new BoardCommentDTO();
				
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setCmntContents(rs.getString("cmntContents"));
				dto.setCmntRegdate(rs.getString("cmntRegdate"));
				dto.setCmntReportCnt(rs.getString("cmntReportCnt"));
				dto.setCmntSeq(rs.getString("cmntSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BoardCommentDAO.| list");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<BoardCommentDTO> findAllBlack() {
		
		final String SQL = "SELECT * FROM vwBoardCommentsBlack order by cmntRegdate desc";

	    try (Connection conn = DBUtil.open();
	         Statement stat = conn.createStatement();
	         ResultSet rs = stat.executeQuery(SQL)) {

	        ArrayList<BoardCommentDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            BoardCommentDTO dto = new BoardCommentDTO();
	            
	            dto.setCmntSeq(rs.getString("cmntSeq"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setBoardSeq(rs.getString("boardSeq"));
	            dto.setCmntContents(rs.getString("cmntContents"));
	            dto.setCmntReportCnt(rs.getString("cmntReportCnt"));
	            dto.setCmntRegdate(rs.getString("cmntRegdate"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BoardCommentsDAO.| list");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public ArrayList<BoardCommentDTO> findChild(String parentBoardSeq) {
		
		final String SQL = "select * from vwBoardComments where boardSeq = ? order by cmntRegdate desc";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, parentBoardSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<BoardCommentDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				BoardCommentDTO dto = new BoardCommentDTO();
				
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setCmntContents(rs.getString("cmntContents"));
				dto.setCmntRegdate(rs.getString("cmntRegdate"));
				dto.setCmntReportCnt(rs.getString("cmntReportCnt"));
				dto.setCmntSeq(rs.getString("cmntSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				
				list.add(dto);
				
			}
			
			rs.close();
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BoardCommentDAO.| child");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@Override
	//댓글 내용 수정
	public int save(BoardCommentDTO dto) {
		
		final String SQL = "update tblBoardComments set cmntContents = ? where cmntSeq = ?";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";

		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
			){
			conn.setAutoCommit(false);
			
			pstat.setString(1, dto.getCmntContents());

			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 부모글번호'" + dto.getBoardSeq() + "' 글번호'" + dto.getCmntSeq() + "' 글내용'" + dto.getCmntContents() + "'에 자유게시판 댓글을 '수정'했습니다.");
				log.setString(3, UserLog.BoardCommentEdited.getValue());
				log.executeUpdate();
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardCommentDAO.| set");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	//신고횟수 증가
	public int addReportCnt(String cmntSeq, String userSeq) {
		
		final String SQL = "insert into tblBoardCommentsReport(cmntSeq, userSeq) values(?, ?)";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";

		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
			){
			conn.setAutoCommit(false);
			
			pstat.setString(1, cmntSeq);
			pstat.setString(2, userSeq);
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, userSeq);
				log.setString(2, "사용자번호'" + userSeq + "'이 글번호'" + cmntSeq +"' 자유게시판글을 댓글을 '신고'했습니다.");
				log.setString(3, UserLog.BoardCreated.getValue());
				log.executeUpdate();
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| addReportCnt");
			e.printStackTrace();
		}
		
		return 0;
		
	}//addReportCnt()
	
	//비활성화
	public int disable(String cmntSeq, String adId) {
		
		final String SQL = "delete from tblBoardCommentWhiteList where cmntSeq = ?";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";

		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
				
		){
			conn.setAutoCommit(false);
			
			pstat.setString(1, cmntSeq);
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
			       log.setString(1, adId);
			       log.setString(2, "'" + adId + "'이 자유게시판 댓글번호'" + cmntSeq + "'을(를) '비활성화'했습니다.");
			       log.setString(3, AdminLog.BoardCommentDisabled.getValue());
			       log.executeUpdate();
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardCommentsDAO.| disable");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//활성화
	public int activation(String cmntSeq, String adId) {
		
		final String SQL = "insert into tblBoardCommentWhiteList(cmntSeq) values(?)";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";
		try (
			Connection conn = DBUtil.open();	
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
		){
			conn.setAutoCommit(false);
			
			pstat.setString(1, cmntSeq);
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
			       log.setString(1, adId);
			       log.setString(2, "'" + adId + "'이 자유게시판 댓글번호'" + cmntSeq + "'을(를) '활성화'했습니다.");
			       log.setString(3, AdminLog.BoardCommentEnabled.getValue());
			       log.executeUpdate();
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardCommentsDAO.| activation");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public boolean isReport(String cmntSeq, String userSeq) {
		
		final String SQL = "select count(*) from tblBoardCommentsReport where cmntSeq = ? and userSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			
			pstat.setString(1, cmntSeq);
			pstat.setString(2, userSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
			
		} catch (Exception e) {
			System.out.println("BoardCommentsDAO.| isReport");
			e.printStackTrace();
		}
		
		return false;
	}
	
}//End of class
