<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file = "../header.jsp" %>

</head>
<%@include file = "../index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">
<div id = "viewheader">
		<img alt="bar" src="./images/hrd/bar.png"><h3>교육기관 상세조회</h3>
	</div>

	<div id="hrdViewContainer">
		<div id="innerHrdViewContainer">
				<div class="form-group">
					<table>
						<thead>
							<tr class = "detailMove">
								<td onclick="location.href='./hrdDetailTrpr.do?trpr_id=${TrprVo.trpr_id}&trpr_degr=${TrprVo.trpr_degr}&trainst_cst_id=${TrprVo.trainst_cst_id}'">교육과정 정보</td>
								<td style="background: white;">교육기관 정보</td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
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
					<h4>교육기관 소개</h4>
					${trainstVo.trainst_intro}
					<c:if test="${trainstVo.trainst_intro eq null}">
						<p style="color: red">등록된 소개가 없습니다.</p>
					</c:if>
					<h4>교육기관 사진</h4>
					${trainstVo.trainst_photo}
					<c:if test="${trainstVo.trainst_photo eq null}">
						<p style="color: red">등록된 사진이 없습니다.</p>
					</c:if>
					<h4>교육기관 동영상</h4>
					${trainstVo.trainst_video}
					<c:if test="${trainstVo.trainst_video eq null}">
						<p style="color: red">등록된 동영상이 없습니다.</p>
					</c:if>
				
				<c:if test="${userInfo.auth eq 'ROLE_ACADEMY'}">
					<div class="buttonDiv">
						<input type="button" value="기관정보 수정하기" class="btn btn-success" onclick="location.href='./trainstUpdate.do'">
					</div>
				</c:if>				
				
				<h3>시설정보 목록</h3>
				<%-- ${facilVo} --%>
					<table class="trainstTable">
						<tr>
							<th>시설명</th><th>시설 수</th>
							<th>시설면적</th><th>수용 인원</th>
						</tr>	
						<c:forEach var="vo" items="${facilVo}">
						<tr>
							<td>
							<c:set var = "length" value="${fn:length(vo.trafclty_nm)}"/>
							${fn:substring(vo.trafclty_nm, 1, length-1)}
							</td>
							<td>
							<c:set var = "length" value="${fn:length(vo.hold_qy)}"/>
							<!-- 값이 없을 경우 ""가 입력되어있음 -->
							<c:if test="${length == 2}">
							 -
							</c:if>
							<c:if test="${length != 2}">
							${fn:substring(vo.hold_qy, 1, length-1)}개
							</c:if>
							</td>
							<td>
							<c:set var = "length" value="${fn:length(vo.fclty_ar_cn)}"/>
							<c:if test="${length == 2}">
							 -
							</c:if>
							<c:if test="${length != 2}">
							${fn:substring(vo.fclty_ar_cn, 1, length-1)}㎡
							</c:if>
							</td>
							<td>
							<c:set var = "length" value="${fn:length(vo.ocu_acptn_cn)}"/>
							<c:if test="${length == 2}">
							 -
							</c:if>
							<c:if test="${length != 2}">
							${fn:substring(vo.ocu_acptn_cn, 1, length-1)}명
							</c:if>
							</td>
						</tr>
						</c:forEach>
					</table>
					
				<h3>장비정보 목록</h3>
<%-- 				${eqmnVo} --%>
					<table class="trainstTable">
						<tr>
							<th>장비명</th><th>보유 수량</th>
						</tr>	
						<c:forEach var="vo" items="${eqmnVo}">
						<tr>
							<td>
							<c:set var = "length" value="${fn:length(vo.eqpmn_nm)}"/>
							${fn:substring(vo.eqpmn_nm, 1, length-1)}
							</td>
							<td>
							<c:set var = "length" value="${fn:length(vo.hold_qy)}"/>
							<!-- 값이 없을 경우 ""가 입력되어있음 -->
							<c:if test="${length == 2}">
							 -
							</c:if>
							<c:if test="${length != 2}">
							${fn:substring(vo.hold_qy, 1, length-1)}개
							</c:if>
							</td>
						</tr>
						</c:forEach>
					</table>
					
					
					
					</div>		
				</div>		
			</div>
		</div>

</body>
</html>