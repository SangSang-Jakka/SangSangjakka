package com.jakka.controller.dashboard.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.user.UserDTO;

@WebServlet("/admin/dashboard/book/capacityview.do")
public class BookCapacityView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		String seq = req.getParameter("seq");

		UserDAO dao = DAOManager.getUserDAO();

		UserDTO dto = dao.findByBook(seq);

		req.setAttribute("dto", dto);

		BookDAO bookDAO = DAOManager.getBookDAO();

		ArrayList<BookDTO> list = bookDAO.findByUserSeq(seq);

		req.setAttribute("capacityList", list);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard_book/capacity_view.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		HttpSession session = req.getSession();
		String adId = (String) session.getAttribute("adId"); // 관리자

		String newStorage = req.getParameter("newCapacity"); // 모달에서 입력한 새로운 용량 값 가져오기
		String userSeq = req.getParameter("userSeq"); // 사용자

		UserDTO userDTO = new UserDTO();
		userDTO.setUserSeq(userSeq);
		userDTO.setLimitStorage(newStorage);


		UserDAO userDAO = DAOManager.getUserDAO();
		int result = userDAO.saveStorage(userDTO, adId); 

		
		resp.setContentType("application/json; charset=UTF-8");
	
		if (result > 0) {
			resp.getWriter().write("용량 변경이 성공적으로 완료되었습니다.");
		} else {
			resp.getWriter().write("용량 변경에 실패하였습니다.");
		}

	}
		


}// End of class
