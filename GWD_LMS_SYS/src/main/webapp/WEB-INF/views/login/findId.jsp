<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../header_popup.jsp" %>

<body>

<div class ='popupheaderbar'>아이디 찾기</div>

<table class=table>
	<thead>
		<tr>
			<th colspan="2" style="text-align: center">
			<input class='custombtn' type = 'button' value="이메일로 찾기" onclick ="findmethod(1)">
			<input class='custombtn' type = "button" value="휴대폰번호로 찾기" onclick ="findmethod(2)"></th>
		</tr>
	</thead>
	<tbody id = 'foremail' style="display: none">
		<tr >
			<th >이름</th>
			<td><input  id ='name1' type = 'text' placeholder='이름을 입력하세요'></td>
		</tr>
		<tr>
				<th>이메일</th>
				<td><input type = 'text'  id ='email' placeholder='이메일을 입력하세요'></td>
		</tr>
		
	</tbody>
	<tbody id ="forphone"  style="display: none">
		<tr>
			<th>이름</th>
			<td><input id ='name2' type = 'text' placeholder='이름을 입력하세요'></td>
		</tr>
		<tr>
			<th>핸드폰 번호</th>
			<td><input class ='phone' id ='phone1' type = 'text' placeholder='000'>
				<input  class ='phone' id ='phone2' type = 'text' placeholder='0000'>
				<input  class ='phone' id ='phone3' type = 'text' placeholder='0000'>
			</td>
		</tr>
	
	</tbody>
	<tfoot style="display: none">
		<tr>
			<th colspan="2" style="text-align: center">
			<input class='custombtn-long' type = 'button' value="찾기" onclick ="executefind()">
			<input type="hidden" id="findmethod"value="">
		</tr>
		<tr id = "phone-result" style="display: none">
			<th colspan="2"></th>
		</tr>
		<tr id = "email-result" style="display: none">
					<th colspan="2"></th>
		</tr>
	</tfoot>
</table>
</body>
<script type="text/javascript">
	function findmethod(val){
		
		if(val ==1){
			//이메일로 찾는 경우
			$("#foremail").css("display","")
			$("#forphone").css("display","none")
			$("#phone-result>th").text("")
			$("#phone-result").css("display","");

			$("#findmethod").val('1')
		}else if(val ==2){
			//핸드폰 번호로 찾는 경우
			$("#foremail").css("display","none")
			$("#email-result>th").text("")
			$("#email-result").css("display","");
			$("#forphone").css("display","")
			 $("#findmethod").val('2')
		}
		$("tfoot").css("display","")
	}	
	function executefind(){
		
		var val = $("#findmethod").val()
		if(val =="1"){
			if($("#name1").val()==""){
				alert("이름을 입력해 주세요")
				$("#name1").focus()

			}else{ 
				if($("#email").val()==""){
				alert("이메일을 입력해 주세요")
				$("#email").focus()
				
				}else{
					$.ajax({
						type : "get",
						url : "./findId.do",
						data: {
							"name" : $("#name1").val(),
							"email" : $("#email").val()
						},
						dataType : "json",
						success:function(msg){
							$("#email-result").css("display","");
							if(msg=="false"){
								$("#email-result>th").text("해당 아이디가 없습니다.")
							}else{
								$("#email-result>th").text("아이디는 < "+msg.ID+" >입니다")
							}
		
						},
						error : function(){
							alert("executefind ajax has a problem")
						}
					});
				}
			}
		}else if(val =="2"){
			if($("#name2").val()==""){
				alert("이름을 입력해 주세요")
				$("#name2").focus()

			}else{ 
				if($("#phone1").val()=="" || $("#phone2").val()==""||$("#phone3").val()==""){
				alert("번호를 입력해 주세요")
				
				}else{
					$.ajax({
						type : "get",
						url : "./findId.do",
						data: {
							"name2" : $("#name2").val(),
							"phone" : $("#phone").val()
						},
						dataType : "json",
						success:function(msg){
							$("#phone-result").css("display","");
							if(msg=="false"){
								$("#phone-result>th").text("해당 아이디가 없습니다.")
							}else{
								$("#phone-result>th").text("아이디는 < "+msg.ID+" >입니다")
							}
		
						},
						error : function(){
							alert("executefind ajax has a problem")
						}
					});
				}
			}
		}
		
	}
</script>
<script src="../resources/js/header_popup.js"></script>
</html>