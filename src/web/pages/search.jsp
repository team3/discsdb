<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
 
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>


<html>
    <head>
        <title>Search albums</title>
        <link rel="stylesheet" href="pages/css/style.css" type="text/css" />        
    </head>
<body>

<div id = "search_main">
    <div id = "form">
        <form action = "search" method = "get" id = "albumForm">
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
            <input id = "button" type = "submit" value = "Send" />
        </form>
    </div>
</div>

</body>
</html>

