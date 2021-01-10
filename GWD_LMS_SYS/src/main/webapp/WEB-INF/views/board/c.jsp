<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
 var alarmTimer = null;
var alarmSet;

function setAlarm() {
	alarmSet = true;
}
function clearAlarm() {
	alarmSet = false;
}
window.onload = function()  {
	if (alarmTimer != null)
		clearInterval(alarmTimer);
	var nowTime = new Date();
	
	clearAlarm(); 
	document.exf1.h.value = document.getElementById("h").value
	document.exf1.m.value = document.getElementById("m").value
	document.exf1.s.value = document.getElementById("s").value
	alarmTimer = setInterval("countTime()", 1000);
}
function matchH() {
	return (document.exf1.ch.value == document.exf1.h.value);
}
function matchM() {
	return (document.exf1.cm.value == document.exf1.m.value);
}
function matchS() {
	return (document.exf1.cs.value == document.exf1.s.value);
}

function countTime() {
	var nowTime = new Date();
	document.exf1.ch.value = nowTime.getHours();
	document.exf1.cm.value = nowTime.getMinutes();
	document.exf1.cs.value = nowTime.getSeconds();

	if (matchH()&&matchM()&&matchS()) {
		var id = document.getElementById("userid").value
		alert(id+"님 1시간 후 면담진행하겠습니다");
		$("#hers").html("<div style='color:red'>new!</div>")
	}
}
/* onload = initAlarm; */
</script>
<body>
${gg}
<!-- topMenu에 넣을 예정 -->
<!-- topMenu에 넣을 예정 -->
<!-- topMenu에 넣을 예정 -->
<!-- topMenu에 넣을 예정 -->
<!-- topMenu에 넣을 예정 -->
<!-- topMenu에 넣을 예정 -->
<!-- topMenu에 넣을 예정 -->
<!-- topMenu에 넣을 예정 -->
<!-- topMenu에 넣을 예정 -->
<!-- topMenu에 넣을 예정 -->
<c:forEach items="${gg}" var="vo">
<form name=exf1>
		<c:set var = "str" value = "${vo.alarm_date}"/>
		 <c:choose>
			<c:when test="${fn:substring(str, 11,13) eq 09}">
				<input type="hidden" name=h id="h" value="9" size=2>
			</c:when>
			<c:when test="${fn:substring(str, 11,13) eq 00}">
				<input type="hidden" name=h id="h" value="0" size=2>
			</c:when>
			<c:otherwise> 
				<input type="hidden" name=h id="h" size=2
					value="${fn:substring(str, 11,13)}">
			</c:otherwise>
		</c:choose> 
		<input type="hidden" name=m  id="m" value="0" size=2> 
		<input type="hidden" name=s id="s" value="0" size=2>
		
		<br>
		<input type="hidden" name=ch size=2>
		<input type="hidden" name=cm size=2> 
		<input type="hidden" name=cs size=2>
	</form>
</c:forEach>

	</body>
</html>