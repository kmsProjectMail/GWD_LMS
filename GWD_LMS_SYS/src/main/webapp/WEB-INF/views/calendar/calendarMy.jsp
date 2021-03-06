<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@include file="../header.jsp" %>






</head>
<%@include file ="../index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">
<div class="col-xs-2" style="margin-top: 63px;">
	<div>
      <div id="calendarList" class="lnb-calendars-d1">
	  </div>
    </div>
</div>

<div id="calendarList" class="lnb-calendars-d1 list-group"></div>
<div class="col-xs-9">
	<div id="menu">
	    <span id="menu-navi">
			<button type="button" class="btn btn-sm move-2week" data-action="move-2week">2주 보기</button>
			<button type="button" class="btn btn-sm move-month" data-action="move-month">월간 보기</button>
			<button type="button" class="btn btn-sm move-today" data-action="move-today">오늘</button>
			<button type="button" class="btn btn-sm move-day" data-action="move-prev">
				<i class="calendar-icon ic-arrow-line-left" data-action="move-prev"></i>
			</button>
	      	<button type="button" class="btn btn-sm move-day" data-action="move-next">
	        	<i class="calendar-icon ic-arrow-line-right" data-action="move-next"></i>
	      	</button>
	    </span>
	    <span id="renderRange" class="render-range"></span>
	</div>
	<div id="calendar" style="height: 600px;"></div>
</div>
<div id="createSchedule" class="modal fade" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" style="text-align: center; position: relative; font-weight: bold;">일정 생성</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" style="position: absolute; top: 20px; right: 30px;">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                <input type="hidden" id="dateID" val =""> 
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">제 목</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" name="title" id="title"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">내 용</label>
                        <div class="col-sm-9">
                            <textarea class="form-control" rows="5" style="width : 100%; resize: none;" id="content" class="content"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user" class="col-sm-2 control-label">태 그</label>
                        <div class="col-sm-9">
                            <select class="form-control" name="user" id="user">
                                <option value="0">-- Select --</option>
                                <option value="2" selected>내 일정</option>
                                <option value="3">학원</option>
                                <option value="4">기념일</option>
                                <option value="5">기타</option>
                                <option value="6">공휴일</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="startDate" class="col-sm-2 control-label">날 짜</label>
                        <div class="col-sm-9">
	                        <div class="tui-datepicker-input tui-datetime-input tui-has-focus" style="float: left; width: 40%;">
			                	<input type="text" id="datepicker-input" aria-label="Date-Time">
			                	<span class="tui-ico-date"></span>
	            			</div>
	            			<div id="wrapper" style="margin-top: -1px;"></div>
							<span style="text-align: center; width: 20%; float: left;">~</span>
			               	<div class="tui-datepicker-input tui-datetime-input tui-has-focus" style="width: 40%;">
				               	<input type="text" id="datepicker-input2" aria-label="Date-Time">
				               	<span class="tui-ico-date"></span>
			               	</div>
							<div id="wrapper2" style="margin-top: -1px; width: 100px;"></div>
                    	</div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="col-sm-11">
                    <div class="col-sm-offset-2">
                        <button id="btn-save-schedule" type="button" class="btn col-sm-4 " style="float: left;">완료</button>
                    </div>
                    <div class="col-sm-offset-2">
                        <button id="btn-delete-schedule" type="button" class="btn col-sm-4" style="float: right; width: 40%;">
                        	<img alt="delete" src="../images/calendar_delete.png" style="width: 20px; margin-right: 5%;">삭제
                        </button>
                    </div>
                </div>
   			</div>
        </div>
    </div>
</div>
</div>
</body>
<script id="template-lnb-calendars-item" type="x-tmpl-mustache">
  {{#users}}
    <div class="lnb-calendars-item" style=" background-color: #f4f4f4; padding:5% 10%;">
        <label>
            <input type="checkbox" class="tui-full-calendar-checkbox-round" value="{{ id }}" checked>
            <span style="border-color: {{ color }}; background-color: {{ color }};" data-visible="visible"></span>
            <span>{{ name }}</span>
        </label>
    </div>
    {{/users}}
</script>
<script src="/GWD_LMS_SYS/dist/tui-calendar.js"></script>
<script src="/GWD_LMS_SYS/resources/js/calendarMy.js"></script>
</html>