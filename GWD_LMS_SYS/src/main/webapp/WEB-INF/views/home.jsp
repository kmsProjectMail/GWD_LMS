<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Home</title>
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
<p><a href="<c:url value="/board/board.do" />">게시판으로</a></p>
 <p><a href="<c:url value="/board/bbb.do" />">알람</a></p>
</form:form>
</sec:authorize>

<h3>
    [<a href="<c:url value="/intro/introduction.do" />">소개 페이지</a>]
    [<a href="<c:url value="/admin/adminHome.do" />">관리자 홈</a>]
    [<a href="<c:url value="/test.do" />">기능 테스트 페이지</a>]

   
    [<a href="<c:url value="/calendar/calendar.do" />">면담 캘린더</a>]
    [<a href="<c:url value="/calendar/calendarList.do" />">면담 리스트</a>]
    [<a href="<c:url value="/calendar/calendarMy.do" />">일정 캘린더</a>]
    [<a href="<c:url value="/calendar/calendarMyList.do" />">일정 리스트</a>]

    [<a href="./chatMain.do">메신저 테스트</a>]
    [<a href="./hrdMain.do">조회 시스템</a>]

</h3>

처리해보자
</body>
</html>
