<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

<form name="redirectForm" action="<c:url value="${redirectUrl}"/>" method="post">
	<input type="hidden" name="clientCd" id="clientCd" value="<c:url value="${clientCd}"/>">	
	<input type="hidden" name="searchKeywordFrom" id="searchKeywordFrom" value="<c:url value="${searchKeywordFrom}"/>">	
	<input type="hidden" name="searchKeywordTo" id="searchKeywordTo" value="<c:url value="${searchKeywordTo}"/>">
		
	<input type="hidden" name="cntrtId" id="cntrtId" value="<c:url value="${cntrtId}"/>">	
	<input type="hidden" name="cntrtSeq" id="cntrtSeq" value="<c:url value="${cntrtSeq}"/>">	
	
	<input type="hidden" name="bbsId" id="bbsId" value="<c:url value="${bbsId}"/>">
		
	<input type="hidden" name="codeId" id="codeId" value="<c:url value="${codeId}"/>">	
	<input type="hidden" name="upDt" id="upDt" value="<c:url value="${upDt}"/>">	
	<input type="hidden" name="closeDt" id="closeDt" value="<c:url value="${closeDt}"/>">	
</form>

<textarea id="alertMessage" rows="" cols="" style="display:none"><c:url value="${alertMessage}"/></textarea>

<script type="text/javascript">

var alertMessage = document.all.alertMessage.value;
if(alertMessage != ''){
	alert(alertMessage);
}
document.redirectForm.submit();
</script>

</body>
</html>