<%@page import="java.security.Principal"%>
<%@page import="com.min.edu.dto.MessengerDto"%>
<%@page import="com.min.edu.info.UserInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	List<MessengerDto> lists = (List<MessengerDto>)request.getAttribute("lists");
	Principal loginDto = (Principal)request.getUserPrincipal();
%>
<body>
	<div>
		<div class="subjectDiv">
			<a1 class="subject">나의 채팅 목록</a1>
		</div>
		
		<table>
			<%
				for(MessengerDto dto : lists){
						String[] member = dto.getChatmember().split(",");
						String sender = "";
						String receiver = "";
						for(int i = 0; i < member.length; i++){
							if(member[i].equalsIgnoreCase(loginDto.getName())){
								sender=member[i];
							}else{
								receiver=member[i];
							}
						}
							%>
							<tr>
								<th><img alt="프로필" src="images/chat_profile.png"></th>
								<td><%=receiver%></td>
								<td>
									<input type="button" class="btn" value="채팅시작" onclick="goSocket('<%=receiver%>', '<%=loginDto.getName()%>')">
								</td>
								<td class="delBtn" style="display: none">
									<input type="button" class="btn" value="삭제" onclick="delChat('<%=receiver%>', '<%=loginDto.getName()%>')">
								</td>
							</tr>
							<%
				}
				%>
		</table>
		<div style="text-align: center;">
			<input type="button" id="edit" class="btn" value="EDIT" style="width: 270px; height: 30px;" >
		</div>
		<script type="text/javascript">
			function goSocket(other,user){	
					window.open("./socketOpen.do?user="+user+"&other="+other, "일대일채팅", "width = 500, height = 580, resizable = no, toolbar = no, menubar = no, location = no, fullscreen = no, left = 300, top = 50");
			}
			
			var loginId = $('#loginId').val();
			
			var ajaxChatList = function(){
				$.ajax({
					url:"./myChatList.do",
					data:"id="+ loginId,
					success:function(result){
						$(".body").html(result);
					}
				});
			}
			
			function delChat(other, me){
				$.ajax({
					url:"./delChat.do",
					data:"id="+me+"&otherId="+other,
					success:function(result) {
						var isc = result;
						if(isc){
							alert("해당 채팅방을 삭제했습니다.");
							ajaxChatList();
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
	</div>
</body>
</html>