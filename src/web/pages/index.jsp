<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="ua.edu.sumdu.lab3.model.Album"%>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/fmt.tld" prefix="fmt" %>


<jsp:useBean id="random" scope="request" class="ua.edu.sumdu.lab3.model.Album" />
<jsp:useBean id="latest" scope="request" class="java.util.ArrayList" />

<html>
<head>
<title>Sweet tunes</title>
<c:set var="stylepath" value="/pages/css/style.css" />
<c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>

<link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />

</head>
<body>
<div class="allpage">
    <%@include file="menu.jsp" %>
    <c:set var = "artpath" value = "/artist?id=" />
    <c:set var="albumspath" value = "/album/all" />
    <c:set var="genrepath" value = "?genre=" />
    <c:set var="truepath" value="${pageContext.request.contextPath}${albumspath}${genrepath}"/>
    <div class="maincont">
        <img src=<c:out value="${random.cover}" /> width="220" height="220" align="left" alt="cover" style= "margin-right: 15px;"/>
        <h1> <c:out value="${random.name}" /> by
        <c:choose>
            <c:when test = "${random.artist >=0}"> 
                <a href=<c:out value= "${pageContext.request.contextPath}${artpath}${random.artist}" />><c:out value="${random.artistName}" /></a> 
            </c:when>
            <c:otherwise>
                <c:out value="${random.artistName}" />
            </c:otherwise>
        </c:choose>
        </h1>
        <p><b>Release:</b> 
            <a 
                href =<c:out value="${pageContext.request.contextPath}" />/album/all?year=<fmt:formatDate pattern="yyyy" value="${random.release}" /> >
                <fmt:formatDate pattern="dd.MM.yyyy" value="${random.release}" /> 
            </a>
        </p>
        <p><b>Label:</b>
        <c:choose>
            <c:when test = "${random.label >= 0}">
                <a href=<c:out value= "${pageContext.request.contextPath}${lblpath}${random.label}" />> <c:out value="${random.labelName}" /></a>
            </c:when>
            <c:otherwise>
                <c:out value="${random.labelName}" />
            </c:otherwise>
        </c:choose>
        </p>
        <p><b>Genre:</b> <a href=<c:out value="${truepath}${random.genre}" /> ><c:out value="${random.genre}" /></a></p>
        <p>
            <b>Review:</b> <br /> 
            <i><c:out value="${random.review}" /></i>
        </p>
    </div>
    <div class="latest">
    <c:choose>
        <c:when test="${latest != null}">
            <c:forEach var="lalbum" items="${latest}">
                <div class = "smallalbum">
                    <br />
                    <a href=<c:out value="${pageContext.request.contextPath}${albpath}${lalbum.id}"/> >
                        <img src = <c:out value="${lalbum.cover}" /> width = "100" height = "100" alt= "" />
                    </a>
                    <br />
                    <c:choose>
                        <c:when test = "${lalbum.artist >= 0}">
                            <a href=<c:out value="${pageContext.request.contextPath}${artpath}${lalbum.artist}"/>>
                                <c:out value="${lalbum.artistName}"/>
                            </a><br />
                        </c:when>
                        <c:otherwise>
                            <c:out value="${lalbum.artistName}"/>
                        </c:otherwise>
                    </c:choose>
                    <a href=<c:out value="${pageContext.request.contextPath}${albpath}${lalbum.id}"/> >
                        <c:out value="${lalbum.name}"/>
                    </a>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            Sorry, <b>0 albums loaded.
        </c:otherwise>
    </c:choose>
    </div>
</div>
<c:set var = "team3" value = "/pages/images/team3_logo.png" />
<div class = "footer">
    <span><img border = "0" src = <c:out value="${pageContext.request.contextPath}${team3}" /> alt= "logo" /><span>
</div>
</body>
</html>
