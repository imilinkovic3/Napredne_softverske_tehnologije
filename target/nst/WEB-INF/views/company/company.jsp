<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="/resources/css/application.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body id="mainBody">
<div class="container">
    <spring:url value="/nst/company/update/" var="companyActionUrl"/>
    <form:form class="form-horizontal" method="post" modelAttribute="company" action="${companyActionUrl}">
        <spring:bind path="pib">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">PIB</label>
                <div class="col-sm-10">
                    <form:input path="pib" type="text" class="form-control" id="pib" placeholder="PIB" readonly="true"/>
                    <form:errors path="pib" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control" id="name" placeholder="Company name"/>
                    <form:errors path="name" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="country">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Country</label>
                <div class="col-sm-10">
                    <form:input path="country" type="text" class="form-control" id="country" placeholder="Country"/>
                    <form:errors path="country" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="city">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">City</label>
                <div class="col-sm-10">
                    <form:input path="city" type="text" class="form-control" id="city" placeholder="City"/>
                    <form:errors path="city" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="postalcode">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Postal code</label>
                <div class="col-sm-10">
                    <form:input path="postalcode" type="text" class="form-control" id="postalcode"
                                placeholder="Postal code"/>
                    <form:errors path="postalcode" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="address">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Address</label>
                <div class="col-sm-10">
                    <form:input path="address" type="text" class="form-control" id="address" placeholder="Address"/>
                    <form:errors path="address" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="fax">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">FAX</label>
                <div class="col-sm-10">
                    <form:input path="fax" type="text" class="form-control" id="fax" placeholder="Fax"/>
                    <form:errors path="fax" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="phone">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Phone</label>
                <div class="col-sm-10">
                    <form:input path="phone" type="text" class="form-control" id="phone" placeholder="Phone"/>
                    <form:errors path="phone" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>