<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file = "../header.jsp" %>


</head>
<%@include file = "../index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">
	<div id = "viewheader">
		<img alt="bar" src="./images/hrd/bar.png"><h3>우리 학원 과정정보 수정</h3>
	</div>
<!-- <h1>과정정보 vo</h1> -->
<%-- ${TrprVo} --%>

	<div id="hrdViewContainer">
		<div id="innerHrdViewContainer">
				<div id="detailView">
					<div class="resultViewdiv ViewNamee">
					<h3>${TrprVo.ino_nm}</h3>
					</div>
					<div class="resultViewdiv updateView">
					<h4>${TrprVo.trpr_nm}</h4>
					<h5>
					<fmt:formatDate value="${TrprVo.tra_start_date}" var="startDate" pattern="yyyy-MM-dd"/>
					<fmt:formatDate value="${TrprVo.tra_end_date}" var="endDate" pattern="yyyy-MM-dd"/>
					${startDate} ~ ${endDate} (${TrprVo.trtm}시간)</h5>
					</div>
				</div>
				
				<form action="./trprModify.do?${_csrf.parameterName}=${_csrf.token}" method="post" name="frm" enctype="multipart/form-data">
					<div id="trprDetailInfo">
						<p>회차 정보:&nbsp; ${TrprVo.trpr_degr}회차</p>
						<p>NCS직종:&nbsp; [${TrprVo.ncs_cd}] ${TrprVo.ncs_nm}</p>
						<p>교육 기간:&nbsp; ${startDate} ~ ${endDate}</p>
						<p>교육 시간:&nbsp; ${TrprVo.trtm}시간</p>
						<p>수강 상담:&nbsp; ${TrprVo.trpr_chap_tel}</p>
						<c:if test="${TrprVo.trpr_chap_tel eq null}">
							<p style="color: red">등록된 담당자가 없습니다.</p>
						</c:if>
						
						<h3>교육과정 개요</h3>
						<h4>교육 목표</h4>
						<c:choose>
							<c:when test="${TrprVo.trpr_overview ne null}">
								<textarea class="form-control" rows="5" placeholder="${TrprVo.trpr_overview}" name="trpr_overview"></textarea>
							</c:when>
							<c:when test="${TrprVo.trpr_overview eq null}">
								<textarea class="form-control" rows="5" placeholder="교육목표를 등록해주세요." name="trpr_overview"></textarea>
							</c:when>
						</c:choose>
						
						<h4>교육 교재</h4>
						<c:choose>
							<c:when test="${TrprVo.trpr_book ne null}">
								<textarea class="form-control" rows="5" placeholder="${TrprVo.trpr_book}" name="trpr_book"></textarea>
							</c:when>
							<c:when test="${TrprVo.trpr_book eq null}">
								<textarea class="form-control" rows="5" placeholder="교재정보를 등록해주세요." name="trpr_book"></textarea>
							</c:when>
						</c:choose>
						
						<h4>교육 강사</h4>
						<c:choose>
							<c:when test="${TrprVo.trpr_teacher ne null}">
								<input type="text" class="form-control" placeholder="${TrprVo.trpr_teacher}" name="trpr_teacher">
							</c:when>
							<c:when test="${TrprVo.trpr_teacher eq null}">
								<input type="text" class="form-control" placeholder="강사정보를 등록해주세요." name="trpr_teacher">
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