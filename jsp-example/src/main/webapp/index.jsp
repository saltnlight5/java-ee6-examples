<!DOCTYPE html>
<html>
    <body>
        <h1>JSP Examples</h1>
        <p>Explore EE 6 JSP and JSTL features</p>
        <p>Ensure your server supports JSTL tag library.</p>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Index</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp-vars.jsp">JSP Implicit Variables</a></li>
            <li><a href="${pageContext.request.contextPath}/jstl-examle.jsp">JSTL Example</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp-redirect.jsp">Redirect Back to Index</a></li>
        </ul>
        <p>Page served on <%= new java.util.Date()%></p>
    </body>
</html>
