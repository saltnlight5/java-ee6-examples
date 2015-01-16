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
@WebFilter(urlPatterns={"/sys-props", "/user"})
public class LoginRequiredFilter implements Filter {
    private static final Logger LOGGER = new Logger(LoginRequiredFilter.class);
    public static final String LOGIN_REDIRECT = "LOGIN_REDIRECT";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;            
            LOGGER.trace("Checking session data for uri=%s", req.getRequestURI());
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute(LoginSession.LOGIN_SESSION_KEY) == null) {
                LOGGER.debug("No session data found for current request uri=%s. Forward request to login page.", req.getRequestURI());
                // We need to save the old URI so we can auto redirect after login.
                req.setAttribute(LOGIN_REDIRECT, req.getRequestURI());
                req.getRequestDispatcher("/login").forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
