package com.jakka.controller.board.suggestion;

import java.io.IOException;

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
 * SuggestionView 서블릿은 건의사항 게시글의 상세 조회 기능을 제공합니다.
 */
@WebServlet("/board/suggestion/View.do")
public class SuggestionView extends HttpServlet{

	/**
     * GET 요청을 처리합니다.
     * 로그인 상태를 확인하고, 선택한 건의사항 게시글의 상세 정보를 조회하여
     * JSP 페이지로 전달합니다. 조회수도 증가시킵니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 로그인 여부 확인
		String userId = (String)session.getAttribute("userId");
		String userSeq = (String)session.getAttribute("seq");
		req.setAttribute("userSeq", userSeq);
		if(userId == null) {
			// 비로그인 상태인 경우 로그인 페이지로 리다이렉션
			resp.sendRedirect("/sangsangjakka/user/login.do");
			return;
		}
		String page = req.getParameter("page");
		System.out.println("페이지" + page);
		SuggestionDAO dao = DAOManager.getSuggestionDAO();

		
		// 조회수 증가
		if(session.getAttribute("read") != null && session.getAttribute("read").toString().equals("n")) {
			dao.addCnt(page);
			session.setAttribute("read", "y");
		}
		
		SuggestionDTO dto = dao.findById(page);
		System.out.println(dto);
		//게시글 조작
		dto.setSgstTitle(dto.getSgstTitle().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
		dto.setSgstContents(dto.getSgstContents().replace(">", "&gt;").replace("<", "&lt;").replace("\r\n", "<br>"));
				
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/suggestion/suggestion_view.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}//End of class


