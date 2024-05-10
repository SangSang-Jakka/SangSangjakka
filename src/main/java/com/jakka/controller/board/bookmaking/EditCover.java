package com.jakka.controller.board.bookmaking;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;

import com.jakka.model.DAOManager;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.book.PageDTO;

@WebServlet("/board/bookmaking/editcover.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 1 MB
		maxFileSize = 1024 * 1024 * 50, // 50 MB
		maxRequestSize = 1024 * 1024 * 50 * 5 // 250 MB
)
public class EditCover extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 데이터 가져오기(bookSeq, pageSeq, basePath)
		// 2. 파일생성
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();

		HttpSession session = req.getSession();
		String userId = (String) req.getSession().getAttribute("userId");

		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/user/login.do");
			return;
		}
		// FormData 파트 처리
		String basePath = getValue(req.getPart("basePath"));
		String bookSeq = getValue(req.getPart("bookSeq"));
		String pageSeq = getValue(req.getPart("pageSeq"));
		String pageUrl = getValue(req.getPart("pageUrl"));
		String imageData = getValue(req.getPart("imageData"));

		String uploadPath = basePath + userId + "/" + bookSeq + "/";

		// 파일 저장 디렉토리 생성
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		// 개발 경로 (절대 경로)
		String devUploadPath = new File(
				"C:\\class\\SangSangjakka\\src\\main\\webapp\\generated\\" + userId + "\\" + bookSeq + "\\")
				.getAbsolutePath() + "\\";
		File devUploadDir = new File(devUploadPath);
		if (!devUploadDir.exists()) {
			devUploadDir.mkdirs();
		}

		try {
			// 파일 정보 가져오기
			if (imageData != null) {
				String newFileName = pageSeq + ".jpg"; //파일 이름 설정
	            Path filePath = Paths.get(uploadPath + newFileName);
	            Path devPath = Paths.get(devUploadPath + newFileName);
				if (imageData.startsWith("C:")) {
                	File file = new File(imageData);
                	Files.copy(file.toPath(), filePath, StandardCopyOption.REPLACE_EXISTING);
                	Files.copy(file.toPath(), devPath, StandardCopyOption.REPLACE_EXISTING);
                } else { // Handling file part directly
                	Part filePart = req.getPart("imageData");
                    if (filePart != null) {
                    	InputStream input = filePart.getInputStream();
                    	String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    	File file = new File(uploadPath + newFileName);
                    	Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    					// 같은 파일을 로컬 개발 경로에 복사
    					File localFile = new File(devUploadPath + newFileName);
    					Files.copy(file.toPath(), localFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
				
				BookDAO dao = DAOManager.getBookDAO();
				BookDTO dto = dao.findById(bookSeq);
				dto.setBookCover("/sangsangjakka/generated/"+userId+"/"+bookSeq+"/cover.jpg");
				dao.save(dto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("File upload failed", e);
		}

		writer.print("File uploaded to: " + uploadPath);
		writer.close();
	}



	private String getValue(Part part) throws IOException {
		if (part == null) {
			return null;
		}
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"))) {
			return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		}
	}
	
}