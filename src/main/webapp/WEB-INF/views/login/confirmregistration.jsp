<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Confirm registration</title>
    <link href="/resources/css/application.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/login.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body id="mainBody">
<form:form id="login-form" action="/nst/register/" method="post">
    <div class="container">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <h3>Please enter a received code</h3>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-8 col-sm-offset-2 ${not empty confirmationCodeError ? 'has-error' : ''}">
                            <input type="text" name="code" id="code"
                                   placeholder="Confirmation code" tabindex="2" class="form-control">
                            <label class="control-label error">${confirmationCodeError}</label>
                        </div>
                        <div class="col-md-8 col-sm-offset-2">
                            <input type="submit" id="register-submit" tabindex="4"
                                   class="form-control btn-primary" value="REGISTER"/>
                        </div>
                    </div>
                </div>
                <hr>
            </div>
        </div>
    </div>
</form:form>
</body>
</html>