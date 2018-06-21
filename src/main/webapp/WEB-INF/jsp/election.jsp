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
<body>

    <%@include file='header.jsp'%>


	<div class="container" >
        <form:form action="election" method="POST" modelAttribute="electionDTO">
            <form:input name="id" id="id" path="id" type="hidden"/>

            Name:
            <form:input name="name" id="name" path="name" />

            MaxSelection:
            <form:input name="maxSelection" id="maxSelection" path="maxSelection" />
            <br/>

            Start<br/>
            date:
            <select class="input_date" id="startDay">
                <option value="01">1</option>
                <option value="02">2</option>
                <option value="03">3</option>
                <option value="04">4</option>
                <option value="05">5</option>
                <option value="06">6</option>
                <option value="07">7</option>
                <option value="08">8</option>
                <option value="09">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
                <option value="25">25</option>
                <option value="26">26</option>
                <option value="27">27</option>
                <option value="28">28</option>
                <option value="29">29</option>
                <option value="30">30</option>
                <option value="31">31</option>
            </select>
             /
            <select class="input_date" id="startMonth">
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
            <select class="input_date" id="startYear">
                <option value="1397">۱۳۹۷</option>
                <option value="1398">۱۳۹۸</option>
                <option value="1399">۱۳۹۹</option>
                <option value="1400">۱۴۰۰</option>
            </select>

            <br/>End<br/>
            date:
            <select class="input_date" id="endDay">
                <option value="01">1</option>
                <option value="02">2</option>
                <option value="03">3</option>
                <option value="04">4</option>
                <option value="05">5</option>
                <option value="06">6</option>
                <option value="07">7</option>
                <option value="08">8</option>
                <option value="09">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
                <option value="25">25</option>
                <option value="26">26</option>
                <option value="27">27</option>
                <option value="28">28</option>
                <option value="29">29</option>
                <option value="30">30</option>
                <option value="31">31</option>
            </select>
                 /
            <select class="input_date" id="endMonth">
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
            <select class="input_date" id="endYear">
                <option value="1397">۱۳۹۷</option>
                <option value="1398">۱۳۹۸</option>
                <option value="1399">۱۳۹۹</option>
                <option value="1400">۱۴۰۰</option>
            </select>

            <br/>
            <form:select id="category" path="category">
                <option value="0">NONE</option>
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.id}">${cat.name}</option>
                </c:forEach>
            </form:select>

            <button type="submit" value="Submit" >Submit</button>
            <button id="clear" type="button">Clear</button>

            <form:input type="hidden" id="startDate" path="startDate" name="startDate"/>
            <form:input type="hidden" id="endDate" path="endDate" name="endDate"/>

        </form:form>

        <c:forEach items="${elections}" var="election">
             ${election.name} - ${election.jalaliStartDate} - ${election.jalaliEndDate}
             <button type="button" id="editBtn${election.id}" name="editBtn${election.id}" value="${election.id}">Edit</button>
             <button type="button" id="removeBtn${election.id}" name="removeBtn${election.id}" value="${election.id}">Remove</button>
             <a href="candidate?election=${election.id}">Candidates</a>
             <a href="uploadVoter?election=${election.id}">Voters</a>
            <br/>
        </c:forEach>


	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
