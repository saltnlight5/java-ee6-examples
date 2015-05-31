<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${app.name}</title>

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
            <a class="navbar-brand" rel="home" href="${controllerPath}">
                ${app.name} ${app.version}
            </a>
        </div>
        <ul class="nav navbar-nav">
            <li>
                <a href="${controllerPath}/index">Home</a>
            </li>
            <li>
                <a href="${controllerPath}/about">About</a>
            </li>
        </ul>
    </div>

    <div class="row">
        <div class="col-xs-2">
            <ul class="list-group">
                <a class="list-group-item" href="${controllerPath}/hello">Hello</a>
                <a class="list-group-item" href="${controllerPath}/sysprops">Sys Props</a>
            </ul>
        </div>
        <div class="col-xs-10">
            <!-- Main content -->
            <div class="content container">