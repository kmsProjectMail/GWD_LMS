<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>휴대폰 인증 페이지</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div style="background-color: #2C3E50; width: 100%; height: 20%; color:snow; text-align: center" align="center" ><h2>휴대폰 인증 페이지</h2></div>

<table class= table>
<input type="hidden" value ="" id ="answer">
	<thead>
		<tr>
			<th>휴대폰 번호</th>
			<td colspan="2"><input style="width: 80px" type="text" placeholder="000" id ="phone1" class="phone">
			<input style="width: 80px" type="text" placeholder="0000" id ="phone2" class="phone">
			<input style="width: 80px" type="text" placeholder="0000" id ="phone3" class="phone">
			 </td>
		</tr>
		
		<tr>
			<th colspan="3" style="text-align: center" ><input class ="btn btn-primary btn-block" type="button" value ="인증하기" onclick="doIdentify()"></th> 
		</tr>
	</thead>
	<tbody>		
		<tr id ="answerArea" style="display: none;">
			<th>인증번호</th>
			<th><input style="width: 100%;" type="text"  id ="myNum" class="myNum"></th>
			<th><input  type="button"  id ="submitAnswer" class="btn btn-primary" onclick="submitAnswer()" value ="인증번호 확인"></th>
		</tr>
	</tbody>
</table>
</body>
<script type="text/javascript">
	$("#phone1").on("propertychange change keyup paste input", function() {
		var newval = $(this).val()
	    if(getTextLength($(this).val()) > 3){
	    	var temp = newval.substring(newval.length-1,newval.length)
	    	$(this).val(newval.substring(0,2) +temp);
	    }
	});
	$("#phone2").on("propertychange change keyup paste input", function() {
		var newval = $(this).val()
	    if(getTextLength($(this).val()) > 4){
	    	var temp = newval.substring(newval.length-1,newval.length)
	    	$(this).val(newval.substring(0,3) +temp);
	    }
	});
	$("#phone3").on("propertychange change keyup paste input", function() {
		var newval = $(this).val()
	    if(getTextLength($(this).val()) > 4){
	    	var temp = newval.substring(newval.length-1,newval.length)
	    	$(this).val(newval.substring(0,3) +temp);
	    }
	});

	function doIdentify(){
		if(getTextLength($("#phone1").val() )==3 && getTextLength($("#phone2").val()) ==4 && getTextLength($("#phone3").val()) == 4)  {
			var phone = $("#phone1").val()+$("#phone2").val()+$("#phone3").val()
			$.ajax({
				url : "./sendIdentifyMsg.do",
				method :"get",
				data : {
					"phone" :phone,
					},
				success : function(msg){
					$("#answer").val(msg);
					$("#answerArea").css("display", "")
				},
				error : function(){
					alert("doIdentify Ajax Has a Problem")
				}
				
			
			})
		}else{
			alert("번호를 제대로 입력해주십시오")
		}
		
	}
	function submitAnswer(){
		$.ajax({
			url : "./comPareIdentifyNumber.do",
			method :"get",
			data : {
				"myNum" :$("#myNum").val(),
				"receiveNum" : $("#answer").val()
				},
			success : function(msg){
				if(msg == "succ"){
					alert("인증성공!");
					opener.document.getElementById("phone").value =  $("#phone1").val()+$("#phone2").val()+$("#phone3").val()
					opener.document.getElementById("phoneidentify").value ="true";
					window.close();
				}else{
					alert("인증실패!")
				}
			},
			error : function(){
				alert("doIdentify Ajax Has a Problem")
			}
		})
	}
	function getTextLength(val){
		return val.length
	}
	function check3(val){
		if(val.length >3){
			return false;
		}
	}
	function check4(val){
		if(val.length !=4){
			return false;
		}
	}
</script>
</html>