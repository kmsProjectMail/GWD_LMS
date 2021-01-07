<%@page import="java.security.Principal"%>
<%@page import="com.min.edu.info.UserInfo"%>
<%@page import="com.min.edu.dto.StudentDto"%>
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
	List<StudentDto> lists = (List<StudentDto>)request.getAttribute("lists");
	Principal loginDto = (Principal)request.getUserPrincipal();
%>
<body>
	<div class="subjectDiv">
		<a1 class="subject">친구 추가</a1>
	</div>

	<br>
	<div class="searchBar">
       	<input onkeyup="filter()" type="text" class="search" id="searchVal" placeholder="Search">
    </div>


	<div class="scrollDiv" style="height: 300px;">
			<table>
				<%
					for(StudentDto dto : lists){
						if(!dto.getId().equals(loginDto.getName())){
							%>
							<tr class="item" style="border-bottom: dashed 1px #D5D5D5;">
								<td class="name"><a  style="font-size: x-small; color: lightgray;"><%=dto.getId()%></a><br><%=dto.getName()%></td>
								<td>
									<input type="button" class="btn" value="친구 추가" onclick="follow( '<%=loginDto.getName()%>','<%=dto.getId()%>' )">
								</td>
							</tr>
							<%
						}
					}
				%>
			</table>
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
			
			function follow(me,you){	
				$.ajax({
					url:"./addFriend.do",
					data:"id="+me+"&friend_id="+you,
					success:function(result) {
						var isc = result;
						if(isc){
							alert("친구 추가에 성공 했습니다.");
						}else{
							alert("이미 친구 추가되어있는 대상입니다.");
						}
					}	
				});
				
				
			}
	</script>
</body>
</html>