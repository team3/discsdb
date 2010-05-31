<%@ page session="false" isErrorPage="true" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
    
<c:set var = "stylepath" value = "/pages/css/style.css" />
<c:set var = "truepath" value = "${pageContext.request.contextPath}${stylepath} "/>  
<html>
<head><title>ST | 404 Not Found</title></head>
<link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
<body>
    <div class="allpage">
        <%@include file="menu.jsp" %>
        <div class="maincont">
            <p>This page was not found!</p>
        </div>
    </div>
</body>
</html>
