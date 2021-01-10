<%@page import="com.min.edu.dto.Board_Dto"%>
<%@page import="com.min.edu.dto.Paging"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
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
	function writeForm(){
		
		location.href = "./writeForm.do";
	}
	
</script>
<body>
	<form name="frm" method="post" onsubmit="return chkEv()">
		<table>
			<tr>
				<th><input type="checkbox" name = "chkAll" onclick="checkAll(this.checked)"></th>
				<th>NUM</th>
				<th>ID</th>
				<th>TITLE</th>
				<th>CONTENT</th>
			</tr>
			<%
				for (Board_Dto d : lists) {
			%>
			<tr>
				<td><input type="checkbox" name="chk" value="<%=d.getBoardseq()%>"></td>
				<td><%=n++%> </td>
				<td><%=d.getUserid()%></td>
				<c:choose>
				<c:when test="${auth eq 'ROLE_STUDENT'}">
				<td><a href="./oneBoard.do?boardseq=<%=d.getBoardseq()%>">
						<%=d.getTitle()%></a></td>
				</c:when>
				<c:otherwise>
				<td><a href="/GWD_LMS_SYS/login/loginForm.do">
						<%=d.getTitle()%></a></td>
				</c:otherwise>
				</c:choose>
				<td><%=d.getContent()%></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td><input type="button" value="글입력" onclick="inputB()"></td>
				<td><input type="button" value="전체삭제" onclick="delAll()"></td>
			</tr>
		</table>
</form>
		<div>
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
	<script type="text/javascript">
		
	</script>
</body>
</html>