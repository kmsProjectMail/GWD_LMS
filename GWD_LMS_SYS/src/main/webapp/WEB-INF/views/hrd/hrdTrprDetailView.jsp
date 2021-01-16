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
		<img alt="bar" src="./images/hrd/bar.png"><h3>교육과정 상세조회</h3>
	</div>
<!-- <h1>과정정보 vo</h1> -->
<%-- ${TrprVo} --%>

	<div id="hrdViewContainer">
		<div id="innerHrdViewContainer">
			<form action="./trprUpdate.do" method="get">
				<div class="form-group">
					<table>
						<thead>
							<tr class = "detailMove">
								<td style="background: white;"><a href="./hrdDetailTrpr.do">교육과정 정보</a></td>
								<td><a href="./hrdDetailTrpr.do">교육기관 정보</a></td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div id="detailView">
					<div class="resultViewdiv">
					<h3>${TrprVo.ino_nm}</h3>
					</div>
					<div class="resultViewdiv ViewTrainst">
					<h4>${TrprVo.trpr_nm}</h4>
					<h5>
					<fmt:formatDate value="${TrprVo.tra_start_date}" var="startDate" pattern="yyyy-MM-dd"/>
					<fmt:formatDate value="${TrprVo.tra_end_date}" var="endDate" pattern="yyyy-MM-dd"/>
					${startDate} ~ ${endDate} (${TrprVo.trtm}시간)</h5>
					</div>
					<div class="resultViewdiv resultBmk">
					<h3>즐겨찾기</h3>
					</div>
				</div>
				<div>
				
					<p>회차 정보 ${TrprVo.trpr_degr}</p>
					<p>NCS직종 [${TrprVo.ncs_cd}] ${TrprVo.ncs_nm}</p>
					<p>교육 기간 ${startDate} ~ ${endDate}</p>
					<p>교육 시간 ${TrprVo.trtm}시간</p>
					<p>수강 상담</p>
					
					
					<h3>교육과정 개요</h3>
					<p>교육 목표</p>
					<c:if test="${TrprVo.trpr_overview eq null}">
						<p style="color: red">등록된 교육목표가 없습니다.</p>
					</c:if>
					<p>교육 교재</p>
					<c:if test="${TrprVo.trpr_book eq null}">
						<p style="color: red">등록된 교재정보가 없습니다.</p>
					</c:if>
					<p>교육 강사</p>
					<c:if test="${TrprVo.trpr_teacher eq null}">
						<p style="color: red">등록된 강사정보가 없습니다.</p>
					</c:if>
				</div>
				</form>
				
			</div>
		</div>

</div>
</body>
</html>