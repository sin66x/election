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
    <script src="/script/election.js"></script>
    <script src="/script/common.js"></script>

</head>
<body dir="${langDir}">

    <%@include file='header.jsp'%>


	<div class="container" >

<form method="POST" enctype="multipart/form-data" action="candidateUpload?${_csrf.parameterName}=${_csrf.token}" class="edit_box">
    <div class="error_msg">
        ${errorMessage}
    </div>
    <table>
        <tr>
            <td>
                ${messages.getMessage("UploadCandidatesFile",lang)}
            </td>
            <td>
                <form:input type="file" name="file" path="file"/>
            </td>
        </tr>
    </table>
    <form:input type="hidden" name="electionId" path="electionId" value="${electionId}"/>
    <button class="btn_submit" type="submit" value="Submit" >${messages.getMessage("Submit",lang)}</button>
</form>
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
                </tr>
            </c:forEach>
            </table>
        </div>
	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
