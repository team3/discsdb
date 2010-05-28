<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
 
<c:if test="${label.name == null}">
    <jsp:forward page = "../index" />
</c:if>
     
<html>
    <head>
        <title>Edit Label</title>
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
        <script src = "pages/js/scripts.js" type="text/javascript"></script>
    </head>
<body>

<div id = "editlabel_main">
    <h1>Try to edit label: <font color = "red"><c:out value="${label.name}" /> </font></h1>
    <form action = "editlabel" name = "edit_label_form" class = "edit_label_form" method = "POST">
        Name: <br />
        <input type = "text" class = "labelname" name= "labelname" value = "<c:out value="${label.name}"  />" />
        <br />
        Info: <br />
        <textarea rows = "15" cols = "60" class = "labelinfo" name = "labelinfo"><c:out value="${label.info}" /></textarea>
        <br />
        Logo: <br />
        <input type = "text" class = "labellogo" name = "labellogo" value = "<c:out value="${label.logo}" />" />
        <br />
        Major: <br />
        <input 
            type = "text" 
            name = "selectedlabelname" 
            class = "selectedlabelname" 
            value = "<c:out value="${label.majorName}" />"
            readonly = "readonly"
        />
        <br />
        <input type = "checkbox" id = "lablistall" />&nbsp;Select
        <br />

        <input type = "hidden" name = "lid" value = <c:out value="${label.id}" /> />
        <input type = "hidden" name = "majorid" value = <c:out value="${label.major}" /> />
        <br />
        <input type = "submit" value = "ok" />
    </form>
</div>

</body>
</html>
