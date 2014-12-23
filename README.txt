= About java-ee6-examples

Explore Java EE 6 by examples
	You will find Maven based projects that explore some of major EE 6 
	components or specs	with working code examples. Most of the examples 
	are developed and run on Glassfish Server.
	
	The purpose of this project is just for my own learning and exploration.
	You may use	these as tutorial and further explore the EE stack yourself.

Author: Zemian Deng <saltnlight5 at gmail dot com>
	You may also want to visit my blog at http://saltnlight5.blogspot.com 
	to see more detailed explanations with these examples.
	
Project Source Code Hosting:
	https://github.com/saltnlight5/java-ee6-examples
	
= Application Logging Dependency

We will try to explore EE stacks without much dependencies as possible, with 
the exception of logging. I prefer to use SLF4J logging, and this library is 
not the standard, so you need to install it into your server first. I choose 
to deploy this way so the application should be very thin and should not 
contain repeated common external libraries.

If the server you are using does not include SLF4J (or need special ways to 
configure it) then please read the server specific documentation on how to add 
common libraries for all applications to use. For example with GlassFish 
server, see "glassfish-logging-example/README.txt" on how to do this.
