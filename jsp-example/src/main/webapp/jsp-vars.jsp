<!DOCTYPE html>
<html>
    <body>
        <h1>JSP Examples</h1>
        <p>Implicit Variables</p>
        <table>
            <tr>
                <td>Name</td><td>Instance</td><td>Example</td>
            </tr>
            <tr>
                <td>applicationScope</td><td>${applicationScope}</td><td>${applicationScope['myAppName']}</td>
            </tr>
            <tr>
                <td>sessionSope</td><td>${sessionSope}</td><td>${sessionSope['loginSession']}</td>
            </tr>
            <tr>
                <td>pageScope</td><td>${pageScope}</td><td>${pageScope['javax.servlet.jsp.jspConfig']}</td>
            </tr>
            <tr>
                <td>requestScope</td><td>${requestScope}</td><td>${requestScope['foo']}</td>
            </tr>
            <tr>
                <td>param</td><td>${param}</td><td>${param['query']}</td>
            </tr>
            <tr>
                <td>header</td><td>${header}</td><td>${header['user-agent']}</td>
            </tr>
            <tr>
                <td>cookie</td><td>${cookie}</td><td>${cookie['JSESSIONID']}</td>
            </tr>
            <tr>
                <td>pageContext</td><td>${pageContext}</td><td>${pageContext.request.contextPath}</td>
            </tr>
        </table>
        <p>Page served on <%= new java.util.Date()%></p>
    </body>
</html>
