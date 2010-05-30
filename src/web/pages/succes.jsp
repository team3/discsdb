<%@taglib uri="/WEB-INF/tags/c.tld" prefix="c" %>

<html>
<head>
<title><c:out value="${param.obj}"/> added</title>
    <script src = "js/jquery-latest.js" type="text/javascript"></script>
    <script src = "js/jquery.delegate.js" type="text/javascript"></script>
    <script src = "js/jquery.validate.js" type="text/javascript"></script>
    <script src = "js/jquery.field.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $("document").ready(function add (){
            window.opener.document.forms[0].selected<c:out value="${param.obj}" />name.value = '<c:out value="${param.name}" />';
            <c:if test = "${not empty param.lid}">
            window.opener.document.forms[0].lid.value = <c:out value="${param.lid}" />;
            </c:if>
            <c:if test = "${not empty param.aid}">
            window.opener.document.forms[0].aid.value = <c:out value="${param.aid}" />;
            </c:if>
            window.close();
        });
    </script>
</head>
<body>
<p><c:out value="${param.obj}"/> succesfully added.</p>
</body>
</html>
