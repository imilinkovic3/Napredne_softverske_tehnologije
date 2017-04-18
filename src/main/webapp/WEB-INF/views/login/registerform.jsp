<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/nst/register/confirmregistration/" var="registerUrl"/>
<form:form modelAttribute="user" id="register-form" name="register-form" method="POST" action="${registerUrl}">
    <spring:bind path="firstname">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="firstname" type="text" name="firstname"
                        placeholder="Firstname" id="firstname" tabindex="1"
                        class="form-control" value=""/>
            <form:errors path="firstname" class="control-label"/>
        </div>
    </spring:bind>

    <spring:bind path="lastname">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="lastname" type="text" name="lastname"
                        placeholder="Lastname" id="lastname" tabindex="1"
                        class="form-control" value=""/>
            <form:errors path="lastname" class="control-label"/>
        </div>
    </spring:bind>

    <spring:bind path="email">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="email" type="text" name="email"
                        placeholder="Email" id="email" tabindex="1" class="form-control"/>
            <form:errors path="email" class="control-label"/>
        </div>
    </spring:bind>

    <spring:bind path="username">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="username" type="text" name="username"
                        placeholder="Username" id="username" tabindex="1"
                        class="form-control"/>
            <form:errors path="username" class="control-label"/>
        </div>
    </spring:bind>

    <spring:bind path="password">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="password" type="password" name="password" placeholder="Password"
                        id="password" tabindex="2" class="form-control"/>
            <form:errors path="password" class="control-label"/>
        </div>
    </spring:bind>

    <spring:bind path="confirmPassword">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="confirmPassword" type="password" name="confirmPassword" placeholder="Confirm password"
                        id="confirmPassword" tabindex="2" class="form-control"/>
            <form:errors path="confirmPassword" class="control-label"/>
        </div>
    </spring:bind>

    <jsp:include page="companyform.jsp"/>


    <div class="g-recaptcha" data-sitekey="6LcbJhcUAAAAAFAnF8fhnaCXJkLOrZiZ6Vp0U6lS"></div>
    <label class="control-label error">${captchaError}</label>

    <div class="form-group">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
                <button path="register-submit" type="submit"
                        id="register-submit" tabindex="4"
                        class="form-control btn btn-register">REGISTER
                </button>
            </div>
        </div>
    </div>
</form:form>