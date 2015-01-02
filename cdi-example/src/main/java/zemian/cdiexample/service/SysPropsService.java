/*
 *  Zemian Deng 2014
 */

package zemian.cdiexample.service;

import javax.enterprise.context.ApplicationScoped;
import zemian.service.logging.Logger;

/**
 *
 * @author zedeng
 */
@ApplicationScoped
public class SysPropsService implements Service {
    //private static final Logger LOGGER = new Logger(Hello.class);
    
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    public String getSysProp(String key) {
        return System.getProperty(key);
    }
}
