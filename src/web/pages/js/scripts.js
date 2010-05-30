$(document).ready(function(){
    
    $("#albumForm").validate({
        rules : {
            name : {required : true },
            date : {required : true, minlength: 8},
            artist : {required : true},
            label : {required : true},
            cover : {required : true, url: true},
            artistname : {required : true},
            labelname : {required : true},
            selectedlabelname : {required : true},
            selectedartistname : {required : true}
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
        
    $("#addnew").change(function(){
        $("#listall").attr("checked", false);
        if ($(this).attr("checked")) {
            $('div.artistfields').empty();
            window.open ("addartist?opener=true",
            "addArtistWindow",
            "menubar=1, resizable=1, width=510, height=540"); 
            $(this).attr("checked", false);
            return;
        } else {
            $('div.artistfields').empty();
        }
    });
                
    $("#labaddnew").change(function(){
        $("#lablistall").attr("checked", false);
        if ($(this).attr("checked")) {
            
            window.open ("addlabel?opener=true",
            "addLabelWindow",
            "menubar=1, resizable=1, width=510, height=650"); 
            $("this").attr("checked", false);
            return;
        } else {
            $('div.labelfields').empty();
        }
    });
    
    $("#listall").change(function(){
        $("this").attr("checked", false);
        window.open ("artist/all?select=true",
            "mywindow",
            "menubar=1, resizable=1, width=640, height=600"); 
    });
    
    $("#lablistall").change(function(){
        $("this").attr("checked", false);
        window.open ("label/all?select=true",
            "mywindow",
            "menubar=1, resizable=1, width=640, height=600"); 
    });
});
    
    
   
    


