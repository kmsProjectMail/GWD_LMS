var token = $("meta[name='_csrf']").attr("content");
	  var header = $("meta[name='_csrf_header']").attr("content");
	  $(document).ajaxSend(function(e, xhr, options) {
		    xhr.setRequestHeader(header, token);
		});

function searchShedule(){
	var keyword = document.getElementById('keyword').value;
	if (keyword.trim()=="") {
		swal({
			title: "",
			text: "검색어를 입력해 주세요."
		}, function(result){
			setTimeout(function(){
				$('#keyword').val("");
				$('#keyword').focus();
			});
		});
	}else {
		searchAjax2(keyword);
		var sDiv = "";
		sDiv += '<input class="form-control mr-sm-2" type="search" id="keyword" name="keyword" placeholder="제목 또는 내용" aria-label="Search"';
		sDiv += 'onkeypress="if(event.keyCode==13) return searchShedule();">';
		sDiv += '<button class="btn btn-outline-success my-2 my-sm-0 " onclick="searchShedule()">검색</button>';
		$('.form-inline').html(sDiv);
		$('.cal_top').html('<div style="font-size:x-large;">검색 결과 <a style="margin-left:5%; font-size:medium; cursor:pointer;" onclick="backList()">이전으로 돌아가기</a></div>');
	}
}

function backList(){
	location.href='./calendarMyList.do';
}

var searchAjax2 = function(keyword){
	$.ajax({
		url : "./searchShceduleList.do",
		type : "post",
		dataType: "json",
		data : {"keyword":keyword},
		success : function(msg){
			var temp = "";
			for(var i = 0; i < msg.length; i++) {
				
				if (msg[i].alarm_check=='Y') {
					temp+= '<tr>'
					+'<td><input type="checkbox" id="'+msg[i].seq+'" name="chk" checked onclick="chk(this)">'
					+'<label id="onoffCheck" for="'+msg[i].seq+'"><span></span></label></td>';
				}else{
					temp+= '<tr>'
					+'<td><input type="checkbox" id="'+msg[i].seq+'" name="chk" onclick="chk(this)">'
					+'<label id="onoffCheck" for="'+msg[i].seq+'"><span></span></label></td>';
				}
				
				if (msg[i].calId=='1') {
					msg[i].calId='면담';
				}else if (msg[i].calId=='2') {
					msg[i].calId='내 일정';
				}else if (msg[i].calId=='3') {
					msg[i].calId='학원';
				}else if (msg[i].calId=='4') {
					msg[i].calId='기념일';
				}else if (msg[i].calId=='5') {
					msg[i].calId='기타';
				}else if (msg[i].calId=='6') {
					msg[i].calId='공휴일';
				}
				
				temp+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2('+msg[i].seq+')">' + msg[i].calId+ '</td>';
				temp+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2('+msg[i].seq+')">' + msg[i].title+ '</td>';
				temp+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2('+msg[i].seq+')">' + msg[i].start+ '</td>';
				temp+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2('+msg[i].seq+')">' + msg[i].end+ '</td></tr>';
			}
			$('.table > tbody').html(temp);
		},
		error : function(){
			alert("searchMeetList.do 잘못된 요청입니다.");
		}
	});
}

function clickTitle2(seq){
	$.ajax({
		url : "./selectOneMy.do",
		data : { "id" : seq },
		dataType : "json",
		type : "post",
		success : function(msg){
			$('#dateID').val(msg.id);
			$('#title').val(msg.title);
			$('#user').val(msg.calendarId);
			$('#content').val(msg.content);
			$('#datepicker-input').val(msg.start);
			$('#datepicker-input2').val(msg.end);
			
			if (msg.calendarId==1) {
				$('.modal-title').html('내 면담 일정');
				$("#user").prop('disabled',true);
				$('.form-control').prop('readonly',true);
				$('#location').prop('disabled',true);
				$('#datepicker-input').prop('disabled',true);
				$('#datepicker-input2').prop('disabled',true);
				$('.modal-footer').css('display','none');
			}
			
			var y = Number($('#datepicker-input').val().substring(0,4));
			var m = Number($('#datepicker-input').val().substr(5,2))-1;
			var d = Number($('#datepicker-input').val().substr(8,2));
			var A = $("#datepicker-input").val().substr(17,2);
			var hh = $("#datepicker-input").val().substr(11,2);
			if (A=="AM" && hh == 12) {
				hh = 0;
			}else if (A=="PM" && hh != 12) {
				hh = Number(hh)+12;
			}
			
			var y2 = Number($('#datepicker-input2').val().substring(0,4));
			var m2 = Number($('#datepicker-input2').val().substr(5,2))-1;
			var d2 = Number($('#datepicker-input2').val().substr(8,2));
			var A2 = $("#datepicker-input2").val().substr(17,2);
			var hh2 = $("#datepicker-input2").val().substr(11,2);
			if (A2=="AM" && hh2 == 12) {
				hh2 = 0;
			}else if (A2=="PM" && hh2 != 12) {
				hh2 = Number(hh2)+12;
			}
			var datepicker = new tui.DatePicker('#wrapper', {
				date: new Date(y,m,d,hh),
				input: {
					element: '#datepicker-input',
					format: 'yyyy-MM-dd HH:00 A'
				},
				timePicker: {
					layoutType: 'tab',
					inputType: 'spinbox'
				},
				calendar: {
			        showToday: false
			    }
			});

			var datepicker2 = new tui.DatePicker('#wrapper2', {
				date: new Date(y2,m2,d2,hh2),
				input: {
					element: '#datepicker-input2',
					format: 'yyyy-MM-dd HH:00 A'
				},
				timePicker: {
					layoutType: 'tab',
					inputType: 'spinbox'
				},
				calendar: {
			        showToday: false
			    }
			});

			$('.tui-timepicker-colon').css('display','none');
			$('.tui-timepicker-minute').css('display','none');
		},
		error : function(){
			alert("selectOne.do 잘못된 요청입니다.");
		}
	});
}

//수정
function modify2() {
	var id = $('#dateID').val();
	var title = $('#title').val();
	var content = $('#content').val();
	var user = $('#user').val();
	var start = $("#datepicker-input").val();
	var end = $("#datepicker-input2").val();
	
	if (start.substring(17)=="PM" && start.substring(11, 13) != "12") {
		var s = Number(start.substring(11, 13))+12;
	}else {
		var s = start.substring(11, 13);
	}
	if (end.substring(17)=="PM" && end.substring(11, 13) != "12") {
		var e = Number(end.substring(11, 13))+12;
	}else {
		var e = end.substring(11, 13);
	}
	
	start = start.substring(0, 4) +
		start.substring(5, 7) +
		start.substring(8, 10) + s;
	
	end = end.substring(0, 4) +
		end.substring(5, 7) +
		end.substring(8, 10) + e;
	
	var A = $("#datepicker-input").val().substr(17,2);
	var hh = $("#datepicker-input").val().substr(11,2);
	if (A=="AM" && hh == 12) {
		hh = 0;
	}else if (A=="PM" && hh != 12) {
		hh = Number(hh)+12;
	}
	
	var A2 = $("#datepicker-input2").val().substr(17,2);
	var hh2 = $("#datepicker-input2").val().substr(11,2);
	
	if (A2=="AM" && hh2 == 12) {
		hh2 = 0;
	}else if (A2=="PM" && hh2 != 12) {
		hh2 = Number(hh2)+12;
	}
	
	var yyyy = $("#datepicker-input").val().substr(0,4);
	var mm = $("#datepicker-input").val().substr(5,2);
	var dd = $("#datepicker-input").val().substr(8,2);
	var dateVal = new Date(yyyy, mm-1, dd, hh);

	var yyyy2 = $("#datepicker-input2").val().substr(0,4);
	var mm2 = $("#datepicker-input2").val().substr(5,2);
	var dd2 = $("#datepicker-input2").val().substr(8,2);
	var dateVal2 = new Date(yyyy2, mm2-1, dd2, hh2);
	
	if (dateVal >= dateVal2) {
		swal({
			title: " ",
			text: "등록 시간을 다시 설정해 주세요."
		});
		return false;
	}else if ($('#user').val() == 0) {
		swal({
			title: " ",
			text: "태그를 선택해 주세요."
		});
		return false;
	}else if (($('#title').val().trim())=="") {
		swal({
			title: " ",
			text: "제목을 입력해 주세요."
		},function(){
			setTimeout(function(){
				$('#title').val("");
				$('#title').focus();
			});
		});
		return false;
	}
	
	$.ajax({
		url: "./updateMy.do",
		type: "post",
		dataType: "json",
		data:
		{
			"content": content,
			"title": title,
			"start": start,
			"end": end,
			"id" : id,
			"calendar_id" : user,
			"category" : "time",
		},
		success: function(msg) {
			swal({
				title: "Success",
				text: "수정 완료되었습니다.",
				type: "success",
				showConfirmButton: false,
				timer: 1500
			});
			var timer = setInterval(function() {
				$('.modal').on('hidden.bs.modal', function (e) {
					location.href="./calendarMyList.do";
//					var keyword = document.getElementById('keyword').value;
//					searchAjax2(keyword);
				});
				clearInterval(timer); 
			},2000);
		},
		error: function() {
			alert("save 잘못된 요청입니다.");
		}
	});
}

//삭제
function deleteSchedule(){
	var id = $('#dateID').val();
    swal({
    	title:"",
    	text:"일정을 삭제하시겠습니까?",
    	closeOnClickOutside: false,
    	showCancelButton : true,
    	confirmButtonText : "삭제",
		cancelButtonText : "취소",
    	closeOnConfirm : false,
		closeOnCancel : true
    	}, function(result){
    		if(result){
    			$.ajax({
    				url : "./deleteMy.do",
    				type : "post",
    				dataType : "json",
    				data : { "id" : id },
    				success : function(msg){
    					swal({
    						title: "Success",
    						text: "삭제 완료되었습니다.",
    						type: "success",
    						showConfirmButton: false,
    						timer: 1500
    					});
    					var timer = setInterval(function() {
        					location.href="./calendarMyList.do";
    						clearInterval(timer); 
						},2000);
    				},
    				error : function() {
    					alert("잘못된 요청입니다.");
    				}
    			});
    		}
    	});
}

function chk(bool){
//	console.log(bool.id, bool.checked);
	var id = bool.id;
	var chkVal = bool.checked;
	$.ajax({
		url: "./updateCheck.do",
		type: "post",
		data: "id="+id+"&chkVal="+chkVal,
		success: function(msg){
			if (msg.isc == "성공,true") {
				swal({
					title: "Success",
					text: "알람이 설정되었습니다.",
					type: "success",
					showConfirmButton: false,
					timer: 1500
				});
			}else if (msg.isc == "성공,false") {
				
				swal({
					title: "Success",
					text: "알람이 해제되었습니다.",
					type: "success",
					showConfirmButton: false,
					timer: 1500
				});
			}
		},
		error: function(){
			alert("문제가 발생했습니다.");
		}
	});
}

window.onload = function(e) {
	$("select option[value*='1']").prop('disabled',true);
	$('.modal').on('hidden.bs.modal', function (e) {
	    $(this).find('form')[0].reset();
	    $("#user").prop('disabled',false);
	    $('.modal-title').html('일정 수정');
		$('.form-control').prop('readonly',false);
		$('#location').prop('disabled',false);
		$('#datepicker-input').prop('disabled',false);
		$('#datepicker-input2').prop('disabled',false);
		$('.modal-footer').css('display','block');
	});
}


//리스트 로드 형식 변경

var today = null;
var year = null;
var month = null;

$(document).ready(function(){
	today = new Date();
	year = today.getFullYear();
	month = today.getMonth()+1;
	drawDays();
	drawSche();
	$(document).ready(function(){
		$('.modal').on('hidden.bs.modal', function (e) {
		    $(this).find('form')[0].reset();
		});
	});
});

function drawDays(){
	$("#cal_top_year").text(year);
	if(month<10){
	  month=String("0"+month);
	}
	$("#cal_top_month").text(month);
}

function movePrevMonth(){
	month--;
	if(month<=0){
	  month=12;
	  year--;
	}
	drawDays();
	drawSche();
}

function moveNextMonth(){
	month++;
	if(month>12){
	  month=1;
	  year++;
	}
	drawDays();
	drawSche();
}

function drawSche(){
	$.ajax({
		url:"./loadMyList.do",
		type:"post",
		dataType:"json",
		success:function(msg){
			var temp = new Array();
			for (var i = 0; i < msg.length; i++) {
				var y = msg[i].start.substr(0,4);
				var m = msg[i].start.substr(5,2);
				if (y==year && m==month) {
					temp.push(msg[i]);
				}
			}
	//		console.log(temp);
			var text="";
			if (temp.length==0) {
				text+='<tr><td colspan="5" style="color:#a8a8a8; text-align:center;">- - - &nbsp;&nbsp;&nbsp;생성된 일정이 없습니다.&nbsp;&nbsp;&nbsp; - - -</td></tr>'
			}
			for (var i = 0; i < temp.length; i++) {
				if (temp[i].alarm_check=='Y') {
					text+= '<tr>'
					+'<td><input type="checkbox" id="'+temp[i].seq+'" name="chk" checked onclick="chk(this)">'
					+'<label id="onoffCheck" for="'+temp[i].seq+'"><span></span></label></td>';
				}else{
					text+= '<tr>'
					+'<td><input type="checkbox" id="'+temp[i].seq+'" name="chk" onclick="chk(this)">'
					+'<label id="onoffCheck" for="'+temp[i].seq+'"><span></span></label></td>';
				}
				
				if (temp[i].calendarId=='1') {
					temp[i].calendarId='면담';
				}else if (temp[i].calendarId=='2') {
					temp[i].calendarId='내 일정';
				}else if (temp[i].calendarId=='3') {
					temp[i].calendarId='학원';
				}else if (temp[i].calendarId=='4') {
					temp[i].calendarId='기념일';
				}else if (temp[i].calendarId=='5') {
					temp[i].calendarId='기타';
				}else if (temp[i].calendarId=='6') {
					temp[i].calendarId='공휴일';
				}
				
				text+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2('+temp[i].seq+')">' + temp[i].calendarId+ '</td>';
				text+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2('+temp[i].seq+')">' + temp[i].title+ '</td>';
				text+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2('+temp[i].seq+')">' + temp[i].start+ '</td>';
				text+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2('+temp[i].seq+')">' + temp[i].end+ '</td></tr>';
			}
			$('.table > tbody').html(text);
		},
		error:function(){
			alert("loadMyList.do error");
		}
	});
}
