<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<jsp:useBean id="label" scope="request" class="ua.edu.sumdu.lab3.model.Label" />

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
        <p style = "margin-left: 200px">
        <a href = "<c:out value= "${pageContext.request.contextPath}" /> ">Main</a>&rarr;
        <a href = <c:out value= "${pageContext.request.contextPath}/label/all"/> >Labels </a>&rarr;
        <font color = "red"><c:out value="${label.name}" /></font>
        </p>
        
        <div class="labelslistcontent">
        <img src=<c:out value="${label.logo}" /> width="220" height="220" align="left" alt="cover"/>
        <h1> <c:out value="${label.name}" /></h1>
        <c:if test="${label.majorName != null}">
            <c:set var="lblpath" value="/label?id=" />
            <p><b>Major :</b><a href =<c:out value="${pageContext.request.contextPath}${lblpath}${label.major}" /> ><c:out value="${label.majorName}" /></a></p>
        </c:if>
        <p>Info:</p>
        <p><i><c:out value="${label.info}" /></i></p>
        <p>
            <a href = <c:out value= "${pageContext.request.contextPath}/editlabel?id=${label.id}" /> >Edit</a>
        </p>
    </div>
    </div>
</body>
</html>
