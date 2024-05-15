package com.jakka.model.dao.book;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.enums.AdminLog;
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
	            dto.setUserNick(rs.getString("userNick"));
	            
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
		
		final String SQL = "update tblBook set shareCnt = shareCnt + 1 where bookSeq = ?";
		
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
		
		final String SQL = "update tblBook set shareCnt = shareCnt + 1 where bookSeq = ?";
		
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
//				dto.setBookReviewCnt(rs.getString("BookReviewCnt"));
//				dto.setBookScrapCnt(rs.getString("bookScrapCnt"));
				dto.setBookSeq(rs.getString("bookSeq"));
				dto.setBookTitle(rs.getString("bookTitle"));
//				dto.setLikeCnt(rs.getString("likeCnt"));
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
		
		final String ACTIONSQL = "insert into tblUserBookAction(userSeq, actionDate, bookSeq, actionCatSeq) "
				+ "values(?, default, ?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement action = conn.prepareStatement(ACTIONSQL);
		){
			conn.setAutoCommit(false);
			
			pstat.setString(1, userSeq);
			pstat.setString(2, bookSeq);
			
			int result = pstat.executeUpdate();
			
			if(result > 0) {
				
				action.setString(1, userSeq);
				action.setString(2, bookSeq);
				action.setString(3, BookAction.Like.getValue());
				action.executeUpdate();
			}
			
			conn.commit();
			
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
		
		final String ACTIONSQL = "insert into tblUserBookAction(userSeq, actionDate, bookSeq, actionCatSeq) "
				+ "values(?, default, ?, ?)";
		
		try (
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				PreparedStatement action = conn.prepareStatement(ACTIONSQL);
			){
				conn.setAutoCommit(false);
			
				
				pstat.setString(1, userSeq);
				pstat.setString(2, bookSeq);
				
				int result = pstat.executeUpdate();
				
				if(result > 0) {
					
					action.setString(1, userSeq);
					action.setString(2, bookSeq);
					action.setString(3, BookAction.Scrap.getValue());
					action.executeUpdate();
					
				}
				
				conn.commit();
				
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
			
			System.out.println(bookSeq);
			System.out.println(userSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				System.out.println(count);
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
	
	@Override
	public ArrayList<BookDTO> findAllAward() {
		
		final String SQL = "select * from vwAward order by awardRegdate desc, awardRank desc";
		
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
				
				dto.setAwardRegdate(rs.getString("awardRegdate"));
				dto.setAwardRank(rs.getString("awardRank"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| listAll");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//현재 명예의 전당 책 리스트
	
	@Override
	public ArrayList<BookDTO> findNowAward() {
		
		final String SQL = "SELECT * FROM vwAward WHERE EXTRACT(MONTH FROM awardRegdate) = EXTRACT(MONTH FROM SYSDATE) AND awardRank BETWEEN 1 AND 5";
		
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
				
				dto.setAwardRegdate(rs.getString("awardRegdate"));
				dto.setAwardRank(rs.getString("awardRank"));
				
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
	public ArrayList<BookDTO> findMonthAward(String month) {
	    final String SQL = "SELECT * FROM vwAward WHERE SUBSTR(awardRegdate, 4, 2) = ? AND awardRank BETWEEN 1 AND 5";
	    try (
	        Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL)
	    ) {
	        pstmt.setString(1, month);
	        ResultSet rs = pstmt.executeQuery();

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
	            dto.setAwardRegdate(rs.getString("awardRegdate"));
	            dto.setAwardRank(rs.getString("awardRank"));
	            list.add(dto);
	        }

	        return list;

	    } catch (Exception e) {
	        System.out.println("BookDAO.| findMonthAward");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	//리스트에는 수여받을 동화책번호5개, 등수, 수여한 관리자 아이디가 들어있어야함
	@Override
	public int presentAward(ArrayList<BookDTO> list, String adId) {
		
		final String SQL = "insert into tblAward(bookSeq, awardRegdate, awardRank) values(?, default, ?)";
		
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) "
				+ "values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
		){
			conn.setAutoCommit(false);
			
			for(int i = 0; i < 5; i++) {
				
				String bookSeq = list.get(i).getBookSeq();
				String awardRank = list.get(i).getAwardRank();
				
				
				pstat.setString(1, bookSeq);
//				pstat.setString(2, i + 1 + "");
				pstat.setString(2, awardRank);
				pstat.executeUpdate();
				
				log.setString(1, adId);
				log.setString(2, "'" + adId + "'이 동화책번호'" + bookSeq  + "'에게 " + awardRank + "등을 수여했습니다.");
				log.setString(3, AdminLog.BookAwarded.getValue());
				log.executeUpdate();
				
			}
			
			
			conn.commit();
			
			return 0;
			
		} catch (Exception e) {
			System.out.println("BookDAOImpl.| presentAward");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	// 신고가 있는 동화책 조회
	@Override
	public ArrayList<BookDTO> findAllReport() {
		
		final String SQL = "select * from vwBook where bookReportCnt > 0";
		
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
	
	// 신고가 없는 동화책 조회
	@Override
	public ArrayList<BookDTO> findAllNoReport() {
		
		final String SQL = "select * from vwBook where bookReportCnt = 0";
		
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
	public List<BookDTO> getShareCount(String month) {
		List<BookDTO> sharelist = new ArrayList<>();
        String sql = "SELECT *\r\n"
        		+ "FROM (\r\n"
        		+ "    SELECT *\r\n"
        		+ "    FROM tblBook \r\n"
        		+ "    WHERE TO_CHAR(bookregdate, 'YYYY/MM') = ?\r\n"
        		+ "      AND SHARECNT > 0\r\n"
        		+ "    ORDER BY SHARECNT DESC\r\n"
        		+ ") \r\n"
        		+ "WHERE ROWNUM <= 10";
       
        try(Connection conn = DBUtil.open();
    			PreparedStatement pstat = conn.prepareStatement(sql)) {
    		
    			pstat.setString(1, month);
    			
    			
    			ResultSet rs = pstat.executeQuery();
    			
    			
    			
    			 for (ResultSet row = rs; row.next(); ) {
    				
    				
    				BookDTO dto = new BookDTO();
    				
    			
    				
    				
    			
    				
    				dto.setBookTitle(rs.getString("bookTitle"));
    				dto.setShareCnt(rs.getString("shareCnt"));
    				dto.setBookInfo(rs.getString("bookInfo"));
    				dto.setBookRegdate(rs.getString("bookRegdate"));
    				
    				
    				sharelist.add(dto);
    			}
    			
    			return sharelist;
    			}
        catch (Exception e) {
			System.out.println("AdminDAOImpl.getInflowCountData");
			e.printStackTrace();
		}
        
        return null;
	}
	
	
	// 신고내역
	@Override
	public ArrayList<BookDTO> findByReport(String bookSeq) {
		
		final String SQL = "select * from vwReportUser where bookSeq = ? and userCatSeq = 16";
		
		try (
				Connection conn = DBUtil.open();
				PreparedStatement stat = conn.prepareStatement(SQL);
			
		){
			
			stat.setInt(1, Integer.parseInt(bookSeq));
			ResultSet rs = stat.executeQuery();
			
			ArrayList<BookDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				BookDTO dto = new BookDTO();
				
				
	            dto.setBookSeq(rs.getString("bookSeq"));
	            dto.setUserNick(rs.getString("userNick"));
	            dto.setReportDate(rs.getString("reportDate"));
	         
	            list.add(dto);

				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| get");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
	// 수상 삭제
	@Override
	public int delAward(String bookSeq) {
		
		final String SQL = "delete from tblAward where bookSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			
			pstat.setString(1, bookSeq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| remove");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public int getBookCount() {
		int bookCount =0;
		final String SQL = "select count(*) as totalbook from tblBook";
		

		 try (
			        Connection conn = DBUtil.open();
			        PreparedStatement pstat = conn.prepareStatement(SQL);
			    ) {
			        ResultSet rs = pstat.executeQuery();

			        if (rs.next()) {
			        	bookCount = rs.getInt("totalbook");
			        }

			    } catch (Exception e) {
			        System.out.println("UserDAOImpl.nUserCount");
			        e.printStackTrace();
			    }

			    return bookCount;
	}
	
	
	@Override
	public int getTodayBookCount(String today) {
		
		int todayBookCount =0;
		final String SQL = "select count(*) as todaybook from tblBook where TO_CHAR(bookregdate, 'YY/MM/dd') = ?";
		

		 try (
			        Connection conn = DBUtil.open();
			        PreparedStatement pstat = conn.prepareStatement(SQL);
			    ) {
			 
					pstat.setString(1, today);
			        ResultSet rs = pstat.executeQuery();

			        if (rs.next()) {
			        	todayBookCount = rs.getInt("todaybook");
			        }

			    } catch (Exception e) {
			        System.out.println("UserDAOImpl.nUserCount");
			        e.printStackTrace();
			    }

			    return todayBookCount;
	}
	
	
	@Override
	public int getPageCount() {
		int pageCount =0;
		final String SQL = "SELECT TRUNC(AVG(pagecount)) AS page\r\n"
				+ "FROM (\r\n"
				+ "    SELECT COUNT(pageseq) AS pagecount\r\n"
				+ "    FROM tblPage\r\n"
				+ "    GROUP BY bookseq\r\n"
				+ ") subquery";
		

		 try (
			        Connection conn = DBUtil.open();
			        PreparedStatement pstat = conn.prepareStatement(SQL);
			    ) {
			        ResultSet rs = pstat.executeQuery();

			        if (rs.next()) {
			        	pageCount = rs.getInt("page");
			        }

			    } catch (Exception e) {
			        System.out.println("UserDAOImpl.nUserCount");
			        e.printStackTrace();
			    }

			    return pageCount;
	}

	
	@Override
	public Map<String, Integer> makeBook(String year) {
		 Map<String, Integer> bookResult = new HashMap<>();

		    String SQL = "SELECT TO_CHAR(bookregdate, 'YY/MM') AS monthYear,\r\n"
		    		+ "       COUNT(*) AS bookCount\r\n"
		    		+ "FROM tblBook\r\n"
		    		+ "WHERE EXTRACT(YEAR FROM bookregdate) = ?\r\n"
		    		+ "GROUP BY TO_CHAR(bookregdate, 'YY/MM')";

		    try {
		    	 Connection conn = DBUtil.open();
		         PreparedStatement pstat = conn.prepareStatement(SQL);
		         String yearFull = "20" + year;
		         pstat.setString(1, yearFull); // 연도 값을 설정

		         ResultSet rs = pstat.executeQuery();

		         while (rs.next()) {
		             String monthYear = rs.getString("monthYear");
		             int bookCount = rs.getInt("bookCount");

		             // 결과를 Map에 저장
		             bookResult.put(monthYear, bookCount);
		         }

		         conn.close(); // 연결 닫기

		     } catch (Exception e) {
		         System.out.println("UserDAOImpl.makeBook");
		         e.printStackTrace();
		     }
		     return bookResult;
	}
	
	
	// userSeq로 해당 사용자의 동화책 찾기
	@Override
	public ArrayList<BookDTO> findByUserSeq(String userSeq) {
		
		final String SQL = "select * from vwBook where userSeq = ?";
		
		
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
		){
			pstat.setInt(1, Integer.parseInt(userSeq));
			
			ResultSet rs = pstat.executeQuery();
			
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
	            
	            list.add(dto);
	            
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BookDAO.| get");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}//End of class
