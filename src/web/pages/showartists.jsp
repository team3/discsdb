<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<jsp:useBean id="artist" scope="request" class="ua.edu.sumdu.lab3.model.Artist" />
<jsp:useBean id="artists" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="number" scope="request" class="java.lang.Integer" />

<html>
    <head>
        <title>Artists</title>
        <style>
            
            table {
                border-collapse:collapse;
                font-size: 12px;
            }
            
            table,th, td {
                border: 1px solid lightgray;
            }
            
        </style>
        <c:set var="stylepath" value="/pages/css/style.css" />
        <c:set var="removepath" value="/remove?obj=artist&id="/>
        <c:set var="artpath" value="/artist?id=" />
        <c:set var="countrypath" value="/artist/all?country=" />
        <c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
        <link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
    </head>
<body>    
    <div class="allpage">
        <div class = "header">
            <%@include file="menu.jsp" %>
        </div>
        <div class="artistslistcontent">
            <h2>Artists</h2>
            <table width = "100%">
            <tr>
                <td>Artist name</td>
                <td>Country</td>
                <td></td>
                <td></td>
            </tr>
            <c:set var="truepath" value="${pageContext.request.contextPath}${removepath}"/>
            <c:forEach var="art" begin="0" items="${artists}">
                <tr>
                    <td><a href =<c:out value="${pageContext.request.contextPath}${artpath}${art.id}"/> ><c:out value="${art.name}" /></a></td>
                    <td><a href =<c:out value="${pageContext.request.contextPath}${countrypath}${art.country}"/> ><c:out value="${art.country}" /></a></td>
                    <td
                        <a href =<c:out value="${pageContext.request.contextPath}${edit_artist}${art.id}" /> > Edit</a>
                    </td>
                        <c:choose>
                        <c:when test="${param.page != null}">
                            <td><a href =<c:out value="${truepath}"/><c:out value="${art.id}"/>&page=<c:out value="${param.page}"/> >Remove</a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href =<c:out value="${truepath}"/><c:out value="${art.id}"/> >Remove</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </table>
    </div>
    </div>
    <c:set var="pagepath" value="/artist/all" />
    <c:set var="pagefirst" value="?page=" />
    <c:set var="pagelast" value="&page=" />
    <c:set var="countrypath" value="?country=" />
    <p>
        <c:if test="${number > 1}">
            <c:forEach var = "i" begin = "1" end="${number}" >
                <c:choose>
                    <c:when test="${i == param.page || (i == 1 && param.page == null)}">
                        <c:out value="${i}" />
                    </c:when>
                    <c:otherwise>
                        <c:if test="${param.country == null}">
                            <c:set var="truepath" value="${pageContext.request.contextPath}${pagepath}${pagefirst}"/>
                            <a href =<c:out value="${truepath}" /><c:out value="${i}" /> ><c:out value="${i}" /></a> &nbsp;
                        </c:if>
                        <c:if test="${param.country != null}">
                            <c:set var="truepath" value="${pageContext.request.contextPath}${pagepath}${countrypath}${param.country}${pagelast}"/>
                            <a href =<c:out value="${truepath}" /><c:out value="${i}" /> ><c:out value="${i}" /></a> &nbsp;
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:if>
    </p>
</body>
</html>
