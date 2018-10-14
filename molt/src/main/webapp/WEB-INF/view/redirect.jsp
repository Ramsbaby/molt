<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/_layout/include/tagLib.jsp" %>
<%
	String redirectURL = request.getHeader("referer");
	if(redirectURL == null || redirectURL.indexOf("signInView") >= 0){
		redirectURL = "/";
	}
%>
<script language="javascript">
	document.location = '<%= redirectURL%>';
</script>