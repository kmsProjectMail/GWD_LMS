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
		<img alt="bar" src="./images/hrd/bar.png"><h3>교육과정 상세조회</h3>
	</div>
<!-- <h1>과정정보 vo</h1> -->

	<div id="hrdViewContainer">
		<div id="innerHrdViewContainer">
				<div class="form-group">
					<table>
						<thead>
							<tr class = "detailMove">
								<td style="background: white;">교육과정 정보</td>
								<td onclick="location.href='./hrdDetailTrainst.do?trpr_id=${TrprVo.trpr_id}&trpr_degr=${TrprVo.trpr_degr}&trainst_cst_id=${TrprVo.trainst_cst_id}'">교육기관 정보</td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div id="detailView">
					<div class="resultViewdiv ViewNamee">
					<h3>${TrprVo.ino_nm}</h3>
					</div>
					
					<c:choose>
						<c:when test="${userInfo.auth ne 'ROLE_STUDENT'}">
							<div class="resultViewdiv ViewTrainst1">
							<h4>${TrprVo.trpr_nm}</h4>
							<h5>
							<fmt:formatDate value="${TrprVo.tra_start_date}" var="startDate" pattern="yyyy-MM-dd"/>
							<fmt:formatDate value="${TrprVo.tra_end_date}" var="endDate" pattern="yyyy-MM-dd"/>
							${startDate} ~ ${endDate} (${TrprVo.trtm}시간)</h5>
							</div>
						</c:when>
					
						<c:when test="${userInfo.auth eq 'ROLE_STUDENT'}">
							<div class="resultViewdiv ViewTrainst3">
							<h4>${TrprVo.trpr_nm}</h4>
							<h5>
							<fmt:formatDate value="${TrprVo.tra_start_date}" var="startDate" pattern="yyyy-MM-dd"/>
							<fmt:formatDate value="${TrprVo.tra_end_date}" var="endDate" pattern="yyyy-MM-dd"/>
							${startDate} ~ ${endDate} (${TrprVo.trtm}시간)</h5>
							</div>
						</c:when>	
					</c:choose>
					
				</div>
				
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
					${TrprVo.trpr_overview}
					<c:if test="${TrprVo.trpr_overview eq null}">
						<p style="color: red">등록된 교육목표가 없습니다.</p>
					</c:if>
					<h4>교육 교재</h4>
					${TrprVo.trpr_book}
					<c:if test="${TrprVo.trpr_book eq null}">
						<p style="color: red">등록된 교재정보가 없습니다.</p>
					</c:if>
					<h4>교육 강사</h4>
					${TrprVo.trpr_teacher}
					<c:if test="${TrprVo.trpr_teacher eq null}">
						<p style="color: red">등록된 강사정보가 없습니다.</p>
					</c:if>
				
					<c:if test="${userInfo.auth eq 'ROLE_ACADEMY'}">
						<div class="buttonDiv">
							<input type="button" value="과정정보 수정하기" class="btn btn-success" onclick="location.href='./trprUpdate.do'">
						</div>
					</c:if>				
				</div>
				
				
			</div>
		</div>

</div>
</body>
</html>