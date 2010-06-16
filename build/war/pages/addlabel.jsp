<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>

<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>
  
<html>
    <head>
        <title>ST | Add Label</title>
        <link rel="stylesheet" href= <c:out value= "${pageContext.request.contextPath}" />/pages/css/style.css type="text/css" />
        <style>
            label.error {
                color: red;
            }
            input.error {
                border: 1px dotted red;
            }
        </style>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery-latest.js type="text/javascript"></script>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery.delegate.js type="text/javascript"></script>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery.validate.js type="text/javascript"></script>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/jquery.field.min.js type="text/javascript"></script>
        <script src = <c:out value= "${pageContext.request.contextPath}" />/pages/js/scripts.js type="text/javascript"></script>
        
        <script type="text/javascript">    
            $("document").ready(function(){
                $("#edit_label_form").validate({
                    rules : {
                        labelname : {required : true },
                        labellogo : {required : true, url: true },
                    }
                });
            });

        </script>
        
    </head>
<body>

<div class="allpage">
    <c:if test="${param.opener != true}">
        <%@include file="menu.jsp" %>
    </c:if>
    <div id = "addlabel_main">
        <div id = "form">
            <h1>Add label</h1>
            <c:choose>
                <c:when test = "${param.refer == true}">
                    <form  name = "edit_label_form" method = "POST" action = "addlabel" id = "edit_label_form" onsubmit="top.close();>
                </c:when>
                <c:otherwise>
                    <form  name = "edit_label_form" method = "POST" action = "addlabel" id = "edit_label_form">
                </c:otherwise>
            </c:choose>
                Name: <br />
                <input type = "text" class = "labelname" name= "labelname" />
                <br />
                Info: <br />
                <textarea rows = "15" cols = "60" class = "labelinfo" name = "labelinfo"></textarea>
                <br />
                Logo: <br />
                <input type = "text" class = "labellogo" name = "labellogo" value="http://" />
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
<c:if test="${param.opener != true}">
    <c:set var = "team3" value = "/pages/images/team3_logo.png" />
    <div class = "footer">
        <span><img border = "0" src = <c:out value="${pageContext.request.contextPath}${team3}" /> alt= "logo" /></span>
    </div>
</c:if>
</body>
</html>
