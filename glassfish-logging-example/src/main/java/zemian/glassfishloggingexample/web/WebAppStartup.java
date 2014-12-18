package zemian.glassfishloggingexample.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An app listener that uses SLF4J logger.
 * 
 * @author zedeng
 */
@WebListener
public class WebAppStartup implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppStartup.class);

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
