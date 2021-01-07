<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<title>일정 조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.min.js"></script>
<script src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css">
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css">
<link rel="stylesheet" type="text/css" href="../dist/tui-calendar.css">
<script type="text/javascript" src="../resources/js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/sweetalert.css">
<script type="text/javascript" src="../resources/js/calendarList.js"></script>
<link rel="stylesheet" href="../css/calendar_calendar.css">
<link rel="stylesheet" href="../css/calendar_calendarMyList.css">
<style type="text/css">
tr:hover{
	cursor: pointer;
}

#thTr:hover {
	cursor: default;
}
</style>
</head>
<body>
<div class="container">
	<div class="form-inline my-2 my-lg-0" style="float: right; width: 30%; margin: 5%;">
      <input class="form-control mr-sm-2" type="search" id="keyword" name="keyword" placeholder="제목 또는 내용" aria-label="Search"
      onkeypress="if(event.keyCode==13) return searchShedule();">
      <button class="btn btn-outline-success my-2 my-sm-0 btn-primary" onclick="searchShedule()">Search</button>
    </div>

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
			<c:forEach items="${jlist}" var="jlist">
			<tr>
				<c:choose>
					<c:when test="${jlist.alarm_check eq 'N'}">
						<td>
							<input type="checkbox" id="${jlist.seq}" name="chk" onclick="chk(this)">
							<label id="onoffCheck" for="${jlist.seq}"><span></span></label>
						</td>
					</c:when>
					<c:otherwise>
						<td>
							<input type="checkbox" id="${jlist.seq}" name="chk" checked onclick="chk(this)">
							<label id="onoffCheck" for="${jlist.seq}"><span></span></label>
						</td>
					</c:otherwise>
				</c:choose>
				
				<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2(${jlist.seq})">
					<c:choose>
						<c:when test="${jlist.calId eq '1'}">면담</c:when>
						<c:when test="${jlist.calId eq '2'}">내 일정</c:when>
						<c:when test="${jlist.calId eq '3'}">학원</c:when>
						<c:when test="${jlist.calId eq '4'}">기념일</c:when>
						<c:when test="${jlist.calId eq '5'}">기타</c:when>
						<c:when test="${jlist.calId eq '6'}">공휴일</c:when>
					</c:choose>
				</td>
				<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2(${jlist.seq})">${jlist.title}</td>
				<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2(${jlist.seq})">${jlist.start}</td>
				<td data-toggle="modal" data-target="#detailView2" onclick="clickTitle2(${jlist.seq})">${jlist.end}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
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
                   <div class="col-sm-2 control-label" style="font-weight: bold;">장 소</div>
                   <div class="col-sm-9">
                       <select class="form-control" name="location" id="location">
                                <option value="">-- Select --</option>
                       </select>
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
                  <button id="btn-save-schedule" type="button" class="btn btn-default col-sm-4 btn-primary" style="float: left; width: 40%;" onclick="modify2()">
                  	<img alt="create" src="../images/calendar_create.png" style="width: 20px; margin-right: 5%;">Save</button>
              </div>
              <div class="col-sm-offset-2">
                  <button id="btn-delete-schedule" type="button" class="btn btn-default col-sm-4" style="float: right; width: 40%;" onclick="deleteSchedule()">
                  	<img alt="delete" src="../images/calendar_delete.png" style="width: 20px; margin-right: 5%;">Delete</button>
              </div>
          </div>
   	 </div>
    </div>
  </div>
</div>
</body>
</html>