/**
 * 
 *//**
 * 
 */


$("#id").on("propertychange change keyup paste input", function() {
	 newval = $(this).val();
	 
	 if(newval.length != 0){
		 $("#idlabel").css('width', '50%');
		 $("#idlabel").css('text-align', 'left');
		 $("#idlabel").css('padding-left', '10px');
		 if(newval.length>=1){
			 if(newval.length < 6){
				 $("#idlabel").text('5자 이상입력해주세요');
				 $("#idlabel").css('color', 'red');
			 }else if (newval.length>5 && newval.length <15){

				 //여기서 아이디 제약조건 검색할것.
				var idReg =/^[a-z0-9]{5,15}$/g;
				if(!idReg.test(newval)){
					$("#idlabel").text('특수기호는 사용 불가합니다.')
				}else{
					
				 $("#idlabel").text('올바른 입력');
				 $("#idlabel").css('color', 'blue');
				}
			 }else if(newval.length>15){
				 $("#idlabel").text('15자를 넘지 않습니다.');
				 $("#idlabel").css('color', 'red');
				 var temp = newval.substring(newval.length -1)
			
				 $(this).val(newval.substring(0, 14) + temp);
				 
			 }
			 
		 }
	 }else{
		
		 $("#idlabel").css('width', '100%');
		 $("#idlabel").css('text-align', 'center');
		 $("#idlabel").css('padding-left', '');
		 $("#idlabel").css('color', 'white');
		 $("#idlabel").text('아이디')
	 }

});


$("#password").on("propertychange change keyup paste input", function() {
	 newval = $(this).val();
	 
	 if(newval.length != 0){
		 $("#pwlabel").css('width', '50%');
		 $("#pwlabel").css('text-align', 'left');
		 $("#pwlabel").css('padding-left', '10px');
		var idReg = /^.*(?=^.{5,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		if(newval.length <5){
			$("#pwlabel").text('5자리 이상 입력해주세요');
			$("#pwlabel").css('color', 'red');
		}else if(newval.length>=5 && newval.length <15){
			if( !idReg.test(newval) ){
				$("#pwlabel").text('비밀번호에는 반드시 숫자, 특수문자가 포함되어야 합니다.');
				$("#pwlabel").css('color', 'red');
			}else{
				$("#pwlabel").text('올바른 입력');
				$("#pwlabel").css('color', 'blue');
			}
	
			if( ($("#id").val() != "" ||  $("#id").val() != null) && $("#id").val().indexOf( newval) != -1 ){
				console.log("id : "+  $("#id").val())
	 			$("#pwlabel").text('아이디와 5자이상 중복 불가');
				$("#pwlabel").css('color', 'red');
			}
		}else if(newval.length>=15){
				$("#pwlabel").text('15자를 넘지 않습니다.');
				$("#pwlabel").css('color', 'red');
				var temp = newval.substring(newval.length -1)
				$(this).val(newval.substring(0, 14) + temp);
			 
	 }
		
		
		
		
	}else{
		
		 $("#pwlabel").css('width', '100%');
		 $("#pwlabel").css('text-align', 'center');
		 $("#pwlabel").css('padding-left', '');
		 $("#pwlabel").css('color', 'white');
		 $("#pwlabel").text('비밀번호')
	 }

});



$("#name").on("propertychange change keyup paste input", function() {
	 newval = $(this).val();
	 
	 if(newval.length !=0){
		 $("#nalabel").css('width', '50%');
		 $("#nalabel").css('text-align', 'left');
		 $("#nalabel").css('padding-left', '10px');
		if(newval.length >=1 && newval.length <15){
			$("#nalabel").text('2자 이상입력해주세요');
			 $("#nalabel").css('color', 'red');
			var idReg =/^[가-힣]+$/;
			if(!idReg.test(newval)){
				$("#nalabel").text('올바르지 못한 입력.')
			}else{				
				$("#nalabel").text('올바른 입력');
				$("#nalabel").css('color', 'blue');
			}
		}else if(newval.length>=15){
				$("#nalabel").text('15자를 넘지 않습니다.');
				$("#nalabel").css('color', 'red');
				var temp = newval.substring(newval.length -1)
				$(this).val(newval.substring(0, 14) + temp);
			 
	 }
	}else{
		
		 $("#nalabel").css('width', '100%');
		 $("#nalabel").css('text-align', 'center');
		 $("#nalabel").css('padding-left', '');
		 $("#nalabel").css('color', 'white');
		 $("#nalabel").text('이름')
	 }
});

$("#email").on("propertychange change keyup paste input", function() {
	 newval = $(this).val();
	 
	 if(newval.length != 0){
		 $("#emlabel").css('width', '50%');
		 $("#emlabel").css('text-align', 'left');
		 $("#emlabel").css('padding-left', '10px');
		 if(newval.length>=1){
			var idReg =  /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
			if(!idReg.test(newval)){
				$("#emlabel").css('color', 'red');

				$("#emlabel").text('올바르지 못한 입력');
			}else{
				$("#emlabel").text('올바른 입력');
				$("#emlabel").css('color', 'blue');
			}
		 }
			 
	 }else{
		;
		 $("#emlabel").css('width', '100%');
		 $("#emlabel").css('text-align', 'center');
		 $("#emlabel").css('padding-left', '');
		 $("#emlabel").css('color', 'white');
		 $("#emlabel").text('이메일')
	 }

});

$("#phone").on("propertychange change keyup paste input", function() {
	 newval = $(this).val();
	 
	 if(newval.length != 0){
		 $("#phlabel").css('width', '50%');
		 $("#phlabel").css('text-align', 'left');
		 $("#phlabel").css('padding-left', '10px');
		 if(newval.length>=1 && newval.length <11 ){
			var idReg =/[0-9]$/;
			if(!idReg.test(newval)){
				$("#phlabel").text('잘못된 입력입니다')
			}else{
				
			 $("#phlabel").text('올바른 입력');
			 $("#phlabel").css('color', 'blue');
			}
		}else if(newval.length>=12){
				 $("#phlabel").text('11자를 넘지 않습니다.');
				 $("#phlabel").css('color', 'red');
				 var temp = newval.substring(newval.length -1);			
				 $(this).val(newval.substring(0, 10) + temp);
			}
		}else{
		
		 $("#phlabel").css('width', '100%');
		 $("#phlabel").css('text-align', 'center');
		 $("#phlabel").css('padding-left', '');
		 $("#phlabel").css('color', 'white');
		 $("#phlabel").text('핸드폰')
	 }

});

$("#address").on("propertychange change keyup paste input", function() {
	 newval = $(this).val();
	 
	 if(newval.length != 0){
		 $("#addrlabel").css('width', '50%');
		 $("#addrlabel").css('text-align', 'left');
		 $("#addrlabel").css('padding-left', '10px');
		 if(newval.length>1){
			
			var idReg =/^[A-Za-z0-9가-힣]+$/;
			if(!idReg.test(newval)){
				$("#addrlabel").text('특수기호는 사용 불가합니다.')
			}else{
				
			 $("#addrlabel").text('올바른 입력');
			 $("#addrlabel").css('color', 'blue');
			}
		 }
	 }else{
		
		 $("#addrlabel").css('width', '100%');
		 $("#addrlabel").css('text-align', 'center');
		 $("#addrlabel").css('padding-left', '');
		 $("#addrlabel").css('color', 'white');
		 $("#addrlabel").text('주소')
	 }

});

function submitbtn(){
	var leng = document.getElementsByTagName("label");
	var flag = 0;
	for(var i =0; i<leng.length; i++){
		if(leng[i].innerHTML == "올바른 입력" ){
			flag++;
		}
	}
	console.log("flag : " + flag);
	console.log("leng : " + leng);
	if(flag == leng.length){
		document.forms[0].submit();
	}
}
