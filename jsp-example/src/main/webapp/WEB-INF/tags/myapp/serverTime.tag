<%@ attribute name="pattern" required="true" %>
<%@ tag import="java.util.Date, java.text.SimpleDateFormat" %>
<%= new SimpleDateFormat(pattern).format(new Date()) %>
