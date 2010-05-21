<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
<%@page isErrorPage = "true" %>

<html>
    <head>
        <title>DiscsDB | Error</titile>
    </head>
    
    <body>
        <%@include file="menu.jsp" %>
        <link rel="stylesheet" href =<c:out value="${pageContext.request.contextPath}/pages/css/style.css" /> type="text/css" />
        <div class = "error">
            <c:choose>
                <c:when test="${error != null}">
                    <c:out value = "${error}" />
                </c:when>
                <c:otherwise>
                    No error.
                </c:otherwise>
            </c:choose>
        <%= exception.getMessage() %>    
        </div>
    </body>
    
</html>
