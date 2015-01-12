package zemian.servlet3example.web;

import javax.servlet.ServletContext;
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
        
        logAppInfo(event);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        Application app = Application.getInstance();
        app.destroy();
        LOGGER.info("WebApp destroyed.");
    }

    private void logAppInfo(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        StringBuilder sb = new StringBuilder("Application Information:\n");
        sb.append(String.format("\tServerInfo: %s", sc.getServerInfo()));
        sb.append(String.format("\n\tServlet Version: %s.%s", sc.getMajorVersion(), sc.getMinorVersion()));
        sb.append(String.format("\n\tServletContext Instance: %s", sc));
        sb.append(String.format("\n\tServletContext Name: %s, Path: %s", sc.getServletContextName(), sc.getContextPath()));
        sb.append(String.format("\n\tSingleton Application Instance: %s", Application.getInstance()));
        sb.append(String.format("\n\tWebAppStartup Listener Instance: %s", this));
        sb.append(String.format("\n\tCurrent Thread: %s", Thread.currentThread()));
        sb.append(String.format("\n\tCurrent Thread ContextClassLoader: %s", Thread.currentThread().getContextClassLoader()));
        sb.append(String.format("\n\tContextClassLoader Tree: %s", ThreadInfoServlet.getClassLoaderTreeInfo("\t")));
        LOGGER.info(sb.toString());
    }
}
