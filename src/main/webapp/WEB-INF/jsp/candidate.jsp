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
    <script src="/script/candidate.js"></script>
    <script src="/script/common.js"></script>

</head>
<body>

    <%@include file='header.jsp'%>

	<div class="container">
        <form:form action="candidate" method="POST" modelAttribute="candidateDTO">
            id:	<form:input type="text" id="id" name="id" path="id"/><br/>
            firstName:	<form:input type="text" id="firstName" name="firstName" path="firstName"/><br/>
            lastName:	<form:input type="text" id="lastName" name="lastName" path="lastName"/><br/>
            fatherName:	<form:input type="text" id="fatherName" name="fatherName" path="fatherName"/><br/>
            candidateCode:	<form:input type="text" id="candidateCode" name="candidateCode" path="candidateCode" disabled="true"/><br/>
            birthdate:	<form:input type="text" id="birthdate" name="birthdate" path="birthdate" placeholder="yyyy/mm/dd"/><br/>
            personalCode:	<form:input type="text" id="personalCode" name="personalCode" path="personalCode"/><br/>
            birthCity:	<form:input type="text" id="birthCity" name="birthCity" path="birthCity"/><br/>
            emplyDate:	<form:input type="text" id="employDate" name="employDate" path="employDate" placeholder="yyyy/mm/dd"/><br/>
            provinceCode:	<form:select id="provinceCode" name="provinceCode" path="provinceCode"/><br/>
            branchCode:	<form:select id="branchCode" name="branchCode" path="branchCode"/><br/>
            officialPosition:	<form:input type="text" id="officialPosition" name="officialPosition" path="officialPosition"/><br/>
            educationDegree:	<form:input type="text" id="educationDegree" name="educationDegree" path="educationDegree"/><br/>
            educationField:	<form:input type="text" id="educationField" name="educationField" path="educationField"/><br/>
            educationUni:	<form:input type="text" id="educationUni" name="educationUni" path="educationUni"/><br/>
            internalHistory:	<form:input type="text" id="internalHistory" name="internalHistory" path="internalHistory"/><br/>
            externalHistory:	<form:input type="text" id="externalHistory" name="externalHistory" path="externalHistory"/><br/>
            <form:input type="hidden" id="election" name="election" path="election"  /><br/>
            isActive:	<form:select id="isActive" name="isActive" path="isActive">
                            <option value="true">true</option>
                            <option value="false">false</option>
                        </form:select>

            <br/>

            <button type="submit" id="submit" name="submit" >Submit</button>
            <button type="button" id="clear" name="clear" >Clear</button>
        </form:form>

            <c:forEach items="${candidates}" var="candidate">
                 ${candidate.firstName} - ${candidate.lastName} - ${candidate.isActive}
                 <button type="button" id="editBtn${candidate.id}" name="editBtn${candidate.id}" value="${candidate.id}">Edit</button>
                 <button type="button" id="removeBtn${candidate.id}" name="removeBtn${candidate.id}" value="${candidate.id}">Remove</button>
                <br/>
            </c:forEach>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
