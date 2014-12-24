package zemian.servlet3example.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import zemian.service.logging.Logger;

@WebServlet("/password")
public class PasswordServlet extends HtmlWriterServlet {
    private static final Logger LOGGER = new Logger(PasswordServlet.class);
    private static final String PASSWORD = "test123"; // Hide me.
    
    @Override
    protected void writeContent(HtmlWriter html) {        
        String message;
        if (html.getReq().getParameter("logout") != null) {
            removeSessionData(html.getReq());
            message = "Your have successfully logged out.";
        } else {
            message = (String)html.getReq().getAttribute("message");
        }        
        if (message ==  null) {
            message = "";
        }
        
        html.header()
            .h(1, "Restricted Area: Please Enter Password")
            .p(message)
            .out("<form method='post' action='password'>")
            .out("<p/><input type='password' name='password'/>")
            .out("<p/><input type='submit' value='Submit'/>")
            .out("</form>")
            .out(html.link("Back to Home", "/index"))
            .footer();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Processing password form.");
        String password = req.getParameter("password");
        if (!PASSWORD.equals(password)) {
            req.setAttribute("message", "Wrong password.");
        } else {
            // Create Session Data here after successful authenticated.
            SessionData sd = getOptionalSessionData(req);
            if (sd == null) {
                createSessionData(req);
                req.setAttribute("message", "You have successfully authenticated.");
            } else {
                req.setAttribute("message", "You already have authenticated.");             
            }
                
        }
        
        doGet(req, resp);
    }    
       
    protected SessionData createSessionData(HttpServletRequest req) {
        SessionData result = new SessionData();
        req.getSession(true).setAttribute(SessionData.SESSION_DATA_KEY, result);
        return result;
    }
    
    protected void removeSessionData(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute(SessionData.SESSION_DATA_KEY);
        }
    }
}
