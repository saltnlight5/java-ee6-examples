package zemian.restexample.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

import zemian.service.util.Utils;

@Path("sscript")
public class ScriptResource {
	@Context
	private Application application;
	@Context
	private UriInfo uriInfo;
	@Context
	private HttpHeaders httpHeaders;
	
	@GET
	public String runScript(@QueryParam("file") @DefaultValue("") String file) {
		String result = null;
		Object[] params = new Object[]{
				"application", application
				, "uriInfo", uriInfo
				, "httpHeaders", httpHeaders
		};
		if (file.equals("")) {
			result = "" + Utils.runGroovyTestScript(params);
		} else {
			result = "" + Utils.runScript(file, params);
		}
		return result;
	}
}
