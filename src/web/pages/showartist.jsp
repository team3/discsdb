<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<jsp:useBean id="artist" scope="request" class="ua.edu.sumdu.lab3.model.Artist" />
<jsp:useBean id="genres" scope="request" class ="ua.edu.sumdu.lab3.CollectionWrapper" />
<c:set var="artByCountryPath" value="/artist/all?country="/>

<html>
    <head>
        <title><jsp:getProperty name="artist" property="name" /></title>
        <c:set var="stylepath" value="/pages/css/style.css" />
        <c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
        <c:set var="genresPath" value="/album/all?genre=" />
        <link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
    </head>
<body>    
    <div class="allpage">
        <%@include file="menu.jsp" %>
        <p style = "margin-left: 200px">
        <a href = <c:out value= "${pageContext.request.contextPath}" /> >Main</a>&rarr;
        <a href = <c:out value= "${pageContext.request.contextPath}/artist/all"/> >Artists </a>&rarr;
        <font color = "red">
            <c:choose>
                <c:when test="${artist.name == null}">
                    No artist
                </c:when>
                <c:otherwise>
                    <c:out value="${artist.name}" />
                </c:otherwise>
            </c:choose>
        </font>
        </p>
        
        <c:if test = "${artist.name != null}">
        <div class="maincont">
            <h1> <c:out value="${artist.name}" /></h1>
            <p><b>Country: </b><a href=<c:out value="${pageContext.request.contextPath}${artByCountryPath}${artist.country}" /> ><c:out value="${artist.country}" /></a></p>
            <p><b>Genre:</b> 
                <c:set var="counter" value="${0}" />
                <c:forEach var="genre" items="${genres.collection}">
                    <c:set var="counter" value="${counter+1}" />
                    <c:choose>
                        <c:when test="${counter < genres.size}">
                            <a href=<c:out value="${pageContext.request.contextPath}${genresPath}${genre}" /> ><c:out value="${genre}" /></a>,&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href=<c:out value="${pageContext.request.contextPath}${genresPath}${genre}" /> ><c:out value="${genre}" /></a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </p>
            <p><b>Info: </b></p>
            <p><i><c:out value="${artist.info}" /></i></p>
            <p>
                <a href = <c:out value= "${pageContext.request.contextPath}/editartist?id=${artist.id}" /> >Edit</a>
            </p>
            <p>
                <a href = <c:out value= "${pageContext.request.contextPath}/remove?obj=artist&id=${artist.id}&mode=self" /> >Remove</a>
            </p>
        </div>
        </c:if>
    </div>
</body>
</html>
