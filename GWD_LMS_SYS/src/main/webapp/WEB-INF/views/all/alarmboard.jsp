<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@include file = "../header.jsp" %>
   
<body> 
<input type ="button" value="클릭" onclick="alarm_list_open()">
<div  id ="alarmdiv" style="overflow:auto; width : 400px; min-height: 0px; max-height: 340px; border: 1px solid black">
	<table class="table" id ="alarmtable">
	
	</table>
</div>
</body>
<script type="text/javascript">
	function loadData(){
		$.ajax({
			url : "/GWD_LMS_SYS/getMyList.do",
			type : "get",
			data:{
					"num" :3
				},
			success :function(msg){
				$("#alarmtable").append(msg);
				$("#alarmdiv").css("display","");
			},
			error : function(){
				alert("alarm_list_open Ajax has a problem")
			}
			
		})
	}
</script>
</html>