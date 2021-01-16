<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header_popup.jsp"%>

<body>
	<div class='popupheaderbar'>비밀번호 변경</div>
<% String id = (String)request.getParameter("id");%>
<table class ="table">
<input type="hidden" id = "id" value=<%=id%> >
	<thead>
		<tr>
			<td>
			<label for="pass" id ="pwlabel">비밀번호</label>
			<input type="password" id ="password" placeholder="비밀번호">
			</td>
		</tr>
		<tr>
			<td>
			<label for="pass" >비밀번호확인</label>
			<input type="password" id ="passwordchk" placeholder="비밀번호확인">
			</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th colspan="2" style="text-align: center;"><input class ='custombtn-long' type="button" value ="확인" onclick="chkPassword()"></th>
		</tr>
	</tbody>
</table>
</body>
<script src="/GWD_LMS_SYS/resources/js/header_popup.js"></script>

<script type="text/javascript">
	function chkPassword(){
		var password = $("#password").val()
		var passwordchk= $("#passwordchk").val()
		if(password == passwordchk && $("#pwlabel").text()=="올바른 입력" ){
			$.ajax({
				url : "./updatePassword.do",
				data :{
					id : $("#id").val(),
					password : $("#password").val()
				},
				success:function(msg){
					alert("비밀번호가 변경되었습니다.")
					window.close();
					
				},
				error : function(){
					alert("chkPassword has a problem")
				}
			
			})
			
		}else{
			alert("비밀번호가 일치하지 않습니다 확인해주십시오")
		}
		
	}

</script>
</html>