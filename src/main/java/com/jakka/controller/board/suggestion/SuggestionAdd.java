package com.jakka.controller.board.suggestion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dto.board.SuggestionDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/board/suggestion/add.do")
public class SuggestionAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/suggestion/suggestion_add.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 결과
		
		// 파일 받아오기
		MultipartRequest multi = new MultipartRequest(
				req,
				req.getRealPath("/generated"),
				1024 * 1024 * 30,
				"UTF-8",
				new DefaultFileRenamePolicy()
		);
				
		
		// 제목 받아오기
		String subject = req.getParameter("subject");
		// 내용 받아오기
		String content = req.getParameter("content");
		// 비밀글 받아오기
		String secret = req.getParameter("secret");
		// seq 받아오기
		String seq = req.getParameter("userSeq");
		
		// DB 작업하기 > INSERT
		SuggestionDAO dao = DAOManager.getSuggestionDAO();
		SuggestionDTO dto = new SuggestionDTO();
		
		// dto를 들고 가기 전 dto에 값 쓰기
		// 제목
		dto.setSgstTitle(subject);
		// 내용
		dto.setSgstContents(content);
		// 파일, 필요한지 체크
		dto.setAttach(multi.getFilesystemName("attach"));
		// 비밀글 체크 여부
		dto.setSgstSecretYN(secret);
		// Seq
		dto.setUserSeq(seq);
		// dto를 들고 DB 작업
		// 생성 int
		int result = dao.add(dto);
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html; charset=UTF-8");
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
