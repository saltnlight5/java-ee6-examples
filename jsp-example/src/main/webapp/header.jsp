<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JSP Example</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

    <style>
        body {
            padding: 65px 10px 10px 10px;
        }
    </style>
</head>

<body>
    <!-- Header horizontal navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" rel="home" href="${pageContext.request.contextPath}">
                JSP Example
            </a>
        </div>
        <ul class="nav navbar-nav">
            <li>
                <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/about.jsp">About</a>
            </li>
        </ul>
    </div>

    <div class="row">
        <div class="col-xs-2">
            <ul class="list-group">
                <a class="list-group-item" href="${pageContext.request.contextPath}/hello.jsp">Hello</a>
                <a class="list-group-item" href="${pageContext.request.contextPath}/jsp-vars.jsp">JSP Implicit Variables</a>
                <a class="list-group-item" href="${pageContext.request.contextPath}/jstl-example.jsp">JSTL Example</a>
                <a class="list-group-item" href="${pageContext.request.contextPath}/jstl-example2.jsp">JSTL Example (toString() Fixed)</a>
                <a class="list-group-item" href="${pageContext.request.contextPath}/jsp-redirect.jsp">Redirect Back to Index</a>
                <a class="list-group-item" href="${pageContext.request.contextPath}/tag-file-example.jsp">Custom Tag File Example</a>
            </ul>
        </div>
        <div class="col-xs-10">
            <!-- Main content -->
            <div class="content container">