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
	.backColor{
		background-color: #394759;
		color: white;
		width: 160px;
	}
	.wrap {
		text-align: center;
		margin: -20px auto;
	}
	
	.wrap a {
		-webkit-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
		-moz-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
		-ms-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
		-o-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
		transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
		display: block;
		margin: 20px auto;
		max-width: 180px;
		text-decoration: none;
		border-radius: 4px;
		padding: 10px 30px;
	}
	
	.wrap a.button {
		color: rgba(30, 22, 54, 0.6);
		box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset;
	}
	
	.wrap a.button:hover {
		color: rgba(255, 255, 255, 0.85);
		box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset;
		text-decoration: none;
	}
</style>
	 


  
</head>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.min.js"></script>


<body>
	<%@include file="../index.jsp" %>
	
	<div id ="container">
		<div id = "viewheader">
			<img alt="bar" src="./images/hrd/bar.png"><h3>상세보기</h3>
		</div>
		<table class="table table-bordered" style="width: 80%;margin: 0px 10%; border-radius: 1em; border : 1px solid black; border-spacing: 0;">
			<tbody>
				<tr>
					<th class="backColor">문서 번호</th>
					<td id="seq" colspan="3">G0000${authorization.authorization_seq}</td>
				</tr>
				<tr>
					<th class="backColor">기안자</th>
					<td>${authorization.id}</td>
					<th class="backColor">기안일</th>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${authorization.regdate}" /></td>
				</tr>
				<tr>
					<th class="backColor">제목</th>
					<td colspan="3">${authorization.title}</td>
				</tr>
				<tr>
					<th class="backColor">결재선(결재 인원)</th>
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
					<th class="backColor">내용</th>
					<td colspan="3">
						<div class="wrap">
  							<a class="button" onclick="contentConfirm()">문서확인</a>
<!-- 					<input class="btn btn-success"  type="button" value="문서확인" onclick='contentConfirm()'> -->
						</div>
					<br>
					* 승인은 문서 확인에서 처리 가능 합니다.
					</td>
				</tr>
				<c:if test="${authorization.fileflag eq 'Y'}">
					<c:forEach var="file" items="${fileList}">
					<tr>
						<th class="subject backColor">첨부파일:</th>
						<td colspan="3">
							<div><a href="#" onclick="fn_fileDown('${file.file_seq}'); return false;">${file.file_origin_fname}</a>(${file.file_size}kb)<br></div>
						</td>
					</tr>
					</c:forEach>
				</c:if>
				<c:if test="${authorization.complain != null}">
					<tr>
						<th class="backColor">반려내용</th>
						<td colspan="3">${authorization.complain}</td>
					</tr>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td id="btnCtr" colspan="4">
						<c:if test="${authorization.group_status.authorized_status eq '대기'}">
							<input class="btn btn-danger"  type="button" value="반려" onclick="documentCompanion()">
						</c:if>
						<input class="btn btn-warning" type="button" value="취소" onclick="history.back(-1)">
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
	
	// 창 오픈 여부
	var windowStatus = 0;
	
	function contentConfirm() {
		var title = "문서 확인";
		var url = "./documentConfirm.do?seq=${authorization.authorization_seq}";
		var attr = "width=900px, height=915px";
		g_oWindow =	window.open(url,title,attr);
		windowStatus=1;
	    g_oInterval = window.setInterval(function() {
	        try {
	            // 창이 꺼졌는지 판단
	            if( g_oWindow == null || g_oWindow.closed ) {
	                window.clearInterval(g_oInterval);
	                g_oWindow = null;
	                // Todo....
					if(windowStatus==1) {
	                //.....
	                	windowStatus=0;
	                	if(document.getElementsByName('compainonForm')[0]) {
	                		returnBtn();
							console.log('14');
	                	} else {
			                var btn = document.getElementById('btnCtr');
							btn.innerHTML = '<input type="button" value="반려" onclick="documentCompanion()"><input type="button" value="취소" onclick="history.back(-1)">';
							console.log('13');
	                	}
					}
	            }

	        } catch (e) { }

	    }, 500);
	}
	
	var seq;
	$(function() {
		seq = $("#seq").text();
		seq = seq.replace("G0000","");
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
	
	
	function companionSubmit() {
		if(confirm("반려시 문서처리가 완료됩니다.")) {
			var form = document.getElementsByName('compainonForm')[0];
			form.submit();
		}
	}
	
	// 반려 클릭후 취소 클릭시 원래 상태로 복원
	function returnBtn() {
		var html = '<input type="button" value="반려" onclick="documentCompanion()">'
		+ '<input type="button" value="취소" onclick="history.back(-1)">';
		$("#btnCtr").html(html);
		$("form[name=compainonForm]").parent().parent().remove();
	}
</script>
</html>