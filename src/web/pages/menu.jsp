<div class = "header">
    <c:set var = "picpath" value = "/pages/images/logo.png" />
    <c:set var = "albumspath" value = "/album/all" />
    <c:set var ="artistspath" value = "/artist/all" />
    <c:set var = "labelspath" value = "/label/all" />
    <c:set var = "datestspath" value = "/date/all" />
    <c:set var = "addpath" value = "/addalbum" />
    <c:set var = "searchpath" value = "/search" />
    <c:set var = "aboutpath" value = "/about" />
    <c:set var = "artpath" value = "/artist?id=" />
    <c:set var = "lblpath" value = "/label?id=" />
    <c:set var = "albpath" value = "/album?id=" />
    <c:set var = "genreparam" value = "?genre=" />
    <c:set var = "pagepath" value = "/album/all" />
    <c:set var = "edit_album" value = "/editalbum?id=" />
    <c:set var = "edit_label" value = "/editlabel?id=" />
    <c:set var = "edit_artist" value = "/editartist?id=" />

    
    <div  class="logopic">
        <a 
            href = "<c:out value= "${pageContext.request.contextPath}" />"> 
            <img border = "0" src = <c:out value="${pageContext.request.contextPath}${picpath}" /> alt= "logo" />
        </a>
    </div>
    <div class = "menu"><a href=<c:out value= "${pageContext.request.contextPath}${aboutpath}" />>ABOUT</a></div>
    <div class = "menu"><a href=<c:out value= "${pageContext.request.contextPath}${albumspath}" />>ALBUMS</a></div> 
    <div class = "menu"><a href=<c:out value= "${pageContext.request.contextPath}${labelspath}" />>LABELS</a></div> 
    <div class = "menu"><a href=<c:out value= "${pageContext.request.contextPath}${artistspath}" />>ARTISTS</a></div> 
    <div class = "menu"><a href=<c:out value= "${pageContext.request.contextPath}${datestspath}" />>DATES</a></div>
    <div class = "menu"><a href=<c:out value= "${pageContext.request.contextPath}${searchpath}" />>SEARCH</a></div>
    <div class = "menu">
    <select onchange = "location.href=this.options[this.selectedIndex].value">
    <option selected value="">ADD</option>
    <option value=<c:out value= "${pageContext.request.contextPath}/addalbum" />>album</option>
    <option value=<c:out value= "${pageContext.request.contextPath}/addartist" />>artist</option>
    <option value=<c:out value= "${pageContext.request.contextPath}/addlabel" />>label</option>
    </select>
    </div>
</div>
