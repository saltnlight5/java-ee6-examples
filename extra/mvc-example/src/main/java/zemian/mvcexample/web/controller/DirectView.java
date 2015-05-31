package zemian.mvcexample.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zemian.mvcexample.service.Application;
import zemian.mvcexample.web.Controller;
import zemian.mvcexample.web.MView;
import zemian.mvcexample.web.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * A simple controller that does nothing but to return empty data model. User may use this to map as pass through
 * to a view page.
 *
 * User: zedeng
 * Date Time: 5/21/15 9:23 PM
 */
public class DirectView implements Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectView.class);

    private Application app;

    public DirectView(Application app) {
        this.app = app;
    }

    @Override
    public MView handle(WebRequest wreq) throws Exception {
        String subPath = getSubPath(wreq.getControllerName(), wreq.getHttpServletRequest());
        LOGGER.debug("Testing controller with subPath={}", subPath);
        return new MView(wreq.getControllerName() + subPath);
    }

    private String getSubPath(String controllerName, HttpServletRequest req) {
        String uri = req.getRequestURI();
        String base = req.getContextPath() + app.getControllerServletPath() + "/" + controllerName;
        String result = uri.substring(base.length());
        return result;
    }
}
