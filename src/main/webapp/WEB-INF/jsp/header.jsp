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
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring Boot</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <% if(isAdmin(request)) { %>
                    <li id="admin_header_home"><a  href="/">Home</a></li>
                    <li id="admin_header_category"><a href="/category">Categories</a></li>
                    <li id="admin_header_election"><a href="/election">Elections</a></li>
                    <li id="admin_header_user"><a href="/user">Users</a></li>
                    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                        <li><button type="submit" value="Logout">Logout</button></li>
                    </form:form>
                    
               <% } else { %>
                    <li class="active"><a href="/">Home</a></li>
                    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                    <li><button type="submit" value="Logout">Logout</button></li>
                    </form:form>
               <% } %>

            </ul>
        </div>
    </div>
</nav>