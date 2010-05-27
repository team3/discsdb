<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

html>
    <head>
        <title>Artists</title>
        <c:set var="stylepath" value="/pages/css/style.css" />
        <c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
        <link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
    </head>
<body>    
    <div class="allpage">
        <div class = "header">
            <%@include file="menu.jsp" %>
        </div>
        <div class="maincont">
            <select name = "artists" class = "artistslist">
                <c:forEach var="art" begin="0" items="${artists}">
                    <option value = <c:out value="${art.name}" />>
                            <c:out value="${lbl.name}" />
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
</body>
</html>
