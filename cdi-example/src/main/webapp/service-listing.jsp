<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <body>
        <h1>Service Listing</h1>
        <table>
            <c:forEach var="service" items="${services}">
            <tr>
                <td>${service.name}</td>
            </tr>
            </c:forEach>
        </table>        
        <p>Page served on <%= new java.util.Date()%></p>
    </body>
</html>
