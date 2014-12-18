// Access EJB beans through JNDI
// See http://docs.oracle.com/javaee/6/tutorial/doc/gipjf.html

// Debug
importPackage(Packages.zemian.jpaexample.web.rest);
JndiUtils.printJndiTree();

// Get a local EJB within the same module deployment
importPackage(Packages.zemian.jpaexample.web.rest);
userService = JndiUtils.lookup("java:module/UserService");
users = userService.findMostActiveUsers()
println("Found " + users.size() + " users.");
for (i=0; i < users.size(); i++) {
    user = users.get(0);
    println(user);
}
users.size();
