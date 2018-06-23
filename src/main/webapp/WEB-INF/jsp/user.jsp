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
    <script src="/script/user.js"></script>
    <script src="/script/common.js"></script>

</head>
<body dir="${langDir}">

	    <%@include file='header.jsp'%>

	<div class="container">
        <form:form action="user" method="POST" modelAttribute="user">
            ${errorMessage}
            <form:input name="id" id="id" path="id" type="hidden"/>

            <label>${messages.getMessage("Username",lang)}</label>
            <form:input name="username" id="username" path="username" />
            <label>${messages.getMessage("Password",lang)}</label>
            <form:input name="password" id="password" path="password" />
            <label>${messages.getMessage("Role",lang)}</label>
            <form:select id="role" path="role">
                <option value="ROLE_admin">${messages.getMessage("Admin",lang)}</option>
                <option value="ROLE_user">${messages.getMessage("Voter",lang)}</option>
            </form:select>
            ${messages.getMessage("Status",lang)}
            <form:select id="isActive" path="isActive">
                <option value="true">${messages.getMessage("Active",lang)}</option>
                <option value="false">${messages.getMessage("DeActive",lang)}</option>
            </form:select>
            <button type="submit" value="Submit">${messages.getMessage("Submit",lang)}</button>
            <button id="clear">${messages.getMessage("Clear",lang)}</button>
        </form:form>
        <input type="text" id="myInput" onkeyup="filterTable(2)" placeholder='${messages.getMessage("Search",lang)}'/>
       <table id="myTable">
          <tr class="header">
                 <th style="width:40%;">${messages.getMessage("Username",lang)}</th>
                 <th style="width:30%;">${messages.getMessage("Edit",lang)}</th>
                 <th style="width:30%;">${messages.getMessage("Remove",lang)}</th>
          </tr>
            <c:forEach items="${users}" var="selected_user">
              <tr>
                  <td>${selected_user.username}</td>
                  <td>${selected_user.isActive}</td>
                  <td>${selected_user.password}</td>
                  <td><button id="editBtn${selected_user.id}" name="editBtn${selected_user.id}" value="${selected_user.id}">${messages.getMessage("Edit",lang)}</button></td>
                  <td><button id="removeBtn${selected_user.id}" name="removeBtn${selected_user.id}" value="${selected_user.id}">${messages.getMessage("Remove",lang)}</button></td>
              </tr>
           </c:forEach>
        </table>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
