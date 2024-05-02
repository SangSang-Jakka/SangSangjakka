<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="left-side-bar">
    <div class="brand-logo">
        <a href="/sangsangjakka/admin/dashboard.do">
            <span class="micon dw dw-open-book-1"> SangSang Jakka</span>
        </a>
    </div>
    <div class="menu-block customscroll">
        <div class="sidebar-menu">
            <ul id="accordion-menu">
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon dw dw-house-1"></span>
                        <span class="mtext">대시보드</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="/sangsangjakka/admin/dashboard.do">대시보드</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon dw dw-user"></span><span class="mtext">회원 관리</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="/sangsangjakka/admin/dashboard/user/stats.do">회원통계</a></li>
                        <li><a href="/sangsangjakka/admin/dashboard/user/manage.do">회원관리</a></li>
                        <li><a href="#">차단회원 관리</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon dw dw-library"></span><span class="mtext">동화책 관리</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="/sangsangjakka/admin/dashboard/book/stats.do">동화책 통계</a></li>
                        <!-- <li><a href="#">동화책 관리</a></li> -->
                        <!-- <li><a href="#">동화책 수상관리</a></li> -->
                        <li><a href="/sangsangjakka/admin/dashboard/book/report.do">신고동화책 관리</a></li>
                        <li><a href="/sangsangjakka/admin/dashboard/book/capacity.do">개인 저장소 기본 용량</a></li>
                        <!-- <li><a href="#">개인 저장소 기본 용량</a></li> -->
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon dw dw-clipboard1"></span><span class="mtext">게시판 관리</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="/sangsangjakka/admin/dashboard/board/stats.do">게시판 통계</a></li>
                        <li><a href="/sangsangjakka/admin/dashboard/notice/manage.do">공지사항</a></li>
                        <li><a href="/sangsangjakka/admin/dashboard/suggestion/manage.do">건의사항</a></li>
                        <li><a href="/sangsangjakka/admin/dashboard/freeboard/manage.do">자유게시판</a></li>
                        <li><a href="#">동화 공유 게시판</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon dw dw-support-1"></span><span class="mtext">관리자 관리</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="#">관리자 계정 목록</a></li>
                        <li><a href="#">관리자 계정 생성</a></li>
                        <li><a href="#">관리자 계정 정지</a></li>
                        <li><a href="#">로그</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</div>