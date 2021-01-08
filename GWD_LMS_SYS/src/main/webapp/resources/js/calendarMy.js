var token = $("meta[name='_csrf']").attr("content");
	  var header = $("meta[name='_csrf_header']").attr("content");
	  $(document).ajaxSend(function(e, xhr, options) {
		    xhr.setRequestHeader(header, token);
		});

//month schedule style
var MONTHLY_CUSTOM_THEME = {
	'month.schedule.borderRadius': '5px',
	'month.schedule.height': '18px',
	'month.schedule.marginTop': '2px',
	'month.schedule.marginLeft': '10px',
	'month.schedule.marginRight': '10px',
	'common.saturday.color': '#3162ea',
	'common.backgroundColor': '#fcfcfc'
};

//캘린더 띄우기
var cal = new tui.Calendar('#calendar', {
	defaultView: 'month',
	theme: MONTHLY_CUSTOM_THEME,
	taskView: false,
	scheduleView: true,
	useCreationPopup: false,
	useDetailPopup: false,
});

//캘린더 이동 버튼
$('span#menu-navi > .btn').on('click', function(e) {
	var action = $(this).data('action');
	var year, month;
	switch (action) {
		case 'move-prev':
			cal.prev();
			break;
		case 'move-next':
			cal.next();
			break;
		case 'move-today':
			cal.today();
			break;
		case 'move-2week':
			cal.setOptions({ month: { visibleWeeksCount: 2 } }, true);
			cal.changeView('month', true);
			break;
		case 'move-month':
			cal.setOptions({ month: { visibleWeeksCount: null } }, true);
			cal.changeView('month', true);
			break;
		default:
			return;
	}
	setRenderRangeText(cal);

	e.stopPropagation();
	e.preventDefault();
	e.stopImmediatePropagation();
});

//오늘 연월(일) 찍기
function setRenderRangeText(cal) {
	var renderRange = document.getElementById('renderRange');
	var options = cal.getOptions();
	var viewName = cal.getViewName();
	var html = [];
	if (viewName === 'day') {
		html.push(moment(cal.getDate().getTime()).format('YYYY.MM.DD'));
	} else if (viewName === 'month' &&
		(!options.month.visibleWeeksCount || options.month.visibleWeeksCount > 4)) {
		html.push(moment(cal.getDate().getTime()).format('YYYY.MM'));
	} else {
		html.push(moment(cal.getDateRangeStart().getTime()).format('YYYY.MM.DD'));
		html.push(' ~ ');
		html.push(moment(cal.getDateRangeEnd().getTime()).format(' MM.DD'));
	}
	renderRange.innerHTML = html.join('');
}

//분류 리스트
function getUserData() {
    var users = [];
    var names = ['면담', '내 일정', '학원', '기념일', '기타', '공휴일'];
    var colors = ['#9e5fff', '#00a9ff', '#ff5583', '#03bd9e', '#bbdc00', '#fc1616'];
    var employ_type = ['regular', 'regular', 'regular', 'regular', 'regular','regular']
    for (var i = 0; i < 6; i++) {
        var user = {
            'name': names[i],
            'id': String(i + 1),
            'employ_type': employ_type[i],
            'color': colors[i]
        };
        users.push(user);
    }
    return users;
}

function renderLNB(users) {
	var template = $('#template-lnb-calendars-item').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, { 'users': users });
	$('#calendarList').html(rendered);
}

//분류 리스트 체크 박스
function addLNBListener(users, cal) {
	$('div.lnb-calendars-item > label').off('click');
	$('div.lnb-calendars-item > label').on('click', function(e) {
		var id = $(this).find('input').val();
		var $firstSpan = $(this).find('span').first();
		var visible = $firstSpan.data('visible');
		if (visible == 'visible') {
			$firstSpan.css('background-color', 'transparent');
			cal.toggleSchedules(id, true, true);
			$firstSpan.data('visible', 'hidden');
		} else {
			$firstSpan.css('background-color', $firstSpan.css('border-color'));
			cal.toggleSchedules(id, false, true);
			$firstSpan.data('visible', 'visible');
		}
		e.stopPropagation();
		e.preventDefault();
		e.stopImmediatePropagation();
	});
}

//분류 별 캘린더 (일정등록 팝업창에 분류 리스트)
var CalendarList = undefined;

function getCalendars(users) {
	var calendars = [];
	for (var user of users) {
		calendars.push({
			id: user['id'],
			name: user['name'],
			color: '#ffffff',
			bgColor: user['color'],
			dragBgColor: user['color'],
			borderColor: user['color'],
			checked: true
		})
	}
	CalendarList = calendars;
	return calendars;
}

//캘린더에 대한 것?
'use strict';

var CalendarList = [];

function CalendarInfo() {
	this.id = null;
	this.name = null;
	this.checked = true;
	this.color = null;
	this.bgColor = null;
	this.borderColor = null;
	this.dragBgColor = null;
}

function addCalendar(calendar) {
	CalendarList.push(calendar);
}

function findCalendar(id) {
	var found;

	CalendarList.forEach(function(calendar) {
		if (calendar.id === id) {
			found = calendar;
		}
	});
	return found || CalendarList[0];
}

function hexToRGBA(hex) {
	var radix = 16;
	var r = parseInt(hex.slice(1, 3), radix),
		g = parseInt(hex.slice(3, 5), radix),
		b = parseInt(hex.slice(5, 7), radix),
		a = parseInt(hex.slice(7, 9), radix) / 255 || 1;
	var rgba = 'rgba(' + r + ', ' + g + ', ' + b + ', ' + a + ')';

	return rgba;
}

$(document).ready(function() {
	var users = getUserData();
	renderLNB(users);
	var calendars = getCalendars(users); /* 사용자(분류) 달력 */
	cal.setCalendars(calendars);  /* 사용자(분류) 달력 */
	addLNBListener(users, cal);
	setRenderRangeText(cal);
	
	$('.modal').on('hidden.bs.modal', function (e) {
	    $(this).find('form')[0].reset()
	});
	
	//저장된 일정 불러오기    
	$.ajax({
		url: "./loadMy.do",
		type: "post",
		dataType: "json",
		success: function(msg) {
			$.each(msg, function(key, value) {
				cal.createSchedules([
					{
						id: value.id,
						calendarId: value.calendarId,
						title: value.title,
						category: value.category,
						start: value.start,
						end: value.end
					}]);
			});
		},
		error: function() {
			alert("refresh 잘못된 요청입니다.");
		}
	});
});

//스케줄에 대한 것
(function(window, Calendar) {
	cal.on({
		'clickSchedule': function(e) {
			$.ajax({
				url: "./selectOne.do",
				type: "post",
				data:
				{
					"id": e.schedule.id
				},
				success: function(msg) {
					var obj = JSON.parse(msg);
					$('#dateID').val(obj.id);
					$('#title').val(obj.title);
					$('#content').val(obj.content);
					$('#user').val(obj.calendarId);
					$("#datepicker-input").val(mileToCustomDate(obj.start));
					$("#datepicker-input2").val(mileToCustomDate(obj.end));
					$(".modal-title").html('Update Schedule');
					$("#btn-delete-schedule").css('display','block');
					$("#btn-save-schedule").html('<img alt="create" src="../images/calendar_create.png" style="width: 20px; margin-right: 5%;">Save');
					$("#btn-save-schedule").css({'float':'left','width':'40%'});
					$("#btn-save-schedule").click(modify);
					$("#btn-delete-schedule").click(deleteSchedule);
					
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
				error: function() {
					alert("selectOne 잘못된 요청입니다.");
				}
			})
			$("#createSchedule").modal();
		},
		'beforeCreateSchedule': function(e) {
			$(".tui-full-calendar-month-guide-block").remove();
			$("#createSchedule").modal();
			$("#datepicker-input").val(mileToCustomDate(e.start));
			$("#datepicker-input2").val(mileToCustomDate(e.end));
			$("#dateID").val(String(Math.random() * 100000000000000000));
			$(".modal-title").html('Create Schedule');
			$("#btn-save-schedule").html('<img alt="create" src="../images/calendar_create.png" style="width: 20px; margin-right: 5%;">Create');
			$("#btn-save-schedule").css({'float':'right','width':'45%'});
			$("#btn-delete-schedule").css('display','none');
			$("#btn-save-schedule").click(onNewSchedule);
			
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
		'beforeUpdateSchedule': function(e) {
			console.log('beforeUpdateSchedule', e);
			e.schedule.start = e.start;
			e.schedule.end = e.end;
			cal.updateSchedule(e.schedule.id, e.schedule.calendarId, e.schedule);
			
			//날짜정보만 업데이트 하는 ajax작성
			$.ajax({
				url: "./updateMy.do",
				type: "post",
				dataType: "json",
				data:
				{
					"id": e.schedule.id,
					"start": customdate(mileToCustomDate(e.start)),
					"end": customdate(mileToCustomDate(e.end)),
					"calendar_id": e.schedule.calendarId,
					"title": e.schedule.title,
					"category" : e.schedule.category
				},
				success: function(msg) {
				},
				error: function() {
					alert("save 잘못된 요청입니다.");
				}
			});
		},
		'beforeDeleteSchedule': function(e) {
			 console.log('beforeDeleteSchedule', e);
			 calendarId= e.schedule.calendarId;
	         id= e.schedule.id;
		}
	});
	function yyyymmdd(dateIn) {
		var yyyy = dateIn.getFullYear();
		var mm = dateIn.getMonth() + 1; // getMonth() is zero-based
		var dd = dateIn.getDate();
		return String(10000 * yyyy + 100 * mm + dd); // Leading zeros for mm and dd
	}
	//?
	function saveNewSchedule(scheduleData) {
		console.log('scheduleData ', scheduleData)
		var calendar = scheduleData.calendar || findCalendar(scheduleData.calendarId);
		var schedule = {
			id: String(Math.random() * 100000000000000000),
			calendarId: '1',
			title: scheduleData.title,
			isAllDay: scheduleData.isAllDay,
			start: scheduleData.start,
			end: scheduleData.end,
			category: scheduleData.isAllDay ? 'allday' : 'time',
			// dueDateClass: '',
			color: calendar.color,
			bgColor: calendar.bgColor,
			dragBgColor: calendar.bgColor,
			borderColor: calendar.borderColor,
			location: scheduleData.location,
		};
		if (calendar) {
			schedule.calendarId = calendar.calendarId;
			schedule.id = calendar.id;
			schedule.color = calendar.color;
			schedule.bgColor = calendar.bgColor;
			schedule.borderColor = calendar.borderColor;
		}

		cal.createSchedules([schedule]);

		refreshScheduleVisibility();
	}

	//timetemplate
	function getTimeTemplate(schedule, isAllDay) {
		var html = [];
		var start = moment(schedule.start.toUTCString());
		if (!isAllDay) {
			html.push('<strong>' + start.format('HH:mm') + '</strong> ');
		}
		if (schedule.isPrivate) {
			html.push('<span class="calendar-font-icon ic-lock-b"></span>');
			html.push(' Private');
		} else {
			if (schedule.isReadOnly) {
				html.push('<span class="calendar-font-icon ic-readonly-b"></span>');
			} else if (schedule.recurrenceRule) {
				html.push('<span class="calendar-font-icon ic-repeat-b"></span>');
			} else if (schedule.attendees.length) {
				html.push('<span class="calendar-font-icon ic-user-b"></span>');
			} else if (schedule.location) {
				html.push('<span class="calendar-font-icon ic-location-b"></span>');
			}
			html.push(' ' + schedule.title);
		}

		return html.join('');
	}

	function onChangeNewScheduleCalendar(e) {
		var target = $(e.target).closest('a[role="menuitem"]')[0];
		var calendarId = getDataAction(target);
		changeNewScheduleCalendar(calendarId);
	}

	function changeNewScheduleCalendar(calendarId) {
		var calendarNameElement = document.getElementById('calendarName');
		var calendar = findCalendar(calendarId);
		var html = [];

		html.push('<span class="calendar-bar" style="background-color: ' + calendar.bgColor + '; border-color:' + calendar.borderColor + ';"></span>');
		html.push('<span class="calendar-name">' + calendar.name + '</span>');

		calendarNameElement.innerHTML = html.join('');

		selectedCalendar = calendar;
	}

	function refreshScheduleVisibility() {
		var calendarElements = Array.prototype.slice.call(document.querySelectorAll('#calendarList input'));

		CalendarList.forEach(function(calendar) {
			cal.toggleSchedules(calendar.id, !calendar.checked, false);
		});

		cal.render(true);

		calendarElements.forEach(function(input) {
			var span = input.nextElementSibling;
			span.style.backgroundColor = input.checked ? span.style.borderColor : 'transparent';
		});
	}

	function setEventListener() {
		$('#dropdownMenu-calendars-list').on('click', onChangeNewScheduleCalendar);
		window.addEventListener('resize', resizeThrottled);
	}

	function getDataAction(target) {
		return target.dataset ? target.dataset.action : target.getAttribute('data-action');
	}
	resizeThrottled = tui.util.throttle(function() {
		cal.render();
	}, 50);
	window.cal = cal;

	setEventListener();
})(window, tui.Calendar);

//등록
function onNewSchedule() {
	
	var schedule = {
		id: $('#dateID').val(),
		title: $('#title').val(),
		content: $('#content').val(),
		calendarId: $('#user').val(),
		start: $("#datepicker-input").val(),
		end: $("#datepicker-input2").val(),
	};
	
	calendarId = schedule.calendarId;
	id = schedule.id;
	content = schedule.content;
	title = schedule.title;
	
	if (schedule.start.substring(17)=="PM" && schedule.start.substring(11, 13) != "12") {
		var s = Number(schedule.start.substring(11, 13))+12;
	}else if (schedule.start.substring(17)=="AM" && schedule.start.substring(11, 13) == "12") {
		var s = "00";
	}
	else {
		var s = schedule.start.substring(11, 13);
	}
	
	if (schedule.end.substring(17)=="PM" && schedule.end.substring(11, 13) != "12") {
		var e = Number(schedule.end.substring(11, 13))+12;
	}else if (schedule.end.substring(17)=="AM" && schedule.end.substring(11, 13) == "12") {
		var e = "00";
	}else {
		var e = schedule.end.substring(11, 13);
	}
	
	start = schedule.start.substring(0, 4) +
		schedule.start.substring(5, 7) +
		schedule.start.substring(8, 10) + s;
	
	end = schedule.end.substring(0, 4) +
		schedule.end.substring(5, 7) +
		schedule.end.substring(8, 10) + e;
	 
//creation-guide
	category = schedule.category;
	color = schedule.color;
	bgColor = schedule.bgColor;
	borderColor = schedule.borderColor;
	
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
		url: "./saveMy.do",
		type: "post",
		dataType: "json",
		data:
		{
			"calendarId": calendarId,
			"id": id,
			"title": title,
			"content": content,
			"start": start,
			"end": end,
			"category": "time"
		},
		success: function(msg) {
			cal.createSchedules([schedule]);
			console.log('beforeCreateSchedule', [schedule]);			
			location.href="./calendarMy.do";
		},
		error: function() {
			alert("save 잘못된 요청입니다.");
		}
	});
	$("#createSchedule").modal('hide');

}

//수정
function modify() {
	var schedule = {
		id: $('#dateID').val(),
		title: $('#title').val(),
		content: $('#content').val(),
		calendarId: $('#user').val(),
		start: $("#datepicker-input").val(),
		end: $("#datepicker-input2").val(),
	};
	calendarId = schedule.calendarId;
	id = schedule.id;
	content = schedule.content;
	title = schedule.title;
	if (schedule.start.substring(17)=="PM" && schedule.start.substring(11, 13) != "12") {
		var s = Number(schedule.start.substring(11, 13))+12;
	}else {
		var s = schedule.start.substring(11, 13);
	}
	
	if (schedule.end.substring(17)=="PM" && schedule.end.substring(11, 13) != "12") {
		var e = Number(schedule.end.substring(11, 13))+12;
	}else {
		var e = schedule.end.substring(11, 13);
	}
	
	start = schedule.start.substring(0, 4) +
		schedule.start.substring(5, 7) +
		schedule.start.substring(8, 10) + s;
	
	end = schedule.end.substring(0, 4) +
		schedule.end.substring(5, 7) +
		schedule.end.substring(8, 10) + e;
	
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
		alert("수정 시간을 다시 설정해 주세요.");
		return false;
	}else if ($('#user').val() == 0) {
		alert("태그를 선택해 주세요.");
		return false;
	}
	
	$.ajax({
		url: "./updateMy.do",
		type: "post",
		dataType: "json",
		data:
		{
			"calendar_id": calendarId,
			"id": id,
			"title": title,
			"content": content,
			"start": start,
			"end": end,
			"category": "time"
		},
		success: function(msg) {
   	        cal.createSchedules([schedule]);
			console.log('beforeCreateSchedule', [schedule]);
			location.href="./calendarMy.do";
			
		},
		error: function() {
			alert("save 잘못된 요청입니다.");
		}
	});
	$(".tui-full-calendar-month-guide-block").remove();
}

function customdate(date){
	var result = ""
	result += date.substring(0, 4)+date.substring(5, 7) +date.substring(8, 10) 
	return result;
}

function mileToCustomDate(mile){
	var date = new Date(mile);
	var year = date.getFullYear();
	var month = date.getUTCMonth() + 1;
	var day = date.getUTCDate();
	var hours = date.getHours();
	
	if (hours < 10) {
		hours = "0"+hours+":00 AM";
	}else if (hours >= 10 && hours <12) {
		hours += ":00 AM";
	}else if (hours === 12) {
		hours += ":00 PM";
	}else if (hours > 12 && hours < 22){
		hours -= 12;
		hours = "0"+hours+":00 PM";
	}else if (hours > 21 && hours <24) {
		hours -= 12;
	    hours += ":00 PM";
	}else if (hours === 24) {
		hours -= 24;
		hours = "0"+hours+":00 AM";
	}
	
	if (date.getUTCHours()>=15) {
		day += 1;
	}
	
	month = formatTime(month);
	day = formatTime(day);

	return year + "-" + month + "-" + day + " " + hours;
}

function formatTime(num){
	return num<10 ? "0"+num : num;
}

function deleteSchedule(){
	var schedule = {
			id: $('#dateID').val(),
		};
	id = schedule.id;
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
		    		data :{
		    			"id" : id
		    		},
			   		success : function(msg){
			   			swal({
							title: "Success",
							text: "삭제 완료되었습니다.",
							type: "success",
							showConfirmButton: false,
							timer: 1500
						});
						var timer = setInterval(function() {
							cal.deleteSchedule(schedule.id, schedule.calendarId);
							location.href="./calendarMy.do";
							clearInterval(timer); 
						},2000);
			   		},
		    		error : function() {
		    			alert("잘못된 요청입니다.");
		    		}
		    	});
  	 }else{
	    	return false;
	    }
	});
}