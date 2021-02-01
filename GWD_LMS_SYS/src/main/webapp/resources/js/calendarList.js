var token = $("meta[name='_csrf']").attr("content");
	  var header = $("meta[name='_csrf_header']").attr("content");
	  $(document).ajaxSend(function(e, xhr, options) {
		    xhr.setRequestHeader(header, token);
		});

$(document).ready(function(){
	$('.modal').on('hidden.bs.modal', function (e) {
	    $(this).find('form')[0].reset();
	});
});

function searchMeet(){
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
		searchAjax(keyword);
		var sDiv = "";
		sDiv += '<input class="form-control mr-sm-2" type="search" id="keyword" name="keyword" placeholder="아이디 또는 이름" aria-label="Search"';
		sDiv += 'onkeypress="if(event.keyCode==13) return searchMeet();">';
		sDiv += '<button class="btn btn-outline-success my-2 my-sm-0 " onclick="searchMeet()">검색</button>';
		$('.form-inline').html(sDiv);
		$('.cal_top').html('<div style="font-size:x-large;">검색 결과 <a style="margin-left:5%; font-size:medium; cursor:pointer;" onclick="backList()">이전으로 돌아가기</a></div>');
	}
}

function backList(){
	location.href='./calendarList.do';
}

var searchAjax = function(keyword){
	$.ajax({
		url : "./searchMeetList.do",
		type : "post",
		dataType: "json",
		data : {"keyword":keyword},
		success : function(msg){
				var temp = "";
				for(var i = 0; i < msg.length; i++) {
				    temp+= '<tr data-toggle="modal" data-target="#detailView2" onclick="clickTitle('+msg[i].seq+')">';
			    	temp+= '<td>' + (Number(msg.length)-i) + '</td>';
				    temp+= '<td>' + msg[i].title+ '</td>';
				    temp+= '<td>' + msg[i].name+ '</td>';
				    temp+= '<td>' + msg[i].phone + '</td>';
				    if (msg[i].content==null) {
				    	temp+= "<td style='color: lightgray;'>내용 없음</td>";
					}else{
						temp+= '<td>' + msg[i].content + '</td>';
					}
				    temp+= '<td>' + msg[i].start + '</td></tr>';
				}
				$('.table > tbody').html(temp);
		},
		error : function(){
			alert("searchMeetList.do 잘못된 요청입니다.");
		}
	});
}

function clickTitle(seq){
	$.ajax({
		url : "./selectOne.do",
		data : { "id" : seq },
		dataType : "json",
		type : "post",
		success : function(msg){
			$('#dateID').val(msg.id);
			$('#title').val(msg.title);
			$('#name').val(msg.name);
			$('#phone').val(msg.phone);
			$('#content').val(msg.content);
			$('#datepicker-input').val(msg.start);
			
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
			$('.tui-timepicker-colon').css('display','none');
			$('.tui-timepicker-minute').css('display','none');
			$("#detailView").modal();
		},
		error : function(){
			alert("selectOne.do 잘못된 요청입니다.");
		}
	});
}

//수정
function modify() {
	var id = $('#dateID').val();
	var title = $('#title').val();
	var content = $('#content').val();
	var start = $("#datepicker-input").val();
	
	if (start.substring(17)=="PM" && start.substring(11, 13) != "12") {
		var s = Number(start.substring(11, 13))+12;
	}else {
		var s = start.substring(11, 13);
	}
	
	start = start.substring(0, 4) +
		start.substring(5, 7) +
		start.substring(8, 10) + s;
	
	var A = $("#datepicker-input").val().substr(17,2);
	var hh = $("#datepicker-input").val().substr(11,2);
	if (A=="AM" && hh == 12) {
		hh = 0;
	}else if (A=="PM" && hh != 12) {
		hh = Number(hh)+12;
	}
	var yyyy = $("#datepicker-input").val().substr(0,4);
	var mm = $("#datepicker-input").val().substr(5,2);
	var dd = $("#datepicker-input").val().substr(8,2);
	var dateVal = new Date(yyyy, mm-1, dd, hh);
	var today = new Date();
	
	if (hh<9 || hh>17) {
		swal({
			title: " ",
			text: "상담 시간은 09:00 AM ~ 17:00 PM 사이에 가능합니다."
		});
		return false;
	}else if (dateVal<today) {
		swal({
			title: " ",
			text: "상담 시간을 다시 설정해 주세요. \n 현재 시각 이후에 가능합니다."
		});
		return false;
	}
	
	$.ajax({
		url: "./update.do",
		type: "post",
		dataType: "json",
		data:
		{
			"content": content,
			"title": title,
			"start": start,
			"id" : id
		},
		success: function(msg) {
			if (msg.iMsg=="false") {
				swal({
					title: " ",
					text: "수정 권한이 없습니다.",
					showConfirmButton: false,
					timer: 1500
				});
				location.reload();
			}else {
				swal({
					title: "Success",
					text: "수정 완료되었습니다.",
					type: "success",
					showConfirmButton: false,
					timer: 1500
				});
			}
			var timer = setInterval(function() {
//				clickTitle(id);
				$('.modal').on('hidden.bs.modal', function (e) {
					location.href="./calendarList.do";
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
    				url : "./delete.do",
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
        					location.href="./calendarList.do";
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
		url:"./loadList.do",
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
				text+='<tr><td colspan="6" style="color:#a8a8a8; text-align:center;">- - - &nbsp;&nbsp;&nbsp;면담 일정이 없습니다.&nbsp;&nbsp;&nbsp; - - -</td></tr>'
			}
			var count = temp.length;
			for (var i = 0; i < temp.length; i++) {
				text+= '<tr><td data-toggle="modal" data-target="#detailView2" onclick="clickTitle('+temp[i].id+')">' + (count--) + '</td>';
				text+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle('+temp[i].id+')">' + temp[i].title+ '</td>';
				text+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle('+temp[i].id+')">' + temp[i].name+ '</td>';
				text+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle('+temp[i].id+')">' + temp[i].phone+ '</td>';
				if (temp[i].content==null) {
					text+= '<td data-toggle="modal" data-target="#detailView2" style="color: lightgray;" onclick="clickTitle('+temp[i].id+')">내용 없음</td>';
				}else{
					text+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle('+temp[i].id+')">' + temp[i].content+ '</td>';
				}
				text+= '<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle('+temp[i].id+')">' + temp[i].start+ '</td></tr>';
			}
			$('.table > tbody').html(text);
		},
		error:function(){
			alert("loadList.do error");
		}
	});
}