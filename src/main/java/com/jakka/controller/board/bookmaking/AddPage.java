package com.jakka.controller.board.bookmaking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;
import com.jakka.model.dto.user.UserDTO;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 동화책에 페이지를 추가하는 서블릿 클래스입니다.
 * 
 * @author Jakka
 */
@WebServlet("/board/bookmaking/addpage.do")
public class AddPage extends HttpServlet {

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

		//1. 데이터 가져오기(bookSeq, pageSeq)
		//2. DB 작업 > insert
		//3. 결과
		
		HttpSession session = req.getSession();
		
		String bookSeq = req.getParameter("bookSeq");
		String pageSeq = req.getParameter("pageSeq");
		String cmntYN = req.getParameter("cmntYN");
		String imgYN = req.getParameter("imgYN");
		String userId = (String) req.getSession().getAttribute("userId");
		
		PageDAO dao = new DAOManager().getPageDAO();
		PageDTO dto = new PageDTO();
		
		dto.setBookSeq(bookSeq);
		dto.setPageSeq(pageSeq);
		dto.setCmntYN(cmntYN);
		dto.setImgYN(imgYN);
		String basicBackground = "/sangsangjakka/resources/img/empty.jpg";
		dto.setPageUrl(basicBackground);
		dto.setPageContents("내용");
		
		int result = dao.add(dto);
		
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
