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
        <form:form action="category" method="POST" modelAttribute="categoryDTO" class="edit_box">
            <div class="error_msg">
                ${errorMessage}
            </div>
            <form:input name="id" id="id" path="id" type="hidden"/>
            <table>
            <tr>
                <td>
                    <label>${messages.getMessage("InsertCatName",lang)}</label>
                </td>
                <td>
                    <form:input name="name" id="name" path="name" />
                </td>
                <td>
                    <label>${messages.getMessage("SelectParent",lang)}</label>
                </td>
                <td>
                <form:select id="parent" path="parent">
                    <option value="0">${messages.getMessage("SelectOne",lang)}</option>
                    <c:forEach items="${parents}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </form:select>
                </td>
            </tr>
            </table>
            <button class="btn_submit" id="submit" value="Submit">${messages.getMessage("Submit",lang)}</button>
            <button class="btn_clear" type="button" id="clear">${messages.getMessage("Clear",lang)}</button>
        </form:form>

            <div class="data_table">
            <input class="search_box" type="text" id="myInput" onkeyup="filterTable(2)" placeholder='${messages.getMessage("Search",lang)}'/>
            <table id="myTable" class="table_total">
            <tr>
                <th class="table_header">
                    ${messages.getMessage("Categories",lang)}
                </th>
                <th class="table_header">
                    ${messages.getMessage("ParentCategory",lang)}
                </th>
                <th class="table_header">
                </th>
                <th class="table_header">
                </th>

            </tr>
        <c:forEach items="${parents}" var="category">
            <tr>
             <td>${category.name}</td>
             <td>${category.parent.name}</td>
             <td>
                <button class="btn_submit" id="editBtn${category.id}" name="editBtn${category.id}" value="${category.id}">${messages.getMessage("Edit",lang)}</button>
             </td>
             <td>
                <button class="btn_clear" id="removeBtn${category.id}" name="removeBtn${category.id}" value="${category.id}">${messages.getMessage("Remove",lang)}</button>
            </td>
            </tr>

        </c:forEach>


	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
