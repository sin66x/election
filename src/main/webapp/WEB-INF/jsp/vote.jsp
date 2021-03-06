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
    <script src="/script/vote.js"></script>
    <script src="/script/common.js"></script>

</head>
    <body dir="${langDir}">

        <%@include file='header.jsp'%>

        <div class="container voting_panel">

                <label class="vote_message">${message}</label>
                <c:if test='${"".equals(message)||message==null}'>
                    <input  type="hidden" id="maxSelection" name="maxSelection" value="${maxSelection}" />
                    <input  type="hidden" id="electionId" name="electionId" value="${electionId}" />
                    <input class="search_box" type="text" id="myInput" onkeyup="filterTable(1)" placeholder='     ${messages.getMessage("Search",lang)}'/>

                    <table id="myTable" class="table_total">
                        <tr>
                              <th class="table_header">${messages.getMessage("CandidateCodeHeader",lang)}</th>
                              <th class="table_header">${messages.getMessage("FirstNameHeader",lang)}</th>
                              <th class="table_header">${messages.getMessage("LastNameHeader",lang)}</th>
                              <th class="table_header">${messages.getMessage("FatherNameHeader",lang)}</th>
                              <th class="table_header">${messages.getMessage("BirthdateHeader",lang)}</th>
                              <th class="table_header">${messages.getMessage("PersonalCodeHeader",lang)}</th>
                              <th class="table_header">${messages.getMessage("BirthCityHeader",lang)}</th>
                              <th class="table_header">${messages.getMessage("EmployDateHeader",lang)}</th>
                              <th class="table_header">${messages.getMessage("OfficialPositionHeader",lang)}</th>
                              <th class="table_header">${messages.getMessage("EducationFieldHeader",lang)}</th>
                              <th class="table_header">${messages.getMessage("EducationUniHeader",lang)}</th>
                              <th class="table_header"></th>
                        </tr>
                      <c:forEach items="${candidates}" var="candidate">
                            <tr>
                              <td>${candidate.candidateCode}</td>
                              <td>${candidate.firstName}</td>
                              <td>${candidate.lastName}</td>
                              <td>${candidate.fatherName}</td>
                              <td>${candidate.jalaliBirthdate}</td>
                              <td>${candidate.personalCode}</td>
                              <td>${candidate.birthCity}</td>
                              <td>${candidate.jalaliEmployDate}</td>
                              <td>${candidate.officialPosition}</td>
                              <td>${candidate.educationField}</td>
                              <td>${candidate.educationUni}</td>
                              <td><button type="button " class="addCandidate btn_notvoted" value="${candidate.id}" >+</button></td>
                            </tr>
                      </c:forEach>
                    </table>
                    <button type="button" class="btn_submit" id="vote" name="vote" value="vote">${messages.getMessage("SubmitVote",lang)}</button>
                </c:if>
        </div>
        <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>

</html>
