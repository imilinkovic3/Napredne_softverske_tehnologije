<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/javascript" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/bootstrap/css/bootstrap-treeview.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/application.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body id="mainBody">
<div id="tree"/>
</body>
</html>
<template:javascript/>

<script type="application/javascript">
    function getTree() {
        var data = [
            {
                text: "Parent 1",
                href: "http://www.facebook.com",
                nodes: [
                    {
                        text: "Child 1",
                        href: "http://www.facebook.com",
                        nodes: [
                            {
                                text: "Node 1",
                                id: "2",
                                href: "http://www.facebook.com",
                                icon: "glyphicon glyphicon-cog",
                                selectedIcon: "glyphicon glyphicon-cog",
                                color: "#000000",
                                backColor: "#FFFFFF",
                                selectable: true,
                                state: {
                                    checked: true,
                                    disabled: false,
                                    expanded: true,
                                    selected: true
                                }
                            },
                            {
                                text: "Grandchild 2"
                            }
                        ]
                    },
                    {
                        text: "Child 2"
                    }
                ]
            },
            {
                text: "Parent 2",
                nodes: [
                    {
                        text: "Child 1",
                        href: "http://www.facebook.com",
                        nodes: [
                            {
                                text: "Node 1",
                                id: "2",
                                href: "http://www.facebook.com",
                                icon: "glyphicon glyphicon-file",
                                selectedIcon: "glyphicon glyphicon-file",
                                color: "#000000",
                                backColor: "#FFFFFF",
                                selectable: true,
                                state: {
                                    checked: true,
                                    disabled: false,
                                    expanded: true,
                                    selected: true
                                }
                            },
                            {
                                text: "Grandchild 2"
                            }
                        ]
                    },
                    {
                        text: "Child 2"
                    }
                ]
            },
            {
                text: "Parent 3"
            },
            {
                text: "Parent 4"
            },
            {
                text: "Parent 5"
            }
        ];

        return data;
    }

    $('#tree').treeview({
        data: aaa(),
        onNodeSelected: function (event, data) {
            alert(data.id);
        }
    });

    function aaa() {
        $.get("/process/getAll/", function (data) {
            console.log("AAAA");
            alert(JSON.stringify(data));
            return data;
        });
    }

</script>