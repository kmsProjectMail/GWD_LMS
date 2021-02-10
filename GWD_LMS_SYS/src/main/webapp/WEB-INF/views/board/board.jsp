<%@page import="com.min.edu.dto.Board_Dto"%>
<%@page import="com.min.edu.dto.Paging"%>
<%@page import="java.util.List"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%
	Paging p = (Paging) request.getAttribute("page");
	List<Board_Dto> lists = (List<Board_Dto>) request.getAttribute("lists");
	int n = 1;
%>



<script type="text/javascript">
	function inputB() {
		location.href = "./inputBForm.do"
	}

	function checkAll(bool) {
		var chk = document.getElementsByName("chk");
		for (var i = 0; i < chk.length; i++) {
			chk[i].checked = bool;
		}
	}
	function chking() {
		var chk = document.getElementsByName("chk");
		var cnt = 0;
		for (var i = 0; i < chk.length; i++) {
			if (chk[i].checked) {
				cnt++
			}
		}
		return cnt;
	}
	window.onload = function() {

		var chk = document.getElementsByName("chk");
		var allchk = document.getElementsByName("chkAll")
		for (var i = 0; i < chk.length; i++) {
			chk[i].onclick = function() {
				if (chk.length == chking()) {
					allchk.checked = true;
				} else {
					allchk.checked = false;
				}
			}
		}
	}

	function delAll() {
		var form = document.frm
		var chk = document.getElementsByName("chk");
		var cnt = 0;
		for (var i = 0; i < chk.length; i++) {
			if (chk[i].checked) {
				cnt++
			}
		}
		if (cnt > 0) {
			form.action = "./deleteBoard.do"
			form.submit()
		} else {
			alert("한개 이상 선택해주세요")
			false;
		}
	}
	function writeForm() {

		location.href = "./writeForm.do";
	}

	function searchWord() {
		var keyword = document.getElementsByName("keyword")[0].value;
		if (keyword == "" || keyword == null) {
			alert("단어를 입력해주세요")
		} else {
			$.ajax({
				url : './wordAjax.do',
				method : 'get',
				dataType : 'json',
				data : {
					"keyword" : keyword
				},
				success : function(data) {
					var html = ""
					if (data.length == 0) {
						$("#tab").html("<tr><td>검색결과가 없습니다</tr></td>")
					} else {
						for (var i = 0; i < data.length; i++) {

							html += "<tr>"
							html += "<td>" + data[i].userid + "</td>"
							html += "<td><a href='./oneBoard.do?boardseq="
									+ data[i].boardseq + "'>" + data[i].title
									+ "</td>"
							html += "<td>" + data[i].content + "</td>"
							html += "</tr>"
							$("#tab").html(html)
							$("#tab").attr('style', 'background:#F5F5F5')
							$("#towthead").attr('style', 'display:display')

							$("#firstThead").attr('style', 'display:none')
							$("#paging").attr('style', 'display:none')
						}
					}
				},
				error : function(request, status, error) {
					console.log("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:" + error);
				}
			})
		}
	}
</script>

</head>
<%@include file="../index.jsp"%>
<body>
	<div class="maincontainer" style="margin-left: 220px;">
		<div id="viewheader">
			<img alt="bar" src="/GWD_LMS_SYS/images/hrd/bar.png">
			<h3>게시판</h3>
		</div>

		<div class="container">

			<form name="hi" class="col-xs-3" style="float: right;">
				<input type="text" name="keyword" class="form-control"
					placeholder="id or 제목 or 내용" style="width: 200px; float: left;">
				<input type="button" value="검색" onclick="searchWord()"
					class="btn btn-default" style="float: right;">
			</form>

			<form name="frm" method="post" onsubmit="return chkEv()">
				<div>
					<table class="table table-hover">
						<thead id="firstThead">
							<tr>
								<c:if test="${auth eq 'ROLE_ADMIN'}">
									<th><input type="checkbox" name="chkAll"
										onclick="checkAll(this.checked)"></th>
								</c:if>

								<th>NUM</th>
								<th>ID</th>
								<th>TITLE</th>
								<th>DATE</th>
							</tr>

							<c:forEach items="${lists}" var="d" varStatus="vs">
								<tr>
									<td>${page.totalCount-(page.page*page.countList+1)
									    		+(fn:length(lists)-vs.index+1)+1}</td>
									<c:if test="${auth eq 'ROLE_ADMIN'}">
										<td><input type="checkbox" name="chk"
											value="${d.boardseq}"></td>
									</c:if>
									<td>${d.userid}</td>
									<td><a href="./oneBoard.do?boardseq=${d.boardseq}">
											${d.title}</a></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
											value="${d.regdate}" /></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="4"><input type="button" value="글입력"
									class="btn btn-default " onclick="inputB()"> <c:if
										test="${auth eq 'ROLE_ADMIN'}">
										<input type="button" value="전체삭제" class="btn btn-default "
											onclick="delAll()">
									</c:if> <input type='button' value='돌아가기' class='btn btn-default'
									onclick='history.back(-1)'></td>
							</tr>
						</thead>
					</table>
					<!-- 스크롤 -->
				</div>
			</form>

			<div id="paging" style="text-align: center;">
				<%
					if (p.getStartPage() > 1) {
				%>
				<a href="./board.do?page=1">◁◁</a>
				<%
					}
					if (p.getStartPage() > 1) {
						if (p.getStartPage() - p.getCountPage() <= 0) {
				%>
				<a href="./board.do?page=1">&lt;</a>
				<%
					} else {
				%>
				<a href="./board.do?page=<%=p.getStartPage() - p.getCountPage()%>">&lt;</a>
				<%
					}
					}
				%>
				<%
					for (int i = p.getStartPage(); i <= p.getEndPage(); i++) {
				%>
				<a
					<%=(i == p.getPage()) ? "style='color: forestgreen; font-weight: bold;'" : ""%>
					href="./board.do?page=<%=i%>">&nbsp;&nbsp;&nbsp;<%=i%>&nbsp;&nbsp;&nbsp;
				</a>
				<%
					}
				%>

				<%
					if (p.getPage() < p.getTotalPage()) {
						if (p.getStartPage() + p.getCountPage() > p.getTotalPage()) {
				%>
				<a href="./board.do?page=<%=p.getTotalPage()%>">&gt;</a>
				<%
					} else {
				%>
				<a href="./board.do?page=<%=p.getStartPage() + p.getCountPage()%>">&gt;</a>
				<%
					}
					}
					if (p.getEndPage() < p.getTotalPage()) {
				%>
				<a href="./board.do?page=<%=p.getTotalPage()%>">▶▶</a>
				<%
					}
				%>
			</div>
			<div style="overflow: auto; height: 450px; width: 100%;"
				id="colorDiv">
				<table class="table table-hover">
					<thead style="display: none;" id="towthead">
						<tr>
							<td><input type='button' value='돌아가기'
								class='btn btn-default'
								onclick='location.href = "/GWD_LMS_SYS/board/board.do"'></td>
						</tr>
						<tr>
							<th>ID</th>
							<th>TITLE</th>
							<th>CONTENT</th>
						<tr>
					</thead>
					<tbody id="tab"></tbody>

				</table>
			</div>
			<!-- ::-webkit-scrollbar-thumb { background: #303030; }  -->
		</div>
	</div>
</body>
</html>

