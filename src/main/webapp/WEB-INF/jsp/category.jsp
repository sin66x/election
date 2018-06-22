<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>

	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />

    <script src="/script/jquery-3.3.1.min.js"></script>
    <script src="/script/category.js"></script>
    <script src="/script/common.js"></script>

</head>
<body dir="${langDir}">

	    <%@include file='header.jsp'%>

	<div class="container">
        <form:form action="category" method="POST" modelAttribute="categoryDTO">
            <form:input name="id" id="id" path="id" type="hidden"/>

            <label>${messages.getMessage("InsertCatName",lang)}</label>
            <form:input name="name" id="name" path="name" />
            <label>${messages.getMessage("SelectParent",lang)}</label>
            <form:select id="parent" path="parent">
                <option value="0">${messages.getMessage("SelectOne",lang)}</option>
                <c:forEach items="${parents}" var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </form:select>
            <button type="submit" value="Submit">${messages.getMessage("Submit",lang)}</button>
            <button type="button" id="clear">${messages.getMessage("Clear",lang)}</button>
        </form:form>

        <c:forEach items="${parents}" var="category">
             ${category.name} - ${category.createdDate} -
             <button id="editBtn${category.id}" name="editBtn${category.id}" value="${category.id}">${messages.getMessage("Edit",lang)}</button>
             <button id="removeBtn${category.id}" name="removeBtn${category.id}" value="${category.id}">${messages.getMessage("Remove",lang)}</button>
            <br/>

        </c:forEach>


	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
