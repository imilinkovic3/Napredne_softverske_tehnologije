<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${message}</strong>
        </div>
    </c:if>

    <h1>Employees</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        </thead>

        <c:forEach var="user" items="${users}">
            <c:if test="${user.id != sessionUser.id}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstname}</td>
                    <td>${user.email}</td>
                    <td>
                        <spring:url value="/nst/user/details/${user.id}/" var="detailsUrl"/>
                        <spring:url value="/nst/user/update/${user.id}/" var="updateUrl"/>
                        <spring:url value="/nst/user/delete/${user.id}/" var="deleteUrl"/>
                        <a class="btn btn-info" href="${detailsUrl}">Details</a>
                        <a class="btn btn-primary" href="${updateUrl}">Update</a>
                        <a class="btn btn-danger" href="${deleteUrl}">Delete</a>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
    <a class="btn btn-primary" href="/nst/user/add/">Add user</a>
</div>