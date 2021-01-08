<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HRD 조회 시스템</title>
</head>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src='<c:url value ="/resources/js/urljs.js"/>'></script>

<body>
<h1><a href="./saveDB.do">HRD DB저장</a></h1>

<h1>HRD 조회 시스템</h1>
<form action="./search.do" method="get">
		<table>
			<thead>
				<tr>
					<td id="keyword">
						<input type="text" id="key" name="key" style="width: 177px;" placeholder="검색어를 입력하세요.">
					</td>
				</tr>
				<tr>
					<td id="area">
						<select class ='area' id="upperAreaCd" name="upperAreaCd" title="훈련지역 시도" style="width: 177px;" onchange="selectArea(this.value)">
								<option value="all">전체</option>
								<option value="서울">서울</option>
								<option value="경기">경기</option>
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
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 						<select class ='date' id="date" name="date" title="날짜" style="width: 177px;"> -->
<!-- 							<option value ="7">1주일</option> -->
<!-- 							<option value ="30">1개월</option> -->
<!-- 							<option value ="90">3개월</option> -->
<!-- 						</select> -->
<!-- 					</td> -->
<!-- 				</tr> -->
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


</body>
</html>