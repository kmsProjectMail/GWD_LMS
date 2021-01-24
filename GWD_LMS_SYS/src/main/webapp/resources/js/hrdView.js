$(document).ready(function(){
	
	$("#keywordType1").click(function(){
//		alert('작동1');
		$("#keywordType1").css("background", "white");
		$("#keywordType2").css("background", "lightgray");
		$("#keywordType3").css("background", "lightgray");
		$("#keyarea").html("");
		var html = "";
		html += '<td colspan="3" id="keyword1">';
		html += '<input type="text" id="keyVal" value="1" style="display: none;">';
		html += '<input type="text" id="key1" class="searchKey form-control" placeholder="기관명 또는 과정명을 입력하세요." style="background-image: url(\'./images/hrd/Search.png\'); background-repeat: no-repeat;">';
		html += '</td>';
		$("#keyarea").append(html);
	})
	$("#keywordType2").click(function(){
//		alert('작동1');
		$("#keywordType1").css("background", "lightgray");
		$("#keywordType2").css("background", "white");
		$("#keywordType3").css("background", "lightgray");
		$("#keyarea").html("");
		var html = "";
		html += '<td colspan="3" id="keyword2">';
		html += '<input type="text" id="keyVal" value="2" style="display: none;">';
		html += '<input type="text" id="key2" class="searchKey form-control" placeholder="기관명을 입력하세요." style="background-image: url(\'./images/hrd/Search.png\'); background-repeat: no-repeat;">';
		html += '</td>';
		$("#keyarea").append(html);
	})
	$("#keywordType3").click(function(){
//		alert('작동1');
		$("#keywordType1").css("background", "lightgray");
		$("#keywordType2").css("background", "lightgray");
		$("#keywordType3").css("background", "white");
		$("#keyarea").html("");
		var html = "";
		html += '<td colspan="3" id="keyword3">';
		html += '<input type="text" id="keyVal" value="3" style="display: none;">';
		html += '<input type="text" id="key3" class="searchKey form-control" placeholder="과정명을 입력하세요." style="background-image: url(\'./images/hrd/Search.png\'); background-repeat: no-repeat;">';
		html += '</td>';
		$("#keyarea").append(html);
	})
	
	$("#startDate").change(function(){
		var sd = $('#startDate').val();
		var temp = sd;
		var ed = $('#endDate').val();
		if(sd>ed){
			alert("기간 범위를 잘못 선택하셨습니다. 다시 선택해주세요.");
			$('#startDate').val(ed);
		}
	})
	$("#endDate").click(function(){
		var sd = $('#startDate').val();
		var ed = $('#endDate').val();
		if(sd>ed){
			alert("기간 범위를 잘못 선택하셨습니다. 다시 선택해주세요.");
			$('#endDate').val(sd);
		}
	})
});



function runajax(){ //버튼을 눌러서 검색
	
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	
//	var startDate = startDate1.substring(0, 4)+startDate1.substring(5, 7)+startDate1.substring(8, 10);
//	var endDate = endDate1.substring(0, 4)+endDate1.substring(5, 7)+endDate1.substring(8, 10);
	
//	console.log("startDate?"+startDate);
//	console.log("endDate?"+endDate);
	
	
//	var date = Number(document.getElementById("date").value);	//hrdView.jsp에서 기간 선택
//	var d = new Date(); //현재시간 문자열로 반환
//	var dayOfMonth = d.getDate();	//주어진 날짜의 일자를 반환 (1이상 31이하의 정수, ex. 1월 14일인 경우 14일을 반환)
	
//	var dd = new Date(2021, 1, 14); // 월을 나타내는 0 ~ 11까지의 정수 (주의: 0부터 시작, 0 = 1월)
	
//	console.log(dayOfMonth);
//	d.setDate(dayOfMonth+date);		//현재일자 + 선택 기간
//	console.log(getFormatDate(d));	//getFormatDate: yyyyMMdd 포맷으로 반환
	
//	url += "srchTraStDt="+getFormatDate(new Date())+"&"	//훈련시작일 From		//검색기간
//	url += "srchTraEndDt="+getFormatDate(d)+"&"			//훈련시작일 To			//검색기간
	
	
	var srchTraArea1 = document.getElementById("upperAreaCd").value; //훈련지역 대분류
	if(srchTraArea1 != '0'){
		var srchTraArea2 = document.getElementById("areaCd").value; //훈련지역 중분류
		
		if(srchTraArea2 == ''){
			var srchTraArea2 = '';
		}
	}
	
	var srchKeco1 = document.getElementById("upperNcsCd").value;
	if(srchKeco1 != '0'){
		var srchKeco2 = document.getElementById("ncsCd").value;
		if(srchKeco2 != '0'){
			srchKeco1 += srchKeco2;
		}
	}
	
	if($("#keyVal").val() == "1"){
		var keyword = document.getElementById("key1").value;
		$.ajax({
			type: "get",
			url: "./search.do",
			data:{ 	
				"addr1" : srchTraArea1,
				"addr2" : srchTraArea2,
				"ncs_cd" : srchKeco1,
				"trpr_nm": keyword,
				"startDate": startDate,
				"endDate": endDate
			},
			dataType: "json",
			success: function(data){
				$.each(data, function(key, value){
					$("#resultViewList").empty();
					if(key == "info"){
						var list = value;
						if(list == ""){
							var html = "<h4 style='color: red; text-align: center; padding: 20px;'>검색결과가 존재하지 않습니다.<br> 다른 조건으로 검색해주세요.<h4>";
							$("#resultViewList").append(html);	
						}else{
							$.each(list, function(k, v){
								console.log(v);
								var html = "";
								
									html +=	"<div id='resultView'> "
									html +=	"<div class='resultViewdiv ViewNamee'> "
									html += "<h3><a href='./hrdDetailTrainst.do?trpr_id="+v.trpr_id+"&trpr_degr="+v.trpr_degr+"&trainst_cst_id="+v.trainst_cst_id+"'>"+v.ino_nm+"</a></h3> " 
									html += "</div> " 
									html += "<div class='resultViewdiv ViewTrainst2'> " 
									html += "<h4><a href='./hrdDetailTrpr.do?trpr_id="+v.trpr_id+"&trpr_degr="+v.trpr_degr+"&trainst_cst_id="+v.trainst_cst_id+"'>"+v.trpr_nm+"</a></h4> " 
									html += "<h5>"+v.tra_start_date+" ~ "+v.tra_end_date+" ("+v.trtm+"시간 & "+v.trpr_degr+"회차)</h5> " 
									html += "</div> " 
									html += "<div class='resultViewdiv resultBmk'> " 
									html += "<h3>즐겨찾기</h3> " 
									html += "</div> " 
									html += "</div> " 
									$("#resultViewList").append(html);	
							});
						}
					}
				});
			} ,
			error:function(){
				alert("몬가... 잘못됐어...")
			}
		})
	swal("검색 완료","잠시만 기다리세요.");
		
	}else if($("#keyVal").val() == "2"){
		var keyword = document.getElementById("key2").value;
		swal("개발중","키워드검색을 이용하세요.");
		
	}else if($("#keyVal").val() == "3"){
		var keyword = document.getElementById("key3").value;
		swal("개발중","키워드검색을 이용하세요.");
	}
	
	
//	alert("검색 완료!");
	
	return false;
}

function getFormatDate(date){
 var year = date.getFullYear(); //yyyy
 var month = (1 + date.getMonth()); //M
 month = month >= 10 ? month : '0' + month; //month 두자리로 저장
 var day = date.getDate(); //d
 day = day >= 10 ? day : '0' + day; //day 두자리로 저장
 return year + '' + month + '' + day; //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
	}




function selectArea(val){
	
	if($(".area").length != 1){ //선택한 요소의 개수를 반환
		$(".area").last().remove();	//버튼이 눌렸을 때 select가 1개 초과일 경우 append된 select를 지움
	}
	if(val == '11'){
		
			var html = 	"<select class ='area form-control' id ='areaCd' name='areaCd' title='훈련지역 시군구'>"
			
				html += "<option value>전체</option>"
				html+= "<option>강남구</option>"
				html+= "<option>강동구</option>"
				html+= "<option>강북구</option>"
				html+= "<option>강서구</option>"
				html+= "<option>관악구</option>"
				html+= "<option>광진구</option>"
				html+= "<option>구로구</option>"
				html+= "<option>금천구</option>"
				html+= "<option>노원구</option>"
				html+= "<option>도봉구</option>"
				html+= "<option>동대문구</option>"
				html+= "<option>동작구</option> "
				html+= "<option>마포구</option> "
				html+= "<option>서대문구</option>"
				html+= "<option>서초구</option> "
				html+= "<option>성동구</option> "
				html+= "<option>성북구</option> "
				html+= "<option>송파구</option> "
				html+= "<option>양천구</option> "
				html+= "<option>영등포구</option>"
				html+= "<option>용산구</option> "
				html+= "<option>은평구</option> "
				html+= "<option>종로구</option> "
				html+= "<option>중구</option> "
				html+= "<option>중랑구</option></select>";
				$("#area").append(html);
	} else if (val == '41') {
		
		var html = "<select class ='area form-control' id='areaCd' name='areaCd' title='훈련지역 시군구'>"+
		"<option value>전체</option>"+
//		"<option value='0'>시군구</option>"+
		"<option>가평군</option>"+
		"<option>고양시</option>"+
		"<option>고양시 덕양구</option>"+
		"<option>고양시 일산구</option>"+
		"<option>고양시 일산동구</option>"+
		"<option>고양시 일산서구</option>"+
		"<option>과천시</option>"+
		"<option>광명시</option>"+
		"<option>광주시</option>"+
		"<option>구리시</option>"+
		"<option>군포시</option>"+
		"<option>김포시</option>"+
		"<option>남양주시</option>"+
		"<option>동두천시</option>"+
		"<option>부천시</option>"+
		"<option>성남시</option>"+
		"<option>성남시 분당구</option>"+
		"<option>성남시 수정구</option>"+
		"<option>성남시 중원구</option>"+
		"<option>수원시</option>"+
		"<option>수원시 권선구</option>"+
		"<option>수원시 영통구</option>"+
		"<option>수원시 장안구</option>"+
		"<option>수원시 팔달구</option>"+
		"<option>시흥시</option>"+
		"<option>안산시</option>"+
		"<option>안산시 단원구</option>"+
		"<option>안산시 상록구</option>"+
		"<option>안성시</option>"+
		"<option>안양시</option>"+
		"<option>안양시 동안구</option>"+
		"<option>안양시 만안구</option>"+
		"<option>양주시</option>"+
		"<option>양평군</option>"+
		"<option>여주시</option>"+
		"<option>연천군</option>"+
		"<option>오산시</option>"+
		"<option>용인시</option>"+
		"<option>용인시 기흥구</option>"+
		"<option>용인시 수지구</option>"+
		"<option>용인시 처인구</option>"+
		"<option>의왕시</option>"+
		"<option>의정부시</option>"+
		"<option>이천시</option>"+
		"<option>파주시</option>"+
		"<option>평택시</option>"+
		"<option>포천군</option>"+
		"<option>포천시</option>"+
		"<option>하남시</option>"+
		"<option>화성시</option></select>";
		
		$("#area").append(html);
	} else {

	}
}

function selectNcs(val){
	if($(".upperNcsCd").length != 1){
		$(".upperNcsCd").last().remove();
	}
	if(val == '01'){
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>사업관리</option>"
				
			html+= "</select>";
			$("#work").append(html);
				
	} else if (val == '02') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"

			html += "<option value='0'>전체</option>"
			html += "<option value='01'>기획사무</option>";
			html += "<option value='02'>총무·인사</option>";
			html += "<option value='03'>재무·회계</option>";
			html += "<option value='04'>생산·품질관리</option>";
			
			html+= "</select>";
		$("#work").append(html);
		
	} else if (val == '03') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"

			html += "<option value='0'>전체</option>"
			html += "<option value='01'>금융</option>";
			html += "<option value='02'>보험</option>";
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '04') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"

			html += "<option value='0'>전체</option>"
			html += "<option value='01'>학교교육</option>";
			html += "<option value='02'>평생교육</option>";
			html += "<option value='03'>직업교육</option>";
			
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '05') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"

			html += "<option value='0'>전체</option>"
			html += "<option value='01'>법률</option>";
			html += "<option value='02'>소방방재</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '06') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"

			html += "<option value='0'>전체</option>"
			html += "<option value='01'>보건</option>";
			html += "<option value='02'>의료</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '07') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"

			html += "<option value='0'>전체</option>"
			html += "<option value='01'>사회복지</option>";
			html += "<option value='02'>상담</option>";
			html += "<option value='03'>보육</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '08') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"  
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>문화·예술</option>";
			html += "<option value='02'>디자인</option>";
			html += "<option value='03'>문화콘텐츠</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '09') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>" 
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>자동차운전·운송</option>"; 
			html += "<option value='02'>철도 운전·운송</option>"; 
			html += "<option value='03'>선박운전·운송</option>"; 
			html += "<option value='04'>항공운전·운송</option>"; 
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '10') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"  
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>영업</option>";
			html += "<option value='02'>부동산</option>";
			html += "<option value='03'>판매</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '11') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"  
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>경비</option>";
			html += "<option value='02'>청소</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '12') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>" 
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>이·미용</option>";
			html += "<option value='02'>결혼·장례</option>";
			html += "<option value='03'>관광·레저</option>";
			html += "<option value='04'>스포츠</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '13') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>" 
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>식음료조리·서비스</option>";
			
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '14') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>" 
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>건설공사관리</option>";
			html += "<option value='02'>토목</option>";
			html += "<option value='03'>건축</option>";
			html += "<option value='04'>플랜트</option>";
			html += "<option value='05'>조경</option>";
			html += "<option value='06'>도시·교통</option>";
			html += "<option value='07'>건설기계운전·정비</option>";
			html += "<option value='08'>해양자원</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '15') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"  
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>기계설계</option>";
			html += "<option value='02'>기계가공</option>";
			html += "<option value='03'>기계조립·관리</option>";
			html += "<option value='04'>기계품질관리</option>";
			html += "<option value='05'>기계장치설치</option>";
			html += "<option value='06'>자동차</option>";
			html += "<option value='07'>철도차량제작</option>";
			html += "<option value='08'>조선</option>";
			html += "<option value='09'>항공기제작</option>";
			html += "<option value='10'>금형</option>";	
			html += "<option value='11'>스마트팩토리</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '16') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"  
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>금속재료</option>";
			html += "<option value='02'>요업재료</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '17') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"  
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>화학물질·화학공정관리</option>";
			html += "<option value='02'>석유·기초화학물 제조</option>";
			html += "<option value='03'>정밀화학제품제조</option>";	
			html += "<option value='04'>플라스틱제품제조</option>";
				
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '18') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>" 
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>섬유제조</option>";
			html += "<option value='02'>패션</option>";
			html += "<option value='03'>의복관리</option>";
				
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '19') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"  
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>전기</option>";
			html += "<option value='02'>전자기기일반</option>";
			html += "<option value='03'>전자기기개발</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '20') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>" 
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>정보기술</option>";
			html += "<option value='02'>통신기술</option>";
			html += "<option value='03'>방송기술</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '21') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"  
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>식품가공</option>";
			html += "<option value='02'>제과·제빵·떡제조</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '22') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"  
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>인쇄·출판</option>";
			html += "<option value='02'>공예</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '23') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>"  
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>산업환경</option>";
			html += "<option value='02'>환경보건</option>";
			html += "<option value='03'>자연환경</option>";
			html += "<option value='04'>환경서비스</option>";
			html += "<option value='05'>에너지·자원</option>";
			html += "<option value='06'>산업안전</option>";
					
			html+= "</select>";
		$("#work").append(html);
	} else if (val == '24') {
		
		var html = 	"<select class ='upperNcsCd form-control' id ='ncsCd' name='NcsCd' title='NCS중분류'>" 
			
			html += "<option value='0'>전체</option>"
			html += "<option value='01'>농업</option>";
			html += "<option value='02'>축산</option>";
			html += "<option value='03'>임업</option>";
			html += "<option value='04'>수산</option>";
					
			html+= "</select>";
		$("#work").append(html);
		
	} else {

	}
}
