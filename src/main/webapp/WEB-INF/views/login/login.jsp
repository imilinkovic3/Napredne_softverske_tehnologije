<%@ taglib prefix="template" tagdir="/WEB-INF/tags/javascript" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link href="/resources/css/login.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/application.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body id="mainBody">
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="login">
                                <a href="#" class="active" id="login-form-link">Login</a>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="register">
                                <a href="/nst/register/registerform/" id="register-form-link">Register</a>
                            </div>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <c:if test="${not empty message}">
                                <div class="alert alert-${css} alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    <strong>${message}</strong>
                                </div>
                            </c:if>

                            <jsp:include page="loginform.jsp"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<template:javascript/>