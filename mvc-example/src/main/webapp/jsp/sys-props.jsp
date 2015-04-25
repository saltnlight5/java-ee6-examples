<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <body>
        <table>
            <c:forEach var="entry" items="${sysProps}">
            <tr>
                <td>${entry.key}</td>
                <td>
                    ${entry.value}
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
