package zemian.servlet3example.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import zemian.service.logging.Logger;

@WebListener
public class WebAppStartup implements ServletContextListener {
    private static final Logger LOGGER = new Logger(WebAppStartup.class);

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
