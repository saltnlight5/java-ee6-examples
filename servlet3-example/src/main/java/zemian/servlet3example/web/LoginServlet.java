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
        String message = getMessage(req);
        
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
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String redirectUri = req.getParameter("redirectUri");
        
        UserService userService = Application.getInstance().getUserService();
        if (userService.validPassword(username, password)) {
            LOGGER.info("User %s logged in successfully.", username);
            // Create Session Data here after successful authenticated.
            LoginSession sd = getOptionalLoginSession(req);
            if (sd == null) {
                createSessionData(req, username);
                // We should auto redirect user if exists.
                if (redirectUri != null) {
                    LOGGER.debug("Redirect after login to: %s", redirectUri);
                    resp.sendRedirect(redirectUri);
                } else {
                    req.setAttribute("message", "You have successfully logged in.");
                }
            } else {
                req.setAttribute("message", "You already have logged in.");             
            }
        } else {
            LOGGER.info("User %s failed to login.", username);
            req.setAttribute("message", "Invalid login.");
        }
        
        doGet(req, resp);
    }    
       
    protected LoginSession createSessionData(HttpServletRequest req, String username) {
        LoginSession result = new LoginSession(username);
        req.getSession(true).setAttribute(LoginSession.LOGIN_SESSION_KEY, result);
        return result;
    }
    
    protected void removeSessionData(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute(LoginSession.LOGIN_SESSION_KEY);
        }
    }

    private String getMessage(HttpServletRequest req) {
        String message;
        if (req.getParameter("logout") != null) {
            removeSessionData(req);
            message = "Your have successfully logged out.";
        } else {
            message = (String)req.getAttribute("message");
        }        
        if (message ==  null) {
            message = "";
        }
        return message;
    }
}
