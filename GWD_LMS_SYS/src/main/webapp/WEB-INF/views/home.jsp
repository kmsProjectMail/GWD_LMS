<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Home</title>
	<script src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.min.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/tui-date-picker.css">
</head>

<body>

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
 <p><a href="<c:url value="/board/bbb.do" />">알람</a></p>
</form:form>
</sec:authorize>

<h3>
    [<a href="<c:url value="/intro/introduction.do" />">소개 페이지</a>]
    [<a href="<c:url value="/admin/adminHome.do" />">관리자 홈</a>]
    [<a href="<c:url value="/test.do" />">기능 테스트 페이지</a>]
	[<a href="<c:url value="/board/board.do" />">게시판으로</a>}
   
    [<a href="<c:url value="/calendar/calendar.do" />">면담 캘린더</a>]
    [<a href="<c:url value="/calendar/calendarList.do" />">면담 리스트</a>]
    [<a href="<c:url value="/calendar/calendarMy.do" />">일정 캘린더</a>]
    [<a href="<c:url value="/calendar/calendarMyList.do" />">일정 리스트</a>]

    [<a href="./chatMain.do">메신저 테스트</a>]
    [<a href="./hrdMain.do">조회 시스템</a>]

</h3>

처리해보자

<table class="calendar-table">
    <tbody>
        <tr>
            <td><div id="calendar-date-ko"></div></td>
        </tr>
    </tbody>
</table>

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
	
	var calDateOptional = DatePicker.createCalendar('#calendar-date-ko', {language: 'ko'});
}
</script>
</html>
