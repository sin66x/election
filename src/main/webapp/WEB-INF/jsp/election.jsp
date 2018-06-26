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
        <form:form action="election" method="POST" modelAttribute="electionDTO" class="edit_box">
            <div class="error_msg">
                ${errorMessage}
            </div>
            <form:input name="id" id="id" path="id" type="hidden" />
            <table>
            <tr>
                <td>${messages.getMessage("Name",lang)}</td>
                <td><form:input name="name" id="name" path="name" /></td>

                <td>${messages.getMessage("MaxSelection",lang)}</td>
                <td><form:input name="maxSelection" id="maxSelection" path="maxSelection" /></td>
            </tr>

            <tr>
            <td>${messages.getMessage("StartDate",lang)}</td>
            <td>
            <select class="input_date combo_day" id="startDay" >
                <option value="01">۱</option>
                <option value="02">۲</option>
                <option value="03">۳</option>
                <option value="04">۴</option>
                <option value="05">۵</option>
                <option value="06">۶</option>
                <option value="07">۷</option>
                <option value="08">۸</option>
                <option value="09">۹</option>
                <option value="10">۱۰</option>
                <option value="11">۱۱</option>
                <option value="12">۱۲</option>
                <option value="13">۱۳</option>
                <option value="14">۱۴</option>
                <option value="15">۱۵</option>
                <option value="16">۱۶</option>
                <option value="17">۱۷</option>
                <option value="18">۱۸</option>
                <option value="19">۱۹</option>
                <option value="20">۲۰</option>
                <option value="21">۲۱</option>
                <option value="22">۲۲</option>
                <option value="23">۲۳</option>
                <option value="24">۲۴</option>
                <option value="25">۲۵</option>
                <option value="26">۲۶</option>
                <option value="27">۲۷</option>
                <option value="28">۲۸</option>
                <option value="29">۲۹</option>
                <option value="30">۳۰</option>
                <option value="31">۳۱</option>
            </select>
             /
            <select class="input_date combo_month" id="startMonth" >
                 <option value="01">فروردین</option>
                 <option value="02">اردیبهشت</option>
                 <option value="03">خرداد</option>
                 <option value="04">تیر</option>
                 <option value="05">مرداد</option>
                 <option value="06">شهریور</option>
                 <option value="07">مهر</option>
                 <option value="08">آبان</option>
                 <option value="09">آذر</option>
                 <option value="10">دی</option>
                 <option value="11">بهمن</option>
                 <option value="12">اسفند</option>
            </select>
            /
            <select class="input_date combo_year" id="startYear" >
                <option value="1397">۱۳۹۷</option>
                <option value="1398">۱۳۹۸</option>
                <option value="1399">۱۳۹۹</option>
                <option value="1400">۱۴۰۰</option>
            </select>
            </td>
            <td>
            ${messages.getMessage("EndDate",lang)}
            </td>
            <td>
            <select class="input_date combo_day" id="endDay" >
                <option value="01">۱</option>
                <option value="02">۲</option>
                <option value="03">۳</option>
                <option value="04">۴</option>
                <option value="05">۵</option>
                <option value="06">۶</option>
                <option value="07">۷</option>
                <option value="08">۸</option>
                <option value="09">۹</option>
                <option value="10">۱۰</option>
                <option value="11">۱۱</option>
                <option value="12">۱۲</option>
                <option value="13">۱۳</option>
                <option value="14">۱۴</option>
                <option value="15">۱۵</option>
                <option value="16">۱۶</option>
                <option value="17">۱۷</option>
                <option value="18">۱۸</option>
                <option value="19">۱۹</option>
                <option value="20">۲۰</option>
                <option value="21">۲۱</option>
                <option value="22">۲۲</option>
                <option value="23">۲۳</option>
                <option value="24">۲۴</option>
                <option value="25">۲۵</option>
                <option value="26">۲۶</option>
                <option value="27">۲۷</option>
                <option value="28">۲۸</option>
                <option value="29">۲۹</option>
                <option value="30">۳۰</option>
                <option value="31">۳۱</option>
            </select>
                 /
            <select class="input_date combo_month" id="endMonth" >
                 <option value="01">فروردین</option>
                 <option value="02">اردیبهشت</option>
                 <option value="03">خرداد</option>
                 <option value="04">تیر</option>
                 <option value="05">مرداد</option>
                 <option value="06">شهریور</option>
                 <option value="07">مهر</option>
                 <option value="08">آبان</option>
                 <option value="09">آذر</option>
                 <option value="10">دی</option>
                 <option value="11">بهمن</option>
                 <option value="12">اسفند</option>
            </select>
                    /
            <select class="input_date combo_year" id="endYear">
                <option value="1397">۱۳۹۷</option>
                <option value="1398">۱۳۹۸</option>
                <option value="1399">۱۳۹۹</option>
                <option value="1400">۱۴۰۰</option>
            </select>
            </td>
            </tr>
            <tr>
            <td>${messages.getMessage("Category",lang)}</td>
            <td>
            <form:select id="category" path="category">
                <option value="0">${messages.getMessage("SelectOne",lang)}</option>
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.id}">${cat.name}</option>
                </c:forEach>
            </form:select>
            </td>
            <td></td>
            <td></td>
            </tr>
            </table>
            <button class="btn_submit" type="submit" value="Submit" >${messages.getMessage("Submit",lang)}</button>
            <button class="btn_clear" id="clear" type="button">${messages.getMessage("Clear",lang)}</button>

            <form:input type="hidden" id="startDate" path="startDate" name="startDate"/>
            <form:input type="hidden" id="endDate" path="endDate" name="endDate"/>

        </form:form>
        <div class="data_table">
            <input class="search_box" type="text" id="myInput" onkeyup="filterTable(4)" placeholder='${messages.getMessage("Search",lang)}'/>
            <table id="myTable" class="table_total">
                <tr>
                    <th class="table_header">${messages.getMessage("Elections",lang)}</th>
                    <th class="table_header">${messages.getMessage("MaxSelectionHeader",lang)}</th>
                    <th class="table_header">${messages.getMessage("StartDate",lang)}</th>
                    <th class="table_header">${messages.getMessage("EndDate",lang)}</th>
                    <th class="table_header">${messages.getMessage("Category",lang)}</th>
                    <th class="table_header"></th>
                    <th class="table_header"></th>
                    <th class="table_header"></th>
                    <th class="table_header"></th>
                    <th class="table_header"></th>
                </tr>
                <c:forEach items="${elections}" var="election">
                <tr>
                    <td>${election.name}</td>
                    <td>${election.maxSelection}</td>
                    <td>${election.jalaliStartDate}</td>
                    <td>${election.jalaliEndDate}</td>
                    <td>${election.category.name}</td>
                    <td>
                        <button class="btn_submit" type="button" id="editBtn${election.id}" name="editBtn${election.id}" value="${election.id}">${messages.getMessage("Edit",lang)}</button>
                    </td>
                    <td>
                        <button class="btn_clear" type="button" id="removeBtn${election.id}" name="removeBtn${election.id}" value="${election.id}">${messages.getMessage("Remove",lang)}</button>
                    </td>
                    <td>
                         <a href="candidate?election=${election.id}">${messages.getMessage("Candidates",lang)}</a>
                    </td>
                    <td>
                        <a href="uploadVoter?election=${election.id}">${messages.getMessage("Voters",lang)}</a>
                     </td>
                     <td>
                         <a href="candidateUpload?election=${election.id}">${messages.getMessage("UploadCandidate",lang)}</a>
                     </td>
                </tr>
                </c:forEach>
            </table>
        </div>


	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
