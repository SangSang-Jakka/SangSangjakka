package com.jakka.controller.board.bookshare;

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
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dto.book.BookDTO;

/**
 * 동화책 목록을 처리하는 서블릿 클래스입니다.
 * 
 * @author Jakka
 */
@WebServlet("/board/book/list.do")
public class BookList extends HttpServlet{
	
	/**
     * GET 요청을 처리합니다.
     * 
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//동화책 페이지 작업
		//페이징
		String page = req.getParameter("page");
		
		int nowPage = 0;	//현재 페이지 번호
		int totalCount = 0;	//총 게시물 수
		int pageSize = 9;	//한 페이지에서 출력할 게시물 수
		int totalPage = 0;	//총 페이지 수
		int begin = 0;		//페이징 시작 위치
		int end = 0;		//페이지 끝 위치
		int n = 0;			
		int loop = 0;
		int blockSize = 9;	//페이지 개수
		
		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		
		String column = req.getParameter("column") != null ? req.getParameter("column") : "";
		String word = req.getParameter("word") != null ? req.getParameter("word") : "";
		String search = "n";	//목록보기(n), 검색하기(y)
		
		if((column != null && !column.equals("")) || (word != null && !word.equals(""))) {
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
		
		//책 가져오기
		BookDAO dao = DAOManager.getBookDAO();
		
		ArrayList<BookDTO> list = dao.findAllWhite(map);
		
		if(list != null) {
			//데이터 조작
			for (BookDTO dto : list) {
				
				
				dto.setBookRegdate((dto.getBookRegdate().substring(0, 10)));
				
				String title = dto.getBookTitle();
				String info = dto.getBookInfo();
				
				//
				if (info.length() > 20) {
					info = info.substring(0, 20) + "..";
				}
				
				//제목 > 태그 > 이스케이프
				info = info.replace(">", "&gt;").replace("<", "&lt;");
				title = title.replace(">", "&gt;").replace("<", "&lt;");
				
				dto.setBookTitle(title);			
			}
			
			totalCount = dao.whiteTotalCnt(map);
			
		}
		
		//총 게시물? 총페이지 수?
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		
		//페이지 바 작업
		StringBuilder builder = new StringBuilder();
	
		
		loop = 1;	//루프 변수(10바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1;		//페이지 번호 역할
		
		
		//이전 10페이지
		if(n == 1) {
			builder.append(String.format(" <a href='#!'>[이전 페이지]</a> ",blockSize));
				
		} else {
			builder.append(String.format(" <a href='/sangsangjakka/board/book/list.do?page=%d&column=%s&word=%s'>[이전 페이지]</a> ", n - 1, column != null ? column : "", word != null ? word : "", blockSize));
		}
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (n == nowPage) {
				builder.append(String.format(" <a href='#!' style='color: tomato;'>%d</a> ", n));
			} else {
				builder.append(String.format(" <a href='/sangsangjakka/board/book/list.do?page=%d&column=%s&word=%s'>%d</a> ", n, column != null ? column : "", word != null ? word : "", n));
			}
			
			loop++;
			n++; 
		}
		
		//다음 10페이지
		if(n >= totalPage) {
			builder.append(String.format(" <a href='#!'>[다음 페이지]</a> ", blockSize));
		}else {
			builder.append(String.format(" <a href='/sangsangjakka/board/book/list.do?page=%d&column=%s&word=%s'>[다음 페이지]</a> ", n, column != null ? column : "", word != null ? word : "", blockSize));
		}
		

		
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pagebar", builder.toString());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookshare/bookshare_list.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}//End of class
