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


@WebServlet("/board/suggestion/add.do")
public class SuggestionAdd extends HttpServlet {

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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 결과
		
		// 파일 받아오기
//		MultipartRequest multi = new MultipartRequest(
//				req,
//				req.getRealPath("/generated"),
//				1024 * 1024 * 30,
//				"UTF-8",
//				new DefaultFileRenamePolicy()
//		);
				
		
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
		
		System.out.println("3" + secret);
		// seq 받아오기
		
		System.out.println("4" + seq);
		
		// DB 작업하기 > INSERT
		SuggestionDAO dao = DAOManager.getSuggestionDAO();
		SuggestionDTO dto = new SuggestionDTO();
		
		// dto를 들고 가기 전 dto에 값 쓰기
		// 제목
		dto.setSgstTitle(subject);
		// 내용
		dto.setSgstContents(content);
		// 파일, 필요한지 체크
//		dto.setAttach(multi.getFilesystemName("attach"));
		// 비밀글 체크 여부
		dto.setSgstSecretYN(secret);
		// Seq
		dto.setUserSeq(seq);
		// dto를 들고 DB 작업
		// 생성 int
		System.out.println("dto:" + dto);
		// SuggestionDTO(sgstSeq=79, sgstTitle=접근성 개선 방안, sgstContents=게시판통합되었으면asdfasdf, sgstRegdate=2024-05-04 06:38:59, sgstSecretYN=y, userSeq=92, sgstCnt=21, userNick=null, attach=null)
		int result = dao.add(dto);
		System.out.println(result);
		
		
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
