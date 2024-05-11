package com.jakka.model.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dto.admin.AdminDTO;
import com.jakka.model.dto.book.BookDTO;

public class AdminDAOImpl implements AdminDAO{
	
	private final static AdminDAOImpl instance = new AdminDAOImpl();
	
	private AdminDAOImpl() {
		//외부 생성 방지
	}
	
	public static AdminDAO getInstance() {
		
		return instance;
		
	}//getInstance()

	//전체 관리자 리스트(루트관리자 제외)
	public ArrayList<AdminDTO> findAll() {
		
		final String SQL = "select * from tblAdmin where adLv = 2";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<AdminDTO> list = new ArrayList<>();
			
			 for (ResultSet row = rs; row.next(); ) {
				
				AdminDTO dto = new AdminDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setAdName(rs.getString("adName"));
				dto.setAdNick(rs.getString("adNick"));
				dto.setAdAddress(rs.getString("adAddress"));
				dto.setAdTel(rs.getString("adTel"));
				dto.setAdLv(rs.getString("adLv"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| list");
			e.printStackTrace();
		}
		
		
		return null;
	}//list()
	
	//관리자 한명의 정보를 가져올때
	public AdminDTO findById(String Id) {
		
		final String SQL = "select * from tblAdmin where adId = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, Id);
			
			ResultSet rs = pstat.executeQuery();
			
			
			if (rs.next()) {
				
				AdminDTO dto = new AdminDTO();
				
				dto.setAdId(rs.getString("adId"));
				dto.setAdPw(rs.getString("adPw"));
				dto.setAdName(rs.getString("adName"));
				dto.setAdAddress(rs.getString("adAddress"));
				dto.setAdLv(rs.getString("adLv"));
				dto.setAdTel(rs.getString("adTel"));
				dto.setAdNick(rs.getString("adNick"));
				
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| get");
			e.printStackTrace();
		}
		
		
		return null;
		
	}//get()
	
	//관리자 추가
	// 아이디, 이름, 닉네임, 주소, 전화번호를 입력
	public int add(AdminDTO dto) {
		
		final String SQL = "insert into tblAdmin (adId, adPw, adName, adNick, adAddress, adTel, adLv) values (?, default, ?, ?, ?, ?, default)";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			) {
			
			pstat.setString(1, dto.getAdId());
			pstat.setString(2, dto.getAdName());
			pstat.setString(3, dto.getAdNick());
			pstat.setString(4, dto.getAdAddress());
			pstat.setString(5, dto.getAdTel());
			
			int result = pstat.executeUpdate(); 
			
			return result;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| add");
			e.printStackTrace();
			
		}
		
		return 0;
		
	}//add()
	
	//관리자 비밀번호 변경
	public int savePw(AdminDTO dto) {
		
	 	final String SQL = "update tblAdmin set adPw = ? where adId = ?";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			) {
			
			pstat.setString(1, dto.getAdPw());
			pstat.setString(2, dto.getAdId());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| setPw");
			e.printStackTrace();
		}
		
		return 0;
	}//setPw()
	
	//관리자 개인정보 수정(닉네임, 주소, 전화번호)
	public int save(AdminDTO dto) {
	    final String SQL = "UPDATE tblAdmin SET adNick = ?, adAddress = ?, adTel = ? WHERE adId = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL)) {

	        pstat.setString(1, dto.getAdNick());
	        pstat.setString(2, dto.getAdAddress());
	        pstat.setString(3, dto.getAdTel());
	        pstat.setString(4, dto.getAdId());

	        int result = pstat.executeUpdate();
	        
	        return result;
	    } catch (Exception e) {
	        System.out.println("AdminDAO.| set");
	        e.printStackTrace();
	        
	    }
	    
	    return 0;
	    
	}//set()
	
	
	// 아이디, 비밀번호 유효 여부 리턴
	public boolean isValid(AdminDTO dto) {
		
		// 맞는 정보인지 여부를 담을 지역 변수 선언하고 초기값 false 넣어주기
		boolean isValid = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			String sql = "select * from tblAdmin where adId = ? and adPw = ?";
			pstat = conn.prepareStatement(sql);
			
			// ? 에 값 바인딩
			pstat.setString(1, dto.getAdId());
			pstat.setString(2, dto.getAdPw());
			// query 문 수행하고 결과 받기
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				// 만약 select 된 row가 있으면
				isValid=true;
			}
			
		} catch (Exception e) {
			System.out.println("AdminDAOImpl.isValid");
			e.printStackTrace();
		}
		
		return isValid;
		
	}
	
	
	// 관리자 로그인
	public AdminDTO login(AdminDTO dto) {
		
		final String SQL = "select * from tblAdmin where adId = ? and adPw = ?";
		
		try(Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL)) {
		
			pstat.setString(1, dto.getAdId());
			pstat.setString(2, dto.getAdPw());
			
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				AdminDTO result = new AdminDTO();
				
				result.setAdName(rs.getString("adName"));
				result.setAdLv(rs.getString("adLv"));		
				result.setAdTel(rs.getString("adTel"));
				result.setAdAddress(rs.getString("adAddress"));
				result.setAdNick(rs.getString("adNick"));
		
				return result;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| login");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	//관리자 로그목록
	
		public ArrayList<AdminDTO> log(String Id) {
		
		final String SQL = "select l.adlogseq, l.adlogdate, l.adid, c.adcatcontents, l.adlogcontents from tblAdLog l join tblAdcat c on l.adcatseq = c.adcatseq where adid =?";
		
		try(Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL)) {
		
			pstat.setString(1, Id);
			
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<AdminDTO> list = new ArrayList<>();
			
			 for (ResultSet row = rs; row.next(); ) {
				
				AdminDTO dto = new AdminDTO();
				
				dto.setAdLogSeq(rs.getString("adLogSeq"));
				dto.setAdLogDate(rs.getString("adLogDate"));
				dto.setAdId(rs.getString("adId"));
				dto.setAdCatContents(rs.getString("adCatContents"));
				dto.setAdLogContents(rs.getString("adLogContents"));
				
				list.add(dto);
			}
			
			return list;
			
		
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| login");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
		
		@Override
		public int remove(String id) {
			
			final String SQL = "delete from tblAdmin where adId= ?";
			
			try (
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				
					
			){
				
				
				pstat.setString(1, id);
				
				return pstat.executeUpdate();
				

				
				
			} catch (Exception e) {
				System.out.println("UserDAO.| disable");
				e.printStackTrace();
			}
			
			return 0;
		}
		
		
		@Override
		 public List<String> getYear() {
	        List<String> years = new ArrayList<>();
	        String sql = "SELECT DISTINCT SUBSTR(userRegdate, 1, 2) AS year FROM tblUser ORDER BY year";

	        try (
	        		Connection conn = DBUtil.open();
	        		PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {

	            while (rs.next()) {
	                String year = rs.getString("year");
	                years.add(year);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return years;
	    }
		
		
		/*
		 * public static List<AdminDTO> getInflowCountData(String month) {
		 * List<AdminDTO> result = new ArrayList<>(); String sql =
		 * "select * from vw_inflowcount"; try (Connection conn = DBUtil.open();
		 * PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs =
		 * pstmt.executeQuery()) {
		 * 
		 * 
		 * 
		 * while (rs.next()) { String registrationMonth =
		 * rs.getString("registration_month"); String inflowname =
		 * rs.getString("inflowname"); int inflowCount = rs.getInt("inflow_count");
		 * 
		 * InflowCountData data = new InflowCountData(registrationMonth, inflowname,
		 * inflowCount); result.add(data); }
		 * 
		 * rs.close(); stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		 * 
		 * return result; }
		 */
	
		
		public List<AdminDTO> getInflowCountData(String month) {
	        List<AdminDTO> result = new ArrayList<>();
	        String sql = "SELECT TO_CHAR(u.userregdate, 'YYYY/MM') AS registrationMonth,\r\n"
	        		+ "       c.inflowName,\r\n"
	        		+ "       COUNT(i.inflowcatseq) AS inflowCount\r\n"
	        		+ "FROM tbluserinflow i\r\n"
	        		+ "INNER JOIN tblUser u ON i.userseq = u.userseq\r\n"
	        		+ "INNER JOIN tblinflowcat c ON i.inflowcatseq = c.inflowcatseq\r\n"
	        		+ "WHERE TO_CHAR(u.userregdate, 'YYYY/MM') = ? \r\n"
	        		+ "GROUP BY TO_CHAR(u.userregdate, 'YYYY/MM'), c.inflowname\r\n"
	        		+ "ORDER BY COUNT(i.inflowcatseq) DESC";
	       
	        try(Connection conn = DBUtil.open();
	    			PreparedStatement pstat = conn.prepareStatement(sql)) {
	    		
	    			pstat.setString(1, month);
	    			
	    			
	    			ResultSet rs = pstat.executeQuery();
	    			
	    			
	    			
	    			 for (ResultSet row = rs; row.next(); ) {
	    				
	    				AdminDTO dto = new AdminDTO();
	    				
	    			
	    				
	    				//dto.setRegistrationMonth(rs.getString("registrationMonth"));
	    				//dto.setInflowCount(rs.getString("inflowCount"));
	    				//dto.setInflowname(rs.getString("inflowName"));
	    				
	    				
	    				result.add(dto);
	    			}
	    			
	    			return result;
	    			}
	        catch (Exception e) {
				System.out.println("AdminDAOImpl.getInflowCountData");
				e.printStackTrace();
			}
	        
	        return null;
	    }
		
		
}//End of class
