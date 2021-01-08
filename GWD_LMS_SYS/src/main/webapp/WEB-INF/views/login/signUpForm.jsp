<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>
<script type="text/javascript">
//이거 왜안되는거징..
$("#id-input").on("propertychange change keyup paste input", function(){
	alert(" 작동?")
	val = $('#id-input').val();
	$.ajax({
		type : "get",
		url : "./idDuplChk.do",
		data : 	"id="+val
		,
		success : function(msg){
			$("#idDuplChker").text(msg)
			
//				alert("idDuplChk Success , msg : "+msg);
		},
		error : function(){
	        alert("idDuplChk Ajax_Jquery Error Occupied");
		}
	});
});
$("#password-input").on("propertychange change keyup paste input", function(){
	alert(" 작동?")
});
</script>
<body>
<form:form autocomplete="off" id='formID' action="./signUpStudent.do" method="post" onsubmit="return checkVal()">
<input type ='hidden' value ='' name ='type'id='returntype'>
<table style="width:80%; text-align: center" >
	<thead>
	<tr>
		<th colspan="3" align="center">
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
		<th id="submitbtns" style="display: none; text-align: center" colspan="3">
			<input  type ="submit" value ="회원가입">
			<input type ="reset" value ="초기화">
			
		</th>
	</tr>

	</tfoot>
</table>
</form:form>
</body>

<script type="text/javascript">
	function createTable(val){
// 		alert(val);
		$("#submitcontent").html('');
		$("#submitbtns").css("display", "");
		
		var html = "";
		html += "<tr><th><label>아이디</label></th><th ><input autocomplete='off'  style='width :100%' type ='text' id='id-input' name ='id' placeholder ='아이디를 입력해주세요' onchange='idDuplChk(this.value)' ></th><td><label id = 'idDuplChker'></label></td></tr>"+
		"<tr><th><label id>비밀번호</label></th><th ><input autocomplete='off'  style='width :100%' id='password-input' type ='password' name='password' name = 'password' placeholder ='비밀번호를 입력해주세요' onchange='passwordregex(this.value)' ></th><td><label id ='passwordtext'></label></td></tr>"+
		"<tr><th><label>비밀번호 확인</label></th><th ><input autocomplete='off'  style='width : 100%' type ='password' id='passwordchk'  name='passwordchk' placeholder ='비밀번호 확인' onchange='passwordchker(this.value)' ></th><td><label id ='passwordchker'></label></td></tr>";
		$("#submitcontent").html(html);
		
		
// 		$("#submitcontent").append("<input type ='text' id='id' name ='id' placeholder ='아이디를 입력해주세요' >");
// 		$("#submitcontent").append("<input type ='password' name='password' name = 'password' placeholder ='비밀번호를 입력해주세요' >");
// 		$("#submitcontent").append("<input type ='password' id='passwordchk'  name='passwordchk' placeholder ='비밀번호 확인' >");
		
		switch (val) {
		case "S":
			html += "<tr><th><label>이름</label></th><th colspan='2' ><input autocomplete='off' style='width : 100%' type ='text' id='name'  name='name' placeholder ='이름을 입력하세요' ></th></tr>";
			html += "<tr><th rowspan=3 ><label>주소</label></th><td><input autocomplete='off' type='text' id='zipcode' readonly='readonly'></td><td><input type ='button' onclick ='openaddress()' value ='우편번호 찾기'> </td></tr>"
			html += "<tr><th colspan ='2'><input autocomplete='off' style='width :100%' type='text' id='addr1' name='addr1' required='required' ></th></tr>"
			html += "<tr><th><input autocomplete='off' style='width :100%' type='text' id='addr2' name='addr2' required='required'></th><th><input autocomplete='off' style='width :100%' type='text' id='extra' name='extra' required='required' readonly = 'readonly' '></th></tr>"
			html += "<tr><th><label>이메일</label></th><th><input autocomplete='off'  style='width : 100%' type ='text' id='email'  name='email' placeholder ='이메일 입력'></th><td><select name ='emailback' id ='back'> <option value ='@naver.com'>naver</option><option value ='@gmail.com'>gmail</option><option value ='@hanmail.net'>daum</option><option value =''>직접입력</option></select></td></tr>";
			html += "<tr><th><label>연락처</label></th><th colspan =2><input autocomplete='off' style='width : 100%' type ='text' id='phone'  name='phone' placeholder ='전화번호 입력'></th></tr>";
			$("#returntype").val('S')
			break;
		case "A":
			
			$("#returntype").val('A')
			break;
		case "C":
			html += "<tr><th rowspan=3 ><label>주소</label></th><td><input type='text' id='zipcode' readonly='readonly'></td><td><input type ='button' onclick ='openaddress()' value ='우편번호 찾기'> </td></tr>"
				html += "<tr><th colspan ='2'><input style='width :100%' type='text' id='addr1' name='addr1' required='required' ></th></tr>"
				html += "<tr><th><input style='width :100%' type='text' id='addr2' name='addr2' required='required'></th><th><input style='width :100%' type='text' id='extra' name='extra' required='required' readonly = 'readonly' '></th></tr>"
			$("#returntype").val('C')
			break;


		}
// 		alert($("#returntype").val())
		$("#submitcontent").html(html);

	}
	
	function checkVal(){
		switch ($("#returntype").val()) {
		case "S":
			$("#formID").attr("action", "./signUpStudent.do")
			
			return;
		case "A":
			$("#formID").attr("action", "./signUpAcademy.do")
			
			return;
		case "C":
			$("#formID").attr("action", "./signUpCenter.do")
			return;
		}
	}
</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function openaddress(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            var addr = '';
	            var extraAddr = '';
	            
	            if(data.userSelectedType ='R'){
	            	addr=data.roadAddress;
	            }else{
	            	addr = data.jibunAddress;
	            }
	            
	            if(data.userSelectedType === 'R'){
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraAddr !== ''){
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	                // 조합된 참고항목을 해당 필드에 넣는다.
	                document.getElementById("extra").value = extraAddr;
	            
	            } else {
	                document.getElementById("extra").value = '';
	            }
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('zipcode').value = data.zonecode;
	            document.getElementById("addr1").value = addr;
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById("addr2").focus();
	            
	        }
	    }).open();
	}
	
	function idDuplChk(val){
		alert(val);
		$.ajax({
			type : "get",
			url : "./idDuplChk.do",
			data : 	"id="+val
			,
			success : function(msg){
				$("#idDuplChker").text(msg)
				
//					alert("idDuplChk Success , msg : "+msg);
			},
			error : function(){
		        alert("idDuplChk Ajax_javascript Error Occupied");
			}
		});
	}
	
	function passwordchker(val){
		alert("val : "+val)
		if (val ==$("#password-input").val()){
			
			$("#passwordchker").css("color", "blue")
			$("#passwordchker").text("비밀번호가 일치합니다.")
		}else{
			$("#passwordchker").css("color", "red");
			$("#passwordchker").text("비밀번호가 일치하지 않습니다.")
			
		}
	}
	function passwordregex(val){
		//정규화 확인
// 		$(passwordchk)
	}
	
	
</script>
</html>