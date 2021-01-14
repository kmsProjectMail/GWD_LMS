<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학원검색</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div style="background-color: #2C3E50; width: 100%; height: 20%; color:snow; text-align: center" align="center" ><h2>학원조회 페이지</h2></div>
<table class ="table">
	<thead>
		<tr>
			<th><input type ="text" id= "trainst_cst_id" placeholder="학원 일련번호 검색" ></th>
			<td><input class ="btn-primary" type ="button" value ="검색" onclick="dosearch()"><input type="hidden" id="result" value=""> </td>
				
		</tr>
	</thead>
	<tbody>
		
	</tbody>
	<tfoot>
		<tr id ="displayArea" style="display: none;">
			<th colspan="2"><input  type="button"  id ="submitAnswer" class="btn btn-primary btn-block" onclick="selectthis()" value ="선택"></th>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
	function dosearch(){
		$.ajax({
			url :"./doSearch.do",
			data : {
				"trainst_cst_id" : $("#trainst_cst_id").val()
			},
			method : "get",
			success : function(msg){
				$("tbody").html("<tr><th colspan=2>학원명은<strong>"+msg+"</strong>입니다.</th></tr>");
				$("#result").val(msg);	
				$("#displayArea").css("display","");
			},
			error : function (){
		        alert("search trainst_cst_id Ajax_javascript Error Occupied");

			}
		})
	} 
	function selectthis(){
		alert("선택")
		opener.document.getElementById("trainst_cst_id").value = $("#trainst_cst_id").val()
		window.close();
	}
</script>
</body>
</html>