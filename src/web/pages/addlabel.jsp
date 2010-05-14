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
            
            function addLabel() {
                var name = $('.labelname').getValue();
                var info = $('.labelinfo').getValue();
                var logo = $('.labellogo').getValue();
                var major = $(".labelslist").getValue();
                 
                var url = '/discs/addlabel?name=' + name + 
                        '&info=' + info + 
                        '&logo=' + logo +
                        '&major=' + major;
                $.ajax({
                    type: 'POST',
                    url: url,
                    success: function(data){
                        if (data == '1') {
                            $("div#editlabel_main").
                                    append('<div class = acceptmessage>Label was added</div>');
                        } else {
                            $("div#editlabel_main").
                                    append('<div class = denymessage>Label was not added</div>');
                        }   
                    },
                dataType: 'text'
                });
            }
            
        </script>
        
    </head>
<body>

<div id = "editlabel_main">
    <h1>Try to add new label.</h1>
    <form  name = "edit_label_form" method = "POST">
        Name: <br />
        <input type = "text" class = "labelname" name= "labelname" />
        <br />
        Info: <br />
        <textarea rows = "15" cols = "60" class = "labelinfo" name = "labelinfo"></textarea>
        <br />
        Logo: <br />
        <input type = "text" class = "labellogo" name = "labellogo" />
        <br />
        <div class = "labelfields">
        Major: 
        </div>
        <input type = "button" onclick = "addLabel()" value = "Add" />
    </form>
</div>

</body>
</html>
