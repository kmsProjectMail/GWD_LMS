<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file = "../header.jsp" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
	table {
		margin: 0px 20%;
		width: 60%;
	}
	th {
		width : 100px;
		text-align: center;
	}
	table input[type=text] {
		width: 100%;
	}
</style>
</head>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src = "<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
<body>
	<%@include file="../index.jsp" %>
	<div id ="container">
		<c:url value="/documentWrite.do?${_csrf.parameterName}=${_csrf.token}" var="write"/>
		<form name="f" action="${write}" method="post" enctype="multipart/form-data">
<%-- 			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
			<table border="1">
				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" title="제목입력" name="title" id="title" placeholder="제목 입력"></td>
					</tr>
					<tr>
						<th>결재선 지정</th>
						<td><div class="ui-widget">
								<label for="searchId">결재원 검색: </label>	
								<select id="status">
									<option>일반</option>
									<option>통보</option>
								</select>
								<input id="searchId">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" id="authorizationGroup"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea rows="5" cols="60" title="내용입력" name="content" id="content" placeholder="내용입력"></textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
		<!-- 				<input multiple="multiple" type="file" name="file" onchange="fileClick(this)"> 의 형태는 여러개의 파일을 한꺼번에 동시에 선택해서 보낼수 있음 -->
						<input type="file" name="file" onchange="fileClick(this)"><br></td> <!-- 단일 파일씩 입력이 가능 javascript로 파일 태그 계속 생성 -->
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="3">
							<input type="button" value="글작성" onclick="writeForm()">
							<input type="button" value="취소" onclick="history.back(-1)">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>



<script type="text/javascript">
	CKEDITOR.replace("content",{
		width:'100%'
		,height:'700px',
		filebrowserUploadUrl: './imageFileUpload.do'
	});// 이미지가 fileupload.do 컨트롤러부분을 타러감
	
	function writeForm() {	// 수정완료 버튼 클릭시 동작
		var form = document.getElementsByName("f")[0];
		var title = document.getElementById("title");
		if(title.value=="" ||CKEDITOR.instances.content.getData().length < 1) { 
			// description.text() 형식으로는 값이 입력되는지 검사할 수 없음 -> <ifram 같은 태그들이 들어가기 때문에 값이 들어간다고 판단을 해버리기때문에
			// 에디터 안에 들어있는 값을 조사해야됨.
			alert("필수 값을 입력해 주세요");
		} else {
			
			var html ='';
			for (var i = 0; i < gPerson.length; i++) {
				// 선택된 id와 이름들을 전부 form안에 생성 후 데이터 전송
				html+='<input name="gPersen" type="hidden" value="'+gPerson[i]+'">';
				html+='<input name="gStatus" type="hidden" value="'+gStatus[i]+'">';
			}
			$("#searchId").after(html);
			$("#authorizationGroup").remove();
			form.submit();
		}
	}
	
	function append(element) {
	    this.dataStore[this.listSize++] = element;
	}
	
	
	var gPerson = [];
	var gStatus = [];
	$(function() {
		
		$("#searchId").keyup(function(){
			var searchId = $("#searchId").val() ;
			
			$("#searchId").autocomplete({
				source: function(request, response) {
					$.ajax({
						url : './searchIdName.do',
						method : 'GET',
						data : {'search':searchId},
						success : function(data) {
							response(
								$.map(data,function(name,id) {
									// {STUDENT01:홍길동,CENTER01:이담당} 으로 존재시 홍길동 검샘시 STDUENT01(홍길동), 
									// CENT 검색시 CETNER01(이담당) 형태로 목록 출력
									return {
										label: id+"("+name+")",  // 목록에 출력할 값의 형태 ex) 아이디(이름)
										key:id // 검색 후의 아이디 삭제 또는 form 데이터의 값을 넘기기 위한 아이디 값 추출
									}
								}) 	
							);
							
						},
						error : function(){
							swal('에러','전송 에러','warning');
						}
					});
				},
				select : function(event, id) {    //아이템 선택시
	                console.log(id);
					// id.item 의 형태 : {label, key , value로 이루어져있음}
					var html="";
					// 선택된 id를 관리하기 위한 hiddin input 태그 생성
	                html += '<div class="ntp"><label title="결재원"><input name="gPersen" type="hidden" value="'+id.item.key+'">'
	                +id.item.label+'</label><input type="button" value="X" onclick="deleteA(this)"><div>';
	                gPerson.push(id.item.key);
	                gStatus.push($("#status").val() == '일반' ? '대기' : '통보');
	                $("#authorizationGroup").append(html);
	            },
	            focus : function(event, ui) {    //포커스 가면
	                return false;//한글 에러 잡기용도로 사용됨
	            },
	            minLength:1,
	            autoFocus:true
			});
			
		});
		
	});
	
	// 삭제 버튼 클릭시 
	function deleteA(btn) {
		var nav = $(btn);
		var index = gPerson.indexOf(nav.parent().children("label").children("input").val());
		gStatus.splice(index,1);
		gPerson.splice(index,1);
		nav.parent().remove();
	}
	
	
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

</html>