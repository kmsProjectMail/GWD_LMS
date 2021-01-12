$(document).ready(function(){
	$("input:radio[id=keywordType1]").click(function(){
//		alert('작동1');
		$("#keyword1").css("display","inline");
		$("#keyword2").css("display","none");
		$("#keyword3").css("display","none");
	})
	$("input:radio[id=keywordType2]").click(function(){
//		alert('작동2');
		$("#keyword1").css("display","none");
		$("#keyword2").css("display","inline");
		$("#keyword3").css("display","none");
	})
	$("input:radio[id=keywordType3]").click(function(){
//		alert('작동3');
		$("#keyword1").css("display","none");
		$("#keyword2").css("display","none");
		$("#keyword3").css("display","inline");
	})
});



function selectArea(val){
	
		if($(".area").length != 1){ //선택한 요소의 개수를 반환
			$(".area").last().remove();	//버튼이 눌렸을 때 select가 1개 초과일 경우 append된 select를 지움
		}
		if(val == '11'){
			
				var html = 	"<select class ='area' id ='areaCd' name='areaCd' title='훈련지역 시군구' style='width:177px;'>"
				
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
					html+= "<option>동작구</option>  "
					html+= "<option>마포구</option>  "
					html+= "<option>서대문구</option>"
					html+= "<option>서초구</option>  "
					html+= "<option>성동구</option>  "
					html+= "<option>성북구</option>  "
					html+= "<option>송파구</option>  "
					html+= "<option>양천구</option>  "
					html+= "<option>영등포구</option>"
					html+= "<option>용산구</option>  "
					html+= "<option>은평구</option>  "
					html+= "<option>종로구</option>  "
					html+= "<option>중구</option>    "
					html+= "<option>중랑구</option></select>";
					$("#area").append(html);
		} else if (val == '41') {
			
			var html = "<select class ='area' id='areaCd' name='areaCd' title='훈련지역 시군구' style='width:177px;'>"+
			"<option value>전체</option>"+
//			"<option value='0'>시군구</option>"+
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

function runajax(){ //버튼을 눌러서 검색
	
//	var date = Number(document.getElementById("date").value);	//home.jsp에서 기간 선택
//	var d = new Date(); //현재시간 문자열로 반환
//	var dayOfMonth = d.getDate();	//주어진 날짜의 일자 반환 (1이상 31이하의 정수)
	
//	var dd = new Date(2020, 12, 28); // 월을 나타내는 0 ~ 11까지의 정수 (주의: 0부터 시작, 0 = 1월)
	
//	console.log(dayOfMonth);
//	d.setDate(dayOfMonth+date);		//현재일자 + 선택 기간
//	console.log(getFormatDate(d));	//getFormatDate: yyyyMMdd 포맷으로 반환
	
//	url += "srchTraStDt="+getFormatDate(new Date())+"&"	//훈련시작일 From		//검색기간
//	url += "srchTraEndDt="+getFormatDate(d)+"&"			//훈련시작일 To			//검색기간
	
	
	var srchTraArea1 = document.getElementById("upperAreaCd").value; //훈련지역 대분류
	if(srchTraArea1 != 'all'){
		var srchTraArea2 = document.getElementById("areaCd").value; //훈련지역 중분류
		
		if(srchTraArea2 == ''){
			var srchTraArea2 = '';
		}
	}else{
		var address = srchTraArea1;
	}
	
	var srchKeco1 =  document.getElementById("upperNcsCd").value;
	
	if($("input:radio[name=keywordType]:checked").val() == "1"){
		var keyword = document.getElementById("key1").value;
		$.ajax({
			type: "get",
			url: "./search.do",
			data:{ 	
				"addr1" : srchTraArea1,
				"addr2" : srchTraArea2,
				"ncs_cd" : srchKeco1,
				"trpr_nm": keyword
			},
			dataType: "json",
			success: function(data){
				$.each(data, function(key, value){
					$("#resulttable>tbody").empty();
					if(key == "info"){
						var list = value;
						$.each(list, function(k, v){
							var html = "";
//						ino_nm, ti.trpr_nm, ti.tra_start_date, ti.tra_end_date, ti.trtm, ti.trpr_degr
//						교육기관명, 교육과정명, 교육시작일, 교육종료일, 교육 시간, 회차정보
							html += "<tr>"
//								html += "	<td><a href= '"+v.trainst_cst_id+"'>"+v.ino_nm+"</td>"
//						html += "	<td><a href= '"+v.hpAddr+"'>"+v.hpAddr+"</a></td> "
								html += "	<td><a href='./hrdDetailTrainst.do?trpr_id="+v.trpr_id+"&trpr_degr="+v.trpr_degr+"&trainst_cst_id="+v.trainst_cst_id+"'>"+v.ino_nm+"</a></td>"
								html += "	<td><a href='./hrdDetailTrpr.do?trpr_id="+v.trpr_id+"&trpr_degr="+v.trpr_degr+"'>"+v.trpr_nm+"</a></td>"
								html += "	<td>"+v.tra_start_date+"</td>"
								html += "	<td>"+v.tra_end_date+"</td>"
								html += "	<td>"+v.trtm+"</td>"
								html += "	<td>"+v.trpr_degr+"</td>"
								html += "</tr>"
									$("#resulttable>tbody").append(html);
							
						});
					}
				});
			} ,
			error:function(){
				alert("몬가... 잘못됐어...")
			}
		})
	alert("검색 완료!");
		
	}else if($("input:radio[name=keywordType]:checked").val() == "2"){
		var keyword = document.getElementById("key2").value;
		alert("개발중, 키워드검색을 이용하세요.");
		
	}else if($("input:radio[name=keywordType]:checked").val() == "3"){
		var keyword = document.getElementById("key3").value;
		alert("개발중, 키워드검색을 이용하세요.");
	}
	
	
//	alert("검색 완료!");
	
	return false;
}

function getFormatDate(date){
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '' + month + '' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
	}

