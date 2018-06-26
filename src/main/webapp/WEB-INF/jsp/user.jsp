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
        <form:form action="user" method="POST" modelAttribute="user" class="edit_box">
            <div class="error_msg">
                ${errorMessage}
            </div>

            <form:input name="id" id="id" path="id" type="hidden"/>
                <table>
                    <tr>
                        <td><label>${messages.getMessage("Username",lang)}</label></td>
                        <td><form:input name="username" id="username" path="username" /></td>
                        <td><label>${messages.getMessage("Role",lang)}</label></td>
                        <td>
                        <form:select id="role" path="role">
                            <option value="ROLE_admin">${messages.getMessage("Admin",lang)}</option>
                            <option value="ROLE_user">${messages.getMessage("Voter",lang)}</option>
                        </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>${messages.getMessage("Password",lang)}</label></td>
                        <td><form:input name="password" id="password" path="password" /></td>
                        <td><label>${messages.getMessage("Status",lang)}</label></td>
                        <td>
                        <form:select id="isActive" path="isActive">
                            <option value="true">${messages.getMessage("Active",lang)}</option>
                            <option value="false">${messages.getMessage("DeActive",lang)}</option>
                        </form:select>
                        </td>
                    </tr>
                </table>
            <button class="btn_submit" type="submit" value="Submit">${messages.getMessage("Submit",lang)}</button>
            <button class="btn_clear" id="clear">${messages.getMessage("Clear",lang)}</button>
        </form:form>

        <div class="data_table">
        <input class="search_box" type="text" id="myInput" onkeyup="filterTable(2)" placeholder='${messages.getMessage("Search",lang)}'/>
       <table id="myTable" class="table_total">
          <tr >
                 <th class="table_header" style="width:25%">${messages.getMessage("Username",lang)}</th>
                 <th class="table_header" style="width:25%">${messages.getMessage("Status",lang)}</th>
                 <th class="table_header" style="width:25%">${messages.getMessage("Role",lang)}</th>
                 <th class="table_header" style="width:10%"></th>
                 <th class="table_header" style="width:10%"></th>
          </tr>
            <c:forEach items="${users}" var="selected_user">
              <tr>
                  <td>${selected_user.username}</td>
                  <td>${selected_user.isActive}</td>
                  <td>${selected_user.role}</td>
                  <td><button class="btn_submit" id="editBtn${selected_user.id}" name="editBtn${selected_user.id}" value="${selected_user.id}">${messages.getMessage("Edit",lang)}</button></td>
                  <td><button class="btn_clear" id="removeBtn${selected_user.id}" name="removeBtn${selected_user.id}" value="${selected_user.id}">${messages.getMessage("Remove",lang)}</button></td>
              </tr>
           </c:forEach>
        </table>
        </div>
	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
