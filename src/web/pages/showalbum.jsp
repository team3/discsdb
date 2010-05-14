<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="ua.edu.sumdu.lab3.model.Album"%>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/fmt.tld" prefix="fmt" %>

<jsp:useBean id="album" scope="request" class="ua.edu.sumdu.lab3.model.Album" />

<html>
<head>
<title>Discs storage</title>
<c:set var="stylepath" value="/pages/css/style.css" />
<c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
<link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />

</head>
<body>
<div class="allpage">
    <%@include file="menu.jsp" %>
    <c:set var="artpath" value="/artist?id=" />
    <c:set var="lblpath" value="/label?id=" />
    <c:set var="genrepath" value="?genre=" />
    <c:set var="truepath" value="${pageContext.request.contextPath}${albumspath}${genrepath}"/>
    
    <p style = "margin-left: 200px">
    <a href = "<c:out value= "${pageContext.request.contextPath}" /> ">Main</a>&rarr;
    <a href= "<c:out value= "${pageContext.request.contextPath}/search?search=${album.artistName}&by=artist" />" >Albums of <c:out value="${album.artistName}" /></a>&rarr;
    <font color = "red"><c:out value="${album.name}" /></font>
    </p>
    
    <div class="maincont">
        
        <img src=<c:out value="${album.cover}" /> width="220" height="220" align="left" alt="cover" style = "margin-right: 15px;" />
        <h1> <c:out value="${album.name}" /> by <a href=<c:out value= "${pageContext.request.contextPath}${artpath}${album.artist}" />><c:out value="${album.artistName}" /></a> </h1>
        <p><b>Type: </b><c:out value="${album.type}" /></p>
        <p><b>Release: </b> <fmt:formatDate pattern="dd.MM.yyyy" value="${album.release}" /> </p>
        <p><b>Label: </b><a href=<c:out value= "${pageContext.request.contextPath}${lblpath}${album.label}" />> <c:out value="${album.labelName}" /></a></p>
        <p><b>Genre: </b> <a href=<c:out value="${truepath}${album.genre}" /> ><c:out value="${album.genre}" /></a></p>
        <p>
            <b>Review: </b> <br /> 
            <i><c:out value="${album.review}" /></i>
        </p>
        <p>
            <a href = <c:out value= "${pageContext.request.contextPath}/editalbum?id=${album.id}" /> >Edit</a>
        </p>
    </div>
</div>
</body>
</html>
