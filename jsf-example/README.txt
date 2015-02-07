= Quick JSF Ref
- By default the FacesServlet is mapped with *.jsf URL, and the view is using *.xhtml extension.
- The face-config.xml file is Optional in JSF 2
- Read API: https://docs.oracle.com/javaee/6/api/javax/faces/webapp/FacesServlet.html

= JSF Notes
- JSF tag ref doc for EE 6
    http://docs.oracle.com/javaee/6/javaserverfaces/2.1/docs/vdldocs/facelets/index.html

- Mojarra - The Oracle's open source implementation of the JSF standard
    https://javaserverfaces.java.net/
    https://javaserverfaces.java.net/users.html (Quick reference links to different version of JSF tag libraries)
    https://wikis.oracle.com/display/GlassFish/JavaServerFacesRI#JavaServerFacesRI-WhatarethetagsforthecurrentreleasesofMojarra%3F (Source)

    * Check out source (include demos)
        svn checkout https://svn.java.net/svn/mojarra~svn/tags/2.1.29 mojarra-2.1.29

- The EE6 tutorial is still show how to use web.xml config for JSF, but 
JSF in Servlet 3.0 (EE6) will automatically map FacesServlet to *.jsf as 
controller and *.xhtml as view.

    See http://docs.oracle.com/javaee/6/api/javax/faces/webapp/FacesServlet.html

- JSF tutorial 
    * http://www.tutorialspoint.com/jsf/index.htm
    * http://www.jsftoolbox.com/documentation/help/12-TagReference/core/index.jsf
	

== JSF and JSP/Servlet Versions

Servlet JSP JSTL EE    JSF
3.1     2.3 1.2  7     2.2
3.0     2.2 1.2  6     2.0
2.5     2.1 1.2  5     1.2
2.4     2.0 1.1  1.4   1.1
2.3     1.2 1.0  1.3   -

= jsf xhtml template
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"      
      xmlns:h="http://java.sun.com/jsf/html">
    <h:body>
		<p>Hello World.</p>
    </h:body>
</html>

= face-config.xml template
<?xml version="1.0" encoding="UTF-8"?>
<faces-config xmlns="http://java.sun.com/xml/ns/javaee"
			  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			  xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-facesconfig_2_0.xsd"
			  version="2.0">
</faces-config>

= web.xml 2.5 JSF config example
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
		 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
		 version="3.0">
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
		 xmlns="http://java.sun.com/xml/ns/javaee" 
		 xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
		 http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
		 version="2.5">
	<display-name>JavaServerFaces</display-name>
	<context-param>
		<param-name>javax.faces.PROJECT_STAGE</param-name>
		<param-value>Development</param-value>
	</context-param>
	<welcome-file-list>
		<welcome-file>faces/welcome.jsf</welcome-file>
	</welcome-file-list>
	<servlet>
		<servlet-name>FacesServlet</servlet-name>
		<servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>FacesServlet</servlet-name>
		<url-pattern>*.jsf</url-pattern>
	</servlet-mapping> 
</web-app>