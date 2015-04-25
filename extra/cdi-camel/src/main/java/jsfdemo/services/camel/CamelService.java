package jsfdemo.services.camel;

import jsfdemo.service.Service;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A service to start/stop Apache Camel for routes and messages processing. Users would need to add
 * a RouteBuilder to this service to add custom logic.
 * 
 * @author zedeng
 *
 */
public class CamelService implements Service {
	private static final Logger LOGGER = LoggerFactory.getLogger(CamelService.class);
	private CamelContext camelContext = new DefaultCamelContext();
	
	@Override
	public void start() throws Exception {
		initCamel();
		startCamel();
		afterCamelStarted();
	}
	
	protected void afterCamelStarted() {
	}

	@Override
	public void stop() {
		try {
			stopCamel();
		} catch (Exception e) {
			throw new RuntimeException("Failed to stop Camel.", e);
		}
	}

	protected void initCamel()  throws Exception {
	}

	protected void startCamel() throws Exception {
		camelContext.start();
		LOGGER.info("Camel started: {}", camelContext);
	}

	protected void stopCamel() {
		try {
			camelContext.stop();
			LOGGER.info("Camel stopped.");
		} catch (Exception e) {
			LOGGER.error("Failed to stop camel.", e);
		}
	}
	
	public CamelContext getCamelContext() {
		return camelContext;
	}
	
	public void addCamelRoute(RouteBuilder routeBuilder) {
		try {
			camelContext.addRoutes(routeBuilder);
		} catch (Exception e) {
			throw new RuntimeException("Failed to add camel route: " + routeBuilder, e);
		}
	}
}
