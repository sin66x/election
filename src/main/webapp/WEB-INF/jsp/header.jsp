<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%!
public boolean isAdmin(ServletRequest request) {
    java.util.Collection<org.springframework.security.core.authority.SimpleGrantedAuthority> authorities =
            (java.util.Collection<org.springframework.security.core.authority.SimpleGrantedAuthority>)    org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    for (org.springframework.security.core.authority.SimpleGrantedAuthority authority: authorities)
    {
        if (authority.toString().equals("ROLE_admin"))
            return true;
    }
    return false;
}
%>
<nav class="navbar navbar-inverse">
<c:if test='${"rtl".equals(langDir)}'>
    <div class="container" style='float:right'>
</c:if>
<c:if test='${"ltr".equals(langDir)}'>
    <div class="container" style='float:left'>
</c:if>
        <div class="navbar-header" style="float:inherit">
            <a class="navbar-brand" href="/"><img src="images/home.png"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse" style="float:inherit">
            <ul class="nav navbar-nav">
                <% if(isAdmin(request)) { %>
                    <li id="admin_header_category"><a href="/category">${messages.getMessage("Categories",lang)}</a></li>
                    <li id="admin_header_election"><a href="/election">${messages.getMessage("Elections",lang)}</a></li>
                    <li id="admin_header_user"><a href="/user">${messages.getMessage("Users",lang)}</a></li>

               <% } else { %>
                    <li class="active"><a href="/">${messages.getMessage("Home",lang)}</a></li>
               <% } %>

            </ul>
        </div>
    </div>
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
        <li><button type="submit" value="Logout">${messages.getMessage("Logout",lang)}</button></li>
    </form:form>
</nav>