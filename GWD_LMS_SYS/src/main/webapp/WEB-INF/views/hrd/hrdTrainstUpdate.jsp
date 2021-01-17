<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file = "../header.jsp" %>

<script type="text/javascript" src='<c:url value ="/resources/js/hrdDetailView.js"/>'></script>

</head>
<%@include file = "../index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">
	<div id = "viewheader">
		<img alt="bar" src="./images/hrd/bar.png"><h3>우리 학원정보 수정</h3>
	</div>
<!-- <h1>과정정보 vo</h1> -->
<%-- ${TrprVo} --%>

	<div id="hrdViewContainer">
		<div id="innerHrdViewContainer">
				<div id="detailView">
					<div class="resultViewdiv trainstName">
					<h3>${TrprVo.ino_nm}</h3>
					</div>
				</div>
				
				<div id="trprDetailInfo">
					<p>주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소:&nbsp; ${trainstVo.addr1} ${trainstVo.addr2}</p>
					<p>전화번호:&nbsp; ${trainstVo.tel_no}</p>
					<p>홈페이지:&nbsp; ${trainstVo.hp_addr}</p>
					<p>담&nbsp;&nbsp;당&nbsp;&nbsp;자:&nbsp; ${TrprVo.trpr_chap_tel}</p>
					<c:if test="${TrprVo.trpr_chap_tel eq null}">
						<p style="color: red">등록된 담당자가 없습니다.</p>
					</c:if>
					
					<h3>교육기관</h3>
					<p>교육기관 소개</p>
					${trainstVo.trainst_intro}
					<c:if test="${trainstVo.trainst_intro eq null}">
						<p style="color: red">등록된 소개가 없습니다.</p>
					</c:if>
					<p>교육기관 사진</p>
					${trainstVo.trainst_photo}
					<c:if test="${trainstVo.trainst_photo eq null}">
						<p style="color: red">등록된 사진이 없습니다.</p>
					</c:if>
					<p>교육기관 동영상</p>
					${trainstVo.trainst_video}
					<c:if test="${trainstVo.trainst_video eq null}">
						<p style="color: red">등록된 동영상이 없습니다.</p>
					</c:if>
					
					<input type="button" value="수정취소" class="btn btn-success" onclick="./trprUpdate.do?trpr_id=${TrprVo.trpr_id}&trpr_degr=${TrprVo.trpr_degr}&trainst_cst_id=${TrprVo.trainst_cst_id}">
					<input type="button" value="초 기 화" class="btn btn-success" onclick="./trprUpdate.do?trpr_id=${TrprVo.trpr_id}&trpr_degr=${TrprVo.trpr_degr}&trainst_cst_id=${TrprVo.trainst_cst_id}">
					<input type="button" value="수정완료" class="btn btn-success" onclick="./trprUpdate.do?trpr_id=${TrprVo.trpr_id}&trpr_degr=${TrprVo.trpr_degr}&trainst_cst_id=${TrprVo.trainst_cst_id}">
				
			</div>
		</div>

</div>
</body>
</html>