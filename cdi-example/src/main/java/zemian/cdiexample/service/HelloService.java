/*
 *  Zemian Deng 2014
 */

package zemian.cdiexample.service;

import javax.enterprise.context.RequestScoped;

/**
 *
 * @author zedeng
 */
@RequestScoped
public class HelloService implements Service {
    
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    public String getMessage() {
        return "Hello World.";
    }
}
