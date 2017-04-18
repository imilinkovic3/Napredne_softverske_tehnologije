<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/javascript" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link href="/resources/css/application.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
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

    <form:form modelAttribute="document" action="/nst/documents/save/" method="POST">
        <form:hidden path="id" value="${document.id}"/>
        <form:hidden path="company.pib" value="${sessionUser.companyBean.pib}"/>


        <div class="visible-lg-inline-block col-lg-offset-11">
            <form:button class="btn btn-primary" type="submit">Save document</form:button>
        </div>

        <table class="table" id="descriptorsTable">
            <tr>
                <td>
                    <h1>Document type name:</h1>
                </td>
                <td></br>
                    <form:input path="name" placeholder="Document type name" type="text" id="documentName"
                                class="form-control"
                                value="${document.name}" readonly="${readOnly}"/>
                </td>
            </tr>
            <tr>
                <th>Name</th>
                <th>Descriptor type</th>
                <th>Required</th>
            </tr>
            <c:forEach var="descriptor" items="${document.documentDescriptors}" varStatus="loop">
                <tr id='row${loop.index + 1}'>
                    <form:hidden path="documentDescriptors[${loop.index}].id" value="${descriptor.id}"/>

                    <td id='descriptorName${loop.index}'>
                        <form:input path="documentDescriptors[${loop.index}].name" placeholder='Descriptor name'
                                    readonly='${readOnly}'
                                    class='form-control' id='descriptorNameInput${loop.index}'/>
                    </td>
                    <td id='descriptorType${loop.index}'>
                        <form:select path="documentDescriptors[${loop.index}].descriptorType" name='type'
                                     class='form-control'
                                     id='types' readonly="${readOnly}">
                            <form:option value="-" label="--Please Select--"/>
                            <form:options items="${descriptorTypes}"/>
                        </form:select>
                    </td>
                    <td id='descriptorRequired${loop.index}' align="center">
                        <form:checkbox path="documentDescriptors[${loop.index}].required" value="false"/>
                    </td>
                    <td>
                        <input type="button" class="btn btn-danger" onclick="removeDescriptor('row${loop.index + 1}')"
                               value="Remove descriptor"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form:form>
    <input type="button" class="btn btn-info" onclick="addDocumentDescriptor()" value="Add descriptor"/>
</div>
</body>
</html>
<template:javascript/>