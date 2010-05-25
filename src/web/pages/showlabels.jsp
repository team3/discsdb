<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<jsp:useBean id="label" scope="request" class="ua.edu.sumdu.lab3.model.Label" />
<jsp:useBean id="labels" scope="request" class="java.util.LinkedList" />
<jsp:useBean id="number" scope="request" class="java.lang.Integer" />

<html>
    <head>
        <title>Labels</title>
        <c:set var="stylepath" value="/pages/css/style.css" />
        <c:set var="removepath" value="/remove?obj=label&id="/>
        <c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
        <link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
    </head>
<body>    
    <div class="allpage">
        <%@include file="menu.jsp" %>
        <div class="maincont">
            
            <table>
            <tr>
                <td>Logo</td>
                <td>Label name</td>
                <td></td>
                <td></td>
            </tr>
            <c:set var="truepath" value="${pageContext.request.contextPath}${removepath}"/>
            <c:forEach var="lbl" begin="0" items="${labels}">
                <tr>
                    <td>
                        <img src=<c:out value="${lbl.logo}" /> width="50" height="50" align="left" alt="cover"/>
                    </td>
                    
                    <td>
                        <a href=<c:out value="${pageContext.request.contextPath}/label?id=${lbl.id}" /> ><c:out value="${lbl.name}" /></a>
                    </td>
                    
                    <td>
                        <a href =<c:out value="${pageContext.request.contextPath}${edit_label}" /><c:out value="${lbl.id}" /> > Edit</a>
                    </td>
                        <c:choose>
                        <c:when test="${param.page != null}">
                            <td><a href =<c:out value="${pageContext.request.contextPath}${removepath}"/><c:out value="${lbl.id}"/>&page=<c:out value="${param.page}"/> >Remove</a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href =<c:out value="${pageContext.request.contextPath}${removepath}"/><c:out value="${lbl.id}"/> >Remove</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </table>
        </div>
    </div>
    <c:set var="pagepath" value="/label/all?page=" />
    <c:set var="truepath" value="${pageContext.request.contextPath}${pagepath}"/>
    <p>
        <c:if test="${number > 1}">
            <c:forEach var = "i" begin = "1" end="${number}" >
                <c:choose>
                    <c:when test="${i == param.page || (i == 1 && param.page == null)}">
                        <c:out value="${i}" />
                    </c:when>
                    <c:otherwise>
                        <a href =<c:out value="${truepath}" /><c:out value="${i}" /> ><c:out value="${i}" /></a> &nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:if>
    </p>
</body>
</html>
