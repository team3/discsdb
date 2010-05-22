<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="ua.edu.sumdu.lab3.model.Album"%>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>


<jsp:useBean id="random" scope="request" class="ua.edu.sumdu.lab3.model.Album" />
<jsp:useBean id="latest" scope="request" class="java.util.LinkedList" />

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
        <%
            DateFormat dfone = new SimpleDateFormat("dd.MM.yyyy");
            DateFormat dftwo = new SimpleDateFormat("yyyy");
            String formatedYears = dftwo.format(random.getRelease());
            String formatedMonthsYears = dfone.format(random.getRelease());
            String countryPath = "/album/all?year="+formatedYears;
        %>
        <p><b>Release:</b> <a href=<%=request.getContextPath() %><%=countryPath %>><%=formatedMonthsYears %></a></p>
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
            <c:forEach var="lalbum" begin="0" items="${latest}">
                <div class = "smallalbum">
                    <img src = <c:out value="${lalbum.cover}" /> width = "100" height = "100" alt= "" />
                    <c:choose>
                        <c:when test = "${lalbum.artist >= 0}">
                            <a href=<c:out value="${pageContext.request.contextPath}${artpath}${lalbum.artist}"/>>
                                <c:out value="${lalbum.artistName}"/>
                            </a>
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
</body>
</html>
