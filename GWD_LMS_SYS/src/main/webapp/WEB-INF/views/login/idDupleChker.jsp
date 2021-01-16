<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header_popup.jsp"%>

<body>
	<div class='popupheaderbar'>아이디 중복검삭</div>

<table>
	<thead>
		<tr>
			<th>
				<label for="pass" id ="idlabel">아이디</label>
				<input type="text" id ="id" autocomplete="none" placeholder="아이디 입력">
			</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th colspan ="2">
				<input type ="button" value ="선택" onclick="usethisID()"> 
			</th>
		</tr>
	</tbody>
</table>
</body>
<script	type="text/javascript">
 function usethisID(){
	 if($("#idlabel").text() == "사용 가능한 아이디" ){
		 opener.document.getElementById("id-input").value = $("#id").val();
		 window.close();
	 }	 
 }
</script>
<script src="../resources/js/header_popup.js"></script>

</html>