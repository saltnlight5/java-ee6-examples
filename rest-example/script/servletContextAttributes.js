names = servletContext.getAttributeNames();
while(names.hasMoreElements()) {
  name = names.nextElement();
  println(name);
}
