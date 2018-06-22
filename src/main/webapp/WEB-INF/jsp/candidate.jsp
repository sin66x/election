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
<body dir="${langDir}">

    <%@include file='header.jsp'%>

	<div class="container">
        <form:form action="candidate" method="POST" modelAttribute="candidateDTO">
            <form:input type="hidden" id="id" name="id" path="id"/><br/>
            ${messages.getMessage("Name",lang)}	<form:input type="text" id="firstName" name="firstName" path="firstName"/><br/>
            ${messages.getMessage("LastName",lang)}	<form:input type="text" id="lastName" name="lastName" path="lastName"/><br/>
            ${messages.getMessage("FatherName",lang)}	<form:input type="text" id="fatherName" name="fatherName" path="fatherName"/><br/>
            ${messages.getMessage("CandidateCode",lang)}	<form:input type="text" id="candidateCode" name="candidateCode" path="candidateCode" disabled="true"/><br/>
            ${messages.getMessage("BirthDate",lang)}	<form:input type="text" id="birthdate" name="birthdate" path="birthdate" placeholder="yyyy/mm/dd"/><br/>
            ${messages.getMessage("PersonalCode",lang)}	<form:input type="text" id="personalCode" name="personalCode" path="personalCode"/><br/>
            ${messages.getMessage("BirthCity",lang)}	<form:input type="text" id="birthCity" name="birthCity" path="birthCity"/><br/>
            ${messages.getMessage("EmployDate",lang)}	<form:input type="text" id="employDate" name="employDate" path="employDate" placeholder="yyyy/mm/dd"/><br/>
            ${messages.getMessage("Province",lang)}	<form:select id="provinceCode" name="provinceCode" path="provinceCode"/><br/>
            ${messages.getMessage("Branch",lang)}	<form:select id="branchCode" name="branchCode" path="branchCode"/><br/>
            ${messages.getMessage("OfficialPosition",lang)}	<form:input type="text" id="officialPosition" name="officialPosition" path="officialPosition"/><br/>
            ${messages.getMessage("EducationDegree",lang)}	<form:input type="text" id="educationDegree" name="educationDegree" path="educationDegree"/><br/>
            ${messages.getMessage("EducationField",lang)}	<form:input type="text" id="educationField" name="educationField" path="educationField"/><br/>
            ${messages.getMessage("EducationUni",lang)}	<form:input type="text" id="educationUni" name="educationUni" path="educationUni"/><br/>
            ${messages.getMessage("InternalHistory",lang)}	<form:input type="text" id="internalHistory" name="internalHistory" path="internalHistory"/><br/>
            ${messages.getMessage("ExternalHistory",lang)}	<form:input type="text" id="externalHistory" name="externalHistory" path="externalHistory"/><br/>
            <form:input type="hidden" id="election" name="election" path="election"  /><br/>
            ${messages.getMessage("Status",lang)}
                        <form:select id="isActive" name="isActive" path="isActive">
                            <option value="true">${messages.getMessage("Active",lang)}</option>
                            <option value="false">${messages.getMessage("DeActive",lang)}</option>
                        </form:select>

            <br/>

            <button type="submit" id="submit" name="submit" >${messages.getMessage("Submit",lang)}</button>
            <button type="button" id="clear" name="clear" >${messages.getMessage("Clear",lang)}</button>
        </form:form>

            <c:forEach items="${candidates}" var="candidate">
                 ${candidate.firstName} - ${candidate.lastName} - ${candidate.isActive}
                 <button type="button" id="editBtn${candidate.id}" name="editBtn${candidate.id}" value="${candidate.id}">${messages.getMessage("Edit",lang)}</button>
                 <button type="button" id="removeBtn${candidate.id}" name="removeBtn${candidate.id}" value="${candidate.id}">${messages.getMessage("Remove",lang)}</button>
                <br/>
            </c:forEach>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
