<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../header.jsp" %>

<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.min.js"></script>
</head>
<body>
	${authorization.content}
	
	
	<c:if test="${authorization.group_status.authorized_status eq '대기'}">
		<input type="button" value="승인" onclick="documentApproved()">
	</c:if>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
	//승인 버튼 클릭시 동작
	function documentApproved() {
		if(confirm("승인시 더이상 문서를 수정할 수 없습니다.")) {
			var html = '<tr><td><form id="approvedForm" action="./documentApproved.do" method="post">'
				+ '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />'
				+ '<input type="hidden" name="seq" value='+seq+' />'
				+ '</form></td></tr>';
				$("#container table tbody").append(html);
				$('#contentNone').attr("display","");
				console.log($('.cke_wysiwyg_frame').contents().find('body'));
				console.log($('body'));
				html2canvas(document.getElementById('contentNone')).then(function(canvas) {
					var imgData = canvas.toDataURL('image/png'); //Image 코드로 뽑아내기 // image 추가
					html ='<input name="last" type="hidden" value="'+imgData+'">';
					$("#approvedForm").append(html);
					alert($("#approvedForm").html());
					var form = document.getElementById('approvedForm');
					// 이미지 넣는 방법 잠깐 고려 해볼것
	//					var userId="${stamp.id}";
	//					var userStamp="${stamp.stamp_image_link}";
	//					var stamp_link="/GWD_LMS_SYS/images/stamp/"+userStamp.substring(userStamp.lastIndexOf('stamp')+5,userStamp.length);
	//					alert(stamp_link);
	//					$('.cke_wysiwyg_frame').contents().find("#stamp"+userId).attr('src','stamp_link');
					// 1) open을 열고 이미지 값 ajax로 수정후 open을 닫는 방법
					// 2) 
					
					form.submit();
				});
	//				alert("test+"+$("#approvedForm").html());
		}		
	}
</script>

</html>