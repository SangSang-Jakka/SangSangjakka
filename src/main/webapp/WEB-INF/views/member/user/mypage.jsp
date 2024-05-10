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

		<div class="articleTitle">
			<h1>마이페이지</h1>
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
								<div class="userInfoNcikName title">
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
											<input type="button" value="변경" id="passwordCheck" class="pointer">
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
										<div class="changebtnbox change">
											<input type="button" value="변경" id="telChangebtn" class="pointer">
										</div>
									</div>
								</div>

								<div class="userInfo-address title">
									<span>주소</span>
									<div class="addressContainer">
										<div class="addressInput">
											<div class="addressFind">
												<div class="inputBorder postNum">
													<input type="text" id="sample6_postcode" placeholder="우편번호"
														readonly required>
												</div>
												<div class="changebtnbox change">
													<input type="button" value="우편번호 찾기" id="addressFind"
														class="pointer" onclick="sample6_execDaumPostcode()">
												</div>
											</div>
											<div class="inputBorder">
												<input type="text" id="sample6_address" name="address" placeholder="주소" required value="${dto.userAddress}" readonly>
											</div>
											<div class="inputBorder">
												<input type="text" id="sample6_detailAddress" placeholder="상세주소" readonly
													>
											</div>
											<div class="inputBorder">
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
                        <th scope="col" class="thNum">번호</th>
                        <th scope="col" class="thTitle">제목</th>
                        <th scope="col" class="thDate">등록일</th>
                        <th scope="col" class="thViews">조회수</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  <c:if test="${list.size() == 0}">
					<tr>
						<td colspan="5">게시물이 없습니다.</td>
					</tr>
				  </c:if>
				  
				  <c:forEach items="${list}" var="dto">
				  		<tr>
	                      <td>${dto.boardSeq}</td>
	                      <th><a href="/sangsangjakka/board/freeboard/view.do?no=${dto.boardSeq}">${dto.boardTitle }</a><p>${dto.cmntCnt}</p></th>
	                      <td>${dto.boardRegdate}</td>
	                      <td>${dto.boardCnt}</td>
	                   </tr>
				  </c:forEach>
         
                  </tbody>
              </table>
						</div>
						<div id="tab06">
							tab6 content
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
							const labels = ['항목1', '항목2', '항목3', '항목4', '항목5'];
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
					data: [1, 2, 3, 4, 5]
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
							const labels = ['항목1', '항목2', '항목3', '항목4', '항목5'];
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
					data: [3, 2, 3, 4, 2]
				}],
				credits: {
					enabled: false // 크레딧을 비활성화
				}
			});
		});

		// 도서 데이터
		let bookData = [{ "id": 1, name: "Book U", date: "2023-08-30" }, { "id": 2, name: "Book L", date: "2022-10-05" }, { "id": 3, name: "Book O", date: "2021-11-28" }, { "id": 4, name: "Book E", date: "2023-07-24" }, { "id": 5, name: "Book F", date: "2023-12-21" }, { "id": 6, name: "Book I", date: "2022-02-02" }, { "id": 7, name: "Book V", date: "2022-01-31" }, { "id": 8, name: "Book W", date: "2022-08-20" }, { "id": 9, name: "Book V", date: "2023-11-01" }, { "id": 10, name: "Book J", date: "2023-11-17" }, { "id": 11, name: "Book E", date: "2021-11-04" }, { "id": 12, name: "Book G", date: "2022-08-24" }, { "id": 13, name: "Book A", date: "2022-01-15" }, { "id": 14, name: "Book B", date: "2022-06-22" }, { "id": 15, name: "Book R", date: "2023-09-17" }, { "id": 16, name: "Book H", date: "2023-05-20" }, { "id": 17, name: "Book P", date: "2023-12-27" }, { "id": 18, name: "Book H", date: "2023-09-19" }, { "id": 19, name: "Book I", date: "2021-09-26" }, { "id": 20, name: "Book H", date: "2021-04-10" }, { "id": 21, name: "Book P", date: "2022-10-19" }, { "id": 22, name: "Book T", date: "2023-04-03" }, { "id": 23, name: "Book E", date: "2022-05-27" }, { "id": 24, name: "Book O", date: "2023-05-04" }, { "id": 25, name: "Book T", date: "2021-08-01" }, { "id": 26, name: "Book S", date: "2022-09-17" }, { "id": 27, name: "Book A", date: "2021-03-22" }, { "id": 28, name: "Book A", date: "2022-06-25" }, { "id": 29, name: "Book V", date: "2021-07-30" }, { "id": 30, name: "Book X", date: "2021-06-16" }, { "id": 31, name: "Book A", date: "2023-11-07" }, { "id": 32, name: "Book U", date: "2023-03-04" }, { "id": 33, name: "Book N", date: "2023-02-19" }, { "id": 34, name: "Book K", date: "2021-12-14" }, { "id": 35, name: "Book U", date: "2023-11-01" }, { "id": 36, name: "Book F", date: "2022-02-03" }, { "id": 37, name: "Book A", date: "2023-12-22" }, { "id": 38, name: "Book J", date: "2021-02-26" }, { "id": 39, name: "Book O", date: "2022-05-19" }, { "id": 40, name: "Book I", date: "2023-11-16" }, { "id": 41, name: "Book R", date: "2021-01-24" }, { "id": 42, name: "Book F", date: "2021-10-25" }, { "id": 43, name: "Book C", date: "2021-12-07" }, { "id": 44, name: "Book S", date: "2021-08-19" }, { "id": 45, name: "Book R", date: "2023-10-29" }, { "id": 46, name: "Book B", date: "2023-09-30" }, { "id": 47, name: "Book I", date: "2021-11-28" }, { "id": 48, name: "Book Q", date: "2021-09-07" }, { "id": 49, name: "Book T", date: "2021-07-07" }, { "id": 50, name: "Book B", date: "2023-10-27" }, { "id": 51, name: "Book M", date: "2022-12-25" }, { "id": 52, name: "Book B", date: "2023-04-21" }, { "id": 53, name: "Book U", date: "2021-11-27" }, { "id": 54, name: "Book J", date: "2023-01-13" }, { "id": 55, name: "Book I", date: "2022-06-15" }, { "id": 56, name: "Book L", date: "2023-04-29" }, { "id": 57, name: "Book H", date: "2023-09-28" }, { "id": 58, name: "Book B", date: "2022-03-07" }, { "id": 59, name: "Book E", date: "2022-07-28" }, { "id": 60, name: "Book X", date: "2021-01-31" }, { "id": 61, name: "Book R", date: "2021-10-02" }, { "id": 62, name: "Book E", date: "2022-01-24" }, { "id": 63, name: "Book U", date: "2022-03-27" }, { "id": 64, name: "Book G", date: "2022-05-20" }, { "id": 65, name: "Book I", date: "2023-12-05" }, { "id": 66, name: "Book G", date: "2022-04-04" }, { "id": 67, name: "Book S", date: "2021-09-07" }, { "id": 68, name: "Book E", date: "2023-03-21" }, { "id": 69, name: "Book O", date: "2023-08-03" }, { "id": 70, name: "Book U", date: "2023-03-15" }, { "id": 71, name: "Book D", date: "2023-12-10" }, { "id": 72, name: "Book P", date: "2022-10-08" }, { "id": 73, name: "Book X", date: "2021-08-21" }, { "id": 74, name: "Book H", date: "2021-07-25" }, { "id": 75, name: "Book L", date: "2021-02-04" }, { "id": 76, name: "Book U", date: "2022-09-13" }, { "id": 77, name: "Book P", date: "2022-12-23" }, { "id": 78, name: "Book V", date: "2021-04-30" }, { "id": 79, name: "Book A", date: "2022-11-01" }, { "id": 80, name: "Book V", date: "2021-05-04" }, { "id": 81, name: "Book U", date: "2022-11-12" }, { "id": 82, name: "Book E", date: "2022-07-29" }, { "id": 83, name: "Book M", date: "2021-11-19" }, { "id": 84, name: "Book V", date: "2021-01-29" }, { "id": 85, name: "Book N", date: "2021-04-23" }, { "id": 86, name: "Book U", date: "2022-09-06" }, { "id": 87, name: "Book V", date: "2021-04-11" }, { "id": 88, name: "Book O", date: "2022-06-07" }, { "id": 89, name: "Book A", date: "2022-11-08" }, { "id": 90, name: "Book G", date: "2023-08-08" }, { "id": 91, name: "Book O", date: "2022-09-01" }, { "id": 92, name: "Book E", date: "2021-10-28" }, { "id": 93, name: "Book B", date: "2022-12-03" }, { "id": 94, name: "Book C", date: "2022-03-10" }, { "id": 95, name: "Book E", date: "2021-05-22" }, { "id": 96, name: "Book X", date: "2023-07-19" }, { "id": 97, name: "Book K", date: "2021-04-30" }, { "id": 98, name: "Book G", date: "2021-07-09" }, { "id": 99, name: "Book I", date: "2021-04-10" }, { "id": 100, name: "Book N", date: "2023-10-02" }, { "id": 101, name: "Book Q", date: "2022-07-01" }, { "id": 102, name: "Book D", date: "2023-11-01" }, { "id": 103, name: "Book O", date: "2021-03-19" }, { "id": 104, name: "Book W", date: "2022-07-31" }, { "id": 105, name: "Book F", date: "2021-04-30" }, { "id": 106, name: "Book C", date: "2022-09-04" }, { "id": 107, name: "Book O", date: "2021-12-12" }, { "id": 108, name: "Book T", date: "2023-05-18" }, { "id": 109, name: "Book J", date: "2021-10-27" }, { "id": 110, name: "Book W", date: "2023-11-27" }, { "id": 111, name: "Book I", date: "2023-03-19" }, { "id": 112, name: "Book I", date: "2022-12-17" }, { "id": 113, name: "Book V", date: "2022-07-02" }, { "id": 114, name: "Book I", date: "2021-12-12" }, { "id": 115, name: "Book U", date: "2022-02-03" }, { "id": 116, name: "Book D", date: "2022-04-27" }, { "id": 117, name: "Book M", date: "2022-04-24" }, { "id": 118, name: "Book C", date: "2023-09-25" }, { "id": 119, name: "Book V", date: "2021-03-28" }, { "id": 120, name: "Book D", date: "2023-06-14" }, { "id": 121, name: "Book U", date: "2021-04-17" }, { "id": 122, name: "Book E", date: "2021-07-29" }, { "id": 123, name: "Book J", date: "2022-07-17" }, { "id": 124, name: "Book S", date: "2023-12-06" }, { "id": 125, name: "Book G", date: "2023-03-08" }, { "id": 126, name: "Book M", date: "2023-06-14" }, { "id": 127, name: "Book I", date: "2022-01-27" }, { "id": 128, name: "Book A", date: "2023-12-17" }, { "id": 129, name: "Book R", date: "2021-05-12" }, { "id": 130, name: "Book O", date: "2021-01-01" }, { "id": 131, name: "Book B", date: "2021-06-18" }, { "id": 132, name: "Book G", date: "2022-06-16" }, { "id": 133, name: "Book E", date: "2021-01-03" }, { "id": 134, name: "Book E", date: "2022-07-23" }, { "id": 135, name: "Book H", date: "2022-10-24" }, { "id": 136, name: "Book F", date: "2023-07-31" }, { "id": 137, name: "Book P", date: "2022-12-16" }, { "id": 138, name: "Book I", date: "2022-09-29" }, { "id": 139, name: "Book U", date: "2022-11-22" }, { "id": 140, name: "Book P", date: "2022-06-22" }, { "id": 141, name: "Book B", date: "2021-11-04" }, { "id": 142, name: "Book C", date: "2023-06-27" }, { "id": 143, name: "Book F", date: "2021-08-19" }, { "id": 144, name: "Book D", date: "2022-09-24" }, { "id": 145, name: "Book F", date: "2021-12-18" }, { "id": 146, name: "Book O", date: "2021-07-05" }, { "id": 147, name: "Book M", date: "2021-03-16" }, { "id": 148, name: "Book N", date: "2021-05-31" }, { "id": 149, name: "Book C", date: "2022-03-29" }, { "id": 150, name: "Book Q", date: "2023-07-02" }, { "id": 151, name: "Book W", date: "2022-07-21" }, { "id": 152, name: "Book I", date: "2021-09-04" }, { "id": 153, name: "Book U", date: "2023-05-17" }, { "id": 154, name: "Book V", date: "2023-08-18" }, { "id": 155, name: "Book H", date: "2022-12-12" }, { "id": 156, name: "Book J", date: "2023-11-14" }, { "id": 157, name: "Book V", date: "2023-07-10" }, { "id": 158, name: "Book P", date: "2021-06-06" }, { "id": 159, name: "Book V", date: "2023-02-26" }, { "id": 160, name: "Book D", date: "2023-09-07" }, { "id": 161, name: "Book L", date: "2021-09-21" }, { "id": 162, name: "Book S", date: "2023-07-05" }, { "id": 163, name: "Book U", date: "2021-04-04" }, { "id": 164, name: "Book E", date: "2023-05-24" }, { "id": 165, name: "Book D", date: "2022-11-30" }, { "id": 166, name: "Book X", date: "2023-05-06" }, { "id": 167, name: "Book H", date: "2022-06-07" }, { "id": 168, name: "Book D", date: "2021-09-12" }, { "id": 169, name: "Book C", date: "2021-07-14" }, { "id": 170, name: "Book M", date: "2022-07-13" }];

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
				// 책 데이터가 없으면 메시지 출력
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
					bookDiv.textContent = book.name + ' (' + book.date + ')';
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
				filteredBooks.sort((a, b) => new Date(b.date) - new Date(a.date));
			} else if (sortOption === 'oldest') {
				filteredBooks.sort((a, b) => new Date(a.date) - new Date(b.date));
			} else {
				filteredBooks.sort((a, b) => a.name.localeCompare(b.name));
			}
			currentPage = 1; // 항상 첫 페이지로 리셋
			renderBooks(filteredBooks);
			updatePagination();
		}

		function searchBooks() {
			let searchTerm = document.getElementById('searchInput').value.toLowerCase();
			filteredBooks = bookData.filter(book => book.name.toLowerCase().includes(searchTerm));
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
		    let inputNickName = document.getElementById("nickName");

		    inputNickName.addEventListener("input", function() {
		        let value = inputNickName.value;
		        let isValidLength = nickNameLength(value);
		        let isValidNickName = checkNickName(value);

		        // 이전에 추가된 모든 에러 메시지 삭제
		        removeErrorMessages();

		        // 글자수와 닉네임 형식이 모두 유효하지 않은 경우 메시지 표시
		        if (!isValidLength || !isValidNickName) {
		            showErrorMessage("닉네임은 4~10 글자로 한글, 영어소문자, 숫자만 입력 가능합니다.");
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

		        let errorContainer = document.querySelector(".userInfoNcikName.title");
		        errorContainer.appendChild(errorMessage);
		    }

		    function removeErrorMessages() {
		        let errorMessages = document.querySelectorAll("#nickNameErrorMessage");
		        errorMessages.forEach(errorMessage => errorMessage.remove());
		    }
		});

		//이메일
		document.addEventListener("DOMContentLoaded", function() {
		    let inputEmail = document.getElementById("userEmailInput");

		    function showErrorMessage(message) {
		        let errorMessage = document.createElement("span");
		        errorMessage.textContent = message;
		        errorMessage.style.color = "red";
		        errorMessage.style.fontSize = "0.8em";
		        errorMessage.id = "emailErrorMessage";

		        let errorContainer = document.querySelector(".userInfoEmail");
		        errorContainer.appendChild(errorMessage);
		    }

		    function removeErrorMessages() {
		        let errorMessages = document.querySelectorAll("#emailErrorMessage");
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
		
		

</script>
</body>
</html>