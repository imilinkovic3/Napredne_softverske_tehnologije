<%@ taglib prefix="template" tagdir="/WEB-INF/tags/javascript" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                        <h3>Forgot password</h3>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" name="login-form" method="post"
                                  action="/nst/forgotpassword/">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <input type="text" name="emailOrUsername"
                                           placeholder="Email or username"
                                           id="emailOrUsername" class="form-control" value=""/>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="submit" id="login-submit" tabindex="4"
                                                   class="form-control btn-primary" value="Submit"/>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${not empty message}">
                                    <div class="alert alert-${css} alert-dismissible" role="alert">
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                        <strong>${message}</strong>
                                    </div>
                                </c:if>
                            </form>
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