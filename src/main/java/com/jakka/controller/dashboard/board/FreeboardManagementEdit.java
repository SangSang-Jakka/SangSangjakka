package com.jakka.controller.dashboard.board;

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
import com.jakka.model.dto.board.BoardDTO;

@WebServlet("/admin/dashboard/freeboard/manageedit.do")
public class FreeboardManagementEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		// 인증 받지 못한 사용자 or 권한이 없는 사용자 > 거부

		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId");
		
		if (adId == null) {
			
			resp.sendRedirect("/sangsangjakka/admin/login.do");
			return;
			
		}
	
		
		String seq = req.getParameter("seq");
		BoardDAO boardDAO = DAOManager.getBoardDAO();
		

		// 게시물 가져오기

		BoardDTO dto = boardDAO.findById(seq);

	

		req.setAttribute("seq", seq);
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_board/freeboard_manage_edit.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");

		// 1. 데이터 가져오기
		// 2. DB 작업 > update
		// 3. 결과

		String boardTitle = req.getParameter("boardTitle");
		String boardContents = req.getParameter("boardContents");
		String seq = req.getParameter("seq");

		
		BoardDAO boardDAO = DAOManager.getBoardDAO();
		BoardDTO dto = boardDAO.findById(seq);

			dto.setBoardTitle(boardTitle);
			dto.setBoardContents(boardContents);
			dto.setBoardSeq(seq);
			

			int result = boardDAO.save(dto);
			
			
			if (result == 1) {
				
				resp.sendRedirect("/sangsangjakka/admin/dashboard/freeboard/manageview.do?seq=" + seq);
				
			} else {
				
				resp.setCharacterEncoding("UTF-8");
				PrintWriter writer = resp.getWriter();
				writer.println("<html><head><meta charset=\"UTF-8\"></head><body>");
				writer.println("<script>alert('수정 실패했습니다.');</script>");
				writer.println("<script>location.href='/sangsangjakka/admin/dashboard/freeboard/manage.do';</script>");
				writer.println("</body></html>");
				writer.close();
			}


	}

}// end
