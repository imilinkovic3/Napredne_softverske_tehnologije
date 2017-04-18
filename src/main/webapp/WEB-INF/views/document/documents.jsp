<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link href="/resources/css/application.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/btn-document.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body id="mainBody">
<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${message}</strong>
        </div>
    </c:if>

    <div class="form-group">
        <a class="btn btn-primary" href="/nst/documents/documentForm/-1/">New document</a>
    </div>
    <div class="col-lg-9 col-sm-offset-1">
        <c:forEach items="${documents}" var="document">
            <a class="btn-document" href="/nst/documents/documentForm/${document.id}/">${document.name}</a>
        </c:forEach>
    </div>
</div>
</body>
</html>