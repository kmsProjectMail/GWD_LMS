<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.chatDrop{
	width: 300px;
	height: 400px;
	margin-left: 215px;
}
</style>
</head>

<script type="text/javascript">
$(document).ready(function() {
	
	var chatVal = $('.chatVal').val();
	
	$.ajax({
		url:"./goChat.do",
		success:function(result) {
			$(".chatDrop").html(result);
		}	
	});
	
	$("#chatDrop").bind("click", function(){
			$(".chatDrop").toggle();
	});
	
});
</script>

<body>

<nav class="navbar navbar-inverse" style="margin-bottom: 0px;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li class="dropdown" id="chatDrop"><a class="dropdown-content" data-toggle="dropdown" href="#">채팅 <span class="caret"></span></a>
      </li>
      <li><a href="#">Page 2</a></li>
    </ul>
  </div>
</nav>
<div class="chatDrop" style="display: none;">
</div>
</body>
</html>