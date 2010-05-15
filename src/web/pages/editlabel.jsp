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
        
        <script type="text/javascript">    
            $(document).ready(function(){
                $.ajax({
                    type: 'GET',
                    url: '/discs/showlabels',
                    success: function(data){
                        labellist = createList(data, 'labelslist');
                        $('div.labelfields').append(labellist);
                    },
                    dataType: 'text'
                });
            });
            
            
            function createList(data, name) {
                var array = data.split('\n');
                var result = '<select name = ' + name + ' class = ' + name + '>';
                for (i = 0; i < array.length-1; i++){
                    result += '<option value="'+array[i]+'">'+array[i]+'</option>';
                }
                result += '<option value = "none"> none </option>';
                result += '</select>';
                return result;
            }
            
        </script>
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
        <br />
         <div class = "labelfields">
        Major: 
        </div>
        <input type = "hidden" name = "labelid" value = <c:out value="${label.id}" /> />
        <input type = "hidden" name = "majorid" value = <c:out value="${label.major}" /> />
        <br />
        <input type = "submit" value = "ok" />
    </form>
</div>

</body>
</html>
