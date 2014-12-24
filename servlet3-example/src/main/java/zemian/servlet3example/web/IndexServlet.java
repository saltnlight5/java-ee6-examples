package zemian.servlet3example.web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/index")
public class IndexServlet extends HtmlWriterServlet {

    @Override
    protected void writeContent(HtmlWriter html) {
        String message = "";
        SessionData sd = getOptionalSessionData(html.getReq());
        if (sd != null) {
            message = "You are currently logged in since " + sd.getDateCreated();
            message += "(" + html.link("Logout", "/password?logout") + ")";
        }
        html.header()
            .h(1, "Welcome to Servlet 3 Example")
            .p("Let's explore Java Servlet 3.x Features.")
            .p(message)
            .ul(
                html.link("Index", "/index"),
                html.link("Sys Props (Password needed)", "/sys-props")
            )
            .footer();
    }
}
