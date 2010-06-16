<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
 
<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
 
<c:if test="${album.name == null}">
    <c:redirect url="../index" />
</c:if>

<%
    java.util.Date release = ((Album)request.getAttribute("album")).getRelease();
    java.text.DateFormat fm = new java.text.SimpleDateFormat("dd.MM.yy");
    
    pageContext.setAttribute("release", fm.format(release));
%>
<html>
    <head>
        <title>ST | Edit album</title>
        <link rel="stylesheet" href="pages/css/style.css" type="text/css" />
        <style>
            label.error {
                color: red;
                font-style: italic;
            }
            input.error {
                border: 1px dotted red;
            }
        </style>
        <script src = "pages/js/jquery-latest.js" type="text/javascript"></script>
        <script src = "pages/js/jquery.delegate.js" type="text/javascript"></script>
        <script src = "pages/js/jquery.validate.js" type="text/javascript"></script>
        <script src = "pages/js/jquery.field.min.js" type="text/javascript"></script>
        <script src = "pages/js/scripts.js" type="text/javascript"></script>
        <script type="text/javascript">
            function openWindow(path){
                window.open (path,
                "Edit",
                "menubar=1, resizable=1, width=510, height=650");
            }
        </script>
    </head>
<body>
<div class = "allpage">
    <%@include file="menu.jsp" %>
    <div id = "editalbum_main">
        <div id = "form">
            <h1>Edit album</h1>
            <form action = "editalbum" method = "post" id = "albumForm">
                Name: <br />
                <input type = "text" name = "name" value = "<c:out value = "${album.name}" />"  />
                <br />
                Type: <c:out value = "${album.type}" />
                <br />
                <select name = "type">  
                    <option value="cd">CD</option>  
                    <option value="dvd">DVD</option>  
                    <option value="vinyl">Vinyl</option>  
                </select>
                <br />
                Release: <br />
           
                <input type = "text" name = "date" 
                        value = "<c:out value = "${release}" />" />
                <br />
                Genre: <br />
                <select name = "genre">  
                    <option value="rock">Rock</option>  
                    <option value="pop">Pop</option>  
                    <option value="metal">Metal</option>  
                    <option value="gelectronic">Electronic</option>  
                </select>
                <br/>
                Cover: <br />
                <input type = "text" name = "cover" value = "<c:out value = "${album.cover}" />" />
                <br />
            
                Artist:  
                <br />
                <input type = "checkbox" id = "addnew" />New 
                <input type = "checkbox" id = "listall" />&nbsp;Select
                <br />
                <input 
                    type = "text" 
                    value = "<c:out value = "${album.artistName}" />" 
                    name = "selectedartistname" 
                    class = "selectedartistname" />
                <br />    
                <p class = "edit_link">
                    <c:if test = "${album.artistName != 'unknown'}">
                        <a href="notfound" onClick="openWindow('editartist?id=<c:out value = "${album.artist}"/>&refer=true&opener=true'); return false">
                            Edit this artist
                        </a>
                    </c:if>
                </p>
                Label: 
                <br />
                <input type = "checkbox" id = "labaddnew" />New 
                <input type = "checkbox" id = "lablistall" />&nbsp;Select
                <br />
                <input 
                    type = "text" 
                    name = "selectedlabelname" 
                    class = "selectedlabelname" 
                    value = "<c:out value = "${album.labelName}" />"
                />
                <br />
                <p class = "edit_link">
                    <c:if test = "${album.labelName != 'unknown'}">
                        <a href="editlabel" 
                            onClick="openWindow('editlabel?id=<c:out value = "${album.label}"/>&refer=true&opener=true'); return false">
                            Edit this label
                        </a>   
                    </c:if>
                </p>
                Review: <br />
                <textarea name = "review" cols = "70" rows = "15"><c:out value = "${album.review}" /></textarea>
                <br />
                <input type = "hidden" name = "id" value = "<c:out value = "${album.id}" />" />
                <input type = "hidden" name = "aid" value = "<c:out value = "${album.artist}" />" />
                <input type = "hidden" name = "lid" value = "<c:out value = "${album.label}" />" />
                <input id = "button" type = "submit" value = "Send" />
            </form>
        </div>
    </div>
</div>
<c:set var = "team3" value = "/pages/images/team3_logo.png" />
<div class = "footer">
    <span><img border = "0" src = <c:out value="${pageContext.request.contextPath}${team3}" /> alt= "logo" /><span>
</div>  

</body>
</html>
