<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%Object auth = request.getAttribute("auth"); %>

	<div id="header">
		<ul class="navbar">
		<sec:authorize access="isAnonymous()">
		  <li><a title="회원정보 수정" href="<c:url value="/login/loginForm.do" />" class="myInfomation"><img alt="로그인" src="<c:url value="/images/home_user.png"/>"></a></li> 
		  </sec:authorize>
		  <sec:authorize access="isAuthenticated()">
		  <li>
		  	<a title="알람" href="#" class="alarm" style="position: relative;">
		  		<input type ="button" class = "calendar_alram_input" id ="calendar_alram_input" onclick="alarm_list_open()"> 
		  		<label id='calendar_alram' style="background-color: red; position: absolute; color: white; border-radius: 10px; font-size: 13px; width: 18px; text-align: center; top: -5px; left: 20px"></label>
		  	</a>
		  	<div  id ="alarmdiv" style="display:none; overflow:auto;position:absolute; right: 0px;  width : 300px; min-height: 0px; max-height: 400px; border: 1px solid black">
				<table class="table" id ="alarmtable">
				
				</table>
			</div>
	  	</li>
		  <li>
		  	<a title="채팅" id="chatBtn" class="chat" value="0" >
		  		<img id="chat_icon" alt="채팅" src = "<c:url value="/images/home_chat.png"/>">
	  		</a>
  		</li> 
		  <li>
		     <form:form action="${pageContext.request.contextPath}/logout" method="POST">
		  		<input type ="submit" class = "home_submitbtn"id = "home_submitbtn" value ="">
	  		</form:form>
			</li>
		  </sec:authorize>
		</ul>
	</div>
	
	<div id="index_menu">
		<div id="menuTop">
			<a href="#" class="homeImage"></a>
			<br>
			<br>
		</div>
		<a onclick ="location.href = '/GWD_LMS_SYS/home.do'"><span id="menuWord">GWD</span></a>
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
			        		<li><a onclick ="location.href = '/GWD_LMS_SYS/board/board.do'">게시판 보기</a></li>
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
			        		<li><a onclick ="location.href = '/GWD_LMS_SYS/hrdMain.do'">교육과정 조회</a></li>
			        		<li><a onclick ="location.href = '/GWD_LMS_SYS/hrdMain.do'">교육과정 검색</a></li>
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
			        		<li><a onclick ="location.href = '/GWD_LMS_SYS/calendar/calendar.do'">면담예약</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">면담 조회
			        	<ul class="twoDepthMenu">
			        		<li><a onclick ="location.href = '/GWD_LMS_SYS/calendar/calendarList.do'">면담조회</a></li>
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
			        		<li><a onclick ="location.href = '/GWD_LMS_SYS/calendar/calendarMy.do'">내 달력</a></li>
			        		<li><a onclick ="location.href = '/GWD_LMS_SYS/calendar/calendarMyList.do'">일정 조회</a></li>
			        	</ul>
			        </li>
		      	</ul>	
			</li>
			<!-- 결재 -->
			<li class="submenu">
				<a title="결재" href="<c:url value="/authorization/authorizationMain.do" />" class="mainImage approval"></a><span class="mainTitle">결재</span>
				<ul class="oneDepthMenu">
			        <li class="subtitle">결재보관함
			        	<ul class="twoDepthMenu">
			        		<li><a onclick="location.href = '/GWD_LMS_SYS/authorizationBranch.do?branch=inComplete'">미처리 결재</a></li>
			        		<li><a onclick="location.href = '/GWD_LMS_SYS/authorizationBranch.do?branch=complete'">완료 결재</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">결재 작성
			        	<ul class="twoDepthMenu">
			        		<li><a onclick="location.href = '/GWD_LMS_SYS/documentWriteMove.do'">결재 작성</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">결재 수단 등록
			        	<ul class="twoDepthMenu">
			        		<li><a onclick="openStamp()">도장등록</a></li>
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
			        		<li><a onclick ="location.href = '/GWD_LMS_SYS/board/boardJaju.do'">자주 묻는 질문</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">문의하기
			        	<ul class="twoDepthMenu">
			        		<li><a onclick ="location.href = '/GWD_LMS_SYS/board/boardMon.do'">문의하기</a></li>
			        	</ul>
			        </li>
			        <li class="subtitle">공지사항
			        	<ul class="twoDepthMenu">
			        		<li><a onclick ="location.href = '/GWD_LMS_SYS/board/boardgong.do'">공지사항</a></li>
			        	</ul>
			        </li>
		      	</ul>	
			</li>
		</ul>
	</div>
	
	
	<div class="chatForm">
		<div class="chatMain"></div>
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


<form name=exf1>
		<input type="text" name=ch size=2>
		<input type="text" name=cm size=2> 
		<input type="text" name=cs size=2> 
		<div class="addss"></div>
	</form>
