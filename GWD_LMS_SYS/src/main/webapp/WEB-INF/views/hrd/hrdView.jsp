<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file = "../header.jsp" %>

<script type="text/javascript" src='<c:url value ="/resources/js/hrdView.js"/>'></script>

</head>
<%@include file = "../index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">
	<div id = "viewheader">
		<img alt="bar" src="./images/hrd/bar.png"><h3>교육과정 검색</h3>
	</div>
	
	<div id="hrdViewContainer">
		<div id="innerHrdViewContainer">
			<form action="./search.do" method="get">
				<div class="form-group">
					<table>
						<thead>
							<tr class = "searchMove">
								<td id="keywordType1">키워드 검색</td>
								<td id="keywordType2">교육기관 검색</td>
								<td id="keywordType3">교육과정 검색</td>
							</tr>
						</thead>
						<tbody>
							<tr id="keyarea">
								<td colspan="3" id="keyword1">
									<input type="text" id="keyVal" value="1" style="display: none;">
									<input type="text" id="key1" class="searchKey form-control" placeholder="기관명 또는 과정명을 입력하세요.">
								</td>
							</tr>
							<tr>
								<td><h5>지역 선택</h5></td>
								<td>
									<select class ='area form-control' id="upperAreaCd" name="upperAreaCd" title="훈련지역 시도" onchange="selectArea(this.value)">
											<option value="0">전체</option>
											<option value="11">서울</option>
											<option value="41">경기</option>
									</select>
								</td>
								<td id="area">
								
								</td>
							</tr>
							<tr>
								<td><h5>직종 선택</h5></td>
								<td>
									<select class ='upperNcsCd form-control' id="upperNcsCd" name="upperNcsCd" title="NCS대분류" onchange="selectNcs(this.value)">
										<option value="0">전체</option>
										<option value="01">사업관리</option>
										<option value="02">경영·회계·사무</option>
										<option value="03">금융·보험</option>
										<option value="04">교육·자연·사회과학</option>
										<option value="05">법률·경찰·소방·교도·국방</option>
										<option value="06">보건·의료</option>
										<option value="07">사회복지·종교</option>
										<option value="08">문화·예술·디자인·방송</option>
										<option value="09">운전·운송</option>
										<option value="10">영업판매</option>
										<option value="11">경비·청소</option>
										<option value="12">이용·숙박·여행·오락·스포츠</option>
										<option value="13">음식서비스</option>
										<option value="14">건설</option>
										<option value="15">기계</option>
										<option value="16">재료</option>
										<option value="17">화학</option>
										<option value="18">섬유·의복</option>
										<option value="19">전기·전자</option>
										<option value="20">정보통신</option>
										<option value="21">식품가공</option>
										<option value="22">인쇄·목재·가구·공예</option>
										<option value="23">환경·에너지·안전</option>
										<option value="24">농림어업</option>
									</select>
								</td>
								<td id="work">
								</td>	
							</tr>
							<tr>
								<td><h5>날짜 선택</h5></td>
								<td>
									<input type="date" id="startDate" class="form-control">
								</td>	
								<td>
									<input type="date" id="endDate" class="form-control">
								</td>
							</tr>
							<tr>
								<td colspan="3" style="text-align: center;">
									<input type="reset" class="btn btn-default" value="초기화"> 
									<input type="button" class="btn btn-success" onclick="runajax()" value="검색">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</form>
			
				<div id="resultViewList">
				</div>
				
				
	<!-- 		<table id = "resulttable"> -->
	<!-- 			<thead> -->
	<!-- 				<tr> -->
	<!-- 					<th>교육기관명</th> <th>교육과정명</th> -->
	<!-- 					<th>교육시작일</th> <th>교육종료일</th> -->
	<!-- 					<th>총 교육시간</th> <th>회차정보</th> -->
	<!-- 					<th>즐겨찾기 여부</th> -->
	<!-- 				</tr> -->
	<!-- 			</thead> -->
	<!-- 			<tbody> -->
	<!-- 			</tbody> -->
	<!-- 		</table> -->
	
	
	<!-- 			<div id="resultView"> -->
	<!-- 				<div class="resultViewdiv"> -->
	<!-- 				<h3>교육기관명</h3> -->
	<!-- 				</div> -->
	<!-- 				<div class="resultViewdiv ViewTrainst"> -->
	<!-- 				<h2>교육과정명-----------</h2> -->
	<!-- 				<h5>2020.12.23 ~ 2021.06.23 (1000시간)</h5> -->
	<!-- 				</div> -->
	<!-- 				<div class="resultViewdiv resultBmk"> -->
	<!-- 				<h2>즐겨찾기</h2> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
			</div>
		</div>
</div>
</body>
<script type="text/javascript">
	$('#keyarea').keydown(function() {
	  if (event.keyCode == 13) {
	    event.preventDefault();		//form tag submit막기
		runajax();
	  };
	});
	
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	 if(dd<10){
	        dd='0'+dd
	    } 
	    if(mm<10){
	        mm='0'+mm
	    } 
	
	today = yyyy+'-'+mm+'-'+dd;
	document.getElementById("startDate").setAttribute("min", today);
	document.getElementById("endDate").setAttribute("min", today);
	
	var d = new Date(); //현재시간 문자열로 반환
	var dayOfMonth = d.getDate();	//주어진 날짜의 일자를 반환 (1이상 31이하의 정수, ex. 1월 14일인 경우 14일을 반환)
// 	console.log(dayOfMonth);
	d.setDate(dayOfMonth+90);		//현재일자 + 선택 기간
	var maxdate = getFormatDate(d); //getFormatDate: yyyyMMdd 포맷으로 반환
// 	console.log(maxdate);
	
	var mdd = maxdate.substring(0, 4);
	var mmm = maxdate.substring(4, 6);
	var myyyy = maxdate.substring(6);
	
	maxdate = mdd+"-"+mmm+"-"+myyyy;
	
	document.getElementById("startDate").setAttribute("max", maxdate);
	document.getElementById("endDate").setAttribute("max", maxdate);
	document.getElementById("endDate").setAttribute("value", maxdate);
	document.getElementById('startDate').setAttribute("value", today);
	

</script>
</html>