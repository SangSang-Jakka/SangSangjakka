package com.jakka;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dto.board.NoticeDTO;

public class Test {
	
	public static void main(String[] args) {
	
		NoticeDAO noticeDAO = DAOManager.getNoticeDAO();
		
		NoticeDTO dto = new NoticeDTO();
		dto.setNoticeTitle("게시판 작성공지");
		dto.setNoticeContents("게시판 작성공지");
		dto.setAdId("admin2463");
		
		int result = noticeDAO.add(dto);
		
		System.out.println(result);
	}
	
	
}

