<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="./css/home.css">

</head>
	<script src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.min.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/tui-date-picker.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#chatBtn").on("click", function() {
			$(".chatForm").css("width","300px");
		});
		$("#chatClose").on("click", function() {
			$(".chatForm").css("width","0px");
		});
		$(".submenu").on("click",function(e) {
			if(e.target != this) {
				return false;
			} else {
				$(this).children('.oneDepthMenu').slideToggle();
			}
		});
		$(".subtitle").on("click",function(e) {
			if(e.target != this) {
				return false;
			} else {
				$(this).children('.twoDepthMenu').slideToggle();
			}
		});
		$(".mainTitle").on("click",function(e) {
			if(e.target != this) {
				return false;
			} else {
				$(this).next('.oneDepthMenu').slideToggle();
			}
		});
		$(".mainImage").on("click",function(e) {
			if(e.target != this) {
				return false;
			} else {
				$(this).next().next('.oneDepthMenu').slideToggle();
			}
		});
	});
	
	function actionsubmit(val){
		location.href =val;
	}
</script>
<body>

	<div id="header">
		<ul class="navbar">
			<sec:authorize access="isAnonymous()">
		  <li><a title="회원정보 수정" href="<c:url value="/login/loginForm.do" />" class="myInfomation"><img alt="알람" src="./images/home_user.png"></a></li> 
		  </sec:authorize>
		  <sec:authorize access="isAuthenticated()">
		  <li><a title="알람" href="#" class="alarm"><img alt="알람" src="./images/home_alram.png"></a></li>
		  <li><a title="채팅" id="chatBtn" class="chat"><img alt="채팅" src="./images/home_chat.png"></a></li> 
		  <li>
		     <form:form action="${pageContext.request.contextPath}/logout" method="POST">
		  		<input type ="submit" class = "home_submitbtn"id = "home_submitbtn" value ="">
	  		</form:form>
			</li>
		  </sec:authorize>
		</ul>
	</div>
	
	
	<div id="menu">
		<div id="menuTop">
			<a href="#" class="homeImage"></a>
			<br>
			<br>
		</div>
		<span id="menuWord">GWD</span>
		<hr>
		<ul>
			<!-- 회원관리 -->
			<li class="submenu">
				<a title="내정보" href="#" class="mainImage myPage"></a><span class="mainTitle">회원관리</span>
				<ul class="oneDepthMenu">
			        <li class="subtitle">개인정보
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">내 정보 보기</a></li>
			        		<li><a href="#">회원 정보 변경</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">즐겨 찾기
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">즐겨 찾기</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">일정 보기
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">내 일정</a></li>
			        	</ul>
			        </li>
		      	</ul>	
			</li>
			<!-- 게시판 -->
			<li class="submenu">
				<a title="게시판" href="#" class="mainImage board"></a><span class="mainTitle">게시판</span>
				<ul class="oneDepthMenu">
			        <li class="subtitle">학생게시판
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">게시판 보기</a></li>
			        		<li><a href="#">내 활동</a></li>
			        	</ul>
			        </li>
		      	</ul>	
			</li>
			<!-- 조회 -->
			<li class="submenu">
				<a title="학원조회" href="#" class="mainImage eduSearch"></a><span class="mainTitle">조회</span>
				<ul class="oneDepthMenu">
			        <li class="subtitle">교육과정 조회
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">교육과정 조회</a></li>
			        		<li><a href="#">교육과정 검색</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">즐겨 찾기
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">내 교육과정</a></li>
			        		<li><a href="#">내 교육기관</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">교육기관
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">우리 학원 보기</a></li>
			        	</ul>
			        </li>
		      	</ul>	
			</li>
			<!-- 면담 -->
			<li class="submenu">
				<a title="면담" href="#" class="mainImage interview"></a><span class="mainTitle">면담</span>
				<ul class="oneDepthMenu">
			        <li class="subtitle">면담예약
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">면담예약</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">면담 조회
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">면담조회</a></li>
			        	</ul>
			        </li>
		      	</ul>	
			</li>
			<!-- 일정 -->
			<li class="submenu">
				<a title="일정" href="#" class="mainImage calendarMenu"></a><span class="mainTitle">일정</span>
				<ul class="oneDepthMenu">
			        <li class="subtitle">일정 관리
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">내 달력</a></li>
			        		<li><a href="#">일정 조회</a></li>
			        	</ul>
			        </li>
		      	</ul>	
			</li>
			<!-- 결재 -->
			<li class="submenu">
				<a title="결재" href="#" class="mainImage approval"></a><span class="mainTitle">결재</span>
				<ul class="oneDepthMenu">
			        <li class="subtitle">결재보관함
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">미처리 결재</a></li>
			        		<li><a href="#">완료 결재</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">결재 작성
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">결재 작성</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">결재 수단 등록
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">도장등록</a></li>
			        		<li><a href="#">서명등록</a></li>
			        	</ul>
			        </li>
		      	</ul>	
			</li>
			<!-- 지원센터 -->
			<li class="submenu">
				<a title="지원센터" href="#" class="mainImage support"></a><span class="mainTitle">지원센터</span>
				<ul class="oneDepthMenu">
			        <li class="subtitle">자주 묻는 질문
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">자주 묻는 질문</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">문의하기
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">문의하기</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">공지사항
			        	<ul class="twoDepthMenu">
			        		<li><a href="#">공지사항</a></li>
			        	</ul>
			        </li>
		      	</ul>	
			</li>
		</ul>
	</div>
	
	
	<div class="chatForm">
		<input type="button" id="chatClose" value="채팅 닫기">
	</div>
	
<!-- 	<div id="container"> -->
<!-- 		<div id="mainContent"> -->
<!-- 			d -->
<!-- 		</div> -->
<!-- 		<div id="calendar"> -->
<!-- 			d -->
<!-- 		</div> -->
<!-- 		<div id="subContent"> -->
<!-- 			d -->
<!-- 		</div> -->
<!-- 	</div> -->
	<div style="margin-left: 220px;">
