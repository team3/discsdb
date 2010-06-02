<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/fmt.tld" prefix="fmt" %>

<jsp:useBean id="album" scope="request" class="ua.edu.sumdu.lab3.model.Album" />
<jsp:useBean id="albums" scope="request" class="java.util.LinkedList" />
<jsp:useBean id="number" scope="request" class="java.lang.Integer" />
    
    <c:set var = "stylepath" value = "/pages/css/style.css" />
    <c:set var = "truepath" value = "${pageContext.request.contextPath}${stylepath} "/>   
    <c:set var = "removepath" value = "/remove?obj=album&id=" />     
<html>
    <head>
        <title>ST | Albums</title>
        <link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
    </head>
<body>    
    <div class="allpage">
        
        <%@include file="menu.jsp" %>
        <div class="maincont">
            <h2>Albums</h2>
            <c:if test = "${albums == null}">
                <h3>No albums found</h3>
            </c:if>
            
            <table>
            <tr>
                <td>Album name</td>
                <td>Artist</td>
                <td>Released</td>
                <td>Genre</td>
                <td>Type</td>
                <td>Label</td>
                <td></td>
                <td></td>
            </tr>
            <c:set var="truepath" value="${pageContext.request.contextPath}${removepath}"/>
            <c:forEach var="alb" begin="0" items="${albums}">
                <tr class = "overed">
                    <td><a href =<c:out value="${pageContext.request.contextPath}${albpath}${alb.id}"/> ><c:out value="${alb.name}" /></a></td>
                    <c:set var="truepath" value="${pageContext.request.contextPath}${artpath}${alb.artist} "/>
                    
                    <td>
                        <c:choose>
                            <c:when test="${alb.artist > 0}">
                                <a href=<c:out value="${truepath}" /> ><c:out value="${alb.artistName}" /></a>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${alb.artistName}" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    
                    <td>

                        <a 
                            href = "<c:out value="${pageContext.request.contextPath}" />/album/all?year=<fmt:formatDate pattern="yyyy" value="${alb.release}" />" >
                            <fmt:formatDate pattern="dd.MM.yyyy" value="${alb.release}" /> 
                        </a>
                    </td>
                    
                    <td>

                        <a href =<c:out value="${pageContext.request.contextPath}${pagepath}${genreparam}${alb.genre}" /> >
                        <c:out value="${alb.genre}" /></a>
                    </td>
                    
                    <td>
                        <c:out value="${alb.type}" />
                    </td>
                    
                    <td>
                        <c:choose>
                            <c:when test="${alb.label > 0}">
                                <a href =<c:out value="${pageContext.request.contextPath}${lblpath}${alb.label}" /> >
                                <c:out value="${alb.labelName}" /></a>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${alb.labelName}" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    
                    <td>
                        <a href =<c:out value="${pageContext.request.contextPath}${edit_album}" /><c:out value="${alb.id}" /> > Edit</a>
                    </td>
                    
                    <c:choose>
                        <c:when test="${param.page != null}">
                            <td>
                                <a href =<c:out value="${pageContext.request.contextPath}${removepath}"/><c:out value="${alb.id}"/>&page=<c:out value="${param.page}"/> >
                                Remove</a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <a href =<c:out value="${pageContext.request.contextPath}${removepath}"/><c:out value="${alb.id}"/> >Remove</a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </table>
    </div>
    <c:set var="yearparam" value="?year=" />
    <c:set var="pagelast" value="&page=" />
    <c:set var="pagefirst" value="?page=" />
    <c:set var="genreparam" value="?genre=" />
    <c:set var="pagepath" value="/album/all" />
        <c:if test="${number > 1}">
            <div class = "pages">
            <span class="yap">[</span>
            <c:forEach var = "i" begin = "1" end="${number}" >
                <c:choose>
                    <c:when test="${i == param.page || (i == 1 && param.page == null)}">
                        <span class="choosed"><c:out value="${i}" /></span>
                        <c:if test = "${i != number}">
                            <span class = "yap"> | </span>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${param.year != null}">
                            <c:set var="truepath" value="${pageContext.request.contextPath}${pagepath}${yearparam}${param.year}${pagelast}${i}" />
                            <span class = "unchoosed"><a href =<c:out value="${truepath}" /> ><c:out value="${i}" /></a></span>
                            <c:if test = "${i != number}">
                                <span class = "yap"> | </span>
                            </c:if>
                        </c:if>
                        <c:if test="${param.genre != null}">
                            <c:set var="truepath" value="${pageContext.request.contextPath}${pagepath}${genreparam}${param.genre}${pagelast}${i}" />
                            <span class = "unchoosed"><a href =<c:out value="${truepath}" /> ><c:out value="${i}" /></a><span>
                            <c:if test = "${i != number}">
                                <span class = "yap"> | </span>
                            </c:if>
                        </c:if>
                        <c:if test="${param.year == null && param.genre == null}">
                            <c:set var="truepath" value="${pageContext.request.contextPath}${pagepath}${pagefirst}" />
                            <span class = "unchoosed"><a href =<c:out value="${truepath}" /><c:out value="${i}" /> ><c:out value="${i}" /></a></span> 
                            <c:if test = "${i != number}">
                                <span class = "yap"> | </span>
                            </c:if>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <span class = "yap">]</span>
            </div>
        </c:if>
    </div>
    <c:set var = "team3" value = "/pages/images/team3_logo.png" />
    <div class = "footer">
        <span><img border = "0" src = <c:out value="${pageContext.request.contextPath}${team3}" /> alt= "logo" /><span>
    </div>
</body>
</html>
