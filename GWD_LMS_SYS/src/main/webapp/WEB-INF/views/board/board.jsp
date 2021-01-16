<%@page import="com.min.edu.dto.Board_Dto"%>
<%@page import="com.min.edu.dto.Paging"%>
<%@page import="java.util.List"%>
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
							html += "<td><a href='./oneBoard.do?boardseq="+data[i].boardseq+"'>" + data[i].title + "</td>"
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

		<div class="container" >
			<form name="hi" class="col-xs-3">
				<input type="text" name="keyword" class="form-control" placeholder="id or 제목 or 내용">
				<input type="button" value="click" onclick="searchWord()"  class="btn btn-default ">
			</form>

			<form name="frm" method="post" onsubmit="return chkEv()">
				<div>
					<table class="table table-hover">
						<thead id="firstThead">
							<tr>
							<c:if test="${auth eq ROLE_ADMIN}">
								<th><input type="checkbox" name="chkAll"
									onclick="checkAll(this.checked)"></th>
							</c:if>

								<th>ID</th>
								<th>TITLE</th>
								<th>CONTENT</th>
								<th>DATE</th>
							</tr>
							<%
								for (Board_Dto d : lists) {
							%>

							<tr>
							<c:if test="${auth eq ROLE_ADMIN}">
								<td><input type="checkbox" name="chk"
									value="<%=d.getBoardseq()%>"></td>
								</c:if>			
								<td><%=d.getUserid()%></td>
								<td><a href="./oneBoard.do?boardseq=<%=d.getBoardseq()%>">
										<%=d.getTitle()%></a></td>
								<td><%=d.getContent()%></td>
								<td><%=d.getRegdate().toLocaleString()%></td>

							</tr>

							<%
								}
							%>
							<tr>
								<td colspan="3"><input type="button" value="글입력"  class="btn btn-default " onclick="inputB()">
								<c:if test="${auth eq ROLE_ADMIN}">
								<input type="button" value="전체삭제"   class="btn btn-default " onclick="delAll()">
								</c:if>
								<input type='button' value='돌아가기' class='btn btn-default' onclick='history.back(-1)'>
								</td>
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
			<div style="overflow: auto; height: 450px; width: 100%;" id="colorDiv">
						<table class="table table-hover">
							<thead style="display: none;"   id="towthead">
							<tr><td><input type='button' value='돌아가기' class='btn btn-default' onclick='history.back(-1)'></td></tr>
								<tr>
									<th>ID</th>
									<th>TITLE</th>
									<th>CONTENT</th>
								<tr>
							</thead>
							<tbody id="tab"></tbody>
							
						</table>
					</div><!-- ::-webkit-scrollbar-thumb { background: #303030; }  -->
		</div>
	</div>
</body>
</html>

