<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/nst/login/" var="loginUrl"/>
<form:form modelAttribute="user" id="login-form" name="login-form" method="post" action="${loginUrl}">
    <spring:bind path="username">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="username" type="text" name="username" placeholder="Email or username"
                        id="username" tabindex="1" class="form-control" value=""/>
            <form:errors path="username" class="control-label"/>
        </div>
    </spring:bind>

    <spring:bind path="password">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="password" type="password" name="password" id="password"
                        placeholder="Password" tabindex="2" class="form-control"/>
            <form:errors path="password" class="control-label"/>
        </div>
    </spring:bind>

    <div class="form-group text-center">
        <input type="checkbox" tabindex="3" class="" name="remember"
               id="remember"> <label for="remember"> Remember Me</label>
    </div>

    <div class="form-group">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
                <input type="submit" id="login-submit" tabindex="4"
                       class="form-control btn btn-login" value="LOGIN"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="row">
            <div class="col-lg-12">
                <div class="text-center">
                    <a tabindex="5" href="/nst/forgotpassword/get/" id="forgot-password-link"
                       class="forgot-password">Forgot Password?</a>
                </div>
            </div>
        </div>
    </div>
</form:form>