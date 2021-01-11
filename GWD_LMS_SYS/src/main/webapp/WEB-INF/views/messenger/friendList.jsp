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
	<div class="searchBar">
       	<input onkeyup="filter()" type="text" class="search" id="searchVal" placeholder="Search">
    </div>
	
	<div class="scrollDiv" style="height: 250px;">
	<table class="chatTable">
		<%
			for(FriendDto dto : flists){
				%>
				<tr class="item">
					<th><img alt="프로필" src="/GWD_LMS_SYS/images/chat_profile.png"></th>
					<td class="name"><a  style="font-size: x-small; color: lightgray;"><%=dto.getFriend_id()%></a><br><%=dto.getName()%></td>
					<td>
						<input type="button" class="btn" value="채팅" onclick="goSocket('<%=dto.getFriend_id()%>', '<%=loginDto.getName()%>')">
					</td>
					<td class="delBtn" style="display: none">
						<input type="button" class="btn" value="삭제" onclick="delFriend('<%=dto.getFriend_id()%>', '<%=loginDto.getName()%>')">
					</td>
				</tr>
				<%
			}
		%>
	</table>
	</div>
	<br>
	<div style="text-align: center;">
		<input type="button" id="edit" class="btn" value="EDIT" style="width: 270px; height: 30px;" >
	</div>
</div>
	<script type="text/javascript">
			function filter(){
		        var value, name, item, i;
		
		        value = document.getElementById("searchVal").value.toUpperCase();
		        item = document.getElementsByClassName("item");
				
		        var len = item.length;
		        
		        for(i=0;i<item.length;i++){
		          name = item[i].getElementsByClassName("name");
		          if(name[0].innerHTML.toUpperCase().indexOf(value) > -1){
		            item[i].style.display = "";
		          }else{
		            item[i].style.display = "none";
		          }
		        }
		      }
	
			function goSocket(other,user){	
					window.open("/GWD_LMS_SYS/socketOpen.do?user="+user+"&other="+other, "일대일채팅", "width = 500, height = 580, resizable = no, toolbar = no, menubar = no, location = no, fullscreen = no, left = 300, top = 50");
			}

			var loginId = $('#loginId').val();
			
			var ajaxFriendList = function(){
				$.ajax({
					url:"/GWD_LMS_SYS/friendList.do",
					data:"id="+ loginId,
					success:function(result){
						$(".body").html(result);
					}
				});
			}
			
			function delFriend(fid, id){
				$.ajax({
					url:"/GWD_LMS_SYS/delFriend.do",
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