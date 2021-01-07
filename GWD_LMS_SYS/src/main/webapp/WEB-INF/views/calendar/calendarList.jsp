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
<title>면담 조회</title>
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
      <input class="form-control mr-sm-2" type="search" id="keyword" name="keyword" placeholder="아이디 또는 이름" aria-label="Search"
      onkeypress="if(event.keyCode==13) return searchMeet();">
      <button class="btn btn-outline-success my-2 my-sm-0 btn-primary" onclick="searchMeet()">Search</button>
    </div>

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

<div id="detailView" class="modal fade" role="dialog">
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