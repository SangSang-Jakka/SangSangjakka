<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

	<div class="myMainContainer">

		<div class="articleTitle">
			<h1>마이페이지</h1>
		</div>
		<div class="tab tabFormWrap">
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
									<div class="emailWrap">
										<input type="text" class="userEmail w70pc" required />
									</div>
								</div>
								<div class="userInfoNcikName title">
									<span>닉네임</span>
									<div class="changableWrap">
										<div class="changableInput inputBorder">
											<input type="text" id="nickName" required />
										</div>
										<div class="changebtnbox change">
											<input type="button" value="변경" id="nickNameChangeBtn" class="pointer">
										</div>
									</div>
								</div>

								<div class="userInfoId title">
									<span>아이디</span>
									<div class="idWrap">
										<div class="idInput inputBorder w70pc">
											<input type="text" id="id" required />
										</div>
									</div>
								</div>


								<div class="userInfoPw title">
									<span>비밀번호</span>
									<div class="changableWrap">
										<div class="changableInput inputBorder">
											<input type="password" id="passwordInput" required />
										</div>
										<div class="changebtnbox change">
											<input type="button" value="변경" id="passwordCheck" class="pointer">
										</div>
									</div>
								</div>

								<div class="userInfoName title">
									<span>이름</span>
									<div class="nameWrap">
										<div class="nameInput inputBorder w70pc">
											<input id="nameInput" type="text" />
										</div>
									</div>
								</div>


								<div class="userInfoSsn title">
									<span>주민등록번호</span>
									<div class="ssnWrap">
										<div class="ssnInput">
											<input type="text" maxlength="6" class="leftSsn inputBorder"
												oninput="formatSSN(this)" required />
											<span>-</span>
											<input type="password" maxlength="7" class="rightSsn" required />
										</div>
									</div>
								</div>

								<div class="userInfoTel title">
									<span>연락처</span>
									<div class="telWrap">
										<div class="telInput">
											<input type="text" id="phoneStart" class="phoneInput" maxlength="3"
												required>
											<span>-</span>
											<input type="text" id="phoneInput1" class="phoneInput" maxlength="4"
												required>
											<span>-</span>
											<input type="text" id="phoneInput2" class="phoneInput" maxlength="4"
												required>
										</div>
										<div class="changebtnbox change">
											<input type="button" value="변경" id="telChangebtn" class="pointer">
										</div>
									</div>
								</div>

								<div class="userInfo-address title">
									<span>주소</span>
									<div class="addressWrap">
										<div class="addressInput">
											<div class="addressFind">
												<div class="inputBorder postNum">
													<input type="text" id="sample6_postcode" placeholder="우편번호"
														required>
												</div>
												<div class="changebtnbox change">
													<input type="button" value="우편번호 찾기" id="addressFind"
														class="pointer" onclick="sample6_execDaumPostcode()">
												</div>
											</div>
											<div class="inputBorder">
												<input type="text" id="sample6_address" placeholder="주소" required>
											</div>
											<div class="inputBorder">
												<input type="text" id="sample6_detailAddress" placeholder="상세주소"
													required>
											</div>
											<div class="inputBorder">
												<input type="text" id="sample6_extraAddress" placeholder="참고항목"
													required>
											</div>
										</div>
									</div>
								</div>

								<div class="userInfo-childrenAge title">
									<span>자녀 연령대</span>
									<div class="childrenAgeWrap">
										<div>
											<input type="checkbox" id="age1" name="childAge" value="1세이상">
											<label for="age3">1세 이상</label>
										</div>
										<div>
											<input type="checkbox" id="age3" name="childAge" value="3세이상">
											<label for="age3">3세 이상</label>
										</div>
										<div>
											<input type="checkbox" id="age5" name="childAge" value="5세이상">
											<label for="age5">5세 이상</label>
										</div>
										<div>
											<input type="checkbox" id="age7" name="childAge" value="7세이상">
											<label for="age7">7세 이상</label>
										</div>
										<div>
											<input type="checkbox" id="age13" name="childAge" value="13세이상">
											<label for="age13">10세 이상</label>
										</div>

									</div>
								</div>


								<div class="userInfo-inFlow title">
									<span>유입경로</span>
									<div class="inFlowWrap">
										<div>
											<input type="checkbox" id="internetSearch" name="inflow" value="인터넷 검색">
											<label for="internetSearch">인터넷 검색</label>
										</div>
										<div>
											<input type="checkbox" id="friendReferral" name="inflow" value="지인 소개">
											<label for="friendReferral">지인 소개</label>
										</div>
										<div>
											<input type="checkbox" id="cafe" name="inflow" value="카페">
											<label for="cafe">카페</label>
										</div>
										<div>
											<input type="checkbox" id="blog" name="inflow" value="블로그">
											<label for="blog">블로그</label>
										</div>
										<div>
											<input type="checkbox" id="socialMedia" name="inflow" value="소셜 미디어 플랫폼">
											<label for="socialMedia">소셜 미디어 플랫폼</label>
										</div>
										<div>
											<input type="checkbox" id="advertisement" name="inflow" value="광고지">
											<label for="advertisement">광고지</label>
										</div>
										<div>
											<input type="checkbox" id="other" name="inflow" value="기타">
											<label for="other">기타</label>
											<input type="text" id="otherInput" placeholder="기타 유입경로 입력"
												style="display: none;">
										</div>
									</div>
								</div>
							</div>
							<div class="agree-check">
								<input type="checkbox" /> <a href="#!">이용약관</a>과 <a href="#!">개인정보 정책</a>에
								</br>따른 수집 및 이용, 마케팅 활용
								선택에 모두 동의합니다.
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
				        <div class="searchWrapper">
				            <input type="text" id="searchBox" placeholder="도서명">
				            <div class="searchIcon">
				                <i class="fa-solid fa-magnifying-glass"></i>
				            </div>
				        </div>
				        <div class="sortingWrapper">
				            <select id="sortOptions">
				                <option value="newest">최신순</option>
				                <option value="oldest">오래된순</option>
				                <option value="name">이름순</option>
				            </select>
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
							tab5 content
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
		    const searchTerm = document.getElementById('searchBox').value.toLowerCase();
		    filteredBooks = bookData.filter(book => book.name.toLowerCase().includes(searchTerm));
		    totalPages = Math.ceil(filteredBooks.length / itemsPerPage);
		    currentPage = Math.max(1, currentPage); // 검색 결과가 없어도 currentPage는 최소값 1
		    renderBooks(filteredBooks);
		    updatePagination();
		    updatePaginationControls();
		}

		function updatePagination() {
		    const pageList = document.getElementById('pageList');
		    pageList.innerHTML = '';

		    let startPage = Math.max(1, currentPage - 2);
		    let endPage = Math.min(startPage + 4, totalPages);
			if (startPage == endPage) {endPage++;}
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
</script>
</body>
</html>