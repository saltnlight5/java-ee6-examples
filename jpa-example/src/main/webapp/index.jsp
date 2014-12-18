<!DOCTYPE html>
<html>
    <body>
        <h1>JPA Examples</h1>
        <p>Explore EE 6 Java Persistence API features</p>
        <h2>Web pages</h2>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index">Index</a></li>
            <li><a href="${pageContext.request.contextPath}/user">User</a></li>
        </ul>
        <h2>REST resources</h2>
        <ul>
            <li><a href="${pageContext.request.contextPath}/rest/script">script</a></li>
            <li><a href="${pageContext.request.contextPath}/rest/user">users</a></li>
            <li><a href="${pageContext.request.contextPath}/rest/samples">samples</a></li>
        </ul>
        <p>Page served on <%= new java.util.Date()%></p>
    </body>
</html>
