/*
 *  Zemian Deng 2014
 */

package zemian.servlet3example.service;

import java.io.File;

/**
 * A singleton application context space to store global variables and states.
 * 
 * @author zedeng
 */
public class Application {
    public static final String SERVLET_CONTEXT_KEY = "zemian.servlet3example.web.Application";
    
    private static Application INSTANCE = new Application();
    
    private File configFile;
    
    private Application() {
    }
    
    public static Application getInstance() {
        return INSTANCE;
    }
    
    public void init() {
        String userHome = System.getProperty("user.home");
        configFile = new File(userHome + "/servlet3example-config.properties");
    }
    
    public void destroy() {        
    }

    public File getConfigFile() {
        return configFile;
    }
    
}
