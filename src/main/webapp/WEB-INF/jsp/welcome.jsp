<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en">
<head>

	<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
    <script src="/script/jquery-3.3.1.min.js"></script>
    <script src="/script/common.js"></script>

</head>
<body>

    <%@include file='header.jsp'%>

	<div class="container">

		<div class="starter-template">
			<h1>Select:</h1>
		</div>
		<c:forEach items="${elections}" var="election">
            <a href="/vote?election=${election.id}">${election.name}</a><br/>
        </c:forEach>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
