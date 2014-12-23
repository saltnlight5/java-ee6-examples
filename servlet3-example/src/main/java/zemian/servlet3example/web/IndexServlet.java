package zemian.servlet3example.web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/index")
public class IndexServlet extends HtmlWriterServlet {

    @Override
    protected void writeContent(HtmlWriter html) {
        html.header()
            .h(1, "Welcome to Servlet 3 Example")
            .p("Let's explore Java Servlet 3.x Features.")
            .ul(
                html.link("Index", getContextPath() + "/index"),
                html.link("Sys Props", getContextPath() + "/sys-props")
            )
            .footer();
    }
}
