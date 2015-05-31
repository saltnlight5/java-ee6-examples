<%@ include file="header.jsp" %>

<h1>Java System Properties</h1>
<table class="table">
    <c:forEach var="entry" items="${sysProps}">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
        </tr>
    </c:forEach>
</table>

<%@ include file="footer.jsp" %>

