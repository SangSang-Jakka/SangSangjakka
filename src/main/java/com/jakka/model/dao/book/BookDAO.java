package com.jakka.model.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.book.BookDTO;

public class BookDAO implements BasicDAO<BookDTO>, ReportCnt, ActiveStatus{

	private final static BookDAO DAO = new BookDAO();
	
	private BookDAO() {
		//외부 생성 방지
	}
	
	public static BookDAO getInstace() {
		
		return DAO;
		
	}//getInstace()
	
	@Override
	public int add(BookDTO dto) {
		
		final String SQL = "INSERT INTO tblBook (bookSeq, bookTitle, bookInfo, bookCover, bookRegdate, bookModDate, likeCnt, bookReviewCnt, bookScrapCnt, bookReportCnt, userSeq, parentBookSeq, rcmAgeSeq) VALUES ((SELECT NVL(MAX(bookSeq), 0) + 1 FROM tblBook), ?, ?, ?, default, NULL, default, default, default, default, ?, ?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			pstat.setString(1, dto.getBookTitle());
			pstat.setString(2, dto.getBookInfo());
			pstat.setString(3, dto.getBookCover());
			pstat.setString(4, dto.getUserSeq());
			pstat.setString(5, dto.getParentBookSeq());
			pstat.setString(6, dto.getRcmAgeSeq());
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| add");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public BookDTO get(String bookSeq) {
		
		final String SQL = "select * from tblBook where bookseq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
		){
			pstat.setString(1, bookSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				BookDTO dto = new BookDTO();
				
	            dto.setBookSeq(rs.getString("bookSeq"));
	            dto.setBookTitle(rs.getString("bookTitle"));
	            dto.setBookInfo(rs.getString("bookInfo"));
	            dto.setBookCover(rs.getString("bookCover"));
	            dto.setBookRegdate(rs.getString("bookRegdate"));
	            dto.setBookModDate(rs.getString("bookModDate"));
	            dto.setLikeCnt(rs.getString("likeCnt"));
	            dto.setBookReviewCnt(rs.getString("bookReviewCnt"));
	            dto.setBookScrapCnt(rs.getString("bookScrapCnt"));
	            dto.setBookReportCnt(rs.getString("bookReportCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setParentBookSeq(rs.getString("parentBookSeq"));
	            dto.setRcmAgeSeq(rs.getString("rcmAgeSeq"));
	            
	            return dto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("BookDAO.| get");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	@Override
	public ArrayList<BookDTO> listAll() {
		
		final String SQL = "select * from vwBook";
		
		try (
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
		){
			
			ArrayList<BookDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				BookDTO dto = new BookDTO();
				
				dto.setBookCover(rs.getString("bookCover"));
				dto.setBookInfo(rs.getString("bookInfo"));
				dto.setBookModDate(rs.getString("bookModDate"));
				dto.setBookRegdate(rs.getString("bookRegdate"));
				dto.setBookReportCnt(rs.getString("bookReportCnt"));
				dto.setBookReviewCnt(rs.getString("bookReviewCnt"));
				dto.setBookScrapCnt(rs.getString("bookScrapCnt"));
				dto.setBookSeq(rs.getString("bookSeq"));
				dto.setBookTitle(rs.getString("bookTitle"));
				dto.setLikeCnt(rs.getString("likeCnt"));
				dto.setParentBookSeq(rs.getString("parentBookSeq"));
				dto.setRcmAgeSeq(rs.getString("rcmAgeSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| listAll");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	//수정(제목, 한줄소개, 커버url, 추천연령)
	public int set(BookDTO dto) {
		
		final String SQL = "UPDATE tblBook SET bookTitle = ?, bookInfo = ?, bookCover = ?, bookModDate = SYSDATE, rcmAgeSeq = ? WHERE bookSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
		){
			
			pstat.setString(1, dto.getBookTitle());
			pstat.setString(2, dto.getBookInfo());
			pstat.setString(3, dto.getBookCover());
			pstat.setString(4, dto.getRcmAgeSeq());
			pstat.setString(5, dto.getUserSeq());
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| set");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	public int disabled(String bookSeq) {
		
		final String SQL = "delete from tblBookWhiteList where bookSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			pstat.setString(1, bookSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| disabled");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int activation(String bookSeq) {
		
		final String SQL = "insert into tblBookWhiteList(bookSeq) values(?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			pstat.setString(1, bookSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| activation");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public int disable(String bookSeq) {
		
		final String SQL = "delete from tblBookWhiteList where bookSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			
			pstat.setString(1, bookSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| disable");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
}//End of class
