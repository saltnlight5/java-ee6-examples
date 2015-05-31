package zemian.servlet3jspexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A note about Servlet and JSP req forwarding. If you do not place the .jsp files into "WEB-INF/jsp/test"
 * and simply match under "webapp/test", your view forward will never succeed because the mapping of this servlet
 * will comes first even when you forward the request! Therefore a view will never be reached.
 *
 * Just ensure the view you are using does not match to the Servlet path mapping used.
 *
 * Note#2. The forward view name should starts with "/".
 */
@WebServlet("/test/*")
public class TestServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String base = req.getContextPath() + req.getServletPath();
        String base = req.getContextPath();
        String view = req.getRequestURI().substring(base.length() + 1);
        LOGGER.info("RequestURI: {}", req.getRequestURI());
        LOGGER.debug("QueryString {}", req.getQueryString());

        if (view.equals("test/hello")) {
            req.setAttribute("name", "World");
        }

        view = "/WEB-INF/jsp/" + view + ".jsp";
        LOGGER.info("Forward to view: {}", view);
        req.getRequestDispatcher(view).forward(req, resp);
    }
}
