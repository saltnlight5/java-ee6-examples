/*
 *  Zemian Deng 2014
 */

package zemian.servlet3jspexample;

/**
 * A singleton application context space to store global variables and states.
 * 
 * @author zedeng
 */
public class Application {
    public static final String SERVLET_CONTEXT_KEY = Application.class.getName();
    
    private static Application INSTANCE = new Application();
    
    private Application() {
    }
    
    public static Application getInstance() {
        return INSTANCE;
    }

    public void init() {
    }
    
    public void destroy() {
    }
}
