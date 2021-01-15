<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../header.jsp" %>
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
</head>
<body>
	<%@include file="../index.jsp" %>
	
	<div id ="container">
		<input type="button" value="글작성" onclick='location.href="<c:url value='/documentWriteMove.do'/>"'>
		<input type="button" value="도장등록" onclick='openStamp()'>
		
		<c:url value="/authorizationBranch.do?branch=inComplete" var="iUrl"/>
		<a href="${iUrl}">미처리 목록 보기</a>
		<c:url value="/authorizationBranch.do?branch=complete" var="cUrl"/>
		<a href="${cUrl}">완료 목록 보기</a>
		
		
		
		<c:choose>
			<c:when test="${page != null}">
				<div style="float: right;">
				<select>
					<option>아이디</option>
				</select>
				<input type="text">
				<input type="button" value="검색">
				</div>
				<br><br>
				<div>미처리 문서 목록</div>
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
								<td>${i.regdate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${complete != null && incomplete != null}">
				<div style="float: right;">
				<select>
					<option>아이디</option>
				</select>
				<input type="text">
				<input type="button" value="검색">
				</div>
				<br><br>
				<div>미처리 문서 목록</div>
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
								<td>${i.regdate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					</c:otherwise>
				</c:choose>
				<br><br><br><br>
				<div>처리 완료 목록</div>
				<c:choose>
					<c:when test="${fn:length(complete) eq 0}">
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
						<c:forEach items="${complete}" var="c">
							<tr>
								<td>${c.authorization_seq}</td>
								<td><a href='<c:url value="/documentDetail.do?seq=${c.authorization_seq}"/>'>${c.title}</a></td>
								<td>${c.id}</td>
								<td>${c.regdate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					</c:otherwise>
				</c:choose>
			</c:when>
			
			<c:when test="${complete != null}">
			<!-- 처리 완료 목록 -->
				<div>처리 완료 목록</div>
				<c:choose>
					<c:when test="${fn:length(complete) eq 0}">
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
						<c:forEach items="${complete}" var="c">
							<tr>
								<td>${c.authorization_seq}</td>
								<td><a href='<c:url value="/documentDetail.do?seq=${c.authorization_seq}"/>'>${c.title}</a></td>
								<td>${c.id}</td>
								<td>${c.regdate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="paging" style="text-align: center;">
				<c:if test="${paging.getStartPage() gt 1}">
					<a href="./authorizationBranch.do?branch=complete&page=1">◁◁</a>
				</c:if>
				<c:if test="${paging.getStartPage() gt 1}">
					<c:choose>
						<c:when test="${paging.getStartPage() - paging.getCountPage() le 0}">
							<a href="./authorizationBranch.do?branch=complete&page=1">&lt;</a>
						</c:when>
						<c:otherwise>
							<a href="./authorizationBranch.do?branch=complete&page=${paging.getStartPage() - paging.getCountPage()}">&lt;</a>
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:forEach begin="${paging.getStartPage()}" end="${paging.getEndPage()}" var="i" step="1">
					<a ${i == paging.getPage() ? "style='color: forestgreen; font-weight: bold;'" : ""} href="./authorizationBranch.do?branch=complete&page=${i}">
						&nbsp;&nbsp;&nbsp;${i}&nbsp;&nbsp;&nbsp;
					</a>
				</c:forEach>
				<c:if test="${paging.getPage() lt paging.getTotalPage()}">
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${paging.getStartPage() + paging.getCountPage() gt paging.getTotalPage()}"> --%>
<%-- 							<a href="./authorizationBranch.do?branch=complete&page=${paging.getTotalPage()}">&gt;</a>		 --%>
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 							<a href="./authorizationBranch.do?branch=complete&page=${paging.getStartPage() + paging.getCountPage()}">&gt;</a>		 --%>
<%-- 						</c:otherwise> --%>
<%-- 					</c:choose> --%>
				</c:if>
				<c:if test="${paging.getEndPage() lt paging.getTotalPage()}">
					<a href="./authorizationBranch.do?branch=complete&page=${paging.getTotalPage()}">&gt;</a>	
					<a href="./authorizationBranch.do?branch=complete&page=${paging.getTotalPage()}">▶▶</a>
				</c:if>
			</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			
			<c:when test="${incomplete != null}">
			<!-- 미처리 완료 목록 -->
				<div>미처리 문서 목록</div>
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
								<td>${i.regdate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="paging" style="text-align: center;">
				<c:if test="${paging.getStartPage() gt 1}">
					<a href="./authorizationBranch.do?branch=incomplete&page=1">◁◁</a>
				</c:if>
				<c:if test="${paging.getStartPage() gt 1}">
					<c:choose>
						<c:when test="${paging.getStartPage() - paging.getCountPage() le 0}">
							<a href="./authorizationBranch.do?branch=incomplete&page=1">&lt;</a>
						</c:when>
						<c:otherwise>
							<a href="./authorizationBranch.do?branch=incomplete&page=${paging.getStartPage() - paging.getCountPage()}">&lt;</a>
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:forEach begin="${paging.getStartPage()}" end="${paging.getEndPage()}" var="i" step="1">
					<a ${i == paging.getPage() ? "style='color: forestgreen; font-weight: bold;'" : ""} href="./authorizationBranch.do?branch=incomplete&page=${i}">
						&nbsp;&nbsp;&nbsp;${i}&nbsp;&nbsp;&nbsp;
					</a>
				</c:forEach>
				<c:if test="${paging.getPage() lt paging.getTotalPage()}">
					<c:choose>
						<c:when test="${paging.getStartPage() + paging.getCountPage() gt paging.getTotalPage()}">
							<a href="./authorizationBranch.do?branch=incomplete&page=${paging.getTotalPage()}">&gt;</a>		
						</c:when>
						<c:otherwise>
							<a href="./authorizationBranch.do?branch=incomplete&page=${paging.getStartPage() + paging.getCountPage()}">&gt;</a>		
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${paging.getEndPage() lt paging.getTotalPage()}">
					<a href="./authorizationBranch.do?branch=incomplete&page=${paging.getTotalPage()}">▶▶</a>
				</c:if>
			</div>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
		
	</div>
</body>
</html>