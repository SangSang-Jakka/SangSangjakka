package com.jakka.controller.member.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakka.model.DAOManager;
import com.jakka.model.DBUtil;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.user.UserDTO;
import com.jakka.model.enums.Inflow;

@WebServlet("/user/signok.do")
public class SignUpOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 	
    	
    	
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("post");
        
    	String newUserId = req.getParameter("userId");
    	String userSeq = req.getParameter("userSeq");
        String childSsn = req.getParameter("childSsn");
        String[] selectedInflowValues = req.getParameterValues("selectedValues");
        
        System.out.println("aaaaaaa");
        System.out.println("New User ID: " + newUserId);
        System.out.println("Child SSN: " + childSsn);
        System.out.println("Selected Inflow Values: " + Arrays.toString(selectedInflowValues));
        
        req.setAttribute("newUserId", newUserId);

        System.out.println("newUserId" + newUserId);
        System.out.println("UserPk: " + userSeq);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/user/signup_ok.jsp");
        dispatcher.forward(req, resp);
        
        
        saveChildAgeData(childSsn, userSeq);
        saveInflowData(userSeq, selectedInflowValues);
    	
    	
    }

    private int getUserPK(String userId) {
        UserDTO dto = new UserDTO();
        
        dto.setUserId(userId);
        
        UserDAO dao = DAOManager.getUserDAO();
        
        return dao.findPK(dto);
    }

    private void saveChildAgeData(String childSsn, String userSeq) {
        String SQL = "INSERT INTO tblChildAge (childBirth, userSeq) VALUES (?, ?)";

        try (Connection conn = DBUtil.open();
             PreparedStatement pstat = conn.prepareStatement(SQL);) {

            pstat.setString(1, childSsn);
            pstat.setString(2, userSeq);
            pstat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveInflowData(String userSeq, String[] selectedInflowValues) {
        String SQL = "INSERT INTO tblUserInflow (userSeq, inflowCatSeq) VALUES (?, ?)";

        try (Connection conn = DBUtil.open();
             PreparedStatement pstat = conn.prepareStatement(SQL);) {

            for (String inflowValue : selectedInflowValues) {
                int inflowSeq = getInflowSeq(inflowValue);

                pstat.setString(1, userSeq);
                pstat.setInt(2, inflowSeq);
                pstat.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getInflowSeq(String inflowValue) {
        switch (inflowValue) {
            case "인터넷 검색":
                return Integer.parseInt(Inflow.Internet_Search.getValue());
            case "지인 소개":
                return Integer.parseInt(Inflow.ACQUAINTANCE.getValue());
            case "카페":
                return Integer.parseInt(Inflow.CAFFE.getValue());
            case "블로그":
                return Integer.parseInt(Inflow.BLOG.getValue());
            case "소셜 미디어 플랫폼":
                return Integer.parseInt(Inflow.SOCIAL_MEDIA_PLATFORM.getValue());
            case "광고지":
                return Integer.parseInt(Inflow.FLYER.getValue());
            default:
                return Integer.parseInt(Inflow.ECT.getValue());
        }
    }
}