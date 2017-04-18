<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><tiles:getAsString name="title"/></title>
    <link href="/resources/css/template.css" rel="stylesheet"/>
</head>

<body>
<header class="fixed-header">
    <tiles:insertAttribute name="header"/>
</header>

<section class="left-menu">
    <tiles:insertAttribute name="menu"/>
</section>

<section class="fixed-content">
    <tiles:insertAttribute name="body"/>
</section>

<footer class="fixed-footer">
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>