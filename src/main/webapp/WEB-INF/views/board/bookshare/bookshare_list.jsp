<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookshare/bookshare_view.css">
	<style>
	
	
	</style>
	</head>
	<body>
	
		<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>

			<!-- 동화책 공유 게시판 -->
		    <h2>동화 공유 게시판</h2>
		    <div class="boardContainer">
		    	<a href="/sangsangjakka/board/book/view.do">
			        <div class="boardBox1">
			        <img src="/sangsangjakka/resources/img/book1.jpg" class="book1Items">
			        <p class="userItems"><i class="fas fa-user"></i>예지공주 Dec 13, 2023</p>
			        
			        <p class="titleItems">이상하고 신비한 동물 사전</p>
			        <p class="contentItems">여러 귀여운 동물들의 보물찾기 대탐험!<br>직접 그린 그림입니다 ㅎㅎ 재밌게 봐주세요</p>
			        <div class="iconItems">
			            <div class="subItems">
			            <i class="fas fa-check"></i>
			            <p>20</p>
			            </div>
			            <div class="subItems">
			            <i class="fas fa-heart"></i>
			            <p>22</p>
			            </div>
			            <div class="subItems">
			            <i class="fas fa-comments"></i>
			            <p>23</p>
			            </div>
			        </div>
			        </div>
		        </a>
				
				<a href="/sangsangjakka/board/book/view.do">
		        <div class="boardBox2">
			        <img src="/sangsangjakka/resources/img/book2.jpg" class="book1Items">
			        <p class="userItems"><i class="fas fa-user"></i>예지공주 Dec 13, 2023</p>
			        
			        <p class="titleItems">이상하고 신비한 동물 사전</p>
			        <p class="contentItems">여러 귀여운 동물들의 보물찾기 대탐험!<br>직접 그린 그림입니다 ㅎㅎ 재밌게 봐주세요</p>
			        <div class="iconItems">
			            <div class="subItems">
			            <i class="fas fa-check"></i>
			            <p>20</p>
			            </div>
			            <div class="subItems">
			            <i class="fas fa-heart"></i>
			            <p>22</p>
			            </div>
			            <div class="subItems">
			            <i class="fas fa-comments"></i>
			            <p>23</p>
			            </div> 
			        </div>
			        </div>
				</a>
				
				<a href="/sangsangjakka/board/book/view.do">
			        <div class="boardBox3">
			            <img src="/sangsangjakka/resources/img/book3.jpg" class="book1Items">
			            <p class="userItems"><i class="fas fa-user"></i>예지공주 Dec 13, 2023</p>
			            
			            <p class="titleItems">이상하고 신비한 동물 사전</p>
			            <p class="contentItems">여러 귀여운 동물들의 보물찾기 대탐험!<br>직접 그린 그림입니다 ㅎㅎ 재밌게 봐주세요</p>
			            <div class="iconItems">
			                <div class="subItems">
			                <i class="fas fa-check"></i>
			                <p>20</p>
			                </div>
			                <div class="subItems">
			                <i class="fas fa-heart"></i>
			                <p>22</p>
			                </div>
			                <div class="subItems">
			                <i class="fas fa-comments"></i>
			                <p>23</p>
			                </div>  
			            </div>
			        </div>
			    </a>
				
				
				<a href="/sangsangjakka/board/book/view.do">
			        <div class="boardBox4">
			            <img src="/sangsangjakka/resources/img/book4.jpg" class="book1Items">
			            <p class="userItems"><i class="fas fa-user"></i>예지공주 Dec 13, 2023</p>
			                
			            <p class="titleItems">이상하고 신비한 동물 사전</p>
			            <p class="contentItems">여러 귀여운 동물들의 보물찾기 대탐험!<br>직접 그린 그림입니다 ㅎㅎ 재밌게 봐주세요</p>
			            <div class="iconItems">
			                <div class="subItems">
			                <i class="fas fa-check"></i>
			                <p>20</p>
			                </div>
			                <div class="subItems">
			                <i class="fas fa-heart"></i>
			                <p>22</p>
			                </div>
			                <div class="subItems">
			                <i class="fas fa-comments"></i>
			                <p>23</p>
			                </div>  
			            </div>
			        </div>
		        </a>
		    
		        
		    </div>
		    <div class="pageItems">
		        <i class="fa-solid fa-angles-left"></i><i class="fas fa-angle-left"></i><i class="fas fa-regular fa-1"></i><i class="fas fa-angle-right"></i><i class="fa-solid fa-angles-right"></i>
		    </div>
		
		
		<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	</script>
	</body>
</html>