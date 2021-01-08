<%@page import="com.min.edu.vo.HRD_Trainst_Info_Vo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Simple Map</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBWnlw1eqaCRZqPS8WOuS7ib9ZcdWtRUMs&callback=initMap&libraries=&v=weekly"  defer></script> -->
<!-- <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=true"></script> -->

</head>
<%-- <script type="text/javascript" src = "<c:url value ='/resources/js/googleMaps.js'/>"></script> --%>
<link href ="<c:url value ="/resources/css/googleMaps.css"/>" rel="stylesheet">

  <body>
  	<input type="text" id = "idinput" class="idinput" > 
  </body>
  
<!--   <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> -->
<script type="text/javascript">
$("#idinput").on("propertychange change keyup paste input", function(){
	alert(" 작동?")
	val = $('#idinput').val();
	$.ajax({
		type : "get",
		url : "./selectOneUser_dynamic.do",
		data : 	"name="+val
		,
		success : function(msg){
			$("#idDuplChker").text(msg)
			
//				alert("idDuplChk Success , msg : "+msg);
		},
		error : function(){
	        alert("selectOneUser_dynamic Ajax_Jquery Error Occupied");
		}
	});
});
</script>
<script>
// 	function openaddress(){
// 	    new daum.Postcode({
// 	        oncomplete: function(data) {
// 	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
// 	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
// 	            var addr = '';
// 	            var extraAddr = '';
	            
// 	            if(data.userSelectedType ='R'){
// 	            	addr=data.roadAddress;
// 	            }else{
// 	            	addr = data.jibunAddress;
// 	            }
	            
// 	            if(data.userSelectedType === 'R'){
// 	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
// 	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
// 	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
// 	                    extraAddr += data.bname;
// 	                }
// 	                // 건물명이 있고, 공동주택일 경우 추가한다.
// 	                if(data.buildingName !== '' && data.apartment === 'Y'){
// 	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
// 	                }
// 	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
// 	                if(extraAddr !== ''){
// 	                    extraAddr = ' (' + extraAddr + ')';
// 	                }
// 	                // 조합된 참고항목을 해당 필드에 넣는다.
// 	                document.getElementById("extra").value = extraAddr;
	            
// 	            } else {
// 	                document.getElementById("extra").value = '';
// 	            }
	
// 	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
// 	            document.getElementById('zipcode').value = data.zonecode;
// 	            document.getElementById("addr1").value = addr;
// 	            // 커서를 상세주소 필드로 이동한다.
// 	            document.getElementById("addr2").focus();
	            
// 	        }
// 	    }).open();
// 	}
	

		
</script>
</html>