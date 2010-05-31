<%@ page contentType="text/html"%>
<%@ page language="java"%>
<%@ page isErrorPage="true" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
<jsp:useBean id="exceptionBean" class="ua.edu.sumdu.lab3.ExceptionBean"/>
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
        <h1>Sorry, but service is down. </h1>
        <b> ERROR </b> :<BR>
        <c:set var="throwable" value="${requestScope['javax.servlet.error.exception']}"/>
        <c:set target="${exceptionBean}" property="throwable" value="${throwable}"/>
        <p><c:out value="${exceptionBean}"/></p>
    </div>
</div>
</body>
</html>
