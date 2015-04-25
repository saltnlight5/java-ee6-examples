/*
 *  Zemian Deng 2014
 */

package zemian.mvcexample.service;

import java.util.HashMap;
import java.util.Map;
import zemian.mvcexample.web.controller.Controller;

/**
 * A singleton application context space to store global variables and states.
 * 
 * @author zedeng
 */
public class Application {
    private static Application INSTANCE = new Application();
    
    private Map<String, Controller> controllers = new HashMap<>();

    public Map<String, Controller> getControllers() {
        return controllers;
    }
    
    private Application() {
    }
    
    public static Application getInstance() {
        return INSTANCE;
    }
}
