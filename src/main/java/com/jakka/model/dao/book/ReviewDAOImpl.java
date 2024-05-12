package com.jakka.model.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.book.ReviewDTO;
import com.jakka.model.enums.AdminLog;
import com.jakka.model.enums.BookAction;
import com.jakka.model.enums.UserLog;

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
		
		final String SQL = "INSERT INTO tblReview (reviewSeq, reviewContents, userSeq, bookSeq, reviewRegdate) "
				+ "VALUES ((SELECT NVL(MAX(reviewSeq), 0) + 1 FROM tblReview), ?, ?, ?, default)";
		
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
			
			pstat.setString(1, dto.getReviewContents());
			pstat.setString(2, dto.getUserSeq());
			pstat.setString(3, dto.getBookSeq());

			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 부모글번호'" + dto.getBookSeq() +"' 글내용'" + dto.getReviewContents()  + "' 동화책 리뷰를 '작성'했습니다.");
				log.setString(3, UserLog.BookReviewCreated.getValue());
				log.executeUpdate();
				
				action.setString(1, dto.getUserSeq());
				action.setString(2, dto.getBookSeq());
				action.setString(3, BookAction.Review.getValue());
				action.executeUpdate();
				
			}
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("ReviewDAO.| add");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public ReviewDTO findById(String reviewSeq) {
		
		final String SQL = "SELECT * FROM vwReview WHERE reviewSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	
	        pstmt.setString(1, reviewSeq);
	        
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
		
		final String SQL = "select * from vwReview order by reviewRegdate desc";
		
		try (
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
		){
			ArrayList<ReviewDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				ReviewDTO dto = new ReviewDTO();
				
				dto.setBookSeq(rs.getString("bookSeq"));
				dto.setReviewRegdate(rs.getString("reviewRegdate"));
				dto.setReviewContents(rs.getString("reviewContents"));
				dto.setReviewLikeCnt(rs.getString("reviewLikeCnt"));
				dto.setReviewReportCnt(rs.getString("reviewReportCnt"));
				dto.setReviewSeq(rs.getString("reviewSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserNick(rs.getString("userNick"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
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
		            dto.setUserNick(rs.getString("userNick"));
		            
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
		
		final String SQL = "SELECT * FROM vwReviewBlack order by reviewRegdate desc";

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
	            dto.setUserNick(rs.getString("userNick"));
	            
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
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL);
	    	 PreparedStatement log = conn.prepareStatement(LOGSQL);
	    ) {
	    	
	    	conn.setAutoCommit(false);
	    	
	        pstmt.setString(1, dto.getReviewContents());
	        pstmt.setString(2, dto.getReviewSeq());

	        int result = pstmt.executeUpdate(); 
	        
	        if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 부모글번호'" + dto.getBookSeq() + "' 글번호'" + dto.getReviewSeq() +"' 글내용'" + dto.getReviewContents() + "' 동화책리뷰를 '수정'했습니다.");
				log.setString(3, UserLog.BookReviewEdited.getValue());
				log.executeUpdate();
			}
	        
	        conn.commit();
	        
	        return result;
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| save");
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	@Override
	public int activation(String reviewSeq, String adId) {
		
		final String SQL = "INSERT INTO tblReviewWhiteList(reviewSeq) VALUES(?)";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL);
	    	 PreparedStatement log = conn.prepareStatement(LOGSQL);
	    ) {
	    	
	    	conn.setAutoCommit(false);
	    	
	        pstmt.setString(1, reviewSeq);
	        
	        int result = pstmt.executeUpdate();
	        
	        if (result > 0) {
	        	log.setString(1, adId);
	        	log.setString(2, "'" + adId + "'이 동화책 리뷰번호'" + reviewSeq + "'을(를) '활성화'했습니다.");
	        	log.setString(3, AdminLog.BookReviewEnabled.getValue());
	        	log.executeUpdate();
	        }
	        
	        conn.commit();
	        
	        return result; 
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| activation");
	        e.printStackTrace();
	    }
	    
	    return 0;
	}
	
	@Override
	public int disable(String reviewSeq, String adId) {
		
		final String SQL = "DELETE FROM tblReviewWhiteList WHERE reviewSeq = ?";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";
		
	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL);
	    	 PreparedStatement log = conn.prepareStatement(LOGSQL);
	    ) {
	    	
	    	conn.setAutoCommit(false);
	    	
	    	pstat.setString(1, reviewSeq);
	        
	        int result = pstat.executeUpdate();
	        
	        if (result > 0) {
	        	log.setString(1, adId);
	        	log.setString(2, "'" + adId + "'이 동화책 리뷰번호'" + reviewSeq + "'을(를) '비활성화'했습니다.");
	        	log.setString(3, AdminLog.BookReviewDisabled.getValue());
	        	log.executeUpdate();
	        }
	        
	        conn.commit();
	        
	        return result;
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| disable");
	        e.printStackTrace();
	    }
	    
	    return 0;
	}
	
	@Override
	public int addReportCnt(String reviewSeq, String userSeq) {
		
		final String SQL = "insert into tblReviewReport(reviewSeq, userSeq) values(?, ?)";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";
		
	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(SQL);
	    	 PreparedStatement log = conn.prepareStatement(LOGSQL);
	    ) {
	    	conn.setAutoCommit(false);
	    	
	        pstmt.setString(1, reviewSeq);
	        pstmt.setString(2, userSeq);
	        
	        int result =  pstmt.executeUpdate();
	        
	        if (result > 0) {
				log.setString(1, userSeq);
				log.setString(2, "사용자번호'" + userSeq + "'이 글번호'" + reviewSeq +"' 동화책리뷰를 '신고'했습니다.");
				log.setString(3, UserLog.BoardReported.getValue());
				log.executeUpdate();
			}
	        
	        conn.commit();
	        
	        return result;
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| addReportCnt");
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	@Override
	public ArrayList<ReviewDTO> findChildWhite(String parentSeq) {
		
		final String SQL = "SELECT * FROM vwReviewWhite WHERE bookSeq = ? order by reviewRegdate desc";
		//final String SQL = "SELECT * FROM (SELECT rownum as rn, vwReviewWhite.* FROM vwReviewWhite WHERE bookSeq = ? order by reviewRegdate desc) WHERE rn BETWEEN ? AND ?";

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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| findChildWhite");
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	@Override
	public ArrayList<ReviewDTO> findChild(String parentSeq) {
		
		final String SQL = "SELECT * FROM vwReview WHERE bookSeq = ? order by reviewRegdate desc";

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
	            dto.setUserNick(rs.getString("userNick"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| findChild");
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	@Override
	public int addLikeCnt(String reviewSeq, String userSeq) {
		
		final String SQL = "insert into tblReviewLike(reviewSeq, userSeq) values(?, ?)";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL); 
		){
			conn.setAutoCommit(false);
			
			pstat.setString(1, reviewSeq);
			pstat.setString(2, userSeq);
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, userSeq);
				log.setString(2, "사용자번호'" + userSeq + "'이  글번호'" + reviewSeq + "' 동화책 리뷰를 '좋아요'했습니다.");
				log.setString(3, UserLog.BookLiked.getValue());
				log.executeUpdate();
			}
			
			return result;
			
		} catch (Exception e) {
			System.out.println("ReviewDAOImpl.| addLikeCnt");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public boolean isLike(String reviewSeq, String userSeq) {
		
		final String SQL = "select count(*) from tblReviewLike where reviewSeq = ? and userSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			
			pstat.setString(1, reviewSeq);
			pstat.setString(2, userSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
			
		} catch (Exception e) {
			System.out.println("ReviewDAOImpl.| isLike");
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	@Override
	public boolean isReport(String reviewSeq, String userSeq) {
		
		final String SQL = "select count(*) from tblReviewReport where reviewSeq = ? and userSeq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			
			pstat.setString(1, reviewSeq);
			pstat.setString(2, userSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
			
		} catch (Exception e) {
			System.out.println("ReviewDAOImpl.| isReport");
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	// 신고 있는 리뷰만 조회
	@Override
	public ArrayList<ReviewDTO> findAllReport() {
		
		final String SQL = "select * from vwReview where reviewReportCnt > 0";
		
		try (
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
		){
			ArrayList<ReviewDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				ReviewDTO dto = new ReviewDTO();
				
				dto.setBookSeq(rs.getString("bookSeq"));
				dto.setReviewRegdate(rs.getString("reviewRegdate"));
				dto.setReviewContents(rs.getString("reviewContents"));
				dto.setReviewLikeCnt(rs.getString("reviewLikeCnt"));
				dto.setReviewReportCnt(rs.getString("reviewReportCnt"));
				dto.setReviewSeq(rs.getString("reviewSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserNick(rs.getString("userNick"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("ReviewDAO.| listAll");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	// 신고 없는 리뷰만 조회
		@Override
		public ArrayList<ReviewDTO> findAllNoReport() {
			
			final String SQL = "select * from vwReview where reviewReportCnt = 0";
			
			try (
				Connection conn = DBUtil.open();
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(SQL);
			){
				ArrayList<ReviewDTO> list = new ArrayList<>();
				
				while(rs.next()) {
					
					ReviewDTO dto = new ReviewDTO();
					
					dto.setBookSeq(rs.getString("bookSeq"));
					dto.setReviewRegdate(rs.getString("reviewRegdate"));
					dto.setReviewContents(rs.getString("reviewContents"));
					dto.setReviewLikeCnt(rs.getString("reviewLikeCnt"));
					dto.setReviewReportCnt(rs.getString("reviewReportCnt"));
					dto.setReviewSeq(rs.getString("reviewSeq"));
					dto.setUserSeq(rs.getString("userSeq"));
					dto.setUserNick(rs.getString("userNick"));
					
					list.add(dto);
					
				}
				
				return list;
				
				
			} catch (Exception e) {
				System.out.println("ReviewDAO.| listAll");
				e.printStackTrace();
			}
			
			return null;
		}
	
	
	@Override
	public ArrayList<ReviewDTO> findChildWhite(String bookSeq, int startIndex, int endIndex) {
		 final String SQL = "SELECT * FROM (SELECT rownum as rn, vwReviewWhite.* FROM vwReviewWhite WHERE bookSeq = ? ORDER BY reviewRegdate DESC) WHERE rn BETWEEN ? AND ?";

		    try (Connection conn = DBUtil.open();
		         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
		        pstmt.setString(1, bookSeq);
		        pstmt.setInt(2, startIndex + 1); // Start index for Oracle rownum
		        pstmt.setInt(3, endIndex + 1); // End index for Oracle rownum

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
		        System.out.println("ReviewDAO.| findChildWhite");
		        e.printStackTrace();
		    }

		    return null;
	}
	

	@Override
	public int reviewTotal(String bookSeq) {
		
		String SQL = "SELECT COUNT(*) FROM tblReview WHERE bookSeq = ?";

		try (Connection conn = DBUtil.open();
		     PreparedStatement pstat = conn.prepareStatement(SQL)) {
		    pstat.setString(1, bookSeq);

		    try (ResultSet rs = pstat.executeQuery()) {
		        if (rs.next()) {
		            return rs.getInt(1); // 첫 번째 열의 값 반환
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

		return 0;
	}
	
	@Override
	public ReviewDTO edit(ReviewDTO dto) {
	    final String UPDATE_SQL = "update tblReview set reviewContents = ? where ReviewSeq = ?";
	    final String SELECT_SQL = "select reviewContents from tblReview where ReviewSeq = ?";

	    try (
	        Connection conn = DBUtil.open();
	        PreparedStatement updatePstat = conn.prepareStatement(UPDATE_SQL);
	        PreparedStatement selectPstat = conn.prepareStatement(SELECT_SQL);
	    ) {
	        // UPDATE 쿼리 실행
	        updatePstat.setString(1, dto.getReviewContents());
	        updatePstat.setString(2, dto.getReviewSeq());
	        int result = updatePstat.executeUpdate();
	        System.out.println("Update result: " + result);

	        // 변경된 내용 확인을 위한 SELECT 쿼리 실행
	        selectPstat.setString(1, dto.getReviewSeq());
	        ResultSet resultSet = selectPstat.executeQuery();
	        if (resultSet.next()) {
	            String updatedContents = resultSet.getString("reviewContents");
	            System.out.println("Updated contents: " + updatedContents);
	            // 변경된 내용을 ReviewDTO 객체에 설정
	            dto.setReviewContents(updatedContents);
	        }

	        // 수정된 ReviewDTO 객체 반환
	        return dto;
	    } catch (Exception e) {
	        System.out.println("Exception occurred: " + e.getMessage());
	        e.printStackTrace();
	    }

	    // 예외 발생 시 null 반환
	    return null;
	}

	@Override
	public int reviewDel(String reviewSeq) {
		
		final String SQL = "DELETE FROM tblReviewWhiteList WHERE reviewSeq = ?";
		
	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL);
	    ) {
	    	
	  
	    	
	    	pstat.setString(1, reviewSeq);
	        
	        int result = pstat.executeUpdate();
	        
	        if (result > 0) {
	        	
	        	return result;
	        }

	    } catch (Exception e) {
	        System.out.println("ReviewDAO.| disable");
	        e.printStackTrace();
	    }
	    
	    return 0;
	}
	
	
}//End of class
