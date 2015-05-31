package zemian.mvcexample.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zemian.mvcexample.service.Application;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppStartup implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppStartup.class);

    @Override
	public void contextInitialized(ServletContextEvent event) {
		Application app = Application.getInstance();
        app.init();

        ServletContext ctx = event.getServletContext();
        ctx.setAttribute("app", app);

        // Save the contextPath for easy JSP link display
        ctx.setAttribute("contextPath", ctx.getContextPath());
        ctx.setAttribute("controllerPath", ctx.getContextPath() + app.getControllerServletPath());

        LOGGER.info("Application {} is initialized.", app);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
        Application app = Application.getInstance();
        app.destroy();
        LOGGER.info("Application {} is destroyed.", app);
	}
}
