/*
 *  Zemian Deng 2014
 */

package zemian.service.logging;

/**
 * A convenient wrapper entry class to this logging package. This is used
 * so user do not need to import many classes and methods provided here is more
 * easier to use.
 * 
 * @author zedeng
 */
public class Logger {
    private Log log;
    public Logger(Class<?> loggerClass) {
        log = LogFactory.createLog(loggerClass);
    }
    public void error(Exception cause, String message, Object ... params) {
        log.error(Message.msg(cause, message, params));
    }
    public void warn(String message, Object ... params) {
        log.warn(Message.msg(message, params));
    }
    public void info(String message, Object ... params) {
        log.info(Message.msg(message, params));
    }
    public void debug(String message, Object ... params) {
        log.debug(Message.msg(message, params));
    }
    public void trace(String message, Object ... params) {
        log.trace(Message.msg(message, params));
    }
}
