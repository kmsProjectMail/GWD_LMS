<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body><!-- action="./inputBoard.do" -->
	<form action="./inputBoard.do" method="post" name="frm" enctype="multipart/form-data">
		<table>
			<tr>
				<td><input type="text" name="userid" value="STUDENT02"></td>
				<td><input type="text" name="title" value="입력"></td>
				<td><input type="text" name="content" value="입력"></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
<!-- 				<input multiple="multiple" type="file" name="file" onchange="fileClick(this)"> 의 형태는 여러개의 파일을 한꺼번에 동시에 선택해서 보낼수 있음 -->
				<input type="file" name="file" onchange="fileClick(this)">
				<br>
				
				</td> <!-- 단일 파일씩 입력이 가능 javascript로 파일 태그 계속 생성 -->
			</tr>
			<tr>
				<td><input type="submit" value="확인">
				<input type="button" value="취소" onclick="history.back(-1)">
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
	<script type="text/javascript">
	/* function inputBoard(){
		var form = document.frm;
		var title = form.title;
		var content = form.content;
		if(title.value=='') {
			alert('제목을 입력해 주세요.');
			return false;
		} 
		else if(content.value==''){
			alert("내용을 입력해 주세요.");
			return false;
		}
		else {
			form.action="./inputBoard.do";
			form.method="post";
			form.submit();
		}
	} */
	
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
</body>
</html>