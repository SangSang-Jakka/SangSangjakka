package com.jakka.controller.board.bookmaking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dto.book.PageDTO;

@WebServlet("/board/bookmaking/editpage.do")
public class EditPage extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 가져오기(bookSeq, pageSeq, cmntYN, imgYN, pageUrl, pageContents)
		//2. DB 작업 > update
		//3. 결과
		
		HttpSession session = req.getSession();
		
		String bookSeq = req.getParameter("bookSeq");
		String pageSeq = req.getParameter("pageSeq");
		String cmntYN = req.getParameter("cmntYN");
		String imgYN = req.getParameter("imgYN");
		String pageUrl = req.getParameter("pageUrl");
		String pageContents = req.getParameter("pageContents");
		String userId = (String) req.getSession().getAttribute("userId");
		
		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/user/login.do");
			return;
		}
		
		PageDAO dao = new DAOManager().getPageDAO();
		PageDTO dto = new PageDTO();
		
		dto.setBookSeq(bookSeq);
		dto.setPageSeq(pageSeq);
		dto.setCmntYN(cmntYN);
		dto.setImgYN(imgYN);
		//pageUrl = pageUrl.substring(pageUrl.indexOf("sangsangjakka")-1);
		dto.setPageUrl(pageUrl);
		dto.setPageContents(pageContents);
		
		int result = dao.save(dto);
		
		//방금 만들어진 페이지 가져오기
		HashMap<Integer, PageDTO> pages = new HashMap<>();
		pages = dao.findPages(bookSeq);
		PageDTO dto2 = pages.get(Integer.parseInt(pageSeq));
		resp.setContentType("application/json");
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		JSONObject subObj = new JSONObject();
		subObj.put("bookSeq", dto2.getBookSeq());
		subObj.put("pageSeq", dto2.getPageSeq());
		subObj.put("cmntYN", dto2.getCmntYN());
		subObj.put("imgYN", dto2.getImgYN());
		subObj.put("pageUrl", dto2.getPageUrl());
		subObj.put("pageContents", dto2.getPageContents());
		
		obj.put("dto", subObj);
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();
		
	}

}