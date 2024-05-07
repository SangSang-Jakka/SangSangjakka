<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="header">
<%@page session="true"%>
        <div class="headerWrap">
            <div class="empty"></div>
            <div class="headerLogo">
                <a href="/sangsangjakka/index.do"><img src="https://ld-wp73.template-help.com/wordpress/prod_26992/v5/wp-content/uploads/2019/12/Group-178.svg" class="jet-logo__img" alt="Funcare" width="205" height="78"></a>
            </div>
            
            <c:if test="${empty userId}">
	            <div class="headerLogin">
	                <div class="loginWrap">
	                    <a href="/sangsangjakka/user/login.do" class="loginBtn">로그인</a>
	                    <a href="/sangsangjakka/user/signup.do" class="singUpBtn">회원가입</a>
	                </div>
	            </div>
            </c:if>
            
            <c:if test="${not empty userId}">
          		<div class="headerLogin">
          			<div class="loginWrap">
          				<p>${userNick}님 안녕하세요<p>
          				<a href="/sangsangjakka/user/logout.do" class="logoutBtn">로그아웃</a>
          				<a href="/sangsangjakka/user/mypage.do" class="mypageBtn">마이페이지</a>
          			</div>
          		</div>
            </c:if>
        </div>
    </header>
    
        <nav id="nav">
            <ul>
                <a href="/sangsangjakka/index.do"><li>메인화면</li></a>
                <a href="/sangsangjakka/board/book/add.do"><li>동화제작</li></a>
                <a href="/sangsangjakka/board/book/list.do"><li>동화나라</li></a>
                <a href="/sangsangjakka/board/award/list.do"><li>명예의 전당</li></a>
                <a href="/sangsangjakka/board/notice/list.do"><li>공지사항</li></a>
                <a href="/sangsangjakka/board/freeboard/list.do"><li>자유게시판</li></a>
                <a href="/sangsangjakka/board/suggestion/list.do"><li>건의사항</li></a>
            </ul>
        </nav>