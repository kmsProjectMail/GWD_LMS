<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>그룹채팅</title>
<link type="text/css" rel="stylesheet" href="./css/chat_groupChat.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   <% 
   	String grId = (String)session.getAttribute("gr_id"); 
    String mem_id = (String)session.getAttribute("mem_id"); 
   %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
      
      var ws = null ;
      var url = null ;
      var nick = null ; 
      var allContent = "";
      
      var token = $("meta[name='_csrf']").attr("content");
	  var header = $("meta[name='_csrf_header']").attr("content");
	  $(document).ajaxSend(function(e, xhr, options) {
		    xhr.setRequestHeader(header, token);
		});
      
      $(document).ready(function() { // dom이 생성되면 실행됨
          nick = $("#nickName").val();
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
          	
          	
          	if(msg.startsWith("<font color=")){	//입장,퇴장
            	$(".receive_msg").append($("<div class = 'noticeTxt'>").append(msg+"<br/>"));
				viewList(id);
          	}else if(send=="<%=mem_id%>"){
	          	$(".receive_msg").append($("<div id='sendDiv'>").append($("<span id='sender'>").text(msg))).append("<br><br>");
          	}else{
	          	$(".receive_msg").append($("<div id='receiveDiv'>").append($("<span id='receiver'>").text(msg))).append("<br><br>");
          	}
          	$(".receive_msg").scrollTop($(".receive_msg")[0].scrollHeight);
          	chatSave();
          }
          
          // 메세지 보내기 버튼 클릭시
         $(".chat_btn").bind("click",function() {
            if($(".chat").val() == '' ) {
               alert("내용을 입력하세요");
               return ;
            }else {
               ws.send(nick+" : "+$(".chat").val());
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
 <div style="text-align: center;">
	 <a style="font-size: x-large;">${gr_id}</a>
 </div>
 <div style="display: inline-block;">
 	<div class="out_btn" onclick="roomClose()"></div>
	<div style="margin-left: 430px; width: 50px; font-size: x-large;" onclick="openRightMenu()" >&#9776;</div>
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
</body>
</html>