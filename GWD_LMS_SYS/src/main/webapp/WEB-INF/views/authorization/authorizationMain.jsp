<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="../home.jsp" %>
	
	<div id ="container">
		<input type="button" value="글작성" onclick='location.href="<c:url value='/documentWriteMove.do'/>"'>
	
		<div>미처리</div>
		<table>
			<thead>
			<tr>
				<th>문서번호</th>
				<th>제목</th>
				<th>기안자</th>
				<th>기안일</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${incomplete}" var="i">
					<tr>
						<td>${i.authorization_seq}</td>
						
						<td><a href='<c:url value="/documentDetail.do?seq=${i.authorization_seq}"/>'>${i.title}</a></td>
						<td>${i.id}</td>
						<td>${i.regdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br><br>
		<div>처리</div>
		<table>
			<thead>
			<tr>
				<th>문서번호</th>
				<th>제목</th>
				<th>기안자</th>
				<th>기안일</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${complete}" var="c">
					<tr>
						<td>${c.authorization_seq}</td>
						<td><a href='<c:url value="/documentDetail.do?seq=${c.authorization_seq}"/>'>${c.title}</a></td>
						<td>${c.id}</td>
						<td>${c.regdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>