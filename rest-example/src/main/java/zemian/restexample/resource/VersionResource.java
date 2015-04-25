package zemian.restexample.resource;

import javax.ws.rs.Path;

@Path("v1/version")
public class VersionResource extends AbstractVersionResource implements IVersionResource {
    @Override
    public String getVersion() {
            return getAppName() + "-" + getVersionValue();
    }
}
