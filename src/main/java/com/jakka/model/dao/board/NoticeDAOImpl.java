package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.Search;
import com.jakka.model.dto.board.NoticeDTO;

public class NoticeDAOImpl implements NoticeDAO{
	
	private final static NoticeDAOImpl DAO = new NoticeDAOImpl();
	
	private NoticeDAOImpl() {
		//외부 생성 방지
	}
	
	public static NoticeDAOImpl getInstance() {
		return DAO;
	}//getInstance()
	
	//전체 공지사항 리스트
	@Override
	public ArrayList<NoticeDTO> findAll() {
		
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
	public int save(NoticeDTO dto) {
		
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
	
	//조회수 증가
	@Override
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
	public NoticeDTO findById(String noticeSeq) {
		
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
	
	//공지사항 고정
	@Override
	public int fixed(String noticeSeq) {
		
		final String SQL = "insert into tblNoticeFix(noticeSeq) values(?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			
			pstat.setString(1, noticeSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.| fixed");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//공지사항 고정 해제
	public int Unfixing(String noticeSeq) {
		
		final String SQL = "delete from tblNoticeFix where noticeSeq = ?";
		
		try (
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
			){
				
				pstat.setString(1, noticeSeq);
				
				int result = pstat.executeUpdate();
				
				return result;
		} catch (Exception e) {
			System.out.println("NoticeDAO.| Unfixing");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//공지사항 삭제
	public int remove(String noticeSeq) {
		
		final String noticeFixSQL = "delete from tblNoticeFix where noticeSeq = ?";
		final String noticeSQL = "delete from tblNotice where noticeSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement fpstat = conn.prepareStatement(noticeFixSQL);
			PreparedStatement npstat = conn.prepareStatement(noticeSQL);
		){
			
			conn.setAutoCommit(false);
			
			fpstat.setString(1, noticeSeq);
			npstat.setString(1, noticeSeq);
			
			//고정글 fpstat부터 실행, 이후 공지사항npstat실행
			int result = (fpstat.executeUpdate() + npstat.executeUpdate());
			
			conn.commit();
			
			//고정글 + 공지사항 삭제 : 2반환
			//공지사항만 삭제 : 1반환
			return result;
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.| remove");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public ArrayList<NoticeDTO> findByContentsContains(String word) {
		
		final String SQL = "select * from tblNotice where noticeContents like ? order by noticeregdate desc";
		
		try (
				Connection connection = DBUtil.open();
				PreparedStatement pstat = connection.prepareStatement(SQL);
					
		){
			pstat.setString(1, "%" + word + "%");
			
			ResultSet rs = pstat.executeQuery();
			
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
			
			rs.close();
			
			return list;
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.| findByContentsContains");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	@Override
	public ArrayList<NoticeDTO> findByNick(String Nick) {
		
		final String SQL = "select n.noticeSeq, n.noticetitle, n.noticecontents, n.noticeregdate, a.adnick from tblNotice n inner join tblAdmin a on n.adId = a.adId where a.adNick = ? order by n.noticeregdate desc;";
		
		try (
			Connection connection = DBUtil.open();
			PreparedStatement pstat = connection.prepareStatement(SQL);
				
		){
			pstat.setString(1, Nick);
			
			ResultSet rs = pstat.executeQuery();
			
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
			
			rs.close();
			
			return list;
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.| findByNick");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<NoticeDTO> findByRegdateAfter(String date) {
		
		final String SQL = "SELECT * FROM tblNotice WHERE noticeRegdate > TO_DATE(?, 'YYYY-MM-DD') ORDER BY noticeRegdate DESC";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, date);
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<NoticeDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	            NoticeDTO dto = new NoticeDTO();
	            
	            dto.setNoticeSeq(rs.getString("noticeSeq"));
	            dto.setNoticeTitle(rs.getString("noticeTitle"));
	            dto.setNoticeContents(rs.getString("noticeContents"));
	            dto.setNoticeRegdate(rs.getString("noticeRegdate"));
	            dto.setNoticeCnt(rs.getString("noticeCnt"));
	            dto.setAdId(rs.getString("adId"));
	            
	            list.add(dto);
	        }
	        
	        rs.close();
	        
	        return list;
	    } catch (Exception e) {
	        System.out.println("NoticeDAO.| findByRegdateAfter");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<NoticeDTO> findByRegdateBefore(String date) {
		
		final String SQL = "SELECT * FROM tblNotice WHERE noticeRegdate < TO_DATE(?, 'YYYY-MM-DD') ORDER BY noticeRegdate DESC";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, date);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<NoticeDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            NoticeDTO dto = new NoticeDTO();
	            
	            dto.setNoticeSeq(rs.getString("noticeSeq"));
	            dto.setNoticeTitle(rs.getString("noticeTitle"));
	            dto.setNoticeContents(rs.getString("noticeContents"));
	            dto.setNoticeRegdate(rs.getString("noticeRegdate"));
	            dto.setNoticeCnt(rs.getString("noticeCnt"));
	            dto.setAdId(rs.getString("adId"));
	            
	            list.add(dto);
	        }
	        
	        rs.close();
	        
	        return list;
	    } catch (Exception e) {
	        System.out.println("NoticeDAO.| findByRegdateBefore");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<NoticeDTO> findByRegdateBetween(String startDate, String endDate) {
		
		final String SQL = "SELECT * FROM tblNotice WHERE noticeRegdate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ORDER BY noticeRegdate DESC";

	    try (
	    	Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, startDate);
	        pstmt.setString(2, endDate);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<NoticeDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            NoticeDTO dto = new NoticeDTO();
	            
	            dto.setNoticeSeq(rs.getString("noticeSeq"));
	            dto.setNoticeTitle(rs.getString("noticeTitle"));
	            dto.setNoticeContents(rs.getString("noticeContents"));
	            dto.setNoticeRegdate(rs.getString("noticeRegdate"));
	            dto.setNoticeCnt(rs.getString("noticeCnt"));
	            dto.setAdId(rs.getString("adId"));
	            
	            list.add(dto);
	        }
	        
	        rs.close();
	        
	        return list;
	    } catch (Exception e) {
	        System.out.println("NoticeDAO.| findByRegdateBetween");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<NoticeDTO> findByTitleContains(String word) {
		
		final String SQL = "select * from tblNotice where noticeTitle like ? order by noticeregdate desc";
		
		try (
				Connection connection = DBUtil.open();
				PreparedStatement pstat = connection.prepareStatement(SQL);
					
		){
			pstat.setString(1, "%" + word + "%");
			
			ResultSet rs = pstat.executeQuery();
			
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
			
			rs.close();
			
			return list;
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.| findByTitleContains");
			e.printStackTrace();
		}
		
		return null;
	}
	
}//End of class


















