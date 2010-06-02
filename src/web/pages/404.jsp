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
<c:set var = "team3" value = "/pages/images/team3_logo.png" />
<div class = "footer">
    <span><img border = "0" src = <c:out value="${pageContext.request.contextPath}${team3}" /> alt= "logo" /><span>
</div>
</body>
</html>
