<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<script src="https://kit.fontawesome.com/e075b9b5dc.js" crossorigin="anonymous"></script>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-more.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>
	<script src="https://code.highcharts.com/modules/accessibility.js"></script>

	
	<link rel="stylesheet" href="/sangsangjakka/resources/css/screens/mypage.css">
</head>
<body>

	<!-- header -->
	<%@include file="/WEB-INF/views/template/header.jsp"%>


	<div class="myMainWrap">

		<div class="pageTitle">
			<h3>마이페이지</h3>
		</div>

		<div class="tab tabFormContainer">
			<ul class="mainnav tabnav flex">
				<li><a href="#tab01">개인정보</a></li>
				<li><a href="#tab02">내동화책</a></li>
				<li><a href="#tab03">보관함</a></li>
				<li><a href="#tab04">활동내역</a></li>
			</ul>
			<div class="tabcontent full maintabcontent">
				<div id="tab01">
					<form method="POST" class="userInfoForm">
						<div class="userInfoContainer">
							<div class="userInfo">
								<div class="userInfoEmail">
									<span>이메일</span>
									<div class="emailContainer">
										<input type="text" class="userEmail w70pc" value="${dto.userEmail}" id="userEmailInput" name="email" required readonly/>
									</div>
								</div>
								<div class="userInfoNickName title">
									<span>닉네임</span>
									<div class="changableContainer">
										<div class="changableInput inputBorder">
											<input type="text" id="inputNick" name="nickName" value="${dto.userNick}" required readonly />
										</div>
										<div class="changebtnbox change">
											<input type="button" value="중복검사" id="nickNameChangeBtn" class="pointer" onclick="openNickCheck()">
										</div>
										  <input type="hidden" id="nickHidden" name="nicknameDuplication" value="nicknameUncheck" />
									</div>
									
								</div>

								<div class="userInfoId title">
									<span>아이디</span>
									<div class="idContainer">
										<div class="idInput inputBorder w70pc">
											<input type="text" id="id" value="${dto.userId}" required readonly/>
										</div>
									</div>
								</div>


								<div class="userInfoPw title">
									<span>비밀번호</span>
									<div class="changableContainer">
										<div class="changableInput inputBorder">
											<input type="password" id="passwordInput" required readonly/>
										</div>
										<div class="changebtnbox change">
											<input type="button" value="비밀번호 변경" id="passwordChangeBtn" class="pointer">
										</div>
									</div>
								</div>

								<div class="userInfoName title">
									<span>이름</span>
									<div class="nameContainer">
										<div class="nameInput inputBorder w70pc">
											<input id="nameInput" type="text" value="${dto.userName}" readonly/>
										</div>
									</div>
								</div>


								<div class="userInfoSsn title">
									<span>주민등록번호</span>
									<div class="ssnContainer">
										<div class="ssnInput">
											<input type="text" maxlength="6" class="leftSsn inputBorder"
												oninput="formatSSN(this)" value="${dto.userLeftSsn}" required readonly/>
											<span>-</span>
											<input type="password" maxlength="7" class="rightSsn" value="${dto.userRightSsn}" required readonly/>
										</div>
									</div>
								</div>

								<div class="userInfoTel title">
									<span>연락처</span>
									<div class="telContainer">
										<div class="telInput">
										<!-- 전화번호 받아오기 -->
										<c:set var="userTel" value="${dto.userTel}"/>
										<!-- "-" 기준으로 쪼개기 -->
										<c:set var="telArray" value="${fn:split(userTel,'-')}"/>
					
											<input type="text" id="phoneStart" name="phoneStart" class="phoneInput" maxlength="3"
												value="${telArray[0]}" required readonly>
											<span>-</span>
											<input type="text" id="phoneInput1" name="phoneInput1" class="phoneInput" maxlength="4"
												value="${telArray[1]}" required readonly>
											<span>-</span>
											<input type="text" id="phoneInput2" name="phoneInput2" class="phoneInput" maxlength="4"
												value="${telArray[2]}" required readonly>
										</div>
									</div>
								</div>

								<div class="userInfo-address title">
									<span>주소</span>
									<div class="addressContainer">
										<div class="addressInput">
											<div class="addressFind">
												<div class="inputBorder postNum" id="postcodebox">
													<input type="text" id="sample6_postcode" placeholder="우편번호"
														readonly required>
												</div>
												<div class="changebtnbox change">
													<input type="button" value="우편번호 찾기" id="addressFind"
														class="pointer" onclick="sample6_execDaumPostcode()">
												</div>
											</div>
											<div class="inputBorder" id="addressBox">
												<input type="text" id="sample6_address" name="address" placeholder="주소" required value="${dto.userAddress}" readonly>
											</div>
											<div class="inputBorder" id="addressDetailBox">
												<input type="text" id="sample6_detailAddress" placeholder="상세주소" readonly
													>
											</div>
											<div class="inputBorder" id="addressExtraBox">
												<input type="text" id="sample6_extraAddress" placeholder="참고항목" readonly
													>
											</div>
										</div>
									</div>
								</div>

							</div>
							<div class="changebtncontainer">
								<div class="changecomplete changebtnbox full">
									<input type="button" value="수정하기" id="changebtn" class="pointer"></input>
								</div>
								<div class="change full">
									<div class="changegroup">
										<div class="changebtnbox">                                                                                                                                                                                                                                                                          
											<input type="submit" value="수정완료" id="changecompletebtn"
												class="pointer"></input>
										</div>
										<div class="changebtnbox">
											<input type="button" value="취소" id="cancelbtn" class="pointer"></input>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div id="tab02">
					<figure class="highcharts-figure">
						<div id="mybook"></div>
					</figure>
					<div class="bookshelfControl">
						<div class="searchContainer">
							<div class="searchBox">
								<input type="text" id="searchInput" placeholder="도서명">
								<div class="searchIcon">
									<i class="fa-solid fa-magnifying-glass"></i>
								</div>
							</div>
						</div>
						<div class="makingContainer">
							<a href="/sangsangjakka/board/bookmaking/view.do">
								<div class="makingBox">
									<input type="button" id="bookmakeBtn" value="도서 만들기">
									<i class="fa-solid fa-wand-magic-sparkles"></i>
								</div>
							</a>
						</div>
						<div class="sortingContainer">
							<div class="sortingBox">
								<select id="sortOptions">
									<option value="newest">최신순</option>
									<option value="oldest">오래된순</option>
									<option value="name">이름순</option>
								</select>
							</div>
						</div>
					</div>
					<div class="bookshelf" id="bookshelf"></div>
					<div class="pagination">
						<button id="firstPage">&lt;&lt;</button>
						<button id="prevPage">&lt;</button>
						<span id="pageList"></span>
						<button id="nextPage">&gt;</button>
						<button id="lastPage">&gt;&gt;</button>
					</div>
				</div>
				<div id="tab03">
					<figure class="highcharts-figure">
						<div id="scrap"></div>
					</figure>
				</div>
				<div id="tab04">
					<ul class="subnav tabnav flex">
						<li><a href="#tab05">내 게시글</a></li>
						<li><a href="#tab06">내 댓글</a></li>
					</ul>
					
					<div class="tabcontent subtabcontent">
						<div id="tab05">
				<table class="boardTable">
                  <thead>
                    <tr>
                        <th scope="col" class="thNum thList">번호</th>
                        <th scope="col" class="thTitle thList">제목</th>
                        <th scope="col" class="thDate thList">등록일</th>
                        <th scope="col" class="thViews thList">조회수</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                 <c:if test="${requestScope.list.size() == 0}">
				    <tr>
				        <td colspan="5">게시물이 없습니다.</td>
				    </tr>
				</c:if>
				
				<c:forEach items="${requestScope.list}" var="dto">
				    <tr class=myBoardList>
				        <td>${dto.boardSeq}</td>
				        <th><a href="/sangsangjakka/board/freeboard/view.do?no=${dto.boardSeq}">${dto.boardTitle}</a><p>${dto.boardCnt}</p></th>
				        <td>${dto.boardRegdate}</td>
				        <td>${dto.boardCnt}</td>
				    </tr>
				</c:forEach>
         
                  </tbody>
              </table>
					</div>
					
						<div id="tab05">
				<table class="boardTable">
                  <thead>
                    <tr>
                        <th scope="col" class="thNum thList">번호</th>
                        <th scope="col" class="thTitle thList">제목</th>
                        <th scope="col" class="thDate thList">등록일</th>
                        <th scope="col" class="thViews thList">조회수</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                 <c:if test="${requestScope.list.size() == 0}">
				    <tr>
				        <td colspan="5">게시물이 없습니다.</td>
				    </tr>
				</c:if>
				
				<c:forEach items="${requestScope.list}" var="dto">
				    <tr class=myBoardList>
				        <td>${dto.boardSeq}</td>
				        <th><a href="/sangsangjakka/board/freeboard/view.do?no=${dto.boardSeq}">${dto.boardTitle}</a><p>${dto.boardCnt}</p></th>
				        <td>${dto.boardRegdate}</td>
				        <td>${dto.boardCnt}</td>
				    </tr>
				</c:forEach>
         
                  </tbody>
              </table>
					</div>
					
					<div id="tab06">
					    <table class="boardTable">
					        <thead>
					            <tr>
					                <th scope="col" class="thNum thList">번호</th>
					                <th scope="col" class="thTitle thList">내용</th>
					                <th scope="col" class="thDate thList">등록일</th>
					                <th scope="col" class="thViews thList">신고횟수</th>
					            </tr>
					        </thead>
					        <tbody>
					            <c:if test="${empty comments}">
					                <tr>
					                    <td colspan="5">댓글이 없습니다.</td>
					                </tr>
					            </c:if>
					            <c:forEach items="${comments}" var="dto">
					                <tr class="myBoardList">
					                    <td>${dto.cmntSeq}</td>
					                    <th><a href="/sangsangjakka/board/freeboard/view.do?no=${dto.boardSeq}">${dto.cmntContents}</a></th>
					                    <td>${dto.cmntRegdate}</td>
					                    <td>${dto.cmntReportCnt}</td>
					                </tr>
					            </c:forEach>
					        </tbody>
					    </table>
					</div>
					
					</div>
				</div>
			</div>
		</div><!--tab-->
	</div>


	<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="validation.js"></script>
<script>
		
		var bookList = [];
		<c:forEach items="${bookList}" var="book">
		    var book = {
		        bookSeq: '${book.bookSeq}',
		        bookTitle: '${book.bookTitle}', 
		        bookInfo: '${book.bookInfo}',
		        bookCover: '${book.bookCover}',
		        bookRegdate: '${book.bookRegdate}'
		    };
		    bookList.push(book); 
		</c:forEach>
		
		
		var tendencyData = [
		    <c:forEach var="entry" items="${tendency}">
		        ${entry.value},
		    </c:forEach>
		];


		$(document).ready(function () {
			// 로딩 시 모든 탭 컨텐츠를 숨기고 첫 번째 탭 컨텐츠를 표시
			$('.maintabcontent > div').hide().first().show();
			$('.mainnav a').first().addClass('active');
		
			// 메인 탭 클릭 이벤트
			$('.mainnav a').on('click', function (e) {
				e.preventDefault(); // 기본 이벤트 방지
				var tabId = $(this).attr('href'); // 클릭된 탭의 href 속성 값 가져오기
		
				// 메인 탭 내용 전환
				$('.maintabcontent > div').hide();
				$(tabId).fadeIn();
				$('.mainnav a').removeClass('active');
				$(this).addClass('active');
				
				// 높이 변화
				if (tabId === '#tab01') {
					$('.tab').css("height", "1600px");
				} else {
					$('.tab').css("height", "900px");
				}
		
				// 서브 탭 초기화
				if (tabId === '#tab04') {
					$('.subnav a').first().addClass('active');
					$('.subtabcontent > div').hide().first().show();
				} else {
					$('.subnav a').removeClass('active');
					$('.subtabcontent > div').hide();
				}
			});
		
			// 서브 탭 클릭 이벤트
			$('.subnav a').on('click', function (e) {
				e.preventDefault();
				var subTabId = $(this).attr('href'); // 클릭된 서브 탭의 href 속성 값 가져오기
		
				$('.subtabcontent > div').hide();
				$(subTabId).fadeIn();
				$('.subnav a').removeClass('active');
				$(this).addClass('active');
			});
		});
		
		
		
		// tab에 있는 인라인 style 변경
		// 페이지가 처음 로드될 때 실행될 함수
		if (!sessionStorage.getItem("hasBeenLoaded")) {
		    window.onload = function() {
		        var tabFormContainer = document.querySelector(".tabFormContainer");
		        tabFormContainer.style.height = "1400px";
		        sessionStorage.setItem("hasBeenLoaded", true);
		    };
		}
		

		$(function () {
			//수정 완료 버튼
			$('#changecompletebtn').click(function () {
				$('.changecomplete').css('display', 'flex');
				$('.change').css('display', 'none');
				$('.userInfoForm').submit()
				alert('변경사항이 저장되었습니다.');
			});
			//수정 취소 버튼
			$('#cancelbtn').click(function () {
				$('.changecomplete').css('display', 'flex');
				$('.change').css('display', 'none');
			});

			//수정 하기 버튼
			$('#changebtn').click(function () {
				$('.changecomplete').css('display', 'none');
				$('.change').css('display', 'flex');
			});
			
			//changebtn 눌렀을때만 입력 가능
			document.getElementById('changebtn').addEventListener('click', function() {
			    document.getElementById('userEmailInput').removeAttribute('readonly');
			    document.getElementById('inputNick').removeAttribute('readonly');
			    document.getElementById('phoneStart').removeAttribute('readonly');
			    document.getElementById('phoneInput1').removeAttribute('readonly');
			    document.getElementById('phoneInput2').removeAttribute('readonly');
			    document.getElementById('sample6_postcode').removeAttribute('readonly');
			    document.getElementById('sample6_address').removeAttribute('readonly');
			    document.getElementById('sample6_detailAddress').removeAttribute('readonly');
			    document.getElementById('sample6_extraAddress').removeAttribute('readonly');
			    
			});
			
			
			
			// 초기에는 주소 입력란과 수정하기 버튼만 보이도록 설정.
			document.getElementById("postcodebox").style.display = "none";
			document.getElementById("addressDetailBox").style.display = "none";
			document.getElementById("addressExtraBox").style.display = "none";

			// 수정하기 버튼을 누를 때
			document.getElementById("changebtn").addEventListener("click", function() {
			    // 우편번호 입력란과 상세주소, 참고항목 입력란을 보이도록 변경
			    document.getElementById("postcodebox").style.display = "block";
			    document.getElementById("addressDetailBox").style.display = "block";
			    document.getElementById("addressExtraBox").style.display = "block";
			});

			// 수정완료 버튼을 누를 때
			document.getElementById("changecompletebtn").addEventListener("click", function() {
			    // 우편번호 입력란과 상세주소, 참고항목 입력란을 다시 숨김
			    document.getElementById("postcodebox").style.display = "none";
			    document.getElementById("addressDetailBox").style.display = "none";
			    document.getElementById("addressExtraBox").style.display = "none";
			});

			
			// 취소 버튼을 누를 때
			document.getElementById("cancelbtn").addEventListener("click", function() {
			    // 우편번호 입력란과 상세주소, 참고항목 입력란을 다시 숨김.
			    document.getElementById("postcodebox").style.display = "none";
			    document.getElementById("addressDetailBox").style.display = "none";
			    document.getElementById("addressExtraBox").style.display = "none";
			});
			
			
			//span태그 색상 변경
			
			// 수정하기 버튼을 클릭할 때 실행될 함수
			document.getElementById("changebtn").addEventListener("click", function() {
			    document.querySelector(".userInfo-address.title span").style.color = "#c95000";
			    document.querySelector(".userInfoTel.title span").style.color = "#c95000";
			    document.querySelector(".userInfoEmail span").style.color = "#c95000";
			    document.querySelector(".userInfoNickName span").style.color = "#c95000";
			    document.querySelector(".userInfoPw span").style.color = "#c95000";			    
			});

			// 수정완료 또는 취소 버튼을 클릭할 때 실행될 함수
			function resetTextColor() {
			    // 주소 텍스트 요소의 색상을 원래 색상으로 재설정합니다.
			    document.querySelector(".userInfo-address.title span").style.color = "";
			    document.querySelector(".userInfoTel.title span").style.color = "";
			    document.querySelector(".userInfoEmail span").style.color = "";
			    document.querySelector(".userInfoNickName span").style.color = "";
			    document.querySelector(".userInfoPw span").style.color = "";
			}

			// 수정완료 버튼을 클릭할 때 색상을 원래대로 변경합니다.
			document.getElementById("changecompletebtn").addEventListener("click", resetTextColor);

			// 취소 버튼을 클릭할 때 색상을 원래대로 변경합니다.
			document.getElementById("cancelbtn").addEventListener("click", resetTextColor);
			
			
			
			
			
			

			//내 동화책 차트
			Highcharts.chart('mybook', {

				chart: {
					polar: true
				},

				title: {
					text: null // 차트 이름 비활성화
				},

				exporting: {
					enabled: false // 차트 메뉴 비활성화
				},

				pane: {
					startAngle: 0,
					endAngle: 360
				},

				xAxis: {
					tickInterval: 72,
					min: 0,
					max: 360,
					labels: {
						formatter: function () {
							const labels = ['창의성 성향', '교육 성향', '모험성향', '감성 성향', '유머 성향'];
							return labels[(this.value / 72) % labels.length];
						}
					}
				},

				yAxis: {
					min: 0
				},

				tooltip: {
					formatter: function () {
						return this.y; // `this.y`는 호버된 데이터 포인트의 값
					}
				},

				plotOptions: {
					series: {
						pointStart: 0,
						pointInterval: 72,
						showInLegend: false
					},
					column: {
						pointPadding: 0,
						groupPadding: 0
					}
				},

				series: [{
					type: 'area',
					name: 'Area',
					color: '#ffa500',
					data: tendencyData
				}],
				credits: {
					enabled: false // 크레딧을 비활성화
				}
			});

			//저장목록 차트
			Highcharts.chart('scrap', {

				chart: {
					polar: true
				},

				title: {
					text: null // 차트 이름 비활성화
				},

				exporting: {
					enabled: false // 차트 메뉴 비활성화
				},

				pane: {
					startAngle: 0,
					endAngle: 360
				},

				xAxis: {
					tickInterval: 72,
					min: 0,
					max: 360,
					labels: {
						formatter: function () {
							const labels = ['모험 성향', '감성 성향', '교육 성향', '유머 성향', '창의성 성향'];
							return labels[(this.value / 72) % labels.length];
						}
					}
				},

				yAxis: {
					min: 0
				},

				tooltip: {
					formatter: function () {
						return this.y; // `this.y`는 호버된 데이터 포인트의 값
					}
				},

				plotOptions: {
					series: {
						pointStart: 0,
						pointInterval: 72,
						showInLegend: false
					},
					column: {
						pointPadding: 0,
						groupPadding: 0
					}
				},

				series: [{
					type: 'area',
					name: 'Area',
					color: '#ffa500',
					data: chartData
				}],
				credits: {
					enabled: false // 크레딧을 비활성화
				}
			});
		});

		// 도서 데이터
		let bookData = bookList;

		document.addEventListener("DOMContentLoaded", function () {
			document.getElementById('sortOptions').addEventListener('change', sortBooks);
			document.querySelector('.searchIcon').addEventListener('click', searchBooks);
			document.getElementById('firstPage').addEventListener('click', () => goToPage(1));
			document.getElementById('prevPage').addEventListener('click', () => goToPage(currentPage - 1));
			document.getElementById('nextPage').addEventListener('click', () => goToPage(currentPage + 1));
			document.getElementById('lastPage').addEventListener('click', () => goToPage(totalPages));

			updateFilteredBooks();
			sortBooks(); // 초기에 최신순으로 정렬
			updatePagination();
		});

		let filteredBooks = [];
		let currentPage = 1;
		const itemsPerPage = 12;
		let totalPages;

		function updateFilteredBooks() {
			filteredBooks = bookData.slice(); // 기본적으로 전체 데이터를 복사
			totalPages = Math.ceil(filteredBooks.length / itemsPerPage);
			updatePaginationControls();
		}

		function renderBooks(data) {
		    const bookshelf = document.getElementById('bookshelf');
		    bookshelf.innerHTML = ''; // 기존 내용 지우기
		    
		    if (data.length === 0) {
		        const noBooksDiv = document.createElement('div');
		        noBooksDiv.className = 'no-books';
		        noBooksDiv.textContent = '책이 없습니다.';
		        
		        bookshelf.appendChild(noBooksDiv);
		    } else {
		        const startIndex = (currentPage - 1) * itemsPerPage;
		        const endIndex = Math.min(startIndex + itemsPerPage, data.length);
		        const visibleBooks = data.slice(startIndex, endIndex);
		        
		        visibleBooks.forEach(book => {
		            const bookDiv = document.createElement('div');
		            bookDiv.className = 'book';
		            bookDiv.innerHTML = `
		                <img src="${book.bookCover}" alt="${book.bookTitle}">
		                <div class="book-info">
		                  <h3><a href="/sangsangjakka/board/book/view.do?no=${book.bookSeq}">${book.bookTitle}</a></h3>
		                  <p>${book.bookRegdate}</p>
		                </div>
		            `;
		            bookshelf.appendChild(bookDiv);
		        });
		        
		        fillEmptySlots(bookshelf);
		    }
		}

		function fillEmptySlots(bookshelf) {
			while (bookshelf.children.length < itemsPerPage) {
				const dummyDiv = document.createElement('div');
				dummyDiv.className = 'book';
				dummyDiv.textContent = '더미 데이터';
				dummyDiv.style.visibility = 'hidden';
				bookshelf.appendChild(dummyDiv);
			}
		}

		function sortBooks() {
			const sortOption = document.getElementById('sortOptions').value;
		    if (sortOption === 'newest') {
		        filteredBooks.sort((a, b) => new Date(b.bookRegdate) - new Date(a.bookRegdate));
		    } else if (sortOption === 'oldest') {
		        filteredBooks.sort((a, b) => new Date(a.bookRegdate) - new Date(b.bookRegdate));
		    } else {
		        filteredBooks.sort((a, b) => a.bookTitle.localeCompare(b.bookTitle));
		    }
			currentPage = 1; // 항상 첫 페이지로 리셋
			renderBooks(filteredBooks);
			updatePagination();
		}

		function searchBooks() {
			let searchTerm = document.getElementById('searchInput').value.toLowerCase();
		    filteredBooks = bookData.filter(book => book.bookTitle.toLowerCase().includes(searchTerm));
			totalPages = Math.ceil(filteredBooks.length / itemsPerPage);
			currentPage = Math.max(1, currentPage); // 검색 결과가 없어도 currentPage는 최소값 1
			sortBooks();
			updatePagination();
			updatePaginationControls();
		}

		function updatePagination() {
			const pageList = document.getElementById('pageList');
			pageList.innerHTML = '';

			let startPage = Math.max(1, currentPage - 2);
			let endPage = Math.min(startPage + 4, totalPages);
			if (endPage<1) {endPage=1;}
			for (let i = startPage; i <= endPage; i++) {
				const pageSpan = document.createElement('span');
				pageSpan.textContent = i;
				pageSpan.classList.toggle('active', i === currentPage);
				pageSpan.style.cursor = 'pointer';
				pageSpan.onclick = () => goToPage(i);
				pageList.appendChild(pageSpan);
			}
			updatePaginationControls();
		}

		function updatePaginationControls() {
			const controls = ['firstPage', 'prevPage', 'nextPage', 'lastPage'];
			controls.forEach(control => {
				document.getElementById(control).disabled = filteredBooks.length === 0 || totalPages <= 1;
			});
		}

		function goToPage(page) {
			if (page < 1 || page > totalPages) return;
			currentPage = page;
			renderBooks(filteredBooks);
			updatePagination();
		}
		
		
		
		
		//주소 입력
		function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
		
		
		
		
		
		//전화번호
		
		function validateInput(input) {
	        // 입력된 값이 숫자가 아니면 삭제
	        input.value = input.value.replace(/\D/g, '');
	        }

	        // 입력창 자동 이동 함수
	        document.getElementById('phoneInput1').addEventListener('input', function() {
	            if (this.value.length >= this.maxLength) {
	                document.getElementById('phoneInput2').focus();
	            }
	        });

	        document.getElementById('phoneInput2').addEventListener('input', function() {
	            if (this.value.length === 0) {
	                document.getElementById('phoneInput1').focus();
	            }
	        });

		
		
		// 닉네임 유효성 및 중복 검사
		document.addEventListener("DOMContentLoaded", function() {
		    let inputNickName = document.getElementById("inputNick");

		    inputNickName.addEventListener("keydown", function() {
		        let value = inputNickName.value;
		        let isValidLength = nickNameLength(value);
		        let isValidNickName = checkNickName(value);

		        // 이전에 추가된 모든 에러 메시지 삭제
		        removeErrorMessages();

		        // 글자수와 닉네임 형식이 모두 유효하지 않은 경우 메시지 표시
		        if (!isValidLength && !isValidNickName) {
		            showErrorMessage("  4~10 글자로 한글, 영어소문자, 숫자만 입력 가능합니다.");
		        } else {
		            // 글자수가 유효하지 않은 경우 메시지 표시
		            if (!isValidLength) {
		                showErrorMessage("  4~10 글자로 입력해주세요.");
		            }

		            // 닉네임 형식이 유효하지 않은 경우 메시지 표시
		            if (!isValidNickName) {
		                showErrorMessage("  한글, 영어소문자, 숫자만 입력 가능합니다.");
		            }
		        }
		    });  
		    

		    function nickNameLength(value) {
		        return value.length >= 4 && value.length <= 10;
		    }

		    function checkNickName(value) {
		        return /^[a-zA-Z0-9가-힣]*$/.test(value);
		    }

		    function showErrorMessage(message) {
		        let errorMessage = document.createElement("span");
		        errorMessage.textContent = message;
		        errorMessage.style.color = "red";
		        errorMessage.style.fontSize = "0.8em";
		        errorMessage.id = "nickNameErrorMessage";

		        let errorContainer = document.querySelector(".userInfoNickName.title");
		        errorContainer.appendChild(errorMessage);
		    }

		    function removeErrorMessages() {
		        let errorMessages = document.querySelectorAll("#nickNameErrorMessage");
		        errorMessages.forEach(errorMessage => errorMessage.remove());
		    }
		});

	
		
		//중복검사
		function signUp() {
	    var idDuplicationValue = document.getElementById("idHidden").value;
	    var nickDuplicationValue = document.getElementsById("nickHidden").value;

	    if (nickDuplicationValue !== "nickChecked") {
	        alert("닉네임 중복검사를 먼저 실행해주세요.");
	        return false;
	    }

	}
		
		
		
		//닉네임 중복체크 화면
		function openNickCheck() {
			window.name = "parent2Form";
			window.open("/sangsangjakka/user/nickcheck.do",
					"checkFrom","width=500, height=300, resizable=no, scrollbars=no");
		}	
		
		
		//비밀번호 변경
		document.getElementById("passwordChangeBtn").addEventListener("click", function() {
    window.location.href = "/sangsangjakka/user/change_pw.do";
});
		
		

</script>
</body>
</html>