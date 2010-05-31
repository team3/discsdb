<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
 
<c:if test="${artist.name == null}">
    <jsp:forward page = "../index" />
</c:if>
     
<html>
    <head>
        <title>ST | Edit Artist</title>
        <link rel="stylesheet" href="pages/css/style.css" type="text/css" />
        <style>
            label.error {
                color: red;
                font-style: italic;
            }
            input.error {
                border: 1px dotted #f00;
            }
        </style>
        <script src = "pages/js/jquery-latest.js" type="text/javascript"></script>
        <script src = "pages/js/jquery.delegate.js" type="text/javascript"></script>
        <script src = "pages/js/jquery.validate.js" type="text/javascript"></script>
        <script src = "pages/js/jquery.field.min.js" type="text/javascript"></script>
        <script type="text/javascript">    
            $("document").ready(function(){
                $("#artist_edit_form").validate({
                    rules : {
                        artistname : {required : true },
                        artistcountry : {required : true },
                    }
                });
            });
        </script>
    </head>
<body>
<div class="allpage">
    <c:if test = "${param.refer != true}">
        <%@include file="menu.jsp" %>
    </c:if>
    <div id = "editartist_main">
        <div id="form">
            <h1>Edit artist</h1>
            <c:choose>
                <c:when test = "${param.refer == true}">
                    <form action = "editartist" name = "artist_edit_form" method = "POST" id = "artist_edit_form" onsubmit="top.close();">
                </c:when>
                <c:otherwise>
                    <form action = "editartist" name = "artist_edit_form" method = "POST" id = "artist_edit_form" >
                </c:otherwise>
            </c:choose>
                Name: <br />
                <input type = "text" class = "artistname" name = "artistname" 
                    value = "<c:out value="${artist.name}" />" />
                <br />
                Country: <br />
                <input type = "text" class = "artistcountry" name = "artistcountry" value = "<c:out value="${artist.country}" />" />
                <br />
                Info: <br />
                <textarea name = "artistinfo" cols = "15" row = "70"><c:out value="${artist.info}" /></textarea>
        
                <input type = "hidden" name = "artistid" value = <c:out value="${artist.id}" /> />
                <br />
                <input type = "submit" value = "Ok" />
            </form>
        </div>
    </div>
</div>

</body>
</html>
