package zemian.restexample.resource;

import java.net.URL;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import zemian.service.util.Utils;

@Path("/class-analysis")
public class ClassAnalysisResource {

		@GET
		@Path("/resource")
		public String getResource(@QueryParam("name") String name) {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL res = classLoader.getResource(name);
			return res.toString();
		}
		
		@GET
		@Path("/resource-content")
		public String getResourceContent(@QueryParam("name") String name) {
			List<String> lines = Utils.readResourceLines(name);
			StringBuffer sb = new StringBuffer();
			for (String line : lines) {
				sb.append(line).append("\n");
			}
			return sb.toString();
		}
		
		@GET
		@Path("/class")
		public String getClassLocation(@QueryParam("name") String name) throws ClassNotFoundException {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();			
			Class<?> clazz = classLoader.loadClass(name);
			URL loc = clazz.getProtectionDomain().getCodeSource().getLocation();
			return loc.toString();
		}
}
