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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * SuggestionEdit 서블릿은 건의사항 게시글 수정 기능을 제공합니다.
 */
@WebServlet("/board/suggestion/edit.do")
public class SuggestionEdit extends HttpServlet {

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
			
			// 쿼리스트링의 게시글 번호 받아오기
			String sgstSeq = req.getParameter("sgstseq"); // 번호
			
			// 게시글의 번호를 통하여 해당 게시글의 정보 조회
			SuggestionDAO dao = DAOManager.getSuggestionDAO(); 
			SuggestionDTO dto = dao.findById(sgstSeq);	// 182
			req.setAttribute("dto", dto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/suggestion/suggestion_edit.jsp");
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

		req.setCharacterEncoding("UTF-8");	
		HttpSession session = req.getSession();
		
		String sgstTitle = req.getParameter("subject");
		String sgstContents = req.getParameter("content");
		String attach = req.getParameter("attach");
		String sgstSecretYN = req.getParameter("secret");
		String sgstSeq = req.getParameter("sgstSeq");
		
		SuggestionDAO dao = DAOManager.getSuggestionDAO();
		SuggestionDTO dto = new SuggestionDTO();
		
		dto.setUserSeq((String) session.getAttribute("userSeq"));
		dto.setSgstTitle(sgstTitle);
		dto.setSgstContents(sgstContents);

		dto.setSgstSecretYN(sgstSecretYN);
		dto.setSgstSeq(sgstSeq);
		
		int result = dao.save(dto);

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<script type='text/javascript'>");
		if(result > 0) {
			writer.println("alert('수정이 완료되었습니다.')");
			writer.println("location.href='/sangsangjakka/board/suggestion/list.do';");
		} else {
			writer.println("alert('제목은 20자 이내, 내용은 1000자 이내로 작성해야 합니다.')");
			writer.println("location.href='/sangsangjakka/board/suggestion/edit.do';");
		}
		writer.println("</script>");
	}

}
