<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@include file = "./header.jsp" %>
</head>
<%@include file = "./index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">
<h1>Home!</h1>

<sec:authorize access="isAnonymous()">
<p><a href="<c:url value="/login/loginForm.do" />">로그인</a></p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="로그아웃" />
    principal : <%= request.getUserPrincipal() %>
<hr>
principal.getName :  <%= request.getUserPrincipal().getName() %>
<hr>
<input type="button" value ="메시지 전송" onclick="msgSender('calendar')">
<input type="button" value ="메시지 전송" onclick="msgSender('chat')">

<p><a href="<c:url value="/board/bbb.do" />">알람</a></p>

</form:form>
</sec:authorize>


<div class ="home_main_search" id = "home_main_search">
	<input id  = "home_main_search_text" class = "home_main_search_text" type = "text" placeholder="무엇을 찾으세요?"> 
	<input id = "home_main_search_button" class = "home_main_search_button" type = "button" value ="검색" >
</div>

<div class ="home_main_good_Trpr" id ="home_main_good_Trpr">
	<ul class ="home_main_good_Trpr_ul" id ="home_main_good_Trpr_ul">
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="서울">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="경기">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="인천">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="충청">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="경상">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="전라">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="강원/제주">
		</li>
	</ul>
</div>

<div class ="home_main_Trpr_viewer">
	<div class="tabContents" style="margin-top: 40px; float: left;">
		<section role="tabpanel" id="section1" class="tabCont on">
			<ol >
				<li class ="home_main_Trpr_viewer_li" ><a>전체</a> </li>
				<li class ="home_main_Trpr_viewer_li" ><a>디자인</a></li>
				<li class ="home_main_Trpr_viewer_li" ><a>사출금형 </a></li>
				<li class ="home_main_Trpr_viewer_li" ><a>법무</a></li>
				<li class ="home_main_Trpr_viewer_li" ><a>건축설계·감리</a></li>
				<li class ="home_main_Trpr_viewer_li" ><a>기계설계</a></li>
				<li class ="home_main_Trpr_viewer_li" ><a>출판</a></li>
				<li class ="home_main_Trpr_viewer_li" ><a>정보기술전략·계획</a></li>
				<li class ="home_main_Trpr_viewer_li" ><a>정보기술개발</a></li>
				<li class ="home_main_Trpr_viewer_li" ><a>정보기술운영</a></li>
				<li class ="home_main_Trpr_viewer_li" ><a>귀금속·보석</a></li>
			</ol>
		</section>
	</div>
	
	<div style="float: right; text-align: center; width: 1050px;">
		<table class = "table">
			<thead>
				<tr >
					<th style="text-align: center;"> <h2>검색결과</h2></th>
				</tr>
			</thead>
			<tbody class ="home_main_Trpr_viewer_result">
				
			</tbody>
		</table>
	</div>
</div>

<!-- <table class="calendar-table"> -->
<!--     <tbody> -->
<!--         <tr> -->
<!--             <td><div id="calendar-date-ko"></div></td> -->
<!--         </tr> -->
<!--     </tbody> -->
<!-- </table> -->
</div>


</body>
<script type="text/javascript">
window.onload = function(){
	var DatePicker = tui.DatePicker;

	DatePicker.localeTexts['ko'] = {
	    titles: {
	        DD: ['일', '월', '화', '수', '목', '금', '토'],
	        D: ['일', '월', '화', '수', '목', '금', '토'],
	        MMM: ['1', '2', '3', '4', '5', '6',
	            '7', '8', '9', '10', '11', '12'],
	        MMMM: ['1월', '2월', '3월', '4월', '5월', '6월',
	            '7월', '8월', '9월', '10월', '11월', '12월']
	    },
	    titleFormat: 'yyyy년 MMM월',
	    todayFormat: '오늘 : yyyy. MMM. d (DD)'
	};
// 	var calDateOptional = DatePicker.createCalendar('#calendar-date-ko', {language: 'ko'});
}
function msgSender(val){
	alert(websocket)
	websocket.send("STUDENT11"+"*"+val+"*");
	
	
}
</script>

</html>



