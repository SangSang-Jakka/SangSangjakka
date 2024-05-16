package com.jakka.controller.board.suggestion;

import java.io.IOException;
import java.io.PrintWriter;

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
* SuggestionAdd 서블릿은 건의사항 게시글 작성 기능을 제공합니다.
*/
@WebServlet("/board/suggestion/add.do")
public class SuggestionAdd extends HttpServlet {

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
		resp.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		// 로그인 여부 확인
		String userSeq = (String)session.getAttribute("userSeq");
		System.out.println("list.jsp에서 name=seq value=$userSeq쓴곳 값" + userSeq);
		req.setAttribute("seq", userSeq);
		System.out.println(userSeq);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/suggestion/suggestion_add.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	/**
     * POST 요청을 처리합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 결과

		HttpSession session = req.getSession();
	    String userSeq = (String) session.getAttribute("userSeq");		
		
		// 제목 받아오기
		String subject = req.getParameter("subject");
		String seq = req.getParameter("seq");
		System.out.println(seq);
				
		System.out.println("1" + subject);
		// 내용 받아오기
		String content = req.getParameter("editordata");
		System.out.println("2" + content);
		// 비밀글 받아오기
		String secret = req.getParameter("secret");
		
		if(secret == null) {
			secret = "n";
		} else {
			secret = "y";
		}
		
		// DB 작업하기 > INSERT
		SuggestionDAO dao = DAOManager.getSuggestionDAO();
		SuggestionDTO dto = new SuggestionDTO();
		
		dto.setSgstTitle(subject);
		dto.setSgstContents(content);
		dto.setSgstSecretYN(secret);
		dto.setUserSeq(userSeq);
		
		// dto를 들고 DB 작업
		// 생성 int
		// SuggestionDTO(sgstSeq=79, sgstTitle=접근성 개선 방안, sgstContents=게시판통합되었으면asdfasdf, sgstRegdate=2024-05-04 06:38:59, sgstSecretYN=y, userSeq=92, sgstCnt=21, userNick=null, attach=null)
		int result = dao.add(dto);
		
		PrintWriter writer = resp.getWriter();
		if(result > 0) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('작성이 완료되었습니다.');");
			writer.println("location.href='/sangsangjakka/board/suggestion/list.do'");
			writer.println("</script>");
			writer.close();
		} else {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('제목 혹은 내용이 입력되지 않았습니다.');");
			writer.println("location.href='/sangsangjakka/board/suggestion/list.do';");
			writer.println("</script>");
		}
		writer.close();
	}
}//End of class
