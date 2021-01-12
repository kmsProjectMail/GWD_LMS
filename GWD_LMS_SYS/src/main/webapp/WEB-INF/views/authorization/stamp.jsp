<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/GWD_LMS_SYS/css/sweetalert.css">
</head>
<body>
	<c:url var="image" value="/stampImage.do"/>
	<img id="preview" width="400" src="${image}" alt="로컬에 있는 이미지가 보여지는 영역">
	<input type="hidden" id="imageFile" value="${imageFile}">
<!-- 	<a id="download" download="thumbnail.jpg" target="_blank"> -->
<!-- 	    <img id="thumbnail" src="" width="100" alt="썸네일영역 (클릭하면 다운로드 가능)"> -->
<!-- 	</a> -->
	<c:url value="/stampTest.do?${_csrf.parameterName}=${_csrf.token}" var="write"/>
	<form:form id="imgForm" action="${write}" method="post" enctype="multipart/form-data">
		<input type="file" id="getfile" name="file" accept="image/*">
		<input type="button" value="등록" onclick="fileCheck(this)">
	</form:form>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="/GWD_LMS_SYS/resources/js/sweetalert.min.js"></script>
<script type="text/javascript">
var file = document.getElementById('getfile');
var imagesFile=document.getElementById('imageFile').value;
function fileCheck(btn) {
	if(imagesFile=='' && file.value=='') {
		swal("이미지를 등록해주세요.","이미지가 등록되지 않았습니다.","warning");
	}
	else if(imagesFile!='' && file.value=='') {
		swal("파일 등록 완료","정상처리 되었습니다.","success");
		window.close();
	} else if(imagesFile!='' && file.value!='') {
		swal("파일 변경 완료","이미지가 변경되었습니다.","success");
		var form = $('#imgForm');
		form.append('<input type="hidden" name="img" value="img">');
		form.submit();
	} else {
		swal("파일 등록 완료","정상처리 되었습니다.","success");
		document.getElementById('imgForm').submit();
	}
}

file.onchange = function () {
    var fileList = file.files ;

    // 읽기
    var reader = new FileReader();
    reader.readAsDataURL(fileList [0]);

    //로드 한 후
    reader.onload = function  () {
        //로컬 이미지를 보여주기
        document.querySelector('#preview').src = reader.result;
    };
};
</script>
</html>