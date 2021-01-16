/**
 * 
 */
$("#id").on("propertychange change keyup paste input", function() {
	 newval = $(this).val();

	if(getTextLength($(this).val())<8) {
		$("#idlabel").css("color", "black")

		$("#idlabel").text("아이디는 8자 이상입니다.");

	}else if(getTextLength($(this).val()) >8 && getTextLength($(this).val())<15){
			$.ajax({
		data : {
			"id" : $("#id").val()	
		},
		url : "./idDuplChk.do",
		type : "get",
		success: function(msg){
			var text= "";
			if(msg=="dupl"){
				$("#idlabel").css("color", "red")
				text ="아이디는 영문과 숫자만 사용할 수 있습니다."
			}else if(msg=="false"){
				$("#idlabel").css("color", "red")
				text ="중복된 아이디입니다!"
			}else {
				$("#idlabel").css("color", "blue")
				text ="사용 가능한 아이디"
			}
			$("#idlabel").text(text);
		},
		error:function(){
			
		}
	})
	}else{
		$("#idlabel").css("color", "black")

		$("#idlabel").text('15자를 넘지 않습니다.');
		var temp = newval.substring(newval.length -1)
		$(this).val(newval.substring(0, 14) + temp);
	}
	

	
});



$("#password").on("propertychange change keyup paste input", function() {
	 newval = $(this).val();
	 if(newval.length != 0){
		 $("#pwlabel").css('width', '50%');
		 $("#pwlabel").css('text-align', 'left');
		 $("#pwlabel").css('padding-left', '10px');
		var idReg = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		if(newval.length <8){
			$("#pwlabel").text('8자리 이상 입력해주세요');
			$("#pwlabel").css('color', 'red');
		}else if(newval.length>=5 && newval.length <15){
			if( !idReg.test(newval) ){
				$("#pwlabel").text('비밀번호에는 반드시 숫자, 특수문자가 포함되어야 합니다.');
				$("#pwlabel").css('color', 'red');
			}else{
				$("#pwlabel").text('올바른 입력');
				$("#pwlabel").css('color', 'blue');
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

$("#phone1").on("propertychange change keyup paste input", function() {
	var newval = $(this).val()
    if(getTextLength($(this).val()) > 3){
    	var temp = newval.substring(newval.length-1,newval.length)
    	$(this).val(newval.substring(0,2) +temp);
    }
});
$("#phone2").on("propertychange change keyup paste input", function() {
	var newval = $(this).val()
    if(getTextLength($(this).val()) > 4){
    	var temp = newval.substring(newval.length-1,newval.length)
    	$(this).val(newval.substring(0,3) +temp);
    }
});
$("#phone3").on("propertychange change keyup paste input", function() {
	var newval = $(this).val()
    if(getTextLength($(this).val()) > 4){
    	var temp = newval.substring(newval.length-1,newval.length)
    	$(this).val(newval.substring(0,3) +temp);
    }
});

function getTextLength(val){
	return val.length
}