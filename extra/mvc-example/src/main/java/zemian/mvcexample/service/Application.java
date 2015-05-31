package zemian.mvcexample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zemian.mvcexample.web.controller.*;
import zemian.mvcexample.web.Controller;
import zemian.mvcexample.web.controller.HelloController;
import zemian.mvcexample.web.controller.SysPropsController;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * The single Application instance to hold services and manage their lifecycles.
 * 
 * @author zedeng
 *
 */
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static final String APP_NAME = "mvn-example";
    public static final String DEFAULT_CONTROLLER_SERVLET_PATH = "/main";

    private static Application INSTANCE = new Application();
	private Application() {}
	public static Application getInstance() {
		return INSTANCE;
	}

    private String controllerServletPath = DEFAULT_CONTROLLER_SERVLET_PATH;
    private Map<String, Controller> controllers;
    private String version;

    @Override
    public String toString() {
        return APP_NAME + " - " + getVersion();
    }

    public void init() {
        controllers = createControllersMap();
    }

    public void destroy() {
    }

    private Map<String, Controller> createControllersMap() {
        Map<String, Controller> map = new HashMap<>();
        map.put("index", new DirectView(this));
        map.put("about", new DirectView(this));
        map.put("test", new DirectView(this));
        map.put("hello", new HelloController());
        map.put("sysprops", new SysPropsController());
        return map;
    }

    public Set<String> getControllerNames() {
        return controllers.keySet();
    }

    /**
     * Get version string at runtime from pom.properties inside META-INF. If not found returns SNAPSHOT with timestamp.
     * @return
     */
    public String getVersion() {
        if (version == null) {
            String res = "META-INF/maven/zemian/mvn-example/pom.properties";
            URL url = Thread.currentThread().getContextClassLoader().getResource(res);
            if (url == null) {
                version = "SNAPSHOT." + Utils.timestamp();
            } else {
                Properties props = Utils.loadProperties(res);
                version = props.getProperty("version");
            }
        }
        return version;
    }

    public String getName() {
        return APP_NAME;
    }

    public String getControllerServletPath() {
        return controllerServletPath;
    }

    public Controller getController(String name) {
        return controllers.get(name);
    }
}
