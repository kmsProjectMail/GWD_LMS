<%@page import="com.min.edu.dto.Board_Dto"%>
<%@page import="com.min.edu.dto.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@include file = "../header.jsp" %>
	
	<script type="text/javascript">
	 var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
	}); 
	</script>
	</head>
	
<%@include file = "../index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">
<%Paging p = (Paging) request.getAttribute("pages"); 
Board_Dto dto = (Board_Dto)request.getAttribute("dto");
%>
<div class="container">
	<table class = "table table-hover">
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
				<td><%=dto.getRegdate().toLocaleString()%></td>
				
			</tr>

		</tbody>
		<tfoot>
			<tr>
			
				<td colspan="4">
				<c:if test="${dto.userid eq logid || auth eq ROLE_ADMIN}">
				<input type="hidden" value="${dto.boardseq}"
					name="boardseq"> <input type="button" class="btn btn-default " value="수정"
					onclick="location.href='./modifyMove.do?boardseq=${dto.boardseq}'">
					<input type="button" class="btn btn-default " value="삭제"
					onclick="location.href='./delBoard.do?boardseq=${dto.boardseq}'">
					</c:if>
					<input type="button" value="돌아가기" class="btn btn-default "
					onclick="history.back(-1)"></td>
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
	<form:form name="readForm" role="form" method="post">
		<!-- 파일 다운로드 클릭시 동작하는 form -->
		<input type="hidden" id="fileNo" name="fileNo" value="">
		<!-- 파일 번호 를 담음 -->
	</form:form>

	<br>
	<br>


	<h2>댓글쓰기</h2>
	<form:form name="form">
		<input type="hidden" value="${dto.boardseq}" name="boardseq" >
		<table>
			<tr>
				<th>id</th>
				<th><input type="text"  class="form-control"  placeholder="id" name="userid" value="${logid}"></th>
			</tr>

			<tr>
				<th>내용</th>
				<th><textarea placeholder="내용을 입력하세요" class="form-control" cols="70" rows="4" name="content" ></textarea></th>
			</tr>
			<tr>
				<th colspan="2"><input type="button" class="btn btn-default " value="확인" onclick="inputReply(${dto.boardseq})"></th>
			</tr>
		</table>
	</form:form>


		
<form:form name="frm" >
	<table>
		<c:forEach items="${lists}" var="reply">
			<tr>
				<td colspan="3"><h3>${reply.userid}</h3></td>
			</tr>
			<tr>
				<td><textarea name="contents" class="form-control" cols="70" rows="4" id="contents">${reply.content}</textarea></td>
				<c:if test="${reply.userid eq logid  || auth eq ROLE_ADMIN}">
				<td><input type="button" class="btn btn-default " value="수정"
					onclick="modiReply(${reply.replyseq},${dto.boardseq})"></td>
				<%-- 				<td><input type="button" value="수정" onclick="modiReply(${reply.replyseq},${dto.boardseq})"></td> --%>
				<td><input type="button" class="btn btn-default " value="삭제"
					onclick="delReply(${reply.replyseq},${dto.boardseq} )"></td>
					</c:if>
			</tr>
			
			
			<tr>
				<td colspan="3">${reply.regdate}</td>
			</tr>
		</c:forEach>
	</table>
	</form:form>
 <!--댓글달기  -->
<%
				if (p.getStartPage() > 1) {
			%>
			<a href="./oneBoard.do?pages=1&boardseq=${dto.boardseq}">◁◁</a>
			<%
				}
				if (p.getStartPage() > 1) {
					if (p.getStartPage() - p.getCountPage() <= 0) {
			%>
			<a href="./oneBoard.do?pages=1&boardseq=${dto.boardseq}">&lt;</a>
			<%
				} else {
			%>
			<a href="./oneBoard.do?pages=<%=p.getStartPage() - p.getCountPage()%>&boardseq=${dto.boardseq}">&lt;</a>
			<!-- <누르면 x1 페이지로 가는 걸 구현 -->
			<%
				}
				}
			%>
			<!-- 페이지 번호 -->
			<%
				for (int i = p.getStartPage(); i <= p.getEndPage(); i++) {
				%>
			<a
				<%=(i == p.getPage()) ? "style='color: forestgreen; font-weight: bold;'" : ""%>
				href="./oneBoard.do?pages=<%=i%>&boardseq=${dto.boardseq}">&nbsp;&nbsp;&nbsp;<%=i%>&nbsp;&nbsp;&nbsp;
			</a>
			
			<%
			}
			%>

			<!-- 페이지 상황에 따른 표시 -->
			<%
				if (p.getPage() < p.getTotalPage()) {
					if (p.getStartPage() + p.getCountPage() > p.getTotalPage()) {
			%>
			<a href="./oneBoard.do?pages=<%=p.getTotalPage()%>&boardseq=${dto.boardseq}">&gt;</a>
			<%
				} else {
			%>
			<a href="./oneBoard.do?pages=<%=p.getStartPage() + p.getCountPage()%>&boardseq=${dto.boardseq}">&gt;</a>
			<%
				}
				}
				if (p.getEndPage() < p.getTotalPage()) {
			%>
			<a href="./oneBoard.do?pages=<%=p.getTotalPage()%>&boardseq=${dto.boardseq}">▶▶</a>
			<%
				}
			%>
	
	<script type="text/javascript"  src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script type="text/javascript">
	 var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
		    xhr.setRequestHeader(header, token);
		});
	function fn_fileDown(fileNo) { // 파일 번호를 담아서 submit을 함
			var formObj = $("form[name='readForm']");
			$("#fileNo").attr("value", fileNo);
			formObj.attr("action", "./fileDown.do");
			formObj.submit();
		}
	
	 function modiReply(rseq, bseq) {
			var content = document.getElementById("contents").value
			alert(content)
			console.log(content,rseq, bseq)
			var frm =  document.frm
			frm.action="./modiReply.do?rseq="+rseq+"&contents="+content+"&bseq="+bseq
			frm.method = "post"
		 $.ajax({
			url:"./modiAjax.do?replyseq="+rseq+"&contents="+content+"&boardseq="+bseq,
			method : "post",
			success : function(msg){
				if(msg.isc == "true"){
					frm.submit();
				}else{
				}
			},
			error:function(request,status,error){
				 console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

			}
		}) 
		} 
		
		function delReply(rseq,bseq) {
			console.log(rseq,bseq)
			 location.href = "./delReply.do?replyseq="+rseq+"&boardseq="+bseq 
		}
			function inputReply(bseq){
			var userid = document.getElementsByName("userid")[0].value 
			var content = document.getElementsByName("content")[0].value 
			var form = document.form
			form.method = "post"
			form.action = "./inputReply.do?bseq="+bseq
			if(content == " " || content == ""){
				alert("내용을 작성해주세요")
			}else{
				$.ajax({
					data : "content="+content+"&userid="+userid+"&boardseq="+bseq,
					url : "./inputRAjax.do",
					method : "post",
					success : function(msg){
						if(msg.isc){
							form.submit(); 
						}else{
							alert("실패")
						}
					},
					error : function(request,status,error){
						 console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				})
			}
			}
	</script>
	</div>
</div>
</body>
</html>