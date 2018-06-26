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
        <form:form action="candidate" method="POST" modelAttribute="candidateDTO" class="edit_box">
            <div class="error_msg">
                ${errorMessage}
            </div>
            <form:input type="hidden" id="id" name="id" path="id"/><br/>

            <table>
                <tr>
                    <td>${messages.getMessage("Name",lang)}</td>
                    <td>	<form:input type="text" id="firstName" name="firstName" path="firstName"/></td>
                    <td>${messages.getMessage("LastName",lang)}</td>
                    <td>	<form:input type="text" id="lastName" name="lastName" path="lastName"/></td>
                </tr>
                <tr>
                    <td>${messages.getMessage("FatherName",lang)}</td>
                    <td>	<form:input type="text" id="fatherName" name="fatherName" path="fatherName"/></td>
                    <td>${messages.getMessage("CandidateCode",lang)}</td>
                    <td>	<form:input type="text" id="candidateCode" name="candidateCode" path="candidateCode" disabled="true"/></td>
                </tr>
                <tr>
                    <td>${messages.getMessage("BirthDate",lang)}</td>
                    <td>	<form:input type="text" id="birthdate" name="birthdate" path="birthdate" placeholder="yyyy/mm/dd"/></td>
                    <td>${messages.getMessage("PersonalCode",lang)}</td>
                    <td>	<form:input type="text" id="personalCode" name="personalCode" path="personalCode"/></td>
                </tr>
                <tr>
                    <td>${messages.getMessage("BirthCity",lang)}</td>
                    <td>	<form:input type="text" id="birthCity" name="birthCity" path="birthCity"/></td>
                    <td>${messages.getMessage("EmployDate",lang)}</td>
                    <td>	<form:input type="text" id="employDate" name="employDate" path="employDate" placeholder="yyyy/mm/dd"/></td>
                </tr>
                <tr>
                    <td>${messages.getMessage("Province",lang)}</td>
                    <td>	<form:select id="provinceCode" name="provinceCode" path="provinceCode"/></td>
                    <td>${messages.getMessage("Branch",lang)}</td>
                    <td>	<form:select id="branchCode" name="branchCode" path="branchCode"/></td>
                </tr>
                <tr>
                    <td>${messages.getMessage("OfficialPosition",lang)}</td>
                    <td>	<form:input type="text" id="officialPosition" name="officialPosition" path="officialPosition"/></td>
                    <td>${messages.getMessage("EducationDegree",lang)}</td>
                    <td>	<form:input type="text" id="educationDegree" name="educationDegree" path="educationDegree"/></td>
                </tr>
                <tr>
                    <td>${messages.getMessage("EducationField",lang)}</td>
                    <td>	<form:input type="text" id="educationField" name="educationField" path="educationField"/></td>
                    <td>${messages.getMessage("EducationUni",lang)}</td>
                    <td>	<form:input type="text" id="educationUni" name="educationUni" path="educationUni"/></td>
                </tr>
                <tr>
                    <td>${messages.getMessage("InternalHistory",lang)}</td>
                    <td>	<form:textarea type="text" id="internalHistory" name="internalHistory" path="internalHistory"/></td>
                    <td>${messages.getMessage("ExternalHistory",lang)}</td>
                    <td>	<form:textarea type="text" id="externalHistory" name="externalHistory" path="externalHistory"/>
                    <form:input type="hidden" id="election" name="election" path="election"  /></td>
                </tr>
                <tr>
                    <td>${messages.getMessage("Status",lang)}</td>
                    <td>
                                <form:select id="isActive" name="isActive" path="isActive">
                                    <option value="true">${messages.getMessage("Active",lang)}</option>
                                    <option value="false">${messages.getMessage("DeActive",lang)}</option>
                                </form:select>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </tr>
            </table>

            <button class="btn_submit" type="submit" id="submit" name="submit" >${messages.getMessage("Submit",lang)}</button>
            <button class="btn_clear" type="button" id="clear" name="clear" >${messages.getMessage("Clear",lang)}</button>
        </form:form>
        <div class="data_table">
            <input class="search_box" type="text" id="myInput" onkeyup="filterTable(2)" placeholder='${messages.getMessage("Search",lang)}'/>
            <table id="myTable" class="table_total">
            <tr>
                <th class="table_header">
                    ${messages.getMessage("FirstNameHeader",lang)}
                </th>
                <th class="table_header">
                    ${messages.getMessage("LastNameHeader",lang)}
                </th>
                <th class="table_header">
                    ${messages.getMessage("Status",lang)}
                </th>
                <th class="table_header">
                    ${messages.getMessage("CandidateCodeHeader",lang)}
                </th>
                <th class="table_header">
                    ${messages.getMessage("PersonalCodeHeader",lang)}
                </th>
                <th class="table_header">
                    ${messages.getMessage("OfficialPositionHeader",lang)}
                </th>
                <th class="table_header">
                </th>
                <th class="table_header">
                </th>

            </tr>
            <c:forEach items="${candidates}" var="candidate">
               <tr>
                    <td>
                        ${candidate.firstName}
                    </td>
                    <td>
                        ${candidate.lastName}
                    </td>
                    <td>
                        ${candidate.isActive}
                    </td>
                    <td>
                        ${candidate.candidateCode}
                    </td>
                    <td>
                        ${candidate.personalCode}
                    </td>
                    <td>
                        ${candidate.officialPosition}
                    </td>
                    <td>
                        <button class="btn_submit" type="button" id="editBtn${candidate.id}" name="editBtn${candidate.id}" value="${candidate.id}">${messages.getMessage("Edit",lang)}</button>
                    </td>
                    <td>
                         <button class="btn_clear" type="button" id="removeBtn${candidate.id}" name="removeBtn${candidate.id}" value="${candidate.id}">${messages.getMessage("Remove",lang)}</button>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </div>
	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
