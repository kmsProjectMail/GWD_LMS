<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file = "../header.jsp" %>

</head>
<%@include file = "../index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">
	<div id = "viewheader">
		<img alt="bar" src="./images/hrd/bar.png"><h3>내 즐겨찾기 목록</h3>
	</div>
	
	<div id="hrdViewContainer">
		<div id="innerHrdViewContainer">
		
			<div id="resultViewList">
			
			<c:if test="${lists eq null}">
				<h4 style="color: red; text-align: center; padding: 20px;">즐겨찾기 목록이 존재하지 않습니다.</h4>
			</c:if>
			
			<c:forEach var="vo" items="${lists}">
				<div id="resultView">
					<div class="resultViewdiv ViewNamee">
					<h3><a href="./hrdDetailTrainst.do?trpr_id=${vo.trpr_id}&trpr_degr=${vo.trpr_degr}&trainst_cst_id=${vo.trainst_cst_id}">${vo.ino_nm}</a></h3>
					</div>
					<div class='resultViewdiv ViewTrainst3'>
					<h4><a href="./hrdDetailTrpr.do?trpr_id=${vo.trpr_id}&trpr_degr=${vo.trpr_degr}&trainst_cst_id=${vo.trainst_cst_id}">${vo.trpr_nm}</a></h4>
					<h5>${vo.tra_start_date} ~ ${vo.tra_end_date} (${vo.trtm} 시간 & ${vo.trpr_degr}회차)</h5>
					</div>
				</div>
			</c:forEach>
			
			</div>
		</div>
	</div>
</div>
</body>
</html>