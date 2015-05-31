package zemian.mvcexample.web;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zemian.mvcexample.service.Application;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * A main controller dispatcher servlet for web application. This serve as hub of MVC layer. All web requests
 * should come through this controller and let the mapped path name instance handle the request. The controller should
 * use HttpServletRequest as read only var, and return a map of data model to let a View (JSP) to render the result.
 * Note that the name of controller path requested would be used for view name as well.
 *
 * <p>Url to controller and view path mapping examples:
 * "/main/hello"        => controllerName=hello, viewPath=/hello.jsp
 * "/main/hello/world"  => controllerName=hello, viewPath=/hello.jsp
 * "/main"              => controllerName=index, viewPath=/index.jsp
 * "/main/"             => controllerName=index, viewPath=/index.jsp
 * </p>
 *
 * <p>The mapping of controllers should be defined in the Application class.</p>
 *
 * User: zedeng
 * Date Time: 5/21/15 8:47 PM
 */
@WebServlet("/main/*")
public class ControllerServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerServlet.class);

    public static final String TEMPLATE_PATH = "/WEB-INF/jsp/"; // In relative to the content web directory.

    private Application app = Application.getInstance();
    private List<String> sortedControllerNames;

    @Override
    public void init() throws ServletException {
        // We want these names to be match to request path with longest name first!
        sortedControllerNames = new ArrayList<>(app.getControllerNames());
        Collections.sort(sortedControllerNames);
        Collections.reverse(sortedControllerNames);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirect if we are root of web app req
        // This can happen when it comes from landing page by web.xml welcome file list.
        String base = req.getContextPath() + req.getServletPath();
        if (req.getRequestURI().length() - 1 <= base.length()) {
            String controllerHome = req.getContextPath() + req.getServletPath() + "/index";
            LOGGER.info("Redirect URI={} to controller home: {}", req.getRequestURI(), controllerHome);
            resp.sendRedirect(controllerHome);
            return;
        }

        // Process normal request
        LOGGER.debug("Processing requestURI={}", req.getRequestURI());
        StopWatch watch = new StopWatch();
        watch.start();

        // Save additional controller request specific data.
        Map<String, Object> cdata = new HashMap<>();
        req.setAttribute("controller", cdata);

        // Parse the path for controller matching
        String controllerPath = parseControllerPath(req);
        String controllerName = resolveControllerName(controllerPath);
        if (controllerName == null)
            throw new ServletException("No controllerName found for path: " + controllerPath);
        Controller controller =  app.getController(controllerName);
        if (controller == null)
            throw new ServletException("No controller instance found for name: " + controllerName);
        LOGGER.debug("Handling request with controllerName={}, instance={}", controllerName, controller);

        cdata.put("name", controllerName);
        cdata.put("controllerPath", controllerPath);
        try {
            // Handle actual request by controller
            WebRequest wreq = new WebRequest();
            wreq.setControllerName(controllerName);
            wreq.setHttpServletRequest(req);
            MView mview = controller.handle(wreq);
            cdata.put("viewName", mview.getNames());
            LOGGER.debug("Resulted mview={}", mview);

            // Push resulted model data to request scope space.
            if (mview.hasData()) {
                for (String name : mview.getNames()) {
                    req.setAttribute(name, mview.get(name));
                }
            }

            // Done, now forward the data model to a view
            String viewPath = resolveViewPath(mview);
            cdata.put("viewPath", viewPath);

            watch.stop();
            LOGGER.info("Request {} => {} completed in {}", controllerName, viewPath, watch);
            req.getRequestDispatcher(viewPath).forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Failed to handle request " + controllerName + " by controller.", e);
        }
    }

    protected String resolveControllerName(String controllerPath) {
        String result = null;
        if (app.getController(controllerPath) == null) {
            // If there is no extract controllerName found, just try to find the closest match
            for (String name : sortedControllerNames) {
                if (controllerPath.startsWith(name)) {
                    result = name;
                    break;
                }
            }
        } else {
            result = controllerPath;
        }
        return result;
    }

    protected String resolveViewPath(MView mview) {
        return TEMPLATE_PATH + mview.getViewName() + ".jsp";
    }

    protected String parseControllerPath(HttpServletRequest req) throws ServletException {
        String uri = req.getRequestURI();
        LOGGER.trace("Parsing for controller name from uri={}", uri);

        // Use entire path after the controllerPath as controller name.
        String base = req.getContextPath() + req.getServletPath();
        // We just want to strip away the contextPath + controllerServletPath away
        String result = uri.substring(base.length());
        // Strip "/" prefix
        if (result.startsWith("/")) {
            result = result.substring(1);
        }//
        // If we do not have any value, then default to "index"
        if (result.equals("")) {
            result = "index";
        }

        // Return result
        return result;
    }
}
