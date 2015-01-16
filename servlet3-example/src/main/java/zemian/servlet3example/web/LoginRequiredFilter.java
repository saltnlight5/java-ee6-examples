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
import zemian.service.logging.Logger;

/**
 * Restrict web resources to require users to be authenticated and LoginSession
 * token to be present.
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
            LOGGER.trace("Checking LoginSession token for uri=%s", req.getRequestURI());
            LoginSession loginSession = LoginServlet.getOptionalLoginSession(req);
            if (loginSession == null) {
                LOGGER.debug("No LoginSession token found; forwarding request to login page.");
                // We need to save the old URI so we can auto redirect after login.
                req.setAttribute(LOGIN_REDIRECT, req.getRequestURI());
                req.getRequestDispatcher("/login").forward(request, response);
                return;
            } else {
                LOGGER.debug("Request allowed using LoginSession token=%s", loginSession.getId());
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Do nothing.
    }

}
