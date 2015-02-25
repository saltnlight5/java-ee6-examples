package zemian.service.logging;

import org.junit.Test;

/**
 * @author zedeng
 */
public class LoggerTest {
    private static final Logger LOGGER = new Logger(LoggerTest.class);
    
    @Test
    public void testLogger() {
        LOGGER.trace("test");
        LOGGER.debug("test");
        LOGGER.info("test");
        LOGGER.warn("test");
        //LOGGER.error(new RuntimeException("Checkpoint"), "test");
        
        LOGGER.info("Hello %s", "World.");
        
        // Note that you can't use MessaggeFormat syntax!
        LOGGER.info("Hello {0}", "World.");
    }
}
