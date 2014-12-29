package zemian.servlet3example.web;

import zemian.servlet3example.service.Application;
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
        Application app = Application.getInstance();
        app.init();
        
        // Since we are providing singleton access to application, storing
        // it into ServletContext is really redudant, but for demo purpose, one
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
