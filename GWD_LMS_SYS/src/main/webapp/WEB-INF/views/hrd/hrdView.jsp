<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file = "../header.jsp" %>

<script type="text/javascript" src='<c:url value ="/resources/js/hrdView.js"/>'></script>

</head>
<%@include file = "../index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">

<img alt="bar" src="./images/hrd/bar.png"><h3>교육과정 검색</h3>
<form action="./search.do" method="get">
		<table>
			<thead>
				<tr>
					<td id="area">
						<select class ='area' id="upperAreaCd" name="upperAreaCd" title="훈련지역 시도" style="width: 177px;" onchange="selectArea(this.value)">
								<option value="0">전체</option>
								<option value="11">서울</option>
								<option value="41">경기</option>
						</select>
					</td>
				</tr>
				<tr>
					<td id="work">
						<select class ='upperNcsCd' id="upperNcsCd" name="upperNcsCd" title="NCS대분류" style="width: 177px;">
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
				</tr>
				<tr>
					<td>
						<input type = "date" id="startDate"><input type = "date" id="endDate">
						
						<!-- <select class ='date' id="date" name="date" title="날짜" style="width: 177px;">
							<option value ="90">전체</option>
							<option value ="7">1주일</option>
							<option value ="30">1개월</option>
							<option value ="60">2개월</option>
						</select> -->
						
					</td>
				</tr>
				<tr>
					<td>
						<input type="radio" checked="checked" name="keywordType" id="keywordType1" value="1">
						<label for="keywordType1" class="on">키워드</label>
						<input type="radio" name="keywordType" id="keywordType2" value="2">
						<label for="keywordType2" class="on">기관 검색</label>
						<input type="radio" name="keywordType" id="keywordType3" value="3">
						<label for="keywordType3" class="on">과정 검색</label>
					</td>
				</tr>
				<tr>
					<td id="keyword1">
						<input type="text" id="key1" class="searchKey" style="width: 177px;" placeholder="기관명 또는 과정명을 입력하세요.">
					</td>
					<td id="keyword2" style="display: none;">
						<input type="text" id="key2" class="searchKey" style="width: 177px;" placeholder="기관명을 입력하세요.">
					</td>
					<td id="keyword3" style="display: none;">
						<input type="text" id="key3" class="searchKey" style="width: 177px;" placeholder="과정명을 입력하세요.">
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type ="button" onclick = "runajax()" value ="검색">
						<input type = "reset" value ="초기화"> 
					</td>
				</tr>
			</tbody>
		</table>
	</form>

<table id = "resulttable">
	<thead>
		<tr>
			<th>교육기관명</th> <th>교육과정명</th>
			<th>교육시작일</th> <th>교육종료일</th>
			<th>총 교육시간</th> <th>회차정보</th>
			<th>즐겨찾기 여부</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>
</body>
<script type="text/javascript">
	$('.searchKey').keydown(function() {
	  if (event.keyCode === 13) {
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