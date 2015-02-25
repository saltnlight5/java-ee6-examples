package zemian.service.logging;

import static zemian.service.logging.Message.msg;
import org.junit.Test;

/**
 * Note that depending on your JDK logging.properties configuration, you might not
 * able to see all level of logging messages!
 * 
 * <p>There is no asserts in this test, but just to exercise the logging calls
 * and user needs to visually inspect the outcome.
 *
 * @author zedeng
 */
public class JdkLogTest {
    private Log log = LogFactory.createLog(JdkLogTest.class);
    
    @Test
    public void testError() {
        log.error(msg("Test error message"));
        log.error(msg("Test %s message with parameters: %d %d %d", "error", 1, 2, 3));
        log.error(msg(new RuntimeException("Checkpoint"), "Test %s message", "error"));
    }

    @Test
    public void testWarn() {
        log.warn(msg("Test warn message"));
        log.warn(msg("Test %s message with parameters: %d %d %d", "warn", 1, 2, 3));
        log.warn(msg(new RuntimeException("Checkpoint"), "Test %s message", "warn"));
    }

    @Test
    public void testInfo() {
        log.info(msg("Test info message"));
        log.info(msg("Test %s message with parameters: %d %d %d", "info", 1, 2, 3));
        log.info(msg(new RuntimeException("Checkpoint"), "Test %s message", "info"));
    }

    @Test
    public void testDebug() {
        log.debug(msg("Test debug message"));
        log.debug(msg("Test %s message with parameters: %d %d %d", "debug", 1, 2, 3));
        log.debug(msg(new RuntimeException("Checkpoint"), "Test %s message", "debug"));
    }

    @Test
    public void testTrace() {
        log.trace(msg("Test error message"));
        log.trace(msg("Test %s message with parameters: %d %d %d", "trace", 1, 2, 3));
        log.trace(msg(new RuntimeException("Checkpoint"), "Test %s message", "trace"));
    }
    
}
