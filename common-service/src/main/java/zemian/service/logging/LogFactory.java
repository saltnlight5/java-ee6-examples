/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zemian.service.logging;

/**
 * A factory class to create Log service instances. This is the main entry point 
 * to use the Log service.
 * 
 * <p>Example:</p>
 * <pre>
 class MyService {
   Log log = LogFactory.createLog(MyService.class);
   public void run() {
     log.info(Message.msg("%s service is running now.", this));
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
