package zemian.servlet3example.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            .p(message)
            .p("Let's explore Java Servlet 3.x Features.")
            .ul(
                html.link("Index", "/index"),
                html.link("Hello", "/hello"),
                html.link("Table Display", "/tables"),
                html.link("Form", "/form"),
                html.link("Sys Props", "/sys-props"),
                html.link("List of users", "/user"),
                html.link("Thread Execution Information", "/thread-info")
            )
            .footer();
    }
    
    private String getMessage(HttpServletRequest req, HtmlWriter html) {
        // Construct a server message based on user logged in with session data or not.
        String message = "";
        LoginSession sd = getOptionalSessionData(req);
        if (sd != null) {
            message = "Welcome " + sd.getUsername() + "! You have been logged in since " + sd.getDateCreated();
            message += "(" + html.link("Logout", "/login?logout") + ")";
        }
        return message;
    }
}
