<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
    <spring:bind path="companyBean.pib">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="companyBean.pib" type="text" name="pib"
                        placeholder="PIB" id="pib" tabindex="1" class="form-control"
                        value=""/>
            <form:errors path="companyBean.pib" class="control-label"/>
        </div>
    </spring:bind>

    <spring:bind path="companyBean.name">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="companyBean.name" type="text" name="companyName"
                        placeholder="Company name" id="companyName" tabindex="1"
                        class="form-control" value=""/>
            <form:errors path="companyBean.name" class="control-label"/>
        </div>
    </spring:bind>
</div>