package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.board.BoardCommentDTO;

public class BoardCommentsDAO implements BasicDAO<BoardCommentDTO>{

	private final static BoardCommentsDAO DAO = new BoardCommentsDAO();
	
	private BoardCommentsDAO() {
		//외부 생성 방지
	}
	
	public static BoardCommentsDAO getInstance() {
		
		return DAO;
		
	}//getInstance()
	
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
	public BoardCommentDTO get(String cmntSeq) {
		
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
	public ArrayList<BoardCommentDTO> list() {
		
		final String SQL = "select * from tblBoardComments";
		
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
	
	public ArrayList<BoardCommentDTO> list(String parentBoardSeq) {
		
		final String SQL = "select * from tblBoardComments where boardSeq = ?";
		
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
			System.out.println("BoardCommentDAO.| list");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@Override
	public int set(BoardCommentDTO dto) {
		
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
	
	
}//End of class
