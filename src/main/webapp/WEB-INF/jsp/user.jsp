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
<body>

	    <%@include file='header.jsp'%>

	<div class="container">
        <form:form action="user" method="POST" modelAttribute="user">
            <form:input name="id" id="id" path="id" type="hidden"/>

            <label>Insert User Name:</label>
            <form:input name="username" id="username" path="username" />
            <label>Insert Password:</label>
            <form:input name="password" id="password" path="password" />
            <label>Select Role:</label>
            <form:select id="role" path="role">
                <option value="ROLE_admin">Admin</option>
                <option value="ROLE_user">Voter</option>
            </form:select>
            <form:select id="isActive" path="isActive">
                <option value="true">True</option>
                <option value="false">False</option>
            </form:select>
            <button type="submit" value="Submit">Submit</button>
            <button id="clear">clear</button>
        </form:form>
        <input type="text" id="myInput" onkeyup="filterTable(2)" placeholder="Filter"/>
       <table id="myTable">
          <tr class="header">
                 <th style="width:40%;">Username</th>
                 <th style="width:30%;">Edit</th>
                 <th style="width:30%;">Remove</th>
          </tr>
            <c:forEach items="${users}" var="selected_user">
              <tr>
                  <td>${selected_user.username}</td>
                  <td>${selected_user.isActive}</td>
                  <td>${selected_user.password}</td>
                  <td><button id="editBtn${selected_user.id}" name="editBtn${selected_user.id}" value="${selected_user.id}">Edit</button></td>
                  <td><button id="removeBtn${selected_user.id}" name="removeBtn${selected_user.id}" value="${selected_user.id}">Remove</button></td>
              </tr>
           </c:forEach>
        </table>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
