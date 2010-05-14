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
        <title>Edit album</title>
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
            function createList(data, name) {
                var array = data.split('\n');
                var result = '<select name = ' + name + ' class = ' + name + '>';
                for (i = 0; i < array.length-1; i++){
                    result += '<option value="'+array[i]+'">'+array[i]+'</option>';
                }
                result += '</select>';
                return result;
            } 
            
            $(document).ready(function(){
                $("#albumForm").validate({
                    rules : {
                        name : {required : true },
                        date : {required : true, minlength: 8},
                        artist : {required : true},
                        label : {required : true},
                        cover : {required : true, url: true},
                        artistname : {required : true},
                        labelname : {required : true}
                    },
                        
                    messages : {
                        username : {
                            required : "Enter name of the album",
                        },
                        date : {
                            required : "Enter Date of release",
                            minlength : "Date format: dd.MM.YY"
                        }, 
                        artist : {
                            required : "Enter artist of the album",
                        }, 
                        label : {
                            required : "Enter label of the album"
                        } 
                    }
                });
                
                $.ajax({
                    type: 'GET',
                    url: 'showartists',
                    success: function(data){
                        $('div.artistfields').append(createList(data, 'artistslist'));
                    },
                    dataType: 'text'
                });
        
                $.ajax({
                    type: 'GET',
                    url: 'showlabels',
                    success: function(data){
                        labellist = createList(data, 'labelslist');
                        $('div.labelfields').append(labellist);
                    },
                    dataType: 'text'
                });
            });
        </script>
        
    </head>
<body>
<%@include file="menu.jsp" %>
<div id = "editalbum_main">
    <div id = "form">
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
            Artist:  <c:out value = "${album.artistName}" />&nbsp;&nbsp;
            <a href = "editartist?id=<c:out value = "${album.artist}" />" target="_blank" >Edit</a>
            <br />
            <div class = "artistfields">
            </div>    
            Label: <c:out value = "${album.labelName}" />&nbsp;&nbsp;
            <a href = "editlabel?id=<c:out value = "${album.label}" />" target="_blank">Edit</a>            
            <br />
            <div class = "labelfields">
            </div>    
            Review: <br />
            <textarea name = "review" cols = "70" rows = "15"><c:out value = "${album.review}" /></textarea>
            <br />
            <input type = "hidden" name = "id" value = "<c:out value = "${album.id}" />" />

            <input id = "button" type = "submit" value = "Send" />
        </form>
    </div>
</div>

</body>
</html>
