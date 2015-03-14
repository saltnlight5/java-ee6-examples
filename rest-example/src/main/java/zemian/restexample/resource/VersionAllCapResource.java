package zemian.restexample.resource;

import javax.ws.rs.Path;

@Path("v2/version")
public class VersionAllCapResource extends AbstractVersionResource implements IVersionResource {
	@Override
	public String getVersion() {
		return (getAppName() + "-" + getVersionValue()).toUpperCase();
	}

}
