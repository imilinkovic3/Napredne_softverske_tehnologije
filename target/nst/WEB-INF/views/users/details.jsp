<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link href="/resources/css/application.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body id="mainBody">
<div class="container">
    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>${user.firstname} ${user.lastname}</h1>
    <br/>

    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${user.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Username</label>
        <div class="col-sm-10">${user.username}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Firstname</label>
        <div class="col-sm-10">${user.firstname}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Lastname</label>
        <div class="col-sm-10">${user.lastname}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Email</label>
        <div class="col-sm-10">${user.email}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">PIB</label>
        <div class="col-sm-10">${user.companyBean.pib}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Name</label>
        <div class="col-sm-10">${user.companyBean.name}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Country</label>
        <div class="col-sm-10">${user.companyBean.country}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">City</label>
        <div class="col-sm-10">${user.companyBean.city}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Address</label>
        <div class="col-sm-10">${user.companyBean.address}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Phone</label>
        <div class="col-sm-10">${user.companyBean.phone}</div>
    </div>

</div>
</body>
</html>