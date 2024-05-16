
<%@page import="com.jakka.model.dao.board.BoardDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.jakka.model.DAOManager"%>
<%@page import="com.jakka.model.dao.user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
    // DAO 객체 생성
    //UserDAO dao = DAOManager.getUserDAO();
	BoardDAO boardDAO = DAOManager.getBoardDAO();
    // DB에서 값 가져오기
   // int newBookCount = dao.getNewBookCount();
   
    
    
    
    
 // 현재 날짜 및 시간 객체 생성
    Date now = new Date();

    // 날짜 형식 지정
    SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");

    // 형식에 맞게 날짜 문자열 생성
    String currentDate = formatter.format(now);
    
    int newPostCount = boardDAO.getNewPostCount(currentDate);
    int newSuggestionCount = boardDAO.getNewSuggestionCount(currentDate);
    
    
    
    int boardReportCount = boardDAO.boardReportCount(currentDate);
    System.out.println("신고된 게시글: " + boardReportCount);
    
    int CommReportCount = boardDAO.CommReportCount(currentDate);
    System.out.println("신고된 댓글: " + CommReportCount);
    

    
%>

<div class="updateContainer">
	<div class="card-box mr">
		<div class="widget-small primary">
			<div class="coloured-icon-a">
				<i class="icon fa fa-book fa-3x" style="color: white"></i>
			</div>
			<div class="info">
				<div class="titleElement">신규 작성글</div>
				<div class="countElement"><%= newPostCount + newSuggestionCount%></div>
			</div>
		</div>
	</div>
	<div class="card-box mr">
		<div class="widget-small danger">
			<div class="coloured-icon-b">
				<i class="icon fa fa-star fa-3x" style="color: white"></i>
			</div>
			<div class="info">
				<div class="titleElement">신고된 게시글</div>
				<div class="countElement"><%=boardReportCount%></div>
			</div>
		</div>
	</div>
	<div class="card-box mr">
		<div class="widget-small primary">
			<div class="coloured-icon-c">
				<i class="icon fa fa-users fa-3x" style="color: white"></i>
			</div>
			<div class="info">
				<div class="titleElement">신고댓글</div>
				<div class="countElement"><%=CommReportCount%></div>
			</div>
		</div>
	</div>
<!-- 	<div class="card-box mr"> -->
<!-- 		<div class="widget-small primary"> -->
<!-- 			<div class="coloured-icon-d"> -->
<!-- 				<i class="icon fa fa-quora fa-3x" style="color: white"></i> -->
<!-- 			</div> -->
<!-- 			<div class="info"> -->
<!-- 				<div class="titleElement">신고리뷰</div> -->
<!-- 				<div class="countElement">해야함</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>


