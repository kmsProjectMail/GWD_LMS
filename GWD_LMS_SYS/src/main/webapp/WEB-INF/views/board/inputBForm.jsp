<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file = "../header.jsp" %>
<script type="text/javascript">
	
	function fileClick(file){
		var doc = file;
		console.log("값 변경");
		if(file.value != '') {
			// 파일이 추가되면 다음 줄에 파일선택창 추가
			console.log('값이 변경됨여');
			$(doc).next().after('<input type="file" name="file" onchange="fileClick(this)"><br>');
		} else {
			// 파일이 지워지면 기존 파일선택창 삭제
			$(doc).next().remove();
			$(doc).remove();
		}
	}
	</script>
</head>
<%@include file = "../index.jsp" %>
<body><!-- action="./inputBoard.do" -->
<div class="maincontainer" style="margin-left: 220px;">
	<form action="./inputBoard.do?${_csrf.parameterName}=${_csrf.token}" method="post" name="frm" enctype="multipart/form-data">
		<table>
		<tr>
				<td>id</td>
				<td><input type="text" class="form-control" name="userid" value="${id}"></td>
				</tr>
				<tr>
				<td>title</td>
				<td><input type="text" class="form-control" name="title"></td>
				</tr>
				<tr>
				<td>content</td>
				<td><input type="text" class="form-control" name="content" ></td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td>
				<input type="file" name="file" onchange="fileClick(this)">
				<br>
				
				</td> 
			</tr>
			<tr>
				<td><input type="submit" value="확인">
				<input type="button" value="취소" onclick="history.back(-1)">
				</td>
			</tr>
		</table>
	</form>
	
</div>
</body>

</html>