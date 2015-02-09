package zemian.jsfexample.web.controller;


import javax.faces.bean.ManagedBean;
import zemian.service.logging.Logger;

/**
 * This managed bean will create new instance per HTTP request.
 * 
 * @author zedeng
 */
@ManagedBean
public class Hello2 {
    private static final Logger LOGGER = new Logger(Hello2.class);
    public Hello2() {
        LOGGER.info("Hello2 component created: " + this);
    }
    
    private String world = "Hello2 World!";

    public String getWorld() {
        return world;
    }
}
