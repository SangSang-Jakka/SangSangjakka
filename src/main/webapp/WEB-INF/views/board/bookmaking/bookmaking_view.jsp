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
	<!-- Additional links for jQuery UI for drag and drop -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<style>
		
		
		
		
		.loading {
			display: none;
		}
		 
		.loading > img {
			width : 370px;
			height: 490px;
			margin : 0 auto;
		}
		
		.loadingGuide {
			text-align:center;
			margin-bottom:30px;
			font-size:20px;
		}
		
		#imageContainer > img {
			widht: 180px;
			height: 240px;
			border-radius:10px;
			
		}
		
		#imageContainer {
		  display:flex;
		  justify-content:center;
		}
		
		#coverImageContainer > img {
			widht: 180px;
			height: 240px;
			border-radius:10px;
			
		}
		
		#coverImageContainer {
		  display:flex;
		  justify-content:center;
		}
		
		.pageImageMakerItem img {
		  width: 100%;
		  height: auto;
		  max-width: 400px; /* 최대 너비 400px로 제한 */
		  max-height: 533px; /* 가로세로 비율 3:4로 계산한 높이 */
		  object-fit: contain;
		  border-radius:10px;
		  transition: transform 0.3s ease;
		}
		
		.pageImageMakerItem img:hover {
	    	transform: scale(1.05); 
		}
		
		.coverImageMakerItem img {
			width: 100%;
			height: auto;
			max-width: 400px; /* 최대 너비 400px로 제한 */
			max-height: 533px; /* 가로세로 비율 3:4로 계산한 높이 */
			object-fit: contain;
			border-radius:10px;
			transition: transform 0.3s ease;
		}
		
		.coverImageMakerItem img:hover {
			transform: scale(1.05); 
		}
		
		
		
		.bookmakingOptionBox {
			display : flex;
			justify-content:center;
			
		}
		
		.bookmakingOptionWrap {
			margin-bottom:30px;
		}
		
		.pageImageDesItem {
			font-size:20px;
			margin-bottom:20px;
		}
		
		.coverImageDesItem {
			font-size:20px;
			margin-bottom:20px;
		}
	
		
		
		
	</style>
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
									<div class="makePageContents">
									<p>${dto.pageContents}</p>
									</div>
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
						<div class="pageTextMakerBox">
							<input class="insertContents" type="text" placeholder="어떤 이야기를 만들까요?"><input type="submit" value="만들기" class="insertBtn pointer">
						</div>
						<ul class="pageTextMakerBox contentsList">
							<li class="pageTextMakerItem">
								<span>1. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.
								</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem selectBtn pointer ">
								</div>
								</li>
							<li class="pageTextMakerItem"><span>2. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem selectBtn pointer">
								</div>
								</li>
							<li class="pageTextMakerItem"><span>3. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem selectBtn pointer">
								</div>
								</li>
							<li class="pageTextMakerItem"><span>4. Lorem ipsum
									dolor sit amet, consectetur adipisicing elit. Fugiat, porro.</span>
								<div class="selectTextBox">
									<input type="submit" value="선택"
										class="selectTextItem selectBtn pointer">
								</div>
								</li>
						</ul>
						<div class="pageDescriptionBox">
							<div class="pageDescriptionItem">
								<input class="insertContents" type="text" placeholder="내용을 입력해주세요.">
							</div>
							<div class="pageDescriptionItem">
								<input class="insertBtn" type="button" value="전송" />
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
						<div class="pageImageDesBox">
							<form class="pageImage">
								<input class="insertContents" type="text" name="prompt" placeholder="만들고 싶은 이미지를 설명해주세요." required>
								<button class="insertBtn" type="submit" >만들기</button>
							</form>
						</div>
						<div class="pageImageMakerBox">
						
							<!-- 로딩중 -->
							<div id="loading" class="loading">
								<img src="/sangsangjakka/resources/img/loading.gif">
								<div class="loadingGuide">🎨 오리가 열심히 이미지를 만드는 중입니다 🎨 </div>
							</div>
							
							<!-- 생성된 이미지 -->
							<div id="imageContainer"></div>
							
							<div id="pageImageDesContainer"></div>
						</div>
						
						<div class="pageImageUploadBox">
							<div class="pageImageUploadItem">
								<label for="pageImageUpload" class="custom-file-upload">
								    <i class="fas fa-cloud-upload-alt"></i> 파일 선택하기
								</label>
								<input type="file" name="pageImageUpload" id="pageImageUpload" style="display: none;">
								<input class="insertBtn" type="button" value="사용하기">
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
							<div class="coverImageDesBox">
								<form class="coverImageForm">
									<input class="insertContents" type="text" name="coverprompt" placeholder="어떤 표지를 만들어 볼까요?" required>
									<button class="insertBtn" type="submit" >만들기</button>
								</form>
							</div>
							<div class="coverImageMakerBox" id="coverImageMakerBox">
								<!-- 로딩중 -->
								<div id="coverloading" class="loading">
									<img src="/sangsangjakka/resources/img/loading.gif">
									<div class="loadingGuide">🎨 오리가 열심히 이미지를 만드는 중입니다 🎨  </div>
								</div>
								
								<!-- 생성된 이미지 -->
								<div id="coverImageContainer"></div>
								
								<div id="coverImageDesContainer"></div>
							</div>
							<div class="coverImageUploadBox">
								<div class="coverImageUploadItem">
									<input type="file" name="coverImageUpload" id="coverImageUpload">
									<input type="button" value="표지로 사용하기">
								</div>
							</div>
						</div>
					</div>
					<div class="coverOptionBox">
						<div class="coverOptionItem btnItem pointer" id="coverPrev">이전으로</div>
						<div class="coverOptionItem btnItem pointer" id="coverNext">제목</div>
					</div>
				</div>
				<div class="titleMaker">
					<div class="step">Step 1 (책 정보를 입력해주세요!)</div>
					<div class="titleBox">
					    <div class="titleItem">
					        <input class="insertContents" type="text" placeholder="제목을 입력해주세요." required/>
					    </div>
					</div>
					<div class="bookInfoBox">
					    <div class="bookInfoItem">
					        <input class="insertContents " type="text" placeholder="간단한 책 소개를 입력해주세요." required/>
					    </div>
					</div>
					<div class="step">Step 2 (동화책의 장르를 선택해주세요!)</div>
					<div class="categoryMakerBox">
					    <div class="categoryMaker">
					    	<div class="categoryBox">
					    		<c:forEach items="${genre}" var="dto" varStatus="status">
									<div class="draggableCategory" id="${dto.genreName}">${dto.genreName}</div>
								</c:forEach>
					    	</div>
					    	<div class="categoryWrap">
					    	<div class="categoryInfo">어떤 동화책인가요? (동화책과 관련된 박스를 여기로 이동해주세요!)</div>
						    <div class="categoryDropArea"></div>
						    </div>
					    </div>
					</div>
				    <div class="titleOptionBox">
				        <div class="titleOptionItem btnItem pointer" id="titlePrev">이전으로</div>
				        <div class="titleOptionItem btnItem pointer" id="titleNext">완성</div>
				    </div>
				</div>

			</div>
		</section>

		<div class="bookmakingGridRight"></div>
	</div>

	<!-- footer -->
	<%@include file="/WEB-INF/views/template/footer.jsp"%>

	<script>
			var cmntYN = '${firstpage.cmntYN}';
			var imgYN = '${firstpage.imgYN}';
			var textYN = '';
			var imageYN = '';
			var lastpage = ${lastpage};
			var $currentVisible;
			var imgChangeCheck = 'n';
			var coverImageYN = 'y';
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

				}

				function check() {
					if ('${firstpage.cmntYN}' != '') {
					
					$('.bookmakingOptionContainer').hide();
					$('.bookmakingWrap').show();
					Page.init();

					$('#textAiSupport').prop('checked', cmntYN === 'y');
					$('#imageAiSupport').prop('checked', imgYN === 'y');
					$('#coverAiSupport').prop('checked', coverImageYN === 'y');

					$('#textAiSupport').change();
					$('#imageAiSupport').change();
					$('#coverAiSupport').change();
					}
				}
				
				// Setup the draggable categories
			    $(".draggableCategory").draggable({
			        helper: "clone", // Use a clone as drag helper
			        cursor: "move",
			        snap: ".selectedCategory", // Snap to other selected categories
			        snapMode: "inner",
			        snapTolerance: 20,
			        revert: "invalid", // Revert if not dropped on droppable
			        start: function(event, ui) {
			            ui.helper.width($(this).width()); // Maintain original width
			        }
			    });

			    // Setup the drop area
			    $(".categoryDropArea").droppable({
			        accept: ".draggableCategory",
			        tolerance: "intersect",
			        drop: function(event, ui) {
			            // Create a new selectedCategory at the drop position
			            var newCat = $('<div class="selectedCategory">' + ui.draggable.text() + '</div>').css({
			                position: 'absolute',
			                top: ui.offset.top - $(this).offset().top, // Adjust the top position
			                left: ui.offset.left - $(this).offset().left, // Adjust the left position
			                width: ui.helper.width() // Maintain the dragged width
			            }).appendTo(this).draggable({
			                snap: ".selectedCategory", // Snap to other categories
			                snapMode: "outer",
			                snapTolerance: 20
			            });

			            // Enable removing by dragging out of the drop area
			            newCat.on("dragstop", function(event, ui) {
			                var dropAreaOffset = $(".categoryDropArea").offset();
			                var dropAreaHeight = $(".categoryDropArea").outerHeight();
			                var dropAreaWidth = $(".categoryDropArea").outerWidth();
			                // Check if the element is outside the drop area
			                if (ui.offset.top < dropAreaOffset.top || 
			                    ui.offset.top > dropAreaOffset.top + dropAreaHeight || 
			                    ui.offset.left < dropAreaOffset.left || 
			                    ui.offset.left > dropAreaOffset.left + dropAreaWidth) {
			                    $(this).remove(); // Remove if dragged out
			                }
			            });
			        }
			    });


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
					var pageUrl = imgChangeCheck == 'y' ? '/sangsangjakka/generated/'+userId+'/'+bookSeq+'/'+currentVisibleId+'.jpg' : cleanUrl;
					$.ajax({
						type: 'GET',
						url: '/sangsangjakka/board/bookmaking/editpage.do',
						data: {
							bookSeq: ${bookSeq},
							pageSeq: currentVisibleId,
							cmntYN: textYN,
							imgYN: imageYN,
							pageUrl: pageUrl,
							pageContents: text
						},
						dataType: 'json',
						success: function(result) {
							
							$('#bb-bookblock .bb-item:visible .pageImage').css('background-image', 'url(' + pageUrl + ')');
							
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
					var pageSeq = $('#bb-bookblock .bb-item:visible').attr('id');
					if ($currentVisible.prev('.bb-item').length) {
						$currentVisible.remove();
						$.ajax({
							type: 'POST',
							url: '/sangsangjakka/board/bookmaking/delpage.do',
							data: {
								bookSeq: ${bookSeq},
								pageSeq: pageSeq
							},
							success: function(result) {
								
							},
							error: function(a,b,c) {
								console.log(a,b,c);
							}
						});
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
							formData.append('imageData', file);
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
				
				$('#imageContainer').on('click', '.pageImageMakerItem', function(event) {
					event.preventDefault();
					var imageDataUrl = $(this).find('img.pageImageMakerItemImg').attr('src');
					var imageId = $(this).find('img.pageImageMakerItemImg').attr('id');
	
					if (imageId) {
						var pageSeq = $('#bb-bookblock .bb-item:visible').attr('id');
						var userId = '${userId}';
						var bookSeq = ${bookSeq};
						var basePath = '${basePath}';
						var imagePath = basePath + userId + '/' + imageId;
	
						$('#' + pageSeq + ' .pageImage').css('background-image', 'url(' + imageDataUrl + ')');
						imgChangeCheck = 'y';
	
						var formData = new FormData();
						formData.append('image', imagePath);
						formData.append('userId', userId);
						formData.append('bookSeq', bookSeq);
						formData.append('pageSeq', pageSeq);
						formData.append('basePath', basePath);
	
						$.ajax({
							type: 'POST',
							url: '/sangsangjakka/board/bookmaking/editpage.do',
							data: formData,
							processData: false,
							contentType: false,
							success: function(data) {
								pageChange();
								imgChangeCheck = 'n';
							},
							error: function(xhr, status, error) {
								// Handle errors
								alert('업로드 실패: ' + error);
							}
						});
					} else {
						alert('이미지 발견 실패');
					}
				});
				
				$('#coverImageContainer').on('click', '.coverImageMakerItem', function(event) {
					event.preventDefault();
					var imageDataUrl = $(this).find('img.coverImageMakerItemImg').attr('src');
					var imageId = $(this).find('img.coverImageMakerItemImg').attr('id');
					var coverUrl = '/sangsangjakka/generated/'+userId+'/'+bookSeq+'/cover.jpg';
					
					if (imageId) {
						var pageSeq = 'cover';
						var userId = '${userId}';
						var bookSeq = ${bookSeq};
						var basePath = '${basePath}';
						var imagePath = basePath + userId + '/' + imageId;
	
						$('.coverImage').css('background-image', 'url(' + imageDataUrl + ')');
	
						var formData = new FormData();
						formData.append('imageData', imagePath);
						formData.append('userId', userId);
						formData.append('bookSeq', bookSeq);
						formData.append('pageSeq', pageSeq);
						formData.append('basePath', basePath);
	
						$.ajax({
							type: 'POST',
							url: '/sangsangjakka/board/bookmaking/editcover.do',
							data: formData,
							processData: false,
							contentType: false,
							success: function(data) {
								$('#bb-bookblock .bb-item:visible').css('background-image', 'url(' + coverUrl + ')');
							},
							error: function(xhr, status, error) {
								// Handle errors
								alert('업로드 실패: ' + error);
							}
						});
					} else {
						alert('이미지 발견 실패');
					}
				});
				
				$('#titleNext').click(function() {
					var bookSeq = ${bookSeq};
					var title = $('.titleItem input[type="text"]').val();
					var bookInfo = $('.bookInfoItem input[type="text"]').val();
					
					var categories = new Set();  // Use a Set to automatically handle unique values

			        $('.selectedCategory').each(function() {
			            categories.add($(this).text().trim());
			        });

			        // Convert Set back to Array if needed for compatibility with other systems
			        categories = Array.from(categories);
					$.ajax({
						type: 'POST',
						url: '/sangsangjakka/board/bookmaking/fin.do',
						data: JSON.stringify({
							categories: categories,
							bookSeq: bookSeq,
							title: title,
							bookInfo: bookInfo
							}),
						success: function(result) {
						window.location.href = '/sangsangjakka/board/bookmaking/fin.do?no=' + bookSeq;
						},
						error: function(xhr, status, error) {
							alert('업로드 실패: ' + error);
						}
					});
				});

			});//$(document).ready
			
			//이미지 동적 생성
			$(document).ready(function() {
				$('.pageImage').submit(function(event) {
					event.preventDefault();
					var prompt = $('input[name="prompt"]').val();
					var imageContainer = $('#imageContainer');
					var pageImageDesContainer = $('#pageImageDesContainer');
					imageContainer.empty();
					pageImageDesContainer.empty();
					$('#loading').show(); // 로딩 이미지 표시
					$.ajax({
						url: '/sangsangjakka/board/bookmaking/view.do',
						method: 'POST',
						data: { prompt: prompt },
						success: function(response) {
							response.forEach(function(imageData) {
								var imgDiv = $('<div>').addClass('pageImageMakerItem');
								var img = $('<img>').attr({
				                    'src': 'data:image/png;base64,' + imageData.imageBase64,
				                    'id': imageData.fileName
				                }).addClass('pageImageMakerItemImg');
								imgDiv.append(img);
								imageContainer.append(imgDiv);
							});
							var DesBox = $('<div>').addClass('pageImageDesBox');
							var DesItem = $('<div>').addClass('pageImageDesItem');
							DesItem.text('그림을 선택해주세요!');
							DesBox.append(DesItem);
							pageImageDesContainer.append(DesBox);
							$('#loading').hide(); // 로딩 이미지 숨기기
						},
						error: function() {
							alert('이미지 생성 실패');
							$('#loading').hide(); // 로딩 이미지 숨기기
						}
					});
				});
				$('.coverImageForm').submit(function(event) {
					event.preventDefault();
					var prompt = $('input[name="coverprompt"]').val();
					var coverImageContainer = $('#coverImageContainer');
					var coverImageDesContainer = $('#coverImageDesContainer');
					coverImageContainer.empty();
					coverImageDesContainer.empty();
					$('#coverloading').show(); // 로딩 이미지 표시
					$.ajax({
						url: '/sangsangjakka/board/bookmaking/view.do',
						method: 'POST',
						data: { prompt: prompt },
						success: function(response) {
							response.forEach(function(imageData) {
								var imgDiv = $('<div>').addClass('coverImageMakerItem');
								var img = $('<img>').attr({
				                    'src': 'data:image/png;base64,' + imageData.imageBase64,
				                    'id': imageData.fileName
				                }).addClass('coverImageMakerItemImg');
								imgDiv.append(img);
								coverImageContainer.append(imgDiv);
							});
							var DesBox = $('<div>').addClass('coverImageDesBox');
							var DesItem = $('<div>').addClass('coverImageDesItem');
							DesItem.text('그림을 선택해주세요!');
							DesBox.append(DesItem);
							pageImageDesContainer.append(DesBox);
							$('#coverloading').hide(); // 로딩 이미지 숨기기
						},
						error: function() {
							alert('이미지 생성 실패');
							$('#coverloading').hide(); // 로딩 이미지 숨기기
						}
					});
				});
				$('.pageText').submit(function(event) {
					event.preventDefault();
					$currentVisible = $('#bb-bookblock .bb-item:visible');
					var preText;
					if ($currentVisible.prev('.bb-item').length) {
						preText = '이전페이지 내용' + $currentVisible.prev('.bb-item').find('p').text();
					} else {
						preText = '첫번째 페이지';
					}
					var prompt = preText + ', 현재 페이지 내용: ' + $('input[name="textPrompt"]').val();
					var pageTextMakerBox = $('ul .pageTextMakerBox');
					pageTextMakerBox.empty();
					$('#textLoading').show(); // 로딩 이미지 표시
					$.ajax({
						url: '/sangsangjakka/story.do',
						method: 'POST',
						data: { data: prompt },
						success: function(response) {
							response.forEach(function(data) {
								var pageTextMakerItem = $('<li>').addClass('pageTextMakerItem');
								var span = $('<span>').text(data);
								pageTextMakerItem.append(span);
								var selectTextBox = $('<div>').addClass('selectTextBox');
								var input = $('<input>').attr({
				                    'type': 'submit',
				                    'value': '선택'
				                }).addClass('selectTextItem btnItem middleBtn tomato pointer');
								selectTextBox.append(input);
								var whitespace = $('<div>').addClass('whitespace').text('&nbsp;');
								pageTextMakerItem.append(selectTextBox);
								pageTextMakerItem.append(whitespace);
							});
							$('#textLoading').hide(); // 로딩 이미지 숨기기
						},
						error: function() {
							alert('만들기 실패');
							$('#textLoading').hide(); // 로딩 이미지 숨기기
						}
					});
				});
				
				document.querySelector('.custom-file-upload').addEventListener('click', function() {
				    document.getElementById('pageImageUpload').click();
				});

				
			});//$(document).ready
			
	
	</script>
</body>
</html>