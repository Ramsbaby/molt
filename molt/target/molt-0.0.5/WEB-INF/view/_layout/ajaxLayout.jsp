<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/_layout/include/tagLib.jsp" %>

<div id="content">
	<tiles:insertAttribute name="content"/>
</div>


<%-- JS View File List --%>
<tiles:useAttribute id="jsViewFileList" name="jsViewFileList" classname="java.util.List" />
<c:forEach var="jsFileName" items="${jsViewFileList}">
	<script type="text/javascript" src="${pageContext.request.contextPath}${jsFileName}"></script>
</c:forEach>
