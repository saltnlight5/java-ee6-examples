package zemian.jsfexample.web.controller;


import javax.faces.bean.ManagedBean;
import zemian.service.logging.Logger;

/**
 * This managed bean will create new instance per HTTP request.
 * 
 * @author zedeng
 */
@ManagedBean
public class Hello {
    private static final Logger LOGGER = new Logger(Hello.class);
    public Hello() {
        LOGGER.info("Hello component created: " + this);
    }
    
    private String world = "Hello World!";

    public String getWorld() {
        return world;
    }
}
