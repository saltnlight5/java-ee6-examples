/*
 *  Zemian Deng 2014
 */

package zemian.servlet3example.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import zemian.service.logging.Logger;

/**
 *
 * @author zedeng
 */
@WebFilter(urlPatterns={"/sys-props/*"})
public class SessionRequiredFilter implements Filter {
    private static final Logger LOGGER = new Logger(SessionRequiredFilter.class);
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;            
            LOGGER.trace("Checking session data for uri=%s", req.getRequestURI());
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute(SessionData.SESSION_DATA_KEY) == null) {
                LOGGER.debug("No session data found for current request uri=%s. Redirect to password page.", req.getRequestURI());
                //throw new RuntimeException("Session Data not found.");
                req.getRequestDispatcher("/password").forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
