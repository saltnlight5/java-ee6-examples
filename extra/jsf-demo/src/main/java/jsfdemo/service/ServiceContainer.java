package jsfdemo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Start and stop a list of services as a container.
 * 
 * @author zedeng
 *
 */
public class ServiceContainer implements Service {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceContainer.class);
	private List<Service> services = new ArrayList<>();
	
	public void add(Service service) {
		services.add(service);
	}

	/**
	 * Start a list of services in order. If one failed, it will stop the previous initialized services and throw the original failed exception.
	 */
	@Override
	public void start() throws Exception {
		startServicesInOrder();
	}

	/**
	 * Stop list of initialized services in reversed order.
	 */
	@Override
	public void stop() {
		stopServicesInReverse(services);
	}

	private void startServicesInOrder() throws Exception {
		List<Service> completedList = new ArrayList<>();
		for (Service service : services) {
			try {
				service.start();
				LOGGER.info("Service {} started.", service);
				completedList.add(service);
			} catch (Exception e) {
				LOGGER.error("Failed to start service {}. Will stop previous started services now.", service, e);
				stopServicesInReverse(completedList);
				throw e;
			}
		}
	}
	
	private void stopServicesInReverse(List<Service> serviceList) {
		List<Service> reversedList = new ArrayList<>(serviceList);
		Collections.reverse(reversedList);
		for (Service service : reversedList) {
			try {
				service.stop();
				LOGGER.info("Service {} stopped.", service);
			} catch (Exception e) {
				// We should not re-throw the exception during stop so other service can have a chance to shutdown as well.
				LOGGER.error("Failed to stop service {}.", service, e);
			}
		}
	}
	
}
