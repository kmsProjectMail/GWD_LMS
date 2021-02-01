<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../header.jsp" %>
	
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>	
<style type="text/css">
	table,tr,td{
		border : 1px solid black;
	}
	table{
		width: 100%;
		height: 100px;
		text-align: center;
	}
	thead th{
		text-align: center;
		background-color: #394759;
		color: white;
		
	}

</style>
	
	<link rel="stylesheet" type="text/css" href="css/main.css">

</head>
<body>
	<%@include file="../index.jsp" %>
	
	<div id ="container">
		<c:choose>
			<c:when test="${search != null}">
				<div id = "viewheader">
					<img alt="bar" src="./images/hrd/bar.png"><h3>검색 문서 목록</h3>
				</div>
				<div style="float: right;">
					<c:url value="/authorizationBranch.do?${_csrf.parameterName}=${_csrf.token}" var="url"/>
					<form:form action="${url}" method="get">
						<select class ='area form-control' style="display:inline-block; position:relative;width: 100px" class = "searchMove" name="search">
							<option value="title">제목</option>
							<option value="content">내용</option>
						</select>
						<input type="text" class="searchKey form-control" style="display:inline-block; position:relative; width:200px; border : 1px solid black; background-image: url('./images/hrd/Search.png'); background-repeat: no-repeat;" name="searchValue"  value="${search}">
						<input type="hidden" name="branch" value="search">
						<input type="submit" class="btn" style="display: inline-block;" value="검색">
					</form:form>
					</div>
					<br><br>
				<c:choose>
					<c:when test="${fn:length(incomplete) eq 0}">
						없음
					</c:when>
					<c:otherwise>
				<table>
					<thead>
					<tr>
						<th>문서번호</th>
						<th>제목</th>
						<th>기안자</th>
						<th>기안일</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${incomplete}" var="i">
							<tr>
								<td>${i.authorization_seq}</td>
								
								<td><a href='<c:url value="/documentDetail.do?seq=${i.authorization_seq}"/>'>${i.title}</a></td>
								<td>${i.id}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${i.regdate}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
					</c:otherwise>
				</c:choose>
			</c:when>

			
			<c:when test="${complete != null}">
			<!-- 처리 완료 목록 -->
				<div id = "viewheader">
					<img alt="bar" src="./images/hrd/bar.png"><h3>처리 완료 문서 목록</h3>
				</div>
				<div style="text-align: right;">
					<c:url value="/authorizationBranch.do?${_csrf.parameterName}=${_csrf.token}" var="url"/>
					<form:form action="${url}" method="get">
						<select class ='area form-control' style="display:inline-block; position:relative;width: 100px" name="search">
							<option value="title">제목</option>
							<option value="content">내용</option>
						</select>
						<input type="text" class="searchKey form-control" style="display:inline-block; position:relative; width:200px; border : 1px solid black; background-image: url('./images/hrd/Search.png'); background-repeat: no-repeat;" name="searchValue" value="${search}">
						<input type="hidden" name="branch" value="search">
						<input type="submit" class="btn" style="display: inline-block;" value="검색">
					</form:form>
					</div>
					<br><br>
				<c:choose>
					<c:when test="${fn:length(complete) eq 0}">
						없음
					</c:when>
					<c:otherwise>
				<table>
					<thead>
					<tr class="table100-head">
						<th style="width: 80px;" >문서번호</th>
						<th style="width: 280px;">제목</th>
						<th style="width: 192px;">기안자</th>
						<th style="width: 260px;">기안일</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${complete}" var="c">
							<tr>
								<td>G0000${c.authorization_seq}</td>
								<td><a href='<c:url value="/documentDetail.do?seq=${c.authorization_seq}"/>'>${c.title}</a></td>
								<td>${c.id}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${c.regdate}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="paging" style="text-align: center;">
				<ul class="pagination">
				<c:if test="${paging.getStartPage() gt 1}">
				<li><a href="./authorizationBranch.do?branch=complete&page=1">◁◁</a></li>
				</c:if>
				<c:if test="${paging.getStartPage() gt 1}">
					<c:choose>
						<c:when test="${paging.getStartPage() - paging.getCountPage() le 0}">
						<li><a href="./authorizationBranch.do?branch=complete&page=1">&lt;</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="./authorizationBranch.do?branch=complete&page=${paging.getStartPage() - paging.getCountPage()}">&lt;</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:forEach begin="${paging.getStartPage()}" end="${paging.getEndPage()}" var="i" step="1">
				<li><a ${i == paging.getPage() ? "style='color: forestgreen; font-weight: bold;'" : ""} href="./authorizationBranch.do?branch=complete&page=${i}">
						&nbsp;&nbsp;&nbsp;${i}&nbsp;&nbsp;&nbsp;
					</a></li>
				</c:forEach>
				<c:if test="${paging.getPage() lt paging.getTotalPage()}">
					<c:choose>
						<c:when test="${paging.getStartPage() + paging.getCountPage() gt paging.getTotalPage()}">
						<li><a href="./authorizationBranch.do?branch=complete&page=${paging.getTotalPage()}">&gt;</a></li>		
						</c:when>
						<c:otherwise>
						<li><a href="./authorizationBranch.do?branch=complete&page=${paging.getStartPage() + paging.getCountPage()}">&gt;</a></li>		
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${paging.getEndPage() lt paging.getTotalPage()}">
				<li><a href="./authorizationBranch.do?branch=complete&page=${paging.getTotalPage()}">▶▶</a></li>
				</c:if>
				</ul>
			</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			
			<c:when test="${incomplete != null}">
			<!-- 미처리 완료 목록 -->
				<div id = "viewheader">
					<img alt="bar" src="./images/hrd/bar.png"><h3>미처리 문서 목록</h3>
				</div>
				<div style="float: right;">
					<c:url value="/authorizationBranch.do?${_csrf.parameterName}=${_csrf.token}" var="url"/>
					<form:form  action="${url}" method="get">
						<select class ='area form-control' style="display:inline-block; position:relative;width: 100px" name="search">
							<option value="title">제목</option>
							<option value="content">내용</option>
						</select>
						<input type="text" class="searchKey form-control" style="display:inline-block; position:relative; width:200px; border : 1px solid black; background-image: url('./images/hrd/Search.png'); background-repeat: no-repeat;" name="searchValue"  value="${search}">
						<input type="hidden" name="branch" value="search">
						<input type="submit" class="btn" style="display: inline-block;" value="검색">
					</form:form>
					</div>
					<br><br>
				<c:choose>
					<c:when test="${fn:length(incomplete) eq 0}">
						없음
					</c:when>
					<c:otherwise>
				<table >
					<thead>
					<tr>
						<th>문서번호</th>
						<th>제목</th>
						<th>기안자</th>
						<th>기안일</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${incomplete}" var="i">
							<tr>
								<td>${i.authorization_seq}</td>
								
								<td><a href='<c:url value="/documentDetail.do?seq=${i.authorization_seq}"/>'>${i.title}</a></td>
								<td>${i.id}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${i.regdate}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="paging" style="text-align: center;">
				<ul class="pagination">
				<c:if test="${paging.getStartPage() gt 1}">
				<li><a href="./authorizationBranch.do?branch=incomplete&page=1">◁◁</a></li>
				</c:if>
				<c:if test="${paging.getStartPage() gt 1}">
					<c:choose>
						<c:when test="${paging.getStartPage() - paging.getCountPage() le 0}">
						<li><a href="./authorizationBranch.do?branch=incomplete&page=1">&lt;</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="./authorizationBranch.do?branch=incomplete&page=${paging.getStartPage() - paging.getCountPage()}">&lt;</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:forEach begin="${paging.getStartPage()}" end="${paging.getEndPage()}" var="i" step="1">
				<li><a ${i == paging.getPage() ? "style='color: forestgreen; font-weight: bold;'" : ""} href="./authorizationBranch.do?branch=incomplete&page=${i}">
						&nbsp;&nbsp;&nbsp;${i}&nbsp;&nbsp;&nbsp;
					</a></li>
				</c:forEach>
				<c:if test="${paging.getPage() lt paging.getTotalPage()}">
					<c:choose>
						<c:when test="${paging.getStartPage() + paging.getCountPage() gt paging.getTotalPage()}">
						<li><a href="./authorizationBranch.do?branch=incomplete&page=${paging.getTotalPage()}">&gt;</a></li>		
						</c:when>
						<c:otherwise>
						<li><a href="./authorizationBranch.do?branch=incomplete&page=${paging.getStartPage() + paging.getCountPage()}">&gt;</a></li>		
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${paging.getEndPage() lt paging.getTotalPage()}">
				<li><a href="./authorizationBranch.do?branch=incomplete&page=${paging.getTotalPage()}">▶▶</a></li>
				</c:if>
				</ul>
			</div>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
		
	</div>
	<script src="js/main.js"></script>
	<script type="text/javascript">
	function openStamp() {
		var title = "도장 등록";
		var url = "./stamp.do";
		var attr = "width=400px, height=500px";
		window.open(url,title,attr);
	}
	</script>
</body>
</html>