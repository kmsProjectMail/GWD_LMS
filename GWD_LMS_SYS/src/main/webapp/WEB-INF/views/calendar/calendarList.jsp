<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp" %>
<link rel="stylesheet" type="text/css" href="/GWD_LMS_SYS/dist/tui-calendar.css">
<link rel="stylesheet" type="text/css" href="/GWD_LMS_SYS/css/sweetalert.css">
<script type="text/javascript" src="/GWD_LMS_SYS/resources/js/calendarList.js"></script>
<link rel="stylesheet" href="/GWD_LMS_SYS/css/calendar_calendar.css">
<style type="text/css">
tr:hover{
	cursor: pointer;
}
#thTr:hover {
	cursor: default;
}
</style>
</head>
<%@ include file="../index.jsp" %>
<body>
<div class="maincontainer" style="margin-left: 220px;">

<div class="container">
	<div class="form-inline my-2 my-lg-0" style="float: right; width: 30%; margin: 5%;">
      <input class="form-control mr-sm-2" type="search" id="keyword" name="keyword" placeholder="아이디 또는 이름" aria-label="Search"
      onkeypress="if(event.keyCode==13) return searchMeet();">
      <button class="btn btn-outline-success my-2 my-sm-0 btn-primary" onclick="searchMeet()">검색</button>
    </div>
    
    <div class="cal_top" style="margin:40px;">
	    <button id="movePrevMonth" type="button" class="btn btn-default btn-sm move-day" onclick="movePrevMonth()">
	    	<i class="calendar-icon ic-arrow-line-left"></i>
	    </button>
	        <span id="cal_top_year" style="font-size: large;"></span>
	        <span id="cal_top_month" style="font-size: xx-large;"></span>
	    <button id="moveNextMonth" type="button" class="btn btn-default btn-sm move-day" onclick="moveNextMonth()">
	   		<i class="calendar-icon ic-arrow-line-right"></i>
	    </button>
	</div>
	
	<div style="overflow:auto; height:600px; width:100%;">
	<table class="table table-hover">
		<thead>
		<tr id="thTr">
			<th>NO</th>
			<th>아이디</th>
			<th>성함</th>
			<th>번호</th>
			<th>내용</th>
			<th>면담 날짜</th>
		</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${jlist}" var="jlist" varStatus="vs">
			<tr data-toggle="modal" data-target="#detailView" onclick="clickTitle(${jlist.seq})">
				<td>${fn:length(lists)-vs.index}</td>
				<td><c:out value="${jlist.title}"/></td>
				<td><c:out value="${jlist.name}"/></td>
				<td><c:out value="${jlist.phone}"/></td>
				<c:choose>
					<c:when test="${jlist.content eq null}">
						<td style="color: lightgray;">내용 없음</td>
					</c:when>
					<c:otherwise>
						<td ><c:out value="${jlist.content}"/></td>
					</c:otherwise>
				</c:choose>
				<td >${jlist.start}</td>
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>

<div id="detailView" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <h4 class="modal-title" style="text-align: center; position: relative; font-weight: bold;">면담 수정</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true" style="position: absolute; top: 20px; right: 30px;">&times;</span>
          </button>
      </div>
      <div class="modal-body">
      	 <form class="form-horizontal" name="frm">
			<input type="hidden" id="dateID" val =""> 
              	<div class="form-group">
                  <label for="title" class="col-sm-2 control-label">아이디</label>
                  <div class="col-sm-9">
                      <input class="form-control" type="text" name="title" id="title" value="" readonly/>
                  </div>
              	</div>
            	<div class="form-group">
                  <label for="name" class="col-sm-2 control-label">성 함</label>
                  <div class="col-sm-9">
                      <input class="form-control" type="text" name="name" id="name" value="" readonly/>
                  </div>
              	</div>
             	<div class="form-group">
                  <label for="phone" class="col-sm-2 control-label">번 호</label>
                  <div class="col-sm-9">
                      <input class="form-control" type="text" name="phone" id="phone" value="" readonly/>
                  </div>
              	</div>
              	<div class="form-group">
                  <label for="content" class="col-sm-2 control-label">내 용</label>
                  <div class="col-sm-9">
                      <textarea class="form-control" rows="5" style="width : 100%; resize: none;" id="content" class="content"></textarea>
                  </div>
              	</div>
				<div class="form-group">
                 	<label for="startDate" class="col-sm-2 control-label">면담 날짜</label>
                 	<div class="col-sm-9">
                	<div class="tui-datepicker-input tui-datetime-input tui-has-focus" style="float: left; width: 40%;">
			          <input type="text" id="datepicker-input" aria-label="Date-Time">
			          <span class="tui-ico-date"></span>
		      		</div>
		      		<div id="wrapper" style="margin-top: -1px;"></div>
             	  	</div>
				</div>
          </form>
      </div>
      <div class="modal-footer">
          <div class="col-sm-11">
              <div class="col-sm-offset-2">
                  <button id="btn-save-schedule" type="button" class="btn btn-default col-sm-4 btn-primary" style="float: left; width: 40%;" onclick="modify()">
                  	<img alt="create" src="../images/calendar_create.png" style="width: 20px; margin-right: 5%;">완료</button>
              </div>
              <div class="col-sm-offset-2">
                  <button id="btn-delete-schedule" type="button" class="btn btn-default col-sm-4" style="float: right; width: 40%;" onclick="deleteSchedule()">
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