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
        <script src = "<c:out value="${pageContext.request.contextPath}" />/pages/js/jquery-latest.js" type="text/javascript"></script>
        <script src = "<c:out value="${pageContext.request.contextPath}" />/pages/js/jquery.delegate.js" type="text/javascript"></script>
        <script src = "<c:out value="${pageContext.request.contextPath}" />/pages/js/jquery.validate.js" type="text/javascript"></script>
        <script src = "<c:out value="${pageContext.request.contextPath}" />/pages/js/jquery.field.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            function add (data, id){
                window.opener.document.forms[0].selectedlabelname.value = data.trim();
                window.opener.document.forms[0].lid.value = id;
                window.close();
            }

        </script>
    </head>
<body>    
    <div class="allpage">
        <c:if test="${empty param.select}">
            <%@include file="menu.jsp" %>
        </c:if>
        <div class="maincont">
            
            <table>
            <tr>
                <td>Logo</td>
                <td>Label name</td>
                <c:choose>
                    <c:when test="${empty param.select}">
                        <td></td>
                        <td></td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            Select
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
            <c:set var="truepath" value="${pageContext.request.contextPath}${removepath}"/>
            <c:forEach var="lbl" begin="0" items="${labels}">
                <tr>
                    <td>
                        <img src=<c:out value="${lbl.logo}" /> width="50" height="50" align="left" alt="cover"/>
                    </td>
                    
                    <td>
                        <c:choose>
                        <c:when test="${not empty param.select}">
                            <a class = "lname" href=<c:out value="${pageContext.request.contextPath}/label?id=${lbl.id}&select=true" /> ><c:out value="${lbl.name}" /></a>
                        </c:when>
                        <c:otherwise>
                            <a class = "lname" href=<c:out value="${pageContext.request.contextPath}/label?id=${lbl.id}" /> ><c:out value="${lbl.name}" /></a>
                        </c:otherwise>
                        </c:choose>
                    </td>
                    
                    <c:choose>
                        <c:when test="${empty param.select}">
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
                        </c:when>
                        <c:otherwise>
                        <td>
                            <input 
                                type = "button" 
                                name = "selectthis" 
                                id = "selectthis"
                                value = "select"
                                onclick = "add('<c:out value="${lbl.name}" />', <c:out value="${lbl.id}" />)"
                        </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </table>
        </div>
    </div>
    <c:set var="pagepath" value="/label/all?page=" />
    <c:set var="select" value="&select=true"/>
    <c:set var="truepath" value="${pageContext.request.contextPath}${pagepath}"/>
    <p>
        <c:if test="${number > 1}">
            <c:forEach var = "i" begin = "1" end="${number}" >
                <c:choose>
                    <c:when test="${i == param.page || (i == 1 && param.page == null)}">
                        <c:out value="${i}" />
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test = "${param.select == true}">
                                <a href =<c:out value="${truepath}" /><c:out value="${i}" /><c:out value="${select}" /> ><c:out value="${i}" /></a> &nbsp;
                            </c:when>
                            <c:otherwise>
                                <a href =<c:out value="${truepath}" /><c:out value="${i}" /> ><c:out value="${i}" /></a> &nbsp;
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:if>
    </p>
</body>
</html>
