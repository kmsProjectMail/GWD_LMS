<%@page import="com.min.edu.dto.StudentDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 목록</title>
</head>
<%
	List<StudentDto> lists = (List<StudentDto>)request.getAttribute("lists");
%>
<script type="text/javascript">
$(function(){
	
	var loginId = $('#loginId').val();
	
	$.ajax({
		url:"/GWD_LMS_SYS/friendList.do",
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
			url:"/GWD_LMS_SYS/myChatList.do",
			data:"id="+ loginId,
			success:function(result){
				$(".body").children().remove();
				$(".body").html(result);
			}
		});
	}
	
	$(".addUser").bind("click", function(){
		ajaxAddUser();
	});
	
	var ajaxAddUser = function(){
		$.ajax({
			url:"/GWD_LMS_SYS/addUser.do",
			success:function(result){
				$(".body").children().remove();
				$(".body").html(result);
			}
		});
	}
	
	$(".friendList").bind("click", function(){
		ajaxFriendList();
	});
	
	var ajaxFriendList = function(){
		$.ajax({
			url:"/GWD_LMS_SYS/friendList.do",
			data:"id="+ loginId,
			success:function(result){
				$(".body").children().remove();
				$(".body").html(result);
			}
		});
	}
	
	$(".chatClose").on("click", function() {
		$(".chatForm").css("width","0px");
	});
});

function goSocket(other,user){
	window.open("/GWD_LMS_SYS/socketOpen.do?user="+user+"&other="+other, "채팅", "width = 440, height = 565, resizable = no, toolbar = no, menubar = no, location = no, fullscreen = no, left = 1080, top = 153");
	$(".alarmBadge").load(location.href + " .alarmBadge");
	$(".alarm").load(location.href + " .alarm");
}

</script>

<body>
	<input type="hidden" id="loginId" value="<%=request.getUserPrincipal().getName()%>">
	<div id="chatContainer">
		<header style="height: 80px; background-color: #2C3E50;">
			<div class="chatClose" style="float:right; cursor:pointer; width:32px; height:32px; background-image: url('/GWD_LMS_SYS/images/chat_exit.png');"></div>
			<br>
			<div class="friendList"></div>
			<div class="myChatList"></div>
			<div class="addUser"></div>
		</header>
		<div class="body">
		</div>
	</div>
</body>
</html>