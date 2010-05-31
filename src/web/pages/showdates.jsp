<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<jsp:useBean id="dates" scope="request" class="java.util.LinkedList" />

<html>
    <head>
        <title>ST | Dates</title>
        <c:set var="stylepath" value="/pages/css/style.css" />
        <c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
        <link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
    </head>
<body>    
    <div class="allpage">
        <%@include file="menu.jsp" %>
        <div class="maincont">
            <h2>Dates</h2>
            <p>
                <c:forEach var = "year" begin="0" items="${dates}">
                    <c:set 
                        var = "truepath" 
                        value = "${pageContext.request.contextPath}${albumspath}?year=${year} "
                    />
                    <a id = "date"
                        href=<c:out value="${truepath}"/>>
                        <c:out value="${year}"/>
                    </a>
                    &nbsp;
                </c:forEach>
            </p>
        </div>
    </div>
</body>
</html>
