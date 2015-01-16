package zemian.servlet3example.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import zemian.service.logging.Logger;
import zemian.servlet3example.service.Application;
import zemian.servlet3example.service.UserService;

@WebServlet("/login")
public class LoginServlet  extends HtmlWriterServlet {
    private static final Logger LOGGER = new Logger(LoginServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HtmlWriter html = createHtmlWriter(req, resp); 
        String message;
        
        // Check to see if we are doing logout or not.
        LoginSession loginSession = getOptionalLoginSession(req);
        if (loginSession != null && req.getParameter("logout") != null) {
            logout(req);
            message = "Your have successfully logged out.";
        } else {    
            message = (String)req.getAttribute("message");
            if (message == null)
                message = "";
        }   
        
        // Show a login form
        String redirectUri = (String)req.getAttribute(LoginRequiredFilter.LOGIN_REDIRECT);
        String redirectHtmlTag = "";
        if (redirectUri != null) {
            redirectHtmlTag = "<input type='hidden' name='redirectUri' value='" + redirectUri + "'/>";
        }
        html.header()
            .h(1, "Please Login")
            .p(message)
            .println("<form method='post' action='login'>")
            .println(redirectHtmlTag)
            .println("<p/>Username: <input type='text' name='username'/>")
            .println("<p/>Password: <input type='password' name='password'/>")
            .println("<p/><input type='submit' value='Submit'/>")
            .println("</form>")
            .footer();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Processing login form.");
        if (login(req)) {
            // Login succeed, we should auto redirect user if exists.
            String redirectUri = req.getParameter("redirectUri");
            if (redirectUri != null) {
                LOGGER.debug("Redirect after login to: %s", redirectUri);
                resp.sendRedirect(redirectUri);
                return;
            }
        }
        
        // Show the form again in case login failed or user didn't provide a redirect
        doGet(req, resp);
    }    
       
    protected LoginSession createLoginSession(HttpServletRequest req, String username) {
        LoginSession result = new LoginSession(username);
        req.getSession(true).setAttribute(LoginSession.LOGIN_SESSION_KEY, result);
        return result;
    }
    
    protected void removeLoginSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute(LoginSession.LOGIN_SESSION_KEY);
        }
    }

    private boolean login(HttpServletRequest req) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        UserService userService = Application.getInstance().getUserService();
        if (userService.validPassword(username, password)) {
            LOGGER.info("User %s logged in successfully.", username);
            // Create Session Data here after successful authenticated.
            LoginSession loginsession = getOptionalLoginSession(req);
            if (loginsession == null) {
                createLoginSession(req, username);
                req.setAttribute("message", "You have successfully logged in.");
            } else {
                req.setAttribute("message", "You already have logged in.");             
            }
        } else {
            LOGGER.info("User %s failed to login.", username);
            req.setAttribute("message", "Invalid login.");
        }
        return true;
    }

    private void logout(HttpServletRequest req) {
        removeLoginSession(req);
        req.getSession().invalidate();
    }
    
    /** Return LoginSession if found in HttpSession scope, else return NULL value. */
    public static LoginSession getOptionalLoginSession(HttpServletRequest req) {
        LoginSession result = null;
        HttpSession session = req.getSession(false);
        if (session != null)
            result = (LoginSession)session.getAttribute(LoginSession.LOGIN_SESSION_KEY);
        return result;
    }
}
