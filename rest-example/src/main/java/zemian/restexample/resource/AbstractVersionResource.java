package zemian.restexample.resource;

public abstract class AbstractVersionResource implements IVersionResource {
	public String getAppName() {
		return "rest-exampl";
	}
	
	public String getVersionValue() {
		return "1.0";
	}

}
