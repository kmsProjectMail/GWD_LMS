<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<section>
	<div>
		<h1>글 수정${dto.boardseq}</h1>
	</div>
<form enctype="multipart/form-data" name="frm" action="./modify.do?${_csrf.parameterName}=${_csrf.token}">
	<input type="hidden" name="boardseq" value="${dto.boardseq}"> 
	<input type="hidden" id="fileNoDel" name="fileNoDel" value=""> 
	<input type="hidden" id="fileNameDel" name="fileNameDel" value=""> 
	<table border="1">
		<thead>
			<tr>
				<th>제목</th>
				<td style="width: 700px;">
					<div><input type="text" style="width: 100%" name="title" value="${dto.title}"></div>
				</td>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td>
					<div>${dto.userid}</div>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<div style="width: 700px; height: 700px; text-align: left;">
						<textarea rows="5" cols="60" name="content" id="content">${dto.content}</textarea>
					</div>
				</td>
			</tr>
			
				<c:forEach var="file" items="${file}" varStatus="var"> <!-- 파일의 개수만큼 for문 반복 -->
						<tr>
						<th>첨부파일:</th>
						<td>
							<a href="#" id="fileName" onclick="return false;">${file.origin_fname}</a>(${file.file_size}kb)
							<button id="fileDel" onclick="fn_del('${file.f_seq}','fileNo_${var.index}');" type="button">삭제</button>
						</td>
					</tr>
				</c:forEach>
				
					<tr>
						<td colspan="2">
							<input type="file" name="file" onchange="fileClick(this)"><br>
						</td>
					</tr>
			
		</thead>
		<tfoot>
			<tr>
				<td colspan="2">
					<input type="button" value="수정완료" onclick="modifyComplete()">
					<input type="button" value="취소" onclick="history.back(-1)">
				</td>
			</tr>
		</tfoot>
	</table>
	
	</form>
</section>

<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<script type="text/javascript">

function modifyComplete() {	// 수정완료 버튼 클릭시 동작
	var form = document.frm;
	var title = document.getElementsByName("title")[0];
	var content = document.getElementsByName("content")[0];
	if(title.value=="" ||content.value=="" ) { 
		alert("필수 값을 입력해 주세요");
	} else {
		isShow=false;
		form.method="post";
		form.submit();
	}
}

	$(document).on("click","#fileDel", function(){ 
		$(this).parent().parent().remove();
		
	});
		var fileNoArry = new Array();
		var fileNameArry = new Array();
	function fn_del(value, name){
		fileNoArry.push(value);
		fileNameArry.push(name);
		$("#fileNoDel").attr("value", fileNoArry);
		$("#fileNameDel").attr("value", fileNameArry);
	}
	function fn_addFile() {
		$(document).on("click","#fileDelBtn", function(){
			$(this).parent().remove();
		});
	}
</script>
</body>
</html>