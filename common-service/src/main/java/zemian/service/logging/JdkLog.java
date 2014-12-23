/*
 *  Zemian Deng 2014
 */

package zemian.service.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A log service that uses JDK java.util.logging to provide actual logging.
 * 
 * @author zedeng
 */
public class JdkLog implements Log {
    // A LOGGER is use in this class itself to help debug loading of JDK logger.
    private static final Logger LOGGER = Logger.getLogger(JdkLog.class.getName());
    // The jdkLogger is the instance that does the actual loggigng for a particular
    // given class that wants logging functionality.
    private Logger jdkLogger;

    public JdkLog(Class<?> loggerClass) {
        LOGGER.fine("Creating new LogService for " + loggerClass);
        jdkLogger = Logger.getLogger(loggerClass.getName());
    }

    @Override
    public void error(Message message) {
        if (jdkLogger.isLoggable(Level.SEVERE)) {
            jdkLogger.log(Level.SEVERE, message.getFormatedMessage(), message.getCause());
        }
    }

    @Override
    public void warn(Message message) {
        if (jdkLogger.isLoggable(Level.WARNING)) {
            jdkLogger.log(Level.WARNING, message.getFormatedMessage(), message.getCause());
        }
    }

    @Override
    public void info(Message message) {
        if (jdkLogger.isLoggable(Level.INFO)) {
            jdkLogger.log(Level.INFO, message.getFormatedMessage(), message.getCause());
        }
    }

    @Override
    public void debug(Message message) {
        if (jdkLogger.isLoggable(Level.FINE)) {
            jdkLogger.log(Level.FINE, message.getFormatedMessage(), message.getCause());
        }
    }

    @Override
    public void trace(Message message) {
        if (jdkLogger.isLoggable(Level.FINER)) {
            jdkLogger.log(Level.FINER, message.getFormatedMessage(), message.getCause());
        }
    }
}
