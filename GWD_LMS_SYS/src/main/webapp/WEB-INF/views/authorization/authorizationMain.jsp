<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../header.jsp" %>
<style type="text/css">
	table,tr,td{
		border : 1px solid black;
	}
	table{
		width: 100%;
		height: 100px;
		text-align: center;
	}
	thead th{
		text-align: center;
		background-color: #394759;
		color: white;
	}

</style>
</head>
<body>
	<%@include file="../index.jsp" %>
	
	<div id ="container">
<%-- 		<input type="button" value="글작성" onclick='location.href="<c:url value='/documentWriteMove.do'/>"'> --%>
<!-- 		<input type="button" value="도장등록" onclick='openStamp()'> -->
		<div style="float: right;">
		<select>
			<option>아이디</option>
		</select>
		<input type="text">
		<input type="button" value="검색">
		</div>
		<br><br>
		<div>미처리 문서 목록</div>
		<table >
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
		<br><br><br><br>
		<div>처리 완료 목록</div>
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
<script type="text/javascript">
	function openStamp() {
		var title = "도장 등록";
		var url = "./stamp.do";
		var attr = "width=300px, height=500px";
		window.open(url,title,attr);
	}
</script>
</body>
</html>