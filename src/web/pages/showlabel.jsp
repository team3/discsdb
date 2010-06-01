<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<jsp:useBean id="label" scope="request" class="ua.edu.sumdu.lab3.model.Label" />
<jsp:useBean id="children" scope="request" class="ua.edu.sumdu.lab3.javabeans.CollectionBean"/>
<jsp:useBean id="path" scope="request" class="ua.edu.sumdu.lab3.javabeans.CollectionBean"/>

<html>
    <head>
        <title>ST | <jsp:getProperty name="label" property="name" /></title>
        <c:set var="stylepath" value="/pages/css/style.css" />
        <c:set var="truepath" value="${pageContext.request.contextPath}${stylepath} "/>
        <link rel="stylesheet" href=<c:out value="${truepath}" /> type="text/css" />
        <script src = "pages/js/jquery-latest.js" type="text/javascript"></script>
        <script src = "pages/js/jquery.delegate.js" type="text/javascript"></script>
        <script src = "pages/js/jquery.validate.js" type="text/javascript"></script>
        <script src = "pages/js/jquery.field.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            function getIdParam(qs) {
                qs = qs.split("+").join(" ");
                var param;
                var tokens;

                while (tokens = /[?&]?([^=]+)=([^&]*)/g.exec(qs)) {
                    if(decodeURIComponent(tokens[1]) == "id") {
                        param = decodeURIComponent(tokens[2]);
                    break;
                    }
                }
                return param;
            }
        
            $("document").ready(function(){
                $("#selectlabel").click(function(){
                    window.opener.document.forms[0].selectedlabelname.value = $('.lname').text().trim();
                    window.opener.document.forms[0].lid.value = getIdParam(document.location.search);
                    window.close();
                });
            });
        </script>
    </head>
<body>    
    <div class="allpage">
        <c:if test="${empty param.select}">
            <%@include file="menu.jsp" %>
        </c:if>
        <c:set var="alblpath" value="/label/all" />
        <c:set var="lblpath" value="/label?id=" />
        <p style = "margin-left: 200px; color: black; font-weight: bold">
            <a href = <c:out value= "${pageContext.request.contextPath}" /> >Main</a>&rarr;

            <c:choose>
                <c:when test="${not empty param.select}">
                    <a href = <c:out value="${pageContext.request.contextPath}${alblpath}?select=true" /> ><b>Labels</b></a>&rarr;
                </c:when>
                <c:otherwise>
                    <a href = <c:out value="${pageContext.request.contextPath}${alblpath}" /> ><b>Labels</b></a>&rarr;
                </c:otherwise>
            </c:choose>
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
        <div class="maincont">
            <c:choose>
                <c:when test="${label.name == null}">
                    <h2>Label does not exist. Mayby deleted?</h2>
                </c:when>
                <c:otherwise>
                    <img src=<c:out value="${label.logo}" /> width="220" height="220" align="left" alt="cover"/>
                    <p class = "lname"> 
                        <c:out value="${label.name}" />
                    </p>
                    <c:if test="${label.majorName != null}">
                        <p><b>Major :</b>
                        <a href =<c:out value="${pageContext.request.contextPath}${lblpath}${label.major}" /> ><c:out value="${label.majorName}" /></a></p>
                    </c:if>
                    <!-- Info section -->
                    <p>
                        <b>Info:</b>
                    </p>
                    
                    <p style = "font-size: 12px;">
                        <i><c:out value="${label.info}" /></i>
                    </p>
                    <!-- Childrens labels section -->
                    <c:if test="${children.size != 0}">
                        <p style = "clear: both"><b>Labels:</b></p>
                        <ul>
                        <c:forEach var="child" begin="0" items="${children.collection}">
                            <li>
                                <c:choose>
                                    <c:when test="${not empty param.select}">
                                        <a href =<c:out value="${pageContext.request.contextPath}${lblpath}${child.id}&select=true" /> >
                                            <c:out value="${child.name}" />
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href =<c:out value="${pageContext.request.contextPath}${lblpath}${child.id}" /> >
                                            <c:out value="${child.name}" />
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${empty param.select}">
                        <p>
                            <a href = <c:out value= "${pageContext.request.contextPath}/editlabel?id=${label.id}" /> >Edit</a>
                        </p>
                    </c:if>
                    <c:if test="${empty param.select}">
                        <p>
                            <a href = <c:out value= "${pageContext.request.contextPath}/remove?obj=label&id=${label.id}&mode=self" /> >Remove</a>
                        </p>
                     </c:if>
                    <c:if test="${not empty param.select}">
                        <p>
                            <div id = "selectlabel">
                                Select this label
                            </div>
                        </p>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
