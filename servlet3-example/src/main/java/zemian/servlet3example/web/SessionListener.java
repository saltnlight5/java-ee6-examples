package zemian.servlet3example.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import zemian.service.logging.Logger;

/**
 * Just a session listener demo that LOG a message whenever a HTTP session object is created.
 * @author zedeng
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = new Logger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.debug("Session created %s", se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.debug("Session destroyed %s", se.getSession());
    }
}
