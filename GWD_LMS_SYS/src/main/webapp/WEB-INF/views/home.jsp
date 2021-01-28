<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@include file = "./header.jsp" %>
</head>
<%@include file = "./index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">

<sec:authorize access="isAnonymous()">
<p><a href="<c:url value="/login/loginForm.do" />">로그인</a></p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">

</sec:authorize>


<div class ="home_main_search" id = "home_main_search">
	<input id  = "home_main_search_text" class = "home_main_search_text" type = "text" placeholder="무엇을 찾으세요?"> 
	<input id = "home_main_search_button" class = "home_main_search_button" type = "button" value ="검색" >
</div>

<div class ="home_main_good_Trpr" id ="home_main_good_Trpr">
	<ul class ="home_main_good_Trpr_ul" id ="home_main_good_Trpr_ul">
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="서울" onclick ="clickAddr(11)">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="경기" onclick ="clickAddr(41)">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="인천">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="충청">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="경상">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="전라">
		</li>
		<li class ="home_main_good_Trpr_li">
			<input class ="home_main_good_Trpr_btn" type ="button" value="강원/제주">
		</li>
	</ul>
</div>

<div class ="home_main_Trpr_viewer">
	<div class="tabContents" style="margin-top: 40px; float: left;">
		<section role="tabpanel" id="section1" class="tabCont on">
			<ol >
				<li class ="home_main_Trpr_viewer_li"  ><a id="0"  onclick="doSearch(this.id)">전체</a> </li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="01" onclick="doSearch(this.id)">경영</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="02" onclick="doSearch(this.id)">금융</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="03" onclick="doSearch(this.id)">교육</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="04" onclick="doSearch(this.id)">법률</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="05" onclick="doSearch(this.id)">보건</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="06" onclick="doSearch(this.id)">사회복지</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="07" onclick="doSearch(this.id)">문화</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="16" onclick="doSearch(this.id)">재료</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="17" onclick="doSearch(this.id)">화학</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="18" onclick="doSearch(this.id)">섬유</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="19" onclick="doSearch(this.id)">전기</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="20" onclick="doSearch(this.id)">정보통신</a></li>
				<li class ="home_main_Trpr_viewer_li"  ><a id="21" onclick="doSearch(this.id)">식품</a></li>
			</ol>
		</section>
	</div>
	
	<div style="float: right; text-align: center; width: 1050px;">
		<table class = "table">
			<thead>
				<tr >
					<th style="text-align: center;"> <h2>검색결과</h2></th>
				</tr>
			</thead>
			<tbody class ="home_main_Trpr_viewer_result" id ="home_main_Trpr_viewer_result">
				
			</tbody>
		</table>
	</div>
</div>

<!-- <table class="calendar-table"> -->
<!--     <tbody> -->
<!--         <tr> -->
<!--             <td><div id="calendar-date-ko"></div></td> -->
<!--         </tr> -->
<!--     </tbody> -->
<!-- </table> -->
</div>


</body>
<script type="text/javascript">
var address = ""

function clickAddr(val){
	address =val
}

function doSearch(val){
	if(address==""){
		address = 11;
	}
	
	
	let today = new Date();
	let thismon = today.getMonth()+1
	
	var startDate = today.getFullYear()+"-"+thismon+"-"+today.getDate()
	
	let endmon = today.getMonth()+1+3
	let endday = new Date(today.getFullYear(),endmon, today.getDate() )
	var endDate = endday.getFullYear()+"-"+endmon+"-"+endday.getDate()
	
	$.ajax({
		url :"./search.do",
		method : "get",
		data : {
			"ncs_cd" : val,
			"addr1" : address,
			"addr2" : "",
			"startDate" :startDate ,
			"endDate" : endDate
		
		},
		success : function(data){
			if(Object.keys(data).length == 0){
				$("#home_main_Trpr_viewer_result").empty();
				var html ="<tr><th>결과가 없습니다.</th></tr>"
				$("#home_main_Trpr_viewer_result").append(html);

			}else{
				$.each(data, function(key, value){
					$("#home_main_Trpr_viewer_result").empty();
					if(key == "info"){
						var list = value;
						$.each(list, function(k, v){
							console.log(v);
							var html = "";
	////					ino_nm, ti.trpr_nm, ti.tra_start_date, ti.tra_end_date, ti.trtm, ti.trpr_degr
	////					교육기관명, 교육과정명, 교육시작일, 교육종료일, 교육 시간, 회차정보
							
								html +=	"<tr id='resultView'> "
								html +=	"<th class='resultViewdiv'> "
								html += "<h3><a href='./hrdDetailTrainst.do?trpr_id="+v.trpr_id+"&trpr_degr="+v.trpr_degr+"&trainst_cst_id="+v.trainst_cst_id+"'>"+v.ino_nm+"</a></h3> " 
								html += "</th> " 
								html += "<th class='resultViewdiv ViewTrainst'> " 
								html += "<h4><a href='./hrdDetailTrpr.do?trpr_id="+v.trpr_id+"&trpr_degr="+v.trpr_degr+"'>"+v.trpr_nm+"</a></h4> " 
								html += "<h5>"+v.tra_start_date+" ~ "+v.tra_end_date+" ("+v.trtm+"시간 & "+v.trpr_degr+"회차)</h5> " 
								html += "</th> " 
								html += "</tr> " 
								$("#home_main_Trpr_viewer_result").append(html);
							
						});
					}
				});
			}
		},
		error: function(){
			"home_Search Ajax has a problem"
		}
	})
}
window.onload = function(){
	var DatePicker = tui.DatePicker;

	DatePicker.localeTexts['ko'] = {
	    titles: {
	        DD: ['일', '월', '화', '수', '목', '금', '토'],
	        D: ['일', '월', '화', '수', '목', '금', '토'],
	        MMM: ['1', '2', '3', '4', '5', '6',
	            '7', '8', '9', '10', '11', '12'],
	        MMMM: ['1월', '2월', '3월', '4월', '5월', '6월',
	            '7월', '8월', '9월', '10월', '11월', '12월']
	    },
	    titleFormat: 'yyyy년 MMM월',
	    todayFormat: '오늘 : yyyy. MMM. d (DD)'
	};
// 	var calDateOptional = DatePicker.createCalendar('#calendar-date-ko', {language: 'ko'});
}
function msgSender(val){
	alert(websocket)
	websocket.send("STUDENT11"+"*"+val+"*");
	
	
}
</script>

</html>



