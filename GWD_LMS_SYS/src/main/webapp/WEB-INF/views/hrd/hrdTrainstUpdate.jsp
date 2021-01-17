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

	<div id="hrdViewContainer">
		<div id="innerHrdViewContainer">
				<div id="detailView">
					<div class="resultViewdiv trainstName">
					<h3>${TrprVo.ino_nm}</h3>
					</div>
				</div>
				
			<form action="./trainstModify.do?${_csrf.parameterName}=${_csrf.token}" method="post" name="frm" enctype="multipart/form-data">
				<div id="trprDetailInfo">
					<p>주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소:&nbsp; ${trainstVo.addr1} ${trainstVo.addr2}</p>
					<p>전화번호:&nbsp; ${trainstVo.tel_no}</p>
					<p>홈페이지:&nbsp; ${trainstVo.hp_addr}</p>
					<p>담&nbsp;&nbsp;당&nbsp;&nbsp;자:&nbsp; ${TrprVo.trpr_chap_tel}</p>
					<c:if test="${TrprVo.trpr_chap_tel eq null}">
						<p style="color: red">등록된 담당자가 없습니다.</p>
					</c:if>
					
					<h3>교육기관</h3>
					<h4>교육기관 소개</h4>
					<c:choose>
						<c:when test="${trainstVo.trainst_intro ne null}">
							<textarea class="form-control" rows="5" placeholder="${trainstVo.trainst_intro}" name="trainst_intro"></textarea>
						</c:when>
						<c:when test="${trainstVo.trainst_intro eq null}">
							<textarea class="form-control" rows="5" placeholder="기관 소개를 등록해주세요." name="trainst_intro"></textarea>
						</c:when>
					</c:choose>
					
					<h4>교육기관 사진</h4>
					<c:choose>
						<c:when test="${trainstVo.trainst_photo ne null}">
							<textarea class="form-control" rows="5" placeholder="${trainstVo.trainst_photo}" name="trainst_photo"></textarea>
						</c:when>
						<c:when test="${trainstVo.trainst_photo eq null}">
							<textarea class="form-control" rows="5" placeholder="기관 사진을 등록해주세요." name="trainst_photo"></textarea>
						</c:when>
					</c:choose>
					
					<h4>교육기관 동영상</h4>
					<c:choose>
						<c:when test="${trainstVo.trainst_video ne null}">
							<input type="text" class="form-control" placeholder="${trainstVo.trainst_video}" name="trainst_video">
						</c:when>
						<c:when test="${trainstVo.trainst_video eq null}">
							<input type="text" class="form-control" placeholder="동영상을 등록해주세요." name="trainst_video">
						</c:when>
					</c:choose>
						
					<div class="buttonDiv">
						<input type="button" value="수정취소" class="btn btn-success" onclick="history.back(-1)">
						<input type="reset" value="초 기 화" class="btn btn-success">
						<input type="submit" value="수정완료" class="btn btn-success">
					</div>
					
				</div>
			</form>
		</div>
	</div>

</div>
</body>
</html>