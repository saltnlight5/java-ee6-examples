package zemian.jpaexample.web.controller;


import javax.faces.bean.ManagedBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This managed bean will create new instance per HTTP request.
 * 
 * @author zedeng
 */
@ManagedBean
public class Hello {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);

    public Hello() {
        LOGGER.info("Hello component created: " + this);
    }
    
    private String world = "Hello World!";

    public String getWorld() {
        return world;
    }
}
