package com.jakka.util.story;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jakka.util.translate.Trans;

@WebServlet("/story.do")
public class Story extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String data = req.getParameter("data");
        String[] category = {
            "HeartwarmingTales",
            "HumorousTales",
            "FriendFamilyStories",
            "Adventuretales"
        };
        String[] storyList = new String[category.length];

        try {
            //data = Trans.translate(data);

            for (int i = 0; i < category.length; i++) {
                HttpURLConnection conn = createConnection();

                String prompt = "현재 페이지의 내용을 작성해 주세요: '\" + "+data+" + \"' 카테고리는 '\" + "+category[i]+" + \"'이며, 1세부터 10세 어린이들을 위한 동화입니다.";
                JsonObject jsonBody = new JsonObject();
                jsonBody.addProperty("model", "gpt-3.5-turbo-instruct");
                jsonBody.addProperty("prompt", prompt);
                jsonBody.addProperty("max_tokens", 150);

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonBody.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    InputStream errorStream = conn.getErrorStream();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(errorStream))) {
                        StringBuilder errorResponse = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            errorResponse.append(line);
                        }
                        System.out.println("Error response from server: " + errorResponse.toString());
                    }
                } else {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                        StringBuilder response = new StringBuilder();
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        storyList[i] = new Gson().fromJson(response.toString(), JsonObject.class)
                                                  .getAsJsonArray("choices")
                                                  .get(0).getAsJsonObject()
                                                  .get("text").getAsString();
                    }
                }
            }
            for (String line : storyList) {
            	int index = line.indexOf(":");
            	line = line.substring(index + 1);
            }

            resp.setContentType("application/json");
            System.out.println(new Gson().toJson(storyList));
            resp.getWriter().write(new Gson().toJson(storyList));
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Processing error: " + e.getMessage());
        }
    }

    private HttpURLConnection createConnection() throws IOException {
        URL url = new URL("https://api.openai.com/v1/completions");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");	
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer sk-proj-73IedNajBbdjxVnStKFnT3BlbkFJMcxmfMImYFLiYyl9ycT4");
        conn.setDoOutput(true);
        return conn;
    }
}
