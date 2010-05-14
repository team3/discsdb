<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<jsp:useBean id="dates" scope="request" class="java.util.ArrayList" />

<html>
    <head>
        <title>Dates</title>
        <c:set var="stylepath" value="/pages/css/style.css" />
        <c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
        <link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
    </head>
<body>    
    <div class="allpage">
        <%@include file="menu.jsp" %>
        <div class="maincont">
            <p>
            <c:forEach var = "year" begin="0" items="${dates}">
                <c:set var="truepath" value="${pageContext.request.contextPath}${albumspath}?year=${year} "/>
                <a href=<c:out value="${truepath}"/>><c:out value="${year}"/>&nbsp;</a>
            </c:forEach>
            </p>
        </div>
    </div>
</body>
</html>
