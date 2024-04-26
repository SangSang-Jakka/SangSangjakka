package com.jakka;

import com.jakka.model.dao.board.NoticeDAO;

public class Main {
	
	public static void main(String[] args) {
		
		NoticeDAO dao = new NoticeDAO();

		int result =  dao.addNoticeCnt("7");
		
		System.out.println(result);
		
	}//main
	
}//End of class
