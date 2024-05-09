package com.jakka.controller.board.bookmaking;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.stream.Collectors;

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
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dto.book.PageDTO;

@WebServlet("/board/bookmaking/editpage.do")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 50, // 50 MB
    maxRequestSize = 1024 * 1024 * 50 * 5 // 250 MB
)
public class EditPage extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 가져오기(bookSeq, pageSeq, cmntYN, imgYN, pageUrl, pageContents)
		//2. DB 작업 > update
		//3. 결과
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

        if (basePath != null) {
        	String uploadPath = basePath + userId + "/" + bookSeq + "/";
        	
        	// 파일 저장 디렉토리 생성
        	File uploadDir = new File(uploadPath);
        	if (!uploadDir.exists()) {
        		uploadDir.mkdirs();
        	}
        	// 개발 경로 (절대 경로)
            String devUploadPath = new File("C:\\class\\SangSangjakka\\src\\main\\webapp\\generated\\" + userId + "\\" + bookSeq + "\\").getAbsolutePath() + "\\";
            File devUploadDir = new File(devUploadPath);
            if (!devUploadDir.exists()) {
                devUploadDir.mkdirs();
            }
        	
        	try {
        		// 파일 정보 가져오기
        		Part filePart = req.getPart("image");
        		if (filePart != null) {
        			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        			String newFileName = pageSeq + ".jpg"; // 원하는 형식으로 파일 이름 설정
        			File file = new File(uploadPath + newFileName);
        			
        			// 파일 저장
        			try (InputStream input = filePart.getInputStream()) {
        				Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        				// 같은 파일을 로컬 개발 경로에 복사
        	            File localFile = new File(devUploadPath + newFileName);
        	            Files.copy(file.toPath(), localFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        				System.out.println("File saved as " + file.getAbsolutePath());
        				System.out.println("localFile saved as " + localFile.getAbsolutePath());
        			}
        		}
        	} catch (Exception e) {
        		e.printStackTrace();
        		throw new ServletException("File upload failed", e);
        	}
        	
        	writer.print("File uploaded to: " + uploadPath);
        	writer.close();
        } else {
        	
        	bookSeq = req.getParameter("bookSeq");
        	pageSeq = req.getParameter("pageSeq");
        	String cmntYN = req.getParameter("cmntYN");
        	String imgYN = req.getParameter("imgYN");
        	String pageContents = req.getParameter("pageContents");
        	
        	
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
        	writer.print(obj);
        	writer.close();
        	
        	
        }
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