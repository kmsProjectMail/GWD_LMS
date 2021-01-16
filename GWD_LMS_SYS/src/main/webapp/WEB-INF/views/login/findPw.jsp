<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header_popup.jsp"%>

<body>
<input type="hidden" value ="" id ="answer">

	<div class='popupheaderbar'>아이디 찾기</div>

	<table class=table>
		<thead>
			<tr>
				<th colspan="2" style="text-align: center"><input
					class='custombtn' type='button' value="이메일로 찾기"
					onclick="findmethod(1)"> <input class='custombtn'
					type="button" value="휴대폰번호로 찾기" onclick="findmethod(2)"></th>
			</tr>
		</thead>
		<tbody id='foremail' style="display: none">
			<tr>
				<th>이름</th>
				<td><input id='name1' type='text' placeholder='이름을 입력하세요' ></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input id='id1' type='text' placeholder='아이디를 입력하세요' ></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type='text' id='email' placeholder='이메일을 입력하세요' ></td>
			</tr>

		</tbody>
		<tbody id="forphone" style="display: none">
			<tr>
				<th>이름</th>
				<td><input id='name2' type='text' placeholder='이름을 입력하세요' ></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input id='id2' type='text' placeholder='아이디를 입력하세요' ></td>
			</tr>
			<tr>
				<th>핸드폰 번호</th>
				<td>
				<input class='phone' id='phone1' type='text' placeholder='000' > 
				<input class='phone' id='phone2'type='text' placeholder='0000' > 
				<input class='phone'id='phone3' type='text' placeholder='0000' >
				</td>
			</tr>

		</tbody>
		<tfoot style="display: none">
			<tr>
				<th colspan="2" style="text-align: center">
				<input class='custombtn-long' type='button' value="찾기" onclick="executefind()"> <input type="hidden" id="findmethod" value="">
			</tr>
			<tr id="phone-result" style="display: none">
				<th ></th>
			</tr>
			<tr id="email-result" style="display: none">
				<th style="width:40%"></th>
			</tr>
			<tr id="timer" style="display: none; color: red;">
				<th></th>
			</tr>
		</tfoot>
	</table>
</body>
<script type="text/javascript">
var isRunning = false;
var timerExpired = false;
	function findmethod(val) {
		if(isRunning ==false){
	
			if (val == 1) {
				//이메일로 찾는 경우
				$("#findmethod").val('1')
	
				$("#foremail").css("display", "")
				$("#forphone").css("display", "none")

				$("#phone-result>th").text("")
				$("#email-result").css("display", "none");
				$("#phone-result").css("display", "");
			} else if (val == 2) {
				//핸드폰 번호로 찾는 경우
				$("#findmethod").val('2')
	
				$("#foremail").css("display", "none")
				$("#forphone").css("display", "")
				
				$("#phone-result").css("display", "none");
				$("#email-result>th").text("")
				$("#email-result").css("display", "");
			}
			$("tfoot").css("display", "")
		}
	}
	function executefind() {
	if(isRunning ==false){
		var val = $("#findmethod").val()
		if (val == "1") {
			if ($("#name1").val() == "") {
				alert("이름을 입력해 주세요")
				$("#name1").focus()

			} else {
				if ($("#id1").val() == "") {
					alert("아이디를 입력해 주세요")
					$("#id1").focus()

				} else {
					if ($("#email").val() == "") {
						alert("이메일을 입력해 주세요")
						$("#email").focus()

					} else {
						$.ajax({
							type : "get",
							url : "./findPw.do",
							data : {
								"name" : $("#name1").val(),
								"id" : $("#id1").val(),
								"email" : $("#email").val()
							},
							dataType : "json",
							success : function(msg) {
								$("#email-result").html("<th style='width:40%'></th>")
								$("#email-result").css("display", "");
								if (msg == "false") {
									$("#email-result>th").text("일치하는 아이디가 없습니다.")
								} else {
									$("#answer").val(msg);

									$("#email-result>th").text(
									"이메일로 인증번호를 보냈습니다")
									var html ="<td><input id='chkanswer' style='width:70%' type ='text' placeholder='인증번호를 입력하세요'><input style='width:29%' type ='button' onclick='chkidenfity()' value='확인'></td>"
									$("#email-result").append(html)
									timerActive();
									
									$("#email-result>th").text()
								}

							},
							error : function() {
								alert("executefind ajax has a problem")
							}
						});
					}
				}
			}
		} else if (val == "2") {
			if ($("#name2").val() == "") {
				alert("이름을 입력해 주세요")
				$("#name2").focus()

			} else {
				if ($("#id2").val() == "") {
					alert("아이디를 입력해 주세요")
					$("#id2").focus()

				} else {
					if ($("#phone1").val() == "" || $("#phone2").val() == ""
							|| $("#phone3").val() == "") {
						alert("번호를 입력해 주세요")

					} else {
						$.ajax({
							type : "get",
							url : "./findPw.do",
							data : {
								"name" : $("#name2").val(),
								"id" : $("#id2").val(),
								"phone" : $("#phone1").val()+""+ $("#phone2").val()+""+ $("#phone3").val()
							},
							dataType : "json",
							success : function(msg) {
								$("#phone-result").html("<th style='width:40%'></th>")
								$("#phone-result").css("display", "");
								if (msg == "false") {
									$("#phone-result>th").text("일치하는 아이디가 없습니다.")
								} else {
									$("#answer").val(msg);

									$("#phone-result>th").text(
									"문자로 인증번호를 보냈습니다")
									var html ="<td><input id='chkanswer' style='width:70%' type ='text' placeholder='인증번호를 입력하세요'><input style='width:29%' type ='button' onclick='chkidenfity()' value='확인'></td>"
									$("#phone-result").append(html)
									timerActive();
									
									$("#phone-result>th").text()
								}
								
							},
							error : function() {
								alert("executefind ajax has a problem")
							}
						});
					}
				}
			}
		}
	}
	}
	
	function chkidenfity(){
		if(timerExpired){
			alert("인증 시간이 만료되었습니다 새로고침후 다시 시도해주세요")
			
		}else{
			
			if($("#answer").val() == $("#chkanswer").val() ){ //인증번호가 맞는경우
				alert("일치")
				var id = "";
				//새창열기
				if( $("#id1").val() != null){
					id= $("#id1").val()
				}else{
					id =$("#id2").val()
				}
				var url ="./updatePasswordForm.do?id="+id;
				window.open(url, '비밀번호 재설정 페이지' , 'width =600, height =500')
			}else{ //인증번호가 틀린경우
				alert("일치하지 않습니다 다시 확인해주세요")
				
			}
			
		}
	}
	function timerActive(){

		var time = 180;
		var myVar ;
		function timer(){
			
			myVar = setInterval(alertFunc,1000);
		}
		if(isRunning==false){
			timer();
			$("#timer").css("display","");
		}
		function alertFunc(){
			isRunning = true;
			var min = time/60
			min = Math.floor(min);
			var sec = time -(60*min);
			
			console.log(min);
			console.log(sec);
			
			$("#timer>th").text(min+'분'+sec+'초');
			
			if(time ==0){
				clearInterval(myVar);
				timerExpired =true;
			}
			time--;
		}
	}
	</script>
</html>