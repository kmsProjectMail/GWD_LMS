<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file = "../header.jsp" %>
</head>
<%@include file = "../index.jsp" %>

<body onload="document.f.id.focus();">
<div class="maincontainer" style="margin-left: 220px;">

<h3>아이디와 비밀번호를 입력해주세요.</h3>


<c:url value="/login" var="loginUrl" />
<form name = "f" action="${loginUrl}" method ="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table class = 'table table-hover'>
	<tr class ='warning'>
		<th>아이디 </th>
		<td><input type ="text" id ="id" name ="id" placeholder ="아이디" required="required" ></td>
	</tr>
	<tr>
		<th>비밀번호 </th>
		<td><input type ="password" id = "password" name ="password"  placeholder ="비밀번호" required="required" ></td>	
	</tr>
	<tr>
		<td colspan="2" style="text-align: center">
			<input class ="btn btn-warning" type="submit" value ="로그인">
			<input class ="btn btn-warning" type="button" value ="회원가입" onclick="window.open('./accessterm.do', '회원가입페이지' , 'width=800, height=600')"> 
			<input class ="btn btn-warning" type="button" value ="아이디 찾기" onclick="window.open('./findIdForm.do','아이디 찾기','width=600,height=400')"> 
			<input class ="btn btn-warning" type="button" value ="비밀번호찾기" onclick="window.open('./findPwForm.do','아이디 찾기','width=600,height=400')"> 
		</td>
	</tr>
	<c:if test="${param.error != null}">
        <p>아이디와 비밀번호가 잘못되었습니다.</p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>로그아웃 하였습니다.</p>
    </c:if>
</table>
</form>
</div>
</body>
</html>
