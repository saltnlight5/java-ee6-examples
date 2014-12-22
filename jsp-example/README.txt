= About JSP
Quoting from Oracle JSP home page:

JavaServer Pages Standard Tag Library (JSTL) encapsulates as simple tags the core functionality common to many Web applications. JSTL has support for common, structural tasks such as iteration and conditionals, tags for manipulating XML documents, internationalization tags, and SQL tags. It also provides a framework for integrating existing custom tags with JSTL tags.

= Build

To bulid this module alone, use this command

cd java-ee6-examples
mvn -pl java-ee6-examples package

= References
JSP Home page: http://www.oracle.com/technetwork/java/index-jsp-135995.html

JSP tutorial: http://docs.oracle.com/javaee/5/tutorial/doc/bnagx.html

JSTL Tag: http://docs.oracle.com/javaee/5/jstl/1.1/docs/tlddocs/
Tutorial on JSTL section: http://docs.oracle.com/javaee/6/tutorial/doc/bnacj.html#bnaco
Older Tutorial on JSTL section: http://docs.oracle.com/javaee/5/tutorial/doc/bnakc.html

Quick JSP and JSTL ref: http://stackoverflow.com/tags/jstl/info

== JSP Implicit Variables

    Implicit Objects

    The JSP expression language defines a set of implicit objects:

        pageContext: The context for the JSP page. Provides access to various objects including:
            servletContext: The context for the JSP page’s servlet and any web components contained in the same application. See Accessing the Web Context.
            session: The session object for the client. See Maintaining Client State.
            request: The request triggering the execution of the JSP page. See Getting Information from Requests.
            response: The response returned by the JSP page. See Constructing Responses.
        In addition, several implicit objects are available that allow easy access to the following objects:
            param: Maps a request parameter name to a single value
            paramValues: Maps a request parameter name to an array of values
            header: Maps a request header name to a single value
            headerValues: Maps a request header name to an array of values
            cookie: Maps a cookie name to a single cookie
            initParam: Maps a context initialization parameter name to a single value
        Finally, there are objects that allow access to the various scoped variables described in Using Scope Objects.
            pageScope: Maps page-scoped variable names to their values
            requestScope: Maps request-scoped variable names to their values
            sessionScope: Maps session-scoped variable names to their values
            applicationScope: Maps application-scoped variable names to their values

http://docs.oracle.com/javaee/5/tutorial/doc/bnahq.html#bnaif
http://docs.oracle.com/javaee/5/tutorial/doc/bnahq.html#bnaif

== JSP/Servlet Versions

Servlet JSP JSTL EE    JSF
3.1     2.3 1.2  7     2.2
3.0     2.2 1.2  6     2.0
2.5     2.1 1.2  5     1.2
2.4     2.0 1.1  1.4   1.1
2.3     1.2 1.0  1.3   -

EE Stack Spec Versions: http://en.wikipedia.org/wiki/Java_EE_version_history

= WebLogic Server Notes

JSF and JSTL docs: https://docs.oracle.com/cd/E21764_01/web.1111/e13712/configurejsfandjtsl.htm#WBAPP207
