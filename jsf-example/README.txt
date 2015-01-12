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