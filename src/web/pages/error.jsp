<%@ page contentType="text/html"%>
<%@ page language="java"%>
<%@ page isErrorPage="true" %>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<html>
<head>
<title>ST | ERROR</title>
<c:set var="stylepath" value="/pages/css/style.css" />
<c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
<link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
</head>

<body>
<div class="allpage">
    <%@include file="menu.jsp" %>
    <div class="maincont">
        <c:set var = "picpath" value = "/pages/images/sinatra.png" />
        <img border = "0" src = <c:out value="${pageContext.request.contextPath}${picpath}" /> align = "right" alt= "sinatra" />
        <h1>Sorry, but service is down. </h1>
        <b> ERROR </b> :<BR>
        
        <span id = "exception"><%= exception.getMessage() %></span>
    </div>
</div>
<c:set var = "team3" value = "/pages/images/team3_logo.png" />
<div class = "footer">
    <span><img border = "0" src = <c:out value="${pageContext.request.contextPath}${team3}" /> alt= "logo" /><span>
</div>
</body>
</html>
