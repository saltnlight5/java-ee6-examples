package jsfdemo.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jsfdemo.service.ServiceApplication;

/**
 * Application startup and shutdown from Servlet environment.
 * 
 * @author zedeng
 *
 */
public class WebAppListener implements ServletContextListener {
	private ServiceApplication serviceApplication = ServiceApplication.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent event) {
		serviceApplication.quickStart();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		serviceApplication.quickStop();
	}

}
