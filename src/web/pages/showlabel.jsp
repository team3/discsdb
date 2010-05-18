<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<jsp:useBean id="label" scope="request" class="ua.edu.sumdu.lab3.model.Label" />
<jsp:useBean id="children" scope="request" class="ua.edu.sumdu.lab3.CollectionWrapper"/>
<jsp:useBean id="path" scope="request" class="ua.edu.sumdu.lab3.CollectionWrapper"/>

<html>
    <head>
        <title>Label|<jsp:getProperty name="label" property="name" /></title>
        <c:set var="stylepath" value="/pages/css/style.css" />
        <c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
        <link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
    </head>
<body>    
    <div class="allpage">
        <%@include file="menu.jsp" %>
        <c:set var="alblpath" value="/label/all" />
        <c:set var="lblpath" value="/label?id=" />
        <p style = "margin-left: 200px; color: black; font-weight: bold">
            <a href = <c:out value= "${pageContext.request.contextPath}" /> >Main</a>&rarr;
            <a href = <c:out value="${pageContext.request.contextPath}${alblpath}" /> ><b>Labels</b></a>&rarr;
            <c:set var="counter" value="${0}" />
            <c:forEach var="elem" begin="0" items="${path.collection}">
                <c:set var="counter" value="${counter+1}" />
                <c:choose>
                    <c:when test="${counter < path.size}">
                        <a href =<c:out value="${pageContext.request.contextPath}${lblpath}${elem.id}" /> ><b><c:out value="${elem.name}"/></b></a>&rarr;
                    </c:when>
                    <c:otherwise>
                        <b><c:out value="${elem.name}"/></b>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </p>
        <div class="labelslistcontent">
        <img src=<c:out value="${label.logo}" /> width="220" height="220" align="left" alt="cover"/>
        <h1> <c:out value="${label.name}" /></h1>
        <c:if test="${label.majorName != null}">
            <p><b>Major :</b><a href =<c:out value="${pageContext.request.contextPath}${lblpath}${label.major}" /> ><c:out value="${label.majorName}" /></a></p>
        </c:if>
        <p>Info:</p>
        <p><i><c:out value="${label.info}" /></i></p>
        <c:if test="${children.size != 0}">
            <p><b>Labels:</b></p>
            <ul>
            <c:forEach var="child" begin="0" items="${children.collection}">
                <li><a href =<c:out value="${pageContext.request.contextPath}${lblpath}${child.id}" /> ><c:out value="${child.name}" /></a></li>
            </c:forEach>
            </ul>
        </c:if>
        <p>
            <a href = <c:out value= "${pageContext.request.contextPath}/editlabel?id=${label.id}" /> >Edit</a>
        </p>
    </div>
    </div>
</body>
</html>
