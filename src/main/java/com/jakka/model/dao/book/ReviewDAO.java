package com.jakka.model.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.book.ReviewDTO;

public class ReviewDAO implements BasicDAO<ReviewDTO>{

	private final static ReviewDAO DAO = new ReviewDAO();
	
	private ReviewDAO() {
		//외부 생성 방지
	}
	
	public static ReviewDAO getInstance() {
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
	public ReviewDTO get(String seq) {
		
		
		
		return null;
	}
	
	@Override
	public ArrayList<ReviewDTO> listAll() {
		
		final String SQL = "select * from tblPage";
		
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
	public int set(ReviewDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int disable() {
		
		
		return 0;
		
	}//disable()
	
	public int activation() {
		
		
		return 0;
	}//activation()
	
}//End of class
