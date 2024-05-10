package com.jakka.controller.board.bookmaking;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.jakka.model.DAOManager;
import com.jakka.model.dao.GenreDAO;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.GenreDTO;
import com.jakka.model.dto.book.PageDTO;
import com.jakka.model.dto.user.UserDTO;
import com.jakka.model.enums.APIKeys;
import com.jakka.util.translate.Trans;

@WebServlet("/board/bookmaking/view.do")
@javax.servlet.annotation.MultipartConfig(maxFileSize = 10 * 1024 * 1024) // 10MB 제한
public class BookmakingView extends HttpServlet {
	
	private static final String API_ENDPOINT = "https://api.stability.ai/v2beta/stable-image/generate/sd3";
    private static final String API_KEY = APIKeys.StabilityAPI.getValue();
	private static final int MAX_REQUEST_SIZE = 10 * 1024 * 1024; // 10MB

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String userId = (String) req.getSession().getAttribute("userId");
		String bookSeq = req.getParameter("no");

		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/user/login.do"); // Redirect to login if not authenticated
			return;
		}

		UserDAO userDao = DAOManager.getUserDAO();
		UserDTO userDto = userDao.findById(userId);

		BookDAO bookDao = DAOManager.getBookDAO();
		PageDAO pageDao = DAOManager.getPageDAO();
		ServletContext context = getServletContext();
		final String BASE_DIRECTORY = "/generated/";
		String basePath = context.getRealPath(BASE_DIRECTORY);
		basePath = basePath.replace("\\", "\\\\");

		if (bookSeq != null) {
			HashMap<Integer, PageDTO> pages = pageDao.findPages(bookSeq);
			PageDTO lastpage = pageDao.lastpage(bookSeq);
			ArrayList<PageDTO> dto = new ArrayList<>();
			for (int i = 0; i <= Integer.parseInt(lastpage.getPageSeq()); i++) {
				if (pages.get(i) != null) {
					dto.add(pages.get(i));
				}
			}
			GenreDAO genreDao = new GenreDAO();
			ArrayList<GenreDTO> genre = genreDao.findAll();
			for (GenreDTO item : genre) {
				item.setGenreName(item.getGenreName().replace(" 동화", ""));
			}
			
			req.setAttribute("genre", genre);
			req.setAttribute("firstpage", dto.get(0));
			req.setAttribute("lastpage", lastpage.getPageSeq());
			req.setAttribute("dto", dto);
			req.setAttribute("bookSeq", bookSeq);
			req.setAttribute("userId", userId);
			req.setAttribute("basePath", basePath);
			req.setAttribute("cover", bookDao.findById(bookSeq).getBookCover());
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_view.jsp");
			dispatcher.forward(req, resp);

		} else {
			HashMap<Integer, PageDTO> pages = pageDao.findPages("0");
			PageDTO lastpage = pageDao.lastpage(bookSeq);
			req.setAttribute("lastpage", lastpage.getPageSeq());
			req.setAttribute("basePath", basePath);
			req.setAttribute("bookSeq", 0);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/bookmaking/bookmaking_view.jsp");
			dispatcher.forward(req, resp);
		}
	}//doGet()
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String userId = (String) session.getAttribute("userId");
		
		
		// 문자 인코딩을 UTF-8로 설정
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            // 사용자가 전송한 프롬프트 데이터 받기
            String prompt = req.getParameter("prompt");
            String enPrompt = Trans.translate(prompt);
            
            //여기서 사용자가 선호하는 
            
            String[] category = {
            		"HeartwarmingTales",
            		"HumorousTales",
            		"FriendFamilyStories",
            		"Adventuretales"
            };
            String[] promptList = new String[2];
            
            for(int i = 0; i < 2; i++) {
            	
            	promptList[i] = enPrompt +  "in a " + category[i] + " style suitable for children aged 1 to 10";
            	System.out.println(promptList[i]);
            }
            
            
            // 요청 크기 제한 검사
            if (req.getContentLength() > MAX_REQUEST_SIZE) {
                req.setAttribute("requestSizeExceeded", true);
                req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
                return;
            }

            //생성된 이미지 저장
            List<String> imageDateBase64List = new ArrayList<>();
            List<Map<String, String>> imageDataList = new ArrayList<>();
            
            for(String text : promptList) {
            	
            	// Stable Diffusion API 호출
            	Map<String, String> params = new HashMap<>();
            	params.put("prompt", text);
            	params.put("aspect_ratio", "1:1");
            	params.put("model", "sd3");
            	
            	System.out.println(text);
            	
            	byte[] responseBytes = sendAPIRequest(params);
            	System.out.println("이미지 생성 완료");
            	
            	// 생성된 이미지 데이터를 Base64로 인코딩하여 request 객체에 저장
            	String imageDataBase64 = java.util.Base64.getEncoder().encodeToString(responseBytes);
            	imageDateBase64List.add(imageDataBase64);
            	
            	// 생성된 이미지를 파일로 저장
                String fileName = text + ".png";
                String filePath = getServletContext().getRealPath("/generated") + "/" + userId + "/" +  fileName;
                java.nio.file.Files.write(java.nio.file.Paths.get(filePath), responseBytes);
                
                Map<String, String> imageData = new HashMap<>();
                imageData.put("fileName", fileName);
                imageData.put("imageBase64", imageDataBase64);
                imageDataList.add(imageData);
            	
                
                
            }
            
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(new Gson().toJson(imageDataList));
            
            
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while generating the image.");
        }

		
	}
	
	private byte[] sendAPIRequest(Map<String, String> params) throws IOException {
		
        URL url = new URL(API_ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        conn.setRequestProperty("accept", "image/*");
        conn.setRequestProperty("authorization", "Bearer " + API_KEY);

        
        String payload = buildFormData(params);
        conn.setDoOutput(true);
        conn.getOutputStream().write(payload.getBytes(StandardCharsets.UTF_8));

        
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return IOUtils.toByteArray(conn.getInputStream());
        } else {
            String errorMessage = IOUtils.toString(conn.getErrorStream(), StandardCharsets.UTF_8);
            throw new IOException("API request failed with response code: " + responseCode + ", error message: " + errorMessage);
        }
    }

    private String buildFormData(Map<String, String> params) {
        String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";
        StringBuilder formData = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            formData.append("--").append(boundary).append("\r\n");
            formData.append("Content-Disposition: form-data; name=\"").append(entry.getKey()).append("\"\r\n\r\n");
            formData.append(entry.getValue()).append("\r\n");
        }

        formData.append("--").append(boundary).append("--\r\n");
        return formData.toString();
    }
	
}//End of class
