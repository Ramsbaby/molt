<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<script language="javascript">
	var result = "${result}";
	var resultMessage = "${resultMessage}";
	var uploadUrl = "${uploadUrl}";
	
	if(result == "FAIL"){
		alert(resultMessage);
	}else{
		var imageUrlObj = parent.document.getElementById("imageUrl");
		imageUrlObj.value = uploadUrl;
		parent.UpLoadImageOk(uploadUrl);
	}
	
</script>
</head>
<body>

</body>

</html>