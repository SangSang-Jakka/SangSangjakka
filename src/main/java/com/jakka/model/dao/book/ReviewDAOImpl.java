package com.jakka.model.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.book.ReviewDTO;

public class ReviewDAOImpl implements ReviewDAO{

	private final static ReviewDAOImpl DAO = new ReviewDAOImpl();
	
	private ReviewDAOImpl() {
		//외부 생성 방지
	}
	
	public static ReviewDAOImpl getInstance() {
		return DAO;
	}//getInstance()
	
	@Override
	public int add(ReviewDTO dto) {
		
		final String SQL = "INSERT INTO tblReview (reviewSeq, reviewContents, userSeq, bookSeq, reviewRegdate) VALUES ((SELECT NVL(MAX(reviewSeq), 0) + 1 FROM tblReview), ?, ?, ?, default)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			pstat.setString(1, dto.getReviewContents());
			pstat.setString(2, dto.getUserSeq());
			pstat.setString(3, dto.getBookSeq());

			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("ReviewDAO.| add");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public ReviewDTO findById(String seq) {
		
		final String SQL = "SELECT * FROM tblReview WHERE reviewSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, seq);
	        
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	
	            ReviewDTO dto = new ReviewDTO();
	            
	            dto.setReviewSeq(rs.getString("reviewSeq"));
	            dto.setReviewContents(rs.getString("reviewContents"));
	            dto.setReviewLikeCnt(rs.getString("reviewLikeCnt"));
	            dto.setReviewReportCnt(rs.getString("reviewReportCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setBookSeq(rs.getString("bookSeq"));
	            dto.setReviewRegdate(rs.getString("reviewRegdate"));
	            
	            return dto;
	        }
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| findById");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ArrayList<ReviewDTO> findAll() {
		
		final String SQL = "select * from tblReview";
		
		try (
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
		){
			ArrayList<ReviewDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				ReviewDTO dto = new ReviewDTO();
				
				dto.setBookSeq(rs.getString("bookSeq"));
				dto.setReviewContents(rs.getString("reviewContents"));
				dto.setReviewLikeCnt(rs.getString("reviewLikeCnt"));
				dto.setReviewReportCnt(rs.getString("reviewReportCnt"));
				dto.setReviewSeq(rs.getString("reviewSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				
			}
			
			
		} catch (Exception e) {
			System.out.println("ReviewDAO.| listAll");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<ReviewDTO> findAllWhite() {
		
		 final String SQL = "SELECT * FROM vwReviewWhite order by reviewRegdate desc";

		    try (Connection conn = DBUtil.open();
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(SQL)) {

		        ArrayList<ReviewDTO> list = new ArrayList<>();
		        
		        while (rs.next()) {
		        	
		            ReviewDTO dto = new ReviewDTO();
		            
		            dto.setReviewSeq(rs.getString("reviewSeq"));
		            dto.setReviewContents(rs.getString("reviewContents"));
		            dto.setReviewLikeCnt(rs.getString("reviewLikeCnt"));
		            dto.setReviewReportCnt(rs.getString("reviewReportCnt"));
		            dto.setUserSeq(rs.getString("userSeq"));
		            dto.setBookSeq(rs.getString("bookSeq"));
		            dto.setReviewRegdate(rs.getString("reviewRegdate"));
		            
		            list.add(dto);
		        }
		        
		        return list;
		        
		    } catch (Exception e) {
		        System.out.println("ReviewDAO.| findAllWhite");
		        e.printStackTrace();
		    }
		    return null;
	}
	
	@Override
	public ArrayList<ReviewDTO> findAllBlack() {
		
		final String SQL = "SELECT * FROM vwBlackReview order by reviewRegdate desc";

	    try (Connection conn = DBUtil.open();
	         Statement stat = conn.createStatement();
	         ResultSet rs = stat.executeQuery(SQL)) {

	        ArrayList<ReviewDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            ReviewDTO dto = new ReviewDTO();
	            
	            dto.setReviewSeq(rs.getString("reviewSeq"));
	            dto.setReviewContents(rs.getString("reviewContents"));
	            dto.setReviewLikeCnt(rs.getString("reviewLikeCnt"));
	            dto.setReviewReportCnt(rs.getString("reviewReportCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setBookSeq(rs.getString("bookSeq"));
	            dto.setReviewRegdate(rs.getString("reviewRegdate"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| findAllBlack");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	//리뷰 내용 수정
	@Override
	public int save(ReviewDTO dto) {
		
		final String SQL = "UPDATE tblReview SET reviewContents = ? WHERE reviewSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, dto.getReviewContents());
	        pstmt.setString(2, dto.getReviewSeq());

	        int result = pstmt.executeUpdate(); 
	        
	        return result;
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| save");
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	@Override
	public int activation(String seq) {
		
		final String SQL = "INSERT INTO tblReviewWhiteList(reviewSeq) VALUES(?)";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, seq);
	        
	        int result = pstmt.executeUpdate();
	        
	        return result; 
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| activation");
	        e.printStackTrace();
	    }
	    
	    return 0;
	}
	
	@Override
	public int disable(String seq) {
		
		final String SQL = "DELETE FROM tblReviewWhiteList WHERE reviewSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL)) {
	    	
	    	pstat.setString(1, seq);
	        
	        int result = pstat.executeUpdate();
	        
	        return result;
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| disable");
	        e.printStackTrace();
	    }
	    
	    return 0;
	}
	
	@Override
	public int addReportCnt(String seq) {
		
		final String SQL = "UPDATE tblReview SET reviewReportCnt = reviewReportCnt + 1 WHERE reviewSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, seq);
	        
	        int result =  pstmt.executeUpdate();
	        
	        return result;
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| addReportCnt");
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	@Override
	public ArrayList<ReviewDTO> findChild(String parentSeq) {
		
		final String SQL = "SELECT * FROM tblReview WHERE bookSeq = ? order by reviewRegdate desc";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, parentSeq);
	        
	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<ReviewDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            ReviewDTO dto = new ReviewDTO();
	            
	            dto.setReviewSeq(rs.getString("reviewSeq"));
	            dto.setReviewContents(rs.getString("reviewContents"));
	            dto.setReviewLikeCnt(rs.getString("reviewLikeCnt"));
	            dto.setReviewReportCnt(rs.getString("reviewReportCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setBookSeq(rs.getString("bookSeq"));
	            dto.setReviewRegdate(rs.getString("reviewRegdate"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| findChild");
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
}//End of class
