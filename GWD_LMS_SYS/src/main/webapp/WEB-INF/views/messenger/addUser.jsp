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
		
			<table>
				<%
					for(StudentDto dto : lists){
						if(!dto.getId().equals(loginDto.getName())){
							%>
							<tr>
								<th>아이디</th>
								<td><%=dto.getId()%></td>
								<td>
									<input type="button" class="btn" value="친구 추가" onclick="follow( '<%=loginDto.getName()%>','<%=dto.getId()%>' )">
								</td>
							</tr>
							<%
						}
					}
				%>
			</table>
			<script type="text/javascript">
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