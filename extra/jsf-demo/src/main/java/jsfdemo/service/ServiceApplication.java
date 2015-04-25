package jsfdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An single application context. We will assemble all the services together to form an application. Users should add 
 * new services into this class, and use this as the entry and exit points of the application.
 * 
 * <p>Different runtime environments need to ensure to invoke #start() and #stop() as part of the life cycle to ensure
 * all depended services are properly started and stopped.
 * 
 * @author zedeng
 *
 */
public class ServiceApplication implements Service {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceApplication.class);
	private static final ServiceApplication INSTANCE = new ServiceApplication();
	private ServiceContainer serviceContainer = new ServiceContainer();

	private ServiceApplication() {		
	}
	
	public static ServiceApplication getInstance() {
		return INSTANCE;
	}

	@Override
	public void start() throws Exception {
		addServices();
		serviceContainer.start();
		LOGGER.info("Application started.");
	}

	@Override
	public void stop() throws Exception {
		serviceContainer.stop();
		LOGGER.info("Application stopped.");
	}
	
	/**
	 * Convenient method to call #start() without checked exception.
	 */
	public void quickStart() {
		try {
			start();
		} catch (Exception e) {
			throw new RuntimeException("Failed to start application", e);
		}
	}

	/**
	 * Convenient method to call #stop() without checked exception.
	 */
	public void quickStop() {
		try {
			serviceContainer.stop();
		} catch (Exception e) {
			throw new RuntimeException("Failed to stop application.", e);
		}
	}

	private void addServices() {
		//serviceContainer.add(createCamelService());
	}

}
