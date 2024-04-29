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

public class BoardDAO implements BasicDAO<BoardDTO>, Cnt, ReportCnt{

	private static final BoardDAO DAO = new BoardDAO();
	
	private BoardDAO() {
		//외부 생성 방지
	}
	
	public static BoardDAO getInstance() {
		
		return DAO;
		
	}//getInstance()
	
	//자유게시판 전체글
	@Override
	public ArrayList<BoardDTO> listAll() {
		
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
	public ArrayList<BoardDTO> list() {
		
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
	
	//게시판 글 추가
	//제목, 내용, 작성자seq
	@Override
	public int add(BoardDTO dto) {
		
		final String SQL = "INSERT INTO tblBoard (boardSeq, boardTitle, boardContents, boardRegdate, boardReportCnt, boardCnt, userSeq) VALUES ((SELECT NVL(MAX(boardSeq), 0) + 1 FROM tblBoard), ?, ?, DEFAULT, DEFAULT, DEFAULT, ?)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getBoardTitle());
			pstat.setString(2, dto.getBoardContents());
			pstat.setString(3, dto.getUserSeq());
			
			int result = pstat.executeUpdate();
			
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| add");
			e.printStackTrace();
		}
		
		return 0;
		
	}//add()
	
	//게시글 수정(이름, 내용)
	@Override
	public int set(BoardDTO dto) {
		
		final String SQL = "update tblBoard set boardTitle = ?, boardContents = ? where boardSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getBoardTitle());
			pstat.setString(2, dto.getBoardContents());
			pstat.setString(3, dto.getBoardSeq());

			int result = pstat.executeUpdate();
			
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
	public int addReportCnt(String boardSeq) {
		
		final String SQL = "update tblBoard set boardReportCnt = boardReportCnt + 1 where boardSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, boardSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| addReportCnt");
			e.printStackTrace();
		}
		
		return 0;
		
	}//addReportCnt()
	
	//특정 게시글 정보 가져오기
	@Override
	public BoardDTO get(String boardSeq) {
		
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
	public int disable(String boardSeq) {
		
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
	public int activation(String boardSeq) {
		
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
	
}//End of class
















