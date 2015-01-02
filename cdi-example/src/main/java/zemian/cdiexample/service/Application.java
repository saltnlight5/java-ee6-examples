/*
 *  Zemian Deng 2014
 */

package zemian.cdiexample.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import zemian.service.logging.Logger;

/**
 *
 * @author zedeng
 */
@ApplicationScoped
public class Application {
    private static final Logger LOGGER = new Logger(Application.class);
    
    /** All available services discovered by CDI */
    @Inject
    private Instance<Service> services;
            
    public List<Service> getServices() {
        List<Service> result = new ArrayList<>();
        for (Service service : services)
            result.add(service);
        return result;
    }
    
    @PostConstruct
    public void init() {
        LOGGER.info("Application %s is starting with %d services", this, getServices().size());
    }
    
    @PreDestroy
    public void destroy() {
        LOGGER.info("Application %s is shutting down.", this);
    }
}
