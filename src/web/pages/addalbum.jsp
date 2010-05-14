<%@ page import="java.util.*" %>
<%@ page import="ua.edu.sumdu.lab3.model.*" %>
    
<html>
    <head>
        <title>Add album</title>
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
        
                
                $("#listall").change(function(){
                    $('div.artistfields').empty();
                    $("#addnew").attr("checked", false);
                    if ($(this).attr("checked")) {
                        $.ajax({
                            type: 'GET',
                            url: 'showartists',
                            success: function(data){
                                $('div.artistfields').append(createList(data, 'artistslist'));
                            },
                            dataType: 'text'
                        });
                    
                        
                        return;
                    } else {
                        $('div.artistfields').empty();
                    }
                });
            
                $("#lablistall").change(function(){
                    $('div.labelfields').empty();
                    $("#labaddnew").attr("checked", false);
                    if ($(this).attr("checked")) {
                        $.ajax({
                            type: 'GET',
                            url: 'showlabels',
                            success: function(data){
                                labellist = createList(data, 'labelslist');
                                $('div.labelfields').append(labellist);
                            },
                            dataType: 'text'
                        });
                        return;
                    } else {
                        $('div.labelfields').empty();
                    }
                });
            
                $("#addnew").change(function(){
                    $("#listall").attr("checked", false);
                    if ($(this).attr("checked")) {
                        $('div.artistfields').empty();
                        window.open ("pages/addartist.jsp",
                        "mywindow",
                        "menubar=1, resizable=1, width=800, height=600"); 
                        $(this).attr("checked", false);
                        return;
                    } else {
                        $('div.artistfields').empty();
                    }
                });
                
                $("#labaddnew").change(function(){
                    $("#lablistall").attr("checked", false);
                    if ($(this).attr("checked")) {
                        window.open ("pages/addlabel.jsp",
                        "mywindow",
                        "menubar=1, resizable=1, width=800, height=600"); 
                        $("this").attr("checked", false);
                        return;
                    } else {
                        $('div.labelfields').empty();
                    }
                });
            });
        </script>
    </head>
<body>

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
            <div class = "artistfields">
            </div>    
            Label: <br />
            <input type = "checkbox" id = "labaddnew" />New 
            <input type = "checkbox" id = "lablistall" />&nbsp;Select
            
            <br />
            <div class = "labelfields">
            </div>    
            Review: <br />
            <textarea name = "review" cols = "70" rows = "15"></textarea>
            <br />
            <input id = "button" type = "submit" value = "Send" />
        </form>
    </div>
</div>

</body>
</html>
