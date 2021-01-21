<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.min.js"></script>
<body>
	<%@include file="../index.jsp" %>
	
	<div id ="container">
		<h2>상세보기</h2>
		<table border="1">
			<tbody>
				<tr>
					<th>문서 번호</th>
					<td id="seq" colspan="3">${authorization.authorization_seq}</td>
				</tr>
				<tr>
					<th>기안자</th>
					<td>${authorization.id}</td>
					<th>기안일</th>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${authorization.regdate}" /></td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3">${authorization.title}</td>
				</tr>
				<tr>
					<th>결재선(결재 인원)</th>
					<td colspan="3">
						<c:forEach begin="0" end="${fn:length(groupList)-1}" items="${groupList}"  var="g" varStatus="i" step="1">
							${g} 
							<c:if test="${i.index != fn:length(groupList)-1}">
								->							
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3"><input type="button" value="문서확인" onclick='contentConfirm()'>
						<div id="contentNone" style=" height: 1500px;">
<%-- 							<textarea rows="5" cols="60" title="내용입력" name="content" id="content" placeholder="내용입력">${authorization.content}</textarea> --%>
							${authorization.content}
						</div>
					</td>
				</tr>
				<c:if test="${authorization.fileflag eq 'Y'}">
					<c:forEach var="file" items="${fileList}">
					<tr>
						<th class="subject">첨부파일:</th>
						<td colspan="3">
							<div><a href="#" onclick="fn_fileDown('${file.file_seq}'); return false;">${file.file_origin_fname}</a>(${file.file_size}kb)<br></div>
						</td>
					</tr>
					</c:forEach>
				</c:if>
				<c:if test="${authorization.complain != null}">
					<tr>
						<th>반려내용</th>
						<td colspan="3">${authorization.complain}</td>
					</tr>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td id="btnCtr" colspan="4">
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
<script type="text/javascript" src = "<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
<script type="text/javascript">
	
// 	CKEDITOR.replace('content');
	
	function contentConfirm() {
		var title = "문서 확인";
		var url = "./documentConfirm.do?seq=${authorization.authorization_seq}";
		var attr = "width=500px, height=715px";
		window.open(url,title,attr);
	}
	
	var seq;
	$(function() {
		seq = $("#seq").text();
		
	})
	

	// 반려버튼 클릭시 textarea 생성 및 버튼 구조 변환
	function documentCompanion() {
		var html = '<tr><td colspan="4"><form name="compainonForm" action="./documentModify.do" method="post">'
		+ '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />'
		+ '<input type="hidden" name="seq" value='+seq+' />'
		+ '<textarea class="complainText" rows="5" cols="60" name="complain" id="complain"></textarea>'
		+ '</form></td></tr>';
		$("#container table tbody").append(html);
		$("#btnCtr").html('');
		html = '<input type="button" value="작성완료" onclick="companionSubmit()"><input type="button" value="취소" onclick="returnBtn()">';
		$("#btnCtr").html(html);
	}
	
	// 승인 버튼 클릭시 동작
	function documentApproved() {
		if(confirm("승인시 더이상 문서를 수정할 수 없습니다.")) {
			var html = '<tr><td><form id="approvedForm" action="./documentApproved.do" method="post">'
				+ '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />'
				+ '<input type="hidden" name="seq" value='+seq+' />'
				+ '</form></td></tr>';
				$("#container table tbody").append(html);
				$('#contentNone').attr("display","");
				console.log($('.cke_wysiwyg_frame').contents().find('body'));
				console.log($('body'));
				html2canvas(document.getElementById('contentNone')).then(function(canvas) {
					var imgData = canvas.toDataURL('image/png'); //Image 코드로 뽑아내기 // image 추가
					html ='<input name="last" type="hidden" value="'+imgData+'">';
					$("#approvedForm").append(html);
					alert($("#approvedForm").html());
					var form = document.getElementById('approvedForm');
					// 이미지 넣는 방법 잠깐 고려 해볼것
// 					var userId="${stamp.id}";
// 					var userStamp="${stamp.stamp_image_link}";
// 					var stamp_link="/GWD_LMS_SYS/images/stamp/"+userStamp.substring(userStamp.lastIndexOf('stamp')+5,userStamp.length);
// 					alert(stamp_link);
// 					$('.cke_wysiwyg_frame').contents().find("#stamp"+userId).attr('src','stamp_link');
					// 1) open을 열고 이미지 값 ajax로 수정후 open을 닫는 방법
					// 2) 
					
					form.submit();
				});
// 				alert("test+"+$("#approvedForm").html());
		}		
	}
	function companionSubmit() {
		if(confirm("반려시 문서처리가 완료됩니다.")) {
			var form = document.getElementsByName('compainonForm')[0];
			form.submit();
		}
	}
	
	// 반려 클릭후 취소 클릭시 원래 상태로 복원
	function returnBtn() {
		var html = '<input type="button" value="승인" onclick="">'
		+ '<input type="button" value="반려" onclick="documentCompanion()">'
		+ '<input type="button" value="취소" onclick="history.back(-1)">';
		$("#btnCtr").html(html);
		$("#companion").parent().parent().remove();
	}
</script>
</html>