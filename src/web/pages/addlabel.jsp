<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
  
<html>
    <head>
        <title>Add Label</title>
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
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/scripts.js type="text/javascript"></script>
        
    </head>
<body>

<div class="allpage">
    <c:if test="${param.opener != true}">
        <%@include file="menu.jsp" %>
    </c:if>
    <div id = "addlabel_main">
        <div id = "form">
            <h1>Add label</h1>
            <form  name = "edit_label_form" method = "POST" action = "addlabel" class = "edit_label_form">
                Name: <br />
                <input type = "text" class = "labelname" name= "labelname" />
                <br />
                Info: <br />
                <textarea rows = "15" cols = "60" class = "labelinfo" name = "labelinfo"></textarea>
                <br />
                Logo: <br />
                <input type = "text" class = "labellogo" name = "labellogo" />
                <br />
                Major: <br />
                <input type = "checkbox" id = "lablistall" />&nbsp;Select
                <br />
                <input 
                    type = "text" 
                    name = "selectedlabelname" 
                    class = "selectedlabelname" 
                    readonly = "readonly"
                />
                <br />
                <input type = "hidden" value = "-1" name = "lid" />
                <c:if test = "${param.opener == true }">
                    <input type="hidden" name = "opener" value = "true"/>
                </c:if>
                <br />
                <input type = "submit" value = "Add" />
            </form>
        </div>
    </div>
</div>
</body>
</html>
