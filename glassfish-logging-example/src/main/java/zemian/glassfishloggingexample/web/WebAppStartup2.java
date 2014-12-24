package zemian.glassfishloggingexample.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import zemian.service.logging.Logger;

/**
 * An app listener that uses SLF4J logger.
 * 
 * @author zedeng
 */
@WebListener
public class WebAppStartup2 implements ServletContextListener {
    private static final Logger LOGGER = new Logger(WebAppStartup2.class);
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOGGER.debug("WebApp is starting up.");
        LOGGER.info("WebApp initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOGGER.info("WebApp destroyed.");
    }
}
