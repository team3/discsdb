<%@ page contentType="text/html"%>
<%@ page language="java"%>
<%@ page isErrorPage="true" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<html>
<head>
<title>ERROR</title>
<c:set var="stylepath" value="/pages/css/style.css" />
<c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
<link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
</head>

<body>
<div class="allpage">
    <%@include file="menu.jsp" %>
    <div class="maincont">
        <h1>Sorry, but service is down. </h1>
        <b> ERROR </b> :<BR>
        <c:out value="${requestScope['javax.servlet.error.message']}" />
    </div>
</div>
</body>
</html>
