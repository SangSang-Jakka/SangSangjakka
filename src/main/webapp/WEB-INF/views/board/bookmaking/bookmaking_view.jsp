<!-- bookmaking_view.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookmaking/default.css" />
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookmaking/bookblock.css" />
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookmaking/bookshare_view.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="/sangsangjakka/resources/js/modernizr.custom.js"></script>
	<script src="https://kit.fontawesome.com/e075b9b5dc.js"	crossorigin="anonymous"></script>
    <script src="/sangsangjakka/resources/js/jquerypp.custom.js"></script>
    <script src="/sangsangjakka/resources/js/jquery.bookblock.js"></script>
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
				        <form method="get" action="<c:url value='/board/bookmaking/add.do'/>">
				            <input type="hidden" name="type" value="y" id="recommendedBook"> <!-- Ensure value attribute is set -->
				            <button type="submit" class="bookmakingOptionItem pointer">
				                <h3 class="bookmakingOptionElement">취향 저격 책 만들기</h3>
				                <p class="bookmakingOptionElement">취향을 저격하는 맞춤형 동화책 만들기</p>
				            </button>
				        </form>
				    </div>
				</div>
				<div class="bookmakingOptionContainer">
				    <div class="bookmakingOptionBox">
				        <form method="get" action="<c:url value='/board/bookmaking/add.do'/>">
				            <input type="hidden" name="type" value="n" id="customBook"> <!-- Ensure value attribute is set -->
				            <button type="submit" class="bookmakingOptionItem pointer">
				                <h3 class="bookmakingOptionElement">새로운 형식 책 만들기</h3>
				                <p class="bookmakingOptionElement">혁신적인 형식과 스타일의 동화책 만들기</p>
				            </button>
				        </form>
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
							<c:forEach items="${dto}" var="dto" varStatus="status">
							    <div class="bb-item" id="${dto.pageSeq}">
							        <div class="pageImage" style="background-image: url('${dto.pageUrl}');">
							        </div>
							        <p>${dto.pageContents}</p>
							    </div>
							</c:forEach>
						</div>

						<div class="pageOptionBox flex btnBox">
							<div class="pageOptionItem btnItem pointer" id="pageEdit">수정</div>
							<div class="pageOptionItem btnItem pointer" id="pageAdd">페이지 추가</div>
							<div class="pageOptionItem btnItem pointer" id="pageNext">표지</div>
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
				<div class="coverMaker">
					<div class="coverImageBox">
						<div class="coverImageItem">
							<div class="coverImage" style="background-image: url('${cover}');">
								
							</div>
						</div>
						<div class="coverImageMaker columnFlex">
							<h3> 책의 표지를 만들어요!
								<input type="checkbox" name="coverAiSupport" id="coverAiSupport" checked>
								<label><small>ai의 도움을 받아요</small></label>
							</h3>
							<div class="full flex coverImageDesBox">
								<input type="text"><input type="submit" value="만들기"
									class="btnItem orange middleBtn pointer">
							</div>
							<div class="coverImageMakerBox">
								<div class="coverImageMakerItem">
									<img src="/sangsangjakka/resources/img/book1.jpg"
										class="coverImageMakerItemImg">
								</div>
								<div class="coverImageMakerItem">
									<img src="/sangsangjakka/resources/img/book1.jpg"
										class="coverImageMakerItemImg">
								</div>
								<div class="coverImageMakerItem">
									<img src="/sangsangjakka/resources/img/book1.jpg"
										class="coverImageMakerItemImg">
								</div>
								<div class="coverImageMakerItem">
									<img src="/sangsangjakka/resources/img/book1.jpg"
										class="coverImageMakerItemImg">
								</div>
							</div>
							<div class="coverImageDesBox">
								<div class="whitespace">&nbsp;</div>
								<h4>그림을 선택해주세요!</h4>
							</div>
							<div class="coverImageUploadBox">
								<div class="coverImageUploadItem">
									<input type="file" name="coverImageUpload" id="coverImageUpload">
									<input type="button" value="표지로 사용하기">
								</div>
							</div>
						</div>
					</div>
					<div class="coverOptionBox full flex">
						<div class="coverOptionItem btnItem pointer" id="coverPrev">이전으로</div>
						<div class="coverOptionItem btnItem pointer" id="coverNext">제목</div>
					</div>
				</div>
				<div class="titleMaker">
					<div class="titleItem">
						<input type="text"/>
					</div>
					<div>
						<h3>
							책의 제목을 만들어요!
							<input type="checkbox" name="titleAiSupport" id="titleAiSupport" checked>
							<label><small>ai의 도움을 받아요</small></label>
						</h3>
					</div>
					<ul class="titleMakerBox">
							<li class="titleMakerItem"><span>1. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem btnItem middleBtn tomato pointer">
								</div>
								<div class="whitespace">&nbsp;</div></li>
							<li class="titleMakerItem"><span>2. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem btnItem middleBtn tomato pointer">
								</div>
								<div class="whitespace">&nbsp;</div></li>
							<li class="titleMakerItem"><span>3. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem btnItem middleBtn tomato pointer">
								</div>
								<div class="whitespace">&nbsp;</div></li>
							<li class="titleMakerItem"><span>4. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem btnItem middleBtn tomato pointer">
								</div>
								<div class="whitespace">&nbsp;</div></li>
						</ul>
						<div class="titleDescriptionBox">
							<div class="titleDescriptionItem">
								<input type="text" placeholder="내용을 입력해주세요.">
							</div>
							<div class="titleDescriptionItem">
								<input type="button" value="전송" />
							</div>
						</div>
					<div class="titleOptionBox full flex">
						<div class="titleOptionItem btnItem pointer" id="titlePrev">이전으로</div>
						<a href="/sangsangjakka/board/bookmaking/fin.do">
							<div class="titleOptionItem btnItem pointer" id="titleNext">완성</div>
						</a>
					</div>
				</div>
			</div>
		</section>

		<div class="bookmakingGridRight"></div>
	</div>

	<!-- footer -->
	<%@include file="/WEB-INF/views/template/footer.jsp"%>

	<script>
			console.log(${bookSeq});
			var cmntYN = '${firstpage.cmntYN}';
			var imgYN = '${firstpage.imgYN}';
			var textYN = '';
			var imageYN = '';
			var lastpage = ${lastpage};
			var $currentVisible;
			var imgChangeCheck = 'n';
			var coverImageYN = 'y';
			var titleImageYN = 'y';
			var Page = (function() {

				var config = {
					$bookBlock: $('#bb-bookblock'),
					$navNext: $('#bb-nav-next'),
					$navPrev: $('#bb-nav-prev'),
					$navFirst: $('#bb-nav-first'),
					$navLast: $('#bb-nav-last')
				},
					init = function() {
						config.$bookBlock.bookblock({
							speed: 800,
							shadowSides: 0.8,
							shadowFlip: 0.7
						});
						initEvents();
					},
					initEvents = function() {

						var $slides = config.$bookBlock.children();

						// add navigation events
						config.$navNext.on('click touchstart', function() {
							config.$bookBlock.bookblock('next');
							$('.pageMaker').hide();
							return false;
						});

						config.$navPrev.on('click touchstart', function() {
							config.$bookBlock.bookblock('prev');
							$('.pageMaker').hide();
							return false;
						});

						config.$navFirst.on('click touchstart', function() {
							config.$bookBlock.bookblock('first');
							$('.pageMaker').hide();
							return false;
						});

						config.$navLast.on('click touchstart', function() {
							config.$bookBlock.bookblock('last');
							$('.pageMaker').hide();
							return false;
						});

						// add swipe events
						$slides.on({
							'swipeleft': function(event) {
								config.$bookBlock.bookblock('next');
								return false;
							},
							'swiperight': function(event) {
								config.$bookBlock.bookblock('prev');
								return false;
							}
						});

						// add keyboard events
						$(document).keydown(function(e) {
							var keyCode = e.keyCode || e.which,
								arrow = {
									left: 37,
									up: 38,
									right: 39,
									down: 40
								};

							switch (keyCode) {
								case arrow.left:
									config.$bookBlock.bookblock('prev');
									break;
								case arrow.right:
									config.$bookBlock.bookblock('next');
									break;
							}
						});
					};

				return { init: init };

			})();
			
			$(document).ready(function() {
				$('.bookmakingWrap').hide();
				$('.coverMaker').hide();
				$('.titleMaker').hide();
				
			    setupCheckboxHandlers();
			    check();
			    
				function setupCheckboxHandlers() {
			        $('#textAiSupport').change(function() {
			            if ($(this).is(':checked')) {
			                $('.pageTextMakerBox').show();
			                $('.pageDescriptionBox').hide();
			                textYN = 'y';
			            } else {
			                $('.pageTextMakerBox').hide();
			                $('.pageDescriptionBox').show();
			                textYN = 'n';
			            }
			        });

			        $('#imageAiSupport').change(function() {
			            if ($(this).is(':checked')) {
			                $('.pageImageMakerBox').show();
			                $('.pageImageDesBox').show();
			                $('.pageImageUploadBox').hide();
			                imageYN = 'y';
			            } else {
			                $('.pageImageMakerBox').hide();
			                $('.pageImageDesBox').hide();
			                $('.pageImageUploadBox').show();
			                imageYN = 'n';
			            }
			        });

			        $('#coverAiSupport').change(function() {
			            if ($(this).is(':checked')) {
			                $('.coverImageMakerBox').show();
			                $('.coverImageDesBox').show();
			                $('.coverImageUploadBox').hide();
			                coverImageYN = 'y';
			            } else {
			                $('.coverImageMakerBox').hide();
			                $('.coverImageDesBox').hide();
			                $('.coverImageUploadBox').show();
			                coverImageYN = 'n';
			            }
			        });
			        
			        $('#titleAiSupport').change(function() {
			            if ($(this).is(':checked')) {
			                $('.titleMakerBox').show();
			                $('.titleDescriptionBox').hide();
			                titleImageYN = 'y';
			            } else {
			                $('.titleMakerBox').hide();
			                $('.titleDescriptionBox').show();
			                titleImageYN = 'n';
			            }
			        });

			    }

			    function check() {
			    	if ('${firstpage.cmntYN}' != '') {
			        
			        $('.bookmakingOptionContainer').hide();
			        $('.bookmakingWrap').show();
			        Page.init();

			        $('#textAiSupport').prop('checked', cmntYN === 'y');
			        $('#imageAiSupport').prop('checked', imgYN === 'y');

			        $('#textAiSupport').change();
			        $('#imageAiSupport').change();
			    	}
			    }


				function initBookmaking() {
					$('.bookmakingOptionContainer').hide();
					$('.bookmakingWrap').show();
					Page.init();

					var bookType = this.id;
					$('#textAiSupport').prop('checked', bookType === 'recommendedBook');
					$('#imageAiSupport').prop('checked', bookType === 'recommendedBook');

					$('#textAiSupport').trigger('change');
					$('#imageAiSupport').trigger('change');
				}

				$('#recommendedBook, #customBook').click(initBookmaking);
				
				$('#bb-bookblock .bb-item').hide().first().show();

				$('#pageAdd').click(function() {
					++lastpage;
					
					$.ajax({
							type: 'POST',
							url: '/sangsangjakka/board/bookmaking/addpage.do',
							data: {
								bookSeq: ${bookSeq},
								pageSeq: lastpage,
								cmntYN: cmntYN,
								imgYN: imgYN
							},
							dataType: 'json',
							success: function(result) {
								
								if (result.result == '1') {
									var $newPage = $(`<div class="bb-item" id="\${result.dto.pageSeq}"></div>`).html(`
											<div class="pageImage" style="background-image: url('\${result.dto.pageUrl}');"></div>
											<p>\${result.dto.pageContents}</p>
										`);
									$('#bb-bookblock').append($newPage);
									Page.init();
									$('#bb-nav-last').click();
									$('.pageMaker').show();
								} else {
									alert('페이지 추가에 실패했습니다.');
								}
								
							},
							error: function(a,b,c) {
								console.log(a,b,c);
							}
						});
				});
				
				function pageChange() {
					var userId = '${userId}';
					var bookSeq = ${bookSeq};
					$currentVisible = $('#bb-bookblock .bb-item:visible');
					var currentVisibleId = $('#bb-bookblock .bb-item:visible').attr('id');
					var text = $currentVisible.find('p').text();
					var imageUrl = $('#' + currentVisibleId + ' .pageImage').css('background-image');
					var cleanUrl = imageUrl.replace(/^url\(["']?([^"')]+)["']?\)$/, '$1');
					if (cleanUrl.startsWith('http://') || cleanUrl.startsWith('https://')) {
					    var urlParts = new URL(cleanUrl);
					    cleanUrl = urlParts.pathname;
					}
					$.ajax({
						type: 'GET',
						url: '/sangsangjakka/board/bookmaking/editpage.do',
						data: {
							bookSeq: ${bookSeq},
							pageSeq: currentVisibleId,
							cmntYN: textYN,
							imgYN: imageYN,
							pageUrl: imgChangeCheck == 'y' ? '/sangsangjakka/generated/'+userId+'/'+bookSeq+'/'+currentVisibleId+'.jpg' : cleanUrl,
							pageContents: text
						},
						dataType: 'json',
						success: function(result) {
							
							if (result.result == '1') {
							} else {
								alert('내용을 채워주세요.');
							}
							
						},
						error: function(a,b,c) {
							console.log(a,b,c);
						}
					});
				}

				$('#bb-nav-next').click(function() {
					var $visiblePage = $('#bb-bookblock .bb-item:visible');
					var $nextPage = $visiblePage.next('.bb-item');
					if ($nextPage.length > 0) {
						$visiblePage.hide();
						$nextPage.show();
					}
				});

				$('#bb-nav-prev').click(function() {
					var $visiblePage = $('#bb-bookblock .bb-item:visible');
					var $prevPage = $visiblePage.prev('.bb-item');
					if ($prevPage.length > 0) {
						$visiblePage.hide();
						$prevPage.show();
					}
				});

				$('#pageDelete').click(function() {
					$currentVisible = $('#bb-bookblock .bb-item:visible');
					if ($currentVisible.prev('.bb-item').length) {
						$currentVisible.remove();
						Page.init();  // Reinitialize after deleting a page
						$('#bb-nav-last').click();	// Navigate to the last page
					} else {
						alert("Cannot delete the first page.");  // Added alert to prevent deletion of the first page
					}
				});
				
				// Click handler for the "전송" button
				$('.pageDescriptionBox input[type="button"]').click(function() {
					// Fetch the text from the input field
					var newText = $(this).closest('.pageDescriptionBox').find('input[type="text"]').val();

					// Find the visible bb-item and update its <p> tag with the new text
					var $visibleBbItem = $('#bb-bookblock .bb-item:visible');
					$visibleBbItem.find('p').text(newText);
					pageChange();
				});
				
				$('.pageImageUploadBox input[type="button"]').click(function() {
				    var fileInput = $('#pageImageUpload')[0];
				    var file = fileInput.files[0];

				    if (file) {
				        var pageSeq = $('#bb-bookblock .bb-item:visible').attr('id');
				        var userId = '${userId}';
				        var bookSeq = ${bookSeq};
				        var basePath = '${basePath}';
				        // FileReader를 사용하여 이미지 파일을 Base64 형태로 읽습니다.
				        var reader = new FileReader();
				        reader.onload = function(e) {
				        	// currentVisibleId에 해당하는 요소의 배경 이미지로 설정
				            $('#' + pageSeq + ' .pageImage').css('background-image', 'url(' + e.target.result + ')');
				            imgChangeCheck = 'y';
				        	
				            // FormData 객체를 생성하고 파일을 추가합니다.
				            var formData = new FormData();
				            formData.append('image', file);
				            formData.append('userId', userId);
				            formData.append('bookSeq', bookSeq);
				            formData.append('pageSeq', pageSeq);
				            formData.append('basePath', basePath);

				            // Ajax 요청을 통해 서버에 파일을 업로드합니다.
				            $.ajax({
				                type: 'POST',
				                url: '/sangsangjakka/board/bookmaking/editpage.do',
				                data: formData,
				                processData: false,  // FormData를 사용할 때는 processData와 contentType을 false로 설정
				                contentType: false,
				                success: function(data) {
				        			pageChange();
				        			imgChangeCheck = 'n';
				                },
				                error: function(xhr, status, error) {
				                    alert('업로드 실패: ' + error);
				                }
				            });
				        };
				        reader.readAsDataURL(file);
				    } else {
				        alert('파일이 선택되지 않았습니다.');
				    }
				});
				
				$('.coverImageUploadBox input[type="button"]').click(function() {
				    var fileInput = $('#coverImageUpload')[0];
				    var file = fileInput.files[0];

				    if (file) {
				        var pageSeq = 'cover';
				        var userId = '${userId}';
				        var bookSeq = ${bookSeq};
				        var basePath = '${basePath}';
				        // FileReader를 사용하여 이미지 파일을 Base64 형태로 읽습니다.
				        var reader = new FileReader();
				        reader.onload = function(e) {
				        	// .coverImage의 배경 이미지로 설정
				            $('.coverImageItem .coverImage').css('background-image', 'url(' + e.target.result + ')');
				            imgChangeCheck = 'y';
				        	
				            // FormData 객체를 생성하고 파일을 추가합니다.
				            var formData = new FormData();
				            formData.append('image', file);
				            formData.append('userId', userId);
				            formData.append('bookSeq', bookSeq);
				            formData.append('pageSeq', pageSeq);
				            formData.append('basePath', basePath);

				            // Ajax 요청을 통해 서버에 파일을 업로드합니다.
				            $.ajax({
				                type: 'POST',
				                url: '/sangsangjakka/board/bookmaking/editcover.do',
				                data: formData,
				                processData: false,  // FormData를 사용할 때는 processData와 contentType을 false로 설정
				                contentType: false,
				                success: function(data) {
				        			imgChangeCheck = 'n';
				                },
				                error: function(xhr, status, error) {
				                    alert('업로드 실패: ' + error);
				                }
				            });
				        };
				        reader.readAsDataURL(file);
				    } else {
				        alert('파일이 선택되지 않았습니다.');
				    }
				});
				
				// Click handler for the "전송" button
				$('.titleDescriptionBox input[type="button"]').click(function() {
					var newText = $(this).closest('.titleDescriptionBox').find('input[type="text"]').val();

					$('.titleItem').find('input[type="text"]').val(newText);
					titleChange();
				});
				
				function titleChange() {
					var bookSeq = ${bookSeq};
					var title = $('.titleItem').find('input[type="text"]').val();
					$.ajax({
						type: 'POST',
						url: '/sangsangjakka/board/bookmaking/edittitle.do',
						data: {
							bookSeq: ${bookSeq},
							title: title
						},
						dataType: 'json',
						success: function(result) {
							
							if (result.result == '1') {
							} else {
								alert('내용을 채워주세요.');
							}
							
						},
						error: function(a,b,c) {
							console.log(a,b,c);
						}
					});
				}
				
				$('#pageNext').click(function() {
					$('.makedPageViewer').hide();
					$('.pageMaker').hide();
					$('.coverMaker').show();
				});

				$('#coverNext').click(function() {
					$('.coverMaker').hide();
					$('.titleMaker').show();
				});
				
				$('#titlePrev').click(function() {
					$('.titleMaker').hide();
					$('.coverMaker').show();
				});
				
				$('#coverPrev').click(function() {
					$('.coverMaker').hide();
					$('.makedPageViewer').show();
				});
				
				$('#pageEdit').click(function() {
					$('.pageMaker').show();
				});

			});
	</script>
</body>
</html>