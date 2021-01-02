<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>

<body>

<form action="./signUp.do">
<table style="width:100%">
	<thead>
	<tr>
		<th colspan="2" align="center">
			<input class="btns" type ="button" name ="signuptype" value ="학생" onclick="createTable('S')">
			<input class="btns" type ="button" name ="signuptype" value ="교육기관" onclick="createTable('A')">
			<input class="btns" type ="button" name ="signuptype" value ="고용센터" onclick="createTable('C')">
		</th>
	</tr>
	</thead>
	<tbody id ="submitcontent" >
		
	</tbody>
	<tfoot>
	<tr>
		<td id="submitbtns" style="display: none" colspan="2"align="center">
			<input  type ="submit" value ="회원가입">
			<input type ="reset" value ="초기화">
		</td>
	</tr>
	</tfoot>
</table>
</form>
</body>

<script type="text/javascript">
	function createTable(val){
// 		alert(val);
		$("#submitcontent").html('');
		$("#submitbtns").css("display", "block");
		
		var html = "";
		html += "<tr><td colspan ='2'><label>아이디</label><input type ='text' id='id' name ='id' placeholder ='아이디를 입력해주세요' ></td></tr>"+
		"<tr><td colspan ='2'><label>비밀번호</label><input type ='password' name='password' name = 'password' placeholder ='비밀번호를 입력해주세요' ></td></tr>"+
		"<tr><td colspan ='2'><label>비밀번호 확인</label><input type ='password' id='passwordchk'  name='passwordchk' placeholder ='비밀번호 확인' ></td></tr>";
		$("#submitcontent").html(html);
		
		
// 		$("#submitcontent").append("<input type ='text' id='id' name ='id' placeholder ='아이디를 입력해주세요' >");
// 		$("#submitcontent").append("<input type ='password' name='password' name = 'password' placeholder ='비밀번호를 입력해주세요' >");
// 		$("#submitcontent").append("<input type ='password' id='passwordchk'  name='passwordchk' placeholder ='비밀번호 확인' >");
		
		switch (val) {
		case "S":
			
			break;
		case "A":
			
			break;
		case "C":
			
			break;


		}
	}
</script>
</html>