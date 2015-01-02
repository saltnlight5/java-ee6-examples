<!DOCTYPE html>
<html>
    <body>
        <h1>CDI Examples</h1>
        <p>Explore EE 6 CDI features</p>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Index</a></li>
            <li><a href="${pageContext.request.contextPath}/hello">Hello</a></li>
            <li><a href="${pageContext.request.contextPath}/service-listing">Service Listing</a></li>
        </ul>
        <p>Page served on <%= new java.util.Date()%></p>
    </body>
</html>
