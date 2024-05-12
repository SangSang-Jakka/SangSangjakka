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

import com.deepl.api.DeepLException;
import com.google.gson.Gson;
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
            data = Trans.translate(data);

            for (int i = 0; i < category.length; i++) {
                HttpURLConnection conn = createConnection();
                
                String prompt = data + ", category is " + category[i] + ", story for children aged 1 to 10, in Korean";
                System.out.println(prompt);
                String jsonInputString = "{\"input\": \"" + prompt + "\"}";

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    InputStream errorStream = conn.getErrorStream();
                    if (errorStream != null) {
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(errorStream))) {
                            StringBuilder errorResponse = new StringBuilder();
                            String line;
                            while ((line = br.readLine()) != null) {
                                errorResponse.append(line);
                            }
                            System.out.println("Error response from server: " + errorResponse.toString());
                        }
                    }
                } else {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                        StringBuilder response = new StringBuilder();
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        storyList[i] = response.toString();  // Store each response in the array
                    }
                }
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(new Gson().toJson(storyList));
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Processing error: " + e.getMessage());
        }
    }

    private HttpURLConnection createConnection() throws IOException {
        URL url = new URL("https://huggingface.co/spaces/bread0808/Sentence");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "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");
        conn.setDoOutput(true);
        return conn;
    }
}
