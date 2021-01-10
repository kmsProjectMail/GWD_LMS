<%@page import="com.min.edu.dto.FileBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.min.edu.dto.MessengerDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>그룹채팅</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link type="text/css" rel="stylesheet" href="./css/chat_groupChat.css">
   <% 
   	String grId = (String)session.getAttribute("gr_id"); 
    String mem_id = (String)session.getAttribute("mem_id"); 
    String mem_name = (String)session.getAttribute("mem_name");
    String other_name = (String)session.getAttribute("otherName");
    List<FileBoardDto> fileList = (List<FileBoardDto>)request.getAttribute("fileList");
    MessengerDto chatroomDto = (MessengerDto)request.getAttribute("chatroomDto");
   %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
      var ws = null ;
      var url = null ;
      var nick = null ; 
      var loginUserName = null ; 
      var allContent = "";

  	  // Security로 인한 아작스 post로 요청시 문제 해결을 위한 것
  	  var token = $("meta[name='_csrf']").attr("content");
	  var header = $("meta[name='_csrf_header']").attr("content");
	  $(document).ajaxSend(function(e, xhr, options) {
		    xhr.setRequestHeader(header, token);
		});
      
	  // dom이 탐색되면 실행됨
      $(document).ready(function() {
          nick = $("#nickName").val();
      	  loginUserName = $("#loginUserName").val();
          $(".chat_div").show();
          $(".chat").focus();
          // 웹소켓 서버
          ws = new WebSocket("ws://localhost:8099/GWD_LMS_SYS/wsChat.do");
          
          // 웹소켓 서버가 오픈 됐을때
          ws.onopen = function() {
             ws.send("#$nick_"+nick);
          };
          
          // 메세지 보낼때
          ws.onmessage = function(event) {
          	var msg = event.data;
          	var id = "<%=grId%>";
          	// alert("메세지 출력 결과 : "+msg); // ex: user01:안녕
            var msgArr = msg.split(":") // ex: [user01], [안녕] [user02],[아녕]
          	var send = msgArr[0]; // ex: user01
			var sendmsg = msgArr[1]; // ex: 안녕
          	
			var finalmsg = msgArr[1]+": " + msgArr[2];
          	
          	if(msg.startsWith("<font color=")){	//입장,퇴장
            	$(".receive_msg").append($("<div class = 'noticeTxt'>").append(msg+"<br/>"));
				viewList(id);
          	}else if(msg.startsWith("<font size=")){
          		$(".receive_msg").append($("<div class = 'fileTxt'>").append(msg+"<br/>"));
          	}else if(send=="<%=mem_id%>"){
	          	$(".receive_msg").append($("<div id='sendDiv'>").append($("<span id='sender'>").text(finalmsg))).append("<br><br>");
          	}else{
	          	$(".receive_msg").append($("<div id='receiveDiv'>").append($("<span id='receiver'>").text(finalmsg))).append("<br><br>");
          	}
          	$(".fileList").load(location.href + " .fileList"); // 파일 업로드 했을 때 실시간으로 파일 업로드를 확인하기 위해 해당 div 영역 새로고침
          	$(".receive_msg").scrollTop($(".receive_msg")[0].scrollHeight);
          	chatSave();
          }
          
          // 메세지 보내기 버튼 클릭시
         $(".chat_btn").bind("click",function() {
            if($(".chat").val() == '' ) {
               alert("내용을 입력하세요");
               return ;
            }else {
               ws.send(nick+" : "+ loginUserName + ":" + $(".chat").val());
               $(".chat").val('');
               $(".chat").focus();
            }
         });
      }); //$(document).ready 끝
      
      // 대화내용 저장
      function chatSave(){
    	chatmember = document.getElementById("chatmember").value; // chatmember에 mem_id, gr_id 를 담음 -> ex: user01, user02 // (그룹)구디, user01
      	allContent = document.getElementById("receive_msg").innerHTML;
    	
      		$.ajax({
      					url : "./updateChat.do",
      					type : "post",
      					data : "chatmember="+chatmember+"&content="+allContent
      		});
      }
      
      // 나가기 버튼 눌렀을때
      function roomClose(){
    	chatmember = document.getElementById("chatmember").value; // chatmember에 mem_id, gr_id 를 담음 -> ex: user01, user02 // (그룹)구디, user01
    	allContent = document.getElementById("receive_msg").innerHTML;
    		$.ajax({
    					url : "./updateChat.do",
    					type : "post",	
    					//업데이트를 위해 db의 chatmember, content을 보냄
    					data : "chatmember="+chatmember+"&content="+allContent,
    					success : function(msg) {
    						var isc = msg;
    						if(isc=="성공"){
    							location.href="./completeLogin.do";
    						}
    					}
    				});
    		 alert("서버와의 연결이 종료되었습니다.");
    		 chatOut();
          	 self.close();
          	 disconnect();
    	}
      
      
      // ws server 종료
      function disconnect() {
         ws.close();
         ws = null ;
      } 
      
    
      // 접속자 목록 조회
      function viewList(grId){
          $(".memList").children().remove();
        $.ajax({
          type: "GET",
          url: "./viewChatList.do",
          data: "mem_id="+$("#nickName").val(),
          success: function(result){
              console.log(result.list);
             for(var k in result.list){
               if(result.list[k]==grId){
                 $(".memList").prepend("<img style='margin-left:30px;' class = 'mem_icon' src = 'images/chat_member.png' alt = '접속자아이콘'><p class='mem_p' style = 'margin-left:15px; font-size : 13px;padding : 5px;border-bottom: 0.5px solid #B4B4B4;'>"+k+"</p>"); 
              }
             }
          }
        });
     } 
      
      // 채팅방을 나갈경우 접속자 목록에서 제거
       function chatOut(){
    	  alert("채팅종료");
      	  $.ajax({
      		  type: "GET",
      		  url: "./socketOut.do",
      		  async: false
      	  });	
      }

    // w3schools sidebar 기능 
	function openRightMenu() {
		document.getElementById("rightMenu").style.display = "block";
	}

    // w3schools sidebar 기능 
	function closeRightMenu() {
		document.getElementById("rightMenu").style.display = "none";
	}
    
</script>
</head>
<body>
<br>
 <input type="hidden" id="nickName" value = <%=mem_id%> />
 <input type="hidden" id="loginUserName" value = <%=mem_name%> />
 <div style="text-align: center;">
	 <a style="font-size: x-large;"><%=other_name%></a>
 </div>
 <div class="btnHeader">
 	<div class="out_btn" onclick="roomClose()"></div>
    <button id="file_div" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal"></button>
	<div class="sideMenuBtn" onclick="openRightMenu()" >&#9776;</div>
 </div>	
 <div class="w3-sidebar w3-bar-block w3-card w3-animate-right" style="display:none;right:0; width: 200px; height: 350px; background-color: #32353B;" id="rightMenu">
   <button onclick="closeRightMenu()" class="w3-bar-item w3-button w3-large" style="font-size: medium;">Close &times;</button>
   <hr>
   <div style="text-align: center;">접속자 목록</div>
   <div class = "memList"></div> 
 </div>
 <br>
 <br>
   <table>
	   <tr>
	      <td width="360x" height="390px" align="center">
	      <div id ="receive_msg" class="receive_msg">
			       ${content}
	      </div>
	      </td>
	   </tr>   
   </table>
   
   <div class="chat_div" style="display:none; margin-top: 10px;">
	   <textarea class="chat" onKeypress="if(event.keyCode==13) $('.chat_btn').click();" ></textarea>
	   <div class="chat_btn"></div>
   </div>
 	
	<div>
		<input type="hidden" id="chatmember" value="${gr_id}">
	</div>
	
	<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color: black;">File Upload&Download</h4>
        </div>
        
        <div class="modal-body">
        	<div class="fileList">
        		<table class="table">
        			<tr>
        				<td style="color: #8C8C8C; white-space:nowrap;">파일이름</td>
        				<td style="color: #8C8C8C; white-space:nowrap;">파일크기</td>
        				<td style="color: #8C8C8C; white-space:nowrap;">날짜</td>
        				<td style="color: #8C8C8C; white-space:nowrap;">등록자</td>
        			</tr>
        			<%
						for(FileBoardDto dto : fileList){
							%>
			        		<input type="hidden" class="file_seq" value="<%=dto.getF_seq()%>">
							<tr class="fileListTd" onclick="fileDown()">
								<td align="left" style="font-size: small;"><%=dto.getOrigin_fname()%></td>
								<td align="left" style="font-size: small;"><%=dto.getFile_size() %></td>
								<td align="left" style="font-size: small;">
									<fmt:formatDate pattern="MM월 dd일 HH:mm" value="<%=dto.getF_regdate() %>"/>
								</td>
								<td align="left" style="font-size: small;"><%=dto.getOwner() %></td>
							</tr>
							<%
						}
        			%>
        		</table>
        	</div>
        </div>
        
        <div class="modal-footer">
	        <form action="./uploadChatFile.do?${_csrf.parameterName}=${_csrf.token}" id="fileForm" method="post" name="frm" enctype="multipart/form-data">
		 		<input type="hidden" class="seq" name="seq" value="<%=chatroomDto.getSeq()%>">
		 		<input type="hidden" class="loginUser" name="loginUser" value="<%=mem_name%>">
		 		<div>
			 		<input type="file" name="file" style="float: left; color: #8C8C8C">
			 		<input type="button" value="업로드" class="btn" onclick="fileupload()">
		 		</div>
	 		</form>
<!--           	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
        </div>
      </div>
    </div>
  </div>
  
<script type="text/javascript">

	var ws = null;
	ws = new WebSocket("ws://localhost:8099/GWD_LMS_SYS/wsChat.do");

	var nick = $("#nickName").val();
	var loginUserName = $("#loginUserName").val(); 
	
  // 파일을 선택해서 업로드
  function fileupload(){
	  var formData = new FormData($("#fileForm")[0]);
	  var seq = $(".seq").val();
	  var loginUser = $(".loginUser").val();
	  $.ajax({
		  type:'post',
		  url:'./uploadChatFile.do',
		  data : formData,
		  processData: false,
		  contentType: false,
		  async: false,
		  success:function(data){
			  alert("파일 업로드 성공");
			  $(".fileList").load(location.href + " .fileList");  // 파일업로드후 파일리스트를 띄워주는 div 영역 새로고침
		  }
	  });
  }
  
  $(".receive_msg").on("dragenter dragover", function(event){
	  event.preventDefault();
  });
  
  // 드로그앤 드롭으로 파일 업로드
  $(".receive_msg").on("drop", function(event){
	  event.preventDefault();
	  var files = event.originalEvent.dataTransfer.files;
	  var file = files[0];
	  var seq = $(".seq").val();
	  var loginUser = $(".loginUser").val();
	  var formData = new FormData($("#fileForm")[0]);
	  formData.append("file", file);
	  
	  $.ajax({
		  type:'post',
		  url:'./uploadChatFile.do',
		  data : formData,
		  processData: false,
		  contentType: false,
		  async: false,
		  success:function(data){
			  alert("파일 업로드 성공");
			  $(".fileList").load(location.href + " .fileList"); // 파일업로드후 파일리스트를 띄워주는 div 영역 새로고침
			  ws.send(nick+" : "+ loginUserName + ":" + "*fileupload*");
		  }
	  });
  });
  
  // 파일 다운로드
  function fileDown(){
	  var seq = $(".file_seq").val();
	  var result = confirm("파일을 다운로드 하시겠습니까?");
	  if(result){
		  window.location ="./downloadChatFile.do?f_seq="+seq;
	  }else{
		  alert("파일 다운로드를 취소하셨습니다.");
	  }
  }
  
  
  
  
  
  
  
  
  
  
  
  
</script>
</body>
</html>