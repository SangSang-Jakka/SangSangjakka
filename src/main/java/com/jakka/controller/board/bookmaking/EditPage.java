package com.jakka.controller.board.bookmaking;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
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

/**
 * 동화책 페이지 편집 서블릿 클래스입니다.
 * 
 * @author Jakka
 */
@WebServlet("/board/bookmaking/editpage.do")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 50, // 10 MB
    maxFileSize = 1024 * 1024 * 50, // 50 MB
    maxRequestSize = 1024 * 1024 * 50 * 5 // 250 MB
)
public class EditPage extends HttpServlet {

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
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login.do");
            return;
        }

        String basePath = getValue(req.getPart("basePath"));
        String bookSeq = getValue(req.getPart("bookSeq"));
        String pageSeq = getValue(req.getPart("pageSeq"));
        String imageData = getValue(req.getPart("image"));

        if (basePath != null && imageData != null) {
        	// 파일 저장 경로
            String uploadPath = basePath + userId + "/" + bookSeq + "/";
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
            

            String fileName = pageSeq + ".jpg";
            Path filePath = Paths.get(uploadPath + fileName);
            Path devPath = Paths.get(devUploadPath + fileName);

            try {
                if (imageData.startsWith("data:image")) { // Handling Base64 encoded image
                    String base64Image = imageData.split(",")[1];
                    byte[] imageBytes = Base64.getDecoder().decode(base64Image);
                    Files.write(filePath, imageBytes);
                    Files.write(devPath, imageBytes);
                } else if (imageData.startsWith("http")) { // Handling URL
                    URL imageUrl = new URL(imageData);
                    try (InputStream in = imageUrl.openStream()) {
                        Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
                        Files.copy(in, devPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } else if (imageData.startsWith("C:")) {
                	File file = new File(imageData);
                	Files.copy(file.toPath(), filePath, StandardCopyOption.REPLACE_EXISTING);
                	Files.copy(file.toPath(), devPath, StandardCopyOption.REPLACE_EXISTING);
                } else { // Handling file part directly
                    Part filePart = req.getPart("image");
                    if (filePart != null) {
                        Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                        Files.copy(filePart.getInputStream(), devPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
                writer.print("File uploaded to: " + uploadPath);
                writer.print("File uploaded to: " + devPath);
            } catch (Exception e) {
                e.printStackTrace();
                writer.print("Failed to upload file: " + e.getMessage());
            }
        } else {
            writer.print("Required data missing.");
        }
        writer.close();
    }
	
	/**
     * Part에서 값을 가져옵니다.
     * 
     * @param part Part 객체
     * @return Part의 값
     * @throws IOException 입출력 예외가 발생한 경우
     */
	private String getValue(Part part) throws IOException {
	    if (part == null) {
	        return null;
	    }
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"))) {
	        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
	    }
	}
	
	/**
     * GET 요청을 처리합니다.
     * 
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외가 발생한 경우
     * @throws IOException      입출력 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String bookSeq = req.getParameter("bookSeq");
		String pageSeq = req.getParameter("pageSeq");
    	String cmntYN = req.getParameter("cmntYN");
    	String imgYN = req.getParameter("imgYN");
    	String pageUrl = req.getParameter("pageUrl");
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
    	
    	PrintWriter writer = resp.getWriter();
    	resp.setCharacterEncoding("UTF-8");
    	writer.print(result);
    	writer.close();
    	
	}

}