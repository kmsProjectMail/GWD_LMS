<%@page import="com.min.edu.dto.StudentDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 목록</title>
<link type="text/css" rel="stylesheet" href="./css/chat_messenger.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>
<%
	List<StudentDto> lists = (List<StudentDto>)request.getAttribute("lists");
%>

<script type="text/javascript">
$(document).ready(function(){
	
	var loginId = $('#loginId').val();
	
	$.ajax({
		url:"./friendList.do",
		data:"id="+ loginId,
		success:function(result) {
			$(".body").html(result);
		}	
	});
	
	$(".myChatList").bind("click", function(){
		ajaxChatList();
	});
	
	var ajaxChatList = function(){
		$.ajax({
			url:"./myChatList.do",
			data:"id="+ loginId,
			success:function(result){
				$(".body").html(result);
			}
		});
	}
	
	$(".addUser").bind("click", function(){
		ajaxAddUser();
	});
	
	var ajaxAddUser = function(){
		$.ajax({
			url:"./addUser.do",
			success:function(result){
				$(".body").html(result);
			}
		});
	}
	
	$(".friendList").bind("click", function(){
		ajaxFriendList();
	});
	
	var ajaxFriendList = function(){
		$.ajax({
			url:"./friendList.do",
			data:"id="+ loginId,
			success:function(result){
				$(".body").html(result);
			}
		});
	}
	
});

</script>

<body>
	<input type="hidden" id="loginId" value="<%=request.getUserPrincipal().getName()%>">
	<div id="container">
		<header style="height: 60px; background-color: #2F3136">
			<div class="friendList"></div>
			<div class="myChatList"></div>
			<div class="addUser"></div>
		</header>

		<div class="body">
		</div>
	</div>
<script type="text/javascript">
		function goSocket(other,user){
				window.open("./socketOpen.do?user="+user+"&other="+other, "채팅", "width = 800, height = 600, resizable = no, toolbar = no, menubar = no, location = no, fullscreen = no, left = 300, top = 50");
		}
</script>
</body>
</html>