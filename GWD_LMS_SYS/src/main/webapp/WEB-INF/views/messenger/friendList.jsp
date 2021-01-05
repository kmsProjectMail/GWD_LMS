<%@page import="java.security.Principal"%>
<%@page import="com.min.edu.info.UserInfo"%>
<%@page import="com.min.edu.dto.FriendDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	List<FriendDto> flists = (List<FriendDto>)request.getAttribute("flists");
	Principal loginDto = (Principal)request.getUserPrincipal();
%>
<body>
<div id="ref">
	<div class="subjectDiv">
		<a1 class="subject">친구 목록</a1>
	</div>
	<table>
		<%
			for(FriendDto dto : flists){
				%>
				<tr>
					<th><img alt="프로필" src="images/chat_profile.png"></th>
					<td><%=dto.getFriend_id()%></td>
					<td>
						<input type="button" class="btn" value="채팅시작" onclick="goSocket('<%=dto.getFriend_id()%>', '<%=loginDto.getName()%>')">
					</td>
					<td class="delBtn" style="display: none">
						<input type="button" class="btn" value="삭제" onclick="delFriend('<%=dto.getFriend_id()%>', '<%=loginDto.getName()%>')">
					</td>
				</tr>
				<%
			}
		%>
	</table>
	<div style="text-align: center;">
		<input type="button" id="edit" class="btn" value="EDIT" style="width: 270px; height: 30px;" >
	</div>
</div>
	<script type="text/javascript">
			function goSocket(other,user){	
					window.open("./socketOpen.do?user="+user+"&other="+other, "일대일채팅", "width = 500, height = 580, resizable = no, toolbar = no, menubar = no, location = no, fullscreen = no, left = 300, top = 50");
			}

			var loginId = $('#loginId').val();
			
			var ajaxFriendList = function(){
				$.ajax({
					url:"./friendList.do",
					data:"id="+ loginId,
					success:function(result){
						$(".body").html(result);
					}
				});
			}
			
			function delFriend(fid, id){
				$.ajax({
					url:"./delFriend.do",
					data:"id="+id+"&friend_id="+fid,
					success:function(result) {
						var isc = result;
						if(isc){
							alert("해당유저를 친구목록에서 제거했습니다.");
							ajaxFriendList();
						}else{
							alert("삭제에 실패 하셨습니다.");
						}
					}	
				});
			}
			
			$(document).ready(function() {
				$("#edit").bind("click", function(){
					$(".delBtn").toggle();
				});
			});
	</script>
</body>
</html>