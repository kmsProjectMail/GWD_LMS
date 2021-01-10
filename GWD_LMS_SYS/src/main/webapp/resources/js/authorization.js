/**
 * 
 */

function fn_fileDown(fileNo) { // 파일 번호를 담아서 submit을 함
	var formObj = $("form[name='fileReadForm']");
	$("#fileNo").attr("value",fileNo);
//	formObj.attr("action","./documentFileDownload.do");
	formObj.submit();
}
