package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.board.NoticeDTO;

public class NoticeDAO implements BasicDAO<NoticeDTO>{
	
	private final static NoticeDAO DAO = new NoticeDAO();
	
	private NoticeDAO() {
		//외부 생성 방지
	}
	
	public static NoticeDAO getInstance() {
		return DAO;
	}//getInstance()
	
	//전체 공지사항 리스트
	@Override
	public ArrayList<NoticeDTO> list() {
		
		final String sql = "select * from tblNotice";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
				
			){
			
			ArrayList<NoticeDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				NoticeDTO dto = new NoticeDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setNoticeCnt(rs.getString("noticeCnt"));
				dto.setNoticeContents(rs.getString("noticeContents"));
				dto.setNoticeRegdate(rs.getString("noticeRegdate"));
				dto.setNoticeSeq(rs.getString("noticeSeq"));
				dto.setNoticeTitle(rs.getString("noticeTitle"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.| list");
			e.printStackTrace();
		}
		
		return null;
		
	}//list()
	
	
	//공지사항글 추가
	@Override
	public int add(NoticeDTO dto) {
		
		final String SQL = "insert into tblNotice (noticeSeq, noticeTitle, noticeContents, noticeRegdate, noticeCnt, adId) values ((SELECT NVL(MAX(noticeSeq),0)+1 FROM tblNotice), ?, ?, default, default, ?)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getNoticeTitle());
			pstat.setString(2, dto.getNoticeContents());
			pstat.setString(3, dto.getAdId());
			
			int result = pstat.executeUpdate(); 
			
			return result;
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.| add");
			e.printStackTrace();
		}
		
		
		return 0;
		
	}//add()

	public void remove() {
		
		
		
	}//list()

	//게시글 수정(제목, 내용)
	@Override
	public int set(NoticeDTO dto) {
		
		final String sql = "update tblNotice set noticeTitle = ?, noticeContents = ? where noticeSeq = ?";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			) {
			
			pstat.setString(1, dto.getNoticeTitle());
			pstat.setString(2, dto.getNoticeContents());
			pstat.setString(3, dto.getNoticeSeq());
			
			int result = pstat.executeUpdate(); 
			
			return result;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| set");
			e.printStackTrace();
		}
		
		return 0;
		
	}//setTitle()
	
	public int addCnt(String noticeSeq) {
		
		final String SQL = "update tblNotice set noticeCnt = noticeCnt + 1 where noticeSeq = ?";
		
		try (
				
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, noticeSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.| addNoticeCnt");
			e.printStackTrace();
		}
		
		return 0;
		
	}//addCnt()
	
	@Override
	public NoticeDTO get(String noticeSeq) {
		
		final String SQL = "select * from tblNotice where noticeSeq = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, noticeSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				NoticeDTO dto = new NoticeDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setNoticeCnt(rs.getString("noticeCnt"));
				dto.setNoticeContents(rs.getString("noticeContents"));
				dto.setNoticeRegdate(rs.getString("noticeRegdate"));
				dto.setNoticeSeq(rs.getString("noticeSeq"));
				dto.setNoticeTitle(rs.getString("noticeTitle"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.| get");
			e.printStackTrace();
		}
		
		return null;
	}//get()
	
	
}//End of class


















