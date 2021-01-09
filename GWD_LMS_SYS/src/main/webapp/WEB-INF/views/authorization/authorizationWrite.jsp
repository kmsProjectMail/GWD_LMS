<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
	<%@include file="../home.jsp" %>
	<div id ="container">
		<c:url value="/documentWrite.do?${_csrf.parameterName}=${_csrf.token}" var="write"/>
		<form name="f" action="${write}" method="post" enctype="multipart/form-data" onsubmit="return writeForm()">
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
								<label for="tags">결재원 검색: </label>	<input id="tags">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" id="authorizationGroup"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><input type="text" title="내용입력" name="content" id="content" placeholder="내용 입력"></td>
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
							<input type="submit" value="글작성">
							<input type="button" value="취소" onclick="history.back(-1)">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>


<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#tags").keyup(function(){
// 			console.log($("#tags").val().length);
// 			var name;
// 			var id = $("#tags").val() ;
// 			$.ajax({
// 				url : './searchIdName.do',
// 				method : 'GET',
// 				data : {'id':id,'name':name},
// 				success : function(data) {
// // 					alert(data.searchs);
// // 					console.log(data.searchs);
// // 					var availableTags =["Action","Apll","ABCDEF","AFAFA","AFIJQF"]
// 					var availableTags =data.searchIds;
// // 					var ava = data.searchNames;
// 					console.log(availableTags);
					
					
// 						$.map(data,function(ul,item) {
// 							console.log(item);
// 							console.log(ul);
// 							return {
// 								label: data.searchIds+"("+data.searchNames+")",
// 								value:item,
// 								test:item+"test"
// 							}
// 						}) 	
					
					
					
// 					$("#tags").autocomplete({
// 						source: availableTags,
// 						select : function(event, ui) {    //아이템 선택시
// 			                console.log(ui.item.value);
// 			                console.log(ui);
// 			            },
// 			            focus : function(event, ui) {    //포커스 가면
// 			                return false;//한글 에러 잡기용도로 사용됨
// 			            }
// 					});
// 				},
// 				error : function(){
// 					alert('에러');
// 				}
// 			});


//--------------------------------------
			console.log($("#tags").val().length);
			var name;
			var id = $("#tags").val() ;
			
			$("#tags").autocomplete({
				source: function(request, response) {
					$.ajax({
						url : './searchIdName.do',
						method : 'GET',
						data : {'id':id,'name':name},
						success : function(data) {
//		 					alert(data.searchs);
//		 					console.log(data.searchs);
//		 					var availableTags =["Action","Apll","ABCDEF","AFAFA","AFIJQF"]
							var availableTags =data;
//		 					var ava = data.searchNames;
							console.log(availableTags);
							
							response(
								$.map(data,function(ul,item) {
									console.log(item);
									console.log(ul);
									return {
										label: item+"("+ul+")",
										value: item+"("+ul+")",
										key:item
									}
								}) 	
							);
							
							
							
						},
						error : function(){
							alert('에러');
						}
					});
				},
				select : function(event, ui) {    //아이템 선택시
	                console.log(ui);
					var html="";
	                html += '<div class="ntp"><label title="결재원"><input name="gPersen" type="hidden" value="'+ui.item.key+'">'+ui.item.label+'</label><input type="button" value="X" onclick="deleteA(this)"><div>';
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
		nav.parent().remove();
	}
	
	function writeForm() {
		if($("#title").val()=='') {
			alert('글을 입력하슈');
			return false;
		}
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