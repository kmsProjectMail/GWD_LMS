<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header_popup.jsp"%>

<body>
<input type="button" onclick="timerActive()" value ="작동">
<input id = "result" value ="text">
</body>
<script>
var timerStatus = false;
function timerActive(){
	alert("작동")
	var num = 60;
	var myVar;
	function timer (){
		myVar = setInterval(alertFunc,1000);
	}
	if(timerStatus ==false){
		timer();
	}
	function alertFunc(){
		timerStatus = true;
		var min = num/60
		min = Math.floor(min);
		var sec = num -(60*min);
		
		console.log(min);
		console.log(sec);
		
		$("#result").val(min+'분'+sec+'초');
		
		if(num ==0){
			clearInterval(myVar);
		}
		num--;
	}
}

</script>
</html>