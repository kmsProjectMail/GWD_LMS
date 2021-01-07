/**
 * 
 */

function selectArea(val){
	
		if($(".area").length != 1){ //선택한 요소의 개수를 반환
			$(".area").last().remove();	//버튼이 눌렸을 때 select가 1개 초과일 경우 append된 select를 지움
		}
		if(val == 11){
			
				var html = 	"<select class ='area' id ='areaCd' name='areaCd' title='훈련지역 시군구' style='width:177px;'>"
				
					html += "<option value ='0'>시군구</option>"
					html+= "<option value='11680'>강남구</option>"
					html+= "<option value='11740'>강동구</option>"
					html+= "<option value='11305'>강북구</option>"
					html+= "<option value='11500'>강서구</option>"
					html+= "<option value='11620'>관악구</option>"
					html+= "<option value='11215'>광진구</option>"
					html+= "<option value='11530'>구로구</option>"
					html+= "<option value='11545'>금천구</option>"
					html+= "<option value='11350'>노원구</option>"
					html+= "<option value='11320'>도봉구</option>"
					html+= "<option value='11230'>동대문구</option>"
					html+= "<option value='11590'>동작구</option>  "
					html+= "<option value='11440'>마포구</option>  "
					html+= "<option value='11410'>서대문구</option>"
					html+= "<option value='11650'>서초구</option>  "
					html+= "<option value='11200'>성동구</option>  "
					html+= "<option value='11290'>성북구</option>  "
					html+= "<option value='11710'>송파구</option>  "
					html+= "<option value='11470'>양천구</option>  "
					html+= "<option value='11560'>영등포구</option>"
					html+= "<option value='11170'>용산구</option>  "
					html+= "<option value='11380'>은평구</option>  "
					html+= "<option value='11110'>종로구</option>  "
					html+= "<option value='11140'>중구</option>    "
					html+= "<option value='11260'>중랑구</option></select>";
					$("#area").append(html);
		} else if (val == 41) {
			
			var html = "<select class ='area' id='areaCd' name='areaCd' title='훈련지역 시군구' style='width:177px;'>"+
			"<option value='0'>시군구</option>"+
			"<option value='41820'>가평군</option>"+
			"<option value='41280'>고양시</option>"+
			"<option value='41281'>고양시 덕양구</option>"+
			"<option value='41283'>고양시 일산구</option>"+
			"<option value='41285'>고양시 일산동구</option>"+
			"<option value='41287'>고양시 일산서구</option>"+
			"<option value='41290'>과천시</option>"+
			"<option value='41210'>광명시</option>"+
			"<option value='41610'>광주시</option>"+
			"<option value='41310'>구리시</option>"+
			"<option value='41410'>군포시</option>"+
			"<option value='41570'>김포시</option>"+
			"<option value='41360'>남양주시</option>"+
			"<option value='41250'>동두천시</option>"+
			"<option value='41190'>부천시</option>"+
			"<option value='41130'>성남시</option>"+
			"<option value='41135'>성남시 분당구</option>"+
			"<option value='41131'>성남시 수정구</option>"+
			"<option value='41133'>성남시 중원구</option>"+
			"<option value='41110'>수원시</option>"+
			"<option value='41113'>수원시 권선구</option>"+
			"<option value='41117'>수원시 영통구</option>"+
			"<option value='41111'>수원시 장안구</option>"+
			"<option value='41115'>수원시 팔달구</option>"+
			"<option value='41390'>시흥시</option>"+
			"<option value='41270'>안산시</option>"+
			"<option value='41273'>안산시 단원구</option>"+
			"<option value='41271'>안산시 상록구</option>"+
			"<option value='41550'>안성시</option>"+
			"<option value='41170'>안양시</option>"+
			"<option value='41173'>안양시 동안구</option>"+
			"<option value='41171'>안양시 만안구</option>"+
			"<option value='41630'>양주시</option>"+
			"<option value='41830'>양평군</option>"+
			"<option value='41670'>여주시</option>"+
			"<option value='41800'>연천군</option>"+
			"<option value='41370'>오산시</option>"+
			"<option value='41460'>용인시</option>"+
			"<option value='41463'>용인시 기흥구</option>"+
			"<option value='41465'>용인시 수지구</option>"+
			"<option value='41461'>용인시 처인구</option>"+
			"<option value='41430'>의왕시</option>"+
			"<option value='41150'>의정부시</option>"+
			"<option value='41500'>이천시</option>"+
			"<option value='41480'>파주시</option>"+
			"<option value='41220'>평택시</option>"+
			"<option value='41810'>포천군</option>"+
			"<option value='41650'>포천시</option>"+
			"<option value='41450'>하남시</option>"+
			"<option value='41590'>화성시</option></select>";
			
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
//	console.log(srchTraArea1);
//	var srchTraArea2 = document.getElementById("areaCd").value; //훈련지역 중분류
	
	var srchKeco1 =  document.getElementById("upperNcsCd").value;
	
	var keyword = document.getElementById("key").value;
//	console.log("키워드: "+keyword);
	
	if(srchKeco1 != 0){ 		//훈련분야 대분류
//	console.log(srchTraArea1+","+srchTraArea2 +","+ srchKeco1+","+date);
	
	$.ajax({
		type: "get",
		url: "./search.do",
		data:{ 	
				"address" : srchTraArea1,
				"ncs_cd" : srchKeco1,
				"trpr_nm": keyword
		},
		dataType: "json",
		success: function(data){
			console.log(data);
			$.each(data, function(key, value){
				$("#resulttable>tbody").empty();
//				var html = "";
				if(key == "info"){
					var list = value;
					$.each(list, function(k, v){
						var html = "";
//						ino_nm, ti.trpr_nm, ti.tra_start_date, ti.tra_end_date, ti.trtm, ti.trpr_degr
//						교육기관명, 교육과정명, 교육시작일, 교육종료일, 교육 시간, 회차정보
						html += "<tr>"
						html += "	<td>"+v.ino_nm+"</td>"
//						html += "	<td><a href= '"+v.hpAddr+"'>"+v.hpAddr+"</a></td> "
						html += "	<td>"+v.trpr_nm+"</td>"
						html += "	<td>"+v.tra_start_date+"</td>"
						html += "	<td>"+v.tra_end_date+"</td>"
						html += "	<td>"+v.trtm+"</td>"
						html += "	<td>"+v.trpr_degr+"</td>"
						html += "</tr>"
						$("#resulttable>tbody").append(html);						
//						$("#resulttable>tbody").html(html);
					});
				}
			});
		} ,
		error:function(){
			alert("몬가... 잘못됐어...")
		}
	})
	alert("종료!");
	return false;
	}
}

function getFormatDate(date){
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '' + month + '' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
	}
