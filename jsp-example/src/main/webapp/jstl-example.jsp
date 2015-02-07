<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="utils" uri="utils" %>
<!DOCTYPE html>
<html>
    <body>
        <h1>JSTL Examples</h1>
        <h2>List of Application Context: ${applicationScope}</h2>
        <table>
            <c:forEach var="entry" items="${applicationScope}">
            <tr>
                <td>${entry.key}</td>
                <td>
                    <%-- A map entry could have a bad .toString() implemenation, 
                    so we use a util to safely convert to a string output. 
                    Look for "_BAD_TO_STRING" suffix in output to see if there is any.
                    --%>
                    <c:out value="${utils:toString(entry.value)}"/>
                </td>
            </tr>
            </c:forEach>
        </table>
        
        <h2>List of Session Context: ${sessionScope}</h2>
        <table>
            <c:forEach var="entry" items="${sessionScope}">
            <tr>
                <td>${entry.key}</td>
                <td>
                    <c:out value="${utils:toString(entry.value)}"/>
                </td>
            </tr>
            </c:forEach>
        </table>
        
        <h2>List of Page Context: ${pageScope}</h2>
        <table>
            <c:forEach var="entry" items="${pageScope}">
            <tr>
                <td>${entry.key}</td>
                <td>
                    <c:out value="${utils:toString(entry.value)}"/>
                </td>
            </tr>
            </c:forEach>
        </table>
        
        <h2>List of Request Context: ${requestSope}</h2>
        <table>
            <c:forEach var="entry" items="${requestSope}">
            <tr>
                <td>${entry.key}</td>
                <td>
                    <c:out value="${utils:toString(entry.value)}"/>
                </td>
            </tr>
            </c:forEach>
        </table>
        
        <h2>List of Query Parameters: ${param}</h2>
        <%--
        Set your URL with ?foo=bar to see output!
        --%>
        <table>
            <c:forEach var="entry" items="${param}">
            <tr>
                <td>${entry.key}</td>
                <td>
                    <c:out value="${utils:toString(entry.value)}"/>
                </td>
            </tr>
            </c:forEach>
        </table>
        
        <h2>List of Header Parameters: ${header}</h2>
        <table>
            <c:forEach var="entry" items="${header}">
            <tr>
                <td>${entry.key}</td>
                <td>
                    <c:out value="${utils:toString(entry.value)}"/>
                </td>
            </tr>
            </c:forEach>
        </table>        
        
        <h2>List of Cookies: ${cookie}</h2>
        <table>
            <c:forEach var="entry" items="${cookie}">
            <tr>
                <td>${entry.key}</td>
                <td>
                    <c:out value="${utils:toString(entry.value)}"/>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
