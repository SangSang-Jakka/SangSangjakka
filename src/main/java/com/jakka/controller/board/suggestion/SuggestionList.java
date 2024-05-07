package com.jakka.controller.board.suggestion;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.SuggestionDTO;

@WebServlet("/board/suggestion/list.do")
public class SuggestionList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 페이징
		String page = req.getParameter("page");
		
		int nowPage = 0;	// 현재 페이지 번호
		int totalCount = 0;	// 총 게시물 수
		int n = 0;			// 조회수
		int begin = 0;		// 페이징 시작 위치
		int end = 0;		// 페이지 끝 위치
		int loop = 0;
		int blockSize = 10;	// 페이지 개수
		int totalPage = 0;	// 총 페이지 수
		int pageSize = 10;	// 한 페이지에서 출력할 게시물 수
		
		if(page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize + 1;
		
		// 검색하기
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String search =	"n";
		
		// 검색 활성화
		if((column != null && word != null)) {
			search = "y";
		} else {
			search = "n";
			column = "";
			word =	"";
		}
		HashMap<String, String> map = new HashMap<>();
		
		map.put("search", search);
		map.put("column", column);
		map.put("word", word);
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		HttpSession session = req.getSession();
		session.setAttribute("read", "n");
		
		SuggestionDAO dao = DAOManager.getSuggestionDAO();
		ArrayList<SuggestionDTO> list = dao.findAllWhite(map);
		// 
		if(list != null) {
			for(SuggestionDTO dto : list) {
				dto.setSgstRegdate(dto.getSgstRegdate().substring(0, 10));
				// 제목 넣을 변수
				String title = dto.getSgstTitle();
				
				if(title.length() > 20) {
					title = title.substring(0 ,20) + "..";
				}
				title = title.replace(">", "&gt").replace("<", "&lt");
				
				dto.setSgstTitle(title);
			}
			// 총 게시물 수 
			totalCount = dao.whiteTotalCnt(map);
		}
		// 총 게시물? 총 페이지 수?
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		// 페이지바 작업
		StringBuilder builder = new StringBuilder();
		
		// 페이지 번호를 매기기 위한 루프 변수 생성
		loop = 1;
		n = ((nowPage - 1) / blockSize) * blockSize + 1; // 페이지 번호 역할
		
		// 1 페이지 일 때
		if(n == 1) {
			builder.append(String.format("<a href='#!'>[이전 %d페이지]</a>", blockSize));
		} else {
			builder.append(String.format("<a href='/sangsangjakka/board/suggestion/list.do?page=%d&column=%s&word=%s'>[이전 %d페이지]</a>", n-1, column, word, blockSize));
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/suggestion/suggestion_list.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter writer = resp.getWriter();
		writer.println("<script type='text/javascript'");
		writer.println("location.href='/WEB-INF/views/board/suggestion/suggestion_list.jsp");
		writer.println("</script>");
	}

}
 