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
<script type="text/javascript" src="/GWD_LMS_SYS/resources/js/hrdView.js"></script>

    <!-- Google Map API -->
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBWnlw1eqaCRZqPS8WOuS7ib9ZcdWtRUMs&callback=initMap">
    </script>
    <script>
    
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
				url:"/GWD_LMS_SYS/returnaddress.do",
				data:{
					"val1" : $("#upperAreaCd option:selected").val(), 
					"val2" :$("#areaCd option:selected").val()
				},
				success:function(msg) {
					
// 					alert("msg : "+msg)
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
    	var resultaddress= []
		var texts =[]
    	var geocoder= new google.maps.Geocoder();
    	var position = "";
    	geocoder.geocode({'address':address}, function(result, status){
    		position = result[0].geometry.location
// 	    	alert("position : "+position)
    		var map = new google.maps.Map(document.getElementById('google-map'), {
                zoom: 16,
                center: position
            });
    		
    		$.ajax({
             	type:"get",
    				url:"/GWD_LMS_SYS/searchAddress.do",
    				data:{
    					"val" : val, 
    					"source" :address
    				},
    				dataType : "json",
    				success:function(msg) {
    					
//     					alert( msg);
    					$.each(msg , function (key, value){
    						if(key == "lists"){
    							var length = Object.keys(value).length;
//     							alert("length : "+length )
    							var list = value;
//     							alert(value);
    							$.each(list, function(k,v){
    								var text = "";
    								text += "<table><tr><th><input type ='button' value ='선택' onclick ='clickthis(\""+v.addr1+v.addr2+"\")'></th></tr>"
    								text += "<tr><th colspan ='2' align = center>상세정보</th></tr>"
    								text += "<tr><th>이름</th><td>" +v.name +"</td></tr>"
    								text += "<tr><th>주소</th><td>" +v.addr1 +"</td></tr>"
    								text += "<tr><th>상세주소</th><td>" +v.addr2 +"</td></tr>"
    								text += "<tr><th>연락처</th><td>" +v.phone +"</td></tr>"
    								text += "</table>"
    								texts.push(text);
//     								$("#tfoot").append(text);
//    									resultaddress.push(v.addr1)
    								drawMarker(map, geocoder, v.addr1, text, v.name)
    							});
    						}
    						
//     						alert(texts)
// 	    					drawMarker(map ,geocoder, resultaddress,texts)
    						
    					});
    					
    				},
    				error:function() {
    					alert("searchAddress Ajax Has a problem..");
    				}
             })
             
             
    	});
    	 
    	 
    }

    function drawMarker(map, geocoder , alladdress,texts,name){
        var myIcon = new google.maps.MarkerImage("/GWD_LMS_SYS/images/googlemaps_marker.png")
//         alert(texts.length +","+ alladdress.length);
	    	geocoder.geocode({'address':alladdress}, function(result, status){
	    		console.log(alladdress)
				if (status === 'OK') {
					var marker = new google.maps.Marker({
						position:result[0].geometry.location , 
						map : map, 
						animation: google.maps.Animation.DROP,
						icon : myIcon,
						title : name});
					
					var infowindow = new google.maps.InfoWindow({
	 					content : "<div id = 'content'>"+texts+"</div>"
	 				});
					marker.addListener("click" , () =>{
						map.setZoom(17.5);
						map.setCenter(result[0].geometry.location);
						infowindow.open(map,marker);
						 if (marker.getAnimation() != null) {
							    marker.setAnimation(null);
							  } else {
							    marker.setAnimation(google.maps.Animation.BOUNCE);
							  }
					})
	    		}
			});

			
    	}
    
    

		function locationTest() {
                navigator.geolocation.getCurrentPosition(handleLocation, handleError); 
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
             	var myhome = { lat: 37.4917004, lng: 126.8858375
                }
            var map = new google.maps.Map(document.getElementById('google-map'), {
                zoom: 12.5,
                center: myhome
            });
 
             
             var myIcon = new google.maps.MarkerImage("/GWD_LMS_SYS/images/googlemaps_myhome.png")
             var maker = new google.maps.Marker({
            	 position:myhome , 
            	 map : map, 
            	 icon : myIcon,						
            	 animation: google.maps.Animation.DROP
});
            /**
             * Google Geocoding. Google Map API에 포함되어 있다.
             */

			var infowindow = new google.maps.InfoWindow({
				content : "<div id = 'content'><h3><strong>영서네집 ㅎㅎ</strong></h3><hr><legend>그냥한고에용ㅎㅎ</legend></div>"
			});
			maker.addListener("click" , () =>{
				map.setZoom(18);
				map.setCenter(myhome);
				infowindow.open(map,maker);
				 if (maker.getAnimation() != null) {
					    maker.setAnimation(null);
					  } else {
					    maker.setAnimation(google.maps.Animation.BOUNCE);
					  }
			})

        }
        
        $(document).ready(function(){
//         	alert("생성") //동작확인했음
        })
        
        function clickthis(val){
        	$(opener.document).find("#rocationText").val(val);
        	window.close();
        }
    </script>
</html>

