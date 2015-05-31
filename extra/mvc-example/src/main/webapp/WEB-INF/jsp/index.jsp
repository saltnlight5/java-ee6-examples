<%@ include file="header.jsp" %>

<h1>Welcome</h1>
<p>Home of Model View Controller Demo.</p>
<ul>
    <c:forEach var="name" items="${controllerNames}">
        <li><a href="${pageContext.request.contextPath}/main/${name}">${name}</a></li>
    </c:forEach>
</ul>

<%@ include file="footer.jsp" %>
