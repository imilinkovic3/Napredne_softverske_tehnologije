<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/javascript" %>

<div class="company-name">
    <label type="text" id="company">${sessionUser.companyBean.name}</label>
</div>

<div class="header-right">
    <div class="one-line">
        <select id="selectRole" class="form-control" size="1">
            <option>Role</option>
            <c:forEach items="${sessionUser.roles}" var="role">
                <option value="${role.name}">${role.name}</option>
            </c:forEach>
        </select>

        <a class="link-css" href="/nst/user/update/${sessionUser.id}/">
            <label class="glyphicon glyphicon-user"></label>
            <label type="text" id="user-name-label">${sessionUser.firstname} ${sessionUser.lastname}</label>
        </a>

    </div>
</div>

<div class="header-left">
    <a class="link-css" href="/nst/logout/">
        <label class="glyphicon glyphicon-off"></label>
        <label type="text">Log out</label>
    </a>

</div>
<template:javascript/>