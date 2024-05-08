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
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.board.SuggestionDTO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/board/freeboard/add.do")
public class FreeboardAdd extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/freeboard/freeboard_add.jsp");
		dispatcher.forward(req, resp);
		System.out.println("get");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post");
		
		// 제목 받아오기
		String subject = req.getParameter("subject");
		// 내용 받아오기
		String content = req.getParameter("editordata");
		
		// seq 받아오기
		HttpSession session = req.getSession();
		String userSeq = (String) session.getAttribute("userSeq");	
		
		System.out.println(userSeq);
		
		// DB 작업하기 > INSERT
		BoardDAO dao = DAOManager.getBoardDAO();
		BoardDTO dto = new BoardDTO();
		
				
		// dto를 들고 가기 전 dto에 값 쓰기
		// 제목
		dto.setBoardTitle(subject);
		// 내용
		dto.setBoardContents(content);
		// Seq
		dto.setUserSeq(userSeq);
		
		
		// dto를 들고 DB 작업
		// 생성 int
		int result = dao.add(dto);
		
//		String boardSeq = dao.findSeq();

		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();

		if(result > 0) {
		    writer.println("<script>");
		    writer.println("alert('작성이 완료되었습니다.');");
		    writer.println("location.href='/sangsangjakka/board/freeboard/view.do?no=';");
		    writer.println("</script>");
		} else {
		    writer.println("<script>");
		    writer.println("alert('제목 혹은 내용이 입력되지 않았습니다.');");
		    writer.println("location.href='/sangsangjakka/board/freeboard/list.do';");
		    writer.println("</script>");
		}

		writer.close();
		writer.close();
				
	}
		
		
		
		
	

}
