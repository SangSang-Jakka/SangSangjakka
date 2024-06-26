package com.jakka.model.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.book.PageDTO;

public class PageDAOImpl implements PageDAO{

	private final static PageDAOImpl DAO = new PageDAOImpl();
	
	private PageDAOImpl() {
		//외부 생성 방지
	}
	
	public static PageDAOImpl getInstance() {
		
		return DAO;
		
	}//getInstance()
	
	@Override
	public ArrayList<PageDTO> findAll() {
		
		final String SQL = "SELECT * FROM tblPage";

	    try (Connection conn = DBUtil.open();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(SQL)) {

	        ArrayList<PageDTO> list = new ArrayList<>();
	        
	        while (rs.next()) {
	        	
	            PageDTO dto = new PageDTO();
	            
	            dto.setPageSeq(rs.getString("pageSeq"));
	            dto.setBookSeq(rs.getString("bookSeq"));
	            dto.setPageUrl(rs.getString("pageUrl"));
	            dto.setPageContents(rs.getString("pageContents"));
	            dto.setCmntYN(rs.getString("cmntYN"));
	            dto.setImgYN(rs.getString("imgYN"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        System.out.println("PageDAO.| findAll");
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	@Override
	public int add(PageDTO dto) {
		
		final String SQL = "INSERT INTO tblPage (pageSeq, bookSeq, pageUrl, pageContents, cmntYN, imgYN, pageRegdate) VALUES (?, ?, ?, ?, ?, ?, DEFAULT)";

		try (Connection conn = DBUtil.open();
		  PreparedStatement pstmt = conn.prepareStatement(SQL)) {
		 pstmt.setString(1, dto.getPageSeq());
		 pstmt.setString(2, dto.getBookSeq());
		 pstmt.setString(3, dto.getPageUrl());
		 pstmt.setString(4, dto.getPageContents());
		 pstmt.setString(5, dto.getCmntYN());
		 pstmt.setString(6, dto.getImgYN());
		 return pstmt.executeUpdate();
		} catch (Exception e) {
		 System.out.println("PageDAO.| add");
		 e.printStackTrace();
		}
		return 0;
	}
	
	//동화책번호 + 페이지번호
	//bookSeq가 4번 page1번 findById("1,4")
	@Override
	public PageDTO findById(String seq) {
	    String[] parts = seq.split(",");
	    return findById(parts[0], parts[1]);
	}
	
	public PageDTO findById(String pageSeq, String bookSeq) {
		
		final String SQL = "SELECT * FROM tblPage WHERE pageSeq = ? AND bookSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL)) {
	        pstat.setInt(1, Integer.parseInt(pageSeq));
	        pstat.setInt(2, Integer.parseInt(bookSeq));
	        ResultSet rs = pstat.executeQuery();
	        if (rs.next()) {
	            PageDTO dto = new PageDTO();
	            dto.setPageSeq(rs.getString("pageSeq"));
	            dto.setBookSeq(rs.getString("bookSeq"));
	            dto.setPageUrl(rs.getString("pageUrl"));
	            dto.setPageContents(rs.getString("pageContents"));
	            dto.setCmntYN(rs.getString("cmntYN"));
	            dto.setImgYN(rs.getString("imgYN"));
	            return dto;
	        }
	    } catch (Exception e) {
	        System.out.println("PageDAO.| findById");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public int save(PageDTO dto) {
		
		final String SQL = "UPDATE tblPage SET pageUrl = ?, pageContents = ?, cmntYN = ?, imgYN = ? WHERE pageSeq = ? AND bookSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL)) {
	    	
	    	pstat.setString(1, dto.getPageUrl());
	    	pstat.setString(2, dto.getPageContents());
	    	pstat.setString(3, dto.getCmntYN());
	    	pstat.setString(4, dto.getImgYN());
	    	pstat.setString(5, dto.getPageSeq());
	    	pstat.setString(6, dto.getBookSeq());

	        return pstat.executeUpdate();
	        
	    } catch (Exception e) {
	        System.out.println("PageDAO.| save");
	        e.printStackTrace();
	    }
	    
	    return 0;
	}
	
	@Override
	public HashMap<Integer, PageDTO> findPages(String bookSeq) {
		
		final String SQL = "SELECT * FROM tblPage WHERE bookSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL)) {
	    	
	    	pstat.setString(1, bookSeq);
	        
	        ResultSet rs = pstat.executeQuery();

	        HashMap<Integer, PageDTO> pageMap = new HashMap<>();
	        
	        while (rs.next()) {
	        	
	            PageDTO dto = new PageDTO();
	            
	            dto.setPageSeq(rs.getString("pageSeq"));
	            dto.setBookSeq(rs.getString("bookSeq"));
	            dto.setPageUrl(rs.getString("pageUrl"));
	            dto.setPageContents(rs.getString("pageContents"));
	            dto.setCmntYN(rs.getString("cmntYN"));
	            dto.setImgYN(rs.getString("imgYN"));
	            
	            pageMap.put(Integer.parseInt(dto.getPageSeq()), dto);
	        }
	        
	        return pageMap;
	        
	    } catch (Exception e) {
	        System.out.println("PageDAO.| findPages");
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	public PageDTO lastpage(String bookSeq) {
		
		final String SQL = "SELECT MAX(pageseq) as pageSeq FROM tblPage WHERE bookSeq = ?";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL)) {
	    	if (bookSeq == null) {
	    		PageDTO dto = new PageDTO();
	    		dto.setPageSeq("0");
	    		return dto;
	    	}
	        pstat.setInt(1, Integer.parseInt(bookSeq));
	        ResultSet rs = pstat.executeQuery();
	        if (rs.next()) {
	            PageDTO dto = new PageDTO();
	            dto.setPageSeq(rs.getString("pageSeq"));
	            return dto;
	        }
	    } catch (Exception e) {
	        System.out.println("PageDAO.| lastpage");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public int del(String pageSeq, String bookSeq) {
		
		final String SQL = "delete FROM tblPage WHERE pageSeq = ? and bookSeq = ?";

	    try (Connection conn = DBUtil.open();
	        PreparedStatement pstat = conn.prepareStatement(SQL)) {
	        pstat.setInt(1, Integer.parseInt(pageSeq));
	        pstat.setInt(2, Integer.parseInt(bookSeq));
	        return pstat.executeUpdate();
	    } catch (Exception e) {
	        System.out.println("PageDAO.| del");
	        e.printStackTrace();
	    }
	    return 0;
	}
	
}//End of class
