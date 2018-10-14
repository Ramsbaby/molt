<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/_layout/include/tagLib.jsp" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->  
<head>
    <title>molt Test Project</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- common css -->
	<tiles:useAttribute id="cssList" name="cssFileList" classname="java.util.List" />
	<c:forEach var="cssFileName" items="${cssList}">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}${cssFileName}" />
	</c:forEach>
	
	<!-- biz css -->
	<tiles:useAttribute id="cssBizList" name="cssBizFileList" classname="java.util.List"/>
	<c:forEach var="cssViewName" items="${cssBizList}">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}${cssViewName}" />
	</c:forEach>
	
	<!-- common js -->
	<tiles:useAttribute id="jsList" name="jsFileList" classname="java.util.List" />
	<c:forEach var="jsFileName" items="${jsList}">
		<script type="text/javascript" src="${pageContext.request.contextPath}${jsFileName}"></script>
	</c:forEach>
	
	<script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";
		var insertMsg = '<spring:message code="common.regist.msg" />';
		var updateMsg = '<spring:message code="common.update.msg" />';
		var deleteMsg = '<spring:message code="common.delete.msg" />';
		var saveMsg = '<spring:message code="common.save.msg" />';
	</script>
</head>	

<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
	<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
	
	<!-- header -->
	<%-- <div class="header-v4">
	<tiles:insertAttribute name="header-topbar"/>
	<tiles:insertAttribute name="header-menu"/>
	</div> --%>
	<div class="mainbg">
		<tiles:insertAttribute name="header-menu"/>
	    
	    <!-- body -->
		<tiles:insertAttribute name="content"/>
	</div>
	<!-- footer -->
    <%-- <div class="footer-v1">
		<tiles:insertAttribute name="footer"/>
	</div> --%>
	<tiles:insertAttribute name="footer"/>
	
	<!-- biz js -->
	<tiles:useAttribute id="jsBizList" name="jsBizFileList" classname="java.util.List"/>
	<c:forEach var="jsName" items="${jsBizList}">
		<script type="text/javascript" src="${pageContext.request.contextPath}${jsName}"></script>
	</c:forEach>
	
	<!-- dialog -->
	<div id="divPopupDialog" title="">
		<div class="dialog-content"></div><!-- /.modal-content -->
	</div>

</body>
</html>