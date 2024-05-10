package com.jakka.controller.board.freeboard;

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
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.board.SuggestionDTO;

@WebServlet("/board/freeboard/edit.do")
public class FreeboardEdit extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 쿼리스트링의 게시글 번호 받아오기
		String freeSeq = req.getParameter("no"); // 번호
		req.setAttribute("freeSeq", freeSeq);
					
		// 게시글의 번호를 통하여 해당 게시글의 정보 조회
		BoardDAO dao = DAOManager.getBoardDAO(); 
		
		System.out.println(freeSeq); //346
		
		BoardDTO dto = dao.findInfo(freeSeq);
		
		req.setAttribute("boardTitle", dto.getBoardTitle());
		req.setAttribute("boardContents", dto.getBoardContents());
		
		System.out.println(dto.getBoardContents());//테스트

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/freeboard/freeboard_edit.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		// 로그인 여부 확인
	    String userSeq = (String) session.getAttribute("userSeq");
	    
	    System.out.println(userSeq);
		
		
		String editTitle = req.getParameter("subject");
		String editContents = req.getParameter("editordata");
		String editSeq = req.getParameter("seq");
		
		System.out.println(editSeq); //346
		
		BoardDAO dao = DAOManager.getBoardDAO();
		BoardDTO dto = new BoardDTO();
		
		dto.setBoardTitle(editContents);
		dto.setBoardContents(editContents);
		dto.setBoardSeq(editSeq);
		dto.setUserSeq(userSeq);

		int result = dao.save(dto);
		
		System.out.println(result); //0

		resp.setContentType("text/html; charset=UTF-8");
		
		
		PrintWriter writer = resp.getWriter();
		
		writer.println("<script type='text/javascript'>");
		if(result > 0) {
			writer.println("alert('수정이 완료되었습니다.')");
			writer.println("location.href='/sangsangjakka/board/freeboard/view.do?no=" + editSeq + "';");
		} else {
			writer.println("alert('제목은 20자 이내, 내용은 1000자 이내로 작성해야 합니다.')");
			writer.println("location.href='/sangsangjakka/board/freeboard/edit.do?no=" + editSeq + "';");
		}
		writer.println("</script>");
	}
	

}
