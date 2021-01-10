<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <style>
        html,
        body,
        #google-map {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0
        }
        #search-panel {
            position: absolute;
            top: 70px;
            left: 1%;
            z-index: 5;
            background-color: #FFFFFF;
            padding: 5px;
            border: 1px solid black;
            text-align: center;
            padding: left: 10px
        }
    </style>
    <title></title>
</head>
<script type="text/javascript" src="../resources/js/urljs.js"></script>

<body>
    <div id="search-panel">
    <table>
    	<thead>
    		<tr>
    			<th>
			    	<input type="button" id = "serach_for_addr" class = "serach_for_addr" value ="주소로 찾기"  onclick='searchfor(1)'>
			    	<input type="button" id = "serach_for_name" class = "serach_for_name" value ="학원 명으로 찾기" onclick='searchfor(2)' >
    			</th>
    		</tr>
    	</thead>
    	
    	<tbody>
    		
    		<tr>
    			<td id = "area">
	    			
    			</td>
    		</tr>
    		
    		<tr>
    			<td id = "btn">
	    			
    			</td>
    		</tr>
    	</tbody>
    	<tfoot id="tfoot">
    		    		
    	</tfoot>
    </table>
    </div>
    <div id="google-map">
    </div>
 
    
</body>


<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <!-- Google Map API -->
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBWnlw1eqaCRZqPS8WOuS7ib9ZcdWtRUMs&callback=initMap">
    </script>
    <script>
    function selectArea(val){
    	if(val ==11){
    		
    	}else{
    		
    	}
    	
    }
    function searchfor(val){
		$("#area").children().remove();	//버튼이 눌렸을 때 select가 1개 초과일 경우 append된 select를 지움
		$("#btn").children().remove();	//버튼이 눌렸을 때 select가 1개 초과일 경우 append된 select를 지움
		$("#tfoot").children().remove();	//버튼이 눌렸을 때 select가 1개 초과일 경우 append된 select를 지움
   		var html = "";
   		var btn= "";
    	if(val ==1){
    		

    		html += "	<select class= 'area' id='upperAreaCd' name = 'upperAreaCd' title ='훈련지역 선택' onchange='selectArea(this.value)'>"
	    	html += "		<option value=''>---</option>"
	    	html += "		<option value='11'>서울</option>"
	    	html += "		<option value='41'>경기</option>"
    		html += "	</select>"
   	       	btn  += "<input id='submit' type='button' value='주소검색' onclick='doSearch(1)'></button>"


			$("#area").append(html);
			$("#btn").append(btn)
    	}else if(val == 2){
    		html += "<input id='address' type='text' placeholder ='주소 입력' />"
   			btn += "<input id='submit' type='button' value='주소검색' onclick='doSearch(2)'></button>"
			$("#area").append(html);
			$("#btn").append(btn)

    	}
    }
    
    function doSearch (val){
        var geocoder = new google.maps.Geocoder();
        var address = "";
        if(val ==1){
        	$.ajax({
        		type:"get",
				url:"../returnaddress.do",
				data:{
					"val1" : $("#upperAreaCd option:selected").val(), 
					"val2" :$("#areaCd option:selected").val()
				},
				success:function(msg) {
					alert("msg : "+msg)
					address = msg;
					doAJAX(val, address)
				},
				error:function() {
					alert("returnaddress Ajax Has a problem..");
					
				}
            })
       	
        	
        }else if(val ==2){
			address = document.getElementById('address').value;
			doAJAX(val, address)
        }

//         alert("!")
//         alert("address : "+address);
       

    }
    function doAJAX(val, address){
    	 $.ajax({
         	type:"get",
				url:"../searchAddress.do",
				data:{
					"val" : val, 
					"source" :address
				},
				dataType : "json",
				success:function(msg) {
// 					alert( msg);
					$.each(msg , function (key, value){
						if(key == "lists"){
							var list = value;
							alert(value);
							$.each(list, function(k,v){
								var text = "";
								text += "<tr>"
								text += "<td>" +v.name +"</td>"
								text += "<td>" +v.addr1 +"</td>"
								text += "<td>" +v.addr2 +"</td>"
								text += "<td>" +v.phone +"</td>"
								text += "</tr>"
								
								$("#tfoot").append(text);
							});
						}
					});
				},
				error:function() {
					alert("testMap Ajax Has a problem..");
				}
         })
    }
        /**
         * Google Map API 주소의 callback 파라미터와 동일한 이름의 함수이다.
         * Google Map API에서 콜백으로 실행시킨다.
         */
        function initMap() {
            console.log('Map is initialized.');
 
            /**
             * 맵을 설정한다.
             * 1번째 파라미터 : 구글 맵을 표시할 위치. 여기서는 #google-map
             * 2번째 파라미터 : 맵 옵션.
             *      ㄴ zoom : 맵 확대 정도
             *      ㄴ center : 맵 중심 좌표 설정
             *              ㄴ lat : 위도 (latitude)
             *              ㄴ lng : 경도 (longitude)
             */
            var map = new google.maps.Map(document.getElementById('google-map'), {
                zoom: 12.5,
                center: {
                    lat: 37.4917004,
                    lng: 126.8858375
                }
            });
 
            /**
             * Google Geocoding. Google Map API에 포함되어 있다.
             */
            var geocoder = new google.maps.Geocoder();
 			
            // submit 버튼 클릭 이벤트 실행
            document.getElementById('submit').addEventListener('click', function() {
                console.log('submit 버튼 클릭 이벤트 실행');
 
                // 여기서 실행
                geocodeAddress(geocoder, map);
                //클릭후 결과값과
            });
 
            /**
             * geocodeAddress
             * 
             * 입력한 주소로 맵의 좌표를 바꾼다.
             */
            function geocodeAddress(geocoder, resultMap) {
                console.log('geocodeAddress 함수 실행');
 
                // 주소 설정
 
                /**
                 * 입력받은 주소로 좌표에 맵 마커를 찍는다.
                 * 1번째 파라미터 : 주소 등 여러가지. 
                 *      ㄴ 참고 : https://developers.google.com/maps/documentation/javascript/geocoding#GeocodingRequests
                 * 
                 * 2번째 파라미터의 함수
                 *      ㄴ result : 결과값
                 *      ㄴ status : 상태. OK가 나오면 정상.
                 */
                 

//                 geocoder.geocode({'address': address}, function(result, status) {
//                     console.log(result);
//                     console.log(status);
 
//                     if (status === 'OK') {
//                         // 맵의 중심 좌표를 설정한다.
//                         resultMap.setCenter(result[0].geometry.location);
//                         // 맵의 확대 정도를 설정한다.
//                         resultMap.setZoom(18);
//                         // 맵 마커
//                         var marker = new google.maps.Marker({
//                             map: resultMap,
//                             position: result[0].geometry.location
//                         });
 
//                         // 위도
//                         console.log('위도(latitude) : ' + marker.position.lat());
//                         // 경도
//                         console.log('경도(longitude) : ' + marker.position.lng());
//                     } else {
//                         alert('지오코드가 다음의 이유로 성공하지 못했습니다 : ' + status);
//                     }
//                 });
                
                
                
            }
        }
        
        $(document).ready(function(){
//         	alert("생성") //동작확인했음
        })
    </script>
</html>

