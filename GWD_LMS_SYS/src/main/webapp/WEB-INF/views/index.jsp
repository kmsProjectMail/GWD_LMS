<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>Insert title here</title>
<style type="text/css">
	body{
		margin : 0px;
		padding : 0px;
/* 		width: 1920px; */
/* 		height: 1080px; */
	}

	/* 채팅창 */
	.chatForm{
		width: 0px;
		height: 300px;
/* 		border: 1px solid black; */
		position: fixed;
		top : 3%;
		z-index: 1;
		right: 0px;
		overflow-x: hidden;
		transition: 0.5s;
		padding-top: 60px;
	}
	
	/* 사이드 메뉴 처리 */
	#menu {
		width: 15%;
		height: 1366px;
		background-color : #2C3E50;
		position: fixed;
		top : 0px;
	}
	
	#menu {
		text-align: center;
	}
	
	#menu hr{
		border: 1px solid black;
	}
	
	#menu ul {
	  margin: 0;
	  padding: 0;
	  text-align: left;
	}

	#menu li {
	  list-style: none;
	  font-size: 18pt;
/* 	  margin-top:30px; */
	/*   padding: 15px 20px; */
	/*   cursor: pointer; */
	}
	
	#menu>li {
		height: 50px;
	}

	#menu a:hover {
/* 	  background-color: #90CAF9; */
	}

	#menu a.active {
	  background-color: #333;
	}

	#menu ul li ul {
	  display: none;
	}
	
	#menu ul li.submenu span.mainTitle {
		margin-left: 16px;
		font-size: 18pt;
	}
	
	#menu ul li.submenu a,#menu ul li.submenu span{
		display: inline-block;
	}
	
	#menu ul li.submenu ul.oneDepthMenu {
		background-color : #394759;
		font-size: 14pt;
	}
	
	#menu ul li.submenu ul.oneDepthMenu li.subtitle {
		text-align: center;
/* 		margin-left: 35%; */
	}
	
	#menu ul li.submenu ul.oneDepthMenu li.subtitle ul.twoDepthMenu {
		background-color : #485B73;
	}
	
	#menu ul li.submenu ul.oneDepthMenu li.subtitle ul.twoDepthMenu li a {
		text-decoration: none;
		color : black; 
		font-size: 12pt;
		margin-left: 35%;
	} 
	
	/* #menu ul li.submenu>ul  {
	    position: absolute;
	    border : 1px solid black;
	    left: 53px;
	    width: 200px;
	    height : 610px;
	    top: 0;
	    background: #FFF
	}
	
	#menu ul li.submenu:hover ul {
	  display: inline-block
	}
	
	#menu ul li.submenu ul li.mainTitle {
		margin-top : 5px;
		margin-left : 10px;
		margin-bottom : 50px;
	}
	
	#menu ul li.submenu ul li.mainTitle a.menuImage{
		display: inline-block;
	    width: 30px;
	    height: 30px;
		background-position: 0px 0px;
	    background-repeat: no-repeat;
 	    vertical-align: top; 
	    content: '';
	}
	
	#menu ul li.submenu ul li.mainTitle span.menuTitle {
		margin-left: 50px;
		font-size: 16pt;
	}
	
	#menu ul li.submenu ul li.subtitle {
		padding-left: 10px;
	}
	
	#menu ul li.submenu ul li.subtitle ul {
		padding-left: 50px;
	}
	
	#menu ul li.submenu:hover ul li.subtitle ul {
	  display: block
	}
	
	#menu ul li.submenu ul li.subtitle ul li a {
		text-decoration: none;
		color : black; 
	} */
	
	
	/* 상단 및 사이드 이미지 처리 */
	.homeImage, .myPage, .board, .eduSearch, .interview, .calendarMenu, .support, .approval, .menuImage, .chat, .myInfomation, .logout, .alarm, .oneDepth, .twoDepth{
		background-image: url( "images/test.png" );
		background-size: 50px 497px;
		background-repeat: no-repeat;
	}
	
	
	.homeImage, .myPage, .board, .eduSearch, .interview, .calendarMenu, .support, .approval, .oneDepth, .twoDepth{
	    display: inline-block;
	    width: 34px;
	    height: 34px;
	    margin-left : 10px;
	    background-position: 0px 0px;
	    background-repeat: no-repeat;
	    vertical-align: top;
	    content: '';
	}
	
	#menuTop{
		padding : 10px 0px 30px;
		text-align: center;
	}
	
	.myPage {
		background-position: 0px -64px;
	}
	
	.board{
		background-position: 0px -128px;
	}
	
	.eduSearch{
		background-position: 0px -192px;
	}
	
	.interview{
		background-position: 0px -256px;
	}
	
	.calendarMenu{
		background-position: 0px -320px;
	}
	
	.support{
		background-position: 0px -448px;
	}
	
	.oneDepth{
		width : 16px;
		height : 16px;
		background-position: 0px -128px;
	}
	
	.twoDepth{
		background-position: 0px -128px;
	}
	
	/* 상단 고정바 이미지 처리 */
	.chat, .myInfomation, .logout, .alarm{
		background-position: 0px -384px;
		width:30px;
		height: 30px;
	    background-position: 0px 0px;
	    background-repeat: no-repeat;
	    content: '';
	}
	.chat:hover{
		cursor: pointer;
	}
	
	.myInfomation {
		background-position: 0px -448px;
	}
	
	.logout{
		background-position: 0px -192px;
	}
	
	.alarm{
		background-position: 0px -128px;
	}
	
	
	
	/* 상단 고정바 */
	#header .navbar {
		background: #A5B7CA;
	  	margin: 0; 
		padding: 0px;  
		list-style: none;
		position: fixed;
		top:0px;
		left : 15%;
		width: 85%;
		height: 40px;
		text-align: right;
	}
	#header .navbar>li {
	  display: inline-block;
	  margin: 5px 10px;
	}
	#header .navbar>li>a {
  	  display: block; 
  	  vertical-align: middle;
/* 	  text-decoration: none; */
/* 	  padding: 10px 20px; */
/* 	  color: white; */
	}
	#header .navbar>li>a:hover {
	  background: #95A7BA;
	}
	
	
	#container {
		width: 78%;
		margin-top : 100px;
		margin-left: 19%;
		height: 800px;
	}
	
	#container #mainContent{
		width: 60%;
		height: 500px;
		display: inline-block;
	}
	#container #calendar{
		width: 36%;
		height: 500px;
		margin-left : 3%;
		display: inline-block;
	}
	#container #subContent{
		height: 260px;
		margin-top : 2%;
	
	}
</style>
</head>
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
	
</script>
<body>

	<div id="header">
		<ul class="navbar">
		  <li><a title="알람" href="#" class="alarm"></a></li>
		  <li><a title="로그아웃" href="#" class="logout"></a></li>
		  <li><a title="회원정보 수정" href="#" class="myInfomation"></a></li> 
		  <li><a title="채팅" id="chatBtn" class="chat"></a></li> 
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
	
</body>
</html>