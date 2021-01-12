<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="com.min.edu.dto.MemberAuthDto"%>
<%@page import="com.min.edu.service.IServiceAuth"%>
<%@page import="java.security.Principal"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<title>Insert title here</title>
  

<script src="https://uicdn.toast.com/tui.code-snippet/latest/tui-code-snippet.js"></script>
<script src="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.min.js"></script>
<script src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chance/1.0.13/chance.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/3.0.1/mustache.min.js"></script>
<script src =" https://uicdn.toast.com/tui.dom/v3.0.0/tui-dom.js "></script>
<link rel="stylesheet" type="text/css" href="/GWD_LMS_SYS/dist/tui-calendar.css">
<link rel="stylesheet" href="/GWD_LMS_SYS/css/calendar_calendar.css">

<link rel="stylesheet" type="text/css" href="/GWD_LMS_SYS/css/sweetalert.css">
<link rel="stylesheet" type="text/css" href="/GWD_LMS_SYS/css/tui-date-picker.css">
<link rel="stylesheet" type="text/css" href="/GWD_LMS_SYS/css/home.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css">
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css">
<link rel="stylesheet" type="text/css" href="https://nhn.github.io/tui.calendar/latest/examples/css/icons.css">
<link rel="stylesheet" type="text/css" href="/GWD_LMS_SYS/dist/tui-calendar.css">

<link rel="stylesheet" type="text/css" href="/GWD_LMS_SYS/css/chat_messenger.css">

<script type="text/javascript" src="/GWD_LMS_SYS/resources/js/sweetalert.min.js"></script>
<link rel="stylesheet" href="/GWD_LMS_SYS/css/calendar_calendar.css">

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">

var alarmTimer = null;
var alarmSet;

function setAlarm() {
	alarmSet = true;
}
function clearAlarm() {
	alarmSet = false;
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
		var student = document.getElementById("ids").value
		alert(student+"회원님 1시간 후 예정시간입니다");
		/* alert(user)  */
	}
}

	$(function(){
		
		var chatVal = $(".chat").val();
		if(chatVal == 0){
			// 메신저 화면 로드
			$.ajax({
				url:"/GWD_LMS_SYS/goChat.do",
				success:function(result){
					$(".chatMain").html(result);
					$(".chatForm").css("width","0px");
				}
			});
		}
		
		$("#chatBtn").on("click", function() {
			$(".chatForm").css("width","300px");
		});
		
		$("#chatClose").on("click", function() {
			$(".chatForm").css("width","0px");
		});
		$(".submenu").on("click",function(e) {
			if(e.target != this) {
				return false;
			} else {
				$(this).children('.oneDepthMenu').slideToggle();
			}
		});
		$(".subtitle").on("click",function(e) {
			if(e.target != this) {
				return false;
			} else {
				$(this).children('.twoDepthMenu').slideToggle();
			}
		});
		$(".mainTitle").on("click",function(e) {
			if(e.target != this) {
				return false;
			} else {
				$(this).next('.oneDepthMenu').slideToggle();
			}
		});
		$(".mainImage").on("click",function(e) {
			if(e.target != this) {
				return false;
			} else {
				$(this).next().next('.oneDepthMenu').slideToggle();
			}
		});
		if( ${pageContext.request.userPrincipal ne null} ){
		$.ajax({
			 url:"./bbb.do",
			 dataType : 'json',
			 method : 'get',
		        success : function(data) {
		        	var varHtml = '';
		        	for (var i = 0; i < data.length; i++) {
		        		 var st = data[i].alarm_date
		 		        st = st.substr(11, 13)
		 		        /* alert(st) */
		 		        console.log(st.substr(0,2))
		 		    var ids = data[i].id
		 		    varHtml = "<input type='hidden' name='h' id='h' value="+st.substr(0,2)+" size=2>"
		 		    varHtml += "<input type='hidden' name='m'  id='m' value='4' size=2>"
		 		    varHtml += "<input type='hidden' name='s' id='s' value='10' size=2>"
		 		    varHtml += "<input type='hidden' name='ids' id='ids' value='"+ids+"' size=2>"
		 		    	$(".addss").html(varHtml);
		        	}
		 		   if (alarmTimer != null)
		 				clearInterval(alarmTimer);
		 			var nowTime = new Date();
		 			clearAlarm(); 
		 			document.exf1.h.value = document.getElementById("h").value
		 			document.exf1.m.value = document.getElementById("m").value
		 			document.exf1.s.value = document.getElementById("s").value
		 			alarmTimer = setInterval("countTime()", 1000);
		    },
		    error : function(request,status,error){
				 console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
			}) 
		}
	});
	
	function actionsubmit(val){
		location.href =val;
	}
	</script>