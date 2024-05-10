package com.jakka.model.dao.book;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;
import com.jakka.model.enums.BookAction;
import com.jakka.model.enums.UserLog;

public class BookDAOImpl implements BookDAO{

	private final static BookDAOImpl DAO = new BookDAOImpl();
	
	private BookDAOImpl() {
		//외부 생성 방지
	}
	
	public static BookDAOImpl getInstace() {
		
		return DAO;
		
	}//getInstace()
	
	@Override
	public int add(BookDTO dto) {
	    final String GET_NEXT_SEQ_SQL = "SELECT NVL(MAX(bookSeq), 0) + 1 AS nextSeq FROM tblBook";
	    final String INSERT_SQL = "INSERT INTO tblBook (bookSeq, bookTitle, bookInfo, bookCover, bookRegdate, bookModDate, userSeq, parentBookSeq, rcmAgeSeq) VALUES (?, ?, ?, ?, default, NULL, ?, ?, ?)";
	    
	    int bookSeq = 0;

	    try (Connection conn = DBUtil.open();
	         Statement stmt = conn.createStatement();
	         PreparedStatement pstat = conn.prepareStatement(INSERT_SQL);
	    ) {
	        // First, get the next bookSeq
	        ResultSet rs = stmt.executeQuery(GET_NEXT_SEQ_SQL);
	        if (rs.next()) {
	            bookSeq = rs.getInt("nextSeq");  // Ensure that this retrieves an integer
	        }

	        // Now, use this bookSeq to insert the new book
	        pstat.setInt(1, bookSeq);  // Set bookSeq as integer directly
	        pstat.setString(2, dto.getBookTitle());
	        pstat.setString(3, dto.getBookInfo());
	        pstat.setString(4, dto.getBookCover());
	        pstat.setInt(5, Integer.parseInt(dto.getUserSeq()));  // Ensure userSeq is converted properly to integer
	        pstat.setObject(6, dto.getParentBookSeq() != null ? Integer.parseInt(dto.getParentBookSeq()) : null);  // Convert to integer or handle null
	        pstat.setInt(7, Integer.parseInt(dto.getRcmAgeSeq()));  // Ensure rcmAgeSeq is converted properly to integer
	        
	        pstat.executeUpdate();
	        
	    } catch (SQLException e) {
	        System.out.println("Error adding book: " + e.getMessage());
	        throw new RuntimeException(e);  // Rethrow or handle as necessary
	    }
	    
	    
	    
	    return bookSeq;
	}
	
	@Override
	public BookDTO findById(String bookSeq) {
		
		final String SQL = "select * from vwBook where bookseq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
		){
			pstat.setInt(1, Integer.parseInt(bookSeq));
			
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
	public ArrayList<BookDTO> findAll() {
		
		final String SQL = "select * from vwBook order by bookRegdate desc";
		
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
				dto.setUserNick(rs.getString("userNick"));
				
				dto.setBookCnt(rs.getString("bookCnt"));
				
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
	public int save(BookDTO dto) {
		
		final String SQL = "UPDATE tblBook SET bookTitle = ?, bookInfo = ?, bookCover = ?, bookModDate = SYSDATE, rcmAgeSeq = ? WHERE bookSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
		){
			
			pstat.setString(1, dto.getBookTitle());
			pstat.setString(2, dto.getBookInfo());
			pstat.setString(3, dto.getBookCover());
			pstat.setString(4, dto.getRcmAgeSeq());
			pstat.setString(5, dto.getBookSeq());
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| set");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	public int activation(String bookSeq, String adId) {
		
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
	public int disable(String bookSeq, String adId) {
		
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
	
	//오버로딩
	@Override
	public int addCnt(String bookSeq) {
		
		final String SQL = "update tblBookShare set shareCnt = shareCnt + 1 where bookSeq = ?";
		
		try (
				
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
					
				){
				
				pstat.setString(1, bookSeq);
				
				int result = pstat.executeUpdate();
				
				return result;
				
			} catch (Exception e) {
			System.out.println("BoardDAOImpl.| addCnt");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public int addCnt(String bookSeq, String userSeq) {
		
		final String SQL = "update tblBookShare set shareCnt = shareCnt + 1 where bookSeq = ?";
		
		final String ACTIONSQL = "insert into tblUserBookAction(userSeq, actionDate, bookSeq, actionCatSeq) "
				+ "values(?, default, ?, ?)";
		
		try (
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				PreparedStatement action = conn.prepareStatement(ACTIONSQL);
				){
				
				pstat.setString(1, bookSeq);
				
				int result = pstat.executeUpdate();
				
				if(result > 0) {
					
					action.setString(1, userSeq);
					action.setString(2, bookSeq);
					action.setString(3, BookAction.Inquiry.getValue());
					action.executeUpdate();
					
				}
				
				return result;
				
			} catch (Exception e) {
			System.out.println("BoardDAOImpl.| addCnt");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public int addReportCnt(String bookSeq, String userSeq) {
		
		final String SQL = "insert into tblBookShareReport(bookSeq, userSeq) values(?, ?)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, bookSeq);
			pstat.setString(2, userSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BoardDAO.| addReportCnt");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public ArrayList<BookDTO> findAllBlack() {
		
		final String SQL = "select * from vwBookBlack order by bookRegdate desc";

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
				dto.setUserNick(rs.getString("userNick"));
				
				dto.setBookCnt(rs.getString("bookCnt"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BookDAOImpl.| findAllBlack");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<BookDTO> findAllWhite() {
		
		final String SQL = "SELECT * FROM vwBookWhite order by bookRegdate desc";
		
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
					dto.setUserNick(rs.getString("userNick"));
					
					dto.setBookCnt(rs.getString("bookCnt"));
					
					list.add(dto);
					
				}
				
				return list;
				
		} catch (Exception e) {
			System.out.println("BookDAOImpl.| findAllWhite");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public int addLikeCnt(String bookSeq, String userSeq) {
		
		final String SQL = "insert into tblLike(userSeq, bookSeq) values(?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			
			pstat.setString(1, userSeq);
			pstat.setString(2, bookSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BookDAOImpl.| addLike");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public int addScrap(String bookSeq, String userSeq) {
		
		final String SQL = "insert into tblScrap(userSeq, bookSeq) values(?, ?)";
		
		try (
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
			){
				
				pstat.setString(1, userSeq);
				pstat.setString(2, bookSeq);
				
				int result = pstat.executeUpdate();
				
				return result;
				
		} catch (Exception e) {
			System.out.println("BookDAOImpl.| addScrap");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public ArrayList<BookDTO> findByContentsContains(String word) {
		
		final String SQL = "SELECT * FROM vwBook WHERE bookInfo LIKE ? ORDER BY bookRegdate DESC";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, "%" + word + "%");
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BookDAO.| findByContentsContains");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BookDTO> findByNick(String Nick) {
		final String SQL = "SELECT * FROM vwBook WHERE userNick = ? ORDER BY bookRegdate DESC";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, Nick);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        return list;
	    } catch (Exception e) {
	        System.out.println("BookDAO.| findByNick");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BookDTO> findByRegdateAfter(String date) {
		
		final String SQL = "SELECT * FROM vwBook WHERE bookRegdate > TO_DATE(?, 'YYYY-MM-DD') ORDER BY bookRegdate DESC";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, date);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BookDAO.| findByRegdateAfter");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BookDTO> findByRegdateBefore(String date) {
		
		final String SQL = "SELECT * FROM vwBook WHERE bookRegdate < TO_DATE(?, 'YYYY-MM-DD') ORDER BY bookRegdate DESC";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, date);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BookDAO.| findByRegdateBefore");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BookDTO> findByRegdateBetween(String startDate, String endDate) {
		
		final String SQL = "SELECT * FROM vwBook WHERE bookRegdate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ORDER BY bookRegdate DESC";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, startDate);
	        pstmt.setString(2, endDate);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BookDAO.| findByRegdateBetween");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BookDTO> findByTitleContains(String word) {
		
		final String SQL = "SELECT * FROM vwBook WHERE bookTitle LIKE ? order by bookRegdate desc";
		
	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, "%" + word + "%");
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        return list;
	    } catch (Exception e) {
	        System.out.println("BookDAO.| findByTitleContains");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BookDTO> findAllLike(String likeUserSeq) {
		
		final String SQL = "SELECT * FROM vwLike WHERE likeUserSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, likeUserSeq);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	    	
	        System.out.println("BookDAO.| findAllLike");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BookDTO> findAllScrap(String scrapUserSeq) {
		
		final String SQL = "SELECT * FROM vwScrap WHERE scrapUserSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, scrapUserSeq);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BookDAO.| findAllScrap");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public boolean isLike(String bookSeq, String userSeq) {
		
		final String SQL = "select count(*) from tblLike where bookSeq = ? and userSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			
			pstat.setString(1, bookSeq);
			pstat.setString(2, userSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
			
		} catch (Exception e) {
			System.out.println("BookDAO.| isLike");
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public int addScrapCnt(String bookSeq, String userSeq) {
		
		final String SQL = "inser into tblScrap(userSeq, bookSeq) values(?, ?)";

		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
		){
			pstat.setString(1, userSeq);
			pstat.setString(2, bookSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| addScrapCnt");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public boolean isReport(String bookSeq, String userSeq) {
		
		final String SQL = "select count(*) from tblBookShareReport where bookSeq = ? and userSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			
			pstat.setString(1, bookSeq);
			pstat.setString(2, userSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
			
		} catch (Exception e) {
			System.out.println("BookDAO.| isReport");
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean isScrap(String bookSeq, String userSeq) {
		
		final String SQL = "select count(*) from tblScrap where bookSeq = ? and userSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			
			pstat.setString(1, bookSeq);
			pstat.setString(2, userSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
			
		} catch (Exception e) {
			System.out.println("BookDAO.| isScrap");
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public int whiteTotalCnt(HashMap<String, String> map) {
		
		String where = "";

		if(map.get("search").equals("y")) {
			
			where = String.format("where %s like '%%%s%%'"
					, map.get("column")
					, map.get("word"));
		}
		
		String sql = String.format("select count(*) as cnt from vwBookWhite %s", where);
		
		try(
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(sql);
		) {


			ResultSet rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	@Override
	public ArrayList<BookDTO> findAllWhite(HashMap<String, String> map) {
		
		String where = "where rnum BETWEEN ? AND ?";
		String col = "";
		
		if (map.get("search").equals("y")) {
			col = col + String.format(" where %s like '%%%s%%'"
							, map.get("column")
							, map.get("word"));
		}
		
		String sql = String.format("SELECT bookSeq, bookTitle, bookInfo, bookRegdate, bookReportCnt, bookCnt, userSeq, userNick, bookReviewCnt, bookScrapCnt, likeCnt, bookCover, bookModDate, parentBookSeq, rcmAgeSeq " +
                "FROM (SELECT ROWNUM RNUM, f.bookSeq, f.bookTitle, f.bookInfo, f.bookRegdate, f.bookReportCnt, f.bookCnt, f.userSeq, f.userNick, f.bookReviewCnt, f.bookScrapCnt, f.likeCnt, f.bookCover, f.bookModDate, f.parentBookSeq, f.rcmAgeSeq " +
                "FROM (SELECT * FROM vwBookWhite %s ORDER BY bookRegdate desc) f) " +
                "%s", col, where);
		
		try (
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(sql);
			){
				pstat.setString(1, map.get("begin"));
				pstat.setString(2, map.get("end"));
				
				ResultSet rs = pstat.executeQuery();
				
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
					dto.setUserNick(rs.getString("userNick"));
					
					dto.setBookCnt(rs.getString("bookCnt"));
					
					list.add(dto);
					
				}
				
				return list;
				
		} catch (Exception e) {
			System.out.println("BookDAOImpl.| findAllWhite");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<BookDTO> findByContentsContains(HashMap<String, String> map) {
		
		final String SQL = "SELECT * FROM vwBook WHERE bookInfo LIKE ? and rnum BETWEEN ? AND ? ORDER BY bookRegdate DESC";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, "%" + map.get("word") + "%");
	        pstmt.setString(2, map.get("begin"));
	        pstmt.setString(3, map.get("end"));
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("BookDAO.| findByContentsContains");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BookDTO> findByNick(HashMap<String, String> map) {
		
		final String SQL = "SELECT * FROM vwBook WHERE userNick = ? and rnum BETWEEN ? AND ? ORDER BY bookRegdate DESC";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, map.get("word"));
	        pstmt.setString(2, map.get("begin"));
	        pstmt.setString(3, map.get("end"));
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        return list;
	    } catch (Exception e) {
	        System.out.println("BookDAO.| findByNick");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<BookDTO> findByTitleContains(HashMap<String, String> map) {
		final String SQL = "SELECT * FROM vwBook WHERE bookTitle LIKE ? and rnum BETWEEN ? AND ? order by bookRegdate desc";
		
	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, "%" + map.get("word") + "%");
	        pstmt.setString(2, map.get("begin"));
	        pstmt.setString(3, map.get("end"));
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BookDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            dto.setBookCnt(rs.getString("bookCnt"));
	            
	            list.add(dto);
	        }
	        return list;
	    } catch (Exception e) {
	        System.out.println("BookDAO.| findByTitleContains");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public void createBookFolder(String userId, String bookSeq, ServletContext context) {
		
		final String BASE_DIRECTORY = "/generated/";
		// Get the real path to the WEB-INF directory
	    String realPath = context.getRealPath(BASE_DIRECTORY);
	    String folderPath = realPath + userId + "/" + bookSeq;
	    File userFolder = new File(folderPath);

        if (!userFolder.exists()) {
            boolean created = userFolder.mkdirs();
            if (created) {
                System.out.println("User folder created: " + folderPath);
            } else {
                System.err.println("Failed to create user folder: " + folderPath);
            }
        } else {
            System.out.println("User folder already exists: " + folderPath);
        }
		
	}
	
	//동화책 완성시
	@Override
	public int complete(BookDTO dto) {
		
		final String SQL = "insert into tblBookWhiteList(bookSeq) values(?)";
		
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) "
				+ "values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";
		
	    final String ACTIONSQL = "insert into tblUserBookAction(userSeq, actionDate, bookSeq, actionCatSeq) "
				+ "values(?, default, ?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
			PreparedStatement action = conn.prepareStatement(ACTIONSQL);
		){
			conn.setAutoCommit(false);
			
			pstat.setString(1, dto.getBookSeq());
			
			int result = pstat.executeUpdate();
			
			if(result > 0) {
	        	
	        	log.setString(1, dto.getUserSeq());
	        	log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 제목'" + dto.getBookTitle() +"' 글내용'" + dto.getBookInfo()  + "' 동화책을 '작성'했습니다.");
	        	log.setString(3, UserLog.BookCreated.getValue());
	        	log.executeUpdate();
	        	
	        	action.setString(1, dto.getUserSeq());
	        	action.setString(2, dto.getBookSeq());
	        	action.setString(3, BookAction.CreateBook.getValue());
	        	action.executeUpdate();
	        	
	        	
	        }
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| activation");
			e.printStackTrace();
		}
		
		return 0;
	}
	
}//End of class
