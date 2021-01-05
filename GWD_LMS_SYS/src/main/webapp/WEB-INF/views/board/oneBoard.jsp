<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${list}
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>title</th>
				<th>content</th>
				<th>regdate</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${dto.userid}</td>
				<td>${dto.title}</td>
				<td>${dto.content}</td>
				<td>${dto.regdate}</td>
			</tr>

		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<input type="hidden" value="${dto.boardseq}" name="boardseq"> 
				<input type="button" value="수정" onclick="location.href='./modifyMove.do?boardseq=${dto.boardseq}'">
				<input type="button" value="삭제" onclick="location.href='./delBoard.do?boardseq=${dto.boardseq}'">
				<%-- <c:choose>
				<c:when test="${empty list}">
				<input type="button" value="삭제" onclick="location.href='./delBoard.do?boardseq=${dto.boardseq}'">
				</c:when>
				<c:otherwise>
				<c:forEach items="${list}" var="rSeq">
				<input type="button" value="삭제" onclick="location.href='./delBoard.do?boardseq=${rSeq.boardseq}'">
				</c:forEach>
				</c:otherwise>
				</c:choose> --%>
				<input type="button" value="돌아가기" onclick="history.back(-1)">
				</td>
			</tr>
			<tr>

				<c:forEach var="file" items="${files}">
					<th class="subject">첨부파일:</th>
					<td colspan="2">
						<div>
							<a href="#" onclick="fn_fileDown('${file.f_seq}'); return false;">${file.origin_fname}</a>(${file.file_size}kb)<br>
						</div>
					</td>
				</c:forEach>
			</tr>

		</tfoot>
	</table>
	<form name="readForm" role="form" method="post">
		<!-- 파일 다운로드 클릭시 동작하는 form -->
		<input type="hidden" id="fileNo" name="fileNo" value="">
		<!-- 파일 번호 를 담음 -->
	</form>
	
	<br>
	<br>
	<!--댓글달기  -->
	<h2>댓글쓰기</h2>
	<form action="./inputReply.do" method="post">
		<input type="hidden" value="${dto.boardseq}" name="boardseq">
		<table>

			<tr>
				<th>id</th>
				<th><input type="text" placeholder="id" name="userid"></th>
			</tr>

			<tr>
				<th>내용</th>
				<th><textarea placeholder="내용을 입력하세요" name="content"></textarea></th>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="확인"></th>
			</tr>
		</table>
	</form>

	<table>
		<c:forEach items="${list}" var="reply">
			<tr>
				<td colspan="3"><h3>${reply.userid}</h3></td>
			</tr>
			<tr>
				<td><textarea name="content" id="content">${reply.content}</textarea></td>
				<td><input type="button" value="수정" onclick="modiReply(${reply.replyseq},${dto.boardseq})"></td>
				<td><input type="button" value="삭제" onclick="delReply(${reply.replyseq},${dto.boardseq} )"></td>
			</tr>
			<tr>
				<td colspan="3">${reply.regdate}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<!--댓글달기  -->

	<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
	<script type="text/javascript">
		function fn_fileDown(fileNo) { // 파일 번호를 담아서 submit을 함
			var formObj = $("form[name='readForm']");
			$("#fileNo").attr("value", fileNo);
			formObj.attr("action", "./fileDown.do");
			formObj.submit();
		}
		/* 
		 function modiReply(seq) {
			$("#content").attr("readonly", false)
			var content = document.getElementById("content")
		$.ajax(function() {
			data : "content="+content+"&replyseq="+seq,
			type:"post",
			url:"./modiAjax.do",
			success:function(msg){
				if(msg.isc == "true"){
					alert("성공")
				}else{
					alert("실패")
				}
			},
			error:function(){
				alert("실패");
			}
		});
		} replyseq, content, boardseq */
		function modiReply(replyseq, boardseq){
		var content = document.getElementById("content").value
		  location.href = "./modiReply.do?replyseq="+replyseq+"&content="+content+"&boardseq="+boardseq 
		}
		
		function delReply(rseq,bseq) {
			console.log(rseq,bseq)
			 location.href = "./delReply.do?replyseq="+rseq+"&boardseq="+bseq 
		}
			/* $.ajax(function() {
				type:"POST",
				url : "./delajax.do?replyseq="+value,
				success:function(msg){
					if(msg.isc == "true"){
						alert("성공")
					}else{
						alert("실패")
					}
				},
				error:function(){
					
				}
			}); */
			
	</script>
</body>
</html>