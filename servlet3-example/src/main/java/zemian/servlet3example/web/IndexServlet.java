package zemian.servlet3example.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexServlet extends HtmlWriterServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HtmlWriter html = createHtmlWriter(req, resp);
        String message = getMessage(req, html);
        
        html.header()
            .h(1, "Welcome to Servlet 3 Example")
            .p("Let's explore Java Servlet 3.x Features.")
            .p(message)
            .ul(
                html.link("Index", "/index"),
                html.link("Hello", "/hello"),
                html.link("Sys Props (Password needed)", "/sys-props")
            )
            .footer();
    }
    
    private String getMessage(HttpServletRequest req, HtmlWriter html) {
        // Construct a server message based on user logged in with session data or not.
        String message = "";
        SessionData sd = getOptionalSessionData(req);
        if (sd != null) {
            message = "You are currently logged in since " + sd.getDateCreated();
            message += "(" + html.link("Logout", "/password?logout") + ")";
        }
        return message;
    }
}
