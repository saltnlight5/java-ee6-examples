package zemian.cdiexample.web;

import java.util.Enumeration;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import zemian.cdiexample.service.Application;
import zemian.service.logging.Logger;

@WebListener
public class WebAppStartup implements ServletContextListener {
    private static final Logger LOGGER = new Logger(WebAppStartup.class);
    
    @Inject
    private Application application;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOGGER.debug("WebApp is starting up.");
        //logServletContextAttributes(event.getServletContext());
        
        LOGGER.info("Application %s is ready.", application);
        
        LOGGER.info("WebApp initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOGGER.info("WebApp destroyed.");
    }

    private void logServletContextAttributes(ServletContext sc) {
        Enumeration<String> names =  sc.getAttributeNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            LOGGER.info("SC attr '%s'=%s", name, sc.getAttribute(name));
        }
    }
}
