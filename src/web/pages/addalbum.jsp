<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
    
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
    
<html>
    <head>
        <title>Add album</title>
        <link rel="stylesheet" href="pages/css/style.css" type="text/css" />
        <link 
            type="text/css" 
            href="pages/js/jquery-ui-1.8.1.custom/css/ui-lightness/jquery-ui-1.8.1.custom.css" 
            rel="stylesheet" 
        />

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
        
        <script src = "pages/js/scripts.js" type="text/javascript"></script>
    </head>
<body>

<%@include file="menu.jsp" %>
<div id = "addalbum_main">

    <div id = "form">
        <form action = "addalbum" method = "post" id = "albumForm">
            Name: <br />
            <input type = "text" name = "name" /><br />
            Type: <br />
            <select name = "type">  
              <option value="cd">CD</option>  
              <option value="dvd">DVD</option>  
              <option value="vinyl">Vinyl</option>  
            </select>
            <br />
            Release: <br />
            <input type = "text" name = "date" value = "dd.MM.YY" /><br />
            Genre: <br />
            <select name = "genre">  
              <option value="rock">Rock</option>  
              <option value="pop">Pop</option>  
              <option value="metal">Metal</option>  
              <option value="gelectronic">Electronic</option>  
            </select>
            <br/>
            Cover: <br />
            <input type = "text" name = "cover"><br />
            Artist: <br />
            <input type = "checkbox" id = "addnew" />New 
            <input type = "checkbox" id = "listall" />&nbsp;Select
            <br />
            <input type = "text" readonly = "readonly" name = "selectedartistname" class = "selectedartistname" />
            <br />
            Label: <br />
            <input type = "checkbox" id = "labaddnew" />New 
            <input type = "checkbox" id = "lablistall" />&nbsp;Select
            <br />
            <input type = "text" readonly = "readonly" name = "selectedlabelname" class = "selectedlabelname" />
            <br />
            Review: <br />
            <textarea name = "review" cols = "70" rows = "15"></textarea>
            <br />
            <input type = "hidden" name = "aid" value = "<c:out value = "${album.artist}" />" />
            <input type = "hidden" name = "lid" value = "<c:out value = "${album.label}" />" />
            <input id = "button" type = "submit" value = "Send" />
        </form>
    </div>
</div>

</body>
</html>
