package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.board.BoardCommentDTO;

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
		
		final String SQL = "insert into tblBoardComments (cmntSeq, userSeq, boardSeq, cmntContents, cmntReportCnt, cmntRegdate) values((SELECT NVL(MAX(cmntSeq), 0) + 1 FROM tblBoardComments), ?, ?, ?, default, default)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getUserSeq());
			pstat.setString(2, dto.getBoardSeq());
			pstat.setString(3, dto.getCmntContents());
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardCommentDAO.| add");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public BoardCommentDTO findById(String cmntSeq) {
		
		final String SQL = "select * from tblBoardComments where cmntSeq = ?";
		
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
		
		final String SQL = "select * from tblBoardComments order by cmntRegdate desc";
		
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
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getCmntContents());

			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardCommentDAO.| set");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	//신고횟수 증가
	public int addReportCnt(String cmntSeq) {
		
		final String SQL = "update tblBoardComments set cmntReportCnt = cmntReportCnt + 1 where boardSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, cmntSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| addReportCnt");
			e.printStackTrace();
		}
		
		return 0;
		
	}//addReportCnt()
	
	//비활성화
	public int disable(String cmntSeq) {
		
		final String SQL = "delete from tblBoardCommentWhiteList where cmntSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			pstat.setString(1, cmntSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardCommentsDAO.| disable");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//활성화
	public int activation(String cmntSeq) {
		
		final String SQL = "insert into tblBoardCommentWhiteList(cmntSeq) values(?)";
		
		try (
			Connection conn = DBUtil.open();	
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			pstat.setString(1, cmntSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardCommentsDAO.| activation");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
}//End of class
