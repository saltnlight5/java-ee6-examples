package zemian.restexample.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface IVersionResource {
    @Path("versionValue")
    @GET
    String getVersion();	
}
