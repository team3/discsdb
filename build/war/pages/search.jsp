<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
 
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>


<html>
    <head>
        <title>ST | Search</title>
        <link rel="stylesheet" href="pages/css/style.css" type="text/css" />
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery-latest.js type="text/javascript"></script>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery.delegate.js type="text/javascript"></script>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery.validate.js type="text/javascript"></script>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery.field.min.js type="text/javascript"></script>
        
        <script type="text/javascript">    
            $("document").ready(function(){
                $("#searchForm").validate({
                    rules : {
                        search : {required : true }
                    }
                });
            });
        </script>
    </head>
<body>
<%@include file="menu.jsp" %>
<div id = "search_main">
    <div id = "form">
    <h1>Search</h1>
        <form action = "search" method = "get" id = "searchForm">
            <br />
            <input type = "text" name = "search"><br />
            By : 
            <br />
            <select name = "by">
                <option value = "artist">artist</option>
                <option value = "genre" >genre</option>
                <option value = "name" >name</option>
                <option value = "date" >date</option>
                <option value = "label" >label</option>
            </select>
            
            <br />
            <br />
            <input id = "button" type = "submit" value = "Send" />
        </form>
    </div>
</div>
<c:set var = "team3" value = "/pages/images/team3_logo.png" />
<div class = "footer">
    <span><img border = "0" src = <c:out value="${pageContext.request.contextPath}${team3}" /> alt= "logo" /></span>
</div>
</body>
</html>

