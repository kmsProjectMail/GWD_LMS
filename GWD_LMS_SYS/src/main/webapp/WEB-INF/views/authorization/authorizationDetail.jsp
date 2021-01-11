<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../header.jsp" %>
</head>
<body>
	<%@include file="../index.jsp" %>
	
	<div id ="container">
		<table border="1">
			<tbody>
				<tr>
					<th>문서 번호</th>
					<td id="seq">${authorization.authorization_seq}</td>
				</tr>
				<tr>
					<th>기안자</th>
					<td>${authorization.id}</td>
					<th>기안일</th>
					<td>${authorization.regdate}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${authorization.title}</td>
				</tr>
				<tr>
					<th>결재선(결재 인원)</th>
					<td>
						<c:forEach items="${groupList}" var="g">
							${g} -> 
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${authorization.content}</td>
				</tr>
				<c:if test="${authorization.fileflag eq 'Y'}">
					<c:forEach var="file" items="${fileList}">
					<tr>
						<th class="subject">첨부파일:</th>
						<td colspan="2">
							<div><a href="#" onclick="fn_fileDown('${file.file_seq}'); return false;">${file.file_origin_fname}</a>(${file.file_size}kb)<br></div>
						</td>
					</tr>
					</c:forEach>
				</c:if>
				<c:if test="${authorization.complain != null}">
					<tr>
						<th>반려내용</th>
						<td>${authorization.complain}</td>
					</tr>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td id="btnCtr" colspan="3">
						<c:if test="${authorization.group_status.authorized_status eq '대기'}">
						<input type="button" value="승인" onclick="documentApproved()">
						<input type="button" value="반려" onclick="documentCompanion()">
						</c:if>
						<input type="button" value="취소" onclick="history.back(-1)">
					</td>
				</tr>
			</tfoot>
		</table>
		<c:url value="/documentFileDownload.do" var="readForm"/>
		<form name="fileReadForm" action="${readForm}" role="form" method="post"> <!-- 파일 다운로드 클릭시 동작하는 form -->
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="hidden" id="fileNo" name="fileNo" value="">  <!-- 파일 번호 를 담음 -->
		</form>
	</div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="<c:url value='/resources/js/authorization.js'/>"></script>

<script type="text/javascript">

	var seq;
	$(function() {
		seq = $("#seq").text();
	})
	

	function documentCompanion() {
// 		alert('반려시 문서처리가 완료됩니다.');
		var html = '<tr><td><form name="compainonForm" action="./documentModify.do" method="post">'
		+ '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />'
		+ '<input type="hidden" name="seq" value='+seq+' />'
		+ '<textarea rows="5" cols="60" name="complain" id="complain"></textarea>'
		+ '</form></td></tr>';
		$("#container table tbody").append(html);
		$("#btnCtr").html('');
		html = '<input type="button" value="작성완료" onclick="companionSubmit()"><input type="button" value="취소" onclick="returnBtn()">';
		$("#btnCtr").html(html);
	}
	function documentApproved() {
		if(confirm("승인시 더이상 문서를 수정할 수 없습니다.")) {
			var html = '<tr><td><form name="approvedForm" action="./documentApproved.do" method="post">'
				+ '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />'
				+ '<input type="hidden" name="seq" value='+seq+' />'
				+ '</form></td></tr>';
				$("#container table tbody").append(html);
			var form = document.getElementsByName('approvedForm')[0];
			form.submit();
		}		
	}
	function companionSubmit() {
		if(confirm("반려시 문서처리가 완료됩니다.")) {
			var form = document.getElementsByName('compainonForm')[0];
			form.submit();
		}
	}
	function returnBtn() {
		var html = '<input type="button" value="승인" onclick="">'
		+ '<input type="button" value="반려" onclick="documentCompanion()">'
		+ '<input type="button" value="취소" onclick="history.back(-1)">';
		$("#btnCtr").html(html);
		$("#companion").parent().parent().remove();
	}
</script>
</html>