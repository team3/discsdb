<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
  
<html>
    <head>
        <title>Add Artist</title>
        <link rel="stylesheet" href= <c:out value= "${pageContext.request.contextPath}" />/pages/css/style.css type="text/css" />
        <style>
            label.error {
                color: red;
                font-style: italic;
            }
            input.error {
                border: 1px dotted #f00;
            }
        </style>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery-latest.js type="text/javascript"></script>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery.delegate.js type="text/javascript"></script>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery.validate.js type="text/javascript"></script>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery.field.min.js type="text/javascript"></script>
        
        <script type="text/javascript">    
            $("document").ready(function(){
                $("artist_edit_form").validate({
                    rules : {
                        artistname : {required : true },
                        artistcountry : {required : true },
                        artistinfo : {required : true }
                    }
                });
            });
                        
        </script>
        
    </head>
<body>

<div id = "editartist_main">
    <h1>Try to add Artist</h1>
    
    <form name = "artist_edit_form" method = "POST" action = "addartist">
        Name: <br />
        <input type = "text" class = "artistname" name = "artistname" />
        <br />
        Country: <br />
        <input type = "text" class = "artistcountry" name = "artistcountry" />
        <br />
        Info: <br />
        <textarea name = "artistinfo" cols = "15" row = "70"></textarea>
        <br />
       
        <input type = "submit" value = "Add" />
    </form>
</div>

</body>
</html>
