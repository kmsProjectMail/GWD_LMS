<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<body>
<div style="background-color: #2C3E50; width: 100%; height: 10%; color:snow; text-align: center" align="center" ><h2>회원가입 페이지</h2></div>
<form>
<input type ='hidden' value ='' name ='type'id='returntype'>
<table class ="table" style="width:80%; text-align: center; margin: auto"  >
	<thead>
	<tr>
		<th colspan="3" align="center">
			<input class ='btn-primary' type ="button" name ="signuptype" value ="학생" onclick="createTable('S')">
			<input class ='btn-primary' type ="button" name ="signuptype" value ="교육기관" onclick="createTable('A')">
			<input class ='btn-primary' type ="button" name ="signuptype" value ="고용센터" onclick="createTable('C')">
		</th>
	</tr>
	</thead>
	<tbody id ="submitcontent" >
		
	</tbody>
	<tfoot>
	<tr>
		<th id="submitbtns" style="display: none; text-align: center" colspan="3">
			<input class ='btn-primary'  type ="button" value ="회원가입" onclick="checkVal()">
			<input class ='btn-primary' type ="reset" value ="초기화">
			
		</th>
	</tr>

	</tfoot>
</table>
</form>
</body>
<script type="text/javascript">
	function createTable(val){
// 		alert(val);
		$("#submitcontent").html('');
		$("#submitbtns").css("display", "");
		
		var html = "";
		html += "<tr><th><label>아이디</label></th><th >"+
		"<input autocomplete='off'  style='width :100%' type ='text' id='id-input' name ='id' onclick='duplchk()' readonly='readonly' placeholder ='아이디를 입력해주세요' onchange='idDuplChk(this.value)' >"+
		"</th><td><input type ='button' value ='중복검사' onclick='duplchk()'></td></tr>"+
		"<tr><th>"+
		"<label id>비밀번호</label></th><th ><input autocomplete='off'  style='width :100%' id='password-input' type ='password' name='password' name = 'password' placeholder ='비밀번호를 입력해주세요' onchange='passwordregex(this.value)' >"+
		"</th><td><label id ='passwordregex'></label><input type ='hidden' id ='passwordhidden' value =''></td></tr>"+		
		"<tr><th>"+
		"<label>비밀번호 확인</label></th><th ><input autocomplete='off'  style='width : 100%' type ='password' id='passwordchk'  name='passwordchk' placeholder ='비밀번호 확인' onchange='passwordchker(this.value)' >"+
		"</th><td><label id ='passwordchker'></label><input type ='hidden' id ='passwordchkerhidden' value =''></td></tr>";
		$("#submitcontent").html(html);
		

// 		$("#submitcontent").append("<input type ='text' id='id' name ='id' placeholder ='아이디를 입력해주세요' >");
// 		$("#submitcontent").append("<input type ='password' name='password' name = 'password' placeholder ='비밀번호를 입력해주세요' >");
// 		$("#submitcontent").append("<input type ='password' id='passwordchk'  name='passwordchk' placeholder ='비밀번호 확인' >");
		
		switch (val) {
		case "S":
			html += "<tr><th><label>이름</label></th><th colspan='2' ><input autocomplete='off' style='width : 100%' type ='text' id='name'  name='name' placeholder ='이름을 입력하세요' ></th></tr>";
			html += "<tr><th rowspan=3 ><label>주소</label></th><td><input style='background-color:lightgrey; border : 1px solid gray;' autocomplete='off' type='text' id='zipcode' readonly='readonly'></td><td><input type ='button' onclick ='openaddress()' value ='우편번호 찾기'> </td></tr>"
			html += "<tr><th colspan ='2'><input  style='background-color:lightgrey; border : 1px solid gray;width :100%'  readonly = 'readonly' autocomplete='off' type='text' id='addr1' name='addr1' required='required' ></th></tr>"
			html += "<tr><th><input   autocomplete='off' style='width :100%; ' type='text' id='addr2' name='addr2' required='required'></th><th><input autocomplete='off' style='background-color:lightgrey; border : 1px solid gray;width :100%; readonly=readonly' type='text' id='extra' name='extra' required='required' readonly = 'readonly' '></th></tr>"
			html += "<tr><th><label>이메일</label></th><th><input autocomplete='off'  style='width : 100%' type ='text' id='email'  name='email' placeholder ='이메일 입력'></th><td><select onchange='emailselect(this.value)' name ='emailback' id ='emailselector' class='emailselector'><option value =''>직접입력</option> <option value ='@naver.com'>naver</option><option value ='@gmail.com'>gmail</option><option value ='@hanmail.net'>daum</option></select></td></tr>";
			html += "<tr><th><label>연락처</label></th>"+
			"<th><input autocomplete='off' style='width : 80%' type ='text' id='phone'  name='phone' readonly='readonly' placeholder ='전화번호를 입력해주세요'  onclick ='openindentify()'></th>"+
			"<th><input type='button' style='width:100%' class ='btn-primary' value='인증하기' onclick ='openindentify()' ><label id ='phonechecker_label'></label><input type ='hidden' id ='phoneidentify' value =''></th></tr>"
			
			$("#returntype").val('S')
			break;
		case "A":
			html += "<tr><th><label>학원코드</label></th><th><input readonly=readonly autocomplete='off' style='width : 80%' type ='text' id='trainst_cst_id'  name='trainst_cst_id' placeholder ='학원코드를 입력하세요' onclick='searchTrainst()' ></label><input type ='hidden' id ='trainst_cst_idhidden' value =''></th>"+
			"<td><label id ='trainst_cst_id_checker'></label><input style='width:100%' class ='btn-primary' type ='button' value ='조회' onclick='searchTrainst()'></td></tr>";
			html += "<tr><th><label>이름</label></th><th colspan='2' ><input autocomplete='off' style='width : 100%' type ='text' id='trprchap'  name='trprchap' placeholder ='이름을 입력하세요' ></th></tr>";
			html += "<tr><th><label>이메일</label></th><th><input autocomplete='off'  style='width : 100%' type ='text' id='trprchapemail'  name='trprchapemail' placeholder ='이메일 입력'></th><td><select onchange='emailselect(this.value)' name ='emailback' id ='emailselector' class='emailselector'><option value =''>직접입력</option> <option value ='@naver.com'>naver</option><option value ='@gmail.com'>gmail</option><option value ='@hanmail.net'>daum</option></select></td></tr>";
			html += "<tr><th><label>연락처</label></th><th><input autocomplete='off' style='width : 100%' type ='text' id='trprchaptel'  name='trprchaptel' placeholder ='전화번호를 입력해주세요'  ><input type ='hidden' id ='phoneidentify' value ='true'></th></tr>"

			$("#returntype").val('A')
			break;
		case "C":
			html += "<tr><th rowspan=3 ><label>주소</label></th><td><input type='text' id='zipcode' readonly='readonly'></td><td><input class ='btn-primary' type ='button' onclick ='openaddress()' value ='우편번호 찾기'> </td></tr>"
				html += "<tr><th colspan ='2'><input style='width :100%' type='text' id='addr1' name='addr1' required='required' ></th></tr>"
				html += "<tr><th><input style='width :100%' type='text' id='addr2' name='addr2' required='required'></th><th><input style='width :100%' type='text' id='extra' name='extra' required='required' readonly = 'readonly' '></th></tr>"
				html += "<tr><th><label>센터 이름</label></th><th colspan='2' ><input autocomplete='off' style='width : 100%' type ='text' id='name'  name='name' placeholder ='이름을 입력하세요' ></th></tr>";
				html += "<tr><th><label>센터 번호</label></th>"+
				"<th><input autocomplete='off' style='width : 80%' type ='text' id='phone' class='cen_phone' name='cen_phone' readonly='readonly' placeholder ='전화번호를 입력해주세요'  onclick ='openindentify()'></th>"+
				"<th><input type='button' style='width:100%' class ='btn-primary' value='인증하기' onclick ='openindentify()' ><label id ='phonechecker_label'></label><input type ='hidden' id ='phoneidentify' value =''></th></tr>"
				
			$("#returntype").val('C')
			break;


		}
// 		alert($("#returntype").val())
		$("#submitcontent").html(html);
			
	}
	
	function searchTrainst(){
		var trainst_cst_id = $("#trainst_cst_id").val();
		window.open("./searchTrainst.do","학원 찾기","width=400, height =600")

	}
	function emailselect(val){
		var email = $("#email").val();
		if(	email.search("@") >=0){
// 		alert( "그리고 "+email.indexOf("@") )
		email = email.substring(0, email.indexOf("@")) +val;
		}else{
			email = $("#email").val()+""+val;
		}
		$("#email").val(email);
	}
	
	
	function selecttrinstno(){
		$.ajax({
			type : "get",
			url : "./selectTrainst_nm.do",
			data : 	{
				"trainst_cst_id" : $("#trainst_cst_id").val()
				},
			success : function(msg){
				$("#trainst_cst_id_checker").html(msg);
				if(msg == "<span style='color:red;'>없는 번호입니다.</span>"){
					$("#trainst_cst_idhidden").val("false");
				}else{
					
					$("#trainst_cst_idhidden").val("true");
				}
				window.close();
			},
			error : function(){
		        alert("trainst_cst_id Ajax_javascript Error Occupied");
			}
		})
	}
	function openindentify(){
		//휴대폰 인증팝업 열기
		window.open("./phoneidentify.do","휴대폰 인증","width=400, height =600")
	}
	function checkVal(){
		var id = $("#id-input").val();
		var password = $("#password-input").val();
		if($("#phoneidentify").val() == "true" && $("#idhidden").val() == "true" 
				&& $("#passwordhidden").val() == "true" && $("#passwordchkerhidden").val() == "true"){
			if ($("#returntype").val() =="S") {
					$.ajax({
						type : "get",
						url : "./signUpStudent.do",
						data : 	{
							"id" : id,
							"password": password,
							"name" : $("#name").val(),
							"addr1" : $("#addr1").val(),
							"addr2" : $("#addr2").val(),
							"email" : $("#email").val(),
							"phone" : $("#phone").val()
							
							},
						success : function(msg){
							
							alert("회원가입에 성공했습니다.")
							window.close();
		//						alert("idDuplChk Success , msg : "+msg);
						},
						error : function(){
					        alert("signUpStudent Ajax_javascript Error Occupied");
						}
					})
			}else if ($("#returntype").val() =="A"){
				$.ajax({
					type : "get",
					url : "./signUpAcademy.do",
					data : 	{
						"id" : id,
						"password": password,
						"trainst_cst_id" : $("#trainst_cst_id").val(),
						"trprchap" : $("#trprchap").val(),
						"trprchapemail" : $("#trprchapemail").val(),
						"trprchaptel" : $("#trprchaptel").val()
						
						},
					success : function(msg){
						
						alert("회원가입에 성공했습니다.")
						window.close();
					},
					error : function(){
				        alert("SignAcademy Ajax_javascript Error Occupied");
					}
				})
			}else{
				$.ajax({
					type : "get",
					url : "./signUpCenter.do",
					data : 	{
						"id" : id,
						"password": password,
						"addr1" : $("#addr1").val(),
						"addr2" : $("#addr2").val(),
						"cen_name" : $(".cen_name").val(),
						"cen_phone" : $("#cen_phone").val(),
						
						},
					success : function(msg){
						
						alert("회원가입에 성공했습니다.")
						window.close();
	//						alert("idDuplChk Success , msg : "+msg);
					},
					error : function(){
				        alert("signUpStudent Ajax_javascript Error Occupied");
					}
				})
			}
			
		}else{
			if($("#phoneidentify").val() == "false"){
				alert("회원 휴대폰 인증이 완료되지 않았습니다.")
			}else if ( $("#passwordhidden").val() == "false" ){
				alert("잘못된 비밀번호 입니다 다시 입력해 주세요")
			}else if ($("#passwordchkerhidden").val() == "false"){
				alert("비밀번호가 동일하지 않습니다 다시 입력해 주세요")
			}
			
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
		$.ajax({
			type : "get",
			url : "./idDuplChk.do",
			data : 	"id="+val
			,
			success : function(msg){
				if($("#idDuplChker>span").text() =="사용 불가능한 아이디"){
					$("#idhidden").val("false")
				}else{
					$("#idhidden").val("true")
				}
				$("#idDuplChker").html(msg)
//					alert("idDuplChk Success , msg : "+msg);
			},
			error : function(){
		        alert("idDuplChk Ajax_javascript Error Occupied");
			}
		});
	}
	
	function passwordchker(val){
		if (val ==$("#password-input").val()){
			
			$("#passwordchker").css("color", "blue")
			$("#passwordchker").text("비밀번호가 일치합니다.")
			$("#passwordchkerhidden").val("true")
		}else{
			$("#passwordchker").css("color", "red");
			$("#passwordchker").text("비밀번호가 일치하지 않습니다.")
			$("#passwordchkerhidden").val("false")

		}
	}
	function passwordregex(val) {
		$.ajax({
			type : "get",
			url : "./passwordregex.do",
			data : 	"password="+val,
			success : function(msg){
				$("#passwordregex").html(msg)
				if($("#passwordregex>span").text() =="사용 가능한 비밀번호"){
					$("#passwordhidden").val("true")
				}else{
					$("#passwordhidden").val("false")
				}
//					alert("idDuplChk Success , msg : "+msg);
			},
			error : function(){
		        alert("passwordregex Ajax_javascript Error Occupied");
			}
		});
	}
	
	function duplchk(){
		window.open("./idDupleChker.do",'아이디 중복검사', 'width=600,height=500')
	}
</script>
</html>