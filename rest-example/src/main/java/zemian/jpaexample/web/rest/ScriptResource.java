/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zemian.jpaexample.web.rest;

import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import zemian.service.logging.Logger;

/**
 * Give instant access to your internal application with dynamic scripting.
 *
 * <p>Example script:
 * <pre>
 * "sc" + servletContext + ", req=" + request;
 * </pre>
 *
 * <p>Example2
 * <pre>
 * names = servletContext.getAttributeNames();
 * while(names.hasMoreElements()) {
 *   name = names.nextElement();
 *   println(name);
 * }
 * </pre>
 * 
 * <p>To import Java classes, use "Packages.<packageName>" or "importPackage(<packageName>)".
 * <pre>
 * importPackage(java.text);
 * df = new SimpleDateFormat("MM/dd/yyyy");
 * dt = df.parse("01/01/2014");
 * </pre>
 */
@Path("script")
public class ScriptResource {
    private static final Logger LOGGER = new Logger(ScriptResource.class);
   
    @Context
    private ServletContext servletContext;
       
    @POST
    public String script(@Context HttpServletRequest request, 
            @QueryParam("engineName") @DefaultValue("JavaScript") String engineName,
            String scriptText) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName(engineName);
        LOGGER.info("Running script text length=" + scriptText.length() + ", engine=" + engine);
        Object result;
        try (Reader reader = new StringReader(scriptText)) {
            Bindings bindings = engine.createBindings();
            bindings.put("servletContext", servletContext);
            bindings.put("request", request);
            result = engine.eval(reader, bindings);
        }
        LOGGER.info("Result " + result);
        return "RESULT=" + result;
    }
    
    @POST
    @Path("file")
    public String scriptFile(@Context HttpServletRequest request,
            @QueryParam("engineName") @DefaultValue("JavaScript") String engineName,
            String fileName) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName(engineName);
        LOGGER.info("Running script file=" + fileName + ", engine=" + engine);
        Object result;
        try (Reader reader = new FileReader(fileName)) {
            Bindings bindings = engine.createBindings();
            bindings.put("servletContext", servletContext);
            bindings.put("request", request);
            result = engine.eval(reader, bindings);
        }
        LOGGER.info("Result " + result);
        return "RESULT=" + result;
    }
}