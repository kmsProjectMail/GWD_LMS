<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.jsp" %>
<script type="text/javascript" src="/GWD_LMS_SYS/resources/js/calendarMyList.js"></script>
<link rel="stylesheet" href="/GWD_LMS_SYS/css/calendar_calendar.css">
<link rel="stylesheet" href="/GWD_LMS_SYS/css/calendar_calendarMyList.css">
<style type="text/css">
tr:hover{
	cursor: pointer;
}

#thTr:hover {
	cursor: default;
}
</style>
</head>
<%@include file = "../index.jsp" %>

<body>
<div class="maincontainer" style="margin-left: 220px;">
<div class="container">
	<div class="form-inline my-2 my-lg-0" style="float: right; width: 30%; margin: 5%;">
      <input class="form-control mr-sm-2" type="search" id="keyword" name="keyword" placeholder="제목 또는 내용" aria-label="Search"
      onkeypress="if(event.keyCode==13) return searchShedule();">
      <button class="btn btn-outline-success my-2 my-sm-0 " onclick="searchShedule()">검색</button>
    </div>
    
	<div class="cal_top" style="margin:40px;">
	    <button id="movePrevMonth" type="button" class="btn btn-sm move-day" onclick="movePrevMonth()">
	    	<i class="calendar-icon ic-arrow-line-left"></i>
	    </button>
	        <span id="cal_top_year" style="font-size: large;"></span>
	        <span id="cal_top_month" style="font-size: xx-large;"></span>
	    <button id="moveNextMonth" type="button" class="btn btn-sm move-day" onclick="moveNextMonth()">
	   		<i class="calendar-icon ic-arrow-line-right"></i>
	    </button>
	</div>
	
	<div style="overflow:auto; height:450px; width:100%;">
	<table class="table table-hover">
		<thead>
		<tr id="thTr">
			<th>알람</th>
			<th>태그</th>
			<th>제목</th>
			<th>시작 날짜</th>
			<th>종료 날짜</th>
		</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
	</div>
</div>






<div id="detailView2" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <h4 class="modal-title" style="text-align: center; position: relative; font-weight: bold;">Update Schedule</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true" style="position: absolute; top: 20px; right: 30px;">&times;</span>
          </button>
      </div>
      <div class="modal-body">
      	 <form class="form-horizontal" name="frm">
               <input type="hidden" id="dateID" val =""> 
               <div class="form-group">
                   <div class="col-sm-2 control-label" style="font-weight: bold;">제 목</div>
                   <div class="col-sm-9">
                       <input class="form-control" type="text" name="title" id="title" value=""/>
                   </div>
               </div>
               <div class="form-group">
                   <div class="col-sm-2 control-label" style="font-weight: bold;">내 용</div>
                   <div class="col-sm-9">
                       <textarea class="form-control" rows="5" style="width : 100%; resize: none;" id="content" class="content"></textarea>
                   </div>
               </div>
               <div class="form-group">
                   <div class="col-sm-2 control-label" style="font-weight: bold;">태 그</div>
                   <div class="col-sm-9">
                       <select class="form-control" name="user" id="user">
                           <option value="0">-- Select --</option>
                           <option value="1">면담</option>
                           <option value="2">내 일정</option>
                           <option value="3">학원</option>
                           <option value="4">기념일</option>
                           <option value="5">기타</option>
                           <option value="6">공휴일</option>
                       </select>
                   </div>
               </div>
               <div class="form-group">
                   <div class="col-sm-2 control-label" style="font-weight: bold;">날 짜</div>
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
                  <button id="btn-save-schedule" type="button" class="btn col-sm-4 " style="float: left; width: 40%;" onclick="modify2()">
                  	<img alt="create" src="../images/calendar_create.png" style="width: 20px; margin-right: 5%;">완료</button>
              </div>
              <div class="col-sm-offset-2">
                  <button id="btn-delete-schedule" type="button" class="btn col-sm-4" style="float: right; width: 40%;" onclick="deleteSchedule()">
                  	<img alt="delete" src="../images/calendar_delete.png" style="width: 20px; margin-right: 5%;">삭제</button>
              </div>
          </div>
   	 </div>
    </div>
  </div>
</div>
</div>
</body>
</html>