package com.jakka.controller.board.notice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.board.NoticeDTO;

@WebServlet("/board/notice/list.do")
public class NoticeList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//페이징
		String page = req.getParameter("page");
		
		int nowPage = 0;	//현재 페이지 번호
		int totalCount = 0;	//총 게시물 수
		int pageSize = 10;	//한 페이지에서 출력할 게시물 수
		int totalPage = 0;	//총 페이지 수
		int begin = 0;		//페이징 시작 위치
		int end = 0;		//페이지 끝 위치
		int n = 0;			
		int loop = 0;
		int blockSize = 10;	//페이지 개수
		
		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String search = "n";	//목록보기(n), 검색하기(y)
		
		
		if((column != null && word != null)) {
			search = "y";
		} else {
			search = "n";
			
			column = "";
			word = "";
		}
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("search", search);	//n, y
		map.put("column", column);
		map.put("word", word);
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		
		HttpSession session = req.getSession();
		
		//조회수 관련
		session.setAttribute("read", "n");
		
		NoticeDAO dao = DAOManager.getNoticeDAO();
		
		ArrayList<NoticeDTO> fix = dao.findAllFix();
		ArrayList<NoticeDTO> list = dao.findAll(map);
		
		if(fix != null) {
			for(NoticeDTO dto : fix) {
				dto.setNoticeRegdate(dto.getNoticeRegdate().substring(0, 10));
				String title = dto.getNoticeTitle();
				
				if (title.length() > 20) {
					title = title.substring(0, 20) + "..";
				}
				
				//제목 > 태그 > 이스케이프
				title = title.replace(">", "&gt;").replace("<", "&lt;");
				dto.setNoticeTitle(title);
			}
			
			totalCount += fix.size();
		}
		
		if(list != null) {
		//데이터 조작
			for (NoticeDTO dto : list) {
				
				dto.setNoticeRegdate(dto.getNoticeRegdate().substring(0, 10));
				String title = dto.getNoticeTitle();
				
				if (title.length() > 20) {
					title = title.substring(0, 20) + "..";
				}
				
				//제목 > 태그 > 이스케이프
				title = title.replace(">", "&gt;").replace("<", "&lt;");
				dto.setNoticeTitle(title);
				
			}
			
			totalCount += dao.totalCnt(map);
			
		}
		
		//총 게시물? 총페이지 수?
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		
		//페이지 바 작업
		StringBuilder builder = new StringBuilder();
		
		loop = 1;	//루프 변수(10바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1;		//페이지 번호 역할
		
		//이전 10페이지
		if(n == 1) {
			builder.append(String.format(" <a href='#!'>[이전 %d페이지]</a> ",blockSize));
				
		} else {
			builder.append(String.format(" <a href='/sangsangjakka/board/notice/list.do?page=%d&column=%s&word=%s'>[이전 %d페이지]</a> ", n - 1, column, word, blockSize));
		}
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (n == nowPage) {
				builder.append(String.format(" <a href='#!' style='color: tomato;'>%d</a> ", n));
			} else {
				builder.append(String.format(" <a href='/sangsangjakka/board/notice/list.do?page=%d&column=%s&word=%s'>%d</a> ", n, column, word, n));
			}
			
			loop++;
			n++; 
		}
		
		//다음 10페이지
		if(n >= totalPage) {
			builder.append(String.format(" <a href='#!'>[다음 %d페이지]</a> ", blockSize));
		}else {
			builder.append(String.format(" <a href='/sangsangjakka/board/notice/list.do?page=%d&column=%s&word=%s'>[다음 %d페이지]</a> ", n, column, word, blockSize));
		}
		
		
		req.setAttribute("fix", fix);
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pagebar", builder.toString());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/notice/notice_list.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}
