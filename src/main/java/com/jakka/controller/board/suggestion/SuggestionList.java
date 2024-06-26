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

/**
* SuggestionList 서블릿은 건의사항 게시판의 목록 조회 기능을 제공합니다.
*/
@WebServlet("/board/suggestion/list.do")
public class SuggestionList extends HttpServlet {

	/**
     * 페이징 및 검색 기능을 포함하여 건의사항 게시글 목록을 조회하고,
     * 조회된 데이터를 JSP 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 페이징
		HttpSession session = req.getSession();
		String userSeq = (String)session.getAttribute("Seq");
		req.setAttribute("userSeq", userSeq);
		
		String userId = (String)session.getAttribute("userId");
		req.setAttribute("userId", userId);
		
		String page = req.getParameter("page");
		System.out.println("page는 : " + page);
		
		int nowPage = 0;	// 현재 페이지 번호
		int totalCount = 0;	// 총 게시물 수, 페이지 수를 계산할 수 있다.
		int n = 0;			// 조회수 설정 변수
		int begin = 0;		// 페이징 시작 위치
		int end = 0;		// 페이지 끝 위치
		int loop = 0;
		int blockSize = 10;	// 페이지 개수, 하단 블럭에 표출할 수 있는 최대 페이지 개수
		int totalPage = 0;	// 총 페이지 수, 총 게시물 수(totalCount)와 한 페이지에서 보여줄 수 있는 게시물 수(pageSize)로 계산
		int pageSize = 10;	// 한 페이지에서 최대 보일 수 있는 글 개수
		
		// 파라미터로 넘겨받은 페이지가 null이므로 현재 페이지를 1로 설정
		if(page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);	// 현재 페이지 설정
		}
		
		begin = ((nowPage - 1) * pageSize) + 1; 
		end = begin + pageSize - 1;
		
		// 검색하기
		String column = req.getParameter("column") != null ? req.getParameter("column") : "";
		String word = req.getParameter("word") != null ? req.getParameter("word") : "";
		String search = "n";	//목록보기(n), 검색하기(y)
		
		// 검색 활성화, search가 n이라면 조회만 하는 기능
		if((column != null && !column.equals("")) || (word != null && !word.equals(""))) {
		    search = "y";
		} else {
		    search = "n";
		    column = "";
		    word = "";
		}
		// hashMap 객체 생성
		HashMap<String, String> map = new HashMap<>();
		
		map.put("search", search);	//n, y
		map.put("column", column);
		map.put("word", word);
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		System.out.println("map" + map);
		// 조회수 관련

		session.setAttribute("read", "n");
		
		// 리스트 뽑아오기
		SuggestionDAO dao = DAOManager.getSuggestionDAO();
		
		String orderBy = req.getParameter("orderBy");
		
		if (orderBy == null) {
	        orderBy = "newest"; // 기본값으로 설정
	    }
		
		ArrayList<SuggestionDTO> list = dao.findAllWhite(map, orderBy);	// 쿼리 실행 결과를 반환한다.
		 
		if(list != null) {
			for(SuggestionDTO dto : list) {						// 쿼리 실행 결과인 arraylist에 담은 list를 dto에 반복해서 담음
				dto.setSgstRegdate(dto.getSgstRegdate().substring(0, 10));
				// 제목 넣을 변수
				String title = dto.getSgstTitle();
				
				if(title.length() > 20) {
					title = title.substring(0 ,20) + "..";
				}
				title = title.replace(">", "&gt").replace("<", "&lt");
				
				dto.setSgstTitle(title);
			}
			// 총 게시물 수 및 검색 결과 게시물 수
			totalCount = dao.whiteTotalCnt(map);
			System.out.println("totalCount: " + totalCount);
		}
		// 총 게시물? 총 페이지 개수
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
			builder.append(String.format("<a href='/sangsangjakka/board/suggestion/list.do?page=%d&column=%s&word=%s'>[이전 %d페이지]</a>", n-1, column != null ? column : "", word != null ? word : "", blockSize));
		}
		
		while(!(loop > blockSize || n > totalPage)) {
			if(n == nowPage) {
				builder.append(String.format("<a href='#!' style='color:tomato';>%d</a>", n));
			} else {
				builder.append(String.format("<a href='/sangsangjakka/board/suggestion/list.do?page=%d&column=%s&word=%s'>%d</a>", n, column != null? column : "", word != null ? word : "", n));
			}
			loop++;
			n++;
		}
		
		// 다음 페이지
		if(n >= totalPage) {
			builder.append(String.format("<a href='#!'>[다음 %d페이지]</a> ", blockSize));
		} else {
			builder.append(String.format("<a href='/sangsangjakka/board/suggestion/list.do?page=%d&column=%s&word=%s'>[다음 %d페이지]</a>", n, column != null ? column : "", word != null ? word : "", blockSize));
			
		}
		
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageBar", builder.toString());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/suggestion/suggestion_list.jsp");
		dispatcher.forward(req, resp);

	}
	
	
	/**
     * POST 요청을 처리합니다.
     * 건의사항 목록 페이지로 리다이렉트합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter writer = resp.getWriter();
		writer.println("<script type='text/javascript'");
		writer.println("location.href='/WEB-INF/views/board/suggestion/suggestion_list.jsp");
		writer.println("</script>");
			
	}

}
 