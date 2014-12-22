<!DOCTYPE html>
<html>
    <body>
        <h1>JSP Examples</h1>
        <p>Implicit Variables</p>
        <table>
            <tr>
                <td>Name</td><td>Value</td>
            </tr>
            <tr>
                <td>applicationScope</td><td>${applicationScope}</td>
            </tr>
            <tr>
                <td>sessionSope</td><td>${sessionSope}</td>
            </tr>
            <tr>
                <td>pageScope</td><td>${pageScope}</td>
            </tr>
            <tr>
                <td>requestScope</td><td>${requestScope}</td>
            </tr>
            <tr>
                <td>header</td><td>${header}</td>
            </tr>
            <tr>
                <td>cookie</td><td>${cookie}</td>
            </tr>
            <tr>
                <td>pageContext</td><td>${pageContext}</td>
            </tr>
            <tr>
                <td>contextPath </td><td>${pageContext.request.contextPath}</td>
            </tr>
        </table>
        <p>Page served on <%= new java.util.Date()%></p>
    </body>
</html>
