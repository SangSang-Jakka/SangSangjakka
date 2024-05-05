<!-- bookmaking_view.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookmaking/default.css" />
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookmaking/bookblock.css" />
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookmaking/bookmaking_view.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="/sangsangjakka/resources/js/modernizr.custom.js"></script>
	<script src="https://kit.fontawesome.com/e075b9b5dc.js"	crossorigin="anonymous"></script>
    <script src="/sangsangjakka/resources/js/jquerypp.custom.js"></script>
    <script src="/sangsangjakka/resources/js/jquery.bookblock.js"></script>
    <script src="/sangsangjakka/resources/js/bookmaking/bookmaking_view.js"></script>
</head>
<body>

	<!-- header -->
	<%@include file="/WEB-INF/views/template/header.jsp"%>

	<div id="bookmakingGrid">
		<div class="bookmakingGridLeft"></div>

		<section class="bookmakingGridCenter">
			<h2>나만의 동화책 만들기</h2>
			<div class="bookmakingOptionWrap">
				<div class="bookmakingOptionContainer">
					<div class="bookmakingOptionBox">
						<div class="bookmakingOptionItem pointer" id="recommendedBook">
							<h3 class="bookmakingOptionElement">취향 저격 책 만들기</h3>
							<p class="bookmakingOptionElement">취향을 저격하는 맞춤형 동화책 만들기</p>
						</div>
					</div>
				</div>
				<div class="bookmakingOptionContainer">
					<div class="bookmakingOptionBox">
						<div class="bookmakingOptionItem pointer" id="customBook">
							<h3 class="bookmakingOptionElement">새로운 형식 책 만들기</h3>
							<p class="bookmakingOptionElement">혁신적인 형식과 스타일의 동화책 만들기</p>
						</div>
					</div>
				</div>
			</div>
			<div class="bookmakingWrap">
				<div class="makedPageViewer">
					<div class="containerLeft">
						<nav class="flex">
							<a id="bb-nav-first" href="#" class="bb-custom-icon pointer"> <i
								class="fa-solid fa-angles-left"></i>
							</a> <a id="bb-nav-prev" href="#" class="bb-custom-icon pointer"><i
								class="fa-solid fa-angle-left"></i></a>
						</nav>
					</div>
					<div class="bb-custom-wrapper">

						<div id="bb-bookblock" class="bb-bookblock">
							<div class="bb-item">
								<div class="pageImage"
									style="background-image: url('/sangsangjakka/resources/img/book1.jpg');">
								</div>
								<p>Pastry bear claw oat cake danish croissant
									jujubes danish. Wypas soufflé muffin. Liquorice powder pastry
									danish. Candy toffee gummi bears chocolate bar lollipop
									applicake chocolate cake danish brownie.</p>
							</div>
						</div>

						<div class="pageOptionBox flex btnBox">
							<div class="pageOptionItem btnItem pointer" id="pageEdit">수정</div>
							<div class="pageOptionItem btnItem pointer" id="pageAdd">페이지 추가</div>
							<div class="pageOptionItem btnItem pointer" id="bookSave">완성</div>
							<div class="pageOptionItem btnItem pointer" id="pageDelete">삭제</div>
						</div>
					</div>
					<div class="containerRight">
						<nav class="flex">
							<a id="bb-nav-next" href="#" class="bb-custom-icon pointer"><i class="fa-solid fa-angle-right"></i></a>
							<a id="bb-nav-last"	href="#" class="bb-custom-icon pointer"><i class="fa-solid fa-angles-right"></i></a>
						</nav>
					</div>
				</div>
				<div class="pageMaker">
					<div class="pageTextMaker columnFlex">
						<h3>
							책의 내용을 만들어요!
							<input type="checkbox" name="textAiSupport" id="textAiSupport" checked>
							<label><small>ai의 도움을 받아요</small></label>
						</h3>
						<div class="full flex pageTextMakerBox">
							<input type="text"><input type="submit" value="만들기" class="btnItem orange middleBtn pointer">
						</div>
						<ul class="pageTextMakerBox">
							<li class="pageTextMakerItem"><span>1. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem btnItem middleBtn tomato pointer">
								</div>
								<div class="whitespace">&nbsp;</div></li>
							<li class="pageTextMakerItem"><span>2. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem btnItem middleBtn tomato pointer">
								</div>
								<div class="whitespace">&nbsp;</div></li>
							<li class="pageTextMakerItem"><span>3. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem btnItem middleBtn tomato pointer">
								</div>
								<div class="whitespace">&nbsp;</div></li>
							<li class="pageTextMakerItem"><span>4. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem btnItem middleBtn tomato pointer">
								</div>
								<div class="whitespace">&nbsp;</div></li>
						</ul>
						<div class="pageDescriptionBox">
							<div class="pageDescriptionItem">
								<input type="text" placeholder="내용을 입력해주세요.">
							</div>
							<div class="pageDescriptionItem">
								<input type="button" value="전송" />
							</div>
						</div>
					</div>

					<div class="vDashed"></div>
					<div class="vDashedRight"></div>

					<div class="pageImageMaker columnFlex">
						<h3> 책의 그림을 만들어요!
							<input type="checkbox" name="imageAiSupport" id="imageAiSupport" checked>
							<label><small>ai의 도움을 받아요</small></label>
						</h3>
						<div class="full flex pageImageDesBox">
							<input type="text"><input type="submit" value="만들기"
								class="btnItem orange middleBtn pointer">
						</div>
						<div class="pageImageMakerBox">
							<div class="pageImageMakerItem">
								<img src="/sangsangjakka/resources/img/book1.jpg"
									class="pageImageMakerItemImg">
							</div>
							<div class="pageImageMakerItem">
								<img src="/sangsangjakka/resources/img/book1.jpg"
									class="pageImageMakerItemImg">
							</div>
							<div class="pageImageMakerItem">
								<img src="/sangsangjakka/resources/img/book1.jpg"
									class="pageImageMakerItemImg">
							</div>
							<div class="pageImageMakerItem">
								<img src="/sangsangjakka/resources/img/book1.jpg"
									class="pageImageMakerItemImg">
							</div>
						</div>
						<div class="pageImageDesBox">
							<div class="whitespace">&nbsp;</div>
							<h4>그림을 선택해주세요!</h4>
						</div>
						<div class="pageImageUploadBox">
							<div class="pageImageUploadItem">
								<input type="file" name="pageImageUpload" id="pageImageUpload">
								<input type="button" value="페이지에 사용하기">
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<div class="bookmakingGridRight"></div>
	</div>

	<!-- footer -->
	<%@include file="/WEB-INF/views/template/footer.jsp"%>

</body>
</html>