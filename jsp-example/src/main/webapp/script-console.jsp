<%@ page import="java.io.*,java.util.*,javax.script.*" %>
<%
// A script console jsp for Java
// Last modified: Zemian Deng <saltnlight5@gmail.com> 05/15/2014

ScriptEngineManager factory = new ScriptEngineManager();

// Get all the script engine names available by inspecting the classpath
List<String> scriptEngineNames = new ArrayList<String>();
for (ScriptEngineFactory fac : factory.getEngineFactories()) {
	String name = fac.getLanguageName();
	if (name.toLowerCase().equals("ecmascript")) {
		name = "JavaScript";
	}
	scriptEngineNames.add(name);
}
	
// Process Form
String scriptText = request.getParameter("scriptText");
String scriptEngineName = request.getParameter("scriptEngineName");
if (scriptEngineName == null) 
	scriptEngineName = "JavaScript";
request.setAttribute("scriptText", scriptText);
request.setAttribute("scriptEngineName", scriptEngineName);
if (scriptText != null) {
	ScriptEngine scriptEngine = factory.getEngineByName(scriptEngineName);	        
	if (scriptEngine == null)
		throw new RuntimeException("Failed to find ScriptEngine " + scriptEngineName);

	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	PrintWriter webOut = new PrintWriter(outStream);

	// Script engine binding variables.
	Bindings bindings = scriptEngine.createBindings();
	bindings.put("page", page);
	bindings.put("config", config);
	bindings.put("pageContext", pageContext);
	bindings.put("request", request);
	bindings.put("response", response);
	bindings.put("out", out);
	bindings.put("session", session);
	bindings.put("application", application);
	
	bindings.put("scriptEngine", scriptEngine);
	bindings.put("webout", webOut);

	// Run the scriptText
	try {
		Object scriptingOutput = scriptEngine.eval(scriptText, bindings);
		if (scriptingOutput == null)
			scriptingOutput = "";
		request.setAttribute("scriptingOutput", scriptingOutput);
	} catch (Exception e) {
		//throw new RuntimeException("Failed execute scriptText.", e);
		StringWriter errorWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(errorWriter));
		request.setAttribute("error", errorWriter.toString());
	} finally {
		webOut.close();
		String webOutResult = outStream.toString();
		if (webOutResult == null)
			webOutResult = "";
		request.setAttribute("webOutResult", webOutResult);
	}
} else {	
	request.setAttribute("scriptText", "");
	request.setAttribute("scriptingOutput", "");
	request.setAttribute("webOutResult", "");
}
%>
<html>

<head>
<%--
	<!-- 
	Use a nice and simple JavaScript editor from http://codemirror.net
	-->
	<script src="//raw.githubusercontent.com/marijnh/CodeMirror/4.1.0/lib/codemirror.js"></script>
	<script src="//raw.githubusercontent.com/marijnh/CodeMirror/4.1.0/mode/javascript/javascript.js"></script>
	<script src="//raw.githubusercontent.com/marijnh/CodeMirror/4.1.0/mode/groovy/groovy.js"></script>
	<link rel="stylesheet" href="//raw.githubusercontent.com/marijnh/CodeMirror/4.1.0/lib/codemirror.css">
 --%>
</head>

<body>
<h4>Script Console for Java</h4>
<form method="post">
<textarea id="code" name="scriptText" rows="20" cols="120"><%= request.getAttribute("scriptText") %></textarea>
<br/>
<select name="scriptEngineName">
<% 
for (String engineName : scriptEngineNames) { 
	if (scriptEngineName.equals(engineName))
		out.println("<option selected='true'>" + engineName + "</option>");
	else
		out.println("<option>" + engineName + "</option>");
}
%>
</select>
<input name="Run" type="submit" value="run"/>
<hr/>
<pre>
<%= request.getAttribute("scriptingOutput") %>
<%= request.getAttribute("webOutResult") %>
</pre>
</form>

<%--
<script>
var editor = CodeMirror.fromTextArea(document.getElementById("code"), {
  lineNumbers: true
});
</script>
--%>

<%
if (request.getAttribute("error") != null) {
	out.println("<pre>");
	out.println(request.getAttribute("error"));
	out.println("</pre>");
}
%>

</body></html>