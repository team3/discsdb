<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
<html>
    <head>
        <title>ST | About</title>
        <link rel="stylesheet" href= <c:out value= "${pageContext.request.contextPath}" />/pages/css/style.css type="text/css" />
    </head>
    <body>
        <div class="allpage">
            <%@include file="menu.jsp" %>
            <c:set var = "picpath" value = "/pages/images/elvis.png" />
            <div class="maincont">
                <img border = "0" src = <c:out value="${pageContext.request.contextPath}${picpath}" /> align = "right" alt= "elvis" />
                <h1>About</h1>
                <p>Welcome to the <b>sweet tunes</b>. Its probably the best database
                 where you can store information about your favs artists, albums and labels.
                 So enjoy!</p>
            </div>
        </div>
        <c:set var = "team3" value = "/pages/images/team3_logo.png" />
        <div class = "footer">
            <span><img border = "0" src = <c:out value="${pageContext.request.contextPath}${team3}" /> alt= "logo" /></span>
        </div>
    </body>
</html>
