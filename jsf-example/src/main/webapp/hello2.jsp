<%--
NOTE: that you can't use #{} inside a JSP page!
And you can't use .jsp as JSF view extension.

This page will result in error:
org.apache.jasper.JasperException: /hello2.jsp(3,13) PWC6228: #{...} not allowed in a template text body.
--%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">
    <h:body>
        <p>#{hello2.world}</p>
    </h:body>
</html>