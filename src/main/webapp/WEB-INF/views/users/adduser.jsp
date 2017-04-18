<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link href="/resources/css/application.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body id="mainBody">
<div class="container">
    <c:choose>
        <c:when test="${user.id == 0}">
            <h1>Add User</h1>
        </c:when>
        <c:otherwise>
            <h1>Update User</h1>
        </c:otherwise>
    </c:choose>
    <br/>

    <spring:url value="/nst/user/save/" var="userActionUrl"/>
    <form:form class="form-horizontal" method="post" modelAttribute="user" action="${userActionUrl}">
        <form:hidden path="id"/>
        <spring:bind path="firstname">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Firstname</label>
                <div class="col-sm-10">
                    <form:input path="firstname" type="text" class="form-control " id="firstname"
                                placeholder="Firstname"/>
                    <form:errors path="firstname" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="lastname">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Lastname</label>
                <div class="col-sm-10">
                    <form:input path="lastname" type="text" class="form-control " id="firstname"
                                placeholder="Lastname"/>
                    <form:errors path="lastname" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Username</label>
                <div class="col-sm-10">
                    <form:input path="username" type="text" class="form-control " id="username" placeholder="Username"/>
                    <form:errors path="username" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <form:input path="email" class="form-control" id="email" placeholder="Email"/>
                    <form:errors path="email" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <form:input path="password" class="form-control" id="password" placeholder="Password"/>
                    <form:errors path="password" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Confirm Password</label>
                <div class="col-sm-10">
                    <form:input path="confirmPassword" class="form-control" id="password" placeholder="Password"/>
                    <form:errors path="confirmPassword" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="stringRoles">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Roles</label>
                <div class="col-sm-5">
                    <form:select path="stringRoles" items="${roles}" multiple="true" size="5" class="form-control"/>
                    <form:errors path="stringRoles" class="control-label"/>
                </div>
                <div class="col-sm-5"></div>
            </div>
        </spring:bind>

        <spring:bind path="companyBean.pib">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">PIB</label>
                <div class="col-sm-10">
                    <form:input path="companyBean.pib" type="text" class="form-control " id="pib"
                                placeholder="PIB" value="${sessionUser.companyBean.pib}" readonly="true"/>
                    <form:errors path="companyBean.pib" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="companyBean.name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <form:input path="companyBean.name" type="text" class="form-control "
                                id="companyBean.name" value="${sessionUser.companyBean.name}"
                                placeholder="Company name" readonly="true"/>
                    <form:errors path="companyBean.name" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${user.id == 0}">
                        <button type="submit" class="btn-lg btn-primary pull-right">Add user</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>

</div>
</body>
</html>