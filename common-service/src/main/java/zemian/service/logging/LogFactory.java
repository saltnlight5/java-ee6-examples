/*
 *  Zemian Deng 2014
 */

package zemian.service.logging;

/**
 * A factory class to create Log service instances. This is the main entry point 
 * to use the Log service.
 * 
 * <p>Example:</p>
 * <pre>
 class MyService {
   static Log LOG = LogFactory.createLog(MyService.class);
   public void run() {
     LOG.info(Message.msg("%s service is running now.", this));
   }
 }
 </pre>
 * 
 * Or you can use the Logger helper class to have less imports.
 * <pre>
 class MyService2 {
   static Logger LOGGER = new Logger(MyService2.class);
   public void run() {
     LOGGER.info("%s service is running now.", this));
   }
 }
 </pre>
 * 
 * @author zedeng
 */
public class LogFactory {
    public static Log createLog(Class<?> loggerClass) {
        return new JdkLog(loggerClass);
    }
}
