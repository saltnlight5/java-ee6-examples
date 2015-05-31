package zemian.servlet3jspexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppStartup implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppStartup.class);


    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOGGER.debug("WebApp is starting up.");
        Application app = Application.getInstance();
        app.init();
                
        // Since we are providing singleton access to application, storing
        // it into ServletContext is really redundant, but for demo purpose, one
        // can store it there and it can be access by all web components without
        // having to call the singleton access as alternative.
        event.getServletContext().setAttribute(Application.SERVLET_CONTEXT_KEY, app);
        LOGGER.info("WebApp initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        Application app = Application.getInstance();
        app.destroy();
        LOGGER.info("WebApp destroyed.");
    }
}
