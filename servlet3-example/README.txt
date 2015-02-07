= web.xml templates

== 3.0 You can use annotation, or explicit with web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
		 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
		 version="3.0">	
    <welcome-file-list>
        <welcome-file>index</welcome-file>
    </welcome-file-list>
</web-app>

== 2.5
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
		 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
		 version="2.5" >
    <welcome-file-list>
        <welcome-file>index</welcome-file>
    </welcome-file-list>
	<servlet>
		<servlet-name>IndexServlet</servlet-name>
		<servlet-class>IndexServlet</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>IndexServlet</servlet-name>
		<url-pattern>/index</url-pattern>
	</servlet-mapping>
</web-app>
