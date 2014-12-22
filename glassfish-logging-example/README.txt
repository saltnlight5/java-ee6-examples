= glassfish-logging-example

This is just an empty web application that will explore and test how we
can use SLF4J logging facade in GlassFish server. Due to glassfish server
implementation also embedded SLF4J, we need to specially setup the server
to handle per application loggin guse SLF4J. See instruction below.

The web application provide has a context listener that will log a message
by SLF4J when it starts up. This is how we can verify that logging is working.

Since we want to use SLF4J in our application, we also must select a logging
framework implementation. For simplicity sake, we will use the JDK logging
as SLF4J implemenation. The instruction below will tell you show to configure
the JDK logging by package name.

== How to build this application

1. cd glassfish-logging-example
2. mvn package
3. cp target/glassfish-logging-example-1.0-SNAPSHOT.war $GF/domains/domain1/autodeploy

Now inspect your log file at $GF/domains/domain1/logs/server.log

= Gettin started with Glassfish server

== Start server

1. cd $GF/bin
2. asadmin start-domain domain1

== Stop server

1. cd $GF/bin
2. asadmin stop-domain domain1

== Server Ports

Admin Console Application is at http://localhost:4848
	* Default setup has no user and password restriction!

Web applications is http://localhost:8080

== To create a new domain with diferent ports

1. cd $GF/bin
2. asadmin create-domain --portbase 9000 domain2

* If you accept default then again no password for admin user. After this, your
  admin console app is at http://localhost:9048 while your application is at
  http://localhost:9080
  
= Glassfish Server Setup

== Getting the dependency jars

You may download jars from their project site, or you may use the Maven and fetch it using
the custom profiles defined in this project pom.xml like this:

	1. cd glassfish-logging-example
	2. mvn dependency:copy-dependencies -P glassfish-logging

Now you will find the jars in target/dependency folder.	Just copy what you need.

== How to setup SLF4J

1. Copy slf4j-api-1.7.7 and slf4j-jdk-1.7.7 jars into $GF/lib/endorsed
2. Edit $GF/domains/domain1/config/logging.properties and add your own logging package level
	zemian.level=FINEST
	
	* NOTE: The "zemian" is the package name I used in this glassfish-logging-example application.

== How to enable JSTL tag for all web applications

1. Copy jstl-1.2.jar into $GF/domains/domain1/lib

== How to add MySQL Driver for all applications

1. Copy mysql-connector-java-5.1.30-bin.jar into $GF/domains/domain1/lib

= Refs
https://glassfish.java.net
http://hwellmann.blogspot.com/2010/12/glassfish-logging-with-slf4j-part-2.html
